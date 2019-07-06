package dagger.android;

import dagger.android.b.C0117b;
import java.util.Map;
import javax.a.a;

/* compiled from: DispatchingAndroidInjector_Factory */
public final class c<T> implements dagger.internal.c<DispatchingAndroidInjector<T>> {
    static final /* synthetic */ boolean a = true;
    private final a<Map<Class<? extends T>, a<C0117b<? extends T>>>> b;

    public c(a<Map<Class<? extends T>, a<C0117b<? extends T>>>> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public DispatchingAndroidInjector<T> b() {
        return new DispatchingAndroidInjector<>((Map) this.b.b());
    }

    public static <T> dagger.internal.c<DispatchingAndroidInjector<T>> a(a<Map<Class<? extends T>, a<C0117b<? extends T>>>> aVar) {
        return new c(aVar);
    }
}
