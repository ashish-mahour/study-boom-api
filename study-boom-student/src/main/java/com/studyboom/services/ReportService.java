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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studyboom.domains.Student;
import com.studyboom.domains.StudentPerfromedTest;
import com.studyboom.dtos.Constants;
import com.studyboom.dtos.TestSeriesReportDTO;
import com.studyboom.repositories.StudentPerformedTestRepository;
import com.studyboom.repositories.StudentRepository;
import com.studyboom.resources.ReportResources;

@Service
public class ReportService implements ReportResources {

	private final Logger LOG = Logger.getLogger("OUT");
	private final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentPerformedTestRepository studentPerformedTestRepository;

	@Override
	public ResponseEntity<?> genrateReports(Long studentId) {

		try {

			Optional<Student> studentOptional = studentRepository.findById(studentId);

			if (!studentOptional.isPresent())
				return new ResponseEntity<>("No Publisher Found!!", HttpStatus.BAD_REQUEST);

			Student student = studentOptional.get();
			List<StudentPerfromedTest> testSeriesPerformedByStudent = student.getTestSeriesPerformendByStudent();

			if (testSeriesPerformedByStudent.size() == 0)
				return new ResponseEntity<>("No Test Performed!!", HttpStatus.BAD_REQUEST);

			XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
			XSSFSheet xssfSheet = xssfWorkbook.createSheet("Student Report.");

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

			for (StudentPerfromedTest studentPerfromedTest : testSeriesPerformedByStudent) {

				XSSFRow dataRow = xssfSheet.createRow(rowNum);

				XSSFCell dataCell1 = dataRow.createCell(0);
				dataCell1.setCellValue(studentPerfromedTest.getTestSeriesPerformed().getName());
				dataCell1.setCellStyle(data);

				XSSFCell dataCell2 = dataRow.createCell(1);
				double attempedPercentage = ((double) studentPerfromedTest.getAttemped()
						/ studentPerfromedTest.getTestSeriesPerformed().getTotalQuestions()) * 100;
				dataCell2.setCellValue(attempedPercentage);
				dataCell2.setCellStyle(data);

				XSSFCell dataCell3 = dataRow.createCell(2);
				dataCell3.setCellValue(studentPerfromedTest.getTestSeriesPerformed().getTotalMarks());
				dataCell3.setCellStyle(data);

				XSSFCell dataCell4 = dataRow.createCell(3);
				double marksPercentage = ((double) studentPerfromedTest.getTotalScore()
						/ studentPerfromedTest.getTestSeriesPerformed().getTotalMarks()) * 100;
				dataCell4.setCellValue(marksPercentage);
				dataCell4.setCellStyle(data);

				rowNum++;
			}

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			xssfWorkbook.write(outputStream);
			xssfWorkbook.close();
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(
					new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
			httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=Student-Report-" + LocalDateTime.now().format(DATE_TIME_FORMAT) + ".xlsx");
			httpHeaders.set("FILE_NAME", "Student-Report-" + LocalDateTime.now().format(DATE_TIME_FORMAT) + ".xlsx");
			httpHeaders.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION + ", FILE_NAME");
			
			return new ResponseEntity<>(outputStream.toByteArray(), httpHeaders, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error in creating Excel file for Student Report : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getReports(Long studentId) {
		try {
			Optional<Student> studentOptional = studentRepository.findById(studentId);

			if (!studentOptional.isPresent())
				return new ResponseEntity<>("No Publisher Found!!", HttpStatus.BAD_REQUEST);

			Student student = studentOptional.get();
			List<StudentPerfromedTest> testSeriesPerformedByStudent = studentPerformedTestRepository.findByPerformendByStudent(student, PageRequest.of(0, 20, Direction.DESC, "performedAt"));

			List<TestSeriesReportDTO> testSeriesReportDTOs = new ArrayList<TestSeriesReportDTO>();
			if (testSeriesPerformedByStudent.size() == 0)
				return new ResponseEntity<>(testSeriesReportDTOs, HttpStatus.OK);
			
			for (StudentPerfromedTest studentPerfromedTest : testSeriesPerformedByStudent) {

				double marksPercentage = ((double) studentPerfromedTest.getTotalScore()
						/ studentPerfromedTest.getTestSeriesPerformed().getTotalMarks()) * 100;
				double attempedPercentage = ((double) studentPerfromedTest.getAttemped()
						/ studentPerfromedTest.getTestSeriesPerformed().getTotalQuestions()) * 100;

				testSeriesReportDTOs.add(new TestSeriesReportDTO(
						studentPerfromedTest.getTestSeriesPerformed().getName(), attempedPercentage,
						studentPerfromedTest.getTestSeriesPerformed().getTotalMarks(), marksPercentage));
			}
			return new ResponseEntity<>(testSeriesReportDTOs, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error in getting Test Series Report for Student : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
