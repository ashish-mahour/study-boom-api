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

import com.studyboom.domains.SubjectCategory;
import com.studyboom.dtos.CategoryDetailsDTO;
import com.studyboom.dtos.CategoryStatusDTO;

@RestController
@RequestMapping("api")
public interface SubjectCategoryResources {

	@GetMapping(path = "/get/subject/categories", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubjectCategory>> getCategories(@RequestParam("pageNo") int pageNo,
			@RequestParam("limit") int limit);

	@PostMapping(path = "/add/subject/cateogories")
	public ResponseEntity<CategoryStatusDTO> addCategory(@RequestBody CategoryDetailsDTO categoryDetailsDTO);

	@PostMapping(path = "/modify/subject/cateogories")
	public ResponseEntity<CategoryStatusDTO> modifyCategory(@RequestBody CategoryDetailsDTO categoryDetailsDTO);
}
