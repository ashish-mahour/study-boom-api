package com.studyboom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
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

	private final Logger LOG = Logger.getLogger("OUT");

	@Autowired
	private SubjectCategoryRepository subjectCategoryRepository;

	@Autowired
	private SubjectSubCategoryRepository subjectSubCategoryRepository;

	@Override
	public ResponseEntity<List<SubjectCategory>> getCategories(int pageNo, int limit) {
		try {
			return new ResponseEntity<List<SubjectCategory>>(
					subjectCategoryRepository.findAll(PageRequest.of(pageNo, limit)).getContent(), HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error while getting subject categories : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<List<SubjectCategory>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<CategoryStatusDTO> addCategory(CategoryDetailsDTO categoryDetailsDTO) {
		try {
			SubjectCategory subjectCategory = subjectCategoryRepository
					.save(new SubjectCategory(categoryDetailsDTO.getCategoryName()));

			List<SubCategoryDetails> subCategories = categoryDetailsDTO.getSubCategories();

			List<SubjectSubCategory> subjectSubCategories = new ArrayList<>();
			for (SubCategoryDetails subCategoryDetails : subCategories)
				if (subCategoryDetails.getSubCategoryId() == null && subCategoryDetails.getSubCategoryName() != null)
					subjectSubCategories
							.add(new SubjectSubCategory(subjectCategory, subCategoryDetails.getSubCategoryName()));

			subjectSubCategoryRepository.saveAll(subjectSubCategories);

			return new ResponseEntity<CategoryStatusDTO>(
					new CategoryStatusDTO(1, "Category Saved!!!", subjectCategory.getId()), HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error while getting adding categories : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<CategoryStatusDTO>(new CategoryStatusDTO(0,
					"Error while getting adding categories : " + e.getLocalizedMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<CategoryStatusDTO> modifyCategory(CategoryDetailsDTO categoryDetailsDTO) {
		try {
			if (categoryDetailsDTO.getCategoryId() == null)
				return new ResponseEntity<CategoryStatusDTO>(new CategoryStatusDTO(0, "Category ID is null!!", null),
						HttpStatus.BAD_REQUEST);

			Optional<SubjectCategory> subjectCategoryOptional = subjectCategoryRepository
					.findById(categoryDetailsDTO.getCategoryId());
			if (!subjectCategoryOptional.isPresent())
				return new ResponseEntity<CategoryStatusDTO>(
						new CategoryStatusDTO(0, "Category not found!!!", categoryDetailsDTO.getCategoryId()),
						HttpStatus.BAD_REQUEST);

			SubjectCategory subjectCategory = subjectCategoryOptional.get();
			subjectCategory.setName(categoryDetailsDTO.getCategoryName());

			List<SubjectSubCategory> subjectSubCategories = subjectCategory.getSubjectCategoryIdToSubCategory();

			List<SubCategoryDetails> subCategories = categoryDetailsDTO.getSubCategories();

			for (SubCategoryDetails subCategoryDetails : subCategories) {

				if (subCategoryDetails.getSubCategoryId() == null && subCategoryDetails.getSubCategoryName() != null) {
					subjectSubCategoryRepository
							.save(new SubjectSubCategory(subjectCategory, subCategoryDetails.getSubCategoryName()));
					continue;
				}
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
		} catch (Exception e) {
			LOG.error("Error while getting modifing categories : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<CategoryStatusDTO>(new CategoryStatusDTO(0,
					"Error while getting modifing categories : " + e.getLocalizedMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<CategoryStatusDTO> deleteCategory(Long id) {
		try {
			if (id == null)
				return new ResponseEntity<CategoryStatusDTO>(new CategoryStatusDTO(0, "Category ID is null!!", null),
						HttpStatus.BAD_REQUEST);

			Optional<SubjectCategory> subjectCategoryOptional = subjectCategoryRepository.findById(id);
			if (!subjectCategoryOptional.isPresent())
				return new ResponseEntity<CategoryStatusDTO>(new CategoryStatusDTO(0, "Category not found!!!", id),
						HttpStatus.BAD_REQUEST);
			SubjectCategory subjectCategory = subjectCategoryOptional.get();
			for (SubjectSubCategory subCategory : subjectCategory.getSubjectCategoryIdToSubCategory())
				subjectSubCategoryRepository.delete(subCategory);

			subjectCategoryRepository.delete(subjectCategory);
			return new ResponseEntity<CategoryStatusDTO>(new CategoryStatusDTO(1, "Category Deleted!!!", id),
					HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error while getting deleting categories : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<CategoryStatusDTO>(new CategoryStatusDTO(0,
					"Error while getting deleting categories : " + e.getLocalizedMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
