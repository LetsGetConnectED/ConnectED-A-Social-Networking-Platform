package com.connected.connection.Controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.connected.connection.Repository.FriendRequestRepository;
import com.connected.connection.Service.FriendRequestService;
import com.connected.connection.Service.UserService;
import com.connected.connection.exceptions.ResourceNotFoundException;
import com.connected.connection.model.FriendRequest;
import com.connected.connection.model.RequestStatus;
import com.connected.connection.model.User;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendRequestService friendRequestService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private FriendRequestRepository friendRequestRepository;
	/*
	 * @Autowired private GlobalExceptionHandler globalExceptionHandler;
	 */

    
 // Fetch friend requests for a specific user
    @GetMapping("/{username}/pending-requests")
    public ResponseEntity<?> getFriendRequests(@PathVariable String username) {
        try {
            Optional<User> userOptional = userService.getUserByUsername(username);
            User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
            List<FriendRequest> friendRequests = friendRequestService.getPendingFriendRequestsForUser(user);
            if (friendRequests.isEmpty()) {
                return ResponseEntity.ok("No new connection requests for the username: " + username);
            } else {
                return ResponseEntity.ok(friendRequests);
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    @GetMapping("/{username}/accepted-requests")
    public ResponseEntity<?> getAcceptedRequests(@PathVariable String username) {
        try {
            Optional<User> userOptional = userService.getUserByUsername(username);
            User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
            List<FriendRequest> acceptedRequests = friendRequestService.getAcceptedFriendRequestsForUser(user);
            if (acceptedRequests.isEmpty()) {
                return ResponseEntity.ok("No accepted connection requests for " + username);
            } else {
                return ResponseEntity.ok(acceptedRequests);
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

	/*
	 * @PostMapping("/request/send") // senderUsername and receiverUsername public
	 * ResponseEntity<?> sendFriendRequest(@RequestParam String senderUsername,
	 * 
	 * @RequestParam String receiverUsername) { try { Optional<User> senderOptional
	 * = userService.getUserByUsername(senderUsername); Optional<User>
	 * receiverOptional = userService.getUserByUsername(receiverUsername);
	 * 
	 * User sender = senderOptional.orElseThrow(() -> new
	 * ResourceNotFoundException("User", "username", senderUsername)); User receiver
	 * = receiverOptional.orElseThrow(() -> new ResourceNotFoundException("User",
	 * "username", receiverUsername));
	 * 
	 * friendRequestService.sendFriendRequest(sender, receiver,
	 * RequestStatus.PENDING); return
	 * ResponseEntity.ok().body("Friend request sent from " + sender.getUsername() +
	 * " to " + receiver.getUsername()); } catch (ResourceNotFoundException e) {
	 * return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); }
	 * catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
	 * body("Failed to send friend request. Please try again later."); } }
	 */
    
    @PostMapping("/request/send") // senderUsername and receiverUsername
    public ResponseEntity<?> sendFriendRequest(@RequestParam String senderUsername,
            @RequestParam String receiverUsername) {
        try {
            Optional<User> senderOptional = userService.getUserByUsername(senderUsername);
            Optional<User> receiverOptional = userService.getUserByUsername(receiverUsername);

            User sender = senderOptional.orElseThrow(() -> new ResourceNotFoundException("User", "username", senderUsername));
            User receiver = receiverOptional.orElseThrow(() -> new ResourceNotFoundException("User", "username", receiverUsername));

            // Check if there is an existing friend request between sender and receiver
            Optional<FriendRequest> existingRequestOptional = friendRequestService.getFriendRequest(sender, receiver);
            if (existingRequestOptional.isPresent()) {
                FriendRequest existingRequest = existingRequestOptional.get();
                if (existingRequest.getStatus() == RequestStatus.PENDING || existingRequest.getStatus() == RequestStatus.ACCEPTED) {
                    // If request has pending or accepted status, return a message
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Friend request already exists between " + sender.getUsername() + " and " + receiver.getUsername());
                }
            }

            // If no existing request or existing request has status rejected, create a new request
            friendRequestService.sendFriendRequest(sender, receiver, RequestStatus.PENDING);
            return ResponseEntity.ok().body("Friend request sent from " + sender.getUsername() + " to " + receiver.getUsername());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send friend request. Please try again later.");
        }}

    @DeleteMapping("/request/cancel") // senderUsername & receiverUsername
    public ResponseEntity<?> cancelFriendRequest(@RequestParam String senderUsername,
            @RequestParam String receiverUsername) {
        try {
            Optional<User> senderOptional = userService.getUserByUsername(senderUsername);
            Optional<User> receiverOptional = userService.getUserByUsername(receiverUsername);

            User sender = senderOptional.orElseThrow(() -> new ResourceNotFoundException("User", "username", senderUsername));
            User receiver = receiverOptional.orElseThrow(() -> new ResourceNotFoundException("User", "username", receiverUsername));

            friendRequestService.cancelFriendRequest(sender, receiver);
            return ResponseEntity.ok().body("Friend request cancelled by " + sender.getUsername() + " for " + receiver.getUsername());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to cancel friend request. Please try again later.");
        }
    }

    @PutMapping("/request/accept") // senderUsername & receiverUsername
    public ResponseEntity<?> acceptFriendRequest(@RequestParam String senderUsername,
            @RequestParam String receiverUsername) {
        try {
            Optional<User> senderOptional = userService.getUserByUsername(senderUsername);
            Optional<User> receiverOptional = userService.getUserByUsername(receiverUsername);

            User sender = senderOptional.orElseThrow(() -> new ResourceNotFoundException("User", "username", senderUsername));
            User receiver = receiverOptional.orElseThrow(() -> new ResourceNotFoundException("User", "username", receiverUsername));

            friendRequestService.acceptFriendRequest(sender, receiver);
            return ResponseEntity.ok().body(receiverUsername + " accepted the request of " + senderUsername);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to accept friend request. Please try again later.");
        }
    }

    @PutMapping("/request/reject") // senderUsername & receiverUsername
    public ResponseEntity<?> rejectFriendRequest(@RequestParam String senderUsername,
            @RequestParam String receiverUsername) {
        try {
            Optional<User> senderOptional = userService.getUserByUsername(senderUsername);
            Optional<User> receiverOptional = userService.getUserByUsername(receiverUsername);

            User sender = senderOptional.orElseThrow(() -> new ResourceNotFoundException("User", "username", senderUsername));
            User receiver = receiverOptional.orElseThrow(() -> new ResourceNotFoundException("User", "username", receiverUsername));

            friendRequestService.rejectFriendRequest(sender, receiver);
            return ResponseEntity.ok().body(receiverUsername + " rejected the request of " + senderUsername);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to reject friend request. Please try again later.");
        }
    }
    

	/*
	 * // Fetch friend requests for a specific user
	 * 
	 * @GetMapping("/{username}/pending-requests") public ResponseEntity<?>
	 * getFriendRequests(@PathVariable String username) { try { Optional<User>
	 * userOptional = userService.getUserByUsername(username); User user =
	 * userOptional.orElseThrow( () ->
	 * globalExceptionHandler.handleResourceNotFoundException("User", "username",
	 * username)); List<FriendRequest> friendRequests =
	 * friendRequestService.getPendingFriendRequestsForUser(user); if
	 * (friendRequests.isEmpty()) { return
	 * ResponseEntity.ok("No new connection requests for the username: " +
	 * username); } else { return ResponseEntity.ok(friendRequests); } } catch
	 * (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
	 */

	/*
	 * // fetch all accepted request by the user
	 * 
	 * @GetMapping("/{username}/accepted-requests") public ResponseEntity<?>
	 * getAcceptedRequests(@PathVariable String username) { try { Optional<User>
	 * userOptional = userService.getUserByUsername(username); User user =
	 * userOptional.orElseThrow( () ->
	 * globalExceptionHandler.handleResourceNotFoundException("User", "username",
	 * username)); List<FriendRequest> acceptedRequests =
	 * friendRequestService.getAcceptedFriendRequestsForUser(user); if
	 * (acceptedRequests.isEmpty()) { return
	 * ResponseEntity.ok("No accepted connection requests for " + username); } else
	 * { return ResponseEntity.ok(acceptedRequests); } } catch (Exception e) {
	 * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
	 * 
	 * @PostMapping("/request/send") // senderUsername and receiverUsername public
	 * ResponseEntity<?> sendFriendRequest(@RequestParam String senderUsername,
	 * 
	 * @RequestParam String receiverUsername) { try { Optional<User> senderOptional
	 * = userService.getUserByUsername(senderUsername); Optional<User>
	 * receiverOptional = userService.getUserByUsername(receiverUsername);
	 * 
	 * User sender = senderOptional.orElseThrow( () ->
	 * globalExceptionHandler.handleResourceNotFoundException("User", "username",
	 * senderUsername)); User receiver = receiverOptional.orElseThrow( () ->
	 * globalExceptionHandler.handleResourceNotFoundException("User", "username",
	 * receiverUsername));
	 * 
	 * friendRequestService.sendFriendRequest(sender, receiver,
	 * RequestStatus.PENDING); return ResponseEntity.ok()
	 * .body("Friend request sent from " + sender.getUsername() + " to " +
	 * receiver.getUsername()); } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	 * .body("Failed to send friend request. Please try again later."); } }
	 * 
	 * @DeleteMapping("/request/cancel") // senderUsername & receiverUsername public
	 * ResponseEntity<?> cancelFriendRequest(@RequestParam String senderUsername,
	 * 
	 * @RequestParam String receiverUsername) { try { Optional<User> senderOptional
	 * = userService.getUserByUsername(senderUsername); Optional<User>
	 * receiverOptional = userService.getUserByUsername(receiverUsername);
	 * 
	 * User sender = senderOptional.orElseThrow( () ->
	 * globalExceptionHandler.handleResourceNotFoundException("User", "username",
	 * senderUsername)); User receiver = receiverOptional.orElseThrow( () ->
	 * globalExceptionHandler.handleResourceNotFoundException("User", "username",
	 * receiverUsername));
	 * 
	 * friendRequestService.cancelFriendRequest(sender, receiver); return
	 * ResponseEntity.ok().body("Friend request cancelled by " +
	 * sender.getUsername() + " for " + receiver.getUsername()); } catch (Exception
	 * e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	 * .body("Failed to cancel friend request. Please try again later."); } }
	 * 
	 * @PutMapping("/request/accept") // senderUsername & receiverUsername public
	 * ResponseEntity<?> acceptFriendRequest(@RequestParam String senderUsername,
	 * 
	 * @RequestParam String receiverUsername) { try { Optional<User> senderOptional
	 * = userService.getUserByUsername(senderUsername); Optional<User>
	 * receiverOptional = userService.getUserByUsername(receiverUsername);
	 * 
	 * User sender = senderOptional.orElseThrow( () ->
	 * globalExceptionHandler.handleResourceNotFoundException("User", "username",
	 * senderUsername)); User receiver = receiverOptional.orElseThrow( () ->
	 * globalExceptionHandler.handleResourceNotFoundException("User", "username",
	 * receiverUsername));
	 * 
	 * friendRequestService.acceptFriendRequest(sender, receiver); return
	 * ResponseEntity.ok().body(receiverUsername + " accepted the request of " +
	 * senderUsername); } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	 * .body("Failed to accept friend request. Please try again later."); } }
	 * 
	 * @PutMapping("/request/reject") // senderUsername & receiverUsername public
	 * ResponseEntity<?> rejectFriendRequest(@RequestParam String senderUsername,
	 * 
	 * @RequestParam String receiverUsername) { try { Optional<User> senderOptional
	 * = userService.getUserByUsername(senderUsername); Optional<User>
	 * receiverOptional = userService.getUserByUsername(receiverUsername);
	 * 
	 * User sender = senderOptional.orElseThrow( () ->
	 * globalExceptionHandler.handleResourceNotFoundException("User", "username",
	 * senderUsername)); User receiver = receiverOptional.orElseThrow( () ->
	 * globalExceptionHandler.handleResourceNotFoundException("User", "username",
	 * receiverUsername));
	 * 
	 * friendRequestService.rejectFriendRequest(sender, receiver); return
	 * ResponseEntity.ok().body(receiverUsername + " rejected the request of " +
	 * senderUsername); } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	 * .body("Failed to reject friend request. Please try again later."); } }
	 */
}


/*
 * package com.connected.connection.Controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.DeleteMapping; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.PutMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.connected.connection.Repository.FriendRequestRepository; import
 * com.connected.connection.Repository.UserRepository; import
 * com.connected.connection.Service.FriendRequestService; import
 * com.connected.connection.Service.UserService; import
 * com.connected.connection.model.FriendRequest; import
 * com.connected.connection.model.RequestStatus; import
 * com.connected.connection.model.User;
 * 
 * @RestController
 * 
 * @RequestMapping("/friend") public class FriendController {
 * 
 * @Autowired private FriendRequestService friendRequestService;
 * 
 * @Autowired private UserRepository userRepository;
 * 
 * @Autowired private UserService userService;
 * 
 * @Autowired private FriendRequestRepository friendRequestRepository;
 * 
 * 
 * // Fetch friend requests for a specific user
 * 
 * @GetMapping("/{username}/pending-requests") public ResponseEntity<?>
 * getFriendRequests(@PathVariable String username) { try { // Get the user
 * based on the provided user-name User user =
 * userService.getUserByUsername(username); if (user != null) { // Fetch all
 * pending friend requests for the user List<FriendRequest> friendRequests =
 * friendRequestService.getPendingFriendRequestsForUser(user); if
 * (friendRequests.isEmpty()) { return
 * ResponseEntity.ok("No new connection requests for the username: " +
 * username); } else { return ResponseEntity.ok(friendRequests); } } else {
 * return ResponseEntity.notFound().build(); } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
 * 
 * // fetch all accepted request by the user
 * 
 * @GetMapping("/{username}/accepted-requests") public ResponseEntity<?>
 * getAcceptedRequests(@PathVariable String username) { try { // Get the user
 * based on the provided user-name User user =
 * userService.getUserByUsername(username); if (user != null) { // Fetch all
 * accepted friend requests for the user List<FriendRequest> acceptedRequests =
 * friendRequestService.getAcceptedFriendRequestsForUser(user); if
 * (acceptedRequests.isEmpty()) { return
 * ResponseEntity.ok("No accepted connection requests for " + username); } else
 * { return ResponseEntity.ok(acceptedRequests); } } else { return
 * ResponseEntity.notFound().build(); } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
 * 
 * 
 * @PostMapping("/request/send") // senderUsername and receiverUsername public
 * ResponseEntity<?> sendFriendRequest(@RequestParam String
 * senderUsername, @RequestParam String receiverUsername) { try { User sender =
 * userRepository.findByUsername(senderUsername); User receiver =
 * userRepository.findByUsername(receiverUsername);
 * 
 * if (sender != null && receiver != null) {
 * friendRequestService.sendFriendRequest(sender, receiver,
 * RequestStatus.PENDING); System.out.println("Friend request sent from " +
 * sender.getUsername() + " to " + receiver.getUsername()); return
 * ResponseEntity.ok().body("Friend request sent from " + sender.getUsername() +
 * " to " + receiver.getUsername()); } else { return
 * ResponseEntity.badRequest().body("Oops! Request defaulted. User not found.");
 * } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
 * body("Failed to send friend request. Please try again later."); } }
 * 
 * 
 * @DeleteMapping("/request/cancel") //senderUsername & receiverUsername public
 * ResponseEntity<?> cancelFriendRequest(@RequestParam String
 * senderUsername, @RequestParam String receiverUsername) { try { User sender =
 * userRepository.findByUsername(senderUsername); User receiver =
 * userRepository.findByUsername(receiverUsername);
 * 
 * if (sender != null && receiver != null) {
 * friendRequestService.cancelFriendRequest(sender, receiver);
 * System.out.println("Request Cancelled"); return ResponseEntity.ok().build();
 * return ResponseEntity.ok().body("Friend request cancelled by " +
 * sender.getUsername() + " for " + receiver.getUsername()); } else {
 * System.out.println("Oops! Request Defaulted"); return
 * ResponseEntity.badRequest().build(); } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
 * body("Failed to cancel friend request. Please try again later."); } }
 * 
 * 
 * @PutMapping("/request/accept") // senderUsername & receiverUsername public
 * ResponseEntity<?> acceptFriendRequest(@RequestParam String
 * senderUsername, @RequestParam String receiverUsername) { try { User sender =
 * userRepository.findByUsername(senderUsername); User receiver =
 * userRepository.findByUsername(receiverUsername);
 * 
 * if (sender != null && receiver != null) { List<FriendRequest> friendRequests
 * = friendRequestRepository.findBySenderAndReceiver(sender, receiver); if
 * (!friendRequests.isEmpty()) { FriendRequest friendRequest =
 * friendRequests.get(0); // Assuming there's only one request between two users
 * friendRequestService.acceptFriendRequest(friendRequest.getId()); String
 * message = receiverUsername + " accepted the request of " + senderUsername;
 * System.out.println(message); return ResponseEntity.ok().body(message); } else
 * { System.out.println("Request not found"); return
 * ResponseEntity.badRequest().body("Oops! Request not found."); } } else {
 * System.out.println("Oops! Request Defaulted"); return
 * ResponseEntity.badRequest().build(); } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
 * body("Failed to accept friend request. Please try again later."); } }
 * 
 * 
 * 
 * @PutMapping("/request/reject") // senderUsername & receiverUsername public
 * ResponseEntity<?> rejectFriendRequest(@RequestParam String
 * senderUsername, @RequestParam String receiverUsername) { try { User sender =
 * userRepository.findByUsername(senderUsername); User receiver =
 * userRepository.findByUsername(receiverUsername);
 * 
 * if (sender != null && receiver != null) { List<FriendRequest> friendRequests
 * = friendRequestRepository.findBySenderAndReceiver(sender, receiver); if
 * (!friendRequests.isEmpty()) { FriendRequest friendRequest =
 * friendRequests.get(0); // Assuming there's only one request between two users
 * friendRequestService.rejectFriendRequest(friendRequest.getId()); String
 * message = receiverUsername + " rejected the request of " + senderUsername;
 * System.out.println(message); return ResponseEntity.ok().body(message); } else
 * { System.out.println("Request not found"); return
 * ResponseEntity.badRequest().body("Oops! Request not found."); } } else {
 * System.out.println("Oops! Request Defaulted"); return
 * ResponseEntity.badRequest().build(); } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
 * body("Failed to reject friend request. Please try again later."); }
 * 
 * }
 * 
 * }
 */

/*
 * @GetMapping("/{username}/friend-requests") public
 * ResponseEntity<List<FriendRequest>> getFriendRequests(@PathVariable String
 * username) { try { // Get the user based on the provided username User user =
 * userService.getUserByUsername(username); if (user != null) { // Fetch all
 * friend requests for the user List<FriendRequest> friendRequests =
 * friendRequestService.getFriendRequestsForUser(user); return
 * ResponseEntity.ok(friendRequests); } else { return
 * ResponseEntity.notFound().build(); } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
 */

/*
 * @PostMapping("/request/send") // receiverUsername and senderUsername public
 * ResponseEntity<?> sendFriendRequest(@RequestParam String
 * senderUsername, @RequestParam String receiverUsername) { try { User sender =
 * userRepository.findByUsername(senderUsername); User receiver =
 * userRepository.findByUsername(receiverUsername);
 * 
 * if (sender != null && receiver != null) {
 * friendRequestService.sendFriendRequest(sender, receiver,
 * RequestStatus.PENDING); System.out.println("Friend request sent to " +
 * receiver.getUsername() + " from " + sender.getUsername() + " successfully");
 * return ResponseEntity.ok() .body("Friend request sent from " +
 * sender.getUsername() + " to " + receiver.getUsername()); } else { return
 * ResponseEntity.badRequest().body("Oops! Request defaulted. User not found.");
 * } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
 * .body("Failed to send friend request. Please try again later."); } }
 */

/*
 * @DeleteMapping("/request/cancel") public ResponseEntity<?>
 * cancelFriendRequest(@RequestParam String senderUsername, @RequestParam String
 * receiverUsername) { try { User sender =
 * userRepository.findByUsername(senderUsername); User receiver =
 * userRepository.findByUsername(receiverUsername);
 * 
 * if (sender != null && receiver != null) {
 * friendRequestService.cancelFriendRequest(sender, receiver);
 * System.out.println("Request Cancelled"); return
 * ResponseEntity.ok().body("Friend request to " + receiver.getUsername() +
 * " is cancelled by " + sender.getUsername() + "successfully"); } else {
 * System.out.println("Oops! Request Defaulted"); return
 * ResponseEntity.badRequest().body("Oops! Request defaulted. User not found.");
 * } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
 * .body("Failed to cancel friend request. Please try again later."); } }
 */

/*
 * @PutMapping("/request/accept") public ResponseEntity<?>
 * acceptFriendRequest(@RequestParam Long requestId) { try {
 * friendRequestService.acceptFriendRequest(requestId);
 * System.out.println("Friend request accepted successfully."); return
 * ResponseEntity.ok().body("Friend request accepted successfully."); } catch
 * (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
 * .body("Failed to accept friend request. Please try again later."); } }
 */

/*
 * @PutMapping("/request/reject") public ResponseEntity<?>
 * rejectFriendRequest(@RequestParam Long requestId) { try {
 * friendRequestService.rejectFriendRequest(requestId);
 * System.out.println("Friend request rejected successfully."); return
 * ResponseEntity.ok().body("Friend request rejected successfully."); } catch
 * (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
 * .body("Failed to reject friend request. Please try again later."); } }
 */

/*
 * package com.connected.connection.Controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.DeleteMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.PutMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.connected.connection.Repository.UserRepository; import
 * com.connected.connection.Service.FriendRequestService; import
 * com.connected.connection.model.RequestStatus; import
 * com.connected.connection.model.User;
 * 
 * @RestController
 * 
 * @RequestMapping("/friend") public class FriendController {
 * 
 * @Autowired private FriendRequestService friendRequestService;
 * 
 * @Autowired private UserRepository userRepository;
 * 
 * @PostMapping("/request/send") // receiverId and senderId public
 * ResponseEntity<?> sendFriendRequest(@RequestParam Long
 * senderId, @RequestParam Long receiverId) { try { User sender =
 * userRepository.findById(senderId).orElse(null); User receiver =
 * userRepository.findById(receiverId).orElse(null);
 * 
 * if (sender != null && receiver != null) {
 * friendRequestService.sendFriendRequest(sender, receiver,
 * RequestStatus.PENDING); System.out.println("Friend request sent to " +
 * receiver.getUsername() + " from " + sender.getUsername() + " successfully");
 * return ResponseEntity.ok() .body("Friend request sent from " +
 * sender.getUsername() + " to " + receiver.getUsername()); } else { return
 * ResponseEntity.badRequest().body("Oops! Request defaulted. User not found.");
 * } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
 * .body("Failed to send friend request. Please try again later."); } }
 * 
 * @DeleteMapping("/request/cancel") public ResponseEntity<?>
 * cancelFriendRequest(@RequestParam Long senderId, @RequestParam Long
 * receiverId) { try { User sender =
 * userRepository.findById(senderId).orElse(null); User receiver =
 * userRepository.findById(receiverId).orElse(null);
 * 
 * if (sender != null && receiver != null) {
 * friendRequestService.cancelFriendRequest(sender, receiver);
 * System.out.println("Request Cancelled"); return
 * ResponseEntity.ok().body("Friend request to " + receiver.getUsername() +
 * " is cancelled by " + sender.getUsername() + "successfully"); } else {
 * System.out.println("Oops! Request Defaulted"); return
 * ResponseEntity.badRequest().body("Oops! Request defaulted. User not found.");
 * } } catch (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
 * .body("Failed to cancel friend request. Please try again later."); } }
 * 
 * @PutMapping("/request/accept") public ResponseEntity<?>
 * acceptFriendRequest(@RequestParam Long requestId) { try { User sender =
 * friendRequestService.getSenderOfRequest(requestId); User receiver =
 * friendRequestService.getReceiverOfRequest(requestId);
 * 
 * if (sender != null && receiver != null) {
 * friendRequestService.acceptFriendRequest(requestId);
 * System.out.println("Friend request accepted by " + receiver.getUsername() +
 * " of " + sender.getUsername() + " successfully."); return
 * ResponseEntity.ok().body("Friend request of " + sender.getUsername() +
 * " is accepted by " + receiver.getUsername() + " successfully."); } else {
 * return ResponseEntity.badRequest().
 * body("Failed to accept friend request. Request not found."); } } catch
 * (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
 * .body("Failed to accept friend request. Please try again later."); } }
 * 
 * @PutMapping("/request/reject") public ResponseEntity<?>
 * rejectFriendRequest(@RequestParam Long requestId) { try { User sender =
 * friendRequestService.getSenderOfRequest(requestId); User receiver =
 * friendRequestService.getReceiverOfRequest(requestId);
 * 
 * if (sender != null && receiver != null) {
 * System.out.println("Request Rejected by " + receiver.getUsername() + " of " +
 * sender.getUsername()); return ResponseEntity.ok().body("Friend request of " +
 * sender.getUsername() + " is rejected by " + receiver.getUsername() +
 * " successfully."); } else { return ResponseEntity.badRequest().
 * body("Failed to reject friend request. Request not found."); } } catch
 * (Exception e) { return
 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
 * .body("Failed to reject friend request. Please try again later."); } }
 * 
 * }
 */