package io.branch.referral;

import android.content.Context;
import android.util.Log;
import io.branch.referral.Branch.b;
import io.branch.referral.g;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BranchUrlBuilder */
abstract class g<T extends g> {
    protected JSONObject a;
    protected String b;
    protected String c;
    protected String d;
    protected String e;
    protected String f;
    protected int g = 0;
    protected int h = 0;
    protected ArrayList<String> i;
    protected Branch j = Branch.b();
    private boolean k;
    private final Context l;

    protected g(Context context) {
        this.l = context.getApplicationContext();
        this.k = true;
    }

    public T a(List<String> list) {
        if (this.i == null) {
            this.i = new ArrayList<>();
        }
        this.i.addAll(list);
        return this;
    }

    public T a(String str, Object obj) {
        try {
            if (this.a == null) {
                this.a = new JSONObject();
            }
            this.a.put(str, obj);
        } catch (JSONException unused) {
        }
        return this;
    }

    public T a(boolean z) {
        this.k = z;
        return this;
    }

    /* access modifiers changed from: protected */
    public String b() {
        if (this.j == null) {
            return null;
        }
        o oVar = new o(this.l, this.f, this.g, this.h, this.i, this.b, this.c, this.d, this.e, h.a(this.a), null, false, this.k);
        return this.j.a(oVar);
    }

    /* access modifiers changed from: protected */
    public void b(b bVar) {
        b(bVar, false);
    }

    /* access modifiers changed from: protected */
    public void b(b bVar, boolean z) {
        b bVar2 = bVar;
        if (this.j != null) {
            o oVar = new o(this.l, this.f, this.g, this.h, this.i, this.b, this.c, this.d, this.e, h.a(this.a), bVar2, true, this.k);
            oVar.a(z);
            this.j.a(oVar);
            return;
        }
        if (bVar2 != null) {
            bVar2.a(null, new c("session has not been initialized", -101));
        }
        Log.i("BranchSDK", "Branch Warning: User session has not been initialized");
    }
}
