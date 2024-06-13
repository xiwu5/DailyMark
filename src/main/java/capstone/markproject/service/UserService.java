package capstone.markproject.service;

import capstone.markproject.entity.User;
import capstone.markproject.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
    UserService encapsulates the business logic for managing User entities within the application,
    offering methods for CRUD operations and user registration,
    with added security measures for password encryption.
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void registerUser(User user) {
        logger.warn("User service: register user： " + user.getUsername());
        // Utilizes PasswordEncoder to securely encode passwords before storing them to enhance security.
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

//    public User authenticate(String username, String password) {
//        User user = findByUsername(username);
//        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//            return user;
//        }
//        return null;
//    }


}
