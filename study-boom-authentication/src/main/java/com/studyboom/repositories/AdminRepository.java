package com.studyboom.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyboom.domains.Admin;
import com.studyboom.domains.Users;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	public Optional<Admin> findByUserIdToAdmin(Users users);

}
