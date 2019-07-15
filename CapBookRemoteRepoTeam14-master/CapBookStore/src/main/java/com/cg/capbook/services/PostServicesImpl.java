package com.cg.capbook.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.capbook.beans.Account;
import com.cg.capbook.beans.Friend;
import com.cg.capbook.beans.Post;
import com.cg.capbook.daoservices.AccountDao;
import com.cg.capbook.daoservices.PostDao;
import com.cg.capbook.exceptions.InvalidEmailIdException;
//abcd

import net.bytebuddy.asm.Advice.Return;
@Component("postServices")
public class PostServicesImpl implements PostServices{
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private PostDao postDao;
	@Autowired
	FriendServices friendServices;
	@Autowired
	PostServices postServices;
	@Override
	public Post savePost(String emailId, String postMessage) {
		Account account=accountDao.findById(emailId).orElse(null);
		Post post=new Post(postMessage, account);
		return postDao.save(post);
	}
	@Override
	public List<Post> ShowAllPosts(String emailId) {
		return postDao.showAllPosts(emailId);
		
	}
	public boolean deletePost(int postId) {
		postDao.deleteById(postId);
		return true;
	}
	@Override
	public List<Post> ShowAllFriendsPosts(String emailId) throws InvalidEmailIdException {
		List<Post> allPosts=new ArrayList<Post>();
		List<Friend> allfriends=friendServices.showAllFriends(emailId);
		
		for(Friend friend : allfriends) {
			List<Post> friendPost=ShowAllPosts(friend.getFriendEmailId());
			for(Post post : friendPost) {
			   allPosts.add(post);
			}
			
		}
		List<Post> MyPost=ShowAllPosts(emailId);
		for(Post post : MyPost) {
			   allPosts.add(post);
			}
		return allPosts;
	}
}
	
	
