package com.hendyirawan.storyexperiment.morphia;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;

/**
 * @author ceefour
 * Person following another Person.
 */
@Entity(noClassnameStored=true)
public class PersonFollowPerson extends Story {

	@Embedded private SimpleRef follower;
	@Embedded private SimpleRef followed;
	
	/**
	 * @return the follower
	 */
	public SimpleRef getFollower() {
		return follower;
	}
	/**
	 * @param follower the follower to set
	 */
	public void setFollower(SimpleRef follower) {
		this.follower = follower;
	}
	/**
	 * @return the followed
	 */
	public SimpleRef getFollowed() {
		return followed;
	}
	/**
	 * @param followed the followed to set
	 */
	public void setFollowed(SimpleRef followed) {
		this.followed = followed;
	}
	
}
