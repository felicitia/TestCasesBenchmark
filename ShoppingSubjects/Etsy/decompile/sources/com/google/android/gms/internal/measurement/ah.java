package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

public final class ah {
    final String a;
    final String b;
    final long c;
    final long d;
    final zzeu e;
    private final String f;

    ah(bu buVar, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzeu zzeu;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.a = str2;
        this.b = str3;
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.f = str;
        this.c = j;
        this.d = j2;
        if (this.d != 0 && this.d > this.c) {
            buVar.r().i().a("Event created with reverse previous/current timestamps. appId", aq.a(str2));
        }
        if (bundle == null || bundle.isEmpty()) {
            zzeu = new zzeu(new Bundle());
        } else {
            Bundle bundle2 = new Bundle(bundle);
            Iterator it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String str4 = (String) it.next();
                if (str4 == null) {
                    buVar.r().h_().a("Param name can't be null");
                } else {
                    Object a2 = buVar.k().a(str4, bundle2.get(str4));
                    if (a2 == null) {
                        buVar.r().i().a("Param value can't be null", buVar.l().b(str4));
                    } else {
                        buVar.k().a(bundle2, str4, a2);
                    }
                }
                it.remove();
            }
            zzeu = new zzeu(bundle2);
        }
        this.e = zzeu;
    }

    private ah(bu buVar, String str, String str2, String str3, long j, long j2, zzeu zzeu) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzeu);
        this.a = str2;
        this.b = str3;
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.f = str;
        this.c = j;
        this.d = j2;
        if (this.d != 0 && this.d > this.c) {
            buVar.r().i().a("Event created with reverse previous/current timestamps. appId, name", aq.a(str2), aq.a(str3));
        }
        this.e = zzeu;
    }

    /* access modifiers changed from: 0000 */
    public final ah a(bu buVar, long j) {
        ah ahVar = new ah(buVar, this.f, this.a, this.b, this.c, j, this.e);
        return ahVar;
    }

    public final String toString() {
        String str = this.a;
        String str2 = this.b;
        String valueOf = String.valueOf(this.e);
        StringBuilder sb = new StringBuilder(33 + String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(valueOf).length());
        sb.append("Event{appId='");
        sb.append(str);
        sb.append("', name='");
        sb.append(str2);
        sb.append("', params=");
        sb.append(valueOf);
        sb.append('}');
        return sb.toString();
    }
}
