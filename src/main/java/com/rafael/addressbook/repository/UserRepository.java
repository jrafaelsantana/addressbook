package com.rafael.addressbook.repository;

import com.rafael.addressbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
