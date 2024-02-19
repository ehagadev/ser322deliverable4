package com.ser322deliverable4.service.user;

import com.ser322deliverable4.model.User;
import com.ser322deliverable4.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        userRepository.insertNewUser(
                user.getEmail(), user.getFirstName(), user.getLastName()
        );
        logger.info("SUCCESSFULLY ADDED NEW USER {} INTO THE DB", user.getEmail());
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        logger.info("FETCHING ALL USERS");
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        logger.info("ATTEMPTING TO FIND USER BY ID: {}", userId);
        Optional<User> byId = userRepository.findUserById(userId);

        if (byId.isPresent()) {
            User user = byId.get();
            logger.info("FOUND USER BY ID: {}", user.getEmail());
            return user;
        } else {
            logger.error("USER NOT FOUND BY ID: {}", userId);
            return null;
        }
    }

    @Override
    public int editUser(User editedUser) {
        logger.info("AT BEGINNING OF EDIT USER");
        Optional<User> byId = userRepository.findUserById(editedUser.getId());
        if (byId.isPresent()) {
            User user = byId.get();
            user.setFirstName(editedUser.getFirstName());
            user.setLastName(editedUser.getLastName());
            int saved = userRepository.updateUserNames(user.getId(), user.getFirstName(), user.getLastName());
            logger.info("SUCCESSFULLY EDITED USER BY ID: {}", editedUser.getId());
            return saved;
        } else {
            logger.error("UNABLE TO FIND USER BY ID: {}", editedUser.getId());
            return 0;
        }
    }

    @Override
    public int deleteUser(Long userId) {
        Optional<User> byId = userRepository.findUserById(userId);
        if (byId.isPresent()) {
            User user = byId.get();
            int response = userRepository.deleteUserById(user.getId());
            logger.info("SUCCESSFULLY DELETED USER BY ID: {}", user.getId());
            return response;
        } else {
            logger.error("UNABLE TO FIND USER BY ID: {}", userId);
            return 0;
        }
    }
}
