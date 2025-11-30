-- Создание таблицы пользователей
CREATE TABLE IF NOT EXISTS users (
                                     id BIGSERIAL PRIMARY KEY,
                                     username VARCHAR(255) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Создание таблицы курсов
CREATE TABLE IF NOT EXISTS courses (
                                       id BIGSERIAL PRIMARY KEY,
                                       title VARCHAR(255) NOT NULL,
                                       description TEXT,
                                       category VARCHAR(100) NOT NULL,
                                       image_url VARCHAR(500),
                                       duration VARCHAR(50),
                                       author VARCHAR(255),
                                       locked BOOLEAN DEFAULT false,
                                       order_in_roadmap INTEGER,
                                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Создание таблицы уроков
CREATE TABLE IF NOT EXISTS lessons (
                                       id BIGSERIAL PRIMARY KEY,
                                       title VARCHAR(255) NOT NULL,
                                       content TEXT,
                                       video_url VARCHAR(500),
                                       slide_content TEXT,
                                       order_in_course INTEGER NOT NULL,
                                       course_id BIGINT NOT NULL,
                                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       CONSTRAINT fk_lesson_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- Создание таблицы прогресса пользователей
CREATE TABLE IF NOT EXISTS user_progress (
                                             id BIGSERIAL PRIMARY KEY,
                                             progress_percentage INTEGER DEFAULT 0,
                                             completed BOOLEAN DEFAULT false,
                                             started_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                             completed_at TIMESTAMP,
                                             user_id BIGINT NOT NULL,
                                             course_id BIGINT NOT NULL,
                                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                             CONSTRAINT fk_progress_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                             CONSTRAINT fk_progress_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
                                             CONSTRAINT unique_user_course UNIQUE (user_id, course_id)
);

-- Создание индексов для улучшения производительности
CREATE INDEX IF NOT EXISTS idx_courses_category ON courses(category);
CREATE INDEX IF NOT EXISTS idx_courses_locked ON courses(locked);
CREATE INDEX IF NOT EXISTS idx_courses_order ON courses(order_in_roadmap);
CREATE INDEX IF NOT EXISTS idx_lessons_course_id ON lessons(course_id);
CREATE INDEX IF NOT EXISTS idx_lessons_order ON lessons(order_in_course);
CREATE INDEX IF NOT EXISTS idx_user_progress_user_id ON user_progress(user_id);
CREATE INDEX IF NOT EXISTS idx_user_progress_course_id ON user_progress(course_id);
CREATE INDEX IF NOT EXISTS idx_user_progress_completed ON user_progress(completed);

-- Функция для автоматического обновления updated_at
CREATE OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Триггеры для автоматического обновления updated_at
CREATE TRIGGER update_users_updated_at BEFORE UPDATE ON users FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_courses_updated_at BEFORE UPDATE ON courses FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_lessons_updated_at BEFORE UPDATE ON lessons FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_user_progress_updated_at BEFORE UPDATE ON user_progress FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
