package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.b;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public final class ajl {
    /* access modifiers changed from: private */
    public final HashSet<String> a = new HashSet<>();
    /* access modifiers changed from: private */
    public final Bundle b = new Bundle();
    /* access modifiers changed from: private */
    public final HashMap<Class<? extends Object>, Object> c = new HashMap<>();
    /* access modifiers changed from: private */
    public final HashSet<String> d = new HashSet<>();
    /* access modifiers changed from: private */
    public final Bundle e = new Bundle();
    /* access modifiers changed from: private */
    public final HashSet<String> f = new HashSet<>();
    /* access modifiers changed from: private */
    public Date g;
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public int i = -1;
    /* access modifiers changed from: private */
    public Location j;
    /* access modifiers changed from: private */
    public boolean k = false;
    /* access modifiers changed from: private */
    public String l;
    /* access modifiers changed from: private */
    public String m;
    /* access modifiers changed from: private */
    public int n = -1;
    /* access modifiers changed from: private */
    public boolean o;

    public final void a(int i2) {
        this.i = i2;
    }

    public final void a(Location location) {
        this.j = location;
    }

    public final void a(Class<? extends b> cls, Bundle bundle) {
        this.b.putBundle(cls.getName(), bundle);
    }

    public final void a(String str) {
        this.a.add(str);
    }

    public final void a(Date date) {
        this.g = date;
    }

    public final void a(boolean z) {
        this.n = z ? 1 : 0;
    }

    public final void b(String str) {
        this.d.add(str);
    }

    public final void b(boolean z) {
        this.o = z;
    }

    public final void c(String str) {
        this.d.remove(str);
    }
}
