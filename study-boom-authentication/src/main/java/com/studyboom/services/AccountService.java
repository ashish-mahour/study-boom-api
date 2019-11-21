package com.studyboom.services;

import org.springframework.http.ResponseEntity;

import com.studyboom.domains.Users;
import com.studyboom.dtos.AccountStatusDTO;
import com.studyboom.dtos.ChangePasswordDTO;
import com.studyboom.dtos.UserDetailsDTO;

public interface AccountService {

	public ResponseEntity<AccountStatusDTO> createAccount(UserDetailsDTO userDetailsDTO);

	public ResponseEntity<AccountStatusDTO> modifyAccount(UserDetailsDTO userDetailsDTO);

	public ResponseEntity<AccountStatusDTO> changePassword(ChangePasswordDTO changePasswordDTO);

	public ResponseEntity<Users> getUser(Long id);

}
