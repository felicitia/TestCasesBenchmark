package kotlin.jvm.internal;

/* compiled from: PackageReference.kt */
public final class r implements l {
    private final Class<?> a;
    private final String b;

    public r(Class<?> cls, String str) {
        p.b(cls, "jClass");
        p.b(str, "moduleName");
        this.a = cls;
        this.b = str;
    }

    public Class<?> a() {
        return this.a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof r) && p.a((Object) a(), (Object) ((r) obj).a());
    }

    public int hashCode() {
        return a().hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(a().toString());
        sb.append(" (Kotlin reflection is not available)");
        return sb.toString();
    }
}
