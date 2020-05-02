package com.studyboom.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyboom.domains.TestSeriesRatings;

@Repository
@Transactional
public interface TestSeriesRatingsRepository extends JpaRepository<TestSeriesRatings, Long>{

}
