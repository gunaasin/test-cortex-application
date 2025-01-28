package com.clone.amazon.address;

public class AddressMapper {


    public Address convertToAddress(AddressRequestDTO addressRequestDTO){
        return Address.builder()
                .fullName(addressRequestDTO.name())
                .phoneNumber(addressRequestDTO.mobile())
                .streetAddress(addressRequestDTO.address())
                .city(addressRequestDTO.city())
                .state(addressRequestDTO.state())
                .pinCode(addressRequestDTO.pinCode())
                .build();
    }


}
