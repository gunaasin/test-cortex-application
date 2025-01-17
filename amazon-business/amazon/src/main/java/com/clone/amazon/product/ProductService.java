package com.clone.amazon.product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
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
}

