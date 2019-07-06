package kotlin.jvm.internal;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.jvm.a;
import kotlin.reflect.c;

/* compiled from: ClassReference.kt */
public final class m implements l, c<Object> {
    private final Class<?> a;

    public m(Class<?> cls) {
        p.b(cls, "jClass");
        this.a = cls;
    }

    public Class<?> a() {
        return this.a;
    }

    public List<Annotation> getAnnotations() {
        b();
        throw null;
    }

    private final Void b() {
        throw new KotlinReflectionNotSupportedError();
    }

    public boolean equals(Object obj) {
        return (obj instanceof m) && p.a((Object) a.a(this), (Object) a.a((c) obj));
    }

    public int hashCode() {
        return a.a(this).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(a().toString());
        sb.append(" (Kotlin reflection is not available)");
        return sb.toString();
    }
}
