package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.EnumMap;

public class EnumMapDeserializer extends StdDeserializer<EnumMap<?, ?>> implements ContextualDeserializer {
    private static final long serialVersionUID = 1518773374647478964L;
    protected final Class<?> _enumClass;
    protected JsonDeserializer<Enum<?>> _keyDeserializer;
    protected final JavaType _mapType;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    public boolean isCachable() {
        return true;
    }

    @Deprecated
    public EnumMapDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2) {
        this(javaType, jsonDeserializer, jsonDeserializer2, null);
    }

    public EnumMapDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, TypeDeserializer typeDeserializer) {
        super(EnumMap.class);
        this._mapType = javaType;
        this._enumClass = javaType.getKeyType().getRawClass();
        this._keyDeserializer = jsonDeserializer;
        this._valueDeserializer = jsonDeserializer2;
        this._valueTypeDeserializer = typeDeserializer;
    }

    @Deprecated
    public EnumMapDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2) {
        return withResolved(jsonDeserializer, jsonDeserializer2, null);
    }

    public EnumMapDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, TypeDeserializer typeDeserializer) {
        if (jsonDeserializer == this._keyDeserializer && jsonDeserializer2 == this._valueDeserializer && typeDeserializer == this._valueTypeDeserializer) {
            return this;
        }
        return new EnumMapDeserializer(this._mapType, jsonDeserializer, jsonDeserializer2, this._valueTypeDeserializer);
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<Enum<?>> jsonDeserializer = this._keyDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializationContext.findContextualValueDeserializer(this._mapType.getKeyType(), beanProperty);
        }
        JsonDeserializer<Object> jsonDeserializer2 = this._valueDeserializer;
        if (jsonDeserializer2 == null) {
            jsonDeserializer2 = deserializationContext.findContextualValueDeserializer(this._mapType.getContentType(), beanProperty);
        } else if (jsonDeserializer2 instanceof ContextualDeserializer) {
            jsonDeserializer2 = ((ContextualDeserializer) jsonDeserializer2).createContextual(deserializationContext, beanProperty);
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer != null) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty);
        }
        return withResolved(jsonDeserializer, jsonDeserializer2, typeDeserializer);
    }

    /* JADX WARNING: type inference failed for: r4v1 */
    /* JADX WARNING: type inference failed for: r4v2, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v3, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v4, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v5, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r8v3, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r4v6 */
    /* JADX WARNING: type inference failed for: r4v7 */
    /* JADX WARNING: type inference failed for: r4v8 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r4v1
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], ?[OBJECT, ARRAY], java.lang.Object]
      uses: [java.lang.Object, java.lang.String]
      mth insns count: 41
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$38/2083670723.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.EnumMap<?, ?> deserialize(com.fasterxml.jackson.core.JsonParser r8, com.fasterxml.jackson.databind.DeserializationContext r9) throws java.io.IOException, com.fasterxml.jackson.core.JsonProcessingException {
        /*
            r7 = this;
            com.fasterxml.jackson.core.JsonToken r0 = r8.getCurrentToken()
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.START_OBJECT
            if (r0 == r1) goto L_0x000f
            java.lang.Class<java.util.EnumMap> r8 = java.util.EnumMap.class
            com.fasterxml.jackson.databind.JsonMappingException r8 = r9.mappingException(r8)
            throw r8
        L_0x000f:
            java.util.EnumMap r0 = r7.constructMap()
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r1 = r7._valueDeserializer
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r2 = r7._valueTypeDeserializer
        L_0x0017:
            com.fasterxml.jackson.core.JsonToken r3 = r8.nextToken()
            com.fasterxml.jackson.core.JsonToken r4 = com.fasterxml.jackson.core.JsonToken.END_OBJECT
            if (r3 == r4) goto L_0x0065
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Enum<?>> r3 = r7._keyDeserializer
            java.lang.Object r3 = r3.deserialize(r8, r9)
            java.lang.Enum r3 = (java.lang.Enum) r3
            r4 = 0
            if (r3 != 0) goto L_0x004d
            com.fasterxml.jackson.databind.DeserializationFeature r3 = com.fasterxml.jackson.databind.DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL
            boolean r3 = r9.isEnabled(r3)
            if (r3 != 0) goto L_0x0046
            boolean r0 = r8.hasCurrentToken()     // Catch:{ Exception -> 0x003d }
            if (r0 == 0) goto L_0x003d
            java.lang.String r8 = r8.getText()     // Catch:{ Exception -> 0x003d }
            r4 = r8
        L_0x003d:
            java.lang.Class<?> r8 = r7._enumClass
            java.lang.String r0 = "value not one of declared Enum instance names"
            com.fasterxml.jackson.databind.JsonMappingException r8 = r9.weirdStringException(r4, r8, r0)
            throw r8
        L_0x0046:
            r8.nextToken()
            r8.skipChildren()
            goto L_0x0017
        L_0x004d:
            com.fasterxml.jackson.core.JsonToken r5 = r8.nextToken()
            com.fasterxml.jackson.core.JsonToken r6 = com.fasterxml.jackson.core.JsonToken.VALUE_NULL
            if (r5 != r6) goto L_0x0056
            goto L_0x0061
        L_0x0056:
            if (r2 != 0) goto L_0x005d
            java.lang.Object r4 = r1.deserialize(r8, r9)
            goto L_0x0061
        L_0x005d:
            java.lang.Object r4 = r1.deserializeWithType(r8, r9, r2)
        L_0x0061:
            r0.put(r3, r4)
            goto L_0x0017
        L_0x0065:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer.deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext):java.util.EnumMap");
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    private EnumMap<?, ?> constructMap() {
        return new EnumMap<>(this._enumClass);
    }
}
