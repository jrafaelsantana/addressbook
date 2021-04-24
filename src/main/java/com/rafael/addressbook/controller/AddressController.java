package com.rafael.addressbook.controller;

import com.rafael.addressbook.model.Address;
import com.rafael.addressbook.model.User;
import com.rafael.addressbook.response.Response;
import com.rafael.addressbook.service.AddressService;
import com.rafael.addressbook.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AddressController {
    final UserService userService;
    final AddressService addressService;

    public AddressController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

@PostMapping("/api/v1/users/{idUser}/addresses")
public ResponseEntity<Response<Address>> createAddress(@PathVariable Long idUser, @RequestBody @Valid Address address, BindingResult result) {
    Response<Address> response = new Response<Address>();

    Optional<User> user = this.userService.getById(idUser);
    if(user.isEmpty()) {
        result.addError(new ObjectError("user", "User not found"));
    } else {
        address.setUser(user.get());
    }

    if (result.hasErrors()) {
        result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(response);
    }

    Address newAddress = this.addressService.save(address);
    response.setData(newAddress);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}
}
