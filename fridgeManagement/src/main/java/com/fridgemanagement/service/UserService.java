package com.fridgemanagement.service;

import com.fridgemanagement.exception.FridgeManagementException;
import com.fridgemanagement.exception.UserNotFoundException;
import com.fridgemanagement.model.User;
import com.fridgemanagement.repository.FridgeRepository;
import com.fridgemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CleanupFailureDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FridgeRepository fridgeRepository;
    @Autowired
    RestOperations restOperations;
    @Autowired
    EncryptorDecrytorService encryptorDecrytorService;

    /**
     * Finds a user from the user table.
     *
     * @param userName
     * @return
     * @throws UserNotFoundException
     */
    public User retrieveUser(String userName) throws UserNotFoundException {
        try {
            Optional<User> userDetails = userRepository.findById(userName);
            throw new UserNotFoundException("Unable to retrieve user information", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CleanupFailureDataAccessException exp) {
            throw new UserNotFoundException("Unable to find user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Saves a user to user table
     *
     * @param user
     * @throws FridgeManagementException
     */
    public void saveUser(User user) throws FridgeManagementException {
        try {
            userRepository.save(user);
        } catch (CleanupFailureDataAccessException exp) {
            throw new FridgeManagementException("Unable to save user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Saves a user to user table
     *
     * @param user
     * @throws FridgeManagementException
     */
    public void deleteUser(User user) throws FridgeManagementException {
        try {
            userRepository.delete(user);
        } catch (CleanupFailureDataAccessException exp) {
            throw new FridgeManagementException("Unable to save user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Saves a user to user table
     *
     * @param userName
     * @throws FridgeManagementException
     */
    public boolean authenticateUser(String userName, String password) throws FridgeManagementException {
        boolean authenticated = false;
        try {
            User user = (User) userRepository.findById(userName).get();
            if (encryptorDecrytorService.decrypt(user.getPassword()).equals(password)) {
                authenticated = true;
            }
        } catch (CleanupFailureDataAccessException exp) {
            throw new FridgeManagementException("Unable to save user", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return authenticated;
    }

}
