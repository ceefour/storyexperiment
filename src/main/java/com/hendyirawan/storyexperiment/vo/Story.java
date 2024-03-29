package com.hendyirawan.storyexperiment.vo;

import java.io.Serializable;

import org.joda.time.DateTime;

import com.hendyirawan.storyexperiment.common.JsonUtils;

/**
 * Simple Story.
 * @author ceefour
 */
@SuppressWarnings("serial")
public class Story implements Serializable {

	private String id;
	private String subject;
	private String kind;
	private DateTime created;
	private boolean publicized;
	
	public Story() {
	}
	
	public Story(String subject, boolean publicized) {
		super();
		this.subject = subject;
		this.publicized = publicized;
		this.created = new DateTime();
	}
	
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
		return JsonUtils.asJson(this);
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
