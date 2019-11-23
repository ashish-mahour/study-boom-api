package com.studyboom.resources;

import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studyboom.domains.Publisher;
import com.studyboom.domains.Student;
import com.studyboom.domains.Users;
import com.studyboom.dtos.AccountStatusDTO;
import com.studyboom.dtos.UserDetailsDTO;

@RestController
@RequestMapping("api")
public interface AccountResources {

	@PostMapping(value = "/modify/account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountStatusDTO> modifyAccount(@RequestBody UserDetailsDTO userDetailsDTO);

	@GetMapping(value = "/get/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Users> getUser(@PathVariable("id") Long id);
	
	@GetMapping(value="/get/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Publisher>> getPublishers(@RequestParam("pageNo") int pageNo, @RequestParam("limit") int limit);

	@GetMapping(value="/get/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Student>> getStudent(@RequestParam("pageNo") int pageNo, @RequestParam("limit") int limit);
}
