package retrofit2;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Map.Entry;
import okhttp3.Headers;
import okhttp3.RequestBody;

abstract class ParameterHandler<T> {

    static final class Body<T> extends ParameterHandler<T> {
        private final Converter<T, RequestBody> converter;

        Body(Converter<T, RequestBody> converter2) {
            this.converter = converter2;
        }

        /* access modifiers changed from: 0000 */
        public void apply(RequestBuilder requestBuilder, T t) {
            if (t == null) {
                throw new IllegalArgumentException("Body parameter value must not be null.");
            }
            try {
                requestBuilder.setBody((RequestBody) this.converter.convert(t));
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to convert ");
                sb.append(t);
                sb.append(" to RequestBody");
                throw new RuntimeException(sb.toString(), e);
            }
        }
    }

    static final class Field<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;

        Field(String str, Converter<T, String> converter, boolean z) {
            this.name = (String) Utils.checkNotNull(str, "name == null");
            this.valueConverter = converter;
            this.encoded = z;
        }

        /* access modifiers changed from: 0000 */
        public void apply(RequestBuilder requestBuilder, T t) throws IOException {
            if (t != null) {
                requestBuilder.addFormField(this.name, (String) this.valueConverter.convert(t), this.encoded);
            }
        }
    }

    static final class FieldMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean encoded;
        private final Converter<T, String> valueConverter;

        FieldMap(Converter<T, String> converter, boolean z) {
            this.valueConverter = converter;
            this.encoded = z;
        }

        /* access modifiers changed from: 0000 */
        public void apply(RequestBuilder requestBuilder, Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Field map was null.");
            }
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Field map contained null key.");
                }
                Object value = entry.getValue();
                if (value == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Field map contained null value for key '");
                    sb.append(str);
                    sb.append("'.");
                    throw new IllegalArgumentException(sb.toString());
                }
                requestBuilder.addFormField(str, (String) this.valueConverter.convert(value), this.encoded);
            }
        }
    }

    static final class Header<T> extends ParameterHandler<T> {
        private final String name;
        private final Converter<T, String> valueConverter;

        Header(String str, Converter<T, String> converter) {
            this.name = (String) Utils.checkNotNull(str, "name == null");
            this.valueConverter = converter;
        }

        /* access modifiers changed from: 0000 */
        public void apply(RequestBuilder requestBuilder, T t) throws IOException {
            if (t != null) {
                requestBuilder.addHeader(this.name, (String) this.valueConverter.convert(t));
            }
        }
    }

    static final class HeaderMap<T> extends ParameterHandler<Map<String, T>> {
        private final Converter<T, String> valueConverter;

        HeaderMap(Converter<T, String> converter) {
            this.valueConverter = converter;
        }

        /* access modifiers changed from: 0000 */
        public void apply(RequestBuilder requestBuilder, Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Header map was null.");
            }
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Header map contained null key.");
                }
                Object value = entry.getValue();
                if (value == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Header map contained null value for key '");
                    sb.append(str);
                    sb.append("'.");
                    throw new IllegalArgumentException(sb.toString());
                }
                requestBuilder.addHeader(str, (String) this.valueConverter.convert(value));
            }
        }
    }

    static final class Part<T> extends ParameterHandler<T> {
        private final Converter<T, RequestBody> converter;
        private final Headers headers;

        Part(Headers headers2, Converter<T, RequestBody> converter2) {
            this.headers = headers2;
            this.converter = converter2;
        }

        /* access modifiers changed from: 0000 */
        public void apply(RequestBuilder requestBuilder, T t) {
            if (t != null) {
                try {
                    requestBuilder.addPart(this.headers, (RequestBody) this.converter.convert(t));
                } catch (IOException e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unable to convert ");
                    sb.append(t);
                    sb.append(" to RequestBody");
                    throw new RuntimeException(sb.toString(), e);
                }
            }
        }
    }

    static final class PartMap<T> extends ParameterHandler<Map<String, T>> {
        private final String transferEncoding;
        private final Converter<T, RequestBody> valueConverter;

        PartMap(Converter<T, RequestBody> converter, String str) {
            this.valueConverter = converter;
            this.transferEncoding = str;
        }

        /* access modifiers changed from: 0000 */
        public void apply(RequestBuilder requestBuilder, Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Part map was null.");
            }
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Part map contained null key.");
                }
                Object value = entry.getValue();
                if (value == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Part map contained null value for key '");
                    sb.append(str);
                    sb.append("'.");
                    throw new IllegalArgumentException(sb.toString());
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("form-data; name=\"");
                sb2.append(str);
                sb2.append("\"");
                requestBuilder.addPart(Headers.of("Content-Disposition", sb2.toString(), "Content-Transfer-Encoding", this.transferEncoding), (RequestBody) this.valueConverter.convert(value));
            }
        }
    }

    static final class Path<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;

        Path(String str, Converter<T, String> converter, boolean z) {
            this.name = (String) Utils.checkNotNull(str, "name == null");
            this.valueConverter = converter;
            this.encoded = z;
        }

        /* access modifiers changed from: 0000 */
        public void apply(RequestBuilder requestBuilder, T t) throws IOException {
            if (t == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Path parameter \"");
                sb.append(this.name);
                sb.append("\" value must not be null.");
                throw new IllegalArgumentException(sb.toString());
            }
            requestBuilder.addPathParam(this.name, (String) this.valueConverter.convert(t), this.encoded);
        }
    }

    static final class Query<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;

        Query(String str, Converter<T, String> converter, boolean z) {
            this.name = (String) Utils.checkNotNull(str, "name == null");
            this.valueConverter = converter;
            this.encoded = z;
        }

        /* access modifiers changed from: 0000 */
        public void apply(RequestBuilder requestBuilder, T t) throws IOException {
            if (t != null) {
                requestBuilder.addQueryParam(this.name, (String) this.valueConverter.convert(t), this.encoded);
            }
        }
    }

    static final class QueryMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean encoded;
        private final Converter<T, String> valueConverter;

        QueryMap(Converter<T, String> converter, boolean z) {
            this.valueConverter = converter;
            this.encoded = z;
        }

        /* access modifiers changed from: 0000 */
        public void apply(RequestBuilder requestBuilder, Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Query map was null.");
            }
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Query map contained null key.");
                }
                Object value = entry.getValue();
                if (value == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Query map contained null value for key '");
                    sb.append(str);
                    sb.append("'.");
                    throw new IllegalArgumentException(sb.toString());
                }
                requestBuilder.addQueryParam(str, (String) this.valueConverter.convert(value), this.encoded);
            }
        }
    }

    static final class RawPart extends ParameterHandler<okhttp3.MultipartBody.Part> {
        static final RawPart INSTANCE = new RawPart();

        private RawPart() {
        }

        /* access modifiers changed from: 0000 */
        public void apply(RequestBuilder requestBuilder, okhttp3.MultipartBody.Part part) throws IOException {
            if (part != null) {
                requestBuilder.addPart(part);
            }
        }
    }

    static final class RelativeUrl extends ParameterHandler<Object> {
        RelativeUrl() {
        }

        /* access modifiers changed from: 0000 */
        public void apply(RequestBuilder requestBuilder, Object obj) {
            requestBuilder.setRelativeUrl(obj);
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract void apply(RequestBuilder requestBuilder, T t) throws IOException;

    ParameterHandler() {
    }

    /* access modifiers changed from: 0000 */
    public final ParameterHandler<Iterable<T>> iterable() {
        return new ParameterHandler<Iterable<T>>() {
            /* access modifiers changed from: 0000 */
            public void apply(RequestBuilder requestBuilder, Iterable<T> iterable) throws IOException {
                if (iterable != null) {
                    for (T apply : iterable) {
                        ParameterHandler.this.apply(requestBuilder, apply);
                    }
                }
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public final ParameterHandler<Object> array() {
        return new ParameterHandler<Object>() {
            /* access modifiers changed from: 0000 */
            public void apply(RequestBuilder requestBuilder, Object obj) throws IOException {
                if (obj != null) {
                    int length = Array.getLength(obj);
                    for (int i = 0; i < length; i++) {
                        ParameterHandler.this.apply(requestBuilder, Array.get(obj, i));
                    }
                }
            }
        };
    }
}
