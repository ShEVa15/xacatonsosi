document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('usernameForm');

    form.addEventListener('submit', (e) => {
        e.preventDefault(); // Останавливаем стандартную отправку формы

        const usernameValue = document.getElementById('username').value;
        const passwordValue = document.getElementById('password').value;

        // Здесь можно добавить логику отправки на сервер (AJAX/Fetch)
        console.log('Попытка авторизации:');
        console.log('Логин:', usernameValue);
        console.log('Пароль:', passwordValue);

        // Пример визуальной реакции (опционально)
        const btn = document.querySelector('.submit-btn');
        const originalText = btn.innerText;
        btn.innerText = 'Вход...';

        setTimeout(() => {
            alert(`Данные отправлены!\nЛогин: ${usernameValue}`);
            btn.innerText = originalText;
        }, 500);
    });
});
