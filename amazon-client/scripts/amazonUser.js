import "./stylescripts/navbar.js";
import "./stylescripts/fotter.js";
import "./stylescripts/backtotop.js";

const accountOptions = [
    {
        image: "https://m.media-amazon.com/images/G/31/x-locale/cs/ya/images/Box._CB485927553_.png",
        title: "Your Orders",
        description: "Track, return, or buy things again"
    },
    {
        image: "https://m.media-amazon.com/images/G/31/x-locale/cs/ya/images/sign-in-lock._CB485931504_.png",
        title: "Login & security",
        description: "Edit login, name, and mobile number"
    },
    {
        image: "https://cdn-icons-png.freepik.com/256/5791/5791647.png?uid=R155768109&ga=GA1.1.164723959.1709136868",
        title: "Logout",
        description: "Logout account"
    },
    {
        image: "https://m.media-amazon.com/images/G/31/x-locale/cs/ya/images/address-map-pin._CB485934183_.png",
        title: "Your Addresses",
        description: "Edit addresses for orders and gifts"
    },
    {
        image: "https://m.media-amazon.com/images/G/31/AmazonBusiness/YAPATF/amazon_business_yap_atf._CB588250268_.jpg",
        title: "Your business account",
        description: "Sign up for free to save up to 28% with GST invoice and bulk discounts and purchase on credit."
    },
    {
        image: "https://m.media-amazon.com/images/G/31/x-locale/cs/ya/images/Payments._CB485926359_.png",
        title: "Payment options",
        description: "Edit or add payment methods"
    },
    {
        image: "https://m.media-amazon.com/images/G/31/x-locale/cs/help/images/gateway/self-service/contact_us._CB623781998_.png",
        title: "Contact Us",
        description: "Contact our customer service via phone or chat"
    }
];

// Fetch the token from localStorage
function getToken() {
    try {
        const storedToken = localStorage.getItem('datacart');
        return storedToken ? JSON.parse(storedToken) : null;
    } catch (error) {
        console.error("Failed to parse token from localStorage:", error);
        return null;
    }
}

// Logout function
async function logout(token) {
    if (!token || !token.token) {
        console.error("Invalid or missing token. Cannot logout.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/auth/logout`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token.token}`
            },
            body: JSON.stringify(token)
        });

        if (!response.ok) {
            console.error("Logout failed:", response.status, response.statusText);
            return;
        }

        console.log("Logout successful:", response);
        // Optionally redirect after logout
        window.location.href = "/signin";
    } catch (error) {
        console.error("Error during logout:", error);
    }
}


async function getAccess(token) {
    if (!token || !token.token) {
        console.error("Invalid or missing token. Cannot logout.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/auth/getKey`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token.token}`
            },
            body: JSON.stringify(token)
        });

        if (!response.ok) {
            window.location.href = "/signin";
            // console.error("Logout failed:", response.status, response.statusText);
            return;
        }

        // console.log("accsess granted successful:", response);
        // Optionally redirect after logout
        // 
    } catch (error) {
        console.error("Error during logout:", error);
        window.location.href = "/signin";
    }


    createAccountCards();
}



// Create account cards dynamically
function createAccountCards() {
    const container = document.getElementById("accountCards");
    const token = getToken();
    
    accountOptions.forEach((option) => {
        const card = document.createElement("div");
        card.className = "card";
        card.innerHTML = `
            <div class="card-box" id="card-box">
                <div class="img-Container">
                    <img src="${option.image}" alt="${option.title}">
                </div>
                <div>
                    <h2>${option.title}</h2>
                    <p>${option.description}</p>
                </div>
            </div>     
        `;

        card.addEventListener("click", () => {
            // console.log(`Navigating to: ${option.title}`);
            switch (option.title) {
                case "Your Orders":
                    window.location.href = "orders";
                    break;
                case "Your Addresses":
                    window.location.href = "address";
                    break;
                case "Logout":
                    logout(token);
                    break;
                case "Contact Us":
                    window.location.href = "https://gunaportfoliogn.vercel.app/";
                    break;
                case "Login & security":
                    window.location.href = "userInfo";
                    break;
                default:
                    console.warn("Unknown action for:", option.title);
            }
        });

        container.appendChild(card);
    });
}


// Initialize account cards
getAccess(getToken());



