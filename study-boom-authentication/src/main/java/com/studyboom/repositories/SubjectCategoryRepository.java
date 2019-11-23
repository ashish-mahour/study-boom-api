package com.studyboom.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyboom.domains.SubjectCategory;

@Repository
@Transactional
public interface SubjectCategoryRepository extends JpaRepository<SubjectCategory, Long> {

}
