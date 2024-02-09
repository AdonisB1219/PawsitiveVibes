package com.pawsitiveVibes.users.exceptions;

public class UserNotFoundException extends RuntimeException{ 	public UserNotFoundException(Long userId) {
    super("User not found with ID: " + userId);
}
}
