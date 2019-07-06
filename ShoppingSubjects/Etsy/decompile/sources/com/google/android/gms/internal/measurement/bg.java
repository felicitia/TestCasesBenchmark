package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

public final class bg {
    private final String a;
    private final String b = null;
    private boolean c;
    private String d;
    private final /* synthetic */ bb e;

    public bg(bb bbVar, String str, String str2) {
        this.e = bbVar;
        Preconditions.checkNotEmpty(str);
        this.a = str;
    }

    @WorkerThread
    public final String a() {
        if (!this.c) {
            this.c = true;
            this.d = this.e.x().getString(this.a, null);
        }
        return this.d;
    }

    @WorkerThread
    public final void a(String str) {
        if (!fg.b(str, this.d)) {
            Editor edit = this.e.x().edit();
            edit.putString(this.a, str);
            edit.apply();
            this.d = str;
        }
    }
}
