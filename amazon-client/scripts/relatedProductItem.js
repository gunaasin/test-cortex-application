import getStarRating from "./util/starRatingGenarater.js";

const params = new URLSearchParams(window.location.search);
const product = JSON.parse(decodeURIComponent(params.get("d")));

// related product section 
const relatedProductkeyword = product.category.categoryName + ", " + product.category.brand;
let relatedProducts = [];

function loadRelatedProduct() {
    const promise = fetch(`http://localhost:8080/api/products/relatedProduct?keyword=${relatedProductkeyword}`).then((response) => {
        return response.json();
    }).then((productData) => {

         relatedProducts = productData.map((item) => { return item;});

        //  console.log('related products are loaded');
        
    }).catch((error) => {
        console.error("some thing is wrong please try again later :(");
        console.error(error);
    });
    return promise;
}

loadRelatedProduct().then(() => {
    loadTheRelatedProduct();
})


function loadTheRelatedProduct() {
    console.log("half black star: \u{2BDA}");
    let relatedProductHTML = "";
    relatedProducts.forEach((item) => {
        // console.log(item);

        relatedProductHTML += `
        <a href="/product?d=${encodeURIComponent(JSON.stringify(item))}"  target="_blank"" class="product-card">
            <img src="${item.image}" alt="Related Product 1">
            <h3>${item.name}</h3>
            <div class="price">₹ ${item.price}</div>
            <div class="rating">
               
                ${getStarRating(item.rating.stars)}
                ${item.rating.count}
            </div>
            <div class="prime">prime</div>
        </a>`
    });

    const relatedProductGrid = document.querySelector(".products-grid");
    relatedProductGrid.innerHTML=relatedProductHTML;
}



