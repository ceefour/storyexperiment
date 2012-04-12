package com.hendyirawan.storyexperiment.dozer;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hendyirawan.storyexperiment.common.JacksonUtils;

/**
 * Simple Story.
 * @author ceefour
 */
public class Story {

	private String id;
	private String subject;
	private String kind;
	private DateTime created;
	private boolean publicized;
	
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the kind
	 */
	public String getKind() {
		return kind;
	}
	/**
	 * @param kind the kind to set
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}
	/**
	 * @return the created
	 */
	public DateTime getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(DateTime created) {
		this.created = created;
	}
	
	@Override
	public String toString() {
		return JacksonUtils.asJson(this);
	}
	public boolean isPublicized() {
		return publicized;
	}
	public void setPublicized(boolean publicized) {
		this.publicized = publicized;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
