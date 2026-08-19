package com.learning.springboot.modules.address.service;

import com.learning.springboot.modules.address.dto.AddressRequestDto;
import com.learning.springboot.modules.address.dto.AddressResponseDto;
import java.util.List;

public interface AddressService {

  AddressResponseDto createAddress(AddressRequestDto addressRequest);

  AddressResponseDto getAddressById(Long id);

  List<AddressResponseDto> getAllAddresses();

  List<AddressResponseDto> getAddressesByUserId(Long id);

  AddressResponseDto updateAddress(Long id, AddressRequestDto addressRequest);

  Void deleteAddressById(Long id);
}
