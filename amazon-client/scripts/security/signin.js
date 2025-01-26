import { validateEmail } from './validation.js';
import { errorMessages } from './message.js';
import { showError, clearError } from './ui.js';



document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById("login-form");
    const emailInput = document.getElementById('email');
    const submit = document.getElementById("submit");
    const message = document.getElementById("message");

    submit.addEventListener('click', (e) => {
        console.log("clicked")
        e.preventDefault();
        let isValid = true;

        clearError('email');
        if (!emailInput.value) {
            showError('email', errorMessages.emailRequired);
            isValid = false;
        } else if (emailInput.value.includes('@') && !validateEmail(emailInput.value)) {
            showError('email', errorMessages.emailInvalid);
            isValid = false;
        }

        if (isValid) {

            submit.disabled = true;
            submit.querySelector('.button-text').style.display = 'none';
            submit.querySelector('.spinner').style.display = 'inline-block';

            const email = emailInput.value;
            const password = document.getElementById("password").value;
            const userData = {
                mailId: email,
                password: password
            }


            const user = async (userData) => {
                try {
                    const response = await fetch("http://localhost:8080/api/auth/signIn", {
                        method: "POST",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(userData),
                        credentials: 'include' 
                    });

                    if (!response.ok) {
                        // message.innerText = "incoreerct credential";
                        showError('email', errorMessages.emailInvalid); 
                        showError('password', errorMessages.passwordInvalid);
                        return;

                    } else if (response.status === 202) {
                        console.log("Login successful");
                        checkCookie(); 
                        form.reset();
                    } else {
                        console.warn(`Unexpected status code: ${response.status}`);
                    }
    
                    localStorage.setItem("datacart",JSON.stringify(await response.json()));
                    console.log('signin key');
                    //  More appropriate message   
                    // console.log(JSON.parse(localStorage.getItem("datacart")));
                    window.location.href='amazonUser';

                } catch (error) {
                    console.error('Error during signin:', error);
                    message.innerText = "An error occurred during login."; 
                } finally {
                    submit.disabled = false;
                    submit.querySelector('.button-text').style.display = 'inline-block';
                    submit.querySelector('.spinner').style.display = 'none';
                }
            };

            function checkCookie() {
                fetch('/protected-resource', {
                    method: 'GET',
                    credentials: 'include' 
                })
                .then(response => {
                    if (response.ok) {
                        console.log('Cookie verification successful!');
                        // window.location.href = '/'; // Redirect on successful login
                    } else {
                        console.error('Cookie verification failed:', response.status);
                        message.innerText = "Authentication failed. Please try again.";
                    }
                })
                .catch(error => {
                    console.error('Error during cookie verification:', error);
                    message.innerText = "A network error occurred.";
                });
            }

            user(userData);


        }
    });

    submit.addEventListener('input', () => {
        clearError('email');
    });
});

// Display user information after successful login
function displayUserInfo() {

}
