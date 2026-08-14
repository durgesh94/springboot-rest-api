package com.learning.springboot.modules.address.controller;

import com.learning.springboot.modules.address.dto.AddressRequestDto;
import com.learning.springboot.modules.address.dto.AddressResponseDto;
import com.learning.springboot.modules.address.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponseDto> createAddress(
            @Valid @RequestBody AddressRequestDto addressRequest
    ) {
        AddressResponseDto addressResponse = addressService.createAddress(addressRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressResponse);
    }

    @GetMapping
    public ResponseEntity<List<AddressResponseDto>> getAllAddresses() {
        List<AddressResponseDto> addressResponseList = addressService.getAllAddresses();
        return ResponseEntity.ok(addressResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> getAddressById(@PathVariable Long id) {
        AddressResponseDto addressResponse = addressService.getAddressById(id);
        return ResponseEntity.ok(addressResponse);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AddressResponseDto>> getAddressesByUserId(@PathVariable Long userId) {

        List<AddressResponseDto> response =
                addressService.getAddressesByUserId(userId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDto> updateAddress(
            @PathVariable Long id,
            @Valid @RequestBody AddressRequestDto addressRequest) {

        AddressResponseDto addressResponse =
                addressService.updateAddress(id, addressRequest);
        return ResponseEntity.ok(addressResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddressById(id);
        return ResponseEntity.noContent().build();
    }
}
