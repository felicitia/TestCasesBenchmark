package com.fasterxml.jackson.databind.ser.std;

import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.AnyGetterWriter;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class BeanSerializerBase extends StdSerializer<Object> implements JsonFormatVisitable, SchemaAware, ContextualSerializer, ResolvableSerializer {
    protected static final BeanPropertyWriter[] NO_PROPS = new BeanPropertyWriter[0];
    protected final AnyGetterWriter _anyGetterWriter;
    protected final BeanPropertyWriter[] _filteredProps;
    protected final ObjectIdWriter _objectIdWriter;
    protected final Object _propertyFilterId;
    protected final BeanPropertyWriter[] _props;
    protected final Shape _serializationShape;
    protected final AnnotatedMember _typeId;

    /* access modifiers changed from: protected */
    public abstract BeanSerializerBase asArraySerializer();

    public abstract void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException;

    /* access modifiers changed from: protected */
    public abstract BeanSerializerBase withIgnorals(String[] strArr);

    public abstract BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter);

    protected BeanSerializerBase(JavaType javaType, BeanSerializerBuilder beanSerializerBuilder, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        super(javaType);
        this._props = beanPropertyWriterArr;
        this._filteredProps = beanPropertyWriterArr2;
        Shape shape = null;
        if (beanSerializerBuilder == null) {
            this._typeId = null;
            this._anyGetterWriter = null;
            this._propertyFilterId = null;
            this._objectIdWriter = null;
            this._serializationShape = null;
            return;
        }
        this._typeId = beanSerializerBuilder.getTypeId();
        this._anyGetterWriter = beanSerializerBuilder.getAnyGetter();
        this._propertyFilterId = beanSerializerBuilder.getFilterId();
        this._objectIdWriter = beanSerializerBuilder.getObjectIdWriter();
        Value findExpectedFormat = beanSerializerBuilder.getBeanDescription().findExpectedFormat(null);
        if (findExpectedFormat != null) {
            shape = findExpectedFormat.getShape();
        }
        this._serializationShape = shape;
    }

    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        super(beanSerializerBase._handledType);
        this._props = beanPropertyWriterArr;
        this._filteredProps = beanPropertyWriterArr2;
        this._typeId = beanSerializerBase._typeId;
        this._anyGetterWriter = beanSerializerBase._anyGetterWriter;
        this._objectIdWriter = beanSerializerBase._objectIdWriter;
        this._propertyFilterId = beanSerializerBase._propertyFilterId;
        this._serializationShape = beanSerializerBase._serializationShape;
    }

    protected BeanSerializerBase(BeanSerializerBase beanSerializerBase, ObjectIdWriter objectIdWriter) {
        super(beanSerializerBase._handledType);
        this._props = beanSerializerBase._props;
        this._filteredProps = beanSerializerBase._filteredProps;
        this._typeId = beanSerializerBase._typeId;
        this._anyGetterWriter = beanSerializerBase._anyGetterWriter;
        this._objectIdWriter = objectIdWriter;
        this._propertyFilterId = beanSerializerBase._propertyFilterId;
        this._serializationShape = beanSerializerBase._serializationShape;
    }

    protected BeanSerializerBase(BeanSerializerBase beanSerializerBase, String[] strArr) {
        ArrayList arrayList;
        super(beanSerializerBase._handledType);
        HashSet arrayToSet = ArrayBuilders.arrayToSet(strArr);
        BeanPropertyWriter[] beanPropertyWriterArr = beanSerializerBase._props;
        BeanPropertyWriter[] beanPropertyWriterArr2 = beanSerializerBase._filteredProps;
        BeanPropertyWriter[] beanPropertyWriterArr3 = null;
        int length = beanPropertyWriterArr.length;
        ArrayList arrayList2 = new ArrayList(length);
        if (beanPropertyWriterArr2 == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(length);
        }
        for (int i = 0; i < length; i++) {
            BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
            if (!arrayToSet.contains(beanPropertyWriter.getName())) {
                arrayList2.add(beanPropertyWriter);
                if (beanPropertyWriterArr2 != null) {
                    arrayList.add(beanPropertyWriterArr2[i]);
                }
            }
        }
        this._props = (BeanPropertyWriter[]) arrayList2.toArray(new BeanPropertyWriter[arrayList2.size()]);
        if (arrayList != null) {
            beanPropertyWriterArr3 = (BeanPropertyWriter[]) arrayList.toArray(new BeanPropertyWriter[arrayList.size()]);
        }
        this._filteredProps = beanPropertyWriterArr3;
        this._typeId = beanSerializerBase._typeId;
        this._anyGetterWriter = beanSerializerBase._anyGetterWriter;
        this._objectIdWriter = beanSerializerBase._objectIdWriter;
        this._propertyFilterId = beanSerializerBase._propertyFilterId;
        this._serializationShape = beanSerializerBase._serializationShape;
    }

    protected BeanSerializerBase(BeanSerializerBase beanSerializerBase) {
        this(beanSerializerBase, beanSerializerBase._props, beanSerializerBase._filteredProps);
    }

    protected BeanSerializerBase(BeanSerializerBase beanSerializerBase, NameTransformer nameTransformer) {
        this(beanSerializerBase, rename(beanSerializerBase._props, nameTransformer), rename(beanSerializerBase._filteredProps, nameTransformer));
    }

    private static final BeanPropertyWriter[] rename(BeanPropertyWriter[] beanPropertyWriterArr, NameTransformer nameTransformer) {
        if (beanPropertyWriterArr == null || beanPropertyWriterArr.length == 0 || nameTransformer == null || nameTransformer == NameTransformer.NOP) {
            return beanPropertyWriterArr;
        }
        int length = beanPropertyWriterArr.length;
        BeanPropertyWriter[] beanPropertyWriterArr2 = new BeanPropertyWriter[length];
        for (int i = 0; i < length; i++) {
            BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
            if (beanPropertyWriter != null) {
                beanPropertyWriterArr2[i] = beanPropertyWriter.rename(nameTransformer);
            }
        }
        return beanPropertyWriterArr2;
    }

    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        int length = this._filteredProps == null ? 0 : this._filteredProps.length;
        int length2 = this._props.length;
        for (int i = 0; i < length2; i++) {
            BeanPropertyWriter beanPropertyWriter = this._props[i];
            if (!beanPropertyWriter.willSuppressNulls() && !beanPropertyWriter.hasNullSerializer()) {
                JsonSerializer findNullValueSerializer = serializerProvider.findNullValueSerializer(beanPropertyWriter);
                if (findNullValueSerializer != null) {
                    beanPropertyWriter.assignNullSerializer(findNullValueSerializer);
                    if (i < length) {
                        BeanPropertyWriter beanPropertyWriter2 = this._filteredProps[i];
                        if (beanPropertyWriter2 != null) {
                            beanPropertyWriter2.assignNullSerializer(findNullValueSerializer);
                        }
                    }
                }
            }
            if (!beanPropertyWriter.hasSerializer()) {
                JsonSerializer findConvertingSerializer = findConvertingSerializer(serializerProvider, beanPropertyWriter);
                if (findConvertingSerializer == null) {
                    JavaType serializationType = beanPropertyWriter.getSerializationType();
                    if (serializationType == null) {
                        serializationType = serializerProvider.constructType(beanPropertyWriter.getGenericPropertyType());
                        if (!serializationType.isFinal()) {
                            if (serializationType.isContainerType() || serializationType.containedTypeCount() > 0) {
                                beanPropertyWriter.setNonTrivialBaseType(serializationType);
                            }
                        }
                    }
                    JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(serializationType, (BeanProperty) beanPropertyWriter);
                    if (serializationType.isContainerType()) {
                        TypeSerializer typeSerializer = (TypeSerializer) serializationType.getContentType().getTypeHandler();
                        if (typeSerializer != null && (findValueSerializer instanceof ContainerSerializer)) {
                            findConvertingSerializer = ((ContainerSerializer) findValueSerializer).withValueTypeSerializer(typeSerializer);
                        }
                    }
                    findConvertingSerializer = findValueSerializer;
                }
                beanPropertyWriter.assignSerializer(findConvertingSerializer);
                if (i < length) {
                    BeanPropertyWriter beanPropertyWriter3 = this._filteredProps[i];
                    if (beanPropertyWriter3 != null) {
                        beanPropertyWriter3.assignSerializer(findConvertingSerializer);
                    }
                }
            }
        }
        if (this._anyGetterWriter != null) {
            this._anyGetterWriter.resolve(serializerProvider);
        }
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> findConvertingSerializer(SerializerProvider serializerProvider, BeanPropertyWriter beanPropertyWriter) throws JsonMappingException {
        AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
        if (annotationIntrospector != null) {
            Object findSerializationConverter = annotationIntrospector.findSerializationConverter(beanPropertyWriter.getMember());
            if (findSerializationConverter != null) {
                Converter converterInstance = serializerProvider.converterInstance(beanPropertyWriter.getMember(), findSerializationConverter);
                JavaType outputType = converterInstance.getOutputType(serializerProvider.getTypeFactory());
                return new StdDelegatingSerializer(converterInstance, outputType, serializerProvider.findValueSerializer(outputType, (BeanProperty) beanPropertyWriter));
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> createContextual(com.fasterxml.jackson.databind.SerializerProvider r13, com.fasterxml.jackson.databind.BeanProperty r14) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r12 = this;
            com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter r0 = r12._objectIdWriter
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r13.getAnnotationIntrospector()
            r2 = 0
            if (r14 == 0) goto L_0x0011
            if (r1 != 0) goto L_0x000c
            goto L_0x0011
        L_0x000c:
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r3 = r14.getMember()
            goto L_0x0012
        L_0x0011:
            r3 = r2
        L_0x0012:
            if (r3 == 0) goto L_0x00dd
            java.lang.String[] r4 = r1.findPropertiesToIgnore(r3)
            com.fasterxml.jackson.databind.introspect.ObjectIdInfo r5 = r1.findObjectIdInfo(r3)
            if (r5 != 0) goto L_0x0037
            if (r0 == 0) goto L_0x00de
            com.fasterxml.jackson.databind.introspect.ObjectIdInfo r0 = new com.fasterxml.jackson.databind.introspect.ObjectIdInfo
            java.lang.String r5 = ""
            r0.<init>(r5, r2, r2)
            com.fasterxml.jackson.databind.introspect.ObjectIdInfo r0 = r1.findObjectReferenceInfo(r3, r0)
            com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter r5 = r12._objectIdWriter
            boolean r0 = r0.getAlwaysAsId()
            com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter r0 = r5.withAlwaysAsId(r0)
            goto L_0x00de
        L_0x0037:
            com.fasterxml.jackson.databind.introspect.ObjectIdInfo r0 = r1.findObjectReferenceInfo(r3, r5)
            java.lang.Class r5 = r0.getGeneratorType()
            com.fasterxml.jackson.databind.JavaType r6 = r13.constructType(r5)
            com.fasterxml.jackson.databind.type.TypeFactory r7 = r13.getTypeFactory()
            java.lang.Class<com.fasterxml.jackson.annotation.ObjectIdGenerator> r8 = com.fasterxml.jackson.annotation.ObjectIdGenerator.class
            com.fasterxml.jackson.databind.JavaType[] r6 = r7.findTypeParameters(r6, r8)
            r7 = 0
            r6 = r6[r7]
            java.lang.Class<com.fasterxml.jackson.annotation.ObjectIdGenerators$PropertyGenerator> r8 = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class
            if (r5 != r8) goto L_0x00cc
            java.lang.String r5 = r0.getPropertyName()
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r6 = r12._props
            int r8 = r6.length
            r6 = r7
        L_0x005c:
            if (r6 != r8) goto L_0x0088
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r0 = "Invalid Object Id definition for "
            r14.append(r0)
            java.lang.Class r0 = r12._handledType
            java.lang.String r0 = r0.getName()
            r14.append(r0)
            java.lang.String r0 = ": can not find property with name '"
            r14.append(r0)
            r14.append(r5)
            java.lang.String r0 = "'"
            r14.append(r0)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            throw r13
        L_0x0088:
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r9 = r12._props
            r9 = r9[r6]
            java.lang.String r10 = r9.getName()
            boolean r10 = r5.equals(r10)
            if (r10 == 0) goto L_0x00c9
            if (r6 <= 0) goto L_0x00b7
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r5 = r12._props
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r8 = r12._props
            r10 = 1
            java.lang.System.arraycopy(r5, r7, r8, r10, r6)
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r5 = r12._props
            r5[r7] = r9
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r5 = r12._filteredProps
            if (r5 == 0) goto L_0x00b7
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r5 = r12._filteredProps
            r5 = r5[r6]
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r8 = r12._filteredProps
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r11 = r12._filteredProps
            java.lang.System.arraycopy(r8, r7, r11, r10, r6)
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r6 = r12._filteredProps
            r6[r7] = r5
        L_0x00b7:
            com.fasterxml.jackson.databind.JavaType r5 = r9.getType()
            com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator r6 = new com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator
            r6.<init>(r0, r9)
            boolean r0 = r0.getAlwaysAsId()
            com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter r0 = com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter.construct(r5, r2, r6, r0)
            goto L_0x00de
        L_0x00c9:
            int r6 = r6 + 1
            goto L_0x005c
        L_0x00cc:
            com.fasterxml.jackson.annotation.ObjectIdGenerator r5 = r13.objectIdGeneratorInstance(r3, r0)
            java.lang.String r7 = r0.getPropertyName()
            boolean r0 = r0.getAlwaysAsId()
            com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter r0 = com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter.construct(r6, r7, r5, r0)
            goto L_0x00de
        L_0x00dd:
            r4 = r2
        L_0x00de:
            if (r0 == 0) goto L_0x00f3
            com.fasterxml.jackson.databind.JavaType r5 = r0.idType
            com.fasterxml.jackson.databind.JsonSerializer r13 = r13.findValueSerializer(r5, r14)
            com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter r13 = r0.withSerializer(r13)
            com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter r14 = r12._objectIdWriter
            if (r13 == r14) goto L_0x00f3
            com.fasterxml.jackson.databind.ser.std.BeanSerializerBase r13 = r12.withObjectIdWriter(r13)
            goto L_0x00f4
        L_0x00f3:
            r13 = r12
        L_0x00f4:
            if (r4 == 0) goto L_0x00fd
            int r14 = r4.length
            if (r14 == 0) goto L_0x00fd
            com.fasterxml.jackson.databind.ser.std.BeanSerializerBase r13 = r13.withIgnorals(r4)
        L_0x00fd:
            if (r3 == 0) goto L_0x0109
            com.fasterxml.jackson.annotation.JsonFormat$Value r14 = r1.findFormat(r3)
            if (r14 == 0) goto L_0x0109
            com.fasterxml.jackson.annotation.JsonFormat$Shape r2 = r14.getShape()
        L_0x0109:
            if (r2 != 0) goto L_0x010d
            com.fasterxml.jackson.annotation.JsonFormat$Shape r2 = r12._serializationShape
        L_0x010d:
            com.fasterxml.jackson.annotation.JsonFormat$Shape r14 = com.fasterxml.jackson.annotation.JsonFormat.Shape.ARRAY
            if (r2 != r14) goto L_0x0115
            com.fasterxml.jackson.databind.ser.std.BeanSerializerBase r13 = r13.asArraySerializer()
        L_0x0115:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public boolean usesObjectId() {
        return this._objectIdWriter != null;
    }

    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        if (this._objectIdWriter != null) {
            _serializeWithObjectId(obj, jsonGenerator, serializerProvider, typeSerializer);
            return;
        }
        String _customTypeId = this._typeId == null ? null : _customTypeId(obj);
        if (_customTypeId == null) {
            typeSerializer.writeTypePrefixForObject(obj, jsonGenerator);
        } else {
            typeSerializer.writeCustomTypePrefixForObject(obj, jsonGenerator, _customTypeId);
        }
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        if (_customTypeId == null) {
            typeSerializer.writeTypeSuffixForObject(obj, jsonGenerator);
        } else {
            typeSerializer.writeCustomTypeSuffixForObject(obj, jsonGenerator, _customTypeId);
        }
    }

    /* access modifiers changed from: protected */
    public final void _serializeWithObjectId(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, boolean z) throws IOException, JsonGenerationException {
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        WritableObjectId findObjectId = serializerProvider.findObjectId(obj, objectIdWriter.generator);
        if (!findObjectId.writeAsId(jsonGenerator, serializerProvider, objectIdWriter)) {
            Object generateId = findObjectId.generateId(obj);
            if (objectIdWriter.alwaysAsId) {
                objectIdWriter.serializer.serialize(generateId, jsonGenerator, serializerProvider);
                return;
            }
            if (z) {
                jsonGenerator.writeStartObject();
            }
            findObjectId.writeAsField(jsonGenerator, serializerProvider, objectIdWriter);
            if (this._propertyFilterId != null) {
                serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
            } else {
                serializeFields(obj, jsonGenerator, serializerProvider);
            }
            if (z) {
                jsonGenerator.writeEndObject();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void _serializeWithObjectId(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        WritableObjectId findObjectId = serializerProvider.findObjectId(obj, objectIdWriter.generator);
        if (!findObjectId.writeAsId(jsonGenerator, serializerProvider, objectIdWriter)) {
            Object generateId = findObjectId.generateId(obj);
            if (objectIdWriter.alwaysAsId) {
                objectIdWriter.serializer.serialize(generateId, jsonGenerator, serializerProvider);
                return;
            }
            String _customTypeId = this._typeId == null ? null : _customTypeId(obj);
            if (_customTypeId == null) {
                typeSerializer.writeTypePrefixForObject(obj, jsonGenerator);
            } else {
                typeSerializer.writeCustomTypePrefixForObject(obj, jsonGenerator, _customTypeId);
            }
            findObjectId.writeAsField(jsonGenerator, serializerProvider, objectIdWriter);
            if (this._propertyFilterId != null) {
                serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
            } else {
                serializeFields(obj, jsonGenerator, serializerProvider);
            }
            if (_customTypeId == null) {
                typeSerializer.writeTypeSuffixForObject(obj, jsonGenerator);
            } else {
                typeSerializer.writeCustomTypeSuffixForObject(obj, jsonGenerator, _customTypeId);
            }
        }
    }

    private final String _customTypeId(Object obj) {
        Object value = this._typeId.getValue(obj);
        if (value == null) {
            return "";
        }
        return value instanceof String ? (String) value : value.toString();
    }

    /* access modifiers changed from: protected */
    public void serializeFields(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        BeanPropertyWriter[] beanPropertyWriterArr;
        if (this._filteredProps == null || serializerProvider.getActiveView() == null) {
            beanPropertyWriterArr = this._props;
        } else {
            beanPropertyWriterArr = this._filteredProps;
        }
        try {
            for (BeanPropertyWriter beanPropertyWriter : beanPropertyWriterArr) {
                if (beanPropertyWriter != null) {
                    beanPropertyWriter.serializeAsField(obj, jsonGenerator, serializerProvider);
                }
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndSerialize(obj, jsonGenerator, serializerProvider);
            }
        } catch (Exception e) {
            wrapAndThrow(serializerProvider, (Throwable) e, obj, 0 == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[0].getName());
        } catch (StackOverflowError e2) {
            JsonMappingException jsonMappingException = new JsonMappingException("Infinite recursion (StackOverflowError)", (Throwable) e2);
            jsonMappingException.prependPath(new Reference(obj, 0 == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[0].getName()));
            throw jsonMappingException;
        }
    }

    /* access modifiers changed from: protected */
    public void serializeFieldsFiltered(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        BeanPropertyWriter[] beanPropertyWriterArr;
        if (this._filteredProps == null || serializerProvider.getActiveView() == null) {
            beanPropertyWriterArr = this._props;
        } else {
            beanPropertyWriterArr = this._filteredProps;
        }
        BeanPropertyFilter findFilter = findFilter(serializerProvider);
        if (findFilter == null) {
            serializeFields(obj, jsonGenerator, serializerProvider);
            return;
        }
        try {
            for (BeanPropertyWriter beanPropertyWriter : beanPropertyWriterArr) {
                if (beanPropertyWriter != null) {
                    findFilter.serializeAsField(obj, jsonGenerator, serializerProvider, beanPropertyWriter);
                }
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndSerialize(obj, jsonGenerator, serializerProvider);
            }
        } catch (Exception e) {
            wrapAndThrow(serializerProvider, (Throwable) e, obj, 0 == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[0].getName());
        } catch (StackOverflowError e2) {
            JsonMappingException jsonMappingException = new JsonMappingException("Infinite recursion (StackOverflowError)", (Throwable) e2);
            jsonMappingException.prependPath(new Reference(obj, 0 == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[0].getName()));
            throw jsonMappingException;
        }
    }

    /* access modifiers changed from: protected */
    public BeanPropertyFilter findFilter(SerializerProvider serializerProvider) throws JsonMappingException {
        Object obj = this._propertyFilterId;
        FilterProvider filterProvider = serializerProvider.getFilterProvider();
        if (filterProvider != null) {
            return filterProvider.findFilter(obj);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can not resolve BeanPropertyFilter with id '");
        sb.append(obj);
        sb.append("'; no FilterProvider configured");
        throw new JsonMappingException(sb.toString());
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        ObjectNode createSchemaNode = createSchemaNode(ResponseConstants.OBJECT, true);
        JsonSerializableSchema jsonSerializableSchema = (JsonSerializableSchema) this._handledType.getAnnotation(JsonSerializableSchema.class);
        if (jsonSerializableSchema != null) {
            String id = jsonSerializableSchema.id();
            if (id != null && id.length() > 0) {
                createSchemaNode.put("id", id);
            }
        }
        ObjectNode objectNode = createSchemaNode.objectNode();
        BeanPropertyFilter findFilter = this._propertyFilterId != null ? findFilter(serializerProvider) : null;
        for (BeanPropertyWriter beanPropertyWriter : this._props) {
            if (findFilter == null) {
                beanPropertyWriter.depositSchemaProperty(objectNode, serializerProvider);
            } else {
                findFilter.depositSchemaProperty(beanPropertyWriter, objectNode, serializerProvider);
            }
        }
        createSchemaNode.put("properties", (JsonNode) objectNode);
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonObjectFormatVisitor expectObjectFormat = jsonFormatVisitorWrapper == null ? null : jsonFormatVisitorWrapper.expectObjectFormat(javaType);
        if (expectObjectFormat != null) {
            int i = 0;
            if (this._propertyFilterId != null) {
                BeanPropertyFilter findFilter = findFilter(jsonFormatVisitorWrapper.getProvider());
                while (i < this._props.length) {
                    findFilter.depositSchemaProperty(this._props[i], expectObjectFormat, jsonFormatVisitorWrapper.getProvider());
                    i++;
                }
                return;
            }
            while (i < this._props.length) {
                this._props[i].depositSchemaProperty(expectObjectFormat);
                i++;
            }
        }
    }
}
