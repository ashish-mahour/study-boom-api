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

import com.studyboom.domains.Requests;
import com.studyboom.dtos.RequestStatusDTO;
import com.studyboom.dtos.RequestsDetailsDTO;

@RestController
@RequestMapping("api")
public interface RequestResources {

	@GetMapping(value = "/get/users/requests", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Requests>> getRequests(@RequestParam("pageNo") int pageNo,
			@RequestParam("limit") int limit);

	@PostMapping(value = "/modify/users/request", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RequestStatusDTO> modifyRequest(@RequestBody RequestsDetailsDTO requestsDetailsDTO);

}
