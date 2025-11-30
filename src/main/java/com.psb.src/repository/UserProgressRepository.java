package com.psb.src.repository;

import com.psb.src.entity.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    Optional<UserProgress> findByUserIdAndCourseId(Long userId, Long courseId);
    List<UserProgress> findByUserId(Long userId);
    List<UserProgress> findByUserIdAndCompletedTrue(Long userId);

    @Query("SELECT up FROM UserProgress up WHERE up.user.id = :userId AND up.course.category = :category")
    List<UserProgress> findByUserIdAndCourseCategory(@Param("userId") Long userId, @Param("category") String category);
}