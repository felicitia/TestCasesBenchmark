package com.usebutton.merchant;

import android.support.annotation.Nullable;

/* compiled from: Task */
abstract class p<T> implements Runnable {
    @Nullable
    private final a<T> a;

    /* compiled from: Task */
    interface a<T> {
        void a(@Nullable T t);

        void a(Throwable th);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public abstract T b() throws Exception;

    p(@Nullable a<T> aVar) {
        this.a = aVar;
    }

    public void run() {
        try {
            Object b = b();
            if (this.a != null) {
                this.a.a(b);
            }
        } catch (Exception e) {
            if (this.a != null) {
                this.a.a((Throwable) e);
            }
        }
    }
}
