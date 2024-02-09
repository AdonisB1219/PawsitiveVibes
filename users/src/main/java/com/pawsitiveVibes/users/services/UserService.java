package com.pawsitiveVibes.users.services;

import com.pawsitiveVibes.users.models.UpdateUser;
import com.pawsitiveVibes.users.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public ResponseEntity<List<User>> getAllUsers();


    public ResponseEntity<User> getUser(Long userId);

    public ResponseEntity<User> addUser(User user);

    ResponseEntity<String> deleteUser(Long userId);

    ResponseEntity<User> updateUser(Long userId, UpdateUser updateUser);
}

