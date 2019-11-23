package com.studyboom.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyboom.domains.Publisher;
import com.studyboom.domains.Users;

@Repository
@Transactional
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

	public Optional<Publisher> findByUserIdToPublisher(Users users);
}
