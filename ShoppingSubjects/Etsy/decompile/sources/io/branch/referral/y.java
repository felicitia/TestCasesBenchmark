package io.branch.referral;

import android.content.Context;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.ReceiptsRequest;
import com.google.a.a.a.a.a.a;
import io.branch.referral.Branch.f;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.Defines.RequestPath;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ServerRequestRegisterOpen */
class y extends s {
    f h;

    public boolean a() {
        return false;
    }

    public String w() {
        return ReceiptsRequest.STATUS_OPEN;
    }

    y(Context context, f fVar, aa aaVar) {
        super(context, RequestPath.RegisterOpen.getPath(), aaVar);
        this.h = fVar;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Jsonkey.DeviceFingerprintID.getKey(), this.b.g());
            jSONObject.put(Jsonkey.IdentityID.getKey(), this.b.i());
            a(jSONObject);
        } catch (JSONException e) {
            a.a(e);
            this.e = true;
        }
    }

    y(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void a(z zVar, Branch branch) {
        super.a(zVar, branch);
        try {
            if (zVar.b().has(Jsonkey.LinkClickID.getKey())) {
                this.b.g(zVar.b().getString(Jsonkey.LinkClickID.getKey()));
            } else {
                this.b.g("bnc_no_value");
            }
            if (zVar.b().has(Jsonkey.Data.getKey())) {
                JSONObject jSONObject = new JSONObject(zVar.b().getString(Jsonkey.Data.getKey()));
                if (jSONObject.has(Jsonkey.Clicked_Branch_Link.getKey()) && jSONObject.getBoolean(Jsonkey.Clicked_Branch_Link.getKey()) && this.b.v().equals("bnc_no_value") && this.b.x() == 1) {
                    this.b.p(zVar.b().getString(Jsonkey.Data.getKey()));
                }
            }
            if (zVar.b().has(Jsonkey.Data.getKey())) {
                this.b.o(zVar.b().getString(Jsonkey.Data.getKey()));
            } else {
                this.b.o("bnc_no_value");
            }
            if (this.h != null && !branch.f) {
                this.h.a(branch.i(), null);
            }
            this.b.a(this.g.e());
        } catch (Exception e) {
            a.a(e);
        }
        b(zVar, branch);
    }

    /* access modifiers changed from: 0000 */
    public void a(f fVar) {
        if (fVar != null) {
            this.h = fVar;
        }
    }

    public void a(int i, String str) {
        if (this.h != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(ResponseConstants.ERROR_MESSAGE, "Trouble reaching server. Please try again in a few minutes");
            } catch (JSONException e) {
                a.a(e);
            }
            f fVar = this.h;
            StringBuilder sb = new StringBuilder();
            sb.append("Trouble initializing Branch. ");
            sb.append(str);
            fVar.a(jSONObject, new c(sb.toString(), i));
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        if (this.h != null) {
            this.h.a(null, new c("Trouble initializing Branch.", -102));
        }
        return true;
    }

    public void b() {
        this.h = null;
    }

    public boolean v() {
        return this.h != null;
    }
}
