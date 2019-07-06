package com.klarna.checkout.internal.js.a;

import android.app.Activity;
import android.content.Intent;
import com.klarna.checkout.browser.BrowserActivity;
import com.klarna.checkout.browser.BrowserActivityListener;
import com.klarna.checkout.internal.js.interfaces.JSBridgeEvent;
import org.json.JSONObject;

public final class n extends o {
    public n(JSBridgeEvent jSBridgeEvent) {
        super(jSBridgeEvent, "event", "sdk:open");
    }

    static /* synthetic */ void a(n nVar, Activity activity, JSONObject jSONObject, BrowserActivityListener browserActivityListener) {
        Intent intent = new Intent(activity, BrowserActivity.class);
        intent.putExtra("url_data", jSONObject.toString());
        if (nVar.a.unitTestMode) {
            browserActivityListener.onActivityClosed();
        } else {
            activity.startActivity(intent);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044 A[Catch:{ Exception -> 0x006c }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0059 A[Catch:{ Exception -> 0x006c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r8, org.json.JSONObject r9) {
        /*
            r7 = this;
            java.lang.String r8 = "args"
            boolean r8 = r9.has(r8)     // Catch:{ JSONException -> 0x0071 }
            if (r8 == 0) goto L_0x0070
            java.lang.String r8 = "args"
            org.json.JSONArray r8 = r9.getJSONArray(r8)     // Catch:{ JSONException -> 0x0071 }
            r9 = 0
            org.json.JSONObject r5 = r8.getJSONObject(r9)     // Catch:{ JSONException -> 0x0071 }
            com.klarna.checkout.internal.js.interfaces.JSBridgeBase r8 = r7.a     // Catch:{ JSONException -> 0x0071 }
            com.klarna.checkout.internal.a r8 = r8.controller     // Catch:{ JSONException -> 0x0071 }
            android.app.Activity r4 = r8.j     // Catch:{ JSONException -> 0x0071 }
            com.klarna.checkout.internal.f r2 = new com.klarna.checkout.internal.f     // Catch:{ JSONException -> 0x0071 }
            com.klarna.checkout.internal.js.interfaces.JSBridgeBase r8 = r7.a     // Catch:{ JSONException -> 0x0071 }
            r2.<init>(r8)     // Catch:{ JSONException -> 0x0071 }
            if (r5 != 0) goto L_0x002d
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0071 }
            java.lang.String r9 = "No url data provided in "
            r8.<init>(r9)     // Catch:{ JSONException -> 0x0071 }
            r8.append(r4)     // Catch:{ JSONException -> 0x0071 }
            return
        L_0x002d:
            java.lang.String r8 = "external"
            boolean r8 = r5.has(r8)     // Catch:{ Exception -> 0x006c }
            if (r8 == 0) goto L_0x0040
            java.lang.String r8 = "external"
            boolean r8 = r5.getBoolean(r8)     // Catch:{ Exception -> 0x006c }
            if (r8 != 0) goto L_0x003e
            goto L_0x0040
        L_0x003e:
            r3 = 0
            goto L_0x0042
        L_0x0040:
            r8 = 1
            r3 = 1
        L_0x0042:
            if (r3 == 0) goto L_0x004d
            com.klarna.checkout.internal.js.interfaces.JSBridgeBase r8 = r7.a     // Catch:{ Exception -> 0x006c }
            com.klarna.checkout.internal.a r8 = r8.controller     // Catch:{ Exception -> 0x006c }
            r0 = 500(0x1f4, float:7.0E-43)
            r8.a(r0)     // Catch:{ Exception -> 0x006c }
        L_0x004d:
            com.klarna.checkout.internal.js.interfaces.JSBridgeBase r8 = r7.a     // Catch:{ Exception -> 0x006c }
            com.klarna.checkout.internal.a r8 = r8.controller     // Catch:{ Exception -> 0x006c }
            com.klarna.checkout.internal.b r8 = r8.k     // Catch:{ Exception -> 0x006c }
            boolean r8 = r8.isShowing()     // Catch:{ Exception -> 0x006c }
            if (r8 == 0) goto L_0x005b
            r9 = 333(0x14d, float:4.67E-43)
        L_0x005b:
            android.os.Handler r8 = new android.os.Handler     // Catch:{ Exception -> 0x006c }
            r8.<init>()     // Catch:{ Exception -> 0x006c }
            com.klarna.checkout.internal.js.a.n$1 r6 = new com.klarna.checkout.internal.js.a.n$1     // Catch:{ Exception -> 0x006c }
            r0 = r6
            r1 = r7
            r0.<init>(r2, r3, r4, r5)     // Catch:{ Exception -> 0x006c }
            long r0 = (long) r9     // Catch:{ Exception -> 0x006c }
            r8.postDelayed(r6, r0)     // Catch:{ Exception -> 0x006c }
            return
        L_0x006c:
            r8 = move-exception
            r8.getMessage()     // Catch:{ JSONException -> 0x0071 }
        L_0x0070:
            return
        L_0x0071:
            r8 = move-exception
            r8.getMessage()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.klarna.checkout.internal.js.a.n.a(java.lang.String, org.json.JSONObject):void");
    }
}
