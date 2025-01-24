package com.clone.amazon.user;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AmazonUserMapper {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AmazonUserMapper(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
    public AmazonUser convertToUser(AmazonUserRequestDTO dto){
        AmazonUser amazonUser = AmazonUser.builder()
                .name(dto.name())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .password(dto.password())
                .role("user")
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
