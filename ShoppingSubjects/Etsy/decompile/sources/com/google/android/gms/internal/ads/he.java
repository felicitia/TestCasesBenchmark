package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import java.util.List;

final class he implements alb {
    private final /* synthetic */ List a;
    private final /* synthetic */ ala b;
    private final /* synthetic */ Context c;

    he(hd hdVar, List list, ala ala, Context context) {
        this.a = list;
        this.b = ala;
        this.c = context;
    }

    public final void a() {
        for (String str : this.a) {
            String str2 = "Pinging url: ";
            String valueOf = String.valueOf(str);
            gv.d(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            this.b.a(Uri.parse(str), null, null);
        }
        this.b.a((Activity) this.c);
    }

    public final void b() {
    }
}
