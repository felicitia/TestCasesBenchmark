package com.paypal.android.sdk.onetouch.core.metadata;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class w extends aa {
    private String f;
    private HashMap<String, String> g;
    private Map<String, String> h = new HashMap();
    private Handler i;
    private boolean j;

    static {
        h.class.getSimpleName();
    }

    public w(String str, HashMap<String, String> hashMap, Handler handler, boolean z) {
        this.f = str;
        this.g = hashMap;
        this.i = handler;
        this.j = z;
    }

    public final void run() {
        Handler handler;
        Message obtain;
        if (this.i != null) {
            try {
                this.i.sendMessage(Message.obtain(this.i, 0, this.f));
                if (!this.j) {
                    this.h.put("CLIENT-AUTH", "No cert");
                }
                this.h.put("X-PAYPAL-RESPONSE-DATA-FORMAT", "NV");
                this.h.put("X-PAYPAL-REQUEST-DATA-FORMAT", "NV");
                this.h.put("X-PAYPAL-SERVICE-VERSION", "1.0.0");
                if (this.j) {
                    t a = h.b.a();
                    a.a(Uri.parse(this.f));
                    a.a(this.h);
                    HashMap<String, String> hashMap = this.g;
                    StringBuilder sb = new StringBuilder();
                    boolean z = true;
                    for (Entry entry : hashMap.entrySet()) {
                        if (z) {
                            z = false;
                        } else {
                            sb.append("&");
                        }
                        sb.append(URLEncoder.encode((String) entry.getKey(), "UTF-8"));
                        sb.append("=");
                        sb.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
                    }
                    int a2 = a.a(sb.toString().getBytes("UTF-8"));
                    if (a2 != 200) {
                        StringBuilder sb2 = new StringBuilder("Network Connection Error with wrong http code: ");
                        sb2.append(a2);
                        throw new Exception(sb2.toString());
                    }
                    String str = new String(a.b(), "UTF-8");
                    handler = this.i;
                    obtain = Message.obtain(this.i, 2, str);
                } else {
                    handler = this.i;
                    obtain = Message.obtain(this.i, 2, "not supported");
                }
                handler.sendMessage(obtain);
            } catch (Exception e) {
                this.i.sendMessage(Message.obtain(this.i, 1, e));
            } catch (Throwable th) {
                ab.a().b(this);
                throw th;
            }
            ab.a().b(this);
        }
    }
}
