package com.ser322deliverable4.service.user;

import com.ser322deliverable4.model.User;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


public interface IUserService {

    User addUser(User user);

    List<User> getAllUsers();

    User getUserById(Long userId);

    int editUser(User editedUser);

    int deleteUser(Long userId) throws SQLIntegrityConstraintViolationException;
}
