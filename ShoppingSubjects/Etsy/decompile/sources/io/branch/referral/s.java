package io.branch.referral;

import android.app.Activity;
import android.content.Context;
import com.etsy.android.lib.requests.ReceiptsRequest;
import io.branch.indexing.c;
import io.branch.referral.Branch.i;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.i.b;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ServerRequestInitSession */
abstract class s extends ServerRequest {
    final aa g;
    private final Context h;
    private final c i = c.a(this.h);

    /* access modifiers changed from: protected */
    public boolean e() {
        return true;
    }

    public boolean i() {
        return true;
    }

    public abstract boolean v();

    public abstract String w();

    s(Context context, String str, aa aaVar) {
        super(context, str);
        this.h = context;
        this.g = aaVar;
    }

    s(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
        this.h = context;
        this.g = new aa(context);
    }

    /* access modifiers changed from: protected */
    public void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        if (!this.g.e().equals("bnc_no_value")) {
            jSONObject.put(Jsonkey.AppVersion.getKey(), this.g.e());
        }
        jSONObject.put(Jsonkey.FaceBookAppLinkChecked.getKey(), this.b.l());
        jSONObject.put(Jsonkey.IsReferrable.getKey(), this.b.x());
        jSONObject.put(Jsonkey.Debug.getKey(), this.b.G());
        b(jSONObject);
        a(this.h, jSONObject);
    }

    /* access modifiers changed from: 0000 */
    public void u() throws JSONException {
        if (h() != null) {
            String c = this.g.c();
            if (!c.equals("bnc_no_value")) {
                h().put(Jsonkey.URIScheme.getKey(), c);
            }
        }
    }

    static boolean a(String str) {
        if (str != null) {
            return str.equalsIgnoreCase(ReceiptsRequest.STATUS_OPEN) || str.equalsIgnoreCase("install");
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(z zVar) {
        if (!(zVar == null || zVar.b() == null || !zVar.b().has(Jsonkey.BranchViewData.getKey()))) {
            try {
                JSONObject jSONObject = zVar.b().getJSONObject(Jsonkey.BranchViewData.getKey());
                String w = w();
                if (Branch.b().e == null || Branch.b().e.get() == null) {
                    return i.a().a(jSONObject, w);
                }
                Activity activity = (Activity) Branch.b().e.get();
                boolean z = true;
                if (activity instanceof i) {
                    z = true ^ ((i) activity).a();
                }
                if (z) {
                    return i.a().a(jSONObject, w, (Context) activity, (b) Branch.b());
                }
                return i.a().a(jSONObject, w);
            } catch (JSONException unused) {
            }
        }
        return false;
    }

    public void a(z zVar, Branch branch) {
        try {
            this.b.j("bnc_no_value");
            this.b.k("bnc_no_value");
            this.b.l("bnc_no_value");
            this.b.h("bnc_no_value");
            this.b.i("bnc_no_value");
            this.b.m("bnc_no_value");
            this.b.n("bnc_no_value");
            this.b.a(Boolean.valueOf(false));
            this.b.q("bnc_no_value");
            this.b.b(false);
            if (zVar.b() != null && zVar.b().has(Jsonkey.Data.getKey())) {
                JSONObject jSONObject = new JSONObject(zVar.b().getString(Jsonkey.Data.getKey()));
                if (jSONObject.optBoolean(Jsonkey.Clicked_Branch_Link.getKey())) {
                    new l().a(this instanceof x ? "Branch Install" : "Branch Open", jSONObject, this.b.i());
                }
            }
        } catch (JSONException unused) {
        }
        if (this.b.u("bnc_previous_update_time") == 0) {
            this.b.a("bnc_previous_update_time", this.b.u("bnc_last_known_update_time"));
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(z zVar, Branch branch) {
        if (this.i != null) {
            this.i.a(zVar.b());
            if (branch.e != null) {
                try {
                    io.branch.indexing.b.a().b((Activity) branch.e.get(), branch.g);
                } catch (Exception unused) {
                }
            }
        }
        branch.g();
    }

    /* access modifiers changed from: 0000 */
    public void x() {
        String o = this.b.o();
        if (!o.equals("bnc_no_value")) {
            try {
                h().put(Jsonkey.LinkIdentifier.getKey(), o);
                h().put(Jsonkey.FaceBookAppLinkChecked.getKey(), this.b.l());
            } catch (JSONException unused) {
            }
        }
        String p = this.b.p();
        if (!p.equals("bnc_no_value")) {
            try {
                h().put(Jsonkey.GoogleSearchInstallReferrer.getKey(), p);
            } catch (JSONException unused2) {
            }
        }
        String q = this.b.q();
        if (!q.equals("bnc_no_value")) {
            try {
                h().put(Jsonkey.GooglePlayInstallReferrer.getKey(), q);
            } catch (JSONException unused3) {
            }
        }
        if (this.b.s()) {
            try {
                h().put(Jsonkey.AndroidAppLinkURL.getKey(), this.b.r());
                h().put(Jsonkey.IsFullAppConv.getKey(), true);
            } catch (JSONException unused4) {
            }
        }
    }

    public void q() {
        JSONObject h2 = h();
        try {
            if (!this.b.r().equals("bnc_no_value")) {
                h2.put(Jsonkey.AndroidAppLinkURL.getKey(), this.b.r());
            }
            if (!this.b.t().equals("bnc_no_value")) {
                h2.put(Jsonkey.AndroidPushIdentifier.getKey(), this.b.t());
            }
            if (!this.b.m().equals("bnc_no_value")) {
                h2.put(Jsonkey.External_Intent_URI.getKey(), this.b.m());
            }
            if (!this.b.n().equals("bnc_no_value")) {
                h2.put(Jsonkey.External_Intent_Extra.getKey(), this.b.n());
            }
            if (this.i != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("mv", this.i.e());
                jSONObject.put("pn", this.h.getPackageName());
                h2.put("cd", jSONObject);
            }
        } catch (JSONException unused) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0035, code lost:
        if ((r2.lastUpdateTime - r2.firstInstallTime) >= org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY) goto L_0x0044;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(org.json.JSONObject r12) throws org.json.JSONException {
        /*
            r11 = this;
            io.branch.referral.aa r0 = r11.g
            java.lang.String r0 = r0.e()
            r1 = 0
            android.content.Context r2 = r11.h     // Catch:{ NameNotFoundException -> 0x0018 }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0018 }
            android.content.Context r3 = r11.h     // Catch:{ NameNotFoundException -> 0x0018 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ NameNotFoundException -> 0x0018 }
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r1)     // Catch:{ NameNotFoundException -> 0x0018 }
            goto L_0x0019
        L_0x0018:
            r2 = 0
        L_0x0019:
            java.lang.String r3 = "bnc_no_value"
            io.branch.referral.m r4 = r11.b
            java.lang.String r4 = r4.e()
            boolean r3 = r3.equals(r4)
            r4 = 2
            if (r3 == 0) goto L_0x0038
            if (r2 == 0) goto L_0x0047
            long r5 = r2.lastUpdateTime
            long r7 = r2.firstInstallTime
            long r9 = r5 - r7
            r5 = 86400000(0x5265c00, double:4.2687272E-316)
            int r0 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r0 < 0) goto L_0x0047
            goto L_0x0044
        L_0x0038:
            io.branch.referral.m r1 = r11.b
            java.lang.String r1 = r1.e()
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0046
        L_0x0044:
            r1 = r4
            goto L_0x0047
        L_0x0046:
            r1 = 1
        L_0x0047:
            io.branch.referral.Defines$Jsonkey r0 = io.branch.referral.Defines.Jsonkey.Update
            java.lang.String r0 = r0.getKey()
            r12.put(r0, r1)
            if (r2 == 0) goto L_0x00b9
            io.branch.referral.Defines$Jsonkey r0 = io.branch.referral.Defines.Jsonkey.FirstInstallTime
            java.lang.String r0 = r0.getKey()
            long r3 = r2.firstInstallTime
            r12.put(r0, r3)
            io.branch.referral.Defines$Jsonkey r0 = io.branch.referral.Defines.Jsonkey.LastUpdateTime
            java.lang.String r0 = r0.getKey()
            long r3 = r2.lastUpdateTime
            r12.put(r0, r3)
            io.branch.referral.m r0 = r11.b
            java.lang.String r1 = "bnc_original_install_time"
            long r0 = r0.u(r1)
            r3 = 0
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x0081
            long r0 = r2.firstInstallTime
            io.branch.referral.m r3 = r11.b
            java.lang.String r4 = "bnc_original_install_time"
            long r5 = r2.firstInstallTime
            r3.a(r4, r5)
        L_0x0081:
            io.branch.referral.Defines$Jsonkey r3 = io.branch.referral.Defines.Jsonkey.OriginalInstallTime
            java.lang.String r3 = r3.getKey()
            r12.put(r3, r0)
            io.branch.referral.m r0 = r11.b
            java.lang.String r1 = "bnc_last_known_update_time"
            long r0 = r0.u(r1)
            long r3 = r2.lastUpdateTime
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x00a8
            io.branch.referral.m r3 = r11.b
            java.lang.String r4 = "bnc_previous_update_time"
            r3.a(r4, r0)
            io.branch.referral.m r0 = r11.b
            java.lang.String r1 = "bnc_last_known_update_time"
            long r2 = r2.lastUpdateTime
            r0.a(r1, r2)
        L_0x00a8:
            io.branch.referral.Defines$Jsonkey r0 = io.branch.referral.Defines.Jsonkey.PreviousUpdateTime
            java.lang.String r0 = r0.getKey()
            io.branch.referral.m r1 = r11.b
            java.lang.String r2 = "bnc_previous_update_time"
            long r1 = r1.u(r2)
            r12.put(r0, r1)
        L_0x00b9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.s.b(org.json.JSONObject):void");
    }

    /* access modifiers changed from: protected */
    public boolean t() {
        JSONObject h2 = h();
        if (!h2.has(Jsonkey.AndroidAppLinkURL.getKey()) && !h2.has(Jsonkey.AndroidPushIdentifier.getKey()) && !h2.has(Jsonkey.LinkIdentifier.getKey())) {
            return super.t();
        }
        h2.remove(Jsonkey.DeviceFingerprintID.getKey());
        h2.remove(Jsonkey.IdentityID.getKey());
        h2.remove(Jsonkey.FaceBookAppLinkChecked.getKey());
        h2.remove(Jsonkey.External_Intent_Extra.getKey());
        h2.remove(Jsonkey.External_Intent_URI.getKey());
        h2.remove(Jsonkey.FirstInstallTime.getKey());
        h2.remove(Jsonkey.LastUpdateTime.getKey());
        h2.remove(Jsonkey.OriginalInstallTime.getKey());
        h2.remove(Jsonkey.PreviousUpdateTime.getKey());
        h2.remove(Jsonkey.InstallBeginTimeStamp.getKey());
        h2.remove(Jsonkey.ClickedReferrerTimeStamp.getKey());
        h2.remove(Jsonkey.HardwareID.getKey());
        h2.remove(Jsonkey.IsHardwareIDReal.getKey());
        h2.remove(Jsonkey.LocalIP.getKey());
        try {
            h2.put(Jsonkey.TrackingDisabled.getKey(), true);
        } catch (JSONException unused) {
        }
        return true;
    }
}
