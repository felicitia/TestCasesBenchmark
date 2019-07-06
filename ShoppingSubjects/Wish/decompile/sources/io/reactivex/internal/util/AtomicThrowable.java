package io.reactivex.internal.util;

import java.util.concurrent.atomic.AtomicReference;

public final class AtomicThrowable extends AtomicReference<Throwable> {
    public boolean addThrowable(Throwable th) {
        return ExceptionHelper.addThrowable(this, th);
    }

    public Throwable terminate() {
        return ExceptionHelper.terminate(this);
    }
}
