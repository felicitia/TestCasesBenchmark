package io.branch.referral;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PrefHelper */
public class m {
    private static boolean a = false;
    private static String b;
    private static m c;
    private static JSONObject h;
    private SharedPreferences d;
    private Editor e;
    private JSONObject f;
    private Context g;

    public String a() {
        return "https://api.branch.io/";
    }

    public m() {
    }

    private m(Context context) {
        this.d = context.getSharedPreferences("branch_referral_shared_pref", 0);
        this.e = this.d.edit();
        this.g = context;
        this.f = new JSONObject();
    }

    public static m a(Context context) {
        if (c == null) {
            c = new m(context);
        }
        return c;
    }

    public void a(int i) {
        e("bnc_timeout", i);
    }

    public int b() {
        return d("bnc_timeout", 5500);
    }

    public void b(int i) {
        e("bnc_retry_count", i);
    }

    public int c() {
        return d("bnc_retry_count", 3);
    }

    public int d() {
        return d("bnc_retry_interval", 1000);
    }

    public void a(String str) {
        a("bnc_app_version", str);
    }

    public String e() {
        return v("bnc_app_version");
    }

    public boolean b(String str) {
        b = str;
        String v = v("bnc_branch_key");
        if (str != null && v != null && v.equals(str)) {
            return false;
        }
        K();
        a("bnc_branch_key", str);
        return true;
    }

