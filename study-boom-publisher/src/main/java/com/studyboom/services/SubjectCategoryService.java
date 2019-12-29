package com.studyboom.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studyboom.domains.SubjectCategory;
import com.studyboom.repositories.SubjectCategoryRepository;
import com.studyboom.resources.SubjectCategoryResources;

@Service
public class SubjectCategoryService implements SubjectCategoryResources {

	private final Logger LOG = Logger.getLogger(SubjectCategoryService.class);

	@Autowired
	private SubjectCategoryRepository subjectCategoryRepository;

	@Override
	public ResponseEntity<List<SubjectCategory>> getCategories() {
		try {
			return new ResponseEntity<List<SubjectCategory>>(subjectCategoryRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error getting subject categories : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<List<SubjectCategory>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
