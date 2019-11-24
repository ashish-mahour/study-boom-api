package com.studyboom.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyboom.domains.Users;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users, Long> {

	public Optional<Users> findByEmail(String email);
	
	public Optional<Users> findByUsernameOrEmailAndPassword(String username, String email, String password);
}
