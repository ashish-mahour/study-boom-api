package com.studyboom.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.studyboom.domains.Admin;
import com.studyboom.domains.Publisher;
import com.studyboom.domains.Student;
import com.studyboom.domains.StudentChoosenSubjectSubCategory;
import com.studyboom.domains.SubjectSubCategory;
import com.studyboom.domains.Users;
import com.studyboom.dtos.AccountStatusDTO;
import com.studyboom.dtos.ChangePasswordDTO;
import com.studyboom.dtos.Constants;
import com.studyboom.dtos.UserDetailsDTO;
import com.studyboom.repositories.AdminRepository;
import com.studyboom.repositories.PublisherRepository;
import com.studyboom.repositories.StudentChoosenSubjectSubCategoryRepository;
import com.studyboom.repositories.StudentRepository;
import com.studyboom.repositories.SubjectSubCategoryRepository;
import com.studyboom.repositories.UserRepository;
import com.studyboom.resources.AccountResources;

@Service
public class AccountService implements AccountResources {

	private final Logger LOG = Logger.getLogger(AccountService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private SubjectSubCategoryRepository subjectSubCategoryRepository;

	@Autowired
	private StudentChoosenSubjectSubCategoryRepository studentChoosenSubjectSubCategoryRepository;

	ObjectWriter writer = new ObjectMapper().writer();

	@Override
	public ResponseEntity<AccountStatusDTO> createAccount(UserDetailsDTO userDetailsDTO) {
		try {
			Users users = userRepository.save(new Users(userDetailsDTO.getFullName(), userDetailsDTO.getUsername(),
					userDetailsDTO.getEmail(), userDetailsDTO.getPassword(), userDetailsDTO.getType(), null, false));

			if (userDetailsDTO.getType().equalsIgnoreCase(Constants.USER_TYPE.ADMIN.name()))
				createAdmin(userDetailsDTO, users);
			else if (userDetailsDTO.getType().equalsIgnoreCase(Constants.USER_TYPE.STUDENT.name()))
				createStudent(userDetailsDTO, users);
			else if (userDetailsDTO.getType().equalsIgnoreCase(Constants.USER_TYPE.PUBLISHER.name()))
				createPublisher(userDetailsDTO, users);

			return new ResponseEntity<AccountStatusDTO>(
					new AccountStatusDTO(Constants.STATUS_OK, "User Created!!", users.getId()), HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error while creating account : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<AccountStatusDTO>(
					new AccountStatusDTO(Constants.STATUS_FAILED,
							"Error while creating account : " + e.getLocalizedMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<AccountStatusDTO> modifyAccount(UserDetailsDTO userDetailsDTO) {
		try {
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
			if (userDetailsDTO.getProfilePic() != null)
				users.setProfilePic(userDetailsDTO.getProfilePic());

			users = userRepository.save(users);

			if (userDetailsDTO.getType().equalsIgnoreCase(Constants.USER_TYPE.ADMIN.name()))
				updateAdmin(userDetailsDTO, users);
			else if (userDetailsDTO.getType().equalsIgnoreCase(Constants.USER_TYPE.STUDENT.name()))
				updateStudent(userDetailsDTO, users);
			else if (userDetailsDTO.getType().equalsIgnoreCase(Constants.USER_TYPE.PUBLISHER.name()))
				updatePublisher(userDetailsDTO, users);

			return new ResponseEntity<AccountStatusDTO>(
					new AccountStatusDTO(Constants.STATUS_OK, "User Updated!!", users.getId()), HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error while modifing account : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<AccountStatusDTO>(
					new AccountStatusDTO(Constants.STATUS_FAILED,
							"Error while modifing account : " + e.getLocalizedMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void createPublisher(UserDetailsDTO userDetailsDTO, Users users) {
		try {
			publisherRepository.save(new Publisher(users, userDetailsDTO.getFullName(), userDetailsDTO.getUsername(),
					userDetailsDTO.getEmail(), userDetailsDTO.getMobileNo(), userDetailsDTO.getBankName(),
					userDetailsDTO.getBranchName(), userDetailsDTO.getAccountNo(), userDetailsDTO.getIfscCode(),
					LocalDateTime.now(), LocalDateTime.now()));
		} catch (Exception e) {
			LOG.error("Error while creating publisher : " + e.getLocalizedMessage(), e);
		}
	}

	private void createStudent(UserDetailsDTO userDetailsDTO, Users users) {
		try {
			Student student = studentRepository.save(new Student(users, userDetailsDTO.getFullName(),
					userDetailsDTO.getUsername(), userDetailsDTO.getEmail(), userDetailsDTO.getMobileNo(),
					LocalDateTime.now(), LocalDateTime.now()));
			for (Long subCategoryId : userDetailsDTO.getChoosedSubCategories()) {
				Optional<SubjectSubCategory> subjectSubCategoryOptional = subjectSubCategoryRepository
						.findById(subCategoryId);
				int priority = 0;
				if (subjectSubCategoryOptional.isPresent())
					studentChoosenSubjectSubCategoryRepository.save(new StudentChoosenSubjectSubCategory(student,
							subjectSubCategoryOptional.get(), ++priority));
			}
		} catch (Exception e) {
			LOG.error("Error while creating student : " + e.getLocalizedMessage(), e);
		}

	}

	private void createAdmin(UserDetailsDTO userDetailsDTO, Users users) {
		try {
			adminRepository.save(new Admin(users, userDetailsDTO.getFullName(), userDetailsDTO.getUsername(),
					userDetailsDTO.getEmail(), userDetailsDTO.getMobileNo(), LocalDateTime.now(), LocalDateTime.now()));
		} catch (Exception e) {
			LOG.error("Error while creating admin : " + e.getLocalizedMessage(), e);
		}
	}

	private boolean updatePublisher(UserDetailsDTO userDetailsDTO, Users users) {
		try {
			Optional<Publisher> publisherOptional = publisherRepository.findByUserIdToPublisher(users);

			if (!publisherOptional.isPresent())
				return false;

			Publisher publisher = publisherOptional.get();
			publisher.setFullName(userDetailsDTO.getFullName());
			publisher.setUsername(userDetailsDTO.getUsername());
			publisher.setEmail(userDetailsDTO.getEmail());
			publisher.setMobile(userDetailsDTO.getMobileNo());
			publisher.setBankName(userDetailsDTO.getBankName());
			publisher.setAccountNo(userDetailsDTO.getAccountNo());
			publisher.setIfscCode(userDetailsDTO.getIfscCode());
			publisher.setModifiedDate(LocalDateTime.now());

			publisherRepository.save(publisher);
			return true;
		} catch (Exception e) {
			LOG.error("Error while modifing publisher : " + e.getLocalizedMessage(), e);
			return false;
		}
	}

	private boolean updateStudent(UserDetailsDTO userDetailsDTO, Users users) {
		try {
			Optional<Student> studentOptional = studentRepository.findByUserIdToStudent(users);
			if (!studentOptional.isPresent())
				return false;

			Student student = studentOptional.get();
			student.setFullName(userDetailsDTO.getFullName());
			student.setFullName(userDetailsDTO.getFullName());
			student.setUsername(userDetailsDTO.getUsername());
			student.setEmail(userDetailsDTO.getEmail());
			student.setMobile(userDetailsDTO.getMobileNo());
			student.setModifiedDate(LocalDateTime.now());

			studentRepository.save(student);

			return true;
		} catch (Exception e) {
			LOG.error("Error while modifing student : " + e.getLocalizedMessage(), e);
			return false;
		}

	}

	private boolean updateAdmin(UserDetailsDTO userDetailsDTO, Users users) {
		try {
			Optional<Admin> adminOptional = adminRepository.findByUserIdToAdmin(users);
			if (!adminOptional.isPresent())
				return false;

			Admin admin = adminOptional.get();
			admin.setFullName(userDetailsDTO.getFullName());
			admin.setFullName(userDetailsDTO.getFullName());
			admin.setUsername(userDetailsDTO.getUsername());
			admin.setEmail(userDetailsDTO.getEmail());
			admin.setMobile(userDetailsDTO.getMobileNo());
			admin.setModifiedDate(LocalDateTime.now());

			adminRepository.save(admin);

			return true;
		} catch (Exception e) {
			LOG.error("Error while modifing admin : " + e.getLocalizedMessage(), e);
			return false;
		}
	}

	@Override
	public ResponseEntity<AccountStatusDTO> changePassword(ChangePasswordDTO changePasswordDTO) {
		try {
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
		} catch (Exception e) {
			LOG.error("Error while changing password : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<AccountStatusDTO>(
					new AccountStatusDTO(Constants.STATUS_FAILED,
							"Error while changing password : " + e.getLocalizedMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Users> getUser(Long id) {
		try {
			Optional<Users> usersOptional = userRepository.findById(id);
			if (usersOptional.isPresent())
				return new ResponseEntity<>(usersOptional.get(), HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error("Error while getting user : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Users> performLogin(String username, String password) {
		try {
			Optional<Users> usersOptional = userRepository.findByUsernameOrEmailAndPassword(username, username,
					password);
			if (usersOptional.isPresent() && usersOptional.get().getIsActivated())
				return new ResponseEntity<>(usersOptional.get(), HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error("Error while login : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
