package com.usebutton.merchant;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.usebutton.merchant.e.a;
import com.usebutton.merchant.exception.ApplicationIdNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: ButtonInternalImpl */
final class d implements c {
    @VisibleForTesting
    ArrayList<a> a = new ArrayList<>();

    d() {
    }

    public void a(f fVar, String str) {
        fVar.a(str);
    }

    public void a(f fVar, Intent intent) {
        Uri data = intent.getData();
        if (data != null) {
            b(fVar, data.getQueryParameter("btn_ref"));
        }
    }

    @Nullable
    public String a(f fVar) {
        return fVar.b();
    }

    public void a(final f fVar, final n nVar, final String str, h hVar) {
        if (fVar.a() == null) {
            nVar.a((Throwable) new ApplicationIdNotFoundException());
        } else if (hVar.d() || fVar.c()) {
            nVar.a((Throwable) null);
        } else {
            fVar.a(true);
            fVar.a(new a<o>() {
                public void a(@Nullable o oVar) {
                    if (oVar == null || !oVar.a() || oVar.b() == null) {
                        nVar.a((Throwable) null);
                        return;
                    }
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(oVar.b()));
                    intent.setPackage(str);
                    a c2 = oVar.c();
                    if (c2 != null) {
                        d.this.b(fVar, c2.a());
                    }
                    nVar.a(intent);
                }

                public void a(Throwable th) {
                    nVar.a(th);
                }
            }, hVar);
        }
    }

    /* access modifiers changed from: private */
    public void b(f fVar, String str) {
        if (str != null && !str.isEmpty()) {
            if (!str.equals(a(fVar))) {
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    if (aVar != null) {
                        aVar.a(str);
                    }
                }
            }
            fVar.b(str);
        }
    }
}
