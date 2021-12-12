package com.scaj.utils;

import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ObjectIdListSerializer extends JsonSerializer<List<ObjectId>> {

	@Override
	public void serialize(List<ObjectId> value, JsonGenerator jgen, SerializerProvider provider)
	        throws IOException, JsonProcessingException {
	
	    if (value.isEmpty()) {
	        jgen.writeStartArray();
	        jgen.writeEndArray();
	        return;
	    }
	
	    jgen.writeStartArray();
	    for (ObjectId fooValue2 : value) {
	        jgen.writeString(fooValue2.toString());
	    }
	    jgen.writeEndArray();
	}
}
