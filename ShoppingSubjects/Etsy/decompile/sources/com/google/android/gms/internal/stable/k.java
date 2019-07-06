package com.google.android.gms.internal.stable;

final class k extends h {
    private final i a = new i();

    k() {
    }

    public final void a(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        } else if (th2 == null) {
            throw new NullPointerException("The suppressed exception cannot be null.");
        } else {
            this.a.a(th, true).add(th2);
        }
    }
}
