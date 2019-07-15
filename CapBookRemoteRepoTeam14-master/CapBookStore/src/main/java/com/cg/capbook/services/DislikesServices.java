package com.cg.capbook.services;

import com.cg.capbook.beans.Dislikes;
import com.cg.capbook.beans.Likes;

public interface DislikesServices {
	public Dislikes updateDislikes(int postId, String dislikedBy);
	public int getDislikesCount(int postId);
}
