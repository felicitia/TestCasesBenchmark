package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;

public abstract class FilteredBeanPropertyWriter {

    private static final class a extends BeanPropertyWriter {
        protected final BeanPropertyWriter a;
        protected final Class<?>[] b;

        protected a(BeanPropertyWriter beanPropertyWriter, Class<?>[] clsArr) {
            super(beanPropertyWriter);
            this.a = beanPropertyWriter;
            this.b = clsArr;
        }

        /* renamed from: a */
        public a rename(NameTransformer nameTransformer) {
            return new a(this.a.rename(nameTransformer), this.b);
        }

        public void assignSerializer(JsonSerializer<Object> jsonSerializer) {
            this.a.assignSerializer(jsonSerializer);
        }

        public void assignNullSerializer(JsonSerializer<Object> jsonSerializer) {
            this.a.assignNullSerializer(jsonSerializer);
        }

        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
            Class activeView = serializerProvider.getActiveView();
            if (activeView != null) {
                int i = 0;
                int length = this.b.length;
                while (i < length && !this.b[i].isAssignableFrom(activeView)) {
                    i++;
                }
                if (i == length) {
                    return;
                }
            }
            this.a.serializeAsField(obj, jsonGenerator, serializerProvider);
        }

        public void serializeAsColumn(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
            Class activeView = serializerProvider.getActiveView();
            if (activeView != null) {
                int i = 0;
                int length = this.b.length;
                while (i < length && !this.b[i].isAssignableFrom(activeView)) {
                    i++;
                }
                if (i == length) {
                    this.a.serializeAsPlaceholder(obj, jsonGenerator, serializerProvider);
                    return;
                }
            }
            this.a.serializeAsColumn(obj, jsonGenerator, serializerProvider);
        }
    }

    private static final class b extends BeanPropertyWriter {
        protected final BeanPropertyWriter a;
        protected final Class<?> b;

        protected b(BeanPropertyWriter beanPropertyWriter, Class<?> cls) {
            super(beanPropertyWriter);
            this.a = beanPropertyWriter;
            this.b = cls;
        }

        /* renamed from: a */
        public b rename(NameTransformer nameTransformer) {
            return new b(this.a.rename(nameTransformer), this.b);
        }

        public void assignSerializer(JsonSerializer<Object> jsonSerializer) {
            this.a.assignSerializer(jsonSerializer);
        }

        public void assignNullSerializer(JsonSerializer<Object> jsonSerializer) {
            this.a.assignNullSerializer(jsonSerializer);
        }

        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
            Class activeView = serializerProvider.getActiveView();
            if (activeView == null || this.b.isAssignableFrom(activeView)) {
                this.a.serializeAsField(obj, jsonGenerator, serializerProvider);
            }
        }

        public void serializeAsColumn(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
            Class activeView = serializerProvider.getActiveView();
            if (activeView == null || this.b.isAssignableFrom(activeView)) {
                this.a.serializeAsColumn(obj, jsonGenerator, serializerProvider);
            } else {
                this.a.serializeAsPlaceholder(obj, jsonGenerator, serializerProvider);
            }
        }
    }

    public static BeanPropertyWriter constructViewBased(BeanPropertyWriter beanPropertyWriter, Class<?>[] clsArr) {
        if (clsArr.length == 1) {
            return new b(beanPropertyWriter, clsArr[0]);
        }
        return new a(beanPropertyWriter, clsArr);
    }
}
