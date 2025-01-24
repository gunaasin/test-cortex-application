const accountOptions = [
    {
        title: "Your Orders",
        description: "Track, return, or buy things again"
    },
    {
        title: "Login & security",
        description: "Edit login, name, and mobile number"
    },
    {
        title: "Prime",
        description: "View benefits and payment settings"
    },
    {
        title: "Your Addresses",
        description: "Edit addresses for orders and gifts"
    },
    {
        title: "Your business account",
        description: "Sign up for free to save up to 28% with GST invoice and bulk discounts and purchase on credit."
    },
    {
        title: "Payment options",
        description: "Edit or add payment methods"
    },
    {
        title: "Amazon Pay balance",
        description: "Add money to your balance"
    },
    {
        title: "Contact Us",
        description: "Contact our customer service via phone or chat"
    }
];

function createAccountCards() {
    const container = document.getElementById('accountCards');
    
    accountOptions.forEach(option => {
        const card = document.createElement('div');
        card.className = 'card';
        
        card.innerHTML = `
            <div class="img-Container">
                <img src="${d}" alt="" srcset="">
            </div>
            <div>
                <h2>${option.title}</h2>
                <p>${option.description}</p>
            </div>
        `;
        
        card.addEventListener('click', () => {
            // In a real application, this would navigate to the respective page
            console.log(`Navigating to: ${option.title}`);
        });
        
        container.appendChild(card);
    });
}

// Initialize the page
createAccountCards();