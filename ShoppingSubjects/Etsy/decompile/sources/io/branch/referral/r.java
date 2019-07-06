package io.branch.referral;

import android.content.Context;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.a.a.a.a.a.a;
import io.branch.referral.Branch.f;
import io.branch.referral.Defines.Jsonkey;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ServerRequestIdentifyUserRequest */
class r extends ServerRequest {
    f g;
    String h = null;

    public boolean a() {
        return false;
    }

    public boolean c() {
        return true;
    }

    public r(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void a(z zVar, Branch branch) {
        try {
            if (h() != null && h().has(Jsonkey.Identity.getKey())) {
                this.b.f(h().getString(Jsonkey.Identity.getKey()));
            }
            this.b.e(zVar.b().getString(Jsonkey.IdentityID.getKey()));
            this.b.r(zVar.b().getString(Jsonkey.Link.getKey()));
            if (zVar.b().has(Jsonkey.ReferringData.getKey())) {
                this.b.p(zVar.b().getString(Jsonkey.ReferringData.getKey()));
            }
            if (this.g != null) {
                this.g.a(branch.h(), null);
            }
        } catch (JSONException e) {
            a.a(e);
        }
    }

    public void a(int i, String str) {
        if (this.g != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(ResponseConstants.ERROR_MESSAGE, "Trouble reaching server. Please try again in a few minutes");
            } catch (JSONException e) {
                a.a(e);
            }
            f fVar = this.g;
            StringBuilder sb = new StringBuilder();
            sb.append("Trouble setting the user alias. ");
            sb.append(str);
            fVar.a(jSONObject, new c(sb.toString(), i));
        }
    }

    public boolean a(Context context) {
        if (!super.b(context)) {
            if (this.g != null) {
                this.g.a(null, new c("Trouble setting the user alias.", -102));
            }
            return true;
        }
        try {
            String string = h().getString(Jsonkey.Identity.getKey());
            if (string == null || string.length() == 0 || string.equals(this.b.j())) {
                return true;
            }
            return false;
        } catch (JSONException unused) {
            return true;
        }
    }

    public void b() {
        this.g = null;
    }
}
