package com.hendyirawan.storyexperiment.morphia;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;

/**
 * Person like Article.
 * @author ceefour
 *
 */
@Entity(noClassnameStored=true)
public class PersonLikeArticle extends Story {

	@Embedded private SimpleRef liker;
	@Embedded private SimpleRef liked;
	
	public PersonLikeArticle() {
		super();
	}
	
	public PersonLikeArticle(String subject, boolean publicized,
			SimpleRef liker, SimpleRef liked) {
		super(subject, "PersonLikeArticle", publicized);
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
