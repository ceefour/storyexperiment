/**
 * 
 */
package com.hendyirawan.storyexperiment.vo;

import com.hendyirawan.storyexperiment.common.JsonUtils;

/**
 * Simple reference to a cloud object within the same tenant.
 * @author ceefour
 *
 */
public class SimpleRef {

	private String id;
	private String targetKind;
	private String slug;
	private String name;
	private String photoId;
	
	public SimpleRef() {
	}
	
	public SimpleRef(String id, String targetKind, String slug, String name,
			String photoId) {
		super();
		this.id = id;
		this.targetKind = targetKind;
		this.slug = slug;
		this.name = name;
		this.photoId = photoId;
	}
	



	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the targetKind
	 */
	public String getTargetKind() {
		return targetKind;
	}
	/**
	 * @param targetKind the targetKind to set
	 */
	public void setTargetKind(String targetKind) {
		this.targetKind = targetKind;
	}
	/**
	 * @return the slug
	 */
	public String getSlug() {
		return slug;
	}
	/**
	 * @param slug the slug to set
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the photoId
	 */
	public String getPhotoId() {
		return photoId;
	}
	/**
	 * @param photoId the photoId to set
	 */
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	@Override
	public String toString() {
		return JsonUtils.asJson(this);
	}
}
