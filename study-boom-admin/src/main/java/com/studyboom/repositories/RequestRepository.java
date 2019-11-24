package com.studyboom.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyboom.domains.Requests;

@Repository
@Transactional
public interface RequestRepository extends JpaRepository<Requests, Long> {

}