    public String f() {
        if (b == null) {
            b = v("bnc_branch_key");
        }
        return b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(boolean r6) {
        /*
            r5 = this;
            if (r6 == 0) goto L_0x0005
            java.lang.String r0 = "io.branch.sdk.BranchKey"
            goto L_0x0007
        L_0x0005:
            java.lang.String r0 = "io.branch.sdk.BranchKey.test"
        L_0x0007:
            if (r6 != 0) goto L_0x000c
            r5.F()
        L_0x000c:
            r1 = 0
            android.content.Context r2 = r5.g     // Catch:{ Exception -> 0x0037 }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ Exception -> 0x0037 }
            android.content.Context r3 = r5.g     // Catch:{ Exception -> 0x0037 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ Exception -> 0x0037 }
            r4 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r2 = r2.getApplicationInfo(r3, r4)     // Catch:{ Exception -> 0x0037 }
            android.os.Bundle r3 = r2.metaData     // Catch:{ Exception -> 0x0037 }
            if (r3 == 0) goto L_0x0037
            android.os.Bundle r3 = r2.metaData     // Catch:{ Exception -> 0x0037 }
            java.lang.String r3 = r3.getString(r0)     // Catch:{ Exception -> 0x0037 }
            if (r3 != 0) goto L_0x0036
            if (r6 != 0) goto L_0x0036
            android.os.Bundle r6 = r2.metaData     // Catch:{ Exception -> 0x0036 }
            java.lang.String r1 = "io.branch.sdk.BranchKey"
            java.lang.String r1 = r6.getString(r1)     // Catch:{ Exception -> 0x0036 }
            goto L_0x0037
        L_0x0036:
            r1 = r3
        L_0x0037:
            boolean r6 = android.text.TextUtils.isEmpty(r1)
            if (r6 == 0) goto L_0x0054
            android.content.Context r6 = r5.g     // Catch:{ Exception -> 0x0054 }
            android.content.res.Resources r6 = r6.getResources()     // Catch:{ Exception -> 0x0054 }
            java.lang.String r2 = "string"
            android.content.Context r3 = r5.g     // Catch:{ Exception -> 0x0054 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ Exception -> 0x0054 }
            int r0 = r6.getIdentifier(r0, r2, r3)     // Catch:{ Exception -> 0x0054 }
            java.lang.String r6 = r6.getString(r0)     // Catch:{ Exception -> 0x0054 }
            goto L_0x0055
        L_0x0054:
            r6 = r1
        L_0x0055:
            if (r6 != 0) goto L_0x0059
            java.lang.String r6 = "bnc_no_value"
        L_0x0059:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.m.a(boolean):java.lang.String");
    }

    public void c(String str) {
        a("bnc_device_fingerprint_id", str);
    }

    public String g() {
        return v("bnc_device_fingerprint_id");
    }

    public void d(String str) {
        a("bnc_session_id", str);
    }

    public String h() {
        return v("bnc_session_id");
    }

    public void e(String str) {
        a("bnc_identity_id", str);
    }

    public String i() {
        return v("bnc_identity_id");
    }

    public void f(String str) {
        a("bnc_identity", str);
    }

    public String j() {
        return v("bnc_identity");
    }

    public void g(String str) {
        a("bnc_link_click_id", str);
    }

    public String k() {
        return v("bnc_link_click_id");
    }

    public void a(Boolean bool) {
        a("bnc_triggered_by_fb_app_link", bool);
    }

    public boolean l() {
        return w("bnc_triggered_by_fb_app_link");
    }

    public void h(String str) {
        a("bnc_external_intent_uri", str);
    }

    public String m() {
        return v("bnc_external_intent_uri");
    }

    public void i(String str) {
        a("bnc_external_intent_extra", str);
    }

    public String n() {
        return v("bnc_external_intent_extra");
    }

    public void j(String str) {
        a("bnc_link_click_identifier", str);
    }

    public String o() {
        return v("bnc_link_click_identifier");
    }

    public void k(String str) {
        a("bnc_google_search_install_identifier", str);
    }

    public String p() {
        return v("bnc_google_search_install_identifier");
    }

    public void l(String str) {
        a("bnc_google_play_install_referrer_extras", str);
    }

    public String q() {
        return v("bnc_google_play_install_referrer_extras");
    }

    public void m(String str) {
        a("bnc_app_link", str);
    }

    public String r() {
        return v("bnc_app_link");
    }

    public void b(boolean z) {
        a("bnc_is_full_app_conversion", Boolean.valueOf(z));
    }

    public boolean s() {
        return w("bnc_is_full_app_conversion");
    }

    public void n(String str) {
        a("bnc_push_identifier", str);
    }

    public String t() {
        return v("bnc_push_identifier");
    }

    public String u() {
        return v("bnc_session_params");
    }

    public void o(String str) {
        a("bnc_session_params", str);
    }

    public String v() {
        return v("bnc_install_params");
    }

    public void p(String str) {
        a("bnc_install_params", str);
    }

    public void q(String str) {
        a("bnc_install_referrer", str);
    }

    public void r(String str) {
        a("bnc_user_url", str);
    }

    public String w() {
        return v("bnc_user_url");
    }

    public int x() {
        return t("bnc_is_referrable");
    }

    public void y() {
        e("bnc_is_referrable", 1);
    }

    public void z() {
        e("bnc_is_referrable", 0);
    }

    /* access modifiers changed from: 0000 */
    public boolean A() {
        return w("bnc_limit_facebook_tracking");
    }

    public void B() {
        Iterator it = I().iterator();
        while (it.hasNext()) {
            a((String) it.next(), 0);
        }
        a(new ArrayList<>());
        Iterator it2 = J().iterator();
        while (it2.hasNext()) {
            String str = (String) it2.next();
            b(str, 0);
            c(str, 0);
        }
        b(new ArrayList<>());
    }

    private ArrayList<String> I() {
        String v = v("bnc_buckets");
        if (v.equals("bnc_no_value")) {
            return new ArrayList<>();
        }
        return z(v);
    }

    private void a(ArrayList<String> arrayList) {
        if (arrayList.size() == 0) {
            a("bnc_buckets", "bnc_no_value");
        } else {
            a("bnc_buckets", c(arrayList));
        }
    }

    public void a(String str, int i) {
        ArrayList I = I();
        if (!I.contains(str)) {
            I.add(str);
            a(I);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("bnc_credit_base_");
        sb.append(str);
        e(sb.toString(), i);
    }

    public int s(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("bnc_credit_base_");
        sb.append(str);
        return t(sb.toString());
    }

    private ArrayList<String> J() {
        String v = v("bnc_actions");
        if (v.equals("bnc_no_value")) {
            return new ArrayList<>();
        }
        return z(v);
    }

    private void b(ArrayList<String> arrayList) {
        if (arrayList.size() == 0) {
            a("bnc_actions", "bnc_no_value");
        } else {
            a("bnc_actions", c(arrayList));
        }
    }

    public void b(String str, int i) {
        ArrayList J = J();
        if (!J.contains(str)) {
            J.add(str);
            b(J);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("bnc_total_base_");
        sb.append(str);
        e(sb.toString(), i);
    }

    public void c(String str, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("bnc_balance_base_");
        sb.append(str);
        e(sb.toString(), i);
    }

    private String c(ArrayList<String> arrayList) {
        String str = "";
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str2);
            sb.append(",");
            str = sb.toString();
        }
        return str.substring(0, str.length() - 1);
    }

    private ArrayList<String> z(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, str.split(","));
        return arrayList;
    }

    public int t(String str) {
        return d(str, 0);
    }

    public int d(String str, int i) {
        return c.d.getInt(str, i);
    }

    public long u(String str) {
        return c.d.getLong(str, 0);
    }

    public String v(String str) {
        return c.d.getString(str, "bnc_no_value");
    }

    public boolean w(String str) {
        return c.d.getBoolean(str, false);
    }

    public void e(String str, int i) {
        c.e.putInt(str, i);
        c.e.apply();
    }

    public void a(String str, long j) {
        c.e.putLong(str, j);
        c.e.apply();
    }

    public void a(String str, String str2) {
        c.e.putString(str, str2);
        c.e.apply();
    }

    public void a(String str, Boolean bool) {
        c.e.putBoolean(str, bool.booleanValue());
        c.e.apply();
    }

    public void x(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("bnc_branch_view_use_");
        sb.append(str);
        e(sb.toString(), y(str) + 1);
    }

    public int y(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("bnc_branch_view_use_");
        sb.append(str);
        return d(sb.toString(), 0);
    }

    public JSONObject C() {
        if (h != null) {
            return h;
        }
        String v = v("bnc_branch_analytical_data");
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(v) && !v.equals("bnc_no_value")) {
            try {
                return new JSONObject(v);
            } catch (JSONException unused) {
            }
        }
        return jSONObject;
    }

