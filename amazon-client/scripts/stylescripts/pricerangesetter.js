const minPrice = document.getElementById("minPrice");
const maxPrice = document.getElementById("maxPrice");
const minPriceDisplay = document.getElementById("minPriceDisplay");
const maxPriceDisplay = document.getElementById("maxPriceDisplay");
const resetButton = document.getElementById("resetButton");

// Update displayed values when sliders move
minPrice.addEventListener("input", updatePrices);
maxPrice.addEventListener("input", updatePrices);

// Function to update displayed prices
export function updatePrices() {
    let min = parseInt(minPrice.value);
    let max = parseInt(maxPrice.value);

    if (min > max) {
        // Swap values to ensure min is less than max
        let temp = min;
        min = max;
        max = temp;
    }

    minPriceDisplay.textContent = `₹${min.toLocaleString()}`;
    maxPriceDisplay.textContent = `₹${max.toLocaleString()}`;
}

// Reset functionality
resetButton.addEventListener("click", (e) => {
    e.preventDefault();
    minPrice.value = 100;
    maxPrice.value = 92300;
    updatePrices();
});


