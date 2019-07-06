package retrofit2.a.b;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import okhttp3.ab;
import okhttp3.z;
import retrofit2.e;

/* compiled from: MoshiConverterFactory */
public final class a extends retrofit2.e.a {
    private final m a;
    private final boolean b;
    private final boolean c;
    private final boolean d;

    public static a a(m mVar) {
        if (mVar != null) {
            return new a(mVar, false, false, false);
        }
        throw new NullPointerException("moshi == null");
    }

    private a(m mVar, boolean z, boolean z2, boolean z3) {
        this.a = mVar;
        this.b = z;
        this.c = z2;
        this.d = z3;
    }

    public e<ab, ?> a(Type type, Annotation[] annotationArr, retrofit2.m mVar) {
        JsonAdapter a2 = this.a.a(type, a(annotationArr));
        if (this.b) {
            a2 = a2.lenient();
        }
        if (this.c) {
            a2 = a2.failOnUnknown();
        }
        if (this.d) {
            a2 = a2.serializeNulls();
        }
        return new c(a2);
    }

    public e<?, z> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, retrofit2.m mVar) {
        JsonAdapter a2 = this.a.a(type, a(annotationArr));
        if (this.b) {
            a2 = a2.lenient();
        }
        if (this.c) {
            a2 = a2.failOnUnknown();
        }
        if (this.d) {
            a2 = a2.serializeNulls();
        }
        return new b(a2);
    }

    private static Set<? extends Annotation> a(Annotation[] annotationArr) {
        LinkedHashSet linkedHashSet = null;
        for (Annotation annotation : annotationArr) {
            if (annotation.annotationType().isAnnotationPresent(f.class)) {
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                }
                linkedHashSet.add(annotation);
            }
        }
        return linkedHashSet != null ? Collections.unmodifiableSet(linkedHashSet) : Collections.emptySet();
    }
}
