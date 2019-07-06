package com.klarna.checkout.internal.js.a;

import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;
import android.os.Build;
import android.os.Build.VERSION;
import com.klarna.checkout.internal.a.c;
import com.klarna.checkout.internal.a.d;
import com.klarna.checkout.internal.a.e;
import com.klarna.checkout.internal.js.interfaces.JSBridgeEvent;
import com.klarna.checkout.sdk.R;
import java.net.URI;
import java.util.HashMap;
import org.json.JSONObject;

public final class b extends o {
    public b(JSBridgeEvent jSBridgeEvent) {
        super(jSBridgeEvent, "http", "request");
    }

    /* access modifiers changed from: 0000 */
    @Deprecated
    public final void a(String str, JSONObject jSONObject) {
        try {
            boolean z = this.a.isMessageFromOverlay;
            jSONObject.put("requestingURL", this.a.controller.a(z).getUrl());
            jSONObject.put("wasRequestFromOverlay", z);
            jSONObject.put("callingEvent", str);
            String string = jSONObject.has("interface") ? jSONObject.getString("interface") : null;
            if (jSONObject.has("data")) {
                c cVar = new c(this.a.controller);
                cVar.c = string;
                cVar.e = new HashMap<>();
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                JSONObject jSONObject3 = jSONObject2.getJSONObject("headers");
                if (jSONObject3.names() != null) {
                    for (int i = 0; i < jSONObject3.names().length(); i++) {
                        String string2 = jSONObject3.names().getString(i);
                        cVar.e.put(string2, jSONObject3.getString(string2));
                    }
                }
                StringBuilder sb = new StringBuilder("KlarnaCheckoutSDK ");
                sb.append(cVar.a.j.getString(R.string.SDK_VERSION_NAME));
                sb.append(" (");
                sb.append(Build.MODEL);
                sb.append("; Android ");
                sb.append(VERSION.RELEASE);
                sb.append("; ");
                sb.append(cVar.a.j.getResources().getConfiguration().locale);
                sb.append(")");
                cVar.e.put("User-Agent", sb.toString());
                String string3 = jSONObject2.getString("url");
                String string4 = jSONObject2.getString("type");
                if (cVar.a.c != null) {
                    cVar.e.put("Cookie", cVar.a().a(new URI(string3)));
                }
                if (jSONObject.has("requestingURL")) {
                    cVar.i = jSONObject.getString("requestingURL");
                }
                if (jSONObject.has("id")) {
                    cVar.d = jSONObject.getString("id");
                }
                if (jSONObject.has("wasRequestFromOverlay")) {
                    cVar.f = jSONObject.getBoolean("wasRequestFromOverlay");
                }
                if (jSONObject.has("callingEvent")) {
                    cVar.h = jSONObject.getString("callingEvent");
                }
                if (jSONObject2.has("body")) {
                    cVar.g = jSONObject2.getString("body");
                } else {
                    cVar.g = null;
                }
                if (string != null) {
                    e.a(cVar.a.j, cVar.c, new d(cVar, string3, string4) {
                        final /* synthetic */ c a;
                        final /* synthetic */ String b;
                        final /* synthetic */ String c;

                        {
                            this.a = r2;
                            this.b = r3;
                            this.c = r4;
                        }

                        public final void a() {
                            try {
                                new b(this.a, this.b, this.c).execute(new String[0]);
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }

                        public final void a(Network network, NetworkCallback networkCallback) {
                            try {
                                c.this.b = networkCallback;
                                new b(this.a, this.b, this.c, network).execute(new String[0]);
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }
                    });
                    return;
                }
                try {
                    new com.klarna.checkout.internal.a.b(cVar, string3, string4).execute(new String[0]);
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }
}