    public void D() {
        h = null;
        a("bnc_branch_analytical_data", "");
    }

    public void a(JSONObject jSONObject) {
        JSONArray jSONArray;
        String h2 = h();
        if (!h2.equals("bnc_no_value")) {
            if (h == null) {
                h = C();
            }
            try {
                if (h.has(h2)) {
                    jSONArray = h.getJSONArray(h2);
                } else {
                    JSONArray jSONArray2 = new JSONArray();
                    h.put(h2, jSONArray2);
                    jSONArray = jSONArray2;
                }
                jSONArray.put(jSONObject);
                a("bnc_branch_analytical_data", h.toString());
            } catch (JSONException unused) {
            }
        }
    }

    public void a(long j) {
        a("bnc_branch_strong_match_time", j);
    }

    public long E() {
        return u("bnc_branch_strong_match_time");
    }

    private void K() {
        String k = k();
        String o = o();
        String r = r();
        String t = t();
        this.e.clear();
        g(k);
        j(o);
        m(r);
        n(t);
        c.e.apply();
    }

    public void F() {
        a = true;
    }

    public boolean G() {
        return a;
    }

    public JSONObject H() {
        return this.f;
    }

    public static void b(String str, String str2) {
        if ((Branch.b == null && a) || (Branch.b != null && Branch.b.booleanValue())) {
            if (str2 != null) {
                Log.i(str, str2);
            } else {
                Log.i(str, "An error occurred. Unable to print the log message");
            }
        }
    }
}
