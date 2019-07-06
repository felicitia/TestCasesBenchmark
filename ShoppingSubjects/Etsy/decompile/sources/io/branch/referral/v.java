package io.branch.referral;

import android.content.Context;
import com.google.a.a.a.a.a.a;
import io.branch.referral.Branch.g;
import io.branch.referral.Defines.Jsonkey;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ServerRequestRedeemRewards */
class v extends ServerRequest {
    g g;
    int h = 0;

    public boolean a() {
        return false;
    }

    public v(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public boolean a(Context context) {
        if (!super.b(context)) {
            if (this.g != null) {
                this.g.a(false, new c("Trouble redeeming rewards.", -102));
            }
            return true;
        } else if (this.h > 0) {
            return false;
        } else {
            if (this.g != null) {
                this.g.a(false, new c("Trouble redeeming rewards.", -107));
            }
            return true;
        }
    }

    public void a(z zVar, Branch branch) {
        c cVar;
        JSONObject h2 = h();
        boolean z = false;
        if (h2 != null && h2.has(Jsonkey.Bucket.getKey()) && h2.has(Jsonkey.Amount.getKey())) {
            try {
                int i = h2.getInt(Jsonkey.Amount.getKey());
                String string = h2.getString(Jsonkey.Bucket.getKey());
                if (i > 0) {
                    z = true;
                }
                this.b.a(string, this.b.s(string) - i);
            } catch (JSONException e) {
                a.a(e);
            }
        }
        if (this.g != null) {
            if (z) {
                cVar = null;
            } else {
                cVar = new c("Trouble redeeming rewards.", -107);
            }
            this.g.a(z, cVar);
        }
    }

    public void a(int i, String str) {
        if (this.g != null) {
            g gVar = this.g;
            StringBuilder sb = new StringBuilder();
            sb.append("Trouble redeeming rewards. ");
            sb.append(str);
            gVar.a(false, new c(sb.toString(), i));
        }
    }

    public void b() {
        this.g = null;
    }
}
