/**
 * 
 */
package com.hendyirawan.storyexperiment.dozer;

import org.dozer.CustomConverter;
import org.joda.time.DateTime;

/**
 * Converts between String and {@link DateTime}.
 * @author ceefour
 */
public class DateTimeConverter implements CustomConverter {

	/* (non-Javadoc)
	 * @see org.dozer.CustomConverter#convert(java.lang.Object, java.lang.Object, java.lang.Class, java.lang.Class)
	 */
	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		if (sourceClass == DateTime.class) {
			return ((DateTime)sourceFieldValue).toString();
		} else {
			return new DateTime(sourceFieldValue);
		}
	}

}
