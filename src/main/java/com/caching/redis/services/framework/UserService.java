package com.caching.redis.services.framework;

import com.caching.redis.exception.UserNotFoundException;
import com.caching.redis.models.Users;

import java.util.List;

public interface UserService {

    Users getUserById(Long id) throws UserNotFoundException;
    void addUser(Users user);
    void updateUser(Users user);
    void deleteUser(Long id);
    List<Users> getAllUsers();


}
