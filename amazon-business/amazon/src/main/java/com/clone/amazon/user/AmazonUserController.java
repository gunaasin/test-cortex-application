package com.clone.amazon.user;

import com.clone.amazon.security.JwtBlockListService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class AmazonUserController {
    private final AmazonUserService userService;
    private final JwtBlockListService jwtBlacklistService;

    public AmazonUserController(AmazonUserService userService , JwtBlockListService jwtBlacklistService){
        this.userService = userService;
        this.jwtBlacklistService=jwtBlacklistService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> saveUser(@RequestBody AmazonUserRequestDTO dto) {
        try {
//            AmazonUserResponseDTO savedUser = userService.saveUser(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error saving user: " + e.getMessage());
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto){
        try {
            String token = (String) userService.verifyUser(userLoginDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(Map.of("message", token));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Incorrect emailId or password");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogOutDTO logOutDTO) {
        jwtBlacklistService.blacklistToken(logOutDTO.token());
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(Map.of("message", "Logged out successfully"));
    }

}
