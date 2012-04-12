/**
 * 
 */
package com.hendyirawan.storyexperiment.dozer;

import org.dozer.BeanFactory;

import com.hendyirawan.storyexperiment.vo.Story;
import com.mongodb.BasicDBObject;

/**
 * Creates {@link Story} subclass objects by looking at <tt>kind</tt> field.
 * @author ceefour
 */
public class StoryBeanFactory implements BeanFactory {

	/* (non-Javadoc)
	 * @see org.dozer.BeanFactory#createBean(java.lang.Object, java.lang.Class, java.lang.String)
	 */
	@Override
	public Object createBean(Object source, Class<?> sourceClass,
			String targetBeanId) {
		BasicDBObject input = (BasicDBObject)source;
		String kind = input.getString("kind");
		try {
			Class<? extends Story> targetClass = (Class<? extends Story>) StoryDao.class.forName("com.hendyirawan.storyexperiment.vo." + kind);
			Story story = targetClass.newInstance();
			return story;
		} catch (Exception e) {
			throw new RuntimeException("Cannot create story with kind " + kind, e);
		}
	}

}
