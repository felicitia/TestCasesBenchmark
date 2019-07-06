package io.branch.referral;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.google.a.a.a.a.a.a;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.Defines.RequestPath;
import io.branch.referral.i.b;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ServerRequestActionCompleted */
class n extends ServerRequest {
    private final b g;

    public void a(int i, String str) {
    }

    public boolean a() {
        return false;
    }

    public void b() {
    }

    public boolean c() {
        return true;
    }

    public n(Context context, String str, JSONObject jSONObject, b bVar) {
        super(context, RequestPath.CompletedAction.getPath());
        this.g = bVar;
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(Jsonkey.IdentityID.getKey(), this.b.i());
            jSONObject2.put(Jsonkey.DeviceFingerprintID.getKey(), this.b.g());
            jSONObject2.put(Jsonkey.SessionID.getKey(), this.b.h());
            if (!this.b.k().equals("bnc_no_value")) {
                jSONObject2.put(Jsonkey.LinkClickID.getKey(), this.b.k());
            }
            jSONObject2.put(Jsonkey.Event.getKey(), str);
            if (jSONObject != null) {
                jSONObject2.put(Jsonkey.Metadata.getKey(), jSONObject);
            }
            a(context, jSONObject2);
            a(jSONObject2);
        } catch (JSONException e) {
            a.a(e);
            this.e = true;
        }
        if (str != null && str.equalsIgnoreCase("purchase")) {
            Log.e("BranchSDK", "Warning: You are sending a purchase event with our non-dedicated purchase function. Please see function sendCommerceEvent");
        }
    }

    public n(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
        this.g = null;
    }

    public void a(z zVar, Branch branch) {
        if (zVar.b() != null && zVar.b().has(Jsonkey.BranchViewData.getKey()) && Branch.b().e != null && Branch.b().e.get() != null) {
            String str = "";
            try {
                JSONObject h = h();
                if (h != null && h.has(Jsonkey.Event.getKey())) {
                    str = h.getString(Jsonkey.Event.getKey());
                }
                if (Branch.b().e != null) {
                    Activity activity = (Activity) Branch.b().e.get();
                    i.a().a(zVar.b().getJSONObject(Jsonkey.BranchViewData.getKey()), str, (Context) activity, this.g);
                }
            } catch (JSONException unused) {
                if (this.g != null) {
                    this.g.a(-201, "Unable to show branch view. Branch view received is invalid ", str);
                }
            }
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        Log.i("BranchSDK", "Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
        return true;
    }
}
