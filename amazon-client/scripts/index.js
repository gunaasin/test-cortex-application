import './stylescripts/navbar.js';
import './stylescripts/fotter.js';
import './stylescripts/backtotop.js';


// adv index to product page
document.querySelectorAll(".adv-img").forEach((element)=>{
    const productKeyword = element.getAttribute("data-product-keyword");

    element.addEventListener("click",()=>{
        // console.log(productKeyword);
        window.location.href = `amazon?d=${encodeURIComponent(btoa(JSON.stringify(productKeyword)))}`;
    });

})
