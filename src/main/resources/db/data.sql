-- Очищаем таблицы (на случай пересоздания)
TRUNCATE TABLE user_progress, lessons, courses, users RESTART IDENTITY CASCADE;

-- Пользователи
INSERT INTO users (username, password) VALUES
                                           ('student1', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTV4UiC'), -- password: 123
                                           ('student2', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTV4UiC');

-- Курсы
INSERT INTO courses (title, description, category, image_url, duration, author, locked, order_in_roadmap) VALUES
                                                                                                              ('Рынок труда: Системная боль', 'Стратегии удержания кадров и оптимизация ФОТ.', 'management', 'https://placehold.co/600x400/051429/FFF?text=Management', '2 ч 15 м', 'И. Иванов', false, 1),
                                                                                                              ('Frontend Architecture 2025', 'Построение масштабируемых интерфейсов.', 'dev', 'https://placehold.co/600x400/1e3c72/FFF?text=Frontend', '12 ч 30 м', 'А. Смирнов', false, 2),
                                                                                                              ('Инвестиции для ТОП-менеджмента', 'Закрытый курс по управлению капиталом.', 'finance', 'https://placehold.co/600x400/2c3e50/FFF?text=Invest', '8 ч 00 м', 'В. Финансов', true, NULL),
                                                                                                              ('Эмоциональный интеллект', 'Управление стрессом и переговоры.', 'soft', 'https://placehold.co/600x400/e67e22/FFF?text=Emo+Intel', '4 ч 45 м', 'Е. Петрова', false, 3),
                                                                                                              ('Backend на Go', 'Микросервисы и высокие нагрузки.', 'dev', 'https://placehold.co/600x400/444/FFF?text=GoLang', '20 ч 00 м', 'Д. Сидоров', false, 4),
                                                                                                              ('Кибербезопасность v2.0', 'Протоколы защиты корпоративных данных.', 'dev', 'https://placehold.co/600x400/000000/FFF?text=Security', '10 ч 00 м', 'С. Безопаснов', true, NULL),
                                                                                                              ('Цифровой маркетинг', 'SEO, SMM и контекстная реклама в 2024.', 'management', 'https://placehold.co/600x400/8e44ad/FFF?text=Marketing', '15 ч 30 м', 'К. Минина', false, 5),
                                                                                                              ('Big Data & ML', 'Продвинутый курс по машинному обучению.', 'dev', 'https://placehold.co/600x400/16a085/FFF?text=BigData', '40 ч 00 м', 'Д. Данных', true, NULL),
                                                                                                              ('UX/UI Проектирование', 'Создание удобных интерфейсов для финтеха.', 'dev', 'https://placehold.co/600x400/d35400/FFF?text=UX/UI', '18 ч 00 м', 'О. Кравец', false, 6),
                                                                                                              ('Agile и Scrum', 'Гибкие методологии управления проектами.', 'management', 'https://placehold.co/600x400/7f8c8d/FFF?text=Agile', '5 ч 00 м', 'П. Зуев', false, 7),
                                                                                                              ('HR-Бренд работодателя', 'Как построить сильный бренд и привлекать таланты.', 'management', 'https://placehold.co/600x400/6A1B9A/FFF?text=HR+Brand', '6 ч 15 м', 'М. Соколова', false, 8),
                                                                                                              ('Блокчейн в банке', 'Технологии распределенного реестра в финтехе.', 'finance', 'https://placehold.co/600x400/2980b9/FFF?text=Blockchain', '14 ч 40 м', 'В. Криптов', false, 9);

-- Уроки для первого курса "Рынок труда: Системная боль"
INSERT INTO lessons (title, content, video_url, slide_content, order_in_course, course_id) VALUES
                                                                                               ('Введение в проблему рынка труда', 'Анализ текущей ситуации на рынке труда и основные вызовы для HR.', '/videos/labor-market-intro.mp4', 'Рынок труда: основные тенденции, демографические сдвиги, цифровая трансформация', 1, 1),
                                                                                               ('Стратегии удержания персонала', 'Методы и инструменты для удержания ключевых сотрудников в условиях высокой конкуренции.', '/videos/retention-strategies.mp4', 'KPI удержания, программы лояльности, нематериальная мотивация', 2, 1),
                                                                                               ('Оптимизация ФОТ', 'Эффективные методы управления фондом оплаты труда без потери качества команды.', '/videos/fot-optimization.mp4', 'Бенчмаркинг зарплат, грейдинг, переменная часть оплаты труда', 3, 1);

-- Уроки для курса "Frontend Architecture 2025"
INSERT INTO lessons (title, content, video_url, slide_content, order_in_course, course_id) VALUES
                                                                                               ('Современные фреймворки', 'Обзор React, Vue, Angular и выбор стека для проекта.', '/videos/modern-frameworks.mp4', 'Сравнительный анализ фреймворков, критерии выбора', 1, 2),
                                                                                               ('Архитектура компонентов', 'Проектирование переиспользуемых и масштабируемых компонентов.', '/videos/component-architecture.mp4', 'Composition vs Inheritance, паттерны проектирования компонентов', 2, 2),
                                                                                               ('Состояние приложения', 'Управление состоянием в больших фронтенд-приложениях.', '/videos/state-management.mp4', 'Redux, MobX, Context API, выбор подхода для разных сценариев', 3, 2);

-- Прогресс пользователей
INSERT INTO user_progress (progress_percentage, completed, started_at, user_id, course_id) VALUES
                                                                                               (45, false, CURRENT_TIMESTAMP, 1, 1),
                                                                                               (0, false, CURRENT_TIMESTAMP, 1, 2),
                                                                                               (10, false, CURRENT_TIMESTAMP, 1, 4),
                                                                                               (75, false, CURRENT_TIMESTAMP, 1, 7),
                                                                                               (20, false, CURRENT_TIMESTAMP, 1, 9),
                                                                                               (0, false, CURRENT_TIMESTAMP, 1, 10);