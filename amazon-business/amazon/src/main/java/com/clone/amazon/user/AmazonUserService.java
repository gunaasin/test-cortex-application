package com.clone.amazon.user;

import com.clone.amazon.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class AmazonUserService {

    private final AmazonUserRepository amazonUserRepository;
    private final AmazonUserMapper amazonUserMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Transactional
    public AmazonUserResponseDTO saveUser(AmazonUserRequestDTO dto) {
        try{
        var response = amazonUserRepository.save(amazonUserMapper.convertToUser(dto));
        return amazonUserMapper.convertToResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object verifyUser(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userLoginDto.mailId(),
                                userLoginDto.password()
                        ));
        var role = amazonUserRepository.findByEmail(userLoginDto.mailId()).getRole();
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(userLoginDto.mailId() , List.of(role));
        }
        return ("fail");
    }
}
