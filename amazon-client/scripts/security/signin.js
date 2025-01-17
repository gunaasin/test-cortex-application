import { validateEmail } from './validation.js';
import { errorMessages } from './message.js';
import { showError, clearError } from './ui.js';

document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('.auth-form');
    const emailInput = document.getElementById('email');

    form.addEventListener('submit', (e) => {
        e.preventDefault();
        let isValid = true;

        // Clear previous errors
        clearError('email');

        // Validate email/mobile
        if (!emailInput.value) {
            showError('email', errorMessages.emailRequired);
            isValid = false;
        } else if (emailInput.value.includes('@') && !validateEmail(emailInput.value)) {
            showError('email', errorMessages.emailInvalid);
            isValid = false;
        }

        if (isValid) {
            // In a real app, this would make an API call
            localStorage.setItem('amazon_email', emailInput.value);
            window.location.href = '/index.html'; // Redirect to password page
        }
    });

    // Clear errors on input
    emailInput.addEventListener('input', () => {
        clearError('email');
    });
});