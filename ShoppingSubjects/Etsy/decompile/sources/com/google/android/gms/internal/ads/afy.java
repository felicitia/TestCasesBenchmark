package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;

@bu
public final class afy {
    private final int a;
    private final int b;
    private final int c;
    private final agl d;
    private final agv e;
    private final Object f = new Object();
    private ArrayList<String> g = new ArrayList<>();
    private ArrayList<String> h = new ArrayList<>();
    private ArrayList<agj> i = new ArrayList<>();
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m;
    private String n = "";
    private String o = "";
    private String p = "";

    public afy(int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.a = i2;
        this.b = i3;
        this.c = i4;
        this.d = new agl(i5);
        this.e = new agv(i6, i7, i8);
    }

    private static String a(ArrayList<String> arrayList, int i2) {
        if (arrayList.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i3 = 0;
        while (i3 < size) {
            Object obj = arrayList2.get(i3);
            i3++;
            sb.append((String) obj);
            sb.append(' ');
            if (sb.length() > 100) {
                break;
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        String sb2 = sb.toString();
        return sb2.length() < 100 ? sb2 : sb2.substring(0, 100);
    }

    private final void c(@Nullable String str, boolean z, float f2, float f3, float f4, float f5) {
        if (str != null && str.length() >= this.c) {
            synchronized (this.f) {
                this.g.add(str);
                this.j += str.length();
                if (z) {
                    this.h.add(str);
                    ArrayList<agj> arrayList = this.i;
                    agj agj = new agj(f2, f3, f4, f5, this.h.size() - 1);
                    arrayList.add(agj);
                }
            }
        }
    }

    public final void a(int i2) {
        this.k = i2;
    }

    public final void a(String str, boolean z, float f2, float f3, float f4, float f5) {
        c(str, z, f2, f3, f4, f5);
        synchronized (this.f) {
            if (this.l < 0) {
                gv.b("ActivityContent: negative number of WebViews.");
            }
            h();
        }
    }

    public final boolean a() {
        boolean z;
        synchronized (this.f) {
            z = this.l == 0;
        }
        return z;
    }

    public final String b() {
        return this.n;
    }

    public final void b(String str, boolean z, float f2, float f3, float f4, float f5) {
        c(str, z, f2, f3, f4, f5);
    }

    public final String c() {
        return this.o;
    }

    public final String d() {
        return this.p;
    }

    public final void e() {
        synchronized (this.f) {
            this.m -= 100;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof afy)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        afy afy = (afy) obj;
        return afy.n != null && afy.n.equals(this.n);
    }

    public final void f() {
        synchronized (this.f) {
            this.l--;
        }
    }

    public final void g() {
        synchronized (this.f) {
            this.l++;
        }
    }

    public final void h() {
        synchronized (this.f) {
            int i2 = (this.j * this.a) + (this.k * this.b);
            if (i2 > this.m) {
                this.m = i2;
                if (((Boolean) ajh.f().a(akl.W)).booleanValue() && !ao.i().l().b()) {
                    this.n = this.d.a(this.g);
                    this.o = this.d.a(this.h);
                }
                if (((Boolean) ajh.f().a(akl.Y)).booleanValue() && !ao.i().l().d()) {
                    this.p = this.e.a(this.h, this.i);
                }
            }
        }
    }

    public final int hashCode() {
        return this.n.hashCode();
    }

    public final int i() {
        return this.m;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final int j() {
        return this.j;
    }

    public final String toString() {
        int i2 = this.k;
        int i3 = this.m;
        int i4 = this.j;
        String a2 = a(this.g, 100);
        String a3 = a(this.h, 100);
        String str = this.n;
        String str2 = this.o;
        String str3 = this.p;
        StringBuilder sb = new StringBuilder(165 + String.valueOf(a2).length() + String.valueOf(a3).length() + String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append("ActivityContent fetchId: ");
        sb.append(i2);
        sb.append(" score:");
        sb.append(i3);
        sb.append(" total_length:");
        sb.append(i4);
        sb.append("\n text: ");
        sb.append(a2);
        sb.append("\n viewableText");
        sb.append(a3);
        sb.append("\n signture: ");
        sb.append(str);
        sb.append("\n viewableSignture: ");
        sb.append(str2);
        sb.append("\n viewableSignatureForVertical: ");
        sb.append(str3);
        return sb.toString();
    }
}
