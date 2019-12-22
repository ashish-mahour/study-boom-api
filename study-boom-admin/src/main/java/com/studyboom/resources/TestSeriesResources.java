package com.studyboom.resources;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studyboom.domains.TestSeries;
import com.studyboom.dtos.TestSeriesStatusDTO;

@RestController
@RequestMapping("/api")
public interface TestSeriesResources {

	@DeleteMapping(path = "/delete/test/series", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TestSeriesStatusDTO> deleteTestSeries(Long testSeriesId);

	@GetMapping(path = "/get/all/test/series", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TestSeries>> getAllTestSeries(int pageNo, int limit);
}
