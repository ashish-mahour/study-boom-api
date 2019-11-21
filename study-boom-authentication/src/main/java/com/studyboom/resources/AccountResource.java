package com.studyboom.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studyboom.domains.Users;
import com.studyboom.dtos.AccountStatusDTO;
import com.studyboom.dtos.ChangePasswordDTO;
import com.studyboom.dtos.UserDetailsDTO;
import com.studyboom.services.AccountService;

@RestController
@RequestMapping("api")
public class AccountResource {

	@Autowired
	private AccountService accountService;

	@PostMapping(value = "/create/account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountStatusDTO> createAccount(@RequestBody UserDetailsDTO userDetailsDTO) {
		return accountService.createAccount(userDetailsDTO);
	}

	@PostMapping(value = "/modify/account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountStatusDTO> modifyAccount(@RequestBody UserDetailsDTO userDetailsDTO) {
		return accountService.modifyAccount(userDetailsDTO);
	}

	@PostMapping(value = "/change/password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountStatusDTO> chnagePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
		return accountService.changePassword(changePasswordDTO);
	}

	@GetMapping(value = "/get/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Users> getUser(@PathVariable(name = "id") Long id) {
		return accountService.getUser(id);
	}
}
