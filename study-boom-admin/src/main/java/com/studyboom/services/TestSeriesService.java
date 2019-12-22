package com.studyboom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studyboom.domains.TestSeries;
import com.studyboom.dtos.TestSeriesStatusDTO;
import com.studyboom.repositories.TestSeriesDataRepository;
import com.studyboom.repositories.TestSeriesReposiroty;
import com.studyboom.resources.TestSeriesResources;

@Service
public class TestSeriesService implements TestSeriesResources {

	@Autowired
	private TestSeriesReposiroty testSeriesRepository;

	@Autowired
	private TestSeriesDataRepository testSeriesDataRepository;

	@Override
	public ResponseEntity<TestSeriesStatusDTO> deleteTestSeries(Long testSeriesId) {
		Optional<TestSeries> testSeriesOptional = testSeriesRepository.findById(testSeriesId);
		if (!testSeriesOptional.isPresent())
			return new ResponseEntity<TestSeriesStatusDTO>(new TestSeriesStatusDTO(null, "Invalid Test Series Id!!", 0),
					HttpStatus.BAD_REQUEST);
		TestSeries testSeries = testSeriesOptional.get();

		testSeriesDataRepository.deleteAll(testSeries.getTestSeriesIdToTestSeriesData());

		testSeriesRepository.delete(testSeries);

		return new ResponseEntity<TestSeriesStatusDTO>(
				new TestSeriesStatusDTO(testSeriesId, "Test Series Deleted!!", 1), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TestSeries>> getAllTestSeries(int pageNo, int limit) {
		return new ResponseEntity<List<TestSeries>>(
				testSeriesRepository.findAll(PageRequest.of(pageNo, limit, Direction.ASC, "name")).getContent(),
				HttpStatus.OK);
	}

}
