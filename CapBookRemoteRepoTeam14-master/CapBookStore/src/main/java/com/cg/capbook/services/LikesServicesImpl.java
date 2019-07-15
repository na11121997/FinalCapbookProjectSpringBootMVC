package com.cg.capbook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.capbook.beans.Likes;
import com.cg.capbook.beans.Post;
import com.cg.capbook.daoservices.LikesDao;
import com.cg.capbook.daoservices.PostDao;
@Component("likeServices")
public class LikesServicesImpl implements LikesServices{
	@Autowired
	private LikesDao likesDao;
	@Autowired 
	private PostDao postDao;

	@Override
	public Likes updateLikes(int postId, String likedBy) {
		Likes alreadyLikedAccount=likesDao.getLikeByStatus(postId, likedBy);
		if(alreadyLikedAccount!=null) {
			if(alreadyLikedAccount.getlikecount()==1){
				alreadyLikedAccount.setlikecount(0);
				return likesDao.save(alreadyLikedAccount);
			}else {
				alreadyLikedAccount.setlikecount(1);
				return likesDao.save(alreadyLikedAccount);
			}
		}
		return likesDao.save(new Likes(likedBy, 1, postDao.findById(postId).orElse(null)));
	}
	@Override
	public int getLikesCount(int postId) {
		Post post=postDao.findById(postId).orElse(null);
		post.setTotalLikeCount(likesDao.getLikeCount(postId));
		postDao.save(post);
		return post.getTotalLikeCount();
	}

}
