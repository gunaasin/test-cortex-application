import { products } from "../../data/products.js";
const navbar = document.getElementById("amazon-nav");

navbar.innerHTML = `
    <div class="amazon-header-left-section">
        <a href="/" class="header-link">
            <img class="amazon-logo" src="images/amazon-logo-white.png">
            <img class="amazon-mobile-logo" src="images/amazon-mobile-logo-white.png">
            <p class="nav-in-text">.in</p>
        </a>
    </div>

    <div class="amazon-header-left-second">
        <p class="delivery-location">
            Delivering to Bengaluru 562129
        </p>
        <h3 class="update-location"><i class="fas fa-map-marker-alt"></i> &nbsp;Update location</h3>
    </div>

    <div class="amazon-header-middle-section">
        <select id="options" class="landing-search-options">
            <option value="1">All</option>
            <option value="2">Alexa Skills</option>
            <option value="3">Amazon Devices</option>
            <option value="4">Amazon Fashion</option>
            <option value="5">Amazon Pharmacy</option>
            <option value="6">Appliances</option>
            <option value="7">Books</option>
            <option value="8">Clothing & Accessories</option>
            <option value="9">Electronics</option>
            <option value="10">Home & Kitchen</option>
            <option value="11">Kindle Store</option>
            <option value="12">Gift Cards</option>
            <option value="13">Music</option>
            <option value="14">Mobile</option>
            <option value="15">Sports, Fitness & Outdoors</option>
        </select>
        <input class="search-bar" id="search-bar" type="text" placeholder="Search Amazon.in">

        <button class="search-button" id="search-btn" >
            <img class="search-icon" src="images/icons/search-icon.png">
        </button>
    </div>

    <a href="signin" class="amazon-header-right-second">
        <p class="sign-in-nav"> Hello, Sign in </p>
        <h3 class="account-list-nav"> Account & list</h3>
    </a>

    <div class="amazon-header-right-section">
        <a class="orders-link header-link" href="orders">
            <h2 class="orders-text"><span>Returns <br></span> & Orders</h2>
        </a>

        <a class="cart-link header-link" href="checkout">
            <img class="cart-icon" src="images/icons/cart-icon.png">

            <div class="cart-quantity">0</div>
            <div class="cart-text">Cart</div>
        </a>
    </div>
`;

const searchBarData = document.getElementById("search-bar");
const searchBtn = document.getElementById("search-btn");
document.addEventListener("DOMContentLoaded", () => {

    searchBarData.addEventListener("keydown", (event) => {
        if (event.key === "Enter") {
        
            let keyword = searchBarData.value.trim();
            window.location.href = `amazon?ds=${encodeURIComponent(btoa(JSON.stringify(keyword)))}`;
        }
    });

    searchBtn.addEventListener("click", () => {

        let keyword = searchBarData.value.trim();
        window.location.href = `amazon?ds=${encodeURIComponent(btoa(JSON.stringify(keyword)))}`;
    })
})




