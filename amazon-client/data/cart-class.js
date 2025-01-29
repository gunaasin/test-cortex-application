class Cart{
    
    cartItems= undefined;

    updateLocalStorage() {
        this.cartItems = JSON.parse(localStorage.getItem("cart"));
        if (!this.cartItems) {
            this.cartItems = [];
        }
    }

    setDataIntoLocalStorage() {
        localStorage.setItem("cart", JSON.stringify(this.cartItems));
    }

    // update a cart quatity 
    updateCartQuatity() {
        const cartQuantity = document.querySelector(".cart-quantity");
        let cartQuantitys = 0;
        cart.forEach((item) => {
            cartQuantitys += item.productQuantity;
        });
        cartQuantity.innerText = cartQuantitys;
        setDataIntoLocalStorage();
    }

    /// removeTheItemToTheCart
    deleteCart(producdId) {
        const newCart = [];
        cart.forEach((cartItem) => {
            if (cartItem.id !== producdId) newCart.push(cartItem);
        });
        cart = newCart;
        setDataIntoLocalStorage();
    }

    // update the delivery option
    updateDeliveryOption(productId, deleiveryOptionId) {
        cart.forEach((cartItem) => {
            if (cartItem.id === productId) cartItem.deleiveryOptionId = deleiveryOptionId;
        });
        setDataIntoLocalStorage();
    }
}

const cart = new cart();













