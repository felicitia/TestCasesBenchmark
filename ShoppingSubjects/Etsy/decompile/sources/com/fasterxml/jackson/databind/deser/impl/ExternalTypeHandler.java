package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExternalTypeHandler {
    private final HashMap<String, Integer> _nameToPropertyIndex;
    private final a[] _properties;
    private final TokenBuffer[] _tokens;
    private final String[] _typeIds;

    public static class Builder {
        private final HashMap<String, Integer> _nameToPropertyIndex = new HashMap<>();
        private final ArrayList<a> _properties = new ArrayList<>();

        public void addExternal(SettableBeanProperty settableBeanProperty, TypeDeserializer typeDeserializer) {
            Integer valueOf = Integer.valueOf(this._properties.size());
            this._properties.add(new a(settableBeanProperty, typeDeserializer));
            this._nameToPropertyIndex.put(settableBeanProperty.getName(), valueOf);
            this._nameToPropertyIndex.put(typeDeserializer.getPropertyName(), valueOf);
        }

        public ExternalTypeHandler build() {
            return new ExternalTypeHandler((a[]) this._properties.toArray(new a[this._properties.size()]), this._nameToPropertyIndex, null, null);
        }
    }

    private static final class a {
        private final SettableBeanProperty a;
        private final TypeDeserializer b;
        private final String c;

        public a(SettableBeanProperty settableBeanProperty, TypeDeserializer typeDeserializer) {
            this.a = settableBeanProperty;
            this.b = typeDeserializer;
            this.c = typeDeserializer.getPropertyName();
        }

        public boolean a(String str) {
            return str.equals(this.c);
        }

        public boolean a() {
            return this.b.getDefaultImpl() != null;
        }

        public String b() {
            Class defaultImpl = this.b.getDefaultImpl();
            if (defaultImpl == null) {
                return null;
            }
            return this.b.getTypeIdResolver().idFromValueAndType(null, defaultImpl);
        }

        public String c() {
            return this.c;
        }

        public SettableBeanProperty d() {
            return this.a;
        }
    }

    protected ExternalTypeHandler(a[] aVarArr, HashMap<String, Integer> hashMap, String[] strArr, TokenBuffer[] tokenBufferArr) {
        this._properties = aVarArr;
        this._nameToPropertyIndex = hashMap;
        this._typeIds = strArr;
        this._tokens = tokenBufferArr;
    }

    protected ExternalTypeHandler(ExternalTypeHandler externalTypeHandler) {
        this._properties = externalTypeHandler._properties;
        this._nameToPropertyIndex = externalTypeHandler._nameToPropertyIndex;
        int length = this._properties.length;
        this._typeIds = new String[length];
        this._tokens = new TokenBuffer[length];
    }

    public ExternalTypeHandler start() {
        return new ExternalTypeHandler(this);
    }

    public boolean handleTypePropertyValue(JsonParser jsonParser, DeserializationContext deserializationContext, String str, Object obj) throws IOException, JsonProcessingException {
        Integer num = (Integer) this._nameToPropertyIndex.get(str);
        boolean z = false;
        if (num == null) {
            return false;
        }
        int intValue = num.intValue();
        if (!this._properties[intValue].a(str)) {
            return false;
        }
        String text = jsonParser.getText();
        if (!(obj == null || this._tokens[intValue] == null)) {
            z = true;
        }
        if (z) {
            _deserializeAndSet(jsonParser, deserializationContext, obj, intValue, text);
            this._tokens[intValue] = null;
        } else {
            this._typeIds[intValue] = text;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        if (r9._typeIds[r0] != null) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002c, code lost:
        if (r9._tokens[r0] != null) goto L_0x002e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean handlePropertyValue(com.fasterxml.jackson.core.JsonParser r10, com.fasterxml.jackson.databind.DeserializationContext r11, java.lang.String r12, java.lang.Object r13) throws java.io.IOException, com.fasterxml.jackson.core.JsonProcessingException {
        /*
            r9 = this;
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r9._nameToPropertyIndex
            java.lang.Object r0 = r0.get(r12)
            java.lang.Integer r0 = (java.lang.Integer) r0
            r1 = 0
            if (r0 != 0) goto L_0x000c
            return r1
        L_0x000c:
            int r0 = r0.intValue()
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$a[] r2 = r9._properties
            r2 = r2[r0]
            boolean r12 = r2.a(r12)
            r8 = 1
            if (r12 == 0) goto L_0x0030
            java.lang.String[] r12 = r9._typeIds
            java.lang.String r2 = r10.getText()
            r12[r0] = r2
            r10.skipChildren()
            if (r13 == 0) goto L_0x0049
            com.fasterxml.jackson.databind.util.TokenBuffer[] r12 = r9._tokens
            r12 = r12[r0]
            if (r12 == 0) goto L_0x0049
        L_0x002e:
            r1 = r8
            goto L_0x0049
        L_0x0030:
            com.fasterxml.jackson.databind.util.TokenBuffer r12 = new com.fasterxml.jackson.databind.util.TokenBuffer
            com.fasterxml.jackson.core.ObjectCodec r2 = r10.getCodec()
            r12.<init>(r2)
            r12.copyCurrentStructure(r10)
            com.fasterxml.jackson.databind.util.TokenBuffer[] r2 = r9._tokens
            r2[r0] = r12
            if (r13 == 0) goto L_0x0049
            java.lang.String[] r12 = r9._typeIds
            r12 = r12[r0]
            if (r12 == 0) goto L_0x0049
            goto L_0x002e
        L_0x0049:
            if (r1 == 0) goto L_0x0060
            java.lang.String[] r12 = r9._typeIds
            r7 = r12[r0]
            java.lang.String[] r12 = r9._typeIds
            r1 = 0
            r12[r0] = r1
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r13
            r6 = r0
            r2._deserializeAndSet(r3, r4, r5, r6, r7)
            com.fasterxml.jackson.databind.util.TokenBuffer[] r10 = r9._tokens
            r10[r0] = r1
        L_0x0060:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.handlePropertyValue(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext, java.lang.String, java.lang.Object):boolean");
    }

    public Object complete(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        int length = this._properties.length;
        for (int i = 0; i < length; i++) {
            String str = this._typeIds[i];
            if (str == null) {
                TokenBuffer tokenBuffer = this._tokens[i];
                if (tokenBuffer == null) {
                    continue;
                } else {
                    JsonToken firstToken = tokenBuffer.firstToken();
                    if (firstToken != null && firstToken.isScalarValue()) {
                        JsonParser asParser = tokenBuffer.asParser(jsonParser);
                        asParser.nextToken();
                        SettableBeanProperty d = this._properties[i].d();
                        Object deserializeIfNatural = TypeDeserializer.deserializeIfNatural(asParser, deserializationContext, d.getType());
                        if (deserializeIfNatural != null) {
                            d.set(obj, deserializeIfNatural);
                        } else if (!this._properties[i].a()) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Missing external type id property '");
                            sb.append(this._properties[i].c());
                            sb.append("'");
                            throw deserializationContext.mappingException(sb.toString());
                        } else {
                            str = this._properties[i].b();
                        }
                    }
                }
            } else if (this._tokens[i] == null) {
                SettableBeanProperty d2 = this._properties[i].d();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Missing property '");
                sb2.append(d2.getName());
                sb2.append("' for external type id '");
                sb2.append(this._properties[i].c());
                throw deserializationContext.mappingException(sb2.toString());
            }
            _deserializeAndSet(jsonParser, deserializationContext, obj, i, str);
        }
        return obj;
    }

    public Object complete(JsonParser jsonParser, DeserializationContext deserializationContext, PropertyValueBuffer propertyValueBuffer, PropertyBasedCreator propertyBasedCreator) throws IOException, JsonProcessingException {
        int length = this._properties.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            String str = this._typeIds[i];
            if (str == null) {
                if (this._tokens[i] == null) {
                    continue;
                } else if (!this._properties[i].a()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Missing external type id property '");
                    sb.append(this._properties[i].c());
                    sb.append("'");
                    throw deserializationContext.mappingException(sb.toString());
                } else {
                    str = this._properties[i].b();
                }
            } else if (this._tokens[i] == null) {
                SettableBeanProperty d = this._properties[i].d();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Missing property '");
                sb2.append(d.getName());
                sb2.append("' for external type id '");
                sb2.append(this._properties[i].c());
                throw deserializationContext.mappingException(sb2.toString());
            }
            objArr[i] = _deserialize(jsonParser, deserializationContext, i, str);
        }
        for (int i2 = 0; i2 < length; i2++) {
            SettableBeanProperty d2 = this._properties[i2].d();
            if (propertyBasedCreator.findCreatorProperty(d2.getName()) != null) {
                propertyValueBuffer.assignParameter(d2.getCreatorIndex(), objArr[i2]);
            }
        }
        Object build = propertyBasedCreator.build(deserializationContext, propertyValueBuffer);
        for (int i3 = 0; i3 < length; i3++) {
            SettableBeanProperty d3 = this._properties[i3].d();
            if (propertyBasedCreator.findCreatorProperty(d3.getName()) == null) {
                d3.set(build, objArr[i3]);
            }
        }
        return build;
    }

    /* access modifiers changed from: protected */
    public final Object _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, int i, String str) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
        tokenBuffer.writeStartArray();
        tokenBuffer.writeString(str);
        JsonParser asParser = this._tokens[i].asParser(jsonParser);
        asParser.nextToken();
        tokenBuffer.copyCurrentStructure(asParser);
        tokenBuffer.writeEndArray();
        JsonParser asParser2 = tokenBuffer.asParser(jsonParser);
        asParser2.nextToken();
        return this._properties[i].d().deserialize(asParser2, deserializationContext);
    }

    /* access modifiers changed from: protected */
    public final void _deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, int i, String str) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
        tokenBuffer.writeStartArray();
        tokenBuffer.writeString(str);
        JsonParser asParser = this._tokens[i].asParser(jsonParser);
        asParser.nextToken();
        tokenBuffer.copyCurrentStructure(asParser);
        tokenBuffer.writeEndArray();
        JsonParser asParser2 = tokenBuffer.asParser(jsonParser);
        asParser2.nextToken();
        this._properties[i].d().deserializeAndSet(asParser2, deserializationContext, obj);
    }
}
