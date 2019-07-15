
 package com.cg.capbook.daoservices;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.capbook.beans.Friend;
 public interface FriendsDao extends JpaRepository<Friend, String>{
	 
	  @Query("from Friend where ACCOUNT_EMAIL_ID=:emailId")
			public List<Friend> showAllFriends(@Param("emailId") String emailId);
	// @Modifying@Transactional
	//@Query("delete from Friend where ACCOUNT_EMAIL_ID=:ACCOUNT_EMAIL_ID and FRIEND_EMAIL_ID=:FRIEND_EMAIL_ID")
	//  		public void toDelete(String ACCOUNT_EMAIL_ID,String FRIEND_EMAIL_ID);

 }
 