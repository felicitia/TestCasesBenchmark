package com.onfido.d;

import com.onfido.d.a.b;

public final class c {
    private final b a;
    private b b;

    public c(b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Binarizer must be non-null.");
        }
        this.a = bVar;
    }

    public b a() {
        if (this.b == null) {
            this.b = this.a.b();
        }
        return this.b;
    }

    public String toString() {
        try {
            return a().toString();
        } catch (h unused) {
            return "";
        }
    }
}
