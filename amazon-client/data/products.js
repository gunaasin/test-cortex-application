import { pageNotFont404 } from "../scripts/stylescripts/pageNotFound.js";

export let products = [];
export let cart = [];

export function loadProductFromBackend() {

  products.length = 0;
  const reader = localStorage.getItem('information-entry');
  const params = new URLSearchParams(window.location.search);
  const keywords = params.get("d");
  const main = document.querySelector(".amazon-body");

  const promise = fetch(`http://localhost:8080/api/main/products?enc=${keywords}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  }).then((response) => {

    return response.json();
  }).then((productData) => {
    products = productData.map((item) => {
      return item;
    });
  }).catch((error) => {
    main.innerHTML = "";
    console.error("some thing is wrong please try again later :(");
    console.error(error);
    main.innerHTML = pageNotFont404;
  });

  return promise;
}



export function loadProductBasedOnSearch(keyword) {
  const promise = fetch(`http://localhost:8080/api/products/search?keyword=${keyword}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json"
    }
  }
  ).then((response) => {
    return response.json();
  }).then((productData) => {

    products.length = 0;
    products.push(...productData);

    console.log('products are loaded');
  }).catch((error) => {
    console.error("some thing is wrong please try again later :(");
    console.error(error);
  });

  return promise;
}


export function findproduct(productId) {
  let matchingProduct;
  products.forEach((product) => {
    if (parseInt(productId) === product.id) matchingProduct = product;
  });
  return matchingProduct;
}
