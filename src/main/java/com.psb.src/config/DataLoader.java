package com.psb.src.config;

import com.psb.src.entity.*;
import com.psb.src.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserProgressRepository userProgressRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        userProgressRepository.deleteAll();
        lessonRepository.deleteAll();
        courseRepository.deleteAll();
        userRepository.deleteAll();

        User student1 = new User();
        student1.setUsername("student1");
        student1.setPassword(passwordEncoder.encode("123"));
        userRepository.save(student1);

        User student2 = new User();
        student2.setUsername("student2");
        student2.setPassword(passwordEncoder.encode("123"));
        userRepository.save(student2);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        userRepository.save(admin);

        Course course1 = new Course(
                "Рынок труда: Системная боль",
                "Стратегии удержания кадров и оптимизация ФОТ.",
                "management",
                "https://placehold.co/600x400/051429/FFF?text=Management",
                "2 ч 15 м",
                "И. Иванов",
                false,
                1
        );

        Course course2 = new Course(
                "Frontend Architecture 2025",
                "Построение масштабируемых интерфейсов.",
                "dev",
                "https://placehold.co/600x400/1e3c72/FFF?text=Frontend",
                "12 ч 30 м",
                "А. Смирнов",
                false,
                2
        );

        Course course3 = new Course(
                "Инвестиции для ТОП-менеджмента",
                "Закрытый курс по управлению капиталом.",
                "finance",
                "https://placehold.co/600x400/2c3e50/FFF?text=Invest",
                "8 ч 00 м",
                "В. Финансов",
                true,
                null
        );

        Course course4 = new Course(
                "Эмоциональный интеллект",
                "Управление стрессом и переговоры.",
                "soft",
                "https://placehold.co/600x400/e67e22/FFF?text=Emo+Intel",
                "4 ч 45 м",
                "Е. Петрова",
                false,
                3
        );

        Course course5 = new Course(
                "Backend на Go",
                "Микросервисы и высокие нагрузки.",
                "dev",
                "https://placehold.co/600x400/444/FFF?text=GoLang",
                "20 ч 00 м",
                "Д. Сидоров",
                false,
                4
        );

        courseRepository.saveAll(Arrays.asList(course1, course2, course3, course4, course5));

        Lesson lesson1 = new Lesson(
                "Введение в проблему рынка труда",
                "Анализ текущей ситуации на рынке труда и основные вызовы для HR.",
                "/videos/labor-market-intro.mp4",
                "Рынок труда: основные тенденции, демографические сдвиги, цифровая трансформация",
                1,
                course1
        );

        Lesson lesson2 = new Lesson(
                "Стратегии удержания персонала",
                "Методы и инструменты для удержания ключевых сотрудников в условиях высокой конкуренции.",
                "/videos/retention-strategies.mp4",
                "KPI удержания, программы лояльности, нематериальная мотивация",
                2,
                course1
        );

        lessonRepository.saveAll(Arrays.asList(lesson1, lesson2));

        UserProgress progress1 = new UserProgress(student1, course1);
        progress1.setProgressPercentage(45);

        UserProgress progress2 = new UserProgress(student1, course2);
        progress2.setProgressPercentage(0);

        UserProgress progress3 = new UserProgress(student1, course4);
        progress3.setProgressPercentage(10);

        userProgressRepository.saveAll(Arrays.asList(progress1, progress2, progress3));

        System.out.println("=== DATA LOADED SUCCESSFULLY ===");
        System.out.println("Users created: student1, student2, admin");
        System.out.println("Password for all users: 123");
        System.out.println("Courses created: 5 courses with various categories");
    }
}