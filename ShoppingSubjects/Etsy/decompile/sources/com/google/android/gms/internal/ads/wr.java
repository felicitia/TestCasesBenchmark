package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.xh.e;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class wr implements aai {
    private final zzbav a;

    private wr(zzbav zzbav) {
        this.a = (zzbav) xj.a(zzbav, "output");
    }

    public static wr a(zzbav zzbav) {
        return zzbav.a != null ? zzbav.a : new wr(zzbav);
    }

    public final int a() {
        return e.j;
    }

    public final void a(int i) throws IOException {
        this.a.a(i, 3);
    }

    public final void a(int i, double d) throws IOException {
        this.a.a(i, d);
    }

    public final void a(int i, float f) throws IOException {
        this.a.a(i, f);
    }

    public final void a(int i, int i2) throws IOException {
        this.a.e(i, i2);
    }

    public final void a(int i, long j) throws IOException {
        this.a.a(i, j);
    }

    public final <K, V> void a(int i, ye<K, V> yeVar, Map<K, V> map) throws IOException {
        for (Entry entry : map.entrySet()) {
            this.a.a(i, 2);
            this.a.b(yd.a(yeVar, entry.getKey(), entry.getValue()));
            yd.a(this.a, yeVar, entry.getKey(), entry.getValue());
        }
    }

    public final void a(int i, zzbah zzbah) throws IOException {
        this.a.a(i, zzbah);
    }

    public final void a(int i, Object obj) throws IOException {
        if (obj instanceof zzbah) {
            this.a.b(i, (zzbah) obj);
        } else {
            this.a.a(i, (yk) obj);
        }
    }

    public final void a(int i, Object obj, zd zdVar) throws IOException {
        this.a.a(i, (yk) obj, zdVar);
    }

    public final void a(int i, String str) throws IOException {
        this.a.a(i, str);
    }

    public final void a(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof xu) {
            xu xuVar = (xu) list;
            while (i2 < list.size()) {
                Object b = xuVar.b(i2);
                if (b instanceof String) {
                    this.a.a(i, (String) b);
                } else {
                    this.a.a(i, (zzbah) b);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.a.a(i, (String) list.get(i2));
            i2++;
        }
    }

    public final void a(int i, List<?> list, zd zdVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            a(i, list.get(i2), zdVar);
        }
    }

    public final void a(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.f(((Integer) list.get(i4)).intValue());
            }
            this.a.b(i3);
            while (i2 < list.size()) {
                this.a.a(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.a.b(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void a(int i, boolean z) throws IOException {
        this.a.a(i, z);
    }

    public final void b(int i) throws IOException {
        this.a.a(i, 4);
    }

    public final void b(int i, int i2) throws IOException {
        this.a.b(i, i2);
    }

    public final void b(int i, long j) throws IOException {
        this.a.c(i, j);
    }

    public final void b(int i, Object obj, zd zdVar) throws IOException {
        zzbav zzbav = this.a;
        yk ykVar = (yk) obj;
        zzbav.a(i, 3);
        zdVar.a(ykVar, (aai) zzbav.a);
        zzbav.a(i, 4);
    }

    public final void b(int i, List<zzbah> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.a.a(i, (zzbah) list.get(i2));
        }
    }

    public final void b(int i, List<?> list, zd zdVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            b(i, list.get(i2), zdVar);
        }
    }

    public final void b(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.i(((Integer) list.get(i4)).intValue());
            }
            this.a.b(i3);
            while (i2 < list.size()) {
                this.a.d(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.a.e(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void c(int i, int i2) throws IOException {
        this.a.b(i, i2);
    }

    public final void c(int i, long j) throws IOException {
        this.a.a(i, j);
    }

    public final void c(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.d(((Long) list.get(i4)).longValue());
            }
            this.a.b(i3);
            while (i2 < list.size()) {
                this.a.a(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.a.a(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void d(int i, int i2) throws IOException {
        this.a.e(i, i2);
    }

    public final void d(int i, long j) throws IOException {
        this.a.c(i, j);
    }

    public final void d(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.e(((Long) list.get(i4)).longValue());
            }
            this.a.b(i3);
            while (i2 < list.size()) {
                this.a.a(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.a.a(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void e(int i, int i2) throws IOException {
        this.a.c(i, i2);
    }

    public final void e(int i, long j) throws IOException {
        this.a.b(i, j);
    }

    public final void e(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.g(((Long) list.get(i4)).longValue());
            }
            this.a.b(i3);
            while (i2 < list.size()) {
                this.a.c(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.a.c(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void f(int i, int i2) throws IOException {
        this.a.d(i, i2);
    }

    public final void f(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.b(((Float) list.get(i4)).floatValue());
            }
            this.a.b(i3);
            while (i2 < list.size()) {
                this.a.a(((Float) list.get(i2)).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.a.a(i, ((Float) list.get(i2)).floatValue());
            i2++;
        }
    }

    public final void g(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.b(((Double) list.get(i4)).doubleValue());
            }
            this.a.b(i3);
            while (i2 < list.size()) {
                this.a.a(((Double) list.get(i2)).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.a.a(i, ((Double) list.get(i2)).doubleValue());
            i2++;
        }
    }

    public final void h(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.k(((Integer) list.get(i4)).intValue());
            }
            this.a.b(i3);
            while (i2 < list.size()) {
                this.a.a(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.a.b(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void i(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.b(((Boolean) list.get(i4)).booleanValue());
            }
            this.a.b(i3);
            while (i2 < list.size()) {
                this.a.a(((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.a.a(i, ((Boolean) list.get(i2)).booleanValue());
            i2++;
        }
    }

    public final void j(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.g(((Integer) list.get(i4)).intValue());
            }
            this.a.b(i3);
            while (i2 < list.size()) {
                this.a.b(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.a.c(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void k(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.j(((Integer) list.get(i4)).intValue());
            }
            this.a.b(i3);
            while (i2 < list.size()) {
                this.a.d(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.a.e(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void l(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.h(((Long) list.get(i4)).longValue());
            }
            this.a.b(i3);
            while (i2 < list.size()) {
                this.a.c(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.a.c(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void m(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.h(((Integer) list.get(i4)).intValue());
            }
            this.a.b(i3);
            while (i2 < list.size()) {
                this.a.c(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.a.d(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void n(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.f(((Long) list.get(i4)).longValue());
            }
            this.a.b(i3);
            while (i2 < list.size()) {
                this.a.b(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.a.b(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }
}
