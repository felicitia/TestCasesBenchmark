package com.google.android.gms.ads;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.reward.b;
import com.google.android.gms.ads.reward.c;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.ait;
import com.google.android.gms.internal.ads.ajp;

public final class g {
    private final ajp a;

    public g(Context context) {
        this.a = new ajp(context);
        Preconditions.checkNotNull(context, "Context cannot be null");
    }

    public final void a() {
        this.a.b();
    }

    public final void a(a aVar) {
        this.a.a(aVar);
        if (aVar == null || !(aVar instanceof ait)) {
            if (aVar == null) {
                this.a.a((ait) null);
            }
            return;
        }
        this.a.a((ait) aVar);
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void a(c cVar) {
        this.a.a(cVar.a());
    }

    public final void a(b bVar) {
        this.a.a(bVar);
    }

    public final void a(c cVar) {
        this.a.a(cVar);
    }

    public final void a(String str) {
        this.a.a(str);
    }

    public final void a(boolean z) {
        this.a.a(true);
    }

    public final Bundle b() {
        return this.a.a();
    }

    public final void b(boolean z) {
        this.a.b(z);
    }
}
