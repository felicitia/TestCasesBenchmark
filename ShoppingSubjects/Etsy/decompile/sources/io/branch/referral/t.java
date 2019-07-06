package io.branch.referral;

import android.content.Context;
import android.util.Log;
import com.google.a.a.a.a.a.a;
import io.branch.referral.Branch.k;
import io.branch.referral.Defines.Jsonkey;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ServerRequestLogout */
class t extends ServerRequest {
    private k g;

    public boolean a() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean d() {
        return false;
    }

    public t(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void a(z zVar, Branch branch) {
        try {
            this.b.d(zVar.b().getString(Jsonkey.SessionID.getKey()));
            this.b.e(zVar.b().getString(Jsonkey.IdentityID.getKey()));
            this.b.r(zVar.b().getString(Jsonkey.Link.getKey()));
            this.b.p("bnc_no_value");
            this.b.o("bnc_no_value");
            this.b.f("bnc_no_value");
            this.b.B();
            if (this.g == null) {
                return;
            }
        } catch (JSONException e) {
            a.a(e);
            if (this.g == null) {
                return;
            }
        } catch (Throwable th) {
            if (this.g != null) {
                this.g.a(true, null);
            }
            throw th;
        }
        this.g.a(true, null);
    }

    public void a(int i, String str) {
        if (this.g != null) {
            k kVar = this.g;
            StringBuilder sb = new StringBuilder();
            sb.append("Logout error. ");
            sb.append(str);
            kVar.a(false, new c(sb.toString(), i));
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        Log.i("BranchSDK", "Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
        if (this.g != null) {
            this.g.a(false, new c("Logout failed", -102));
        }
        return true;
    }

    public void b() {
        this.g = null;
    }
}
