import {resumeCheckOutRender} from './checkout/orderSummary.js';
import {renderPaymentSummary} from './checkout/paymentSummary.js';
import {loadProductFromBackend} from '../data/products.js';
import {loadCartFromBackend} from '../data/cart.js';
import './stylescripts/fotter.js';
import './stylescripts/backtotop.js';

async function loadPage() {
    try{
        await loadProductFromBackend();
    
        await new Promise((resolve)=>{
            loadCartFromBackend(()=>{
                resolve();
            });
         });
    }catch(error){
       console.log("sumthing is worng !! ") ;
    }

    

     resumeCheckOutRender(); 
     renderPaymentSummary();
}loadPage()

