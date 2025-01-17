export const orders = JSON.parse(localStorage.getItem('orders')) || [];

console.log(orders);
export function addOrder(product){
    orders.unshift(product);
    saveToLocalStorage();
}

function saveToLocalStorage(){
    localStorage.setItem('orders',JSON.stringify(orders));
}