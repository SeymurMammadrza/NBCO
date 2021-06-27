package com.app.nbco.user.service;

import com.app.nbco.user.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAll();

    User findById(long id);

    void deleteById(long id);

    void delete (User user);
}
