package com.cg.capbook.services;

import com.cg.capbook.beans.Likes;

public interface LikesServices {
	public Likes updateLikes(int postId, String likedBy);
	public int getLikesCount(int postId);
}
