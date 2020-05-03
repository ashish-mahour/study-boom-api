package com.studyboom.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studyboom.domains.Publisher;
import com.studyboom.domains.SubjectSubCategory;
import com.studyboom.domains.TestSeries;

@Repository
@Transactional
public interface TestSeriesReposiroty extends JpaRepository<TestSeries, Long> {

	public List<TestSeries> findByUploadedByPublisher(Publisher publisher, Pageable pageable);

	public List<TestSeries> findByIsVisibleAndSubjectSubCategoryIdToTestSeriesIn(Boolean isVisible, List<SubjectSubCategory> subjectSubCategories,
			Pageable pageable);
}
