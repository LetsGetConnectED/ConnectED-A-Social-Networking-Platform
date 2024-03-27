/*package com.connected.connection.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connected.connection.Service.UserService;
import com.connected.connection.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;*/

/*
 * -----------------------------------------------------------------------------
 *									 Modified
 * -----------------------------------------------------------------------------
 */

package com.connected.connection.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.connected.connection.Service.UserService;

import com.connected.connection.model.User;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	// Sign-in end-point
	@PostMapping("/signin")
	public ResponseEntity<Optional<User>> signIn(@RequestParam String username, @RequestParam String password) {
		try {
			// Authenticate the user based on the provided user-name and password
			Optional<User> user = userService.getUserByUsernameAndPassword(username, password);
			if (user != null) {
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}



	/*
	 * -----------------------------------------------------------------------------
	 * 									Modified
	 * -----------------------------------------------------------------------------
	 */

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			List<User> users = userService.getAllUsers();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/getUserById/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		try {
			Optional<User> userOptional = userService.getUserById(id);
			return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/getUserByUsername/{username}")
	public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable String username) {
		try {
			Optional<User> user = userService.getUserByUsername(username);
			if (user != null) {
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
	    try {
	        if (user.getUsername() != null && user.getName() != null && user.getPassword() != null) {
	            User savedUser = userService.addUser(user);
	            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	        } else {
	            return ResponseEntity.badRequest().build();
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}


	/*
	 * @PostMapping("/addUser") public ResponseEntity<User> createUser(@RequestBody
	 * User user) { try { if (user.getUsername() != null) { User savedUser =
	 * userService.addUser(user); return
	 * ResponseEntity.status(HttpStatus.CREATED).body(savedUser); } else { return
	 * ResponseEntity.badRequest().build(); } } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
	 */

	@PutMapping("/updateUserById/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User user) {
		try {
			User updatedUser = userService.updateUserById(id, user);
			if (updatedUser != null) {
				return ResponseEntity.ok(updatedUser);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/updateUserByUsername/{username}")
	public ResponseEntity<User> updateUserByUsername(@PathVariable String username, @RequestBody User user) {
		try {
			User updatedUser = userService.updateUserByUsername(username, user);
			if (updatedUser != null) {
				return ResponseEntity.ok(updatedUser);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/deleteUserById/{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
		try {
			userService.deleteUserById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/*
	 * @DeleteMapping("/deleteUserByUsername/{username}") public
	 * ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) {
	 * try { userService.deleteUserByUsername(username); return
	 * ResponseEntity.noContent().build(); } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
	 */
}

/*-----------------------------------------------------------------------------------*/
/*
 * package com.connected.connection.Controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.*;
 * 
 * import com.connected.connection.Repository.UserRepository; import
 * com.connected.connection.model.User;
 * 
 * import java.util.List; import java.util.Optional;
 * 
 * 
 * 
 * @RestController
 * 
 * @RequestMapping("/users") public class UserController {
 * 
 * @Autowired private UserRepository userRepository;
 * 
 * @GetMapping("/getAllUsers") public ResponseEntity<List<User>> getAllUsers() {
 * try { List<User> users = userRepository.findAll(); return
 * ResponseEntity.ok(users); } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
 * 
 * @GetMapping("/getUserById/{id}") public ResponseEntity<User>
 * getUserById(@PathVariable Long id) { try { Optional<User> userOptional =
 * userRepository.findById(id); return userOptional.map(ResponseEntity::ok)
 * .orElseGet(() -> ResponseEntity.notFound().build()); } catch (Exception e) {
 * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
 * 
 * @GetMapping("/getUserByUsername/{username}") public ResponseEntity<User>
 * getUserByUsername(@PathVariable String username) { try { User user =
 * userRepository.findByUsername(username); if (user != null) { return
 * ResponseEntity.ok(user); } else { return ResponseEntity.notFound().build(); }
 * } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
 * 
 * @PostMapping("/addUser") public ResponseEntity<User> createUser(@RequestBody
 * User user) { try { if (user.getUsername() != null) { User savedUser =
 * userRepository.save(user); return
 * ResponseEntity.status(HttpStatus.CREATED).body(savedUser); } else { return
 * ResponseEntity.badRequest().build(); } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
 * 
 * @PutMapping("/updateUserById/{id}") public ResponseEntity<User>
 * updateUserById(@PathVariable Long id, @RequestBody User user) { try {
 * Optional<User> existingUserOptional = userRepository.findById(id); if
 * (existingUserOptional.isPresent()) { user.setId(id); User updatedUser =
 * userRepository.save(user); return ResponseEntity.ok(updatedUser); } else {
 * return ResponseEntity.notFound().build(); } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
 * 
 * @PutMapping("/updateUserByUsername/{username}") public ResponseEntity<User>
 * updateUserByUsername(@PathVariable String username, @RequestBody User user) {
 * try { User existingUser = userRepository.findByUsername(username); if
 * (existingUser != null) { // Update user properties
 * existingUser.setName(user.getName()); // Example: Updating name // Save
 * updated user User updatedUser = userRepository.save(existingUser); return
 * ResponseEntity.ok(updatedUser); } else { return
 * ResponseEntity.notFound().build(); } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
 * 
 * @DeleteMapping("/deleteUserById/{id}") public ResponseEntity<Void>
 * deleteUserById(@PathVariable Long id) { try { Optional<User>
 * existingUserOptional = userRepository.findById(id); if
 * (existingUserOptional.isPresent()) { userRepository.deleteById(id); return
 * ResponseEntity.noContent().build(); } else { return
 * ResponseEntity.notFound().build(); } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
 * 
 * @DeleteMapping("/deleteUserByUsername/{username}") public
 * ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) {
 * try { User existingUser = userRepository.findByUsername(username); if
 * (existingUser != null) { userRepository.delete(existingUser); return
 * ResponseEntity.noContent().build(); } else { return
 * ResponseEntity.notFound().build(); } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } } }
 */