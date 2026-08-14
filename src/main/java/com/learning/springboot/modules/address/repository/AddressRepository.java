package com.learning.springboot.modules.address.repository;

import com.learning.springboot.modules.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
