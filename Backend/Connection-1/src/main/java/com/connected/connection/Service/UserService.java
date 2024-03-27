package com.connected.connection.Service;

import com.connected.connection.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUsername(String username);

    User addUser(User user);

    User updateUserById(Long id, User user);

    User updateUserByUsername(String username, User user);

    void deleteUserById(Long id);

    //void deleteUserByUsername(String username);

    Optional<User> getUserByUsernameAndPassword(String username, String password);
}


/*
 * package com.connected.connection.Service;
 * 
 * import com.connected.connection.model.User;
 * 
 * import java.util.List; import java.util.Optional;
 * 
 * public interface UserService {
 * 
 * List<User> getAllUsers();
 * 
 * Optional<User> getUserById(Long id);
 * 
 * User getUserByUsername(String username);
 * 
 * User addUser(User user);
 * 
 * User updateUserById(Long id, User user);
 * 
 * User updateUserByUsername(String username, User user);
 * 
 * void deleteUserById(Long id);
 * 
 * void deleteUserByUsername(String username);
 * 
 * User getUserByUsernameAndPassword(String username, String password); }
 */



/*
 * package com.connected.connection.Service;
 * 
 * import com.connected.connection.Repository.UserRepository; import
 * com.connected.connection.model.User; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import java.util.List; import java.util.Optional;
 * 
 * @Service public class UserService {
 * 
 * @Autowired private UserRepository userRepository;
 * 
 * public List<User> getAllUsers() { return userRepository.findAll(); }
 * 
 * public Optional<User> getUserById(Long id) { return
 * userRepository.findById(id); }
 * 
 * public User getUserByUsername(String username) { return
 * userRepository.findByUsername(username); }
 * 
 * public User addUser(User user) { return userRepository.save(user); }
 * 
 * public User updateUserById(Long id, User user) { user.setId(id); return
 * userRepository.save(user); }
 * 
 * public User updateUserByUsername(String username, User user) { User
 * existingUser = userRepository.findByUsername(username); if (existingUser !=
 * null) { existingUser.setName(user.getName());
 * existingUser.setPassword(user.getPassword()); return
 * userRepository.save(existingUser); } return null; }
 * 
 * public void deleteUserById(Long id) { userRepository.deleteById(id); }
 * 
 * public void deleteUserByUsername(String username) { User existingUser =
 * userRepository.findByUsername(username); if (existingUser != null) {
 * userRepository.delete(existingUser); } } }
 */