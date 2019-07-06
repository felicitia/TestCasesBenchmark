package com.jakewharton.rxbinding2.internal;

import android.os.Looper;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import io.reactivex.Observer;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: Preconditions */
public final class b {
    public static void a(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static boolean a(Observer<?> observer) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected to be called on the main thread but was ");
        sb.append(Thread.currentThread().getName());
        observer.onError(new IllegalStateException(sb.toString()));
        return false;
    }
}
