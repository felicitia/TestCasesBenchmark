package io.branch.referral;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.a.a.a.a.a.a;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.Defines.RequestPath;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ServerRequest {
    protected String a;
    protected final m b;
    long c = 0;
    final Set<PROCESS_WAIT_LOCK> d;
    public boolean e = false;
    boolean f = false;
    private JSONObject g;
    private final aa h;
    private boolean i;
    private int j = 0;
    private final Context k;

    public enum BRANCH_API_VERSION {
        V1,
        V2
    }

    enum PROCESS_WAIT_LOCK {
        FB_APP_LINK_WAIT_LOCK,
        GAID_FETCH_WAIT_LOCK,
        INTENT_PENDING_WAIT_LOCK,
        STRONG_MATCH_PENDING_WAIT_LOCK,
        INSTALL_REFERRER_FETCH_WAIT_LOCK
    }

    public abstract void a(int i2, String str);

    public abstract void a(z zVar, Branch branch);

    public abstract boolean a();

    public abstract boolean a(Context context);

    public abstract void b();

    public boolean c() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean d() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return false;
    }

    public boolean i() {
        return false;
    }

    public void q() {
    }

    /* access modifiers changed from: protected */
    public boolean t() {
        return false;
    }

    public ServerRequest(Context context, String str) {
        this.k = context;
        this.a = str;
        this.b = m.a(context);
        this.h = new aa(context);
        this.g = new JSONObject();
        this.i = Branch.c();
        this.d = new HashSet();
    }

    protected ServerRequest(String str, JSONObject jSONObject, Context context) {
        this.k = context;
        this.a = str;
        this.g = jSONObject;
        this.b = m.a(context);
        this.h = new aa(context);
        this.i = Branch.c();
        this.d = new HashSet();
    }

    public final String f() {
        return this.a;
    }

    public String g() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.b.a());
        sb.append(this.a);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void a(JSONObject jSONObject) throws JSONException {
        this.g = jSONObject;
        if (r() == BRANCH_API_VERSION.V2) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                this.g.put(Jsonkey.UserData.getKey(), jSONObject2);
                k.a(this.b.G(), this.h, this.i).a(this.k, this.b, jSONObject2);
            } catch (JSONException unused) {
            }
        } else {
            k.a(this.b.G(), this.h, this.i).a(this.g);
        }
    }

    public JSONObject h() {
        return this.g;
    }

    public JSONObject a(ConcurrentHashMap<String, String> concurrentHashMap) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.g != null) {
                JSONObject jSONObject2 = new JSONObject(this.g.toString());
                Iterator keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    jSONObject.put(str, jSONObject2.get(str));
                }
            }
            if (concurrentHashMap.size() <= 0) {
                return jSONObject;
            }
            JSONObject jSONObject3 = new JSONObject();
            for (String str2 : concurrentHashMap.keySet()) {
                jSONObject3.put(str2, concurrentHashMap.get(str2));
                concurrentHashMap.remove(str2);
            }
            jSONObject.put(Jsonkey.Branch_Instrumentation.getKey(), jSONObject3);
            return jSONObject;
        } catch (JSONException unused) {
            return jSONObject;
        } catch (ConcurrentModificationException unused2) {
            return this.g;
        }
    }

    public JSONObject j() {
        return this.g;
    }

    public JSONObject k() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("REQ_POST", this.g);
            jSONObject.put("REQ_POST_PATH", this.a);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001b A[Catch:{ JSONException -> 0x0022 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static io.branch.referral.ServerRequest a(org.json.JSONObject r4, android.content.Context r5) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.lang.String r2 = "REQ_POST"
            boolean r2 = r4.has(r2)     // Catch:{ JSONException -> 0x0012 }
            if (r2 == 0) goto L_0x0012
            java.lang.String r2 = "REQ_POST"
            org.json.JSONObject r2 = r4.getJSONObject(r2)     // Catch:{ JSONException -> 0x0012 }
            goto L_0x0013
        L_0x0012:
            r2 = r1
        L_0x0013:
            java.lang.String r3 = "REQ_POST_PATH"
            boolean r3 = r4.has(r3)     // Catch:{ JSONException -> 0x0022 }
            if (r3 == 0) goto L_0x0022
            java.lang.String r3 = "REQ_POST_PATH"
            java.lang.String r4 = r4.getString(r3)     // Catch:{ JSONException -> 0x0022 }
            r0 = r4
        L_0x0022:
            if (r0 == 0) goto L_0x002f
            int r4 = r0.length()
            if (r4 <= 0) goto L_0x002f
            io.branch.referral.ServerRequest r4 = a(r0, r2, r5)
            return r4
        L_0x002f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.ServerRequest.a(org.json.JSONObject, android.content.Context):io.branch.referral.ServerRequest");
    }

    private static ServerRequest a(String str, JSONObject jSONObject, Context context) {
        if (str.equalsIgnoreCase(RequestPath.CompletedAction.getPath())) {
            return new n(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.GetURL.getPath())) {
            return new o(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.GetCreditHistory.getPath())) {
            return new p(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.GetCredits.getPath())) {
            return new q(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.IdentifyUser.getPath())) {
            return new r(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.Logout.getPath())) {
            return new t(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.RedeemRewards.getPath())) {
            return new v(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.RegisterClose.getPath())) {
            return new w(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.RegisterInstall.getPath())) {
            return new x(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(RequestPath.RegisterOpen.getPath())) {
            return new y(str, jSONObject, context);
        }
        return null;
    }

    private void u() {
        BRANCH_API_VERSION r = r();
        aa aaVar = this.h;
        if (!TextUtils.isEmpty(aa.a)) {
            try {
                if (r == BRANCH_API_VERSION.V2) {
                    JSONObject optJSONObject = this.g.optJSONObject(Jsonkey.UserData.getKey());
                    if (optJSONObject != null) {
                        String key = Jsonkey.AAID.getKey();
                        aa aaVar2 = this.h;
                        optJSONObject.put(key, aa.a);
                        optJSONObject.put(Jsonkey.LimitedAdTracking.getKey(), this.h.b);
                        optJSONObject.remove(Jsonkey.UnidentifiedDevice.getKey());
                        return;
                    }
                    return;
                }
                JSONObject jSONObject = this.g;
                String key2 = Jsonkey.GoogleAdvertisingID.getKey();
                aa aaVar3 = this.h;
                jSONObject.put(key2, aa.a);
                this.g.put(Jsonkey.LATVal.getKey(), this.h.b);
            } catch (JSONException e2) {
                a.a(e2);
            }
        } else if (r == BRANCH_API_VERSION.V2) {
            try {
                if (r == BRANCH_API_VERSION.V2) {
                    JSONObject optJSONObject2 = this.g.optJSONObject(Jsonkey.UserData.getKey());
                    if (optJSONObject2 != null && !optJSONObject2.has(Jsonkey.AndroidID.getKey())) {
                        optJSONObject2.put(Jsonkey.UnidentifiedDevice.getKey(), true);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    private void v() {
        if (r() == BRANCH_API_VERSION.V2) {
            JSONObject optJSONObject = this.g.optJSONObject(Jsonkey.UserData.getKey());
            if (optJSONObject != null) {
                try {
                    optJSONObject.put(Jsonkey.DeveloperIdentity.getKey(), this.b.j());
                    optJSONObject.put(Jsonkey.DeviceFingerprintID.getKey(), this.b.g());
                } catch (JSONException unused) {
                }
            }
        }
    }

    private void w() {
        try {
            JSONObject jSONObject = new JSONObject();
            Iterator keys = this.b.H().keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                jSONObject.put(str, this.b.H().get(str));
            }
            JSONObject optJSONObject = this.g.optJSONObject(Jsonkey.Metadata.getKey());
            if (optJSONObject != null) {
                Iterator keys2 = optJSONObject.keys();
                while (keys2.hasNext()) {
                    String str2 = (String) keys2.next();
                    jSONObject.put(str2, optJSONObject.get(str2));
                }
            }
            this.g.put(Jsonkey.Metadata.getKey(), jSONObject);
        } catch (JSONException unused) {
            Log.e("BranchSDK", "Could not merge metadata, ignoring user metadata.");
        }
    }

    private void x() {
        JSONObject optJSONObject = r() == BRANCH_API_VERSION.V1 ? this.g : this.g.optJSONObject(Jsonkey.UserData.getKey());
        if (optJSONObject != null) {
            boolean A = this.b.A();
            if (A) {
                try {
                    optJSONObject.putOpt(Jsonkey.limitFacebookTracking.getKey(), Boolean.valueOf(A));
                } catch (JSONException unused) {
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void l() {
        w();
        if (e()) {
            x();
        }
    }

    /* access modifiers changed from: 0000 */
    public void m() {
        if (this instanceof s) {
            ((s) this).x();
        }
        v();
        if (i() && !h.a(this.k)) {
            u();
        }
    }

    /* access modifiers changed from: protected */
    public boolean b(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.INTERNET") == 0;
    }

    public void n() {
        this.c = System.currentTimeMillis();
    }

    public long o() {
        if (this.c > 0) {
            return System.currentTimeMillis() - this.c;
        }
        return 0;
    }

    public void a(PROCESS_WAIT_LOCK process_wait_lock) {
        if (process_wait_lock != null) {
            this.d.add(process_wait_lock);
        }
    }

    public void b(PROCESS_WAIT_LOCK process_wait_lock) {
        this.d.remove(process_wait_lock);
    }

    public boolean p() {
        return this.d.size() > 0;
    }

    /* access modifiers changed from: protected */
    public void a(Context context, JSONObject jSONObject) {
        try {
            String key = (c(context) ? Jsonkey.NativeApp : Jsonkey.InstantApp).getKey();
            if (r() == BRANCH_API_VERSION.V2) {
                JSONObject optJSONObject = jSONObject.optJSONObject(Jsonkey.UserData.getKey());
                if (optJSONObject != null) {
                    optJSONObject.put(Jsonkey.Environment.getKey(), key);
                    return;
                }
                return;
            }
            jSONObject.put(Jsonkey.Environment.getKey(), key);
        } catch (Exception unused) {
        }
    }

    private static boolean c(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(context.getPackageName());
        boolean z = false;
        if (launchIntentForPackage == null) {
            return false;
        }
        List queryIntentActivities = packageManager.queryIntentActivities(launchIntentForPackage, 65536);
        if (queryIntentActivities != null && queryIntentActivities.size() > 0) {
            z = true;
        }
        return z;
    }

    public BRANCH_API_VERSION r() {
        return BRANCH_API_VERSION.V1;
    }

    public void s() {
        StringBuilder sb = new StringBuilder();
        sb.append("Requested operation cannot be completed since tracking is disabled [");
        sb.append(this.a);
        sb.append("]");
        m.b("BranchSDK", sb.toString());
        a(-117, "");
    }
}
