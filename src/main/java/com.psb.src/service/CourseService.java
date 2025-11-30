package com.psb.src.service;

import com.psb.src.entity.Course;
import com.psb.src.entity.User;
import com.psb.src.entity.UserProgress;
import com.psb.src.repository.CourseRepository;
import com.psb.src.repository.UserProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserProgressRepository userProgressRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getCoursesByCategory(String category) {
        if ("all".equals(category)) {
            return courseRepository.findAll();
        }
        return courseRepository.findByCategory(category);
    }

    public List<Course> getRoadmapCourses() {
        return courseRepository.findByOrderInRoadmapIsNotNullOrderByOrderInRoadmap();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public UserProgress getUserProgress(User user, Course course) {
        return userProgressRepository.findByUserIdAndCourseId(user.getId(), course.getId())
                .orElse(new UserProgress(user, course));
    }

    public List<UserProgress> getUserProgressByCategory(User user, String category) {
        return userProgressRepository.findByUserIdAndCourseCategory(user.getId(), category);
    }

    public UserProgress updateProgress(User user, Course course, Integer progress) {
        UserProgress userProgress = getUserProgress(user, course);
        userProgress.setProgressPercentage(progress);
        return userProgressRepository.save(userProgress);
    }

    public UserProgress markAsCompleted(User user, Course course) {
        UserProgress userProgress = getUserProgress(user, course);
        userProgress.setCompleted(true);
        return userProgressRepository.save(userProgress);
    }
}