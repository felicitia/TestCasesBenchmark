package com.google.android.gms.internal.ads;

@bu
public final class ajh {
    private static final Object a = new Object();
    private static ajh b;
    private final jp c = new jp();
    private final aiz d;
    private final String e;
    private final akh f;
    private final aki g;
    private final akj h;

    static {
        ajh ajh = new ajh();
        synchronized (a) {
            b = ajh;
        }
    }

    protected ajh() {
        aiz aiz = new aiz(new aiv(), new aiu(), new ajq(), new amh(), new el(), new p(), new ami());
        this.d = aiz;
        this.e = jp.c();
        this.f = new akh();
        this.g = new aki();
        this.h = new akj();
    }

    public static jp a() {
        return g().c;
    }

    public static aiz b() {
        return g().d;
    }

    public static String c() {
        return g().e;
    }

    public static aki d() {
        return g().g;
    }

    public static akh e() {
        return g().f;
    }

    public static akj f() {
        return g().h;
    }

    private static ajh g() {
        ajh ajh;
        synchronized (a) {
            ajh = b;
        }
        return ajh;
    }
}
