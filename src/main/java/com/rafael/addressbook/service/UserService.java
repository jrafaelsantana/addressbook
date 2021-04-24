package com.rafael.addressbook.service;

import com.rafael.addressbook.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User usuario);
    List<User> listAll();
    Optional<User> getById(Long id);
}
