package com.clone.amazon.user;
import com.clone.amazon.address.Address;
import com.clone.amazon.cart.Cart;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AmazonUserMapper {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AmazonUserMapper(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
    Address defaultAddress = Address.builder()
            .streetAddress("Default Street")
            .city("Default City")
            .state("Default State")
            .pinCode("000000")
            .fullName("delivery name")
            .phoneNumber("phone number")
            .build();

    public AmazonUser convertToUser(AmazonUserRequestDTO dto){
        AmazonUser amazonUser = AmazonUser.builder()
                .name(dto.name())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .password(dto.password())
                .role("user")
                .cart(new Cart())
                .address(defaultAddress)
                .build();
        amazonUser.setPassword(bCryptPasswordEncoder.encode(amazonUser.getPassword()));
        return amazonUser;
    }

    public AmazonUserResponseDTO convertToResponse(AmazonUser amazonUser){
        return AmazonUserResponseDTO.builder()
                .name(amazonUser.getName())
                .build();
    }
}
