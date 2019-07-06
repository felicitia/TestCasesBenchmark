package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.lang.reflect.Method;

public class EnumDeserializer extends StdScalarDeserializer<Enum<?>> {
    private static final long serialVersionUID = -5893263645879532318L;
    protected final EnumResolver<?> _resolver;

    protected static class FactoryBasedDeserializer extends StdScalarDeserializer<Object> {
        private static final long serialVersionUID = -7775129435872564122L;
        protected final Class<?> _enumClass;
        protected final Method _factory;
        protected final Class<?> _inputType;

        public FactoryBasedDeserializer(Class<?> cls, AnnotatedMethod annotatedMethod, Class<?> cls2) {
            super(Enum.class);
            this._enumClass = cls;
            this._factory = annotatedMethod.getAnnotated();
            this._inputType = cls2;
        }

        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            Object obj;
            if (this._inputType == null) {
                obj = jsonParser.getText();
            } else if (this._inputType == Integer.class) {
                obj = Integer.valueOf(jsonParser.getValueAsInt());
            } else if (this._inputType == Long.class) {
                obj = Long.valueOf(jsonParser.getValueAsLong());
            } else {
                throw deserializationContext.mappingException(this._enumClass);
            }
            try {
                return this._factory.invoke(this._enumClass, new Object[]{obj});
            } catch (Exception e) {
                ClassUtil.unwrapAndThrowAsIAE(e);
                return null;
            }
        }
    }

    public boolean isCachable() {
        return true;
    }

    public EnumDeserializer(EnumResolver<?> enumResolver) {
        super(Enum.class);
        this._resolver = enumResolver;
    }

    public static JsonDeserializer<?> deserializerForCreator(DeserializationConfig deserializationConfig, Class<?> cls, AnnotatedMethod annotatedMethod) {
        Class cls2;
        Class<Long> rawParameterType = annotatedMethod.getRawParameterType(0);
        if (rawParameterType == String.class) {
            cls2 = null;
        } else if (rawParameterType == Integer.TYPE || rawParameterType == Integer.class) {
            cls2 = Integer.class;
        } else if (rawParameterType == Long.TYPE || rawParameterType == Long.class) {
            cls2 = Long.class;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Parameter #0 type for factory method (");
            sb.append(annotatedMethod);
            sb.append(") not suitable, must be java.lang.String or int/Integer/long/Long");
            throw new IllegalArgumentException(sb.toString());
        }
        if (deserializationConfig.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(annotatedMethod.getMember());
        }
        return new FactoryBasedDeserializer(cls, annotatedMethod, cls2);
    }

    public Enum<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING || currentToken == JsonToken.FIELD_NAME) {
            String text = jsonParser.getText();
            Enum<?> findEnum = this._resolver.findEnum(text);
            if (findEnum == null) {
                if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && (text.length() == 0 || text.trim().length() == 0)) {
                    return null;
                }
                if (!deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    throw deserializationContext.weirdStringException(text, this._resolver.getEnumClass(), "value not one of declared Enum instance names");
                }
            }
            return findEnum;
        } else if (currentToken != JsonToken.VALUE_NUMBER_INT) {
            throw deserializationContext.mappingException(this._resolver.getEnumClass());
        } else if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
            throw deserializationContext.mappingException("Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)");
        } else {
            int intValue = jsonParser.getIntValue();
            Enum<?> enumR = this._resolver.getEnum(intValue);
            if (enumR != null || deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return enumR;
            }
            Integer valueOf = Integer.valueOf(intValue);
            Class enumClass = this._resolver.getEnumClass();
            StringBuilder sb = new StringBuilder();
            sb.append("index value outside legal index range [0..");
            sb.append(this._resolver.lastValidIndex());
            sb.append("]");
            throw deserializationContext.weirdNumberException(valueOf, enumClass, sb.toString());
        }
    }
}
