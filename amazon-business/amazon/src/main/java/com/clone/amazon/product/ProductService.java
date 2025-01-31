package com.clone.amazon.product;
import com.clone.amazon.security.JwtService;
import com.clone.amazon.user.AmazonUserRepository;
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
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private  final JwtService jwtService;
    private final AmazonUserRepository amazonUserRepository;


    public ProductService(
            ProductRepository productRepository ,
            ProductMapper productMapper,
            AmazonUserRepository amazonUserRepository,
            JwtService jwtService
    ){
        this.productRepository=productRepository;
        this.productMapper = productMapper;
        this.amazonUserRepository=amazonUserRepository;
        this.jwtService=jwtService;
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
    var extractUser = jwtService.extractRoles(dto.token()).getFirst();
    var amazonuser = amazonUserRepository.findByEmail(dto.email());
    if(amazonuser.getRole().equals("admin") && extractUser.equals("admin")){
        var response =  productRepository.save(productMapper.convertRequestToProduct(dto)
        );
        return productMapper.productResponseDTO(response);
    }
       return null;
    }

    public List<ProductResponseDTO> getProductBySearch(String keyword){
       var result =  productRepository.searchProducts(keyword);
         Collections.shuffle(result);

               return result
                 .stream()
                 .map(productMapper::productResponseDTO)
                 .collect(Collectors.toList());


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
                    .map(x -> x.trim().replaceAll("^\"|\"$", ""))
                    .toArray(String[]::new);

            products = productRepository.getProductBasedOnBrandAndCategory(productInfo[0], productInfo[1]);
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
        Collections.shuffle(products);

        return products.stream()
                .map(productMapper::productResponseDTO)
                .collect(Collectors.toList());
//                .toList();
    }
}

