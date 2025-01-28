package com.clone.amazon.address;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

   public AddressController(AddressService addressService){
        this.addressService=addressService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> addAddress(
            @RequestBody AddressRequestDTO addressRequestDTO
    ){
        try{
            return ResponseEntity.ok().body(addressService.addAddress(addressRequestDTO));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "wrong credential"));
        }
    }


    @GetMapping("/get")
    public ResponseEntity<?> getAddress(
            @RequestParam String token,
            @RequestParam String email
    ) {
        System.out.println("hello world");
        try {

            return ResponseEntity.ok().body(addressService.getAddress(token, email));
        } catch (Exception e) {
            // Returning an AddressResponseDTO with error guidance
            return ResponseEntity.badRequest().body(
                    AddressResponseDTO.builder()
                            .email("Enter email")
                            .name("Enter name")
                            .mobile("Enter mobile number")
                            .pinCode("Enter pin code eg: 543 564")
                            .address("Enter area and street")
                            .city("Enter city")
                            .state("Enter state")
                            .type("Type of address, e.g., Home or Work")
                            .build()
            );
        }
    }


}
