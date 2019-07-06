package retrofit2;

import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.s;
import okhttp3.u;
import org.apache.http.entity.mime.MIME;
import retrofit2.b.b;
import retrofit2.b.c;
import retrofit2.b.d;
import retrofit2.b.e;
import retrofit2.b.f;
import retrofit2.b.g;
import retrofit2.b.h;
import retrofit2.b.i;
import retrofit2.b.j;
import retrofit2.b.k;
import retrofit2.b.l;
import retrofit2.b.m;
import retrofit2.b.o;
import retrofit2.b.p;
import retrofit2.b.q;
import retrofit2.b.r;
import retrofit2.b.t;
import retrofit2.b.v;
import retrofit2.b.x;

/* compiled from: ServiceMethod */
final class n<R, T> {
    static final Pattern a = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
    static final Pattern b = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]*");
    private final okhttp3.e.a c;
    private final c<R, T> d;
    private final HttpUrl e;
    private final e<ab, R> f;
    private final String g;
    private final String h;
    private final s i;
    private final u j;
    private final boolean k;
    private final boolean l;
    private final boolean m;
    private final i<?>[] n;

    /* compiled from: ServiceMethod */
    static final class a<T, R> {
        final m a;
        final Method b;
        final Annotation[] c;
        final Annotation[][] d;
        final Type[] e;
        Type f;
        boolean g;
        boolean h;
        boolean i;
        boolean j;
        boolean k;
        boolean l;
        String m;
        boolean n;
        boolean o;
        boolean p;
        String q;
        s r;
        u s;
        Set<String> t;
        i<?>[] u;
        e<ab, T> v;
        c<T, R> w;

        a(m mVar, Method method) {
            this.a = mVar;
            this.b = method;
            this.c = method.getAnnotations();
            this.e = method.getGenericParameterTypes();
            this.d = method.getParameterAnnotations();
        }

        public n a() {
            this.w = b();
            this.f = this.w.a();
            if (this.f == l.class || this.f == aa.class) {
                StringBuilder sb = new StringBuilder();
                sb.append("'");
                sb.append(o.a(this.f).getName());
                sb.append("' is not a valid response body type. Did you mean ResponseBody?");
                throw a(sb.toString(), new Object[0]);
            }
            this.v = c();
            for (Annotation a2 : this.c) {
                a(a2);
            }
            if (this.m == null) {
                throw a("HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
            }
            if (!this.n) {
                if (this.p) {
                    throw a("Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                } else if (this.o) {
                    throw a("FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                }
            }
            int length = this.d.length;
            this.u = new i[length];
            for (int i2 = 0; i2 < length; i2++) {
                Type type = this.e[i2];
                if (o.d(type)) {
                    throw a(i2, "Parameter type must not include a type variable or wildcard: %s", type);
                }
                Annotation[] annotationArr = this.d[i2];
                if (annotationArr == null) {
                    throw a(i2, "No Retrofit annotation found.", new Object[0]);
                }
                this.u[i2] = a(i2, type, annotationArr);
            }
            if (this.q == null && !this.l) {
                throw a("Missing either @%s URL or @Url parameter.", this.m);
            } else if (!this.o && !this.p && !this.n && this.i) {
                throw a("Non-body HTTP method cannot contain @Body.", new Object[0]);
            } else if (this.o && !this.g) {
                throw a("Form-encoded method must contain at least one @Field.", new Object[0]);
            } else if (!this.p || this.h) {
                return new n(this);
            } else {
                throw a("Multipart method must contain at least one @Part.", new Object[0]);
            }
        }

        private c<T, R> b() {
            Type genericReturnType = this.b.getGenericReturnType();
            if (o.d(genericReturnType)) {
                throw a("Method return type must not include a type variable or wildcard: %s", genericReturnType);
            } else if (genericReturnType == Void.TYPE) {
                throw a("Service methods cannot return void.", new Object[0]);
            } else {
                try {
                    return this.a.a(genericReturnType, this.b.getAnnotations());
                } catch (RuntimeException e2) {
                    throw a((Throwable) e2, "Unable to create call adapter for %s", genericReturnType);
                }
            }
        }

        private void a(Annotation annotation) {
            if (annotation instanceof b) {
                a(BaseHttpRequest.DELETE, ((b) annotation).a(), false);
            } else if (annotation instanceof f) {
                a(BaseHttpRequest.GET, ((f) annotation).a(), false);
            } else if (annotation instanceof g) {
                a(BaseHttpRequest.HEAD, ((g) annotation).a(), false);
                if (!Void.class.equals(this.f)) {
                    throw a("HEAD method must use Void as response type.", new Object[0]);
                }
            } else if (annotation instanceof retrofit2.b.n) {
                a("PATCH", ((retrofit2.b.n) annotation).a(), true);
            } else if (annotation instanceof o) {
                a(BaseHttpRequest.POST, ((o) annotation).a(), true);
            } else if (annotation instanceof p) {
                a(BaseHttpRequest.PUT, ((p) annotation).a(), true);
            } else if (annotation instanceof m) {
                a(BaseHttpRequest.OPTIONS, ((m) annotation).a(), false);
            } else if (annotation instanceof h) {
                h hVar = (h) annotation;
                a(hVar.a(), hVar.b(), hVar.c());
            } else if (annotation instanceof k) {
                String[] a2 = ((k) annotation).a();
                if (a2.length == 0) {
                    throw a("@Headers annotation is empty.", new Object[0]);
                }
                this.r = a(a2);
            } else if (annotation instanceof l) {
                if (this.o) {
                    throw a("Only one encoding annotation is allowed.", new Object[0]);
                }
                this.p = true;
            } else if (!(annotation instanceof e)) {
            } else {
                if (this.p) {
                    throw a("Only one encoding annotation is allowed.", new Object[0]);
                }
                this.o = true;
            }
        }

        private void a(String str, String str2, boolean z) {
            if (this.m != null) {
                throw a("Only one HTTP method is allowed. Found: %s and %s.", this.m, str);
            }
            this.m = str;
            this.n = z;
            if (!str2.isEmpty()) {
                int indexOf = str2.indexOf(63);
                if (indexOf != -1 && indexOf < str2.length() - 1) {
                    String substring = str2.substring(indexOf + 1);
                    if (n.a.matcher(substring).find()) {
                        throw a("URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", substring);
                    }
                }
                this.q = str2;
                this.t = n.a(str2);
            }
        }

        private s a(String[] strArr) {
            okhttp3.s.a aVar = new okhttp3.s.a();
            for (String str : strArr) {
                int indexOf = str.indexOf(58);
                if (indexOf == -1 || indexOf == 0 || indexOf == str.length() - 1) {
                    throw a("@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str);
                }
                String substring = str.substring(0, indexOf);
                String trim = str.substring(indexOf + 1).trim();
                if (MIME.CONTENT_TYPE.equalsIgnoreCase(substring)) {
                    u a2 = u.a(trim);
                    if (a2 == null) {
                        throw a("Malformed content type: %s", trim);
                    }
                    this.s = a2;
                } else {
                    aVar.a(substring, trim);
                }
            }
            return aVar.a();
        }

        private i<?> a(int i2, Type type, Annotation[] annotationArr) {
            i<?> iVar = null;
            for (Annotation a2 : annotationArr) {
                i<?> a3 = a(i2, type, annotationArr, a2);
                if (a3 != null) {
                    if (iVar != null) {
                        throw a(i2, "Multiple Retrofit annotations found, only one allowed.", new Object[0]);
                    }
                    iVar = a3;
                }
            }
            if (iVar != null) {
                return iVar;
            }
            throw a(i2, "No Retrofit annotation found.", new Object[0]);
        }

        private i<?> a(int i2, Type type, Annotation[] annotationArr, Annotation annotation) {
            if (annotation instanceof x) {
                if (this.l) {
                    throw a(i2, "Multiple @Url method annotations found.", new Object[0]);
                } else if (this.j) {
                    throw a(i2, "@Path parameters may not be used with @Url.", new Object[0]);
                } else if (this.k) {
                    throw a(i2, "A @Url parameter must not come after a @Query", new Object[0]);
                } else if (this.q != null) {
                    throw a(i2, "@Url cannot be used with @%s URL", this.m);
                } else {
                    this.l = true;
                    if (type == HttpUrl.class || type == String.class || type == URI.class || ((type instanceof Class) && "android.net.Uri".equals(((Class) type).getName()))) {
                        return new m();
                    }
                    throw a(i2, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
                }
            } else if (annotation instanceof retrofit2.b.s) {
                if (this.k) {
                    throw a(i2, "A @Path parameter must not come after a @Query.", new Object[0]);
                } else if (this.l) {
                    throw a(i2, "@Path parameters may not be used with @Url.", new Object[0]);
                } else if (this.q == null) {
                    throw a(i2, "@Path can only be used with relative url on @%s", this.m);
                } else {
                    this.j = true;
                    retrofit2.b.s sVar = (retrofit2.b.s) annotation;
                    String a2 = sVar.a();
                    a(i2, a2);
                    return new h(a2, this.a.c(type, annotationArr), sVar.b());
                }
            } else if (annotation instanceof t) {
                t tVar = (t) annotation;
                String a3 = tVar.a();
                boolean b2 = tVar.b();
                Class a4 = o.a(type);
                this.k = true;
                if (Iterable.class.isAssignableFrom(a4)) {
                    if (!(type instanceof ParameterizedType)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(a4.getSimpleName());
                        sb.append(" must include generic type (e.g., ");
                        sb.append(a4.getSimpleName());
                        sb.append("<String>)");
                        throw a(i2, sb.toString(), new Object[0]);
                    }
                    return new C0200i(a3, this.a.c(o.a(0, (ParameterizedType) type), annotationArr), b2).a();
                } else if (!a4.isArray()) {
                    return new C0200i(a3, this.a.c(type, annotationArr), b2);
                } else {
                    return new C0200i(a3, this.a.c(n.a(a4.getComponentType()), annotationArr), b2).b();
                }
            } else if (annotation instanceof v) {
                boolean a5 = ((v) annotation).a();
                Class a6 = o.a(type);
                this.k = true;
                if (Iterable.class.isAssignableFrom(a6)) {
                    if (!(type instanceof ParameterizedType)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(a6.getSimpleName());
                        sb2.append(" must include generic type (e.g., ");
                        sb2.append(a6.getSimpleName());
                        sb2.append("<String>)");
                        throw a(i2, sb2.toString(), new Object[0]);
                    }
                    return new k(this.a.c(o.a(0, (ParameterizedType) type), annotationArr), a5).a();
                } else if (!a6.isArray()) {
                    return new k(this.a.c(type, annotationArr), a5);
                } else {
                    return new k(this.a.c(n.a(a6.getComponentType()), annotationArr), a5).b();
                }
            } else if (annotation instanceof retrofit2.b.u) {
                Class a7 = o.a(type);
                if (!Map.class.isAssignableFrom(a7)) {
                    throw a(i2, "@QueryMap parameter type must be Map.", new Object[0]);
                }
                Type b3 = o.b(type, a7, Map.class);
                if (!(b3 instanceof ParameterizedType)) {
                    throw a(i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType = (ParameterizedType) b3;
                Type a8 = o.a(0, parameterizedType);
                if (String.class != a8) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("@QueryMap keys must be of type String: ");
                    sb3.append(a8);
                    throw a(i2, sb3.toString(), new Object[0]);
                }
                return new j(this.a.c(o.a(1, parameterizedType), annotationArr), ((retrofit2.b.u) annotation).a());
            } else if (annotation instanceof i) {
                String a9 = ((i) annotation).a();
                Class a10 = o.a(type);
                if (Iterable.class.isAssignableFrom(a10)) {
                    if (!(type instanceof ParameterizedType)) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(a10.getSimpleName());
                        sb4.append(" must include generic type (e.g., ");
                        sb4.append(a10.getSimpleName());
                        sb4.append("<String>)");
                        throw a(i2, sb4.toString(), new Object[0]);
                    }
                    return new d(a9, this.a.c(o.a(0, (ParameterizedType) type), annotationArr)).a();
                } else if (!a10.isArray()) {
                    return new d(a9, this.a.c(type, annotationArr));
                } else {
                    return new d(a9, this.a.c(n.a(a10.getComponentType()), annotationArr)).b();
                }
            } else if (annotation instanceof j) {
                Class a11 = o.a(type);
                if (!Map.class.isAssignableFrom(a11)) {
                    throw a(i2, "@HeaderMap parameter type must be Map.", new Object[0]);
                }
                Type b4 = o.b(type, a11, Map.class);
                if (!(b4 instanceof ParameterizedType)) {
                    throw a(i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType2 = (ParameterizedType) b4;
                Type a12 = o.a(0, parameterizedType2);
                if (String.class != a12) {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("@HeaderMap keys must be of type String: ");
                    sb5.append(a12);
                    throw a(i2, sb5.toString(), new Object[0]);
                }
                return new e(this.a.c(o.a(1, parameterizedType2), annotationArr));
            } else if (annotation instanceof c) {
                if (!this.o) {
                    throw a(i2, "@Field parameters can only be used with form encoding.", new Object[0]);
                }
                c cVar = (c) annotation;
                String a13 = cVar.a();
                boolean b5 = cVar.b();
                this.g = true;
                Class a14 = o.a(type);
                if (Iterable.class.isAssignableFrom(a14)) {
                    if (!(type instanceof ParameterizedType)) {
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append(a14.getSimpleName());
                        sb6.append(" must include generic type (e.g., ");
                        sb6.append(a14.getSimpleName());
                        sb6.append("<String>)");
                        throw a(i2, sb6.toString(), new Object[0]);
                    }
                    return new b(a13, this.a.c(o.a(0, (ParameterizedType) type), annotationArr), b5).a();
                } else if (!a14.isArray()) {
                    return new b(a13, this.a.c(type, annotationArr), b5);
                } else {
                    return new b(a13, this.a.c(n.a(a14.getComponentType()), annotationArr), b5).b();
                }
            } else if (annotation instanceof d) {
                if (!this.o) {
                    throw a(i2, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
                }
                Class a15 = o.a(type);
                if (!Map.class.isAssignableFrom(a15)) {
                    throw a(i2, "@FieldMap parameter type must be Map.", new Object[0]);
                }
                Type b6 = o.b(type, a15, Map.class);
                if (!(b6 instanceof ParameterizedType)) {
                    throw a(i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType3 = (ParameterizedType) b6;
                Type a16 = o.a(0, parameterizedType3);
                if (String.class != a16) {
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("@FieldMap keys must be of type String: ");
                    sb7.append(a16);
                    throw a(i2, sb7.toString(), new Object[0]);
                }
                e c2 = this.a.c(o.a(1, parameterizedType3), annotationArr);
                this.g = true;
                return new c(c2, ((d) annotation).a());
            } else if (annotation instanceof q) {
                if (!this.p) {
                    throw a(i2, "@Part parameters can only be used with multipart encoding.", new Object[0]);
                }
                q qVar = (q) annotation;
                this.h = true;
                String a17 = qVar.a();
                Class a18 = o.a(type);
                if (!a17.isEmpty()) {
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append("form-data; name=\"");
                    sb8.append(a17);
                    sb8.append("\"");
                    s a19 = s.a(MIME.CONTENT_DISPOSITION, sb8.toString(), MIME.CONTENT_TRANSFER_ENC, qVar.b());
                    if (Iterable.class.isAssignableFrom(a18)) {
                        if (!(type instanceof ParameterizedType)) {
                            StringBuilder sb9 = new StringBuilder();
                            sb9.append(a18.getSimpleName());
                            sb9.append(" must include generic type (e.g., ");
                            sb9.append(a18.getSimpleName());
                            sb9.append("<String>)");
                            throw a(i2, sb9.toString(), new Object[0]);
                        }
                        Type a20 = o.a(0, (ParameterizedType) type);
                        if (!okhttp3.v.b.class.isAssignableFrom(o.a(a20))) {
                            return new f(a19, this.a.a(a20, annotationArr, this.c)).a();
                        }
                        throw a(i2, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    } else if (a18.isArray()) {
                        Class a21 = n.a(a18.getComponentType());
                        if (!okhttp3.v.b.class.isAssignableFrom(a21)) {
                            return new f(a19, this.a.a((Type) a21, annotationArr, this.c)).b();
                        }
                        throw a(i2, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    } else if (!okhttp3.v.b.class.isAssignableFrom(a18)) {
                        return new f(a19, this.a.a(type, annotationArr, this.c));
                    } else {
                        throw a(i2, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    }
                } else if (Iterable.class.isAssignableFrom(a18)) {
                    if (!(type instanceof ParameterizedType)) {
                        StringBuilder sb10 = new StringBuilder();
                        sb10.append(a18.getSimpleName());
                        sb10.append(" must include generic type (e.g., ");
                        sb10.append(a18.getSimpleName());
                        sb10.append("<String>)");
                        throw a(i2, sb10.toString(), new Object[0]);
                    } else if (okhttp3.v.b.class.isAssignableFrom(o.a(o.a(0, (ParameterizedType) type)))) {
                        return l.a.a();
                    } else {
                        throw a(i2, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    }
                } else if (a18.isArray()) {
                    if (okhttp3.v.b.class.isAssignableFrom(a18.getComponentType())) {
                        return l.a.b();
                    }
                    throw a(i2, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                } else if (okhttp3.v.b.class.isAssignableFrom(a18)) {
                    return l.a;
                } else {
                    throw a(i2, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                }
            } else if (annotation instanceof r) {
                if (!this.p) {
                    throw a(i2, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
                }
                this.h = true;
                Class a22 = o.a(type);
                if (!Map.class.isAssignableFrom(a22)) {
                    throw a(i2, "@PartMap parameter type must be Map.", new Object[0]);
                }
                Type b7 = o.b(type, a22, Map.class);
                if (!(b7 instanceof ParameterizedType)) {
                    throw a(i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType4 = (ParameterizedType) b7;
                Type a23 = o.a(0, parameterizedType4);
                if (String.class != a23) {
                    StringBuilder sb11 = new StringBuilder();
                    sb11.append("@PartMap keys must be of type String: ");
                    sb11.append(a23);
                    throw a(i2, sb11.toString(), new Object[0]);
                }
                Type a24 = o.a(1, parameterizedType4);
                if (!okhttp3.v.b.class.isAssignableFrom(o.a(a24))) {
                    return new g(this.a.a(a24, annotationArr, this.c), ((r) annotation).a());
                }
                throw a(i2, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
            } else if (!(annotation instanceof retrofit2.b.a)) {
                return null;
            } else {
                if (this.o || this.p) {
                    throw a(i2, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
                } else if (this.i) {
                    throw a(i2, "Multiple @Body method annotations found.", new Object[0]);
                } else {
                    try {
                        e a25 = this.a.a(type, annotationArr, this.c);
                        this.i = true;
                        return new a(a25);
                    } catch (RuntimeException e2) {
                        throw a((Throwable) e2, i2, "Unable to create @Body converter for %s", type);
                    }
                }
            }
        }

        private void a(int i2, String str) {
            if (!n.b.matcher(str).matches()) {
                throw a(i2, "@Path parameter name must match %s. Found: %s", n.a.pattern(), str);
            } else if (!this.t.contains(str)) {
                throw a(i2, "URL \"%s\" does not contain \"{%s}\".", this.q, str);
            }
        }

        private e<ab, T> c() {
            try {
                return this.a.b(this.f, this.b.getAnnotations());
            } catch (RuntimeException e2) {
                throw a((Throwable) e2, "Unable to create converter for %s", this.f);
            }
        }

        private RuntimeException a(String str, Object... objArr) {
            return a((Throwable) null, str, objArr);
        }

        private RuntimeException a(Throwable th, String str, Object... objArr) {
            String format = String.format(str, objArr);
            StringBuilder sb = new StringBuilder();
            sb.append(format);
            sb.append("\n    for method ");
            sb.append(this.b.getDeclaringClass().getSimpleName());
            sb.append(".");
            sb.append(this.b.getName());
            return new IllegalArgumentException(sb.toString(), th);
        }

        private RuntimeException a(Throwable th, int i2, String str, Object... objArr) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" (parameter #");
            sb.append(i2 + 1);
            sb.append(")");
            return a(th, sb.toString(), objArr);
        }

        private RuntimeException a(int i2, String str, Object... objArr) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" (parameter #");
            sb.append(i2 + 1);
            sb.append(")");
            return a(sb.toString(), objArr);
        }
    }

    n(a<R, T> aVar) {
        this.c = aVar.a.a();
        this.d = aVar.w;
        this.e = aVar.a.b();
        this.f = aVar.v;
        this.g = aVar.m;
        this.h = aVar.q;
        this.i = aVar.r;
        this.j = aVar.s;
        this.k = aVar.n;
        this.l = aVar.o;
        this.m = aVar.p;
        this.n = aVar.u;
    }

    /* access modifiers changed from: 0000 */
    public okhttp3.e a(Object... objArr) throws IOException {
        k kVar = new k(this.g, this.e, this.h, this.i, this.j, this.k, this.l, this.m);
        i<?>[] iVarArr = this.n;
        int length = objArr != null ? objArr.length : 0;
        if (length != iVarArr.length) {
            StringBuilder sb = new StringBuilder();
            sb.append("Argument count (");
            sb.append(length);
            sb.append(") doesn't match expected count (");
            sb.append(iVarArr.length);
            sb.append(")");
            throw new IllegalArgumentException(sb.toString());
        }
        for (int i2 = 0; i2 < length; i2++) {
            iVarArr[i2].a(kVar, objArr[i2]);
        }
        return this.c.a(kVar.a());
    }

    /* access modifiers changed from: 0000 */
    public T a(b<R> bVar) {
        return this.d.a(bVar);
    }

    /* access modifiers changed from: 0000 */
    public R a(ab abVar) throws IOException {
        return this.f.a(abVar);
    }

    static Set<String> a(String str) {
        Matcher matcher = a.matcher(str);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (matcher.find()) {
            linkedHashSet.add(matcher.group(1));
        }
        return linkedHashSet;
    }

    static Class<?> a(Class<?> cls) {
        if (Boolean.TYPE == cls) {
            return Boolean.class;
        }
        if (Byte.TYPE == cls) {
            return Byte.class;
        }
        if (Character.TYPE == cls) {
            return Character.class;
        }
        if (Double.TYPE == cls) {
            return Double.class;
        }
        if (Float.TYPE == cls) {
            return Float.class;
        }
        if (Integer.TYPE == cls) {
            return Integer.class;
        }
        if (Long.TYPE == cls) {
            return Long.class;
        }
        return Short.TYPE == cls ? Short.class : cls;
    }
}
