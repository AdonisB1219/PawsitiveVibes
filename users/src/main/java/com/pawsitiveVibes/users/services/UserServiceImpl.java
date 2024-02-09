package com.pawsitiveVibes.users.services;

import com.pawsitiveVibes.users.exceptions.NotValidFieldException;
import com.pawsitiveVibes.users.exceptions.UserNotFoundException;
import com.pawsitiveVibes.users.models.UpdateUser;
import com.pawsitiveVibes.users.models.User;
import com.pawsitiveVibes.users.repositories.UserRepository;
import com.pawsitiveVibes.users.utils.ValidationExceptionMessage;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    ValidationExceptionMessage validationExceptionMessage;


    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user = userRepository.findAll();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<User> getUser(Long userId) {
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) throw new UserNotFoundException(userId);
        return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
    }

    public ResponseEntity<User> addUser(User user) {
        User userSaved;
        try {
            userSaved = userRepository.save(user);
        } catch (ConstraintViolationException e) {
            String errorMessage = validationExceptionMessage.makeMessage(e.getMessage());
            throw new NotValidFieldException(errorMessage);
        }
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteUser(Long userId) {
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) throw new UserNotFoundException(userId);
        userRepository.deleteById(userId);
        return new ResponseEntity<>("El usuario con id: [ " + userId + " ] ha sido borrado exitosamente.", HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<User> updateUser(Long userId, UpdateUser upUser) {
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) throw new UserNotFoundException(userId);
        User user = optUser.get();
        if (upUser.getEmail() != null) user.setEmail(upUser.getEmail());
        if (upUser.getUsername() != null) user.setUsername(upUser.getUsername());
        if (upUser.getPassword() != null) user.setPassword(upUser.getPassword());
        if (upUser.getImage() != null) user.setImage(upUser.getImage());
        User userUpdated = new User();
        try{
            userUpdated = userRepository.save(user);
        } catch (TransactionSystemException e) {
            Throwable rootCause = e.getRootCause();
            if (rootCause instanceof ConstraintViolationException) {
                String errorMessage = validationExceptionMessage.makeMessage(rootCause.getMessage());
                throw new NotValidFieldException(errorMessage);
            }
        }
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }


}
