package com.studyboom.repositories;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyboom.domains.Requests;
import com.studyboom.domains.Users;

@Repository
@Transactional
public interface RequestRepository extends JpaRepository<Requests, Long> {

	public Set<Requests> findByUserIdToRequests(Users users, Pageable pageable);
	

}
