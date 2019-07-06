package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public abstract class StdConverter<IN, OUT> implements Converter<IN, OUT> {
    public abstract OUT convert(IN in);

    public JavaType getInputType(TypeFactory typeFactory) {
        JavaType[] findTypeParameters = typeFactory.findTypeParameters(getClass(), Converter.class);
        if (findTypeParameters != null && findTypeParameters.length >= 2) {
            return findTypeParameters[0];
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can not find OUT type parameter for Converter of type ");
        sb.append(getClass().getName());
        throw new IllegalStateException(sb.toString());
    }

    public JavaType getOutputType(TypeFactory typeFactory) {
        JavaType[] findTypeParameters = typeFactory.findTypeParameters(getClass(), Converter.class);
        if (findTypeParameters != null && findTypeParameters.length >= 2) {
            return findTypeParameters[1];
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can not find OUT type parameter for Converter of type ");
        sb.append(getClass().getName());
        throw new IllegalStateException(sb.toString());
    }
}
