package com.studyboom.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public interface ReportResources {

	@GetMapping(path = "/generate/reports")
	public ResponseEntity<?> genrateReports(@RequestParam("publisherId") Long publisherId);
	
	@GetMapping(path = "/get/reports")
	public ResponseEntity<?> getReports(@RequestParam("publisherId") Long publisherId);
	
}
