package com.onfido.d;

public final class d extends j {
    private static final d c;

    static {
        d dVar = new d();
        c = dVar;
        dVar.setStackTrace(b);
    }

    private d() {
    }

    public static d a() {
        return a ? new d() : c;
    }
}
