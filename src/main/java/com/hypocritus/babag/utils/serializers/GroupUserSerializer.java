package com.hypocritus.babag.utils.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hypocritus.babag.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GroupUserSerializer extends StdSerializer<List<User>> {

    public GroupUserSerializer() {
        this(null);
    }

    protected GroupUserSerializer(Class<List<User>> t) {
        super(t);
    }

    @Override
    public void serialize(List<User> users, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        for(User user: users) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("userId", user.getUserId());
            jsonGenerator.writeStringField("userName", user.getUsername());
            jsonGenerator.writeEndObject();
        }
    }
}
