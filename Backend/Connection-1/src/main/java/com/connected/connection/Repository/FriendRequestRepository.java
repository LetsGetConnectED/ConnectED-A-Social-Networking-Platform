package com.connected.connection.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connected.connection.model.FriendRequest;
import com.connected.connection.model.User;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

	List<FriendRequest> findBySenderAndReceiver(User sender, User receiver);

	List<FriendRequest> findByReceiver(User receiver);
	
	/*
	 * Optional<FriendRequest> fetchBySenderAndReceiver (User sender, User
	 * receiver);
	 */

}
