package io.branch.referral;

import android.content.Context;
import com.google.a.a.a.a.a.a;
import io.branch.referral.Branch.g;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ServerRequestGetRewards */
class q extends ServerRequest {
    g g;

    public boolean a() {
        return true;
    }

    public q(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public String g() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.g());
        sb.append(this.b.i());
        return sb.toString();
    }

    public void a(z zVar, Branch branch) {
        Iterator keys = zVar.b().keys();
        boolean z = false;
        while (keys.hasNext()) {
            String str = (String) keys.next();
            try {
                int i = zVar.b().getInt(str);
                if (i != this.b.s(str)) {
                    z = true;
                }
                this.b.a(str, i);
            } catch (JSONException e) {
                a.a(e);
            }
        }
        if (this.g != null) {
            this.g.a(z, null);
        }
    }

    public void a(int i, String str) {
        if (this.g != null) {
            g gVar = this.g;
            StringBuilder sb = new StringBuilder();
            sb.append("Trouble retrieving user credits. ");
            sb.append(str);
            gVar.a(false, new c(sb.toString(), i));
        }
    }

    public boolean a(Context context) {
        if (super.b(context)) {
            return false;
        }
        if (this.g != null) {
            this.g.a(false, new c("Trouble retrieving user credits.", -102));
        }
        return true;
    }

    public void b() {
        this.g = null;
    }
}
