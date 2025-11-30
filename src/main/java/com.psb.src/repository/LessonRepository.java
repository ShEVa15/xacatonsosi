package com.psb.src.repository;

import com.psb.src.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByCourseIdOrderByOrderInCourse(Long courseId);
    List<Lesson> findByCourseIdAndOrderInCourse(Long courseId, Integer orderInCourse);
}