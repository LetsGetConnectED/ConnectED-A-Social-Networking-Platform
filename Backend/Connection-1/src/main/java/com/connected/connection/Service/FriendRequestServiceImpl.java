package com.connected.connection.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connected.connection.Repository.FriendRequestRepository;
import com.connected.connection.model.FriendRequest;
import com.connected.connection.model.RequestStatus;
import com.connected.connection.model.User;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Override
    public List<FriendRequest> getFriendRequestsForUser(User user) {
        return friendRequestRepository.findByReceiver(user);
    }
    
    @Override
    public void sendFriendRequest(User sender, User receiver, RequestStatus status) {
        // Create a new friend request with the provided sender, receiver, and status
        FriendRequest request = new FriendRequest();
        request.setSender(sender);
        request.setReceiver(receiver);
        request.setStatus(status);

        // Save the friend request
        friendRequestRepository.save(request);
    }

    @Override
    public void cancelFriendRequest(User sender, User receiver) {
        List<FriendRequest> requests = friendRequestRepository.findBySenderAndReceiver(sender, receiver);
        if (!requests.isEmpty()) {
            FriendRequest requestToDelete = requests.get(0); // Assuming you want to delete the first request
            friendRequestRepository.delete(requestToDelete);
        }
    }

    @Override
    public void acceptFriendRequest(Long requestId) {
        Optional<FriendRequest> optionalRequest = friendRequestRepository.findById(requestId);
        optionalRequest.ifPresent(request -> {
            request.setStatus(RequestStatus.ACCEPTED);
            friendRequestRepository.save(request);
        });
    }

    @Override
    public void rejectFriendRequest(Long requestId) {
        Optional<FriendRequest> optionalRequest = friendRequestRepository.findById(requestId);
        optionalRequest.ifPresent(request -> {
            request.setStatus(RequestStatus.REJECTED);
            friendRequestRepository.save(request);
        });
    }

    @Override
    public User getSenderOfRequest(Long requestId) {
        Optional<FriendRequest> optionalRequest = friendRequestRepository.findById(requestId);
        if (optionalRequest.isPresent()) {
            return optionalRequest.get().getSender();
        }
        return null;
    }

    @Override
    public User getReceiverOfRequest(Long requestId) {
        Optional<FriendRequest> optionalRequest = friendRequestRepository.findById(requestId);
        if (optionalRequest.isPresent()) {
            return optionalRequest.get().getReceiver();
        }
        return null;
    }
    
    @Override
    public List<FriendRequest> getPendingFriendRequestsForUser(User user) {
        // Fetch all friend requests for the user
        List<FriendRequest> allFriendRequests = friendRequestRepository.findByReceiver(user);
        
        // Filter the pending friend requests
        List<FriendRequest> pendingRequests = new ArrayList<>();
        for (FriendRequest request : allFriendRequests) {
            if (request.getStatus() == RequestStatus.PENDING) {
                pendingRequests.add(request);
            }
        }
        
        return pendingRequests;
    }
    
    @Override
    public List<FriendRequest> getAcceptedFriendRequestsForUser(User user) {
        // Fetch all friend requests for the user
        List<FriendRequest> allFriendRequests = friendRequestRepository.findByReceiver(user);
        
        // Filter the pending friend requests
        List<FriendRequest> acceptedRequests = new ArrayList<>();
        for (FriendRequest request : allFriendRequests) {
            if (request.getStatus() == RequestStatus.ACCEPTED) {
                acceptedRequests.add(request);
            }
        }
        
        return acceptedRequests;
    }
}
