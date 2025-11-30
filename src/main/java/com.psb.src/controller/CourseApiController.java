package com.psb.src.controller;

import com.psb.src.entity.Course;
import com.psb.src.entity.User;
import com.psb.src.entity.UserProgress;
import com.psb.src.service.CourseService;
import com.psb.src.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
public class CourseApiController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private LessonService lessonService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/category/{category}")
    public List<Course> getCoursesByCategory(@PathVariable String category) {
        return courseService.getCoursesByCategory(category);
    }

    @GetMapping("/roadmap")
    public List<Course> getRoadmapCourses() {
        return courseService.getRoadmapCourses();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getCourseWithProgress(@PathVariable Long id,
                                                     @AuthenticationPrincipal User user) {
        Map<String, Object> response = new HashMap<>();

        Course course = courseService.getCourseById(id).orElse(null);
        if (course == null) {
            response.put("error", "Course not found");
            return response;
        }

        UserProgress progress = courseService.getUserProgress(user, course);

        response.put("course", course);
        response.put("progress", progress);
        response.put("lessons", lessonService.getLessonsByCourseId(id));

        return response;
    }

    @PostMapping("/{id}/progress")
    public UserProgress updateProgress(@PathVariable Long id,
                                       @RequestParam Integer progress,
                                       @AuthenticationPrincipal User user) {
        Course course = courseService.getCourseById(id).orElseThrow();
        return courseService.updateProgress(user, course, progress);
    }

    @PostMapping("/{id}/complete")
    public UserProgress markAsCompleted(@PathVariable Long id,
                                        @AuthenticationPrincipal User user) {
        Course course = courseService.getCourseById(id).orElseThrow();
        return courseService.markAsCompleted(user, course);
    }
}