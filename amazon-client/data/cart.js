export let cart = JSON.parse(localStorage.getItem("cart"));

if(!cart){
  cart = [];
}

function setDataIntoLocalStorage(){
  localStorage.setItem("cart" , JSON.stringify(cart));
}


export function loadCartFromBackend(loadTheCart) {
  const XHR = new XMLHttpRequest();
  XHR.addEventListener('load', () => {
    console.log(XHR.response);
    // cart = JSON.parse();
    loadTheCart();
  });
  XHR.open('GET', 'https://supersimplebackend.dev/cart');
  XHR.send();
}


// update a cart quatity 

const cartQuantity = document.querySelector(".cart-quantity");
export function updateCartQuatity() {
  let cartQuantitys = 0;
  cart.forEach((item) => {
    cartQuantitys += item.quantity;
  });
  // console.log(cart);
  cartQuantity.innerText = cartQuantitys;
  setDataIntoLocalStorage();
}


/// removeTheItemToTheCart

export function deleteCart(producdId){
  const newCart= [] ;
  cart.forEach((cartItem)=>{
    // console.log(cartItem.id);
    if(cartItem.productId !== producdId){
      newCart.push(cartItem);
    }
  });
  cart = newCart;
  setDataIntoLocalStorage();
}


// update the delivery option
export function updateDeliveryOption(productId , deleiveryOptionId){

  cart.forEach((cartItem) => {

    if (cartItem.productId === productId) {
      cartItem.deleiveryOptionId = deleiveryOptionId;
    }
  }); 
  setDataIntoLocalStorage();
}