package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.gv;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class g implements ae<Object> {
    private final Object a = new Object();
    private final Map<String, h> b = new HashMap();

    public final void a(String str, h hVar) {
        synchronized (this.a) {
            this.b.put(str, hVar);
        }
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str;
        String str2 = (String) map.get("id");
        String str3 = (String) map.get("fail");
        String str4 = (String) map.get("fail_reason");
        String str5 = (String) map.get("fail_stack");
        String str6 = (String) map.get("result");
        if (TextUtils.isEmpty(str5)) {
            str4 = "Unknown Fail Reason.";
        }
        if (TextUtils.isEmpty(str5)) {
            str = "";
        } else {
            String str7 = "\n";
            String valueOf = String.valueOf(str5);
            str = valueOf.length() != 0 ? str7.concat(valueOf) : new String(str7);
        }
        synchronized (this.a) {
            h hVar = (h) this.b.remove(str2);
            if (hVar == null) {
                String str8 = "Received result for unexpected method invocation: ";
                String valueOf2 = String.valueOf(str2);
                gv.e(valueOf2.length() != 0 ? str8.concat(valueOf2) : new String(str8));
            } else if (!TextUtils.isEmpty(str3)) {
                String valueOf3 = String.valueOf(str4);
                String valueOf4 = String.valueOf(str);
                hVar.a(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3));
            } else if (str6 == null) {
                hVar.a((JSONObject) null);
            } else {
                try {
                    JSONObject jSONObject = new JSONObject(str6);
                    if (gv.a()) {
                        String str9 = "Result GMSG: ";
                        String valueOf5 = String.valueOf(jSONObject.toString(2));
                        gv.a(valueOf5.length() != 0 ? str9.concat(valueOf5) : new String(str9));
                    }
                    hVar.a(jSONObject);
                } catch (JSONException e) {
                    hVar.a(e.getMessage());
                }
            }
        }
    }
}
