package retrofit2;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* compiled from: Platform */
class j {
    private static final j a = c();

    /* compiled from: Platform */
    static class a extends j {

        /* renamed from: retrofit2.j$a$a reason: collision with other inner class name */
        /* compiled from: Platform */
        static class C0201a implements Executor {
            private final Handler a = new Handler(Looper.getMainLooper());

            C0201a() {
            }

            public void execute(Runnable runnable) {
                this.a.post(runnable);
            }
        }

        a() {
        }

        public Executor b() {
            return new C0201a();
        }

        /* access modifiers changed from: 0000 */
        public retrofit2.c.a a(Executor executor) {
            if (executor != null) {
                return new g(executor);
            }
            throw new AssertionError();
        }
    }

    @IgnoreJRERequirement
    /* compiled from: Platform */
    static class b extends j {
        b() {
        }

        /* access modifiers changed from: 0000 */
        public boolean a(Method method) {
            return method.isDefault();
        }

        /* access modifiers changed from: 0000 */
        public Object a(Method method, Class<?> cls, Object obj, Object... objArr) throws Throwable {
            Constructor declaredConstructor = Lookup.class.getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
            declaredConstructor.setAccessible(true);
            return ((Lookup) declaredConstructor.newInstance(new Object[]{cls, Integer.valueOf(-1)})).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(Method method) {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public Executor b() {
        return null;
    }

    j() {
    }

    static j a() {
        return a;
    }

    private static j c() {
        try {
            Class.forName("android.os.Build");
            if (VERSION.SDK_INT != 0) {
                return new a();
            }
        } catch (ClassNotFoundException unused) {
        }
        try {
            Class.forName("java.util.Optional");
            return new b();
        } catch (ClassNotFoundException unused2) {
            return new j();
        }
    }

    /* access modifiers changed from: 0000 */
    public retrofit2.c.a a(Executor executor) {
        if (executor != null) {
            return new g(executor);
        }
        return f.a;
    }

    /* access modifiers changed from: 0000 */
    public Object a(Method method, Class<?> cls, Object obj, Object... objArr) throws Throwable {
        throw new UnsupportedOperationException();
    }
}
