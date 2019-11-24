package com.studyboom.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studyboom.domains.SubjectCategory;
import com.studyboom.domains.SubjectSubCategory;
import com.studyboom.dtos.CategoryDetailsDTO;
import com.studyboom.dtos.CategoryStatusDTO;
import com.studyboom.dtos.SubCategoryDetails;
import com.studyboom.repositories.SubjectCategoryRepository;
import com.studyboom.repositories.SubjectSubCategoryRepository;
import com.studyboom.resources.SubjectCategoryResources;

@Service
public class SubjectCategoryService implements SubjectCategoryResources {

	@Autowired
	private SubjectCategoryRepository subjectCategoryRepository;

	@Autowired
	private SubjectSubCategoryRepository subjectSubCategoryRepository;

	@Override
	public ResponseEntity<List<SubjectCategory>> getCategories(int pageNo, int limit) {
		return new ResponseEntity<List<SubjectCategory>>(
				subjectCategoryRepository.findAll(PageRequest.of(pageNo, limit)).getContent(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoryStatusDTO> addCategory(CategoryDetailsDTO categoryDetailsDTO) {

		SubjectCategory subjectCategory = subjectCategoryRepository
				.save(new SubjectCategory(categoryDetailsDTO.getCategoryName()));

		Set<SubCategoryDetails> subCategories = categoryDetailsDTO.getSubCategories();

		Set<SubjectSubCategory> subjectSubCategories = new TreeSet<>();
		for (SubCategoryDetails subCategoryDetails : subCategories)
			subjectSubCategories.add(new SubjectSubCategory(subjectCategory, subCategoryDetails.getSubCategoryName()));

		subjectSubCategoryRepository.saveAll(subjectSubCategories);

		return new ResponseEntity<CategoryStatusDTO>(
				new CategoryStatusDTO(1, "Category Saved!!!", subjectCategory.getId()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoryStatusDTO> modifyCategory(CategoryDetailsDTO categoryDetailsDTO) {
		Optional<SubjectCategory> subjectCategoryOptional = subjectCategoryRepository
				.findById(categoryDetailsDTO.getCategoryId());
		if (!subjectCategoryOptional.isPresent())
			return new ResponseEntity<CategoryStatusDTO>(
					new CategoryStatusDTO(0, "Category not found!!!", categoryDetailsDTO.getCategoryId()),
					HttpStatus.BAD_REQUEST);

		SubjectCategory subjectCategory = subjectCategoryOptional.get();
		subjectCategory.setName(subjectCategory.getName());

		Set<SubjectSubCategory> subjectSubCategories = subjectCategory.getSubjectCategoryIdToSubCategory();

		Set<SubCategoryDetails> subCategories = categoryDetailsDTO.getSubCategories();

		for (SubCategoryDetails subCategoryDetails : subCategories) {

			if (subCategoryDetails.getSubCategoryId() == null)
				continue;

			Optional<SubjectSubCategory> subjectSubCategoryOptional = subjectSubCategoryRepository
					.findById(subCategoryDetails.getSubCategoryId());

			SubjectSubCategory subjectSubCategory = null;
			if (subjectSubCategoryOptional.isPresent()) {
				subjectSubCategory = subjectSubCategoryOptional.get();
				subjectSubCategories.remove(subjectSubCategory);
				subjectSubCategory.setName(subCategoryDetails.getSubCategoryName());
			}

		}

		subjectCategoryRepository.save(subjectCategory);

		return new ResponseEntity<CategoryStatusDTO>(
				new CategoryStatusDTO(1, "Category Modified!!!", subjectCategory.getId()), HttpStatus.OK);
	}

}
