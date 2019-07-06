package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

public final class bd {
    private final String a;
    private final boolean b = true;
    private boolean c;
    private boolean d;
    private final /* synthetic */ bb e;

    public bd(bb bbVar, String str, boolean z) {
        this.e = bbVar;
        Preconditions.checkNotEmpty(str);
        this.a = str;
    }

    @WorkerThread
    public final void a(boolean z) {
        Editor edit = this.e.x().edit();
        edit.putBoolean(this.a, z);
        edit.apply();
        this.d = z;
    }

    @WorkerThread
    public final boolean a() {
        if (!this.c) {
            this.c = true;
            this.d = this.e.x().getBoolean(this.a, this.b);
        }
        return this.d;
    }
}
