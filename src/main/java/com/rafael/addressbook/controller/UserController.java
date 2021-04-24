package com.rafael.addressbook.controller;

import com.rafael.addressbook.model.User;
import com.rafael.addressbook.response.Response;
import com.rafael.addressbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Response<List<User>>> listUsers() {
        Response<List<User>> response = new Response<List<User>>();
        List<User> users = this.userService.listAll();

        response.setData(users);

        return ResponseEntity.ok(response);
    }

@GetMapping("{id}")
public ResponseEntity<Response<User>> showUser(@PathVariable Long id) {
    Response<User> response = new Response<User>();
    Optional<User> user = this.userService.getById(id);

    if(user.isEmpty()) {
        response.getErrors().add("User not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    response.setData(user.get());

    return ResponseEntity.ok(response);
}

@PostMapping
public ResponseEntity<Response<User>> createUser(@RequestBody @Valid User user, BindingResult result) {
    Response<User> response = new Response<User>();

    if(result.hasErrors()) {
        result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(response);
    }

    User newUser = this.userService.save(user);
    response.setData(newUser);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}
}
