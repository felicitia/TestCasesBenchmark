package retrofit2.a.a;

import com.google.gson.e;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.ab;
import okhttp3.z;
import retrofit2.m;

/* compiled from: GsonConverterFactory */
public final class a extends retrofit2.e.a {
    private final e a;

    public static a a(e eVar) {
        if (eVar != null) {
            return new a(eVar);
        }
        throw new NullPointerException("gson == null");
    }

    private a(e eVar) {
        this.a = eVar;
    }

    public retrofit2.e<ab, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        return new c(this.a, this.a.a(com.google.gson.a.a.a(type)));
    }

    public retrofit2.e<?, z> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, m mVar) {
        return new b(this.a, this.a.a(com.google.gson.a.a.a(type)));
    }
}
