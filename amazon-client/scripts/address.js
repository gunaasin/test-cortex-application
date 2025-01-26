import "./stylescripts/navbar.js";
import "./stylescripts/fotter.js";
import "./stylescripts/backtotop.js"

// Store addresses in localStorage
let addresses = JSON.parse(localStorage.getItem('addresses') || '[]');
let editingAddressId = null;

// DOM Elements
const addAddressCard = document.getElementById('addAddressCard');
const addressModal = document.getElementById('addressModal');
const closeBtn = document.querySelector('.close');
const addressForm = document.getElementById('addressForm');
const addressList = document.getElementById('addressList');
const typeButtons = document.querySelectorAll('.type-btn');
const modalTitle = document.querySelector('.modal-content h2');
const submitBtn = document.querySelector('.submit-btn');

// Show modal when clicking add address card
addAddressCard.addEventListener('click', () => {
    showModal();
});

function showModal(address = null) {
    addressModal.style.display = 'block';
    addressForm.reset();
    editingAddressId = null;
    modalTitle.textContent = 'Add a new address';
    submitBtn.textContent = 'Add address';
    
    // Reset address type buttons
    typeButtons.forEach(btn => btn.classList.remove('active'));
    typeButtons[0].classList.add('active');

    if (address) {
        editingAddressId = address.id;
        modalTitle.textContent = 'Edit address';
        submitBtn.textContent = 'Update address';
        
        // Fill form with address data
        document.getElementById('fullName').value = address.fullName;
        document.getElementById('mobile').value = address.mobile;
        document.getElementById('pincode').value = address.pincode;
        document.getElementById('address').value = address.address;
        document.getElementById('city').value = address.city;
        document.getElementById('state').value = address.state;
        
        // Set correct address type button
        typeButtons.forEach(btn => {
            if (btn.dataset.type === address.type) {
                btn.classList.add('active');
            }
        });
    }
}

// Close modal when clicking close button or outside
closeBtn.addEventListener('click', () => {
    addressModal.style.display = 'none';
});

window.addEventListener('click', (e) => {
    if (e.target === addressModal) {
        addressModal.style.display = 'none';
    }
});

// Handle address type selection
typeButtons.forEach(button => {
    button.addEventListener('click', () => {
        typeButtons.forEach(btn => btn.classList.remove('active'));
        button.classList.add('active');
    });
});

// Handle form submission
addressForm.addEventListener('submit', (e) => {
    e.preventDefault();
    
    const addressData = {
        id: editingAddressId || Date.now(),
        fullName: document.getElementById('fullName').value,
        mobile: document.getElementById('mobile').value,
        pincode: document.getElementById('pincode').value,
        address: document.getElementById('address').value,
        city: document.getElementById('city').value,
        state: document.getElementById('state').value,
        type: document.querySelector('.type-btn.active').dataset.type
    };
    
    if (editingAddressId) {
        // Update existing address
        addresses = addresses.map(addr => 
            addr.id === editingAddressId ? addressData : addr
        );
    } else {
        // Add new address
        addresses.push(addressData);
    }
    
    localStorage.setItem('addresses', JSON.stringify(addresses));
    renderAddresses();
    addressModal.style.display = 'none';
    addressForm.reset();
});

// Delete address
function deleteAddress(id) {
    addresses = addresses.filter(addr => addr.id !== id);
    localStorage.setItem('addresses', JSON.stringify(addresses));
    renderAddresses();
}

// Edit address
function editAddress(id) {
    const address = addresses.find(addr => addr.id === id);
    if (address) {
        showModal(address);
    }
}

// Render addresses
function renderAddresses() {
    addressList.innerHTML = '';
    
    addresses.forEach(addr => {
        const addressCard = document.createElement('div');
        addressCard.className = 'address-card';
        
        addressCard.innerHTML = `
            <span class="address-type">${addr.type}</span>
            <h3>${addr.fullName}</h3>
            <p>${addr.address}</p>
            <p>${addr.city}, ${addr.state} ${addr.pincode}</p>
            <p>Mobile: ${addr.mobile}</p>
            <div class="card-actions">
                <button class="edit-btn" onclick="editAddress(${addr.id})">Edit</button>
                <button class="delete-btn" onclick="deleteAddress(${addr.id})">Delete</button>
            </div>
        `;
        
        addressList.appendChild(addressCard);
    });
}

// Make functions available globally
window.deleteAddress = deleteAddress;
window.editAddress = editAddress;

// Initial render
renderAddresses();