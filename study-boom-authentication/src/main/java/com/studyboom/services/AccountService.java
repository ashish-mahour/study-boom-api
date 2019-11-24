package com.studyboom.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.studyboom.domains.Admin;
import com.studyboom.domains.Publisher;
import com.studyboom.domains.Student;
import com.studyboom.domains.Users;
import com.studyboom.dtos.AccountStatusDTO;
import com.studyboom.dtos.ChangePasswordDTO;
import com.studyboom.dtos.Constants;
import com.studyboom.dtos.UserDetailsDTO;
import com.studyboom.repositories.AdminRepository;
import com.studyboom.repositories.PublisherRepository;
import com.studyboom.repositories.StudentRepository;
import com.studyboom.repositories.UserRepository;
import com.studyboom.resources.AccountResources;

@Service
public class AccountService implements AccountResources {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private PublisherRepository publisherRepository;

	ObjectWriter writer = new ObjectMapper().writer();

	@Override
	public ResponseEntity<AccountStatusDTO> createAccount(UserDetailsDTO userDetailsDTO) {
		Users users = userRepository.save(new Users(userDetailsDTO.getFullName(), userDetailsDTO.getUsername(),
				userDetailsDTO.getEmail(), userDetailsDTO.getPassword(), userDetailsDTO.getType(), null, false));

		if (userDetailsDTO.getType().equalsIgnoreCase(Constants.USER_TYPE.ADMIN.name()))
			createAdmin(userDetailsDTO, users);
		else if (userDetailsDTO.getType().equalsIgnoreCase(Constants.USER_TYPE.ADMIN.name()))
			createStudent(userDetailsDTO, users);
		else if (userDetailsDTO.getType().equalsIgnoreCase(Constants.USER_TYPE.ADMIN.name()))
			createPublisher(userDetailsDTO, users);

		return new ResponseEntity<AccountStatusDTO>(
				new AccountStatusDTO(Constants.STATUS_OK, "User Created!!", users.getId()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AccountStatusDTO> modifyAccount(UserDetailsDTO userDetailsDTO) {
		Optional<Users> userOptional = userRepository.findById(userDetailsDTO.getId());
		if (!userOptional.isPresent())
			return new ResponseEntity<AccountStatusDTO>(new AccountStatusDTO(Constants.STATUS_OK,
					"User is not Present in the Database!!", userDetailsDTO.getId()), HttpStatus.OK);

		Users users = userOptional.get();

		/*
		 * MODIFING USER DETAILS
		 */

		if (userDetailsDTO.getFullName() != null)
			users.setFullName(userDetailsDTO.getFullName());
		if (userDetailsDTO.getUsername() != null)
			users.setUsername(userDetailsDTO.getUsername());
		if (userDetailsDTO.getIsActivated() != null)
			users.setIsActivated(userDetailsDTO.getIsActivated());
		if (userDetailsDTO.getPassword() != null)
			users.setPassword(userDetailsDTO.getPassword());

		if (userDetailsDTO.getType().equalsIgnoreCase(Constants.USER_TYPE.ADMIN.name()))
			updateAdmin(userDetailsDTO, users);
		else if (userDetailsDTO.getType().equalsIgnoreCase(Constants.USER_TYPE.ADMIN.name()))
			updateStudent(userDetailsDTO, users);
		else if (userDetailsDTO.getType().equalsIgnoreCase(Constants.USER_TYPE.ADMIN.name()))
			updatePublisher(userDetailsDTO, users);

		userRepository.save(users);

		return new ResponseEntity<AccountStatusDTO>(
				new AccountStatusDTO(Constants.STATUS_OK, "User Updated!!", users.getId()), HttpStatus.OK);
	}

	private void createPublisher(UserDetailsDTO userDetailsDTO, Users users) {
		publisherRepository.save(new Publisher(users, userDetailsDTO.getFullName(), userDetailsDTO.getUsername(),
				userDetailsDTO.getEmail(), userDetailsDTO.getMobile(), userDetailsDTO.getBankName(),
				userDetailsDTO.getAccountNo(), userDetailsDTO.getIfscCode(), LocalDateTime.now(), LocalDateTime.now()));
	}

	private void createStudent(UserDetailsDTO userDetailsDTO, Users users) {
		studentRepository.save(new Student(users, userDetailsDTO.getFullName(), userDetailsDTO.getUsername(),
				userDetailsDTO.getEmail(), userDetailsDTO.getMobile(), LocalDateTime.now(), LocalDateTime.now()));
	}

	private void createAdmin(UserDetailsDTO userDetailsDTO, Users users) {
		adminRepository.save(new Admin(users, userDetailsDTO.getFullName(), userDetailsDTO.getUsername(),
				userDetailsDTO.getEmail(), userDetailsDTO.getMobile(), LocalDateTime.now(), LocalDateTime.now()));
	}

	private boolean updatePublisher(UserDetailsDTO userDetailsDTO, Users users) {
		Optional<Publisher> publisherOptional = publisherRepository.findByUserIdToPublisher(users);

		if (!publisherOptional.isPresent())
			return false;

		Publisher publisher = publisherOptional.get();
		publisher.setFullName(userDetailsDTO.getFullName());
		publisher.setUsername(userDetailsDTO.getUsername());
		publisher.setEmail(userDetailsDTO.getEmail());
		publisher.setMobile(userDetailsDTO.getMobile());
		publisher.setBankName(userDetailsDTO.getBankName());
		publisher.setAccountNo(userDetailsDTO.getAccountNo());
		publisher.setIfscCode(userDetailsDTO.getIfscCode());
		publisher.setModifiedDate(LocalDateTime.now());

		publisherRepository.save(publisher);

		return true;
	}

	private boolean updateStudent(UserDetailsDTO userDetailsDTO, Users users) {
		Optional<Student> studentOptional = studentRepository.findByUserIdToStudent(users);
		if (!studentOptional.isPresent())
			return false;

		Student student = studentOptional.get();
		student.setFullName(userDetailsDTO.getFullName());
		student.setFullName(userDetailsDTO.getFullName());
		student.setUsername(userDetailsDTO.getUsername());
		student.setEmail(userDetailsDTO.getEmail());
		student.setMobile(userDetailsDTO.getMobile());
		student.setModifiedDate(LocalDateTime.now());

		studentRepository.save(student);

		return true;

	}

	private boolean updateAdmin(UserDetailsDTO userDetailsDTO, Users users) {
		Optional<Admin> adminOptional = adminRepository.findByUserIdToAdmin(users);
		if (!adminOptional.isPresent())
			return false;

		Admin admin = adminOptional.get();
		admin.setFullName(userDetailsDTO.getFullName());
		admin.setFullName(userDetailsDTO.getFullName());
		admin.setUsername(userDetailsDTO.getUsername());
		admin.setEmail(userDetailsDTO.getEmail());
		admin.setMobile(userDetailsDTO.getMobile());
		admin.setModifiedDate(LocalDateTime.now());

		adminRepository.save(admin);

		return true;
	}

	@Override
	public ResponseEntity<AccountStatusDTO> changePassword(ChangePasswordDTO changePasswordDTO) {
		Optional<Users> usersOptional = userRepository.findByEmail(changePasswordDTO.getEmail());
		if (!usersOptional.isPresent())
			return new ResponseEntity<AccountStatusDTO>(
					new AccountStatusDTO(Constants.STATUS_FAILED, "No user found!!", null), HttpStatus.BAD_REQUEST);

		Users users = usersOptional.get();
		if (users.getPassword().equals(changePasswordDTO.getOldPassword()))
			return new ResponseEntity<AccountStatusDTO>(
					new AccountStatusDTO(Constants.STATUS_FAILED, "Old Password didn't match!!", users.getId()),
					HttpStatus.BAD_REQUEST);

		/*
		 * PASSWORD CHANGED
		 */

		users.setPassword(changePasswordDTO.getNewPassword());

		return new ResponseEntity<AccountStatusDTO>(
				new AccountStatusDTO(Constants.STATUS_OK, "Password Changed!!", users.getId()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Users> getUser(Long id) {
		Optional<Users> usersOptional = userRepository.findById(id);
		if (usersOptional.isPresent())
			return new ResponseEntity<>(usersOptional.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Users> performLogin(String username, String password) {
		Optional<Users> usersOptional = userRepository.findByUsernameOrEmailAndPassword(username, username, password);
		if (usersOptional.isPresent() && usersOptional.get().getIsActivated())
			return new ResponseEntity<>(usersOptional.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
