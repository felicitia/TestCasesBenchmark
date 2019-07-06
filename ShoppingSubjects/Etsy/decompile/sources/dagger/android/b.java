package dagger.android;

import com.google.errorprone.annotations.DoNotMock;

@DoNotMock("Faked versions of AndroidInjector are much clearer than a mock. See https://google.github.io/dagger/testing")
/* compiled from: AndroidInjector */
public interface b<T> {

    @DoNotMock
    /* compiled from: AndroidInjector */
    public static abstract class a<T> implements C0117b<T> {
        public abstract void a(T t);

        public abstract b<T> b();

        public final b<T> b(T t) {
            a(t);
            return b();
        }
    }

    @DoNotMock
    /* renamed from: dagger.android.b$b reason: collision with other inner class name */
    /* compiled from: AndroidInjector */
    public interface C0117b<T> {
        b<T> b(T t);
    }

    void a(T t);
}
