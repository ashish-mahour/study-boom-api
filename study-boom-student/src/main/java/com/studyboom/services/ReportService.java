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

import com.studyboom.domains.Student;
import com.studyboom.domains.StudentPerfromedTest;
import com.studyboom.dtos.Constants;
import com.studyboom.repositories.StudentRepository;
import com.studyboom.resources.ReportResources;

@Service
public class ReportService implements ReportResources {

	private final Logger LOG = Logger.getLogger("OUT");
	private final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public ResponseEntity<byte[]> getReports(Long studentId) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try {

			Optional<Student> studentOptional = studentRepository.findById(studentId);

			if (!studentOptional.isPresent())
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

			Student student = studentOptional.get();
			List<StudentPerfromedTest> testSeriesPerformedByStudent = student.getTestSeriesPerformendByStudent();

			if (testSeriesPerformedByStudent.size() == 0)
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

			for (StudentPerfromedTest studentPerfromedTest : testSeriesPerformedByStudent) {

				XSSFRow dataRow = xssfSheet.createRow(rowNum);

				XSSFCell dataCell1 = dataRow.createCell(0);
				dataCell1.setCellValue(studentPerfromedTest.getTestSeriesPerformed().getName());
				dataCell1.setCellStyle(data);

				XSSFCell dataCell2 = dataRow.createCell(1);
				double attempedPercentage = (studentPerfromedTest.getAttemped() / studentPerfromedTest.getAttemped()
						+ studentPerfromedTest.getUnattemped()) * 100;
				dataCell2.setCellValue(attempedPercentage);
				dataCell2.setCellStyle(data);

				XSSFCell dataCell3 = dataRow.createCell(2);
				dataCell3.setCellValue(studentPerfromedTest.getTestSeriesPerformed().getTotalMarks());
				dataCell3.setCellStyle(data);

				XSSFCell dataCell4 = dataRow.createCell(3);
				dataCell4.setCellValue(studentPerfromedTest.getTotalScore());
				dataCell4.setCellStyle(data);

				rowNum++;
			}

			xssfWorkbook.write(outputStream);
			xssfWorkbook.close();

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename: \"Student-Report-" + LocalDateTime.now().format(DATE_TIME_FORMAT) + "\"");

			return new ResponseEntity<byte[]>(outputStream.toByteArray(), httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("Error in creating Excel file for Publisher Report : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
