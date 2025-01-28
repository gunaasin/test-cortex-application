package com.clone.amazon.user;

import com.clone.amazon.security.JwtBlockListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            System.out.println(dto);
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
                    .body(Map.of("token", token));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Incorrect emailId or password");
        }
    }

    @PostMapping("/getKey")
    public ResponseEntity<?> start(@RequestBody String key){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(Map.of("message", "access granted"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogOutDTO logOutDTO) {
        System.out.println(logOutDTO);
        jwtBlacklistService.blacklistToken(logOutDTO.token());
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(Map.of("message", "Logged out successfully"));
    }

}
