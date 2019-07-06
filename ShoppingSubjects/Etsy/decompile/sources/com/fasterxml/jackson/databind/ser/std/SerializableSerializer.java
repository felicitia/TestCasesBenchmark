package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@JacksonStdImpl
public class SerializableSerializer extends StdSerializer<JsonSerializable> {
    private static final AtomicReference<ObjectMapper> _mapperReference = new AtomicReference<>();
    public static final SerializableSerializer instance = new SerializableSerializer();

    protected SerializableSerializer() {
        super(JsonSerializable.class);
    }

    public void serialize(JsonSerializable jsonSerializable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonSerializable.serialize(jsonGenerator, serializerProvider);
    }

    public final void serializeWithType(JsonSerializable jsonSerializable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        jsonSerializable.serializeWithType(jsonGenerator, serializerProvider, typeSerializer);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonNode getSchema(com.fasterxml.jackson.databind.SerializerProvider r6, java.lang.reflect.Type r7) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r5 = this;
            com.fasterxml.jackson.databind.node.ObjectNode r6 = r5.createObjectNode()
            java.lang.String r0 = "any"
            r1 = 0
            if (r7 == 0) goto L_0x0044
            java.lang.Class r7 = com.fasterxml.jackson.databind.type.TypeFactory.rawClass(r7)
            java.lang.Class<com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema> r2 = com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema.class
            boolean r2 = r7.isAnnotationPresent(r2)
            if (r2 == 0) goto L_0x0044
            java.lang.Class<com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema> r0 = com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema.class
            java.lang.annotation.Annotation r7 = r7.getAnnotation(r0)
            com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema r7 = (com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema) r7
            java.lang.String r0 = r7.schemaType()
            java.lang.String r2 = "##irrelevant"
            java.lang.String r3 = r7.schemaObjectPropertiesDefinition()
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0032
            java.lang.String r2 = r7.schemaObjectPropertiesDefinition()
            goto L_0x0033
        L_0x0032:
            r2 = r1
        L_0x0033:
            java.lang.String r3 = "##irrelevant"
            java.lang.String r4 = r7.schemaItemDefinition()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0045
            java.lang.String r1 = r7.schemaItemDefinition()
            goto L_0x0045
        L_0x0044:
            r2 = r1
        L_0x0045:
            java.lang.String r7 = "type"
            r6.put(r7, r0)
            if (r2 == 0) goto L_0x0062
            java.lang.String r7 = "properties"
            com.fasterxml.jackson.databind.ObjectMapper r0 = _getObjectMapper()     // Catch:{ IOException -> 0x005a }
            com.fasterxml.jackson.databind.JsonNode r0 = r0.readTree(r2)     // Catch:{ IOException -> 0x005a }
            r6.put(r7, r0)     // Catch:{ IOException -> 0x005a }
            goto L_0x0062
        L_0x005a:
            com.fasterxml.jackson.databind.JsonMappingException r6 = new com.fasterxml.jackson.databind.JsonMappingException
            java.lang.String r7 = "Failed to parse @JsonSerializableSchema.schemaObjectPropertiesDefinition value"
            r6.<init>(r7)
            throw r6
        L_0x0062:
            if (r1 == 0) goto L_0x007a
            java.lang.String r7 = "items"
            com.fasterxml.jackson.databind.ObjectMapper r0 = _getObjectMapper()     // Catch:{ IOException -> 0x0072 }
            com.fasterxml.jackson.databind.JsonNode r0 = r0.readTree(r1)     // Catch:{ IOException -> 0x0072 }
            r6.put(r7, r0)     // Catch:{ IOException -> 0x0072 }
            goto L_0x007a
        L_0x0072:
            com.fasterxml.jackson.databind.JsonMappingException r6 = new com.fasterxml.jackson.databind.JsonMappingException
            java.lang.String r7 = "Failed to parse @JsonSerializableSchema.schemaItemDefinition value"
            r6.<init>(r7)
            throw r6
        L_0x007a:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.SerializableSerializer.getSchema(com.fasterxml.jackson.databind.SerializerProvider, java.lang.reflect.Type):com.fasterxml.jackson.databind.JsonNode");
    }

    private static final synchronized ObjectMapper _getObjectMapper() {
        ObjectMapper objectMapper;
        synchronized (SerializableSerializer.class) {
            objectMapper = (ObjectMapper) _mapperReference.get();
            if (objectMapper == null) {
                objectMapper = new ObjectMapper();
                _mapperReference.set(objectMapper);
            }
        }
        return objectMapper;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        jsonFormatVisitorWrapper.expectAnyFormat(javaType);
    }
}
