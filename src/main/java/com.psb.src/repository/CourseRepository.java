package com.psb.src.repository;

import com.psb.src.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCategory(String category);
    List<Course> findByLockedFalse();
    List<Course> findByOrderInRoadmapIsNotNullOrderByOrderInRoadmap();

    @Query("SELECT c FROM Course c WHERE c.category = :category AND c.locked = false")
    List<Course> findAvailableByCategory(@Param("category") String category);
}