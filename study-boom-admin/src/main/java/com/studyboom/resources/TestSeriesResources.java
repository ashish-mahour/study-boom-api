package com.studyboom.resources;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studyboom.domains.TestSeries;
import com.studyboom.dtos.TestSeriesDetailsDTO;
import com.studyboom.dtos.TestSeriesStatusDTO;

@RestController
@RequestMapping("/api")
public interface TestSeriesResources {

	@PostMapping(path = "/modify/test/series", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TestSeriesStatusDTO> modifyTestSeries(@RequestBody TestSeriesDetailsDTO testSeriesDetailsDTO);

	@GetMapping(path = "/get/all/test/series", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TestSeries>> getAllTestSeries(@RequestParam("pageNo") int pageNo, @RequestParam("limit") int limit);
}
