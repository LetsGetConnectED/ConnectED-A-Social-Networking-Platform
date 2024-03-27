package com.connected.connection.Service;

import java.util.List;

import com.connected.connection.model.FriendRequest;
import com.connected.connection.model.RequestStatus;
import com.connected.connection.model.User;

public interface FriendRequestService {

	void sendFriendRequest(User sender, User receiver, RequestStatus status);

	void cancelFriendRequest(User sender, User receiver);

	void acceptFriendRequest(Long requestId);

	void rejectFriendRequest(Long requestId);

	User getSenderOfRequest(Long requestId);

	User getReceiverOfRequest(Long requestId);

	List<FriendRequest> getFriendRequestsForUser(User user);

	List<FriendRequest> getPendingFriendRequestsForUser(User user);
	
	List<FriendRequest> getAcceptedFriendRequestsForUser(User user);
	
	void acceptFriendRequest(User sender, User receiver);
	
	void rejectFriendRequest(User sender, User receiver);
}

/*
 * package com.connected.connection.Service;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.connected.connection.Repository.FriendRequestRepository; import
 * com.connected.connection.model.FriendRequest; import
 * com.connected.connection.model.RequestStatus; import
 * com.connected.connection.model.User;
 * 
 * import java.util.List; import java.util.Optional;
 * 
 * @Service public class FriendRequestService {
 * 
 * @Autowired private FriendRequestRepository friendRequestRepository;
 * 
 * public void sendFriendRequest(User sender, User receiver, RequestStatus
 * status) { // Create a new friend request with the provided sender, receiver,
 * and status FriendRequest request = new FriendRequest();
 * request.setSender(sender); request.setReceiver(receiver);
 * request.setStatus(status);
 * 
 * // Save the friend request friendRequestRepository.save(request); }
 * 
 * 
 * public void sendFriendRequest(User sender, User receiver) { FriendRequest
 * request = new FriendRequest(null, sender, receiver, null);
 * request.setSender(sender); request.setReceiver(receiver);
 * friendRequestRepository.save(request); }
 * 
 * 
 * public void cancelFriendRequest(User sender, User receiver) {
 * List<FriendRequest> requests =
 * friendRequestRepository.findBySenderAndReceiver(sender, receiver); if
 * (!requests.isEmpty()) { FriendRequest requestToDelete = requests.get(0); //
 * Assuming you want to delete the first request
 * friendRequestRepository.delete(requestToDelete); } }
 * 
 * 
 * 
 * public FriendRequest acceptFriendRequest(Long requestId) {
 * Optional<FriendRequest> optionalRequest =
 * friendRequestRepository.findById(requestId); if (optionalRequest.isPresent())
 * { FriendRequest request = optionalRequest.get();
 * request.setStatus(RequestStatus.ACCEPTED);
 * friendRequestRepository.save(request); return request; } return null; }
 * 
 * 
 * 
 * public void acceptFriendRequest(Long requestId) { Optional<FriendRequest>
 * optionalRequest = friendRequestRepository.findById(requestId);
 * optionalRequest.ifPresent(request -> {
 * request.setStatus(RequestStatus.ACCEPTED);
 * friendRequestRepository.save(request); }); }
 * 
 * public void rejectFriendRequest(Long requestId) { Optional<FriendRequest>
 * optionalRequest = friendRequestRepository.findById(requestId);
 * optionalRequest.ifPresent(request -> {
 * request.setStatus(RequestStatus.REJECTED);
 * friendRequestRepository.save(request); }); }
 * 
 * public User getSenderOfRequest(Long requestId) { Optional<FriendRequest>
 * optionalRequest = friendRequestRepository.findById(requestId); if
 * (optionalRequest.isPresent()) { return optionalRequest.get().getSender(); }
 * return null; }
 * 
 * public User getReceiverOfRequest(Long requestId) { Optional<FriendRequest>
 * optionalRequest = friendRequestRepository.findById(requestId); if
 * (optionalRequest.isPresent()) { return optionalRequest.get().getReceiver(); }
 * return null; }
 * 
 * 
 * }
 * 
 */