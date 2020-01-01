package com.studyboom.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studyboom.domains.Publisher;
import com.studyboom.domains.SubjectSubCategory;
import com.studyboom.domains.TestSeries;
import com.studyboom.domains.TestSeriesData;
import com.studyboom.dtos.TestSeriesDetailsDTO;
import com.studyboom.dtos.TestSeriesQuestionDTO;
import com.studyboom.dtos.TestSeriesStatusDTO;
import com.studyboom.repositories.PublisherRepository;
import com.studyboom.repositories.SubjectSubCategoryRepository;
import com.studyboom.repositories.TestSeriesDataRepository;
import com.studyboom.repositories.TestSeriesReposiroty;
import com.studyboom.resources.TestSeriesResources;

@Service
public class TestSeriesService implements TestSeriesResources {

	private final Logger LOG = Logger.getLogger("OUT");

	@Autowired
	private TestSeriesReposiroty testSeriesRepository;

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private SubjectSubCategoryRepository subjectSubCategoryRepository;

	@Autowired
	private TestSeriesDataRepository testSeriesDataRepository;

	@Override
	public ResponseEntity<TestSeriesStatusDTO> createTestSeries(TestSeriesDetailsDTO testSeriesDetailsDTO) {
		try {
			Optional<Publisher> publisherOptiional = publisherRepository
					.findById(testSeriesDetailsDTO.getPublisherId());
			if (!publisherOptiional.isPresent())
				return new ResponseEntity<TestSeriesStatusDTO>(
						new TestSeriesStatusDTO(null, "Invalid Publisher Id!!", 0), HttpStatus.BAD_REQUEST);

			Optional<SubjectSubCategory> subjectSubCategotyOptional = subjectSubCategoryRepository
					.findById(testSeriesDetailsDTO.getSubCategoryId());
			if (!subjectSubCategotyOptional.isPresent())
				return new ResponseEntity<TestSeriesStatusDTO>(
						new TestSeriesStatusDTO(null, "Invalid Subject Sub Category Id!!", 0), HttpStatus.BAD_REQUEST);

			TestSeries testSeries = testSeriesRepository.save(new TestSeries(publisherOptiional.get(),
					testSeriesDetailsDTO.getTestSeriesName(), subjectSubCategotyOptional.get(),
					testSeriesDetailsDTO.getTotalQuestions(), testSeriesDetailsDTO.getDurationMax(),
					testSeriesDetailsDTO.getTotalMarks(), testSeriesDetailsDTO.getPassingMarks(),
					testSeriesDetailsDTO.getPrice(), LocalDateTime.now(), LocalDateTime.now()));

			createQuestions(testSeries, subjectSubCategotyOptional.get(),
					testSeriesDetailsDTO.getTestSeriesQuestions());

			return new ResponseEntity<TestSeriesStatusDTO>(
					new TestSeriesStatusDTO(testSeries.getId(), "TestSeries Saved!!", 1), HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error while creating test series : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<TestSeriesStatusDTO>(
					new TestSeriesStatusDTO(null, "Error while creating test series : " + e.getLocalizedMessage(), 1),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void createQuestions(TestSeries testSeries, SubjectSubCategory subjectSubCategory,
			List<TestSeriesQuestionDTO> testSeriesQuestions) {
		try {
			for (TestSeriesQuestionDTO testSeriesQuestionDTO : testSeriesQuestions)
				testSeriesDataRepository.save(new TestSeriesData(testSeries, subjectSubCategory,
						testSeriesQuestionDTO.getQuestionText(), testSeriesQuestionDTO.getAnswerText(),
						testSeriesQuestionDTO.getChoice1(), testSeriesQuestionDTO.getChoice2(),
						testSeriesQuestionDTO.getChoice3(), testSeriesQuestionDTO.getChoice4()));
		} catch (Exception e) {
			LOG.error("Error while creating questions : " + e.getLocalizedMessage(), e);
		}

	}

	@Override
	public ResponseEntity<TestSeriesStatusDTO> modifyTestSeries(TestSeriesDetailsDTO testSeriesDetailsDTO) {
		try {
			Optional<TestSeries> testSeriesOptional = testSeriesRepository
					.findById(testSeriesDetailsDTO.getTestSeriesId());
			if (!testSeriesOptional.isPresent())
				return new ResponseEntity<TestSeriesStatusDTO>(
						new TestSeriesStatusDTO(null, "Invalid Test Series Id!!", 0), HttpStatus.BAD_REQUEST);

			TestSeries testSeries = testSeriesOptional.get();

			Optional<Publisher> publisherOptiional = publisherRepository
					.findById(testSeriesDetailsDTO.getPublisherId());
			if (!publisherOptiional.isPresent())
				return new ResponseEntity<TestSeriesStatusDTO>(
						new TestSeriesStatusDTO(testSeries.getId(), "Invalid Publisher Id!!", 0),
						HttpStatus.BAD_REQUEST);

			Optional<SubjectSubCategory> subjectSubCategotyOptional = subjectSubCategoryRepository
					.findById(testSeriesDetailsDTO.getSubCategoryId());
			if (!subjectSubCategotyOptional.isPresent())
				return new ResponseEntity<TestSeriesStatusDTO>(
						new TestSeriesStatusDTO(testSeries.getId(), "Invalid Subject Sub Category Id!!", 0),
						HttpStatus.BAD_REQUEST);

			updateTestSeries(testSeriesDetailsDTO, testSeries, publisherOptiional.get(),
					subjectSubCategotyOptional.get());
			return new ResponseEntity<TestSeriesStatusDTO>(
					new TestSeriesStatusDTO(testSeries.getId(), "Test Series Updated!!", 1), HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error while modifing test series : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<TestSeriesStatusDTO>(
					new TestSeriesStatusDTO(null, "Error while modifing test series : " + e.getLocalizedMessage(), 1),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void updateTestSeries(TestSeriesDetailsDTO testSeriesDetailsDTO, TestSeries testSeries, Publisher publisher,
			SubjectSubCategory subjectSubCategory) {
		try {
			if (testSeriesDetailsDTO.getTestSeriesName() != null)
				testSeries.setName(testSeriesDetailsDTO.getTestSeriesName());

			testSeries.setUploadedByPublisher(publisher);
			testSeries.setSubjectSubCategoryIdToTestSeries(subjectSubCategory);

			if (testSeriesDetailsDTO.getTotalQuestions() != null)
				testSeries.setTotalQuestions(testSeriesDetailsDTO.getTotalQuestions());

			if (testSeriesDetailsDTO.getDurationMax() != null)
				testSeries.setDurationMin(testSeriesDetailsDTO.getDurationMax());

			if (testSeriesDetailsDTO.getTotalMarks() != null)
				testSeries.setTotalMarks(testSeriesDetailsDTO.getTotalMarks());

			if (testSeriesDetailsDTO.getPassingMarks() != null)
				testSeries.setPassingMarks(testSeriesDetailsDTO.getPassingMarks());

			if (testSeriesDetailsDTO.getPrice() != null)
				testSeries.setPrice(testSeriesDetailsDTO.getPrice());

			testSeries.setModifiedDate(LocalDateTime.now());

			updateQuestions(testSeriesDetailsDTO.getTestSeriesQuestions(), testSeries, subjectSubCategory);

			testSeriesRepository.save(testSeries);
		} catch (Exception e) {
			LOG.error("Error while updating test series : " + e.getLocalizedMessage(), e);
		}
	}

	private void updateQuestions(List<TestSeriesQuestionDTO> testSeriesQuestions, TestSeries testSeries,
			SubjectSubCategory subjectSubCategory) {
		try {
			List<TestSeriesData> testSeriesDatas = testSeriesDataRepository
					.findByTestSeriesIdToTestSeriesData(testSeries);
			if (testSeriesDatas.size() > 0)
				testSeriesDataRepository.deleteAll(testSeriesDatas);

			createQuestions(testSeries, subjectSubCategory, testSeriesQuestions);
		} catch (Exception e) {
			LOG.error("Error while updating questions : " + e.getLocalizedMessage(), e);
		}

	}

	@Override
	public ResponseEntity<List<TestSeries>> getTestSeriesById(Long publisherId, int pageNo, int limit) {
		try {
			Optional<Publisher> publisherOptiional = publisherRepository.findById(publisherId);
			if (!publisherOptiional.isPresent())
				return new ResponseEntity<List<TestSeries>>(HttpStatus.BAD_REQUEST);

			List<TestSeries> testSeries = testSeriesRepository.findByUploadedByPublisher(publisherOptiional.get(),
					PageRequest.of(pageNo, limit, Direction.ASC, "name"));
			return new ResponseEntity<List<TestSeries>>(testSeries, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error while geting test series : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<List<TestSeries>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
