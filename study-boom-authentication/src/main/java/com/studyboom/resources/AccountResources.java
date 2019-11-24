package com.studyboom.resources;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studyboom.domains.Users;
import com.studyboom.dtos.AccountStatusDTO;
import com.studyboom.dtos.ChangePasswordDTO;
import com.studyboom.dtos.UserDetailsDTO;

@RestController
@RequestMapping("api")
public interface AccountResources {

	@PostMapping(value = "/create/account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountStatusDTO> createAccount(@RequestBody UserDetailsDTO userDetailsDTO);

	@PostMapping(value = "/modify/account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountStatusDTO> modifyAccount(@RequestBody UserDetailsDTO userDetailsDTO);

	@PostMapping(value = "/change/password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountStatusDTO> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO);

	@GetMapping(value = "/get/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Users> getUser(@PathVariable("id") Long id);

	@GetMapping(value = "/perform/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Users> performLogin(@RequestParam("username") String username,
			@RequestParam("password") String password);
}
