package com.clone.amazon.user;

import com.clone.amazon.security.JwtService;
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

    public AmazonUserResponseDTO saveUser(AmazonUserRequestDTO dto) {
        var response = amazonUserRepository.save(amazonUserMapper.convertToUser(dto));
        return amazonUserMapper.convertToResponse(response);
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
