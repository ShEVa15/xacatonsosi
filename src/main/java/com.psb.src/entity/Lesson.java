package com.psb.src.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String videoUrl;
    private String slideContent;
    private Integer orderInCourse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Lesson() {}

    public Lesson(String title, String content, String videoUrl, String slideContent, Integer orderInCourse, Course course) {
        this.title = title;
        this.content = content;
        this.videoUrl = videoUrl;
        this.slideContent = slideContent;
        this.orderInCourse = orderInCourse;
        this.course = course;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public String getSlideContent() { return slideContent; }
    public void setSlideContent(String slideContent) { this.slideContent = slideContent; }

    public Integer getOrderInCourse() { return orderInCourse; }
    public void setOrderInCourse(Integer orderInCourse) { this.orderInCourse = orderInCourse; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}