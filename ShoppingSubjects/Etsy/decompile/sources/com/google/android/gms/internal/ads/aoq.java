package com.google.android.gms.internal.ads;

import android.content.Context;

@bu
public final class aoq {
    private Context a;

    public final void a(Context context) {
        if (this.a == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.a = context;
        }
    }
}
