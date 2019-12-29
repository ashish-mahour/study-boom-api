package com.studyboom.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyboom.domains.StudentChoosenSubjectSubCategory;

@Repository
@Transactional
public interface StudentChoosenSubjectSubCategoryRepository extends JpaRepository<StudentChoosenSubjectSubCategory, Long> {

}
