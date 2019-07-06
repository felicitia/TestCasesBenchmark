package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.b.a;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.mediation.b;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.language.bm.Languages;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class arc implements arg {
    /* access modifiers changed from: private */
    public final String a;
    private final zzxn b;
    private final long c;
    private final aqz d;
    private final aqy e;
    private zzjj f;
    private final zzjn g;
    private final Context h;
    /* access modifiers changed from: private */
    public final Object i = new Object();
    private final zzang j;
    private final boolean k;
    private final zzpl l;
    private final List<String> m;
    private final List<String> n;
    private final List<String> o;
    private final boolean p;
    private final boolean q;
    /* access modifiers changed from: private */
    public zzxq r;
    /* access modifiers changed from: private */
    public int s = -2;
    private zzxw t;

    public arc(Context context, String str, zzxn zzxn, aqz aqz, aqy aqy, zzjj zzjj, zzjn zzjn, zzang zzang, boolean z, boolean z2, zzpl zzpl, List<String> list, List<String> list2, List<String> list3, boolean z3) {
        String str2 = str;
        aqz aqz2 = aqz;
        aqy aqy2 = aqy;
        this.h = context;
        this.b = zzxn;
        this.e = aqy2;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str2)) {
            str2 = b();
        }
        this.a = str2;
        this.d = aqz2;
        long j2 = aqy2.t != -1 ? aqy2.t : aqz2.b != -1 ? aqz2.b : 10000;
        this.c = j2;
        this.f = zzjj;
        this.g = zzjn;
        this.j = zzang;
        this.k = z;
        this.p = z2;
        this.l = zzpl;
        this.m = list;
        this.n = list2;
        this.o = list3;
        this.q = z3;
    }

    @VisibleForTesting
    private static zzxq a(b bVar) {
        return new zzyk(bVar);
    }

    private final String a(String str) {
        if (str == null || !e() || b(2)) {
            return str;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.remove("cpm_floor_cents");
            return jSONObject.toString();
        } catch (JSONException unused) {
            gv.e("Could not remove field. Returning the original value");
            return str;
        }
    }

    /* access modifiers changed from: private */
    public final void a(zzxa zzxa) {
        String a2 = a(this.e.k);
        try {
            if (this.j.zzcvf >= 4100000) {
                if (!this.k) {
                    if (!this.e.b()) {
                        if (this.g.zzarc) {
                            this.r.zza(ObjectWrapper.wrap(this.h), this.f, a2, this.e.a, (zzxt) zzxa);
                            return;
                        } else if (!this.p) {
                            this.r.zza(ObjectWrapper.wrap(this.h), this.g, this.f, a2, this.e.a, zzxa);
                            return;
                        } else if (this.e.o != null) {
                            this.r.zza(ObjectWrapper.wrap(this.h), this.f, a2, this.e.a, zzxa, new zzpl(b(this.e.s)), this.e.r);
                            return;
                        } else {
                            this.r.zza(ObjectWrapper.wrap(this.h), this.g, this.f, a2, this.e.a, zzxa);
                            return;
                        }
                    }
                }
                ArrayList arrayList = new ArrayList(this.m);
                if (this.n != null) {
                    for (String str : this.n) {
                        String str2 = ":false";
                        if (this.o != null && this.o.contains(str)) {
                            str2 = ":true";
                        }
                        StringBuilder sb = new StringBuilder(7 + String.valueOf(str).length() + String.valueOf(str2).length());
                        sb.append("custom:");
                        sb.append(str);
                        sb.append(str2);
                        arrayList.add(sb.toString());
                    }
                }
                this.r.zza(ObjectWrapper.wrap(this.h), this.f, a2, this.e.a, zzxa, this.l, arrayList);
            } else if (this.g.zzarc) {
                this.r.zza(ObjectWrapper.wrap(this.h), this.f, a2, zzxa);
            } else {
                this.r.zza(ObjectWrapper.wrap(this.h), this.g, this.f, a2, (zzxt) zzxa);
            }
        } catch (RemoteException e2) {
            gv.c("Could not request ad from mediation adapter.", e2);
            a(5);
        }
    }

    private static com.google.android.gms.ads.formats.b b(String str) {
        a aVar = new a();
        if (str == null) {
            return aVar.a();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i2 = 0;
            aVar.b(jSONObject.optBoolean("multiple_images", false));
            aVar.a(jSONObject.optBoolean("only_urls", false));
            String optString = jSONObject.optString("native_image_orientation", Languages.ANY);
            if ("landscape".equals(optString)) {
                i2 = 2;
            } else if ("portrait".equals(optString)) {
                i2 = 1;
            } else if (!Languages.ANY.equals(optString)) {
                i2 = -1;
            }
            aVar.a(i2);
        } catch (JSONException e2) {
            gv.c("Exception occurred when creating native ad options", e2);
        }
        return aVar.a();
    }

    private final String b() {
        try {
            if (!TextUtils.isEmpty(this.e.e)) {
                return this.b.zzbn(this.e.e) ? "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter" : "com.google.ads.mediation.customevent.CustomEventAdapter";
            }
        } catch (RemoteException unused) {
            gv.e("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    /* access modifiers changed from: private */
    public final boolean b(int i2) {
        try {
            Bundle bundle = this.k ? this.r.zzmr() : this.g.zzarc ? this.r.getInterstitialAdapterInfo() : this.r.zzmq();
            return bundle != null && (bundle.getInt(ResponseConstants.CAPABILITIES, 0) & i2) == i2;
        } catch (RemoteException unused) {
            gv.e("Could not get adapter info. Returning false");
            return false;
        }
    }

    private final zzxw c() {
        if (this.s != 0 || !e()) {
            return null;
        }
        try {
            if (!(!b(4) || this.t == null || this.t.zzmm() == 0)) {
                return this.t;
            }
        } catch (RemoteException unused) {
            gv.e("Could not get cpm value from MediationResponseMetadata");
        }
        return new are(f());
    }

    /* access modifiers changed from: private */
    public final zzxq d() {
        String str = "Instantiating mediation adapter: ";
        String valueOf = String.valueOf(this.a);
        gv.d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        if (!this.k && !this.e.b()) {
            if (((Boolean) ajh.f().a(akl.bw)).booleanValue() && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.a)) {
                return a((b) new AdMobAdapter());
            }
            if (((Boolean) ajh.f().a(akl.bx)).booleanValue() && "com.google.ads.mediation.AdUrlAdapter".equals(this.a)) {
                return a((b) new AdUrlAdapter());
            }
            if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.a)) {
                return new zzyk(new zzzv());
            }
        }
        try {
            return this.b.zzbm(this.a);
        } catch (RemoteException e2) {
            String str2 = "Could not instantiate mediation adapter: ";
            String valueOf2 = String.valueOf(this.a);
            gv.a(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e2);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public final boolean e() {
        return this.d.m != -1;
    }

    private final int f() {
        if (this.e.k == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.e.k);
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.a)) {
                return jSONObject.optInt("cpm_cents", 0);
            }
            int optInt = b(2) ? jSONObject.optInt("cpm_floor_cents", 0) : 0;
            if (optInt == 0) {
                optInt = jSONObject.optInt("penalized_average_cpm_cents", 0);
            }
            return optInt;
        } catch (JSONException unused) {
            gv.e("Could not convert to json. Returning 0");
            return 0;
        }
    }

    public final arf a(long j2, long j3) {
        arf arf;
        synchronized (this.i) {
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                zzxa zzxa = new zzxa();
                hd.a.post(new ard(this, zzxa));
                long j4 = this.c;
                while (this.s == -2) {
                    long elapsedRealtime2 = SystemClock.elapsedRealtime();
                    long j5 = j4 - (elapsedRealtime2 - elapsedRealtime);
                    long j6 = elapsedRealtime;
                    long j7 = j3 - (elapsedRealtime2 - j2);
                    if (j5 <= 0 || j7 <= 0) {
                        gv.d("Timed out waiting for adapter.");
                        this.s = 3;
                    } else {
                        this.i.wait(Math.min(j5, j7));
                    }
                    elapsedRealtime = j6;
                }
                arf = new arf(this.e, this.r, this.a, zzxa, this.s, c(), ao.l().elapsedRealtime() - elapsedRealtime);
            } catch (InterruptedException unused) {
                this.s = 5;
            } catch (Throwable th) {
                throw th;
            }
        }
        return arf;
    }

    public final void a() {
        synchronized (this.i) {
            try {
                if (this.r != null) {
                    this.r.destroy();
                }
            } catch (RemoteException e2) {
                gv.c("Could not destroy mediation adapter.", e2);
            }
            this.s = -1;
            this.i.notify();
        }
    }

    public final void a(int i2) {
        synchronized (this.i) {
            this.s = i2;
            this.i.notify();
        }
    }

    public final void a(int i2, zzxw zzxw) {
        synchronized (this.i) {
            this.s = 0;
            this.t = zzxw;
            this.i.notify();
        }
    }
}
