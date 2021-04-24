package com.rafael.addressbook.repository;

import com.rafael.addressbook.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
