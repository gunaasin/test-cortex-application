import { cart, updateCartQuatity } from "../data/cart.js";
import { deliveryOptions } from "../data/deliveryOptions.js";
import { products, loadProductFromBackend , loadProductBasedOnSearch } from "../data/products.js";
import {loadFilterFunction} from "./stylescripts/productfilter.js";
// import './stylescripts/pricerangesetter.js';
import './stylescripts/fotter.js';
import './stylescripts/backtotop.js';

loadProductFromBackend().then(() => {
  loadTheProduct();
  loadFilterFunction();
});




const searchBarData = document.getElementById("search-bar");
document.addEventListener("DOMContentLoaded", () => {
  searchBarData.addEventListener("keydown" , (event)=>{
    if(event.key === "Enter"){
      products.length=0;
      someData.categories.length=0;
      
      let keyword = searchBarData.value;
  
      loadProductBasedOnSearch(keyword).then(()=>{
        loadTheProduct();
        loadFilterFunction();
      });
      searchBarData.value ="";
    }
  });
})





export const someData = {
  categories: [],
};





export function loadTheProduct() {
  let productHTML = "";
  products.forEach((products) => {

    someData.categories.push(products.category.categoryName);

    
    productHTML += `
        <div class="product-container">
        <a href="product?d=${encodeURIComponent(JSON.stringify(products))}"  target="_blank" class="callable-container">
          <div class="product-image-container">
            <img class="product-image"
              src="${products.image}">
          </div>

          <div class="product-name limit-text-to-2-lines"> ${products.name}</div>

          
           <div class="product-rating-container">
                    <div class="stars">★★★★☆</div>
                    <p class="product-rating-count link-primary" >${products.rating.count}</p>
            </div>

          <div class="product-price"> 
            <span class="currency">₹</span>
            ${products.price}
          </div>
          </a>

          <div class="product-quantity-container">
            <select id ="options">
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
              <option value="9">9</option>
              <option value="10">10</option>
            </select>
          </div>
         
          ${ products.category.categoryName === 'clothing' ? 
          `<a href="images/clothing-size-chart.png" target="_blank" class="size-chart">size chart</a>` : ``}
          
          <div class="product-spacer"></div>

          <div class="added-to-cart">
            <img src="images/icons/checkmark.png">
            Added to cart
          </div>
           ${products.price > 30000 ? `
          <div class="prime">
          </div>
          ` : ""}

          <button class="add-to-cart-button button-primary add-to-cart" data-product-id="${products.id}">
            Add to Cart
          </button>
        </div>
      `
  });

  // load the html in a page
  const productContainer = document.querySelector(".js-product-container");
  productContainer.innerHTML = productHTML;
 
  // seclect the quantity in dropdown
 const selectQuatity = document.querySelectorAll("#options");
 let selectedValue = 1;
 selectQuatity.forEach((select) => {
   select.addEventListener("change", () => {
     selectedValue = parseInt(select.value);
   });
 });


 // add to cart array section
 function addToCartFunction(productId) {
   let productIsThere;

   // after click the add to cart the select section change to 1
   selectQuatity.forEach((select) => {
     select.value = 1;
   })

   cart.forEach((item) => {
     if (item.productId === productId) {
       productIsThere = item;
     }
   })

   if (productIsThere) {
     if (selectedValue > 1) {
       productIsThere.quantity += selectedValue;
     } else {
       productIsThere.quantity += 1;
     }
   } else {
     cart.push({
       productId: productId,
       quantity: selectedValue,
       deleiveryOptionId: '1'
     });
   }
 }

 /* add to cart section */
 const addToCart = document.querySelectorAll(".add-to-cart");
 addToCart.forEach((button) => {
   button.addEventListener("click", () => {
     button.innerHTML = '<img src="images/icons/checkmark.png" class="tick-img">Added to cart';
     setTimeout(() => {
       button.innerHTML = "Add to Cart";
     }, 4000);

     const productId = button.getAttribute("data-product-id");
     addToCartFunction(productId);
     updateCartQuatity();
   });
 });
 
}





