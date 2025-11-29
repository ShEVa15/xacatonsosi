document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('loginForm');

    form.addEventListener('submit', (e) => {
        e.preventDefault(); // Останавливаем стандартную отправку формы

        const loginValue = document.getElementById('login').value;
        const passwordValue = document.getElementById('password').value;

        // Здесь можно добавить логику отправки на сервер (AJAX/Fetch)
        console.log('Попытка авторизации:');
        console.log('Логин:', loginValue);
        console.log('Пароль:', passwordValue);

        // Пример визуальной реакции (опционально)
        const btn = document.querySelector('.submit-btn');
        const originalText = btn.innerText;
        btn.innerText = 'Вход...';
        
        setTimeout(() => {
            btn.innerText = originalText;
        }, 500);
    });
});
