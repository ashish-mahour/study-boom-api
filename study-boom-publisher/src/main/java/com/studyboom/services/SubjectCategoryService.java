package com.studyboom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studyboom.domains.SubjectCategory;
import com.studyboom.repositories.SubjectCategoryRepository;
import com.studyboom.resources.SubjectCategoryResources;

@Service
public class SubjectCategoryService implements SubjectCategoryResources {

	@Autowired
	private SubjectCategoryRepository subjectCategoryRepository;

	@Override
	public ResponseEntity<List<SubjectCategory>> getCategories() {
		return new ResponseEntity<List<SubjectCategory>>(subjectCategoryRepository.findAll(), HttpStatus.OK);
	}

}
