
package com.cg.capbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.capbook.beans.Account;
import com.cg.capbook.beans.Friend;
import com.cg.capbook.beans.Relationship; 
import com.cg.capbook.daoservices.FriendsDao;
import com.cg.capbook.daoservices.RelationshipDao; 
import com.cg.capbook.exceptions.InvalidEmailIdException;
@Component("friendServices")
public class FriendServicesImpl implements FriendServices{
	@Autowired 
	private CapbookServices capbookServices;

	@Autowired 
	private RelationshipDao relationshipDao;

	@Autowired 
	private FriendsDao friendsDao;



	@Override public boolean sendFriendRequest(String senderId, String receiverId) throws InvalidEmailIdException { 
		capbookServices.findAccountByEmailId(senderId); 
		capbookServices.findAccountByEmailId(receiverId);
		Relationship relationship = new Relationship(); 
		relationship.setSenderEmailId(senderId);
		relationship.setReceiverEmailId(receiverId);
		relationship.setStatus("pending");
		relationshipDao.save(relationship);
		return true; 
		}
	@Override
	public boolean confirmFriendRequest(String senderId, String receiverId) throws InvalidEmailIdException {
		
		Account senderAcc=capbookServices.findAccountByEmailId(senderId); 
		Account recieverAcc=capbookServices.findAccountByEmailId(receiverId); 
		Friend friend1=new Friend(senderId, recieverAcc);
		friendsDao.save(friend1);
		Friend friend2=new Friend(receiverId, senderAcc);
		friendsDao.save(friend2);
		System.out.println(friend1.getFriendEmailId()+""+friend1.getAccount().getEmailId());
		System.out.println(friend2.getFriendEmailId()+""+friend2.getAccount().getEmailId());
		
		
		
		
		Relationship relation=findFriendRequest(senderId, receiverId);
		relation.setStatus("friends");
		relationshipDao.save(relation);
		return false;
	}
	@Override
	public boolean rejectFriendRequest(String senderId, String receiverId) {
		Relationship relation=findFriendRequest(senderId, receiverId);
		relation.setStatus("rejected");
		relationshipDao.save(relation);
		return false;
	}
	@Override
	public List<Relationship> showAllFriendRequests(String emailId) {
		return relationshipDao.showAllFriendRequests(emailId);
	}
	@Override
	public List<Relationship> showAllSentFriendRequests(String emailId) {
		return relationshipDao.showAllSentFriendRequests(emailId);
	}
	@Override
	public Relationship findFriendRequest(String senderId, String receiverId) {
		return relationshipDao.findFriendRequest(senderId, receiverId);
	}
	public List<Friend> showAllFriends(String emailId)throws InvalidEmailIdException{
	return friendsDao.showAllFriends(emailId);
	}
	@Override
	public boolean removeFriend(String accountId, String friendId) throws InvalidEmailIdException {
		Relationship relation=findFriendRequest(accountId, friendId);
		relationshipDao.delete(relation);
		//friendsDao.toDelete(accountId, friendId);
		return false;
	}
}

