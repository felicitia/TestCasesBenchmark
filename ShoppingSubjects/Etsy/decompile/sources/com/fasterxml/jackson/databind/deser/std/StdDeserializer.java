package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public abstract class StdDeserializer<T> extends JsonDeserializer<T> implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Class<?> _valueClass;

    public JavaType getValueType() {
        return null;
    }

    protected StdDeserializer(Class<?> cls) {
        this._valueClass = cls;
    }

    protected StdDeserializer(JavaType javaType) {
        Class<?> cls;
        if (javaType == null) {
            cls = null;
        } else {
            cls = javaType.getRawClass();
        }
        this._valueClass = cls;
    }

    public Class<?> getValueClass() {
        return this._valueClass;
    }

    /* access modifiers changed from: protected */
    public boolean isDefaultDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return (jsonDeserializer == null || jsonDeserializer.getClass().getAnnotation(JacksonStdImpl.class) == null) ? false : true;
    }

    /* access modifiers changed from: protected */
    public boolean isDefaultKeyDeserializer(KeyDeserializer keyDeserializer) {
        return (keyDeserializer == null || keyDeserializer.getClass().getAnnotation(JacksonStdImpl.class) == null) ? false : true;
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    /* access modifiers changed from: protected */
    public final boolean _parseBooleanPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        boolean z = true;
        if (currentToken == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (currentToken == JsonToken.VALUE_FALSE || currentToken == JsonToken.VALUE_NULL) {
            return false;
        }
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            if (jsonParser.getNumberType() != NumberType.INT) {
                return _parseBooleanFromNumber(jsonParser, deserializationContext);
            }
            if (jsonParser.getIntValue() == 0) {
                z = false;
            }
            return z;
        } else if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if ("true".equals(trim)) {
                return true;
            }
            if ("false".equals(trim) || trim.length() == 0) {
                return Boolean.FALSE.booleanValue();
            }
            throw deserializationContext.weirdStringException(trim, this._valueClass, "only \"true\" or \"false\" recognized");
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* access modifiers changed from: protected */
    public final Boolean _parseBoolean(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
        }
        if (currentToken == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
        }
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            if (jsonParser.getNumberType() != NumberType.INT) {
                return Boolean.valueOf(_parseBooleanFromNumber(jsonParser, deserializationContext));
            }
            return jsonParser.getIntValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Boolean) getNullValue();
        } else {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if ("true".equals(trim)) {
                    return Boolean.TRUE;
                }
                if ("false".equals(trim)) {
                    return Boolean.FALSE;
                }
                if (trim.length() == 0) {
                    return (Boolean) getEmptyValue();
                }
                throw deserializationContext.weirdStringException(trim, this._valueClass, "only \"true\" or \"false\" recognized");
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean _parseBooleanFromNumber(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getNumberType() == NumberType.LONG) {
            return (jsonParser.getLongValue() == 0 ? Boolean.FALSE : Boolean.TRUE).booleanValue();
        }
        String text = jsonParser.getText();
        if ("0.0".equals(text) || "0".equals(text)) {
            return Boolean.FALSE.booleanValue();
        }
        return Boolean.TRUE.booleanValue();
    }

    /* access modifiers changed from: protected */
    public Byte _parseByte(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Byte.valueOf(jsonParser.getByteValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            try {
                if (trim.length() == 0) {
                    return (Byte) getEmptyValue();
                }
                int parseInt = NumberInput.parseInt(trim);
                if (parseInt >= -128 && parseInt <= 255) {
                    return Byte.valueOf((byte) parseInt);
                }
                throw deserializationContext.weirdStringException(trim, this._valueClass, "overflow, value can not be represented as 8-bit value");
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid Byte value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Byte) getNullValue();
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* access modifiers changed from: protected */
    public Short _parseShort(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Short.valueOf(jsonParser.getShortValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            try {
                if (trim.length() == 0) {
                    return (Short) getEmptyValue();
                }
                int parseInt = NumberInput.parseInt(trim);
                if (parseInt >= -32768 && parseInt <= 32767) {
                    return Short.valueOf((short) parseInt);
                }
                throw deserializationContext.weirdStringException(trim, this._valueClass, "overflow, value can not be represented as 16-bit value");
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid Short value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Short) getNullValue();
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* access modifiers changed from: protected */
    public final short _parseShortPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
        if (_parseIntPrimitive >= -32768 && _parseIntPrimitive <= 32767) {
            return (short) _parseIntPrimitive;
        }
        throw deserializationContext.weirdStringException(String.valueOf(_parseIntPrimitive), this._valueClass, "overflow, value can not be represented as 16-bit value");
    }

    /* access modifiers changed from: protected */
    public final int _parseIntPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getIntValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            try {
                int length = trim.length();
                if (length > 9) {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= -2147483648L) {
                        if (parseLong <= 2147483647L) {
                            return (int) parseLong;
                        }
                    }
                    Class<?> cls = this._valueClass;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Overflow: numeric value (");
                    sb.append(trim);
                    sb.append(") out of range of int (");
                    sb.append(Integer.MIN_VALUE);
                    sb.append(" - ");
                    sb.append(Integer.MAX_VALUE);
                    sb.append(")");
                    throw deserializationContext.weirdStringException(trim, cls, sb.toString());
                } else if (length == 0) {
                    return 0;
                } else {
                    return NumberInput.parseInt(trim);
                }
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid int value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0;
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* access modifiers changed from: protected */
    public final Integer _parseInteger(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Integer.valueOf(jsonParser.getIntValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            try {
                int length = trim.length();
                if (length > 9) {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= -2147483648L) {
                        if (parseLong <= 2147483647L) {
                            return Integer.valueOf((int) parseLong);
                        }
                    }
                    Class<?> cls = this._valueClass;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Overflow: numeric value (");
                    sb.append(trim);
                    sb.append(") out of range of Integer (");
                    sb.append(Integer.MIN_VALUE);
                    sb.append(" - ");
                    sb.append(Integer.MAX_VALUE);
                    sb.append(")");
                    throw deserializationContext.weirdStringException(trim, cls, sb.toString());
                } else if (length == 0) {
                    return (Integer) getEmptyValue();
                } else {
                    return Integer.valueOf(NumberInput.parseInt(trim));
                }
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid Integer value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Integer) getNullValue();
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* access modifiers changed from: protected */
    public final Long _parseLong(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Long.valueOf(jsonParser.getLongValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return (Long) getEmptyValue();
            }
            try {
                return Long.valueOf(NumberInput.parseLong(trim));
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid Long value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Long) getNullValue();
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* access modifiers changed from: protected */
    public final long _parseLongPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getLongValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return 0;
            }
            try {
                return NumberInput.parseLong(trim);
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid long value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0;
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* access modifiers changed from: protected */
    public final Float _parseFloat(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Float.valueOf(jsonParser.getFloatValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return (Float) getEmptyValue();
            }
            char charAt = trim.charAt(0);
            if (charAt != '-') {
                if (charAt != 'I') {
                    if (charAt == 'N' && "NaN".equals(trim)) {
                        return Float.valueOf(Float.NaN);
                    }
                } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                    return Float.valueOf(Float.POSITIVE_INFINITY);
                }
            } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                return Float.valueOf(Float.NEGATIVE_INFINITY);
            }
            try {
                return Float.valueOf(Float.parseFloat(trim));
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid Float value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Float) getNullValue();
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* access modifiers changed from: protected */
    public final float _parseFloatPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getFloatValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return 0.0f;
            }
            char charAt = trim.charAt(0);
            if (charAt != '-') {
                if (charAt != 'I') {
                    if (charAt == 'N' && "NaN".equals(trim)) {
                        return Float.NaN;
                    }
                } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                    return Float.POSITIVE_INFINITY;
                }
            } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                return Float.NEGATIVE_INFINITY;
            }
            try {
                return Float.parseFloat(trim);
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid float value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0.0f;
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* access modifiers changed from: protected */
    public final Double _parseDouble(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Double.valueOf(jsonParser.getDoubleValue());
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return (Double) getEmptyValue();
            }
            char charAt = trim.charAt(0);
            if (charAt != '-') {
                if (charAt != 'I') {
                    if (charAt == 'N' && "NaN".equals(trim)) {
                        return Double.valueOf(Double.NaN);
                    }
                } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                    return Double.valueOf(Double.POSITIVE_INFINITY);
                }
            } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            }
            try {
                return Double.valueOf(parseDouble(trim));
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid Double value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Double) getNullValue();
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* access modifiers changed from: protected */
    public final double _parseDoublePrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getDoubleValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return 0.0d;
            }
            char charAt = trim.charAt(0);
            if (charAt != '-') {
                if (charAt != 'I') {
                    if (charAt == 'N' && "NaN".equals(trim)) {
                        return Double.NaN;
                    }
                } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                    return Double.POSITIVE_INFINITY;
                }
            } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                return Double.NEGATIVE_INFINITY;
            }
            try {
                return parseDouble(trim);
            } catch (IllegalArgumentException unused) {
                throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid double value");
            }
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return 0.0d;
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    /* access modifiers changed from: protected */
    public Date _parseDate(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String str;
        IllegalArgumentException e;
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            return new Date(jsonParser.getLongValue());
        }
        if (currentToken == JsonToken.VALUE_NULL) {
            return (Date) getNullValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            try {
                str = jsonParser.getText().trim();
                try {
                    if (str.length() == 0) {
                        return (Date) getEmptyValue();
                    }
                    return deserializationContext.parseDate(str);
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    Class<?> cls = this._valueClass;
                    StringBuilder sb = new StringBuilder();
                    sb.append("not a valid representation (error: ");
                    sb.append(e.getMessage());
                    sb.append(")");
                    throw deserializationContext.weirdStringException(str, cls, sb.toString());
                }
            } catch (IllegalArgumentException e3) {
                e = e3;
                str = null;
                Class<?> cls2 = this._valueClass;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("not a valid representation (error: ");
                sb2.append(e.getMessage());
                sb2.append(")");
                throw deserializationContext.weirdStringException(str, cls2, sb2.toString());
            }
        } else {
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected static final double parseDouble(String str) throws NumberFormatException {
        if (NumberInput.NASTY_SMALL_DOUBLE.equals(str)) {
            return Double.MIN_VALUE;
        }
        return Double.parseDouble(str);
    }

    /* access modifiers changed from: protected */
    public final String _parseString(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String valueAsString = jsonParser.getValueAsString();
        if (valueAsString != null) {
            return valueAsString;
        }
        throw deserializationContext.mappingException(String.class, jsonParser.getCurrentToken());
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> findDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return deserializationContext.findContextualValueDeserializer(javaType, beanProperty);
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> findConvertingContentDeserializer(DeserializationContext deserializationContext, BeanProperty beanProperty, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (!(annotationIntrospector == null || beanProperty == null)) {
            Object findDeserializationContentConverter = annotationIntrospector.findDeserializationContentConverter(beanProperty.getMember());
            if (findDeserializationContentConverter != null) {
                Converter converterInstance = deserializationContext.converterInstance(beanProperty.getMember(), findDeserializationContentConverter);
                JavaType inputType = converterInstance.getInputType(deserializationContext.getTypeFactory());
                if (jsonDeserializer == null) {
                    jsonDeserializer = deserializationContext.findContextualValueDeserializer(inputType, beanProperty);
                }
                return new StdDelegatingDeserializer(converterInstance, inputType, jsonDeserializer);
            }
        }
        return jsonDeserializer;
    }

    /* access modifiers changed from: protected */
    public void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException, JsonProcessingException {
        if (obj == null) {
            obj = getValueClass();
        }
        if (!deserializationContext.handleUnknownProperty(jsonParser, this, obj, str)) {
            deserializationContext.reportUnknownProperty(obj, str, this);
            jsonParser.skipChildren();
        }
    }
}
