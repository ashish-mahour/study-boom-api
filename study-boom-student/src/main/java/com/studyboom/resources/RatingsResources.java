package com.studyboom.resources;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studyboom.dtos.TestSeriesRatingDTO;
import com.studyboom.dtos.TestSeriesRatingStatusDTO;

@RestController
@RequestMapping("/api")
public interface RatingsResources {

	@PostMapping(value = "/submit/ratings", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TestSeriesRatingStatusDTO> submitRatings(@RequestBody TestSeriesRatingDTO testSeriesRatingDTO);
}
