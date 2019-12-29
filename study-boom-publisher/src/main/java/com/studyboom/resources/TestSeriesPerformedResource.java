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

import com.studyboom.domains.StudentPerfromedTest;
import com.studyboom.domains.TestSeries;
import com.studyboom.dtos.StudentPerformedTestDTO;
import com.studyboom.dtos.TestSeriesPerformedStatusDTO;

@RestController
@RequestMapping("/api")
public interface TestSeriesPerformedResource {

	@GetMapping(path = "/get/test/series/by/categories", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TestSeries>> getTestSeriesByCategories(@RequestParam("studentId") Long studentId,
			@RequestParam("pageNo") int pageNo, @RequestParam("limit") int limit);
	
	@GetMapping(path = "/get/performed/test/series", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StudentPerfromedTest>> getPerformedTestSeries(@RequestParam("studentId") Long studentId,
			@RequestParam("pageNo") int pageNo, @RequestParam("limit") int limit);

	@PostMapping(path = "/test/performed", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TestSeriesPerformedStatusDTO> testPeformed(
			@RequestBody StudentPerformedTestDTO studentPerformedTestDTO);

}
