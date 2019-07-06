package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.util.Annotations;

public class PropertyBuilder {
    protected final AnnotationIntrospector _annotationIntrospector = this._config.getAnnotationIntrospector();
    protected final BeanDescription _beanDesc;
    protected final SerializationConfig _config;
    protected Object _defaultBean;
    protected final Include _outputProps;

    public PropertyBuilder(SerializationConfig serializationConfig, BeanDescription beanDescription) {
        this._config = serializationConfig;
        this._beanDesc = beanDescription;
        this._outputProps = beanDescription.findSerializationInclusion(serializationConfig.getSerializationInclusion());
    }

    public Annotations getClassAnnotations() {
        return this._beanDesc.getClassAnnotations();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0075, code lost:
        if (r5.isContainerType() == false) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007f, code lost:
        if (r0._config.isEnabled(com.fasterxml.jackson.databind.SerializationFeature.WRITE_EMPTY_JSON_ARRAYS) != false) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0081, code lost:
        r1 = com.fasterxml.jackson.databind.ser.BeanPropertyWriter.MARKER_FOR_EMPTY;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0086, code lost:
        r10 = r1;
        r9 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.ser.BeanPropertyWriter buildWriter(com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition r14, com.fasterxml.jackson.databind.JavaType r15, com.fasterxml.jackson.databind.JsonSerializer<?> r16, com.fasterxml.jackson.databind.jsontype.TypeSerializer r17, com.fasterxml.jackson.databind.jsontype.TypeSerializer r18, com.fasterxml.jackson.databind.introspect.AnnotatedMember r19, boolean r20) {
        /*
            r13 = this;
            r0 = r13
            r1 = r18
            r11 = r19
            r5 = r15
            r2 = r20
            com.fasterxml.jackson.databind.JavaType r2 = r0.findSerializationType(r11, r2, r5)
            if (r1 == 0) goto L_0x0056
            if (r2 != 0) goto L_0x0011
            r2 = r5
        L_0x0011:
            com.fasterxml.jackson.databind.JavaType r3 = r2.getContentType()
            if (r3 != 0) goto L_0x004d
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Problem trying to create BeanPropertyWriter for property '"
            r3.append(r4)
            java.lang.String r4 = r14.getName()
            r3.append(r4)
            java.lang.String r4 = "' (of type "
            r3.append(r4)
            com.fasterxml.jackson.databind.BeanDescription r4 = r0._beanDesc
            com.fasterxml.jackson.databind.JavaType r4 = r4.getType()
            r3.append(r4)
            java.lang.String r4 = "); serialization type "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = " has no content"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2)
            throw r1
        L_0x004d:
            com.fasterxml.jackson.databind.JavaType r1 = r2.withContentTypeHandler(r1)
            r1.getContentType()
            r8 = r1
            goto L_0x0057
        L_0x0056:
            r8 = r2
        L_0x0057:
            r1 = 0
            r2 = 0
            com.fasterxml.jackson.databind.AnnotationIntrospector r3 = r0._annotationIntrospector
            com.fasterxml.jackson.annotation.JsonInclude$Include r4 = r0._outputProps
            com.fasterxml.jackson.annotation.JsonInclude$Include r3 = r3.findSerializationInclusion(r11, r4)
            r4 = 1
            if (r3 == 0) goto L_0x00a2
            int[] r6 = com.fasterxml.jackson.databind.ser.PropertyBuilder.AnonymousClass1.a
            int r3 = r3.ordinal()
            r3 = r6[r3]
            switch(r3) {
                case 1: goto L_0x0089;
                case 2: goto L_0x0084;
                case 3: goto L_0x0070;
                case 4: goto L_0x0071;
                default: goto L_0x006f;
            }
        L_0x006f:
            goto L_0x00a2
        L_0x0070:
            r2 = r4
        L_0x0071:
            boolean r3 = r5.isContainerType()
            if (r3 == 0) goto L_0x00a2
            com.fasterxml.jackson.databind.SerializationConfig r3 = r0._config
            com.fasterxml.jackson.databind.SerializationFeature r4 = com.fasterxml.jackson.databind.SerializationFeature.WRITE_EMPTY_JSON_ARRAYS
            boolean r3 = r3.isEnabled(r4)
            if (r3 != 0) goto L_0x00a2
            java.lang.Object r1 = com.fasterxml.jackson.databind.ser.BeanPropertyWriter.MARKER_FOR_EMPTY
            goto L_0x00a2
        L_0x0084:
            java.lang.Object r1 = com.fasterxml.jackson.databind.ser.BeanPropertyWriter.MARKER_FOR_EMPTY
        L_0x0086:
            r10 = r1
            r9 = r4
            goto L_0x00a4
        L_0x0089:
            java.lang.String r1 = r14.getName()
            java.lang.Object r1 = r0.getDefaultValue(r1, r11)
            if (r1 != 0) goto L_0x0094
            goto L_0x0086
        L_0x0094:
            java.lang.Class r3 = r1.getClass()
            boolean r3 = r3.isArray()
            if (r3 == 0) goto L_0x00a2
            java.lang.Object r1 = com.fasterxml.jackson.databind.util.ArrayBuilders.getArrayComparator(r1)
        L_0x00a2:
            r10 = r1
            r9 = r2
        L_0x00a4:
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter r12 = new com.fasterxml.jackson.databind.ser.BeanPropertyWriter
            com.fasterxml.jackson.databind.BeanDescription r1 = r0._beanDesc
            com.fasterxml.jackson.databind.util.Annotations r4 = r1.getClassAnnotations()
            r1 = r12
            r2 = r14
            r3 = r11
            r6 = r16
            r7 = r17
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r0._annotationIntrospector
            com.fasterxml.jackson.databind.util.NameTransformer r1 = r1.findUnwrappingNameTransformer(r11)
            if (r1 == 0) goto L_0x00c2
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter r12 = r12.unwrappingWriter(r1)
        L_0x00c2:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.PropertyBuilder.buildWriter(com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition, com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.introspect.AnnotatedMember, boolean):com.fasterxml.jackson.databind.ser.BeanPropertyWriter");
    }

    /* access modifiers changed from: protected */
    public JavaType findSerializationType(Annotated annotated, boolean z, JavaType javaType) {
        JavaType constructSpecializedType;
        Class findSerializationType = this._annotationIntrospector.findSerializationType(annotated);
        boolean z2 = true;
        if (findSerializationType != null) {
            Class rawClass = javaType.getRawClass();
            if (findSerializationType.isAssignableFrom(rawClass)) {
                constructSpecializedType = javaType.widenBy(findSerializationType);
            } else if (!rawClass.isAssignableFrom(findSerializationType)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Illegal concrete-type annotation for method '");
                sb.append(annotated.getName());
                sb.append("': class ");
                sb.append(findSerializationType.getName());
                sb.append(" not a super-type of (declared) class ");
                sb.append(rawClass.getName());
                throw new IllegalArgumentException(sb.toString());
            } else {
                constructSpecializedType = this._config.constructSpecializedType(javaType, findSerializationType);
            }
            javaType = constructSpecializedType;
            z = true;
        }
        JavaType modifySecondaryTypesByAnnotation = BeanSerializerFactory.modifySecondaryTypesByAnnotation(this._config, annotated, javaType);
        if (modifySecondaryTypesByAnnotation != javaType) {
            javaType = modifySecondaryTypesByAnnotation;
            z = true;
        }
        if (!z) {
            Typing findSerializationTyping = this._annotationIntrospector.findSerializationTyping(annotated);
            if (findSerializationTyping != null) {
                if (findSerializationTyping != Typing.STATIC) {
                    z2 = false;
                }
                z = z2;
            }
        }
        if (z) {
            return javaType;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Object getDefaultBean() {
        if (this._defaultBean == null) {
            this._defaultBean = this._beanDesc.instantiateBean(this._config.canOverrideAccessModifiers());
            if (this._defaultBean == null) {
                Class annotated = this._beanDesc.getClassInfo().getAnnotated();
                StringBuilder sb = new StringBuilder();
                sb.append("Class ");
                sb.append(annotated.getName());
                sb.append(" has no default constructor; can not instantiate default bean value to support 'properties=JsonSerialize.Inclusion.NON_DEFAULT' annotation");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return this._defaultBean;
    }

    /* access modifiers changed from: protected */
    public Object getDefaultValue(String str, AnnotatedMember annotatedMember) {
        Object defaultBean = getDefaultBean();
        try {
            return annotatedMember.getValue(defaultBean);
        } catch (Exception e) {
            return _throwWrapped(e, str, defaultBean);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.lang.Exception, code=java.lang.Throwable, for r3v0, types: [java.lang.Throwable, java.lang.Exception] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object _throwWrapped(java.lang.Throwable r3, java.lang.String r4, java.lang.Object r5) {
        /*
            r2 = this;
        L_0x0000:
            java.lang.Throwable r0 = r3.getCause()
            if (r0 == 0) goto L_0x000b
            java.lang.Throwable r3 = r3.getCause()
            goto L_0x0000
        L_0x000b:
            boolean r0 = r3 instanceof java.lang.Error
            if (r0 == 0) goto L_0x0012
            java.lang.Error r3 = (java.lang.Error) r3
            throw r3
        L_0x0012:
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 == 0) goto L_0x0019
            java.lang.RuntimeException r3 = (java.lang.RuntimeException) r3
            throw r3
        L_0x0019:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Failed to get property '"
            r0.append(r1)
            r0.append(r4)
            java.lang.String r4 = "' of default "
            r0.append(r4)
            java.lang.Class r4 = r5.getClass()
            java.lang.String r4 = r4.getName()
            r0.append(r4)
            java.lang.String r4 = " instance"
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.PropertyBuilder._throwWrapped(java.lang.Exception, java.lang.String, java.lang.Object):java.lang.Object");
    }
}
