package io.branch.referral;

import android.content.Context;
import com.google.a.a.a.a.a.a;
import io.branch.referral.Branch.b;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.Defines.LinkParam;
import io.branch.referral.Defines.RequestPath;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ServerRequestCreateUrl */
class o extends ServerRequest {
    private d g;
    private boolean h = true;
    private b i;
    private boolean j;
    private boolean k = true;

    public boolean a() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean d() {
        return false;
    }

    public o(Context context, String str, int i2, int i3, Collection<String> collection, String str2, String str3, String str4, String str5, JSONObject jSONObject, b bVar, boolean z, boolean z2) {
        super(context, RequestPath.GetURL.getPath());
        this.i = bVar;
        this.h = z;
        this.k = z2;
        this.g = new d();
        try {
            this.g.put(Jsonkey.IdentityID.getKey(), this.b.i());
            this.g.put(Jsonkey.DeviceFingerprintID.getKey(), this.b.g());
            this.g.put(Jsonkey.SessionID.getKey(), this.b.h());
            if (!this.b.k().equals("bnc_no_value")) {
                this.g.put(Jsonkey.LinkClickID.getKey(), this.b.k());
            }
            this.g.a(i2);
            this.g.b(i3);
            this.g.a(collection);
            this.g.a(str);
            this.g.b(str2);
            this.g.c(str3);
            this.g.d(str4);
            this.g.e(str5);
            this.g.a(jSONObject);
            a((JSONObject) this.g);
        } catch (JSONException e) {
            a.a(e);
            this.e = true;
        }
    }

    public o(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public d u() {
        return this.g;
    }

    /* access modifiers changed from: 0000 */
    public boolean v() {
        return this.k;
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        if (this.i != null) {
            this.i.a(null, new c("Trouble creating a URL.", -102));
        }
        return true;
    }

    public void a(z zVar, Branch branch) {
        try {
            String string = zVar.b().getString("url");
            if (this.i != null) {
                this.i.a(string, null);
            }
            c(string);
        } catch (Exception e) {
            a.a(e);
        }
    }

    public void a(String str) {
        if (this.i != null) {
            this.i.a(str, null);
        }
        c(str);
    }

    public void a(int i2, String str) {
        if (this.i != null) {
            String str2 = null;
            if (this.k) {
                str2 = w();
            }
            b bVar = this.i;
            StringBuilder sb = new StringBuilder();
            sb.append("Trouble creating a URL. ");
            sb.append(str);
            bVar.a(str2, new c(sb.toString(), i2));
        }
    }

    public String w() {
        if (!this.b.w().equals("bnc_no_value")) {
            return b(this.b.w());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("https://bnc.lt/a/");
        sb.append(this.b.f());
        return b(sb.toString());
    }

    public void x() {
        if (this.i != null) {
            this.i.a(null, new c("Trouble creating a URL.", -105));
        }
    }

    public void b() {
        this.i = null;
    }

    public boolean y() {
        return this.h;
    }

    private String b(String str) {
        try {
            if (Branch.b().a() && !str.contains("https://bnc.lt/a/")) {
                str = str.replace(new URL(str).getQuery(), "");
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str.contains("?") ? "" : "?");
            String sb2 = sb.toString();
            try {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(sb2);
                sb3.append(sb2.endsWith("?") ? "" : "&");
                str = sb3.toString();
                Collection<String> a = this.g.a();
                if (a != null) {
                    for (String str2 : a) {
                        if (str2 != null && str2.length() > 0) {
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append(str);
                            sb4.append(LinkParam.Tags);
                            sb4.append("=");
                            sb4.append(URLEncoder.encode(str2, "UTF8"));
                            sb4.append("&");
                            str = sb4.toString();
                        }
                    }
                }
                String b = this.g.b();
                if (b != null && b.length() > 0) {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(str);
                    sb5.append(LinkParam.Alias);
                    sb5.append("=");
                    sb5.append(URLEncoder.encode(b, "UTF8"));
                    sb5.append("&");
                    str = sb5.toString();
                }
                String e = this.g.e();
                if (e != null && e.length() > 0) {
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(str);
                    sb6.append(LinkParam.Channel);
                    sb6.append("=");
                    sb6.append(URLEncoder.encode(e, "UTF8"));
                    sb6.append("&");
                    str = sb6.toString();
                }
                String f = this.g.f();
                if (f != null && f.length() > 0) {
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append(str);
                    sb7.append(LinkParam.Feature);
                    sb7.append("=");
                    sb7.append(URLEncoder.encode(f, "UTF8"));
                    sb7.append("&");
                    str = sb7.toString();
                }
                String g2 = this.g.g();
                if (g2 != null && g2.length() > 0) {
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append(str);
                    sb8.append(LinkParam.Stage);
                    sb8.append("=");
                    sb8.append(URLEncoder.encode(g2, "UTF8"));
                    sb8.append("&");
                    str = sb8.toString();
                }
                String h2 = this.g.h();
                if (h2 != null && h2.length() > 0) {
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append(str);
                    sb9.append(LinkParam.Campaign);
                    sb9.append("=");
                    sb9.append(URLEncoder.encode(h2, "UTF8"));
                    sb9.append("&");
                    str = sb9.toString();
                }
                long c = (long) this.g.c();
                StringBuilder sb10 = new StringBuilder();
                sb10.append(str);
                sb10.append(LinkParam.Type);
                sb10.append("=");
                sb10.append(c);
                sb10.append("&");
                sb2 = sb10.toString();
                long d = (long) this.g.d();
                StringBuilder sb11 = new StringBuilder();
                sb11.append(sb2);
                sb11.append(LinkParam.Duration);
                sb11.append("=");
                sb11.append(d);
                str = sb11.toString();
                String jSONObject = this.g.i().toString();
                if (jSONObject == null || jSONObject.length() <= 0) {
                    return str;
                }
                String encode = URLEncoder.encode(b.b(jSONObject.getBytes(), 2), "UTF8");
                StringBuilder sb12 = new StringBuilder();
                sb12.append(str);
                sb12.append("&source=android&data=");
                sb12.append(encode);
                return sb12.toString();
            } catch (Exception unused) {
                str = sb2;
                this.i.a(null, new c("Trouble creating a URL.", -116));
                return str;
            }
        } catch (Exception unused2) {
            this.i.a(null, new c("Trouble creating a URL.", -116));
            return str;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z) {
        this.j = z;
    }

    /* access modifiers changed from: 0000 */
    public boolean z() {
        return this.j;
    }

    private void c(String str) {
        JSONObject j2 = this.g.j();
        if (z() && j2 != null) {
            new l().a("Branch Share", j2, this.b.i());
        }
    }
}
