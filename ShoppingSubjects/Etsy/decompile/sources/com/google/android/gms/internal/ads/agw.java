package com.google.android.gms.internal.ads;

import java.util.Comparator;

public final class agw implements Comparator<agj> {
    public agw(agv agv) {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        agj agj = (agj) obj;
        agj agj2 = (agj) obj2;
        if (agj.b() < agj2.b()) {
            return -1;
        }
        if (agj.b() > agj2.b()) {
            return 1;
        }
        if (agj.a() < agj2.a()) {
            return -1;
        }
        if (agj.a() > agj2.a()) {
            return 1;
        }
        float d = (agj.d() - agj.b()) * (agj.c() - agj.a());
        float d2 = (agj2.d() - agj2.b()) * (agj2.c() - agj2.a());
        if (d > d2) {
            return -1;
        }
        return d < d2 ? 1 : 0;
    }
}
