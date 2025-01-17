import { validateEmail, validatePassword, validateMobile, validateName } from './validation.js';
import { errorMessages } from './message.js';
import { showError, clearError } from './ui.js';

document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('.auth-form');
    const nameInput = document.getElementById('name');
    const mobileInput = document.getElementById('mobile');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');

    form.addEventListener('submit', (e) => {
        e.preventDefault();
        let isValid = true;

        // Clear all previous errors
        ['name', 'mobile', 'email', 'password'].forEach(clearError);

        // Validate name
        if (!nameInput.value) {
            showError('name', errorMessages.nameRequired);
            isValid = false;
        } else if (!validateName(nameInput.value)) {
            showError('name', errorMessages.nameInvalid);
            isValid = false;
        }

        // Validate mobile
        if (!mobileInput.value) {
            showError('mobile', errorMessages.mobileRequired);
            isValid = false;
        } else if (!validateMobile(mobileInput.value)) {
            showError('mobile', errorMessages.mobileInvalid);
            isValid = false;
        }

        // Validate email if provided
        if (emailInput.value && !validateEmail(emailInput.value)) {
            showError('email', errorMessages.emailInvalid);
            isValid = false;
        }

        // Validate password
        if (!passwordInput.value) {
            showError('password', errorMessages.passwordRequired);
            isValid = false;
        } else if (!validatePassword(passwordInput.value)) {
            showError('password', errorMessages.passwordLength);
            isValid = false;
        }

        if (isValid) {
            // In a real app, this would make an API call
            const userData = {
                name: nameInput.value,
                mobile: mobileInput.value,
                email: emailInput.value,
                password: passwordInput.value
            };
            localStorage.setItem('amazon_user', JSON.stringify(userData));
            window.location.href = '/signin.html'; // Redirect to home page
        }
    });

    // Clear errors on input
    [nameInput, mobileInput, emailInput, passwordInput].forEach(input => {
        input.addEventListener('input', () => {
            clearError(input.id);
        });
    });
});