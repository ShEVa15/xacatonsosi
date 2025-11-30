package com.psb.src.controller;

import com.psb.src.entity.User;
import com.psb.src.entity.UserProgress;
import com.psb.src.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<UserProgress> getUserProgress(@AuthenticationPrincipal User user) {
        return courseService.getUserProgressByCategory(user, "all");
    }

    @GetMapping("/category/{category}")
    public List<UserProgress> getUserProgressByCategory(@PathVariable String category,
                                                        @AuthenticationPrincipal User user) {
        return courseService.getUserProgressByCategory(user, category);
    }

    @GetMapping("/overview")
    public Map<String, Object> getProgressOverview(@AuthenticationPrincipal User user) {
        List<UserProgress> allProgress = courseService.getUserProgressByCategory(user, "all");
        List<UserProgress> completed = allProgress.stream()
                .filter(UserProgress::isCompleted)
                .toList();

        double completionRate = allProgress.isEmpty() ? 0 :
                (double) completed.size() / allProgress.size() * 100;

        return Map.of(
                "totalCourses", allProgress.size(),
                "completedCourses", completed.size(),
                "completionRate", Math.round(completionRate),
                "inProgress", allProgress.stream()
                        .filter(p -> p.getProgressPercentage() > 0 && !p.isCompleted())
                        .toList()
        );
    }
}