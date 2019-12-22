package com.studyboom.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyboom.domains.TestSeries;
import com.studyboom.domains.TestSeriesData;

@Repository
@Transactional
public interface TestSeriesDataRepository extends JpaRepository<TestSeriesData, Long>{

	public List<TestSeriesData> findByTestSeriesIdToTestSeriesData(TestSeries testSeries); 
}
