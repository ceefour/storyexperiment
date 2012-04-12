/**
 * 
 */
package com.hendyirawan.storyexperiment.dozer;

import java.util.Date;

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
		if (DateTime.class.isAssignableFrom(sourceClass)) {
			// Source is DateTime
			if (Date.class.isAssignableFrom(destinationClass)) {
				return ((DateTime)sourceFieldValue).toDate();
			} else {
				return ((DateTime)sourceFieldValue).toString();
			}
		} else {
			// Source is something else, either Date or String
			// and want to convert to DateTime
			return new DateTime(sourceFieldValue);
		}
	}

}
