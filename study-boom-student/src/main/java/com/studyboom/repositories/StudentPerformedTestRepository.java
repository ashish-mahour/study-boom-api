package com.studyboom.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyboom.domains.Student;
import com.studyboom.domains.StudentPerfromedTest;

@Repository
@Transactional
public interface StudentPerformedTestRepository extends JpaRepository<StudentPerfromedTest, Long> {

	public List<StudentPerfromedTest> findByPerformendByStudent(Student student);
}
