package com.google.android.gms.ads.internal;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.fl;
import com.google.android.gms.internal.ads.hd;
import com.google.android.gms.internal.ads.zzael;

@bu
public final class bh {
    private final Context a;
    private boolean b;
    private fl c;
    private zzael d;

    public bh(Context context, fl flVar, zzael zzael) {
        this.a = context;
        this.c = flVar;
        this.d = zzael;
        if (this.d == null) {
            this.d = new zzael();
        }
    }

    private final boolean c() {
        return (this.c != null && this.c.a().zzcni) || this.d.zzcfr;
    }

    public final void a() {
        this.b = true;
    }

    public final void a(@Nullable String str) {
        if (c()) {
            if (str == null) {
                str = "";
            }
            if (this.c != null) {
                this.c.a(str, null, 3);
                return;
            }
            if (this.d.zzcfr && this.d.zzcfs != null) {
                for (String str2 : this.d.zzcfs) {
                    if (!TextUtils.isEmpty(str2)) {
                        String replace = str2.replace("{NAVIGATION_URL}", Uri.encode(str));
                        ao.e();
                        hd.a(this.a, "", replace);
                    }
                }
            }
        }
    }

    public final boolean b() {
        return !c() || this.b;
    }
}
