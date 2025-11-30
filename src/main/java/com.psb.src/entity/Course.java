package com.psb.src.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String category; // dev, management, finance, soft

    private String imageUrl;
    private String duration;
    private String author;
    private boolean locked = false;
    private Integer orderInRoadmap;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lesson> lessons = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserProgress> userProgresses = new ArrayList<>();

    // Конструкторы
    public Course() {}

    public Course(String title, String description, String category, String imageUrl,
                  String duration, String author, boolean locked, Integer orderInRoadmap) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
        this.duration = duration;
        this.author = author;
        this.locked = locked;
        this.orderInRoadmap = orderInRoadmap;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public boolean isLocked() { return locked; }
    public void setLocked(boolean locked) { this.locked = locked; }

    public Integer getOrderInRoadmap() { return orderInRoadmap; }
    public void setOrderInRoadmap(Integer orderInRoadmap) { this.orderInRoadmap = orderInRoadmap; }

    public List<Lesson> getLessons() { return lessons; }
    public void setLessons(List<Lesson> lessons) { this.lessons = lessons; }

    public List<UserProgress> getUserProgresses() { return userProgresses; }
    public void setUserProgresses(List<UserProgress> userProgresses) { this.userProgresses = userProgresses; }
}