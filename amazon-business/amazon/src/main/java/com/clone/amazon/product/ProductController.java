package com.clone.amazon.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    public ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

//    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> productList(){
        var allProduct = productService.getAllProducts();

        if(allProduct == null || allProduct.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDTO> postProduct(
            @RequestBody ProductRequestDTO productRequestDTO
    ){
        var response = productService.addProduct(productRequestDTO);
        return ResponseEntity.ok(response) ;
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<ProductResponseDTO>> getListOfProductBySearch(
            @RequestParam String keyword    ){
        var response = productService.getProductBySearch(keyword);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/relatedProduct")
    public ResponseEntity<List<ProductResponseDTO>> getRelatedProducts(
            @RequestParam String keyword
    ){
       var response = productService.getRelatedProducts(keyword);
        return ResponseEntity.ok(response) ;
    }

    @GetMapping("main/products")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductBasedOnUserSelectADV(
            @RequestParam String enc
    ){
        var response = productService.getProductByADV(enc);
        return ResponseEntity.ok(response);
    }
}


