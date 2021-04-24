package com.rafael.addressbook.service.implementation;

import com.rafael.addressbook.model.Address;
import com.rafael.addressbook.repository.AddressRepository;
import com.rafael.addressbook.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImplementation implements AddressService {

    final AddressRepository addressRepository;

    public AddressServiceImplementation(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {
        return this.addressRepository.save(address);
    }
}
