package com.studyboom.services;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studyboom.domains.Publisher;
import com.studyboom.domains.TestSeries;
import com.studyboom.domains.TestSeriesRatings;
import com.studyboom.dtos.Constants;
import com.studyboom.dtos.TestSeriesReportDTO;
import com.studyboom.repositories.PublisherRepository;
import com.studyboom.repositories.TestSeriesReposiroty;
import com.studyboom.resources.ReportResources;

@Service
public class ReportService implements ReportResources {

	private final Logger LOG = Logger.getLogger("OUT");
	private final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired
	private TestSeriesReposiroty testSeriesReposiroty;

	@Override
	public ResponseEntity<?> genrateReports(Long publisherId) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try {

			Optional<Publisher> publisherOptional = publisherRepository.findById(publisherId);

			if (!publisherOptional.isPresent())
				return new ResponseEntity<>("No Publisher Found.", HttpStatus.BAD_REQUEST);

			Publisher publisher = publisherOptional.get();
			List<TestSeries> publisherUploadedTestSeries = publisher.getUploadedByPublisherTestSeries();

			if (publisherUploadedTestSeries.size() == 0)
				return new ResponseEntity<>("No Test Series Uploaded.", HttpStatus.BAD_REQUEST);

			XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
			XSSFSheet xssfSheet = xssfWorkbook.createSheet("Publisher Report.");

			int rowNum = 0;

			/*
			 * HEADER STYLE
			 */

			XSSFCellStyle header = xssfWorkbook.createCellStyle();
			XSSFFont headerFont = xssfWorkbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.WHITE.getIndex());
			header.setFont(headerFont);
			header.setWrapText(true);
			header.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			header.setFillBackgroundColor(IndexedColors.BLACK.getIndex());

			/*
			 * DATA STYLE
			 */

			XSSFCellStyle data = xssfWorkbook.createCellStyle();
			XSSFFont dataFont = xssfWorkbook.createFont();
			dataFont.setFontHeightInPoints((short) 12);
			data.setFont(dataFont);
			data.setWrapText(true);

			/*
			 * HEADER ROW
			 */

			XSSFRow row = xssfSheet.createRow(rowNum);
			for (int i = 0; i < Constants.reportsColumns.length; i++) {
				XSSFCell cell = row.createCell(i);
				cell.setCellValue(Constants.reportsColumns[i]);
				cell.setCellStyle(header);
				xssfSheet.autoSizeColumn(i);
			}
			rowNum++;

			for (TestSeries testSeries : publisherUploadedTestSeries) {

				List<TestSeriesRatings> testSeriesRatings = testSeries.getTestSeriesIdToRatings();

				if (testSeriesRatings.size() == 0)
					continue;

				Integer totalRatings = null;
				for (TestSeriesRatings testRatings : testSeriesRatings) {
					if (totalRatings == null)
						totalRatings = testRatings.getOverallRatings();
					else
						totalRatings = totalRatings + testRatings.getOverallRatings();
				}

				XSSFRow dataRow = xssfSheet.createRow(rowNum);
				XSSFCell dataCell1 = dataRow.createCell(0);
				dataCell1.setCellValue(testSeries.getName());
				dataCell1.setCellStyle(data);

				XSSFCell dataCell2 = dataRow.createCell(1);
				dataCell2.setCellValue((totalRatings == null ? 0 : totalRatings) / testSeriesRatings.size());
				dataCell2.setCellStyle(data);

				rowNum++;
			}

			xssfWorkbook.write(outputStream);
			xssfWorkbook.close();

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename: Publisher-Report-" + LocalDateTime.now().format(DATE_TIME_FORMAT) + ".xlsx");
			httpHeaders.set("FILE_NAME", "Publisher-Report-" + LocalDateTime.now().format(DATE_TIME_FORMAT) + ".xlsx");
			httpHeaders.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION + ", FILE_NAME");
			
			return new ResponseEntity<byte[]>(outputStream.toByteArray(), httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("Error in creating Excel file for Publisher Report : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getReports(Long publisherId) {
		try {
			Optional<Publisher> publisherOptional = publisherRepository.findById(publisherId);

			if (!publisherOptional.isPresent())
				return new ResponseEntity<>("No Publisher Found.", HttpStatus.BAD_REQUEST);

			Publisher publisher = publisherOptional.get();
			List<TestSeries> publisherUploadedTestSeries = testSeriesReposiroty.findByUploadedByPublisher(publisher, PageRequest.of(0, 20, Direction.DESC, "createdDate"));

			List<TestSeriesReportDTO> testSeriesReportDTOs = new ArrayList<TestSeriesReportDTO>();

			if (publisherUploadedTestSeries.size() == 0)
				return new ResponseEntity<>(testSeriesReportDTOs, HttpStatus.OK);

			for (TestSeries testSeries : publisherUploadedTestSeries) {
				List<TestSeriesRatings> testSeriesRatings = testSeries.getTestSeriesIdToRatings();

				if (testSeriesRatings.size() == 0)
					continue;

				Integer totalRatings = null;
				for (TestSeriesRatings testRatings : testSeriesRatings) {
					if (totalRatings == null)
						totalRatings = testRatings.getOverallRatings();
					else
						totalRatings = totalRatings + testRatings.getOverallRatings();
				}

				testSeriesReportDTOs.add(new TestSeriesReportDTO(testSeries.getName(),
						(totalRatings == null ? 0 : totalRatings) / testSeriesRatings.size()));
			}

			return new ResponseEntity<>(testSeriesReportDTOs, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error in getting Test Series Report for Student : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
