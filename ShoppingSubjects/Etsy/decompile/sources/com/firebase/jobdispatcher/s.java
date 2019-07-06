package com.firebase.jobdispatcher;

import android.net.Uri;
import android.support.annotation.NonNull;

/* compiled from: ObservedUri */
public final class s {
    private final Uri a;
    private final int b;

    public s(@NonNull Uri uri, int i) {
        if (uri == null) {
            throw new IllegalArgumentException("URI must not be null.");
        }
        this.a = uri;
        this.b = i;
    }

    public Uri a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        if (this.b != sVar.b || !this.a.equals(sVar.a)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return this.a.hashCode() ^ this.b;
    }
}
