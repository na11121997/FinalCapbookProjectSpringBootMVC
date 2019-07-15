package com.cg.capbook.beans;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
//abcd
@Entity
public class Post implements Comparable<Post>{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int postId;
	private String postContent,postPic;
	@ManyToOne
	private Account account;
	@OneToMany(mappedBy="posts",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Likes> likes;
	@OneToMany(mappedBy="posts",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Dislikes> dislikes;
	@OneToMany(mappedBy="posts",cascade=CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval=true)
	@MapKey
	private Map<Integer, Comments> comments;
	public Post(int postId, String postContent, String postPic, Account account, List<Likes> likes,
			List<Dislikes> dislikes, int totalLikeCount, int totalDislikeCount, Map<Integer, Comments> comments) {
		super();
		this.postId = postId;
		this.postContent = postContent;
		this.postPic = postPic;
		this.account = account;
		this.likes = likes;
		this.dislikes = dislikes;
		this.totalLikeCount = totalLikeCount;
		this.totalDislikeCount = totalDislikeCount;
		this.comments = comments;
	}
	private int totalLikeCount;
	private int totalDislikeCount;
	public int getTotalDislikeCount() {
		return totalDislikeCount;
	}

	public void setTotalDislikeCount(int totalDislikeCount) {
		this.totalDislikeCount = totalDislikeCount;
	}
	

	public int getTotalLikeCount() {
		return totalLikeCount;
	}

	public Post(int postId, String postContent, String postPic, Account account, List<Likes> likes,
			List<Dislikes> dislikes, int totalLikeCount, Map<Integer, Comments> comments) {
		super();
		this.postId = postId;
		this.postContent = postContent;
		this.postPic = postPic;
		this.account = account;
		this.likes = likes;
		this.dislikes = dislikes;
		this.totalLikeCount = totalLikeCount;
		this.comments = comments;
	}

	public void setTotalLikeCount(int totalLikeCount) {
		this.totalLikeCount = totalLikeCount;
	}

	public List<Dislikes> getDislikes() {
		return dislikes;
	}

	public void setDislikes(List<Dislikes> dislikes) {
		this.dislikes = dislikes;
	}

	public List<Likes> getLikes() {
		return likes;
	}

	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}
	public Post() {}

	public Post(String postContent, Account account) {
		super();
		this.postContent = postContent;
		this.account = account;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public Account getaccount() {
		return account;
	}
	public void setaccount(Account account) {
		this.account = account;
	}
	public String getPostPic() {
		return postPic;
	}
	public void setPostPic(String postPic) {
		this.postPic = postPic;
	}


	public Map<Integer, Comments> getComments() {
		return comments;
	}
	public void setComments(Map<Integer, Comments> comments) {
		this.comments = comments;
	}
	public Post(int postId, String postContent, String postPic, Account account, List<Likes> likes,
			List<Dislikes> dislikes, Map<Integer, Comments> comments) {
		super();
		this.postId = postId;
		this.postContent = postContent;
		this.postPic = postPic;
		this.account = account;
		this.likes = likes;
		this.dislikes = dislikes;
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", postContent=" + postContent + ", account=" + account + ", postPic=" + postPic
				+ ", Likes=" + likes + ", comments=" + comments + "]";
	}

	@Override
	public int compareTo(Post comparePost) {
		 int compareId=((Post)comparePost).getPostId();
		 return compareId-this.postId;
	}

}