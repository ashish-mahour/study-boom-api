package com.studyboom.resources;

import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studyboom.domains.SubjectCategory;

@RestController
@RequestMapping("api")
public interface SubjectCategoryResources {

	@GetMapping(path="/get/subject/categories", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<SubjectCategory>> getCategories();
}
