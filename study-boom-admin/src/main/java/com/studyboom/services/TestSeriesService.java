package com.studyboom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studyboom.domains.Admin;
import com.studyboom.domains.SubjectSubCategory;
import com.studyboom.domains.TestSeries;
import com.studyboom.dtos.TestSeriesDetailsDTO;
import com.studyboom.dtos.TestSeriesStatusDTO;
import com.studyboom.repositories.AdminRepository;
import com.studyboom.repositories.SubjectSubCategoryRepository;
import com.studyboom.repositories.TestSeriesReposiroty;
import com.studyboom.resources.TestSeriesResources;

@Service
public class TestSeriesService implements TestSeriesResources {

	@Autowired
	private TestSeriesReposiroty testSeriesRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private SubjectSubCategoryRepository subjectSubCategoryRepository;


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

	@Override
	public ResponseEntity<TestSeriesStatusDTO> modifyTestSeries(TestSeriesDetailsDTO testSeriesDetailsDTO) {
		Optional<TestSeries> testSeriesOptional = testSeriesRepository
				.findById(testSeriesDetailsDTO.getTestSeriesId());
		if (!testSeriesOptional.isPresent())
			return new ResponseEntity<TestSeriesStatusDTO>(
					new TestSeriesStatusDTO(null, "Invalid Test Series Id!!", 0), HttpStatus.BAD_REQUEST);

		TestSeries testSeries = testSeriesOptional.get();

		Optional<Admin> adminOptiional = adminRepository
				.findById(testSeriesDetailsDTO.getAdminId());
		if (!adminOptiional.isPresent())
			return new ResponseEntity<TestSeriesStatusDTO>(
					new TestSeriesStatusDTO(testSeries.getId(), "Invalid Admin Id!!", 0),
					HttpStatus.BAD_REQUEST);

		Optional<SubjectSubCategory> subjectSubCategotyOptional = subjectSubCategoryRepository
				.findById(testSeriesDetailsDTO.getSubCategoryId());
		if (!subjectSubCategotyOptional.isPresent())
			return new ResponseEntity<TestSeriesStatusDTO>(
					new TestSeriesStatusDTO(testSeries.getId(), "Invalid Subject Sub Category Id!!", 0),
					HttpStatus.BAD_REQUEST);
		
		testSeries.setIsVisible(testSeriesDetailsDTO.getIsVisible());
		testSeriesRepository.save(testSeries);
		return new ResponseEntity<TestSeriesStatusDTO>(
				new TestSeriesStatusDTO(testSeries.getId(), "Test Series Updated!!", 1), HttpStatus.OK);
	}

}
