package com.hendyirawan.storyexperiment.morphia;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.hendyirawan.storyexperiment.common.JacksonUtils;

/**
 * Simple Story.
 * @author ceefour
 */
@Entity(noClassnameStored=true)
public class Story {

	@Id private ObjectId id;
	private String subject;
	private String kind;
	private DateTime created;
	private boolean publicized;

	/**
	 * Create an absolutely empty Story.
	 */
	public Story() {
	}
	
	/**
	 * You should create a concrete subclass of Story, not this constructor.
	 * @param subject
	 * @param kind
	 * @param publicized
	 */
	public Story(String subject, String kind, boolean publicized) {
		super();
		this.subject = subject;
		this.kind = kind;
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
		return JacksonUtils.asJson(this);
	}
	public boolean isPublicized() {
		return publicized;
	}
	public void setPublicized(boolean publicized) {
		this.publicized = publicized;
	}
	/**
	 * @return the id
	 */
	public ObjectId getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(ObjectId id) {
		this.id = id;
	}
}
