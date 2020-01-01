package com.studyboom.services;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.studyboom.resources.ReportResources;

@Service
public class ReportService implements ReportResources {

	private final Logger LOG = Logger.getLogger("OUT");
	private final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	@Autowired
	private PublisherRepository publisherRepository;

	@Override
	public ResponseEntity<byte[]> getReports(Long publisherId) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try {

			Optional<Publisher> publisherOptional = publisherRepository.findById(publisherId);

			if (!publisherOptional.isPresent())
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

			Publisher publisher = publisherOptional.get();
			List<TestSeries> publisherUploadedTestSeries = publisher.getUploadedByPublisherTestSeries();

			if (publisherUploadedTestSeries.size() == 0)
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

			XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
			XSSFSheet xssfSheet = xssfWorkbook.cloneSheet(0);

			int rowNum = 0;

			/*
			 * HEADER STYLE
			 */

			XSSFCellStyle header = xssfWorkbook.createCellStyle();
			XSSFFont headerFont = xssfWorkbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeight((short) 16);
			header.setFont(headerFont);
			header.setWrapText(true);
			header.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
			header.setFillForegroundColor(IndexedColors.WHITE.getIndex());

			/*
			 * DATA STYLE
			 */

			XSSFCellStyle data = xssfWorkbook.createCellStyle();
			XSSFFont dataFont = xssfWorkbook.createFont();
			dataFont.setFontHeight((short) 14);
			data.setFont(headerFont);
			data.setWrapText(true);
			data.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
			data.setFillForegroundColor(IndexedColors.BLACK.getIndex());

			/*
			 * HEADER ROW
			 */

			XSSFRow row = xssfSheet.createRow(rowNum);
			for (int i = 0; i < Constants.reportsColumns.length; i++) {
				XSSFCell cell = row.createCell(i);
				cell.setCellValue(Constants.reportsColumns[i]);
				cell.setCellStyle(header);
				xssfSheet.setColumnWidth(i, Constants.reportDefaultColumnSize);
			}
			rowNum++;

			for (TestSeries testSeries : publisherUploadedTestSeries) {

				List<TestSeriesRatings> testSeriesRatings = testSeries.getTestSeriesIdToRatings();

				if (testSeriesRatings.size() == 0)
					continue;

				TestSeriesReportDTO testSeriesReportDTO = new TestSeriesReportDTO();
				testSeriesReportDTO.setTestSeriesName(testSeries.getName());

				for (TestSeriesRatings testRatings : testSeriesRatings) {
					if (testSeriesReportDTO.getOverAllRating() == null)
						testSeriesReportDTO.setOverAllRating(testRatings.getOverallRatings());
					else
						testSeriesReportDTO.setOverAllRating(
								testSeriesReportDTO.getOverAllRating() + testRatings.getOverallRatings());

				}

				XSSFRow dataRow = xssfSheet.createRow(rowNum);

				XSSFCell dataCell1 = dataRow.createCell(0);
				dataCell1.setCellValue(testSeriesReportDTO.getTestSeriesName());
				dataCell1.setCellStyle(data);

				XSSFCell dataCell2 = dataRow.createCell(1);
				dataCell2.setCellValue(testSeriesReportDTO.getOverAllRating() / testSeriesRatings.size());
				dataCell2.setCellStyle(data);

				rowNum++;
			}

			xssfWorkbook.write(outputStream);
			xssfWorkbook.close();

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename: \"Publisher-Report-" + LocalDateTime.now().format(DATE_TIME_FORMAT) + "\"");

			return new ResponseEntity<byte[]>(outputStream.toByteArray(), httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("Error in creating Excel file for Publisher Report : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
