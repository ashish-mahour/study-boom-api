package com.studyboom.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyboom.domains.SubjectSubCategory;

@Repository
@Transactional
public interface SubjectSubCategoryRepository extends JpaRepository<SubjectSubCategory, Long>{

}
