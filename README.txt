Прототип цифровой образовательной платформы, разработанный для демонстрации современного процесса обучения. Система построена на микросервисной архитектуре с использованием контейнеризации Docker.

## Функциональность

* Каталог курсов: Фильтрация по категориям, просмотр детальной информации.
* Роадмэп: Визуализация прогресса обучения (Frontend Roadmap).
* Безопасность:
    * Шифрование соединения с базой данных (PostgreSQL SSL).
    * Аутентификация пользователей (Spring Security).
* Архитектура: Разделение на Frontend (Nginx), Backend (Spring Boot) и Database (PostgreSQL).

## Технологический стек

### Backend
* Java 21 (Eclipse Temurin)
* Spring Boot 3.x (Web, Security, Data JPA)
* Hibernate (ORM)
* Maven (Сборка)

### Frontend
* HTML5 / CSS3
* JavaScript (Vanilla JS, Fetch API)
* Nginx (Reverse Proxy & Static Server)

### Database
* PostgreSQL 15
* SSL: Принудительное шифрование (sslmode=require)

### DevOps
* Docker & Docker Compose
* Bash Scripts (Автоматизация развертывания)

## Структура проекта

* db-init/ - Скрипты инициализации БД и генерации SSL сертификатов.
* frontend_src/ - Исходный код фронтенда (HTML/CSS/JS).
* src/ - Исходный код Java Backend.
* docker-compose.yml - Конфигурация запуска (режим с Nginx).
* run_platform.sh - Основной скрипт управления запуском.
* setup_project.sh - Скрипт генерации файловой структуры.

## Установка и запуск

Для работы требуются Docker и Docker Compose.

1. Генерация структуры проекта и файлов:
   ```bash
   ./setup_project.sh
Запуск платформы:

Bash

./run_platform.sh
При запуске выберите режим работы:

Mode 1 (Standalone): Запуск через Nginx (порт 80). Рекомендуемый режим.

Mode 2 (Direct): Прямой доступ к Backend (8080) и Frontend (3000).

Доступ к системе
После успешного запуска приложение доступно по адресу: http://localhost (или IP-адрес вашего сервера)

Тестовые учетные данные:

Логин: student1

Пароль: 123

Архитектура контейнеров
psb-db: PostgreSQL. При старте генерирует самоподписанные сертификаты для SSL.

psb-backend: Spring Boot API. Подключается к БД только по защищенному каналу. Использует зеркала Maven для ускорения сборки.

psb-frontend: Nginx. Раздает статические файлы и проксирует запросы к API.