package io.branch.referral;

import android.text.TextUtils;
import com.b.a.a.a.b;
import com.b.a.a.a.c;
import io.branch.referral.Defines.Jsonkey;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ExtendedAnswerProvider */
class l {
    l() {
    }

    public void a(String str, JSONObject jSONObject, String str2) {
        try {
            c cVar = new c(str);
            if (jSONObject != null) {
                a(cVar, jSONObject, "");
                cVar.a(Jsonkey.BranchIdentity.getKey(), str2);
                b.a().a(cVar);
            }
        } catch (Throwable unused) {
        }
    }

    private void a(c cVar, JSONObject jSONObject, String str) throws JSONException {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            Object obj = jSONObject.get(str2);
            if (!str2.startsWith("+")) {
                if (obj instanceof JSONObject) {
                    JSONObject jSONObject2 = (JSONObject) obj;
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(str2);
                    sb.append(".");
                    a(cVar, jSONObject2, sb.toString());
                } else if (obj instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) obj;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str2);
                    sb2.append(".");
                    a(cVar, jSONArray, sb2.toString());
                } else {
                    a(cVar, str, str2, jSONObject.getString(str2));
                }
            }
        }
    }

    private void a(c cVar, JSONArray jSONArray, String str) throws JSONException {
        for (int i = 0; i < jSONArray.length(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("~");
            sb.append(Integer.toString(i));
            a(cVar, str, sb.toString(), jSONArray.getString(i));
        }
    }

    private void a(c cVar, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        if (str2.startsWith("~")) {
            StringBuilder sb = new StringBuilder();
            sb.append(str.replaceFirst("~", ""));
            sb.append(str2.replaceFirst("~", ""));
            cVar.a(sb.toString(), str3);
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("$");
        sb2.append(Jsonkey.IdentityID.getKey());
        if (str2.equals(sb2.toString())) {
            cVar.a(Jsonkey.ReferringBranchIdentity.getKey(), str3);
        }
    }
}
