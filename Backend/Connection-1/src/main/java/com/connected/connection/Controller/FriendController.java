package com.connected.connection.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.connected.connection.Repository.UserRepository;
import com.connected.connection.Service.FriendRequestService;
import com.connected.connection.model.RequestStatus;
import com.connected.connection.model.User;

@RestController
@RequestMapping("/friend")
public class FriendController {
	@Autowired
	private FriendRequestService friendRequestService;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/request/send") // receiverId and senderId
	public ResponseEntity<?> sendFriendRequest(@RequestParam Long senderId, @RequestParam Long receiverId) {
		try {
			User sender = userRepository.findById(senderId).orElse(null);
			User receiver = userRepository.findById(receiverId).orElse(null);

			if (sender != null && receiver != null) {
				friendRequestService.sendFriendRequest(sender, receiver, RequestStatus.PENDING);
				System.out.println("Friend request sent to " + receiver.getUsername() + " from " + sender.getUsername()
						+ " successfully");
				return ResponseEntity.ok()
						.body("Friend request sent from " + sender.getUsername() + " to " + receiver.getUsername());
			} else {
				return ResponseEntity.badRequest().body("Oops! Request defaulted. User not found.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to send friend request. Please try again later.");
		}
	}

	@DeleteMapping("/request/cancel")
	public ResponseEntity<?> cancelFriendRequest(@RequestParam Long senderId, @RequestParam Long receiverId) {
		try {
			User sender = userRepository.findById(senderId).orElse(null);
			User receiver = userRepository.findById(receiverId).orElse(null);

			if (sender != null && receiver != null) {
				friendRequestService.cancelFriendRequest(sender, receiver);
				System.out.println("Request Cancelled");
				return ResponseEntity.ok().body("Friend request to " + receiver.getUsername() + " is cancelled by "
						+ sender.getUsername() + "successfully");
			} else {
				System.out.println("Oops! Request Defaulted");
				return ResponseEntity.badRequest().body("Oops! Request defaulted. User not found.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to cancel friend request. Please try again later.");
		}
	}

	@PutMapping("/request/accept")
	public ResponseEntity<?> acceptFriendRequest(@RequestParam Long requestId) {
		try {
			User sender = friendRequestService.getSenderOfRequest(requestId);
			User receiver = friendRequestService.getReceiverOfRequest(requestId);

			if (sender != null && receiver != null) {
				friendRequestService.acceptFriendRequest(requestId);
				System.out.println("Friend request accepted by " + receiver.getUsername() + " of "
						+ sender.getUsername() + " successfully.");
				return ResponseEntity.ok().body("Friend request of " + sender.getUsername() + " is accepted by "
						+ receiver.getUsername() + " successfully.");
			} else {
				return ResponseEntity.badRequest().body("Failed to accept friend request. Request not found.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to accept friend request. Please try again later.");
		}
	}

	@PutMapping("/request/reject")
	public ResponseEntity<?> rejectFriendRequest(@RequestParam Long requestId) {
		try {
			User sender = friendRequestService.getSenderOfRequest(requestId);
			User receiver = friendRequestService.getReceiverOfRequest(requestId);

			if (sender != null && receiver != null) {
				System.out.println("Request Rejected by " + receiver.getUsername() + " of " + sender.getUsername());
				return ResponseEntity.ok().body("Friend request of " + sender.getUsername() + " is rejected by "
						+ receiver.getUsername() + " successfully.");
			} else {
				return ResponseEntity.badRequest().body("Failed to reject friend request. Request not found.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to reject friend request. Please try again later.");
		}
	}

	/* -----------------------------------1----------------------------------- */

	/*
	 * @PostMapping("/request/send") // senderUsername and receiverUsername public
	 * ResponseEntity<?> sendFriendRequest(@RequestParam String
	 * senderUsername, @RequestParam String receiverUsername) { try { User sender =
	 * userRepository.findByUsername(senderUsername); User receiver =
	 * userRepository.findByUsername(receiverUsername);
	 * 
	 * if (sender != null && receiver != null) {
	 * friendRequestService.sendFriendRequest(sender, receiver, null);
	 * friendRequestService.sendFriendRequest(sender, receiver,
	 * RequestStatus.PENDING); System.out.println("Friend request sent from " +
	 * sender.getUsername() + " to " + receiver.getUsername()); return
	 * ResponseEntity.ok().body("Friend request sent from " + sender.getUsername() +
	 * " to " + receiver.getUsername()); } else { return
	 * ResponseEntity.badRequest().body("Oops! Request defaulted. User not found.");
	 * } } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
	 * body("Failed to send friend request. Please try again later."); } }
	 */

	/*----------------------------------------------------------------------------*/

	/*------------------------------------2----------------------------------------*/

	/*
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
	 */
	/*----------------------------------------------------------------------------*/

	/*-----------------------------------3-----------------------------------------*/

	/*
	 * @PutMapping("/request/accept") // senderUsername & receiverUsername public
	 * ResponseEntity<?> acceptFriendRequest(@RequestParam String senderUsername,
	 * 
	 * @RequestParam String receiverUsername) { try { User sender =
	 * userRepository.findByUsername(senderUsername); User receiver =
	 * userRepository.findByUsername(receiverUsername);
	 * 
	 * if (sender != null && receiver != null) { List<FriendRequest> friendRequests
	 * = friendRequestRepository.findBySenderAndReceiver(sender, receiver); if
	 * (!friendRequests.isEmpty()) { FriendRequest friendRequest =
	 * friendRequests.get(0); // Assuming there's only one request between // two
	 * users friendRequestService.acceptFriendRequest(friendRequest.getId()); String
	 * message = receiverUsername + " accepted the request of " + senderUsername;
	 * System.out.println(message); return ResponseEntity.ok().body(message); } else
	 * { System.out.println("Request not found"); return
	 * ResponseEntity.badRequest().body("Oops! Request not found."); } } else {
	 * System.out.println("Oops! Request Defaulted"); return
	 * ResponseEntity.badRequest().build(); } } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	 * .body("Failed to accept friend request. Please try again later."); } }
	 */

	/*----------------------------------------------------------------------------*/

	/*--------------------------------------4--------------------------------------*/

	/*
	 * @PutMapping("/request/reject") // senderUsername & receiverUsername public
	 * ResponseEntity<?> rejectFriendRequest(@RequestParam String senderUsername,
	 * 
	 * @RequestParam String receiverUsername) { try { User sender =
	 * userRepository.findByUsername(senderUsername); User receiver =
	 * userRepository.findByUsername(receiverUsername);
	 * 
	 * if (sender != null && receiver != null) { List<FriendRequest> friendRequests
	 * = friendRequestRepository.findBySenderAndReceiver(sender, receiver); if
	 * (!friendRequests.isEmpty()) { FriendRequest friendRequest =
	 * friendRequests.get(0); // Assuming there's only one request between // two
	 * users friendRequestService.rejectFriendRequest(friendRequest.getId()); String
	 * message = receiverUsername + " rejected the request of " + senderUsername;
	 * System.out.println(message); return ResponseEntity.ok().body(message); } else
	 * { System.out.println("Request not found"); return
	 * ResponseEntity.badRequest().body("Oops! Request not found."); } } else {
	 * System.out.println("Oops! Request Defaulted"); return
	 * ResponseEntity.badRequest().build(); } } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	 * .body("Failed to reject friend request. Please try again later."); } }
	 */

	/*----------------------------------------------------------------------------*/

}
