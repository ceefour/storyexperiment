package com.hendyirawan.storyexperiment.common;

import java.io.IOException;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Serializes MongoDB {@link ObjectId} as {@link String}.
 * @author ceefour
 * @deprecated use {@link ToStringSerializer}
 */
@Deprecated
public class ObjectIdSerializer extends StdSerializer<ObjectId> {

	public ObjectIdSerializer() {
		super(ObjectId.class);
	}
	
	@Override
	public void serialize(ObjectId value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jgen.writeString(value.toString());
	}

}
