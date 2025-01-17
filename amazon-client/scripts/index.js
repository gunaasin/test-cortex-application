import './stylescripts/navbar.js';
import './stylescripts/fotter.js';
import './stylescripts/backtotop.js';


// nav index to product page
document.querySelectorAll(".adv-img").forEach((element)=>{
    element.addEventListener("click",()=>{
        window.location.href="amazon";
    });
})
