package com.clone.amazon.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/admin")
    @PreAuthorize("hasRole('admin','seller')")
    public ResponseEntity<?> postProduct(
            @RequestBody ProductRequestDTO productRequestDTO
    ){
        try {
        var response = productService.addProduct(productRequestDTO);
             return ResponseEntity.ok(response) ;
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(Map.of("message" ,"this page not allowed"));
        }
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
    public ResponseEntity<?> getAllProductBasedOnUserSelectADV(
            @RequestParam String enc
    ){
        System.out.println( "enc is "+enc);
        if(enc!=null){
            var response = productService.getProductByADV(enc);
        return ResponseEntity.ok(response);

        }
        return ResponseEntity.badRequest().body(Map.of("message","wrong one" ));
    }
}


