document.addEventListener('DOMContentLoaded', () => {

    const roadmapData = [
        {
            id: 1,
            title: "HTML5 & Семантика",
            duration: "2 недели",
            status: "done",
            description: "Глубокое погружение в семантическую верстку, доступность (a11y) и SEO-оптимизацию структуры документа. Разбор новых тегов HTML5.",
            btnText: "Повторить материал"
        },
        {
            id: 2,
            title: "CSS Grid & Flexbox",
            duration: "3 недели",
            status: "done",
            description: "Мастер-класс по современным методам раскладки. Создание сложных адаптивных сеток, выравнивание, Grid Areas и Flex-свойства.",
            btnText: "Повторить материал"
        },
        {
            id: 3,
            title: "JavaScript Core",
            duration: "4 недели",
            status: "in-progress",
            description: "Основы языка, работа с DOM, события, замыкания, контекст (this) и прототипное наследование. Решение алгоритмических задач.",
            btnText: "Продолжить обучение"
        },
        {
            id: 4,
            title: "React & Redux",
            duration: "5 недель",
            status: "locked",
            description: "Компонентный подход, хуки (Hooks), управление состоянием через Redux Toolkit и взаимодействие с API. Создание SPA.",
            btnText: "Доступ закрыт"
        },
        {
            id: 5,
            title: "TypeScript & Architecture",
            duration: "3 недели",
            status: "locked",
            description: "Статическая типизация для больших проектов. Паттерны проектирования, SOLID принципы во фронтенде и настройка CI/CD.",
            btnText: "Доступ закрыт"
        }
    ];

    const stepsContainer = document.getElementById('roadmapSteps');
    const titleEl = document.getElementById('stepTitle');
    const descEl = document.getElementById('stepDesc');
    const durationEl = document.getElementById('stepDuration');
    const statusEl = document.getElementById('stepStatus');
    const actionBtn = document.getElementById('stepActionBtn');
    const contentArea = document.getElementById('detailsContent');

    let activeIndex = 0;

    if (actionBtn) {
        actionBtn.addEventListener('click', () => {
            // Переходим на страницу курса только если кнопка не заблокирована
            // (хотя disabled атрибут и так не даст кликнуть, это доп. проверка)
            const currentStep = roadmapData[activeIndex];
            if (currentStep.status !== 'locked') {
                window.location.href = 'course.html';
            }
        });
    }

    function renderDots() {
        if (!stepsContainer) return;
        stepsContainer.innerHTML = '';

        roadmapData.forEach((step, index) => {
            const dot = document.createElement('div');
            dot.classList.add('step-dot');
            dot.textContent = index + 1;

            if (index === activeIndex) {
                dot.classList.add('active');
            } else if (step.status === 'done') {
                dot.classList.add('completed');
            }

            dot.addEventListener('click', () => handleDotClick(index));
            stepsContainer.appendChild(dot);
        });
    }

    function handleDotClick(index) {
        if (index === activeIndex) return;

        activeIndex = index;
        renderDots();

        contentArea.classList.add('fade-out');

        setTimeout(() => {
            updateContent(roadmapData[index]);
            contentArea.classList.remove('fade-out');
        }, 300);
    }

    function updateContent(data) {
        titleEl.textContent = data.title;
        descEl.textContent = data.description;
        durationEl.textContent = `⏱ ${data.duration}`;
        actionBtn.textContent = data.btnText;

        statusEl.className = 'badge status-badge';
        if (data.status === 'done') {
            statusEl.textContent = 'Завершено';
            statusEl.classList.add('done');
            actionBtn.disabled = false;
        } else if (data.status === 'in-progress') {
            statusEl.textContent = 'В процессе';
            statusEl.classList.add('in-progress');
            actionBtn.disabled = false;
        } else {
            statusEl.textContent = 'Закрыто';
            statusEl.classList.add('locked');
            actionBtn.disabled = true;
            actionBtn.style.opacity = '0.6';
            actionBtn.style.cursor = 'not-allowed';
            return;
        }

        actionBtn.style.opacity = '1';
        actionBtn.style.cursor = 'pointer';
    }

    const currentStepIndex = roadmapData.findIndex(s => s.status === 'in-progress');
    activeIndex = currentStepIndex !== -1 ? currentStepIndex : 0;

    if (stepsContainer) {
        renderDots();
        updateContent(roadmapData[activeIndex]);
    }


    const heroSection = document.querySelector('.hero-visual');
    const codeWindow = document.querySelector('.code-editor-window');

    if (heroSection && codeWindow) {
        document.addEventListener('mousemove', (e) => {
            if (window.innerWidth < 768) return;

            const centerX = window.innerWidth / 2;
            const centerY = window.innerHeight / 2;

            const moveX = (e.clientX - centerX) / 40;
            const moveY = (e.clientY - centerY) / 40;

            codeWindow.style.transform = `rotateX(${-moveY}deg) rotateY(${moveX}deg)`;
        });

        document.addEventListener('mouseleave', () => {
            codeWindow.style.transform = `rotateX(5deg) rotateY(-2deg)`;
        });
    }
});