package com.squareup.moshi;

import com.squareup.moshi.JsonReader.Token;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import okio.c;
import okio.d;
import okio.e;

public abstract class JsonAdapter<T> {

    public interface a {
        JsonAdapter<?> a(Type type, Set<? extends Annotation> set, m mVar);
    }

    public abstract T fromJson(JsonReader jsonReader) throws IOException;

    /* access modifiers changed from: 0000 */
    public boolean isLenient() {
        return false;
    }

    public abstract void toJson(l lVar, T t) throws IOException;

    public final T fromJson(e eVar) throws IOException {
        return fromJson(JsonReader.a(eVar));
    }

    public final T fromJson(String str) throws IOException {
        JsonReader a2 = JsonReader.a((e) new c().b(str));
        T fromJson = fromJson(a2);
        if (isLenient() || a2.h() == Token.END_DOCUMENT) {
            return fromJson;
        }
        throw new JsonDataException("JSON document was not fully consumed.");
    }

    public final void toJson(d dVar, T t) throws IOException {
        toJson(l.a(dVar), t);
    }

    public final String toJson(T t) {
        c cVar = new c();
        try {
            toJson((d) cVar, t);
            return cVar.p();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final Object toJsonValue(T t) {
        k kVar = new k();
        try {
            toJson((l) kVar, t);
            return kVar.f();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final T fromJsonValue(Object obj) {
        try {
            return fromJson((JsonReader) new j(obj));
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final JsonAdapter<T> serializeNulls() {
        return new JsonAdapter<T>() {
            public T fromJson(JsonReader jsonReader) throws IOException {
                return this.fromJson(jsonReader);
            }

            public void toJson(l lVar, T t) throws IOException {
                boolean k = lVar.k();
                lVar.c(true);
                try {
                    this.toJson(lVar, t);
                } finally {
                    lVar.c(k);
                }
            }

            /* access modifiers changed from: 0000 */
            public boolean isLenient() {
                return this.isLenient();
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(this);
                sb.append(".serializeNulls()");
                return sb.toString();
            }
        };
    }

    public final JsonAdapter<T> nullSafe() {
        return new JsonAdapter<T>() {
            public T fromJson(JsonReader jsonReader) throws IOException {
                if (jsonReader.h() == Token.NULL) {
                    return jsonReader.m();
                }
                return this.fromJson(jsonReader);
            }

            public void toJson(l lVar, T t) throws IOException {
                if (t == null) {
                    lVar.e();
                } else {
                    this.toJson(lVar, t);
                }
            }

            /* access modifiers changed from: 0000 */
            public boolean isLenient() {
                return this.isLenient();
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(this);
                sb.append(".nullSafe()");
                return sb.toString();
            }
        };
    }

    public final JsonAdapter<T> nonNull() {
        return new JsonAdapter<T>() {
            public T fromJson(JsonReader jsonReader) throws IOException {
                if (jsonReader.h() != Token.NULL) {
                    return this.fromJson(jsonReader);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected null at ");
                sb.append(jsonReader.s());
                throw new JsonDataException(sb.toString());
            }

            public void toJson(l lVar, T t) throws IOException {
                if (t == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unexpected null at ");
                    sb.append(lVar.m());
                    throw new JsonDataException(sb.toString());
                }
                this.toJson(lVar, t);
            }

            /* access modifiers changed from: 0000 */
            public boolean isLenient() {
                return this.isLenient();
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(this);
                sb.append(".nonNull()");
                return sb.toString();
            }
        };
    }

    public final JsonAdapter<T> lenient() {
        return new JsonAdapter<T>() {
            /* access modifiers changed from: 0000 */
            public boolean isLenient() {
                return true;
            }

            public T fromJson(JsonReader jsonReader) throws IOException {
                boolean a2 = jsonReader.a();
                jsonReader.a(true);
                try {
                    return this.fromJson(jsonReader);
                } finally {
                    jsonReader.a(a2);
                }
            }

            public void toJson(l lVar, T t) throws IOException {
                boolean j = lVar.j();
                lVar.b(true);
                try {
                    this.toJson(lVar, t);
                } finally {
                    lVar.b(j);
                }
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(this);
                sb.append(".lenient()");
                return sb.toString();
            }
        };
    }

    public final JsonAdapter<T> failOnUnknown() {
        return new JsonAdapter<T>() {
            public T fromJson(JsonReader jsonReader) throws IOException {
                boolean b2 = jsonReader.b();
                jsonReader.b(true);
                try {
                    return this.fromJson(jsonReader);
                } finally {
                    jsonReader.b(b2);
                }
            }

            public void toJson(l lVar, T t) throws IOException {
                this.toJson(lVar, t);
            }

            /* access modifiers changed from: 0000 */
            public boolean isLenient() {
                return this.isLenient();
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(this);
                sb.append(".failOnUnknown()");
                return sb.toString();
            }
        };
    }

    public JsonAdapter<T> indent(final String str) {
        if (str != null) {
            return new JsonAdapter<T>() {
                public T fromJson(JsonReader jsonReader) throws IOException {
                    return this.fromJson(jsonReader);
                }

                public void toJson(l lVar, T t) throws IOException {
                    String i = lVar.i();
                    lVar.a(str);
                    try {
                        this.toJson(lVar, t);
                    } finally {
                        lVar.a(i);
                    }
                }

                /* access modifiers changed from: 0000 */
                public boolean isLenient() {
                    return this.isLenient();
                }

                public String toString() {
                    StringBuilder sb = new StringBuilder();
                    sb.append(this);
                    sb.append(".indent(\"");
                    sb.append(str);
                    sb.append("\")");
                    return sb.toString();
                }
            };
        }
        throw new NullPointerException("indent == null");
    }
}
