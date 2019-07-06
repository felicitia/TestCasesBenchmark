package io.reactivex.internal.util;

import io.reactivex.exceptions.CompositeException;
import java.util.concurrent.atomic.AtomicReference;

public final class ExceptionHelper {
    public static final Throwable TERMINATED = new Throwable("No further exceptions") {
        public Throwable fillInStackTrace() {
            return this;
        }
    };

    public static RuntimeException wrapOrThrow(Throwable th) {
        if (th instanceof Error) {
            throw ((Error) th);
        } else if (th instanceof RuntimeException) {
            return (RuntimeException) th;
        } else {
            return new RuntimeException(th);
        }
    }

    public static <T> boolean addThrowable(AtomicReference<Throwable> atomicReference, Throwable th) {
        Throwable th2;
        Throwable th3;
        do {
            th2 = (Throwable) atomicReference.get();
            if (th2 == TERMINATED) {
                return false;
            }
            if (th2 == null) {
                th3 = th;
            } else {
                th3 = new CompositeException(th2, th);
            }
        } while (!atomicReference.compareAndSet(th2, th3));
        return true;
    }

    public static <T> Throwable terminate(AtomicReference<Throwable> atomicReference) {
        Throwable th = (Throwable) atomicReference.get();
        return th != TERMINATED ? (Throwable) atomicReference.getAndSet(TERMINATED) : th;
    }
}
