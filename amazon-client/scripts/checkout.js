import {resumeCheckOutRender} from './checkout/orderSummary.js';
import {renderPaymentSummary} from './checkout/paymentSummary.js';
// import {loadProductFromBackend} from '../data/products.js';
import { loadCartFromBackend ,loadedCart} from '../data/cart.js';
import './stylescripts/fotter.js';
import './stylescripts/backtotop.js';

// Fetch the token from localStorage
export function getToken() {
    try {
        const storedToken = localStorage.getItem('datacart');
        return storedToken ? JSON.parse(storedToken) : null;
    } catch (error) {
        console.error("Failed to parse token from localStorage:", error);
        return null;
    }
  }


export async function checkoutProcess() {
    try {
       const loadedCart = await loadCartFromBackend(getToken().token)
        resumeCheckOutRender(loadedCart);
       // Wait for the cart to load
       
      renderPaymentSummary(loadedCart);
    } catch (error) {
      console.error("Something is wrong during the checkout process!", error);
    }
  }
  
  // Call the function
  checkoutProcess();



