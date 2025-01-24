package com.clone.amazon.product;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private static List<Product> products  ;
    public ProductRepository productRepository;
    public ProductMapper productMapper;
    public ProductService(
            ProductRepository productRepository ,
            ProductMapper productMapper
    ){
        this.productRepository=productRepository;
        this.productMapper = productMapper;
    }



    public List<ProductResponseDTO> getAllProducts(){
        List<Product> products =productRepository.findAll();
        Collections.shuffle(products);

        return products
                .stream()
                .map(productMapper::productResponseDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO addProduct(ProductRequestDTO dto){
        var response =  productRepository.save(
                productMapper.convertRequestToProduct(dto)
        );
        return productMapper.productResponseDTO(response);
    }

    public List<ProductResponseDTO> getProductBySearch(String keyword){
         return  productRepository.searchProducts(keyword)
                 .stream()
                 .map(productMapper::productResponseDTO)
                 .toList();
    }

    public List<ProductResponseDTO> getRelatedProducts(String keyword) {
        String[] array = Arrays.stream(keyword.split(","))
                .map(x -> x.trim())
                .toArray(String[]::new);

        List<Product> limitedProducts = productRepository.relatedProducts(array[0], array[1]);

        // Shuffle the list to randomize the order
        Collections.shuffle(limitedProducts);

        return limitedProducts.stream()
                .limit(6)// Limit the list to 6 products
                .map(productMapper::productResponseDTO)
                .collect(Collectors.toList());
    }


    // get ADV related products
    public List<ProductResponseDTO> getProductByADV(String enc) {
        String decodeData = new String(Base64.getDecoder().decode(enc), StandardCharsets.UTF_8);
        System.out.println("Decoded Data: " + decodeData);

        if (decodeData.contains("&")) {
            String[] productInfo = Arrays.stream(decodeData.split("&"))
                    .map(x -> x.trim().replaceAll("^\"|\"$", ""))  // Trim each part to remove unnecessary spaces
                    .toArray(String[]::new);

            products = productRepository.getProductBasedOnBrandAndCategory(productInfo[0], productInfo[1]);
            System.out.println("Products with extracted values: " + products);
        }
        else if (decodeData.contains("*")) {
            String[] productInfo = Arrays.stream(decodeData.split("\\*"))
                    .map(x -> x.trim().replaceAll("^\"|\"$", ""))
                    .toArray(String[]::new);
            products = productRepository.getProductBasedOnKeyWordAndCategory(productInfo[0],productInfo[1]);

        } else if(decodeData.contains(",")) {
            String[] productInfo = Arrays.stream(decodeData.split(","))
                    .map(x -> x.trim().replaceAll("^\"|\"$", ""))
                    .toArray(String[]::new);
            products = productRepository.getProductBasedOnKeyWordAndKeyWord(productInfo[0] , productInfo[1]);
        }
        
        return products.stream()
                .map(productMapper::productResponseDTO)
                .toList();
    }
}

