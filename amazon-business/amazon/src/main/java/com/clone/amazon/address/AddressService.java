package com.clone.amazon.address;

import com.clone.amazon.security.JwtService;
import com.clone.amazon.user.AmazonUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

public class AddressService {
    private final AddressRepo addressRepo;
    private final JwtService jwtService;
    private final AmazonUserRepository amazonUserRepository;

    public AddressService(AddressRepo addressRepo , JwtService jwtService , AmazonUserRepository amazonUserRepository){
        this.addressRepo=addressRepo;
        this.jwtService=jwtService;
        this.amazonUserRepository=amazonUserRepository;
    }

    public Object addAddress(AddressRequestDTO addressRequestDTO) {
        try {
            if (jwtService.validateToken(addressRequestDTO.token(), addressRequestDTO.email())) {
                var email = jwtService.extractMailId(addressRequestDTO.token());
                var address = amazonUserRepository.findByEmail(email).getAddress();
                   address.setCity(addressRequestDTO.city());
                   address.setStreetAddress(addressRequestDTO.address());
                   address.setFullName(addressRequestDTO.name());
                   address.setPhoneNumber(addressRequestDTO.mobile());
                   address.setType(addressRequestDTO.type());
                   address.setPinCode(addressRequestDTO.pinCode());
                   address.setState(addressRequestDTO.state());
                   addressRepo.save(address);

                   var response =  addressRepo.save(address);
                   return AddressResponseDTO.builder()
                           .email(addressRequestDTO.email())
                           .name(response.getFullName())
                           .mobile(response.getPhoneNumber())
                           .pinCode(response.getPinCode())
                           .address(response.getStreetAddress())
                           .city(response.getCity())
                           .state(response.getState())
                           .type(response.getType())
                           .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "wrong credential";
    }

    public Object getAddress(String token , String email) {
        try {
            if (jwtService.validateToken(token, email)) {
                var encEmail = jwtService.extractMailId(token);
                var response = amazonUserRepository.findByEmail(encEmail).getAddress();
                return AddressResponseDTO.builder()
                        .email(encEmail)
                        .name(response.getFullName())
                        .mobile(response.getPhoneNumber())
                        .pinCode(response.getPinCode())
                        .address(response.getStreetAddress())
                        .city(response.getCity())
                        .state(response.getState())
                        .type(response.getType())
                        .build();
            }
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("exception in address service");

        }
        return AddressResponseDTO.builder()
                .email("Enter email")
                .name("Enter name")
                .mobile("Enter mobile Number")
                .pinCode("Enter pin code eg: 543 564")
                .address("Enter Area and Street")
                .city("Enter city")
                .state("Enter state")
                .type("type of address eg: Home Or Work")
                .build();

    }
}
