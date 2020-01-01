package com.studyboom.services;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
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

	private final Logger LOG = Logger.getLogger("OUT");

	@Autowired
	private TestSeriesReposiroty testSeriesRepository;

	@Autowired
	private TestSeriesDataRepository testSeriesDataRepository;

	@Override
	public ResponseEntity<TestSeriesStatusDTO> deleteTestSeries(Long testSeriesId) {
		try {
			Optional<TestSeries> testSeriesOptional = testSeriesRepository.findById(testSeriesId);
			if (!testSeriesOptional.isPresent())
				return new ResponseEntity<TestSeriesStatusDTO>(
						new TestSeriesStatusDTO(null, "Invalid Test Series Id!!", 0), HttpStatus.BAD_REQUEST);
			TestSeries testSeries = testSeriesOptional.get();

			testSeriesDataRepository.deleteAll(testSeries.getTestSeriesIdToTestSeriesData());

			testSeriesRepository.delete(testSeries);

			return new ResponseEntity<TestSeriesStatusDTO>(
					new TestSeriesStatusDTO(testSeriesId, "Test Series Deleted!!", 1), HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error while deleting test series : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<TestSeriesStatusDTO>(
					new TestSeriesStatusDTO(testSeriesId,
							"Error while deleting test series : " + e.getLocalizedMessage(), 0),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<TestSeries>> getAllTestSeries(int pageNo, int limit) {
		try {
			return new ResponseEntity<List<TestSeries>>(
					testSeriesRepository.findAll(PageRequest.of(pageNo, limit, Direction.ASC, "name")).getContent(),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<TestSeries>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
