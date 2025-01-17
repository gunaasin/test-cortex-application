import "./from.js";
import "./stylescripts/doggleForm.js";



document.addEventListener('DOMContentLoaded', function() {
    const paymentForm = document.getElementById('paymentForm');
    const placeOrderBtn = document.getElementById('placeOrderBtn');
    
    // Handle payment method selection
    paymentForm.addEventListener('change', function(e) {
        if (e.target.type === 'radio') {
            // Enable place order button when a payment method is selected
            placeOrderBtn.disabled = false;
            
            // Highlight selected payment option
            document.querySelectorAll('.payment-option').forEach(option => {
                option.style.borderColor = '#ddd';
            });
            e.target.closest('.payment-option').style.borderColor = '#2874f0';
        }
    });
    
    // Handle place order button click
    placeOrderBtn.addEventListener('click', function(e) {
        e.preventDefault();
        
        const selectedPayment = document.querySelector('input[name="payment"]:checked');
        
        if (!selectedPayment) {
            alert('Please select a payment method');
            return;
        }
        
        // Here you would typically handle the order submission
        alert('Order placed successfully!');
    });
    
    // Handle promo code application
    const promoInput = document.querySelector('.promo-code input');
    const promoButton = document.querySelector('.promo-code button');
    
    promoButton.addEventListener('click', function() {
        if (promoInput.value.trim()) {
            // Here you would typically validate the promo code
            alert('Promo code applied!');
            promoInput.value = '';
        } else {
            alert('Please enter a promo code');
        }
    });
});