
package com.cg.capbook.services;

import java.util.List;

import com.cg.capbook.beans.Friend;
import com.cg.capbook.beans.Relationship;
import com.cg.capbook.exceptions.InvalidEmailIdException;

public interface FriendServices { 
	public boolean sendFriendRequest(String senderId, String receiverId) throws InvalidEmailIdException; 
	public boolean confirmFriendRequest(String senderId,String receiverId) throws InvalidEmailIdException; 
	public boolean removeFriend(String senderId,String receiverId) throws InvalidEmailIdException; 
	public boolean rejectFriendRequest(String senderId,String receiverId); 
	public List<Relationship> showAllFriendRequests(String emailId); 
	public List<Relationship> showAllSentFriendRequests(String emailId); 
	public Relationship findFriendRequest(String senderId,String receiverId); 
	public List<Friend> showAllFriends(String emailId)throws InvalidEmailIdException;
	}
