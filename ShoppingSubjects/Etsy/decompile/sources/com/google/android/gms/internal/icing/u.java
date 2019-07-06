package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.ah.d;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class u implements df {
    private final zzbu a;

    private u(zzbu zzbu) {
        this.a = (zzbu) aj.a(zzbu, "output");
    }

    public static u a(zzbu zzbu) {
        return zzbu.a != null ? zzbu.a : new u(zzbu);
    }

    public final int a() {
        return d.j;
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

    public final <K, V> void a(int i, bf<K, V> bfVar, Map<K, V> map) throws IOException {
        for (Entry entry : map.entrySet()) {
            this.a.a(i, 2);
            this.a.b(ac.a(bfVar.a, 1, entry.getKey()) + ac.a(bfVar.b, 2, entry.getValue()));
            zzbu zzbu = this.a;
            Object key = entry.getKey();
            Object value = entry.getValue();
            ac.a(zzbu, bfVar.a, 1, key);
            ac.a(zzbu, bfVar.b, 2, value);
        }
    }

    public final void a(int i, zzbi zzbi) throws IOException {
        this.a.a(i, zzbi);
    }

    public final void a(int i, Object obj) throws IOException {
        if (obj instanceof zzbi) {
            this.a.b(i, (zzbi) obj);
        } else {
            this.a.a(i, (bl) obj);
        }
    }

    public final void a(int i, Object obj, ca caVar) throws IOException {
        this.a.a(i, (bl) obj, caVar);
    }

    public final void a(int i, String str) throws IOException {
        this.a.a(i, str);
    }

    public final void a(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof aw) {
            aw awVar = (aw) list;
            while (i2 < list.size()) {
                Object b = awVar.b(i2);
                if (b instanceof String) {
                    this.a.a(i, (String) b);
                } else {
                    this.a.a(i, (zzbi) b);
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

    public final void a(int i, List<?> list, ca caVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            a(i, list.get(i2), caVar);
        }
    }

    public final void a(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.f(((Integer) list.get(i4)).intValue());
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

    public final void b(int i, Object obj, ca caVar) throws IOException {
        zzbu zzbu = this.a;
        bl blVar = (bl) obj;
        zzbu.a(i, 3);
        caVar.a(blVar, (df) zzbu.a);
        zzbu.a(i, 4);
    }

    public final void b(int i, List<zzbi> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.a.a(i, (zzbi) list.get(i2));
        }
    }

    public final void b(int i, List<?> list, ca caVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            b(i, list.get(i2), caVar);
        }
    }

    public final void b(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.a.a(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbu.i(((Integer) list.get(i4)).intValue());
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
                i3 += zzbu.d(((Long) list.get(i4)).longValue());
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
                i3 += zzbu.e(((Long) list.get(i4)).longValue());
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
                i3 += zzbu.g(((Long) list.get(i4)).longValue());
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
                i3 += zzbu.b(((Float) list.get(i4)).floatValue());
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
                i3 += zzbu.b(((Double) list.get(i4)).doubleValue());
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
                i3 += zzbu.k(((Integer) list.get(i4)).intValue());
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
                i3 += zzbu.b(((Boolean) list.get(i4)).booleanValue());
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
                i3 += zzbu.g(((Integer) list.get(i4)).intValue());
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
                i3 += zzbu.j(((Integer) list.get(i4)).intValue());
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
                i3 += zzbu.h(((Long) list.get(i4)).longValue());
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
                i3 += zzbu.h(((Integer) list.get(i4)).intValue());
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
                i3 += zzbu.f(((Long) list.get(i4)).longValue());
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
