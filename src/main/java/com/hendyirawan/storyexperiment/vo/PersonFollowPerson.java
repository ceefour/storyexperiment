package com.hendyirawan.storyexperiment.vo;

/**
 * @author ceefour
 * Person following another Person.
 */
public class PersonFollowPerson extends Story {

	private SimpleRef follower;
	private SimpleRef followed;
	
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
