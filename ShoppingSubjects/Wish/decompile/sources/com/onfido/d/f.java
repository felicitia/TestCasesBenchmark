package com.onfido.d;

public final class f extends j {
    private static final f c;

    static {
        f fVar = new f();
        c = fVar;
        fVar.setStackTrace(b);
    }

    private f() {
    }

    public static f a() {
        return a ? new f() : c;
    }
}
