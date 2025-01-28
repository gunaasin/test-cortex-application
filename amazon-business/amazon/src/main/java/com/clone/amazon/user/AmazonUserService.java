package com.clone.amazon.user;

import com.clone.amazon.security.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AmazonUserService {

    private final AmazonUserRepository amazonUserRepository;
    private final AmazonUserMapper amazonUserMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AmazonUserService(
            AmazonUserRepository amazonUserRepository,
            AmazonUserMapper amazonUserMapper,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ){
        this.amazonUserRepository=amazonUserRepository;
        this.authenticationManager=authenticationManager;
        this.amazonUserMapper = amazonUserMapper;
        this.jwtService=jwtService;
    }

    @Transactional
    public AmazonUserResponseDTO saveUser(AmazonUserRequestDTO dto) {
        System.out.println(dto +" in the amazon user save");
        try{
        var response = amazonUserRepository.save(amazonUserMapper.convertToUser(dto));
        System.out.println(dto +" 2 in the amazon user save");
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
//        System.out.println(authentication.isAuthenticated());
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(userLoginDto.mailId());
        }
        return ("fail");
    }
}
