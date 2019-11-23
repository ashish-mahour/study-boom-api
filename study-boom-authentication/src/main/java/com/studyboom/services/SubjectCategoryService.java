package com.studyboom.services;

import java.util.Set;
import java.util.TreeSet;

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
	public ResponseEntity<Set<SubjectCategory>> getCategories() {
		Set<SubjectCategory> subjectCategorySet = new TreeSet<>();
		subjectCategorySet.addAll(subjectCategoryRepository.findAll());
		return new ResponseEntity<Set<SubjectCategory>>(subjectCategorySet, HttpStatus.OK);
	}
}
