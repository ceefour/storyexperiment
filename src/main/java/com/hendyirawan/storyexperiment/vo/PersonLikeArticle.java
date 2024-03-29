/**
 * 
 */
package com.hendyirawan.storyexperiment.vo;

/**
 * Person like Article.
 * @author ceefour
 *
 */
public class PersonLikeArticle extends Story {

	private SimpleRef liker;
	private SimpleRef liked;
	
	public PersonLikeArticle() {
		super();
	}
	
	public PersonLikeArticle(String subject, boolean publicized,
			SimpleRef liker, SimpleRef liked) {
		super(subject, publicized);
		setKind("PersonLikeArticle");
		this.liker = liker;
		this.liked = liked;
	}
	
	/**
	 * @return the liker
	 */
	public SimpleRef getLiker() {
		return liker;
	}
	/**
	 * @param liker the liker to set
	 */
	public void setLiker(SimpleRef liker) {
		this.liker = liker;
	}
	/**
	 * @return the liked
	 */
	public SimpleRef getLiked() {
		return liked;
	}
	/**
	 * @param liked the liked to set
	 */
	public void setLiked(SimpleRef liked) {
		this.liked = liked;
	}
	
}
