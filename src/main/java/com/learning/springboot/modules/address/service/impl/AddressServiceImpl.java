package com.learning.springboot.modules.address.service.impl;

import com.learning.springboot.exception.ResourceNotFoundException;
import com.learning.springboot.modules.address.dto.AddressRequestDto;
import com.learning.springboot.modules.address.dto.AddressResponseDto;
import com.learning.springboot.modules.address.entity.Address;
import com.learning.springboot.modules.address.mapper.AddressMapper;
import com.learning.springboot.modules.address.repository.AddressRepository;
import com.learning.springboot.modules.address.service.AddressService;
import com.learning.springboot.modules.user.entity.User;
import com.learning.springboot.modules.user.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

  private final AddressRepository addressRepository;
  private final UserRepository userRepository;
  
  private static final String ADDRESS_STRING = "Address";
  private static final String USER_STRING = "User";

  // Constructor injection
  public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
    this.addressRepository = addressRepository;
    this.userRepository = userRepository;
  }

  @Override
  public AddressResponseDto createAddress(AddressRequestDto addressRequest) {
    // Step 1: Find user
    Long userId = addressRequest.getUserId();
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException(USER_STRING, userId));
    // Step 2: Convert dto to entity
    Address address = AddressMapper.toEntity(addressRequest, user);
    // Step 3: Save address
    Address savedAddress = addressRepository.save(address);
    // Step 4: Convert entity to dto
    return AddressMapper.toDto(savedAddress);
  }

  @Override
  public AddressResponseDto getAddressById(Long id) {
    Address address =
        addressRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ADDRESS_STRING, id));
    return AddressMapper.toDto(address);
  }

  @Override
  public List<AddressResponseDto> getAllAddresses() {
    List<Address> addresses = addressRepository.findAll();
    return addresses.stream().map(AddressMapper::toDto).toList();
  }

  @Override
  public List<AddressResponseDto> getAddressesByUserId(Long userId) {
    // Verify user exist
    userRepository
        .findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException(USER_STRING, userId));

    List<Address> addresses = addressRepository.findByUserId(userId);

    return addresses.stream().map(AddressMapper::toDto).toList();
  }

  @Override
  public AddressResponseDto updateAddress(Long id, AddressRequestDto addressRequest) {
    // Step 1: Find existing address
    Address existingAddress =
        addressRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ADDRESS_STRING, id));

    // Step 2: Find user
    Long userId = addressRequest.getUserId();
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException(USER_STRING, userId));

    // Step 3: Update existing entity
    AddressMapper.updateEntity(existingAddress, addressRequest, user);

    // Step 4: Save updated address
    Address updatedAddress = addressRepository.save(existingAddress);

    // Step 5: Convert entity to dto
    return AddressMapper.toDto(updatedAddress);
  }

  @Override
  public Void deleteAddressById(Long id) {
    Address address =
        addressRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ADDRESS_STRING, id));

    addressRepository.delete(address);
    return null;
  }
}
