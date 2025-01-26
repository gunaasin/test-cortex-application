import { getToken } from "../scripts/checkout.js";
import { resumeCheckOutRender } from "../scripts/checkout/orderSummary.js";
import { renderPaymentSummary } from "../scripts/checkout/paymentSummary.js";

export let loadedCart = [];

export async function loadCartFromBackend(token) {
  const tokenObject = {
    token: token,
    email: "max@gmail.com",
  };

  try {
    const response = await fetch(`http://localhost:8080/api/cart/product`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(tokenObject),
    });

    if (!response.ok) {
      console.error("Failed to fetch cart products. Redirecting to signin.");
      window.location.href = "/signin";
      return; 
    }

    let loadedCart = await response.json(); 
    loadedCart = loadedCart.map((item) => item); 
    return loadedCart;
    console.log("Cart products are loaded:", loadedCart);

  
  } catch (error) {
    console.error("Error during fetching cart products:", error);
    // window.location.href = "/signin";
  }
}


export let cart = JSON.parse(localStorage.getItem("cart"));

if (!cart) {
  cart = [];
}

function setDataIntoLocalStorage() {
  localStorage.setItem("cart", JSON.stringify(cart));
}



// update the delivery option
export function addProductInCart(request) {
  const token = getToken().token;
  const productData ={
    productId : request.productId,
    quantity : request.quantity,
    token: token,
    email: "max@gmail.com"
  }

  const postProduct = async (productData) => {
    try {
      const response = await fetch("http://localhost:8080/api/cart/add", {
        method: "POST",
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(productData)
      });

      if (!response.ok) {
        cart.push(request);
        setDataIntoLocalStorage();
        return new Error(`HTTP error :( ${response.status}`)
      }
      updateCartCount();
      console.log(' successfully:');
    } catch (error) {
      console.error("error", error);
    }
  }
  postProduct(productData);
}




// update the delivery option
export function updateDeliveryOption(cartItemId, deliveryOptionId) {

  const token = getToken().token;
  const productData ={
    cartItemId : cartItemId,
    deliveryOptionId : deliveryOptionId,
    token: token,
    email: "max@gmail.com"
  }

  const postProduct = async (productData) => {
    try {
      const response = await fetch("http://localhost:8080/api/cart/update", {
        method: "POST",
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(productData)
      });

      if (!response.ok) {
        return new Error(`HTTP error :( ${response.status}`)
      }
      loadedCart.length=0;
      loadedCart = await response.json();
      resumeCheckOutRender(loadedCart);
      renderPaymentSummary(loadedCart);
      // console.log('loadedCart successfully:', loadedCart);
    } catch (error) {
      console.error("error", error);
    }
  }
  postProduct(productData);

}