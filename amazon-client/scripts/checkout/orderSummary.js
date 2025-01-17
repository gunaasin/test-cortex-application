import { cart, deleteCart, updateDeliveryOption } from "../../data/cart.js";
import { findproduct } from "../../data/products.js";
import { deliveryOptions, getDeleiveryOption } from "../../data/deliveryOptions.js";
import {renderPaymentSummary} from "./paymentSummary.js";


export function resumeCheckOutRender() {
    const orders = document.querySelector(".order-summary");

    let ordersHTML = "";

    cart.forEach((cartItem) => {
        const productId = cartItem.productId;
        const matchingProduct = findproduct(productId);
        const deleiveryOptionId = cartItem.deleiveryOptionId;
        const deliveryOption = getDeleiveryOption(deleiveryOptionId);
        const today = dayjs();
        const deleveryDate = today.add(deliveryOption.dDate, 'days');
        const dateString = deleveryDate.format('dddd, MMMM D');

        ordersHTML += `
          <div class="cart-item-container js-cart-item-container-${cartItem.productId}">
            <div class="delivery-date">
              Delivery date: ${dateString}
            </div>
            <div class="cart-item-details-grid">
              <img class="product-image"
                src="${matchingProduct.image}">
              <div class="cart-item-details">
                <div class="product-name">
                  ${matchingProduct.name}
                </div>
                <div class="product-price">
                   ${matchingProduct.price}
                </div>
                <div class="product-quantity">
                  <span>
                    Quantity: <span class="quantity-label">${cartItem.quantity}</span>
                  </span>
                  <span class="update-quantity-link link-primary">
                    Share
                  </span>
                  <span class="delete-quantity-link link-primary js-delete-product" data-producdId="${cartItem.productId}">
                    Delete
                  </span>
                </div>
              </div>

              <div class="delivery-options">
                <div class="delivery-options-title">
                  Choose a delivery option:
                </div>
                ${deleveryOptionHTML(matchingProduct.id, cartItem)}
              </div>

            </div>
          </div>`
    });



    /// genarate the deleivery-Options html 
    function deleveryOptionHTML(matchingProduct, cartItem) {

        let optionsHtml = "";
        deliveryOptions.forEach((options) => {
            const today = dayjs();
            const deleveryDate = today.add(options.dDate, 'days');
            const dateString = deleveryDate.format('dddd, MMMM D');
            const shipingFees = options.dCharge === 0 ? "FREE" : `₹ ${options.dCharge}`;
            const ischecked = options.dID === cartItem.deleiveryOptionId;
            optionsHtml += ` 
                        <div class="delivery-option js-delivery-option " 
                            data-deliveryId="${matchingProduct}"
                            data-delivery-mathing-Id="${options.dID}">

                            <input type="radio"
                            ${ischecked ? 'checked' : ''}
                            class="delivery-option-input "
                            name="delivery-option-${matchingProduct}">
            
                            <div>
                                <div class="delivery-option-date">
                                    ${dateString}
                                </div>
                                <div class="delivery-option-price">
                                    ${shipingFees} - Shipping
                                </div>
                            </div>
                        </div>
                        `
        });

        return optionsHtml;
    }

    orders.innerHTML = ordersHTML;

    document.querySelectorAll(".js-delete-product")
        .forEach((link) => {
            link.addEventListener("click", () => {
                const delProId = link.getAttribute("data-producdId");

                deleteCart(delProId);
                renderPaymentSummary();

                const removeDom = document.querySelector(`.js-cart-item-container-${delProId}`);
                removeDom.remove();


            });
        });

    document.querySelectorAll(".js-delivery-option")
        .forEach((element) => {
            element.addEventListener("click", () => {
                updateDeliveryOption(element.getAttribute("data-deliveryId"), element.getAttribute("data-delivery-mathing-Id"));
                resumeCheckOutRender();
                renderPaymentSummary();
            });

        });

}
