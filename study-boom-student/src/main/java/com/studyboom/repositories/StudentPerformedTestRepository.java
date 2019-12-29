package com.studyboom.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyboom.domains.StudentPerfromedTest;

@Repository
@Transactional
public interface StudentPerformedTestRepository extends JpaRepository<StudentPerfromedTest, Long> {

}
