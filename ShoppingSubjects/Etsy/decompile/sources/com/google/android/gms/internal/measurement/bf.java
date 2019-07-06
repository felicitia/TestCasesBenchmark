package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

public final class bf {
    @VisibleForTesting
    private final String a;
    private final String b;
    private final String c;
    private final long d;
    private final /* synthetic */ bb e;

    private bf(bb bbVar, String str, long j) {
        this.e = bbVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkArgument(j > 0);
        this.a = String.valueOf(str).concat(":start");
        this.b = String.valueOf(str).concat(":count");
        this.c = String.valueOf(str).concat(":value");
        this.d = j;
    }

    @WorkerThread
    private final void b() {
        this.e.d();
        long currentTimeMillis = this.e.m().currentTimeMillis();
        Editor edit = this.e.x().edit();
        edit.remove(this.b);
        edit.remove(this.c);
        edit.putLong(this.a, currentTimeMillis);
        edit.apply();
    }

    @WorkerThread
    private final long c() {
        return this.e.x().getLong(this.a, 0);
    }

    @WorkerThread
    public final Pair<String, Long> a() {
        long j;
        this.e.d();
        this.e.d();
        long c2 = c();
        if (c2 == 0) {
            b();
            j = 0;
        } else {
            j = Math.abs(c2 - this.e.m().currentTimeMillis());
        }
        if (j < this.d) {
            return null;
        }
        if (j > (this.d << 1)) {
            b();
            return null;
        }
        String string = this.e.x().getString(this.c, null);
        long j2 = this.e.x().getLong(this.b, 0);
        b();
        return (string == null || j2 <= 0) ? bb.a : new Pair<>(string, Long.valueOf(j2));
    }

    @WorkerThread
    public final void a(String str, long j) {
        this.e.d();
        if (c() == 0) {
            b();
        }
        if (str == null) {
            str = "";
        }
        long j2 = this.e.x().getLong(this.b, 0);
        if (j2 <= 0) {
            Editor edit = this.e.x().edit();
            edit.putString(this.c, str);
            edit.putLong(this.b, 1);
            edit.apply();
            return;
        }
        long j3 = j2 + 1;
        boolean z = (this.e.p().h().nextLong() & Long.MAX_VALUE) < Long.MAX_VALUE / j3;
        Editor edit2 = this.e.x().edit();
        if (z) {
            edit2.putString(this.c, str);
        }
        edit2.putLong(this.b, j3);
        edit2.apply();
    }
}
