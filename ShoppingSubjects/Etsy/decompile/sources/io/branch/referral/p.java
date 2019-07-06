package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch.d;
import org.json.JSONObject;

/* compiled from: ServerRequestGetRewardHistory */
class p extends ServerRequest {
    d g;

    public boolean a() {
        return false;
    }

    public p(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void a(z zVar, Branch branch) {
        if (this.g != null) {
            this.g.a(zVar.c(), null);
        }
    }

    public void a(int i, String str) {
        if (this.g != null) {
            d dVar = this.g;
            StringBuilder sb = new StringBuilder();
            sb.append("Trouble retrieving user credit history. ");
            sb.append(str);
            dVar.a(null, new c(sb.toString(), i));
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        if (this.g != null) {
            this.g.a(null, new c("Trouble retrieving user credit history.", -102));
        }
        return true;
    }

    public void b() {
        this.g = null;
    }
}
