package com.studyboom.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studyboom.domains.Student;
import com.studyboom.domains.TestSeries;
import com.studyboom.domains.TestSeriesRatings;
import com.studyboom.dtos.TestSeriesRatingDTO;
import com.studyboom.dtos.TestSeriesRatingStatusDTO;
import com.studyboom.repositories.StudentRepository;
import com.studyboom.repositories.TestSeriesRatingsRepository;
import com.studyboom.repositories.TestSeriesReposiroty;
import com.studyboom.resources.RatingsResources;

@Service
public class RatingService implements RatingsResources {

	@Autowired
	private TestSeriesRatingsRepository testSeriesRatingsRepository;

	@Autowired
	private TestSeriesReposiroty testSeriesReposiroty;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public ResponseEntity<TestSeriesRatingStatusDTO> submitRatings(TestSeriesRatingDTO testSeriesRatingDTO) {
		Optional<Student> studentOptional = studentRepository.findById(testSeriesRatingDTO.getStudentId());

		if (!studentOptional.isPresent())
			return new ResponseEntity<TestSeriesRatingStatusDTO>(
					new TestSeriesRatingStatusDTO("No Student Found.", null), HttpStatus.OK);

		Optional<TestSeries> testSeriesOptional = testSeriesReposiroty.findById(testSeriesRatingDTO.getTestSeriesId());

		if (!testSeriesOptional.isPresent())
			return new ResponseEntity<TestSeriesRatingStatusDTO>(
					new TestSeriesRatingStatusDTO("No Test Series Found.", null), HttpStatus.OK);

		TestSeriesRatings testSeriesRatings = testSeriesRatingsRepository.save(new TestSeriesRatings(
				studentOptional.get(), testSeriesOptional.get(), testSeriesRatingDTO.getDifficulty(),
				testSeriesRatingDTO.getQuestionsQuality(), testSeriesRatingDTO.getAnswersQuality(),
				testSeriesRatingDTO.getPriceRating(), testSeriesRatingDTO.getOverallRatings()));
		return new ResponseEntity<TestSeriesRatingStatusDTO>(
				new TestSeriesRatingStatusDTO("Ratings Submitted.", testSeriesRatings.getId()), HttpStatus.OK);
	}

}
