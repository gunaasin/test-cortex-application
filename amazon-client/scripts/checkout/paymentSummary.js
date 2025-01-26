import { cart } from "../../data/cart.js";
import { findproduct } from "../../data/products.js";
import { getDeleiveryOption } from "../../data/deliveryOptions.js";
import { convertMoney } from "../util/money.js";
import { addOrder } from "../../data/order.js";

// console.log(cart);
export function renderPaymentSummary(loadedCart) {
  let totalproductPrice = 0;
  let shipingPrice = 0;

  loadedCart.forEach((cartItem) => {
    const product = cartItem.productResponseForCartDTO;
    totalproductPrice += product.price * cartItem.quantity;
    const shipingFees = cartItem.deliveryCharge;
    shipingPrice += shipingFees;
  });

  const totalBeforeTax = totalproductPrice + shipingPrice;
  const taxOfTotal = Math.round(totalBeforeTax * 0.12);
  const totalCost = totalBeforeTax + taxOfTotal;

 

  const paymentSummaryHTML =
    `
         <div class="payment-summary-title">
            Order Summary
          </div>

          <div class="payment-summary-row">
            <div>Items (${cart.length}):</div>
            <div class="payment-summary-money">₹ ${totalproductPrice}</div>
          </div>

          <div class="payment-summary-row">
            <div>Shipping &amp; handling:</div>
            <div class="payment-summary-money">₹ ${shipingPrice}</div>
          </div>

          <div class="payment-summary-row subtotal-row">
            <div>Total before tax:</div>
            <div class="payment-summary-money">₹ ${totalBeforeTax}</div>
          </div>

          <div class="payment-summary-row">
            <div>Estimated tax (12%):</div>
            <div class="payment-summary-money">₹ ${taxOfTotal}</div>
          </div>

          <div class="payment-summary-row total-row">
            <div>Order total:</div>
            <div class="payment-summary-money">₹ ${totalCost}</div>
          </div>

          <button class="place-order-button button-primary js-place-order">
            Place your order
          </button>
    `

  document.querySelector(".js-payment-summary").innerHTML = paymentSummaryHTML;


  const orderPlaceButton = document.querySelector(".js-place-order");
   orderPlaceButton.addEventListener('click', async () => {
   
      orderPlaceButton.innerHTML = "You'r order is process";
      try {
        const responce = await fetch('https://supersimplebackend.dev/orders', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            cart:cart,
          })
        });

        if(!responce.ok){
          throw new Error('http error');
        }
        addOrder(cart);
        const order = await responce.json();
        console.log(order);
      }catch(error){
        console.log('some thing is worng');
      }
      openOrders();
         
  });

  const openOrders =()=>{
    setTimeout(()=>{
      window.location.href="payment";
    },1000);
  }


}