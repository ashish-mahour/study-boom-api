package com.studyboom.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyboom.domains.Student;
import com.studyboom.domains.Users;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	public Optional<Student> findByUserIdToStudent(Users users);

}
