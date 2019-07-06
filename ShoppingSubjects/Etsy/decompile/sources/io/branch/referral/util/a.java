package io.branch.referral.util;

import android.content.Context;
import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.Defines.RequestPath;
import io.branch.referral.ServerRequest;
import io.branch.referral.ServerRequest.BRANCH_API_VERSION;
import io.branch.referral.z;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BranchEvent */
public class a {
    /* access modifiers changed from: private */
    public final String a;
    /* access modifiers changed from: private */
    public final boolean b;
    /* access modifiers changed from: private */
    public final JSONObject c;
    /* access modifiers changed from: private */
    public final JSONObject d;
    /* access modifiers changed from: private */
    public final List<BranchUniversalObject> e;

    /* renamed from: io.branch.referral.util.a$a reason: collision with other inner class name */
    /* compiled from: BranchEvent */
    private class C0180a extends ServerRequest {
        public void a(int i, String str) {
        }

        public void a(z zVar, Branch branch) {
        }

        public boolean a() {
            return false;
        }

        public boolean a(Context context) {
            return false;
        }

        public void b() {
        }

        public boolean c() {
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean e() {
            return true;
        }

        public boolean i() {
            return true;
        }

        C0180a(Context context, String str) {
            super(context, str);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Jsonkey.Name.getKey(), a.this.a);
                if (a.this.d.length() > 0) {
                    jSONObject.put(Jsonkey.CustomData.getKey(), a.this.d);
                }
                if (a.this.c.length() > 0) {
                    jSONObject.put(Jsonkey.EventData.getKey(), a.this.c);
                }
                if (a.this.b && a.this.e.size() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    jSONObject.put(Jsonkey.ContentItems.getKey(), jSONArray);
                    for (BranchUniversalObject convertToJson : a.this.e) {
                        jSONArray.put(convertToJson.convertToJson());
                    }
                }
                a(jSONObject);
            } catch (JSONException e) {
                com.google.a.a.a.a.a.a.a(e);
            }
            a(context, jSONObject);
        }

        public BRANCH_API_VERSION r() {
            return BRANCH_API_VERSION.V2;
        }
    }

    public a(BRANCH_STANDARD_EVENT branch_standard_event) {
        this(branch_standard_event.getName(), true);
    }

    private a(String str, boolean z) {
        this.c = new JSONObject();
        this.d = new JSONObject();
        this.a = str;
        this.b = z;
        this.e = new ArrayList();
    }

    public a a(BranchUniversalObject... branchUniversalObjectArr) {
        Collections.addAll(this.e, branchUniversalObjectArr);
        return this;
    }

    public boolean a(Context context) {
        String path = (this.b ? RequestPath.TrackStandardEvent : RequestPath.TrackCustomEvent).getPath();
        if (Branch.b() == null) {
            return false;
        }
        Branch.b().a((ServerRequest) new C0180a(context, path));
        return true;
    }
}
