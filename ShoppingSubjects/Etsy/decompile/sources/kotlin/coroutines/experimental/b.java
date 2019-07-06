package kotlin.coroutines.experimental;

/* compiled from: Coroutines.kt */
public interface b<T> {
    d getContext();

    void resume(T t);

    void resumeWithException(Throwable th);
}
