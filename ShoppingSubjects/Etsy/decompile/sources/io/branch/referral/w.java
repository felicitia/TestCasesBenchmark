package io.branch.referral;

import android.content.Context;
import android.util.Log;
import com.google.a.a.a.a.a.a;
import io.branch.indexing.b;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.Defines.RequestPath;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ServerRequestRegisterClose */
class w extends ServerRequest {
    public void a(int i, String str) {
    }

    public boolean a() {
        return false;
    }

    public void b() {
    }

    /* access modifiers changed from: 0000 */
    public boolean d() {
        return false;
    }

    public w(Context context) {
        super(context, RequestPath.RegisterClose.getPath());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Jsonkey.DeviceFingerprintID.getKey(), this.b.g());
            jSONObject.put(Jsonkey.IdentityID.getKey(), this.b.i());
            jSONObject.put(Jsonkey.SessionID.getKey(), this.b.h());
            if (!this.b.k().equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.LinkClickID.getKey(), this.b.k());
            }
            JSONObject a = b.a().a(context);
            if (a != null) {
                jSONObject.put(Jsonkey.ContentDiscovery.getKey(), a);
            }
            if (k.a() != null) {
                jSONObject.put(Jsonkey.AppVersion.getKey(), k.a().b());
            }
            a(jSONObject);
        } catch (JSONException e) {
            a.a(e);
            this.e = true;
        }
    }

    public w(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        Log.i("BranchSDK", "Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
        return true;
    }

    public void a(z zVar, Branch branch) {
        this.b.o("bnc_no_value");
    }
}
