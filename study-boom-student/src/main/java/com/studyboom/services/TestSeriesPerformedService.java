package com.studyboom.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studyboom.domains.Student;
import com.studyboom.domains.StudentChoosenSubjectSubCategory;
import com.studyboom.domains.StudentPerfromedTest;
import com.studyboom.domains.SubjectSubCategory;
import com.studyboom.domains.TestSeries;
import com.studyboom.domains.TestSeriesData;
import com.studyboom.dtos.StudentChoosedAnswersDTO;
import com.studyboom.dtos.StudentPerformedTestDTO;
import com.studyboom.dtos.TestSeriesPerformedStatusDTO;
import com.studyboom.repositories.StudentPerformedTestRepository;
import com.studyboom.repositories.StudentRepository;
import com.studyboom.repositories.TestSeriesDataRepository;
import com.studyboom.repositories.TestSeriesReposiroty;
import com.studyboom.resources.TestSeriesPerformedResource;

@Service
public class TestSeriesPerformedService implements TestSeriesPerformedResource {

	private final Logger LOG = Logger.getLogger("OUT");

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TestSeriesReposiroty testSeriesReposiroty;

	@Autowired
	private StudentPerformedTestRepository studentPerformedTestRepository;

	@Autowired
	private TestSeriesDataRepository testSeriesDataRepository;

	@Override
	public ResponseEntity<List<TestSeries>> getTestSeriesByCategories(Long studentId, int pageNo, int limit) {
		List<TestSeries> testSeries = null;
		try {
			Optional<Student> studentOptional = studentRepository.findById(studentId);

			if (!studentOptional.isPresent())
				return new ResponseEntity<List<TestSeries>>(HttpStatus.BAD_REQUEST);

			Student student = studentOptional.get();

			List<StudentChoosenSubjectSubCategory> studentChoosenSubjectSubCategories = student
					.getStudentIdToChoosenSubCategories();

			if (studentChoosenSubjectSubCategories.size() == 0)
				return new ResponseEntity<List<TestSeries>>(HttpStatus.BAD_REQUEST);

			List<SubjectSubCategory> studentChoosedSubCategories = new ArrayList<SubjectSubCategory>();
			for (StudentChoosenSubjectSubCategory studentChoosenSubjectSubCategory : studentChoosenSubjectSubCategories)
				studentChoosedSubCategories
						.add(studentChoosenSubjectSubCategory.getSubjectSubCategoryIdToChoosenSubCategories());

			testSeries = testSeriesReposiroty.findBySubjectSubCategoryIdToTestSeriesIn(studentChoosedSubCategories,
					PageRequest.of(pageNo, limit, Direction.ASC, "name"));

			return new ResponseEntity<List<TestSeries>>(testSeries, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error while getting test series by categories : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<List<TestSeries>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<List<StudentPerfromedTest>> getPerformedTestSeries(Long studentId, int pageNo, int limit) {
		try {
			Optional<Student> studentOptional = studentRepository.findById(studentId);

			if (!studentOptional.isPresent())
				return new ResponseEntity<List<StudentPerfromedTest>>(HttpStatus.BAD_REQUEST);

			Student student = studentOptional.get();
			return new ResponseEntity<List<StudentPerfromedTest>>(
					studentPerformedTestRepository.findByPerformendByStudent(student), HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error while getting test series by ID : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<List<StudentPerfromedTest>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<TestSeriesPerformedStatusDTO> testPeformed(StudentPerformedTestDTO studentPerformedTestDTO) {
		try {
			Optional<Student> studentOptional = studentRepository.findById(studentPerformedTestDTO.getStudentId());

			if (!studentOptional.isPresent())
				return new ResponseEntity<TestSeriesPerformedStatusDTO>(
						new TestSeriesPerformedStatusDTO(null, "Student ID not found!!", 0), HttpStatus.BAD_REQUEST);

			Optional<TestSeries> testSeriesOptional = testSeriesReposiroty
					.findById(studentPerformedTestDTO.getTestSeriesId());

			if (!testSeriesOptional.isPresent())
				return new ResponseEntity<TestSeriesPerformedStatusDTO>(
						new TestSeriesPerformedStatusDTO(null, "Test ID not found!!", 0), HttpStatus.BAD_REQUEST);

			TestSeries testSeries = testSeriesOptional.get();

			int eachQuestionMarks = testSeries.getTotalMarks() / testSeries.getTotalQuestions();
			Integer totalMarks = 0;
			for (StudentChoosedAnswersDTO studentChoosedAnswersDTO : studentPerformedTestDTO
					.getStudentChoosedAnswers()) {
				Optional<TestSeriesData> testSeriesDataOptional = testSeriesDataRepository
						.findById(studentChoosedAnswersDTO.getQuestionId());
				if (!testSeriesDataOptional.isPresent())
					continue;
				TestSeriesData testSeriesData = testSeriesDataOptional.get();
				if (testSeriesData.getAnswerText().equalsIgnoreCase(studentChoosedAnswersDTO.getChoosedAnswer()))
					totalMarks += eachQuestionMarks;
			}

			studentPerformedTestRepository.save(new StudentPerfromedTest(studentOptional.get(), testSeries,
					studentPerformedTestDTO.getAttempted(), studentPerformedTestDTO.getUnattemped(), totalMarks,
					studentPerformedTestDTO.getTimeTaken(), LocalDateTime.now()));

		} catch (Exception e) {
			LOG.error("Error while performin test series : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<TestSeriesPerformedStatusDTO>(
					new TestSeriesPerformedStatusDTO(null,
							"Error while performin test series : " + e.getLocalizedMessage(), 0),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}

}
