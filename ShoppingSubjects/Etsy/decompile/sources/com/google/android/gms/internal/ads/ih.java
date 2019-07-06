package com.google.android.gms.internal.ads;

import android.content.Context;
import java.io.File;
import java.util.regex.Pattern;

@bu
public final class ih extends fs {
    private final Context a;

    private ih(Context context, nq nqVar) {
        super(nqVar);
        this.a = context;
    }

    public static apa a(Context context) {
        apa apa = new apa(new iu(new File(context.getCacheDir(), "admob_volley")), new ih(context, new ol()));
        apa.a();
        return apa;
    }

    public final all a(amf<?> amf) throws zzae {
        if (amf.h() && amf.c() == 0) {
            if (Pattern.matches((String) ajh.f().a(akl.cJ), amf.e())) {
                ajh.a();
                if (jp.c(this.a)) {
                    all a2 = new amm(this.a).a(amf);
                    if (a2 != null) {
                        String str = "Got gmscore asset response: ";
                        String valueOf = String.valueOf(amf.e());
                        gv.a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                        return a2;
                    }
                    String str2 = "Failed to get gmscore asset response: ";
                    String valueOf2 = String.valueOf(amf.e());
                    gv.a(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
                }
            }
        }
        return super.a(amf);
    }
}
