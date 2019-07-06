package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.h;
import org.json.JSONException;
import org.json.JSONObject;

final class aqu implements h {
    private final apt a;
    private final le b;
    private final /* synthetic */ aqr c;

    public aqu(aqr aqr, apt apt, le leVar) {
        this.c = aqr;
        this.a = apt;
        this.b = leVar;
    }

    public final void a(String str) {
        if (str == null) {
            try {
                this.b.a(new zzwe());
            } catch (IllegalStateException unused) {
            } catch (Throwable th) {
                this.a.c();
                throw th;
            }
        } else {
            this.b.a(new zzwe(str));
        }
        this.a.c();
    }

    public final void a(JSONObject jSONObject) {
        try {
            this.b.b(this.c.a.a(jSONObject));
        } catch (IllegalStateException unused) {
        } catch (JSONException e) {
            this.b.b(e);
        } catch (Throwable th) {
            this.a.c();
            throw th;
        }
        this.a.c();
    }
}
