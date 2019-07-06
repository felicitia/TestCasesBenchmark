package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

@bu
public final class kz {
    public static final Executor a = new la();
    public static final Executor b = new lb();
    private static final ky c = a(a);
    private static final ky d = a(b);

    public static ky a(Executor executor) {
        return new lc(executor, null);
    }
}
