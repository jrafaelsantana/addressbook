package com.rafael.addressbook.service.implementation;

import com.rafael.addressbook.model.User;
import com.rafael.addressbook.repository.UserRepository;
import com.rafael.addressbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<User> listAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return this.userRepository.findById(id);
    }
}
