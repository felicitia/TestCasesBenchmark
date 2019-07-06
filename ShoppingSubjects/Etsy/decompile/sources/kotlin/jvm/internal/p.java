package kotlin.jvm.internal;

import java.util.Arrays;
import java.util.List;
import kotlin.KotlinNullPointerException;
import kotlin.UninitializedPropertyAccessException;

/* compiled from: Intrinsics */
public class p {
    public static int a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    private p() {
    }

    public static void a() {
        throw ((KotlinNullPointerException) a((T) new KotlinNullPointerException()));
    }

    public static void a(String str) {
        throw ((UninitializedPropertyAccessException) a((T) new UninitializedPropertyAccessException(str)));
    }

    public static void b(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("lateinit property ");
        sb.append(str);
        sb.append(" has not been initialized");
        a(sb.toString());
    }

    public static void a(Object obj, String str) {
        if (obj == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" must not be null");
            throw ((IllegalStateException) a((T) new IllegalStateException(sb.toString())));
        }
    }

    public static void b(Object obj, String str) {
        if (obj == null) {
            d(str);
        }
    }

    private static void d(String str) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        StringBuilder sb = new StringBuilder();
        sb.append("Parameter specified as non-null is null: method ");
        sb.append(className);
        sb.append(".");
        sb.append(methodName);
        sb.append(", parameter ");
        sb.append(str);
        throw ((IllegalArgumentException) a((T) new IllegalArgumentException(sb.toString())));
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static void b() {
        c("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
    }

    public static void c(String str) {
        throw new UnsupportedOperationException(str);
    }

    public static void a(int i, String str) {
        b();
    }

    private static <T extends Throwable> T a(T t) {
        return a(t, p.class.getName());
    }

    static <T extends Throwable> T a(T t, String str) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int i = -1;
        int length = stackTrace.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        List subList = Arrays.asList(stackTrace).subList(i + 1, length);
        t.setStackTrace((StackTraceElement[]) subList.toArray(new StackTraceElement[subList.size()]));
        return t;
    }
}
