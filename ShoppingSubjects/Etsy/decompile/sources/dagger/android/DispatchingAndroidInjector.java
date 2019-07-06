package dagger.android;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.android.b.C0117b;
import dagger.internal.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import javax.a.a;

public final class DispatchingAndroidInjector<T> implements b<T> {
    private final Map<Class<? extends T>, a<C0117b<? extends T>>> a;

    public static final class InvalidInjectorBindingException extends RuntimeException {
        InvalidInjectorBindingException(String str, ClassCastException classCastException) {
            super(str, classCastException);
        }
    }

    DispatchingAndroidInjector(Map<Class<? extends T>, a<C0117b<? extends T>>> map) {
        this.a = map;
    }

    @CanIgnoreReturnValue
    public boolean b(T t) {
        a aVar = (a) this.a.get(t.getClass());
        if (aVar == null) {
            return false;
        }
        C0117b bVar = (C0117b) aVar.b();
        try {
            ((b) f.a(bVar.b(t), "%s.create(I) should not return null.", bVar.getClass().getCanonicalName())).a(t);
            return true;
        } catch (ClassCastException e) {
            throw new InvalidInjectorBindingException(String.format("%s does not implement AndroidInjector.Factory<%s>", new Object[]{bVar.getClass().getCanonicalName(), t.getClass().getCanonicalName()}), e);
        }
    }

    public void a(T t) {
        if (!b(t)) {
            throw new IllegalArgumentException(c(t));
        }
    }

    private String c(T t) {
        ArrayList arrayList = new ArrayList();
        for (Class cls : this.a.keySet()) {
            if (cls.isInstance(t)) {
                arrayList.add(cls.getCanonicalName());
            }
        }
        Collections.sort(arrayList);
        return String.format(arrayList.isEmpty() ? "No injector factory bound for Class<%s>" : "No injector factory bound for Class<%1$s>. Injector factories were bound for supertypes of %1$s: %2$s. Did you mean to bind an injector factory for the subtype?", new Object[]{t.getClass().getCanonicalName(), arrayList});
    }
}
