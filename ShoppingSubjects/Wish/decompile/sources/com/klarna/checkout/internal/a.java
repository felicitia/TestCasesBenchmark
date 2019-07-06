package com.klarna.checkout.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.os.Build.VERSION;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.klarna.checkout.SignalListener;
import com.klarna.checkout.internal.a.d;
import com.klarna.checkout.internal.a.e;
import com.klarna.checkout.internal.b.c;
import com.klarna.checkout.internal.c.b;
import com.klarna.checkout.internal.js.interfaces.JSBridgeEvent;
import java.util.Queue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public final class a {
    public static String a;
    public long b;
    public final c c;
    public final WebView d;
    public final b e;
    protected boolean f;
    public WebView g;
    public SignalListener i;
    public Activity j;
    public b k;
    public JSONArray l;
    public String m;

    public a(Activity activity, String str) {
        String str2;
        this.m = str;
        this.f = VERSION.SDK_INT < 17;
        this.j = activity;
        if (!this.f && this.d != null) {
            if (this.k != null) {
                ViewGroup viewGroup = (ViewGroup) this.d.getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                this.k.dismiss();
            }
            this.k = b.a(this.j, this.d);
        }
        this.l = new JSONArray();
        if (VERSION.SDK_INT >= 22) {
            e.a(this.j, null, new d() {
                public final void a() {
                    a.this.l = new JSONArray();
                }

                @TargetApi(22)
                public final void a(Network network, NetworkCallback networkCallback) {
                    LinkProperties linkProperties = ((ConnectivityManager) a.this.j.getApplication().getSystemService("connectivity")).getLinkProperties(network);
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("name", linkProperties.getInterfaceName());
                        jSONObject.put("inet", ((LinkAddress) linkProperties.getLinkAddresses().get(0)).getAddress());
                        a.this.l.put(jSONObject);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    e.a(a.this.j, networkCallback);
                }
            });
        }
        this.j.getWindow().setFlags(8192, 8192);
        if (this.f) {
            this.d = null;
            this.k = null;
            this.e = null;
            this.c = null;
            return;
        }
        this.e = new b();
        this.c = new c(".kco_dtas");
        c cVar = this.c;
        Activity activity2 = this.j;
        if (!c.a(activity2)) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity2);
            com.klarna.checkout.internal.b.b bVar = cVar.b;
            StringBuilder sb = new StringBuilder("{\"");
            sb.append("storage_set");
            sb.append("\":\"\", \"");
            sb.append("cookies");
            sb.append("\":[]}");
            String a2 = bVar.a(com.klarna.checkout.internal.c.c.a(sb.toString()));
            com.klarna.checkout.internal.b.b bVar2 = cVar.b;
            StringBuilder sb2 = new StringBuilder("com.klarna.data");
            sb2.append(cVar.c);
            str2 = bVar2.b(defaultSharedPreferences.getString(sb2.toString(), a2));
        } else {
            str2 = cVar.a();
        }
        if (!"-1".equals(str2) && str2 != null) {
            try {
                JSONObject jSONObject = new JSONObject(com.klarna.checkout.internal.c.c.b(str2));
                if (jSONObject.has("storage_set")) {
                    cVar.d = jSONObject.getString("storage_set");
                }
                JSONArray jSONArray = jSONObject.getJSONArray("cookies");
                com.klarna.checkout.internal.b.a aVar = cVar.a;
                new g() {
                    public final void a(String str) {
                    }
                };
                aVar.a(jSONArray);
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        WebView webView = new WebView(this.j);
        webView.setTag("fullscreen");
        webView.setId(654322);
        com.klarna.checkout.internal.c.e.a(webView);
        webView.setWebViewClient(com.klarna.checkout.internal.c.e.a(this));
        if (!this.f) {
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setAppCachePath(this.j.getApplicationContext().getCacheDir().getPath());
            webView.addJavascriptInterface(new JSBridgeEvent(this, true), "KCO_NATIVE");
            webView.addJavascriptInterface(new JSBridgeEvent(this, true), "KCO_HANDSHAKE");
            webView.setHorizontalScrollBarEnabled(false);
            webView.setVerticalScrollBarEnabled(false);
            webView.setVerticalFadingEdgeEnabled(false);
        }
        this.d = webView;
        this.k = b.a(activity, this.d);
    }

    public static String a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("action", str);
            jSONObject.put("event", str2);
        } catch (JSONException e2) {
            e2.getMessage();
        }
        return jSONObject.toString();
    }

    public final WebView a(boolean z) {
        if (!z) {
            if (this.g != null && this.g.getTag() == null) {
                this.g.setTag("checkout");
            }
            return this.g;
        }
        if (this.g != null) {
            this.d.setTag("fullscreen");
        }
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public final void a() {
        if (this.e != null && !d()) {
            a(a("event", "sdk:shown"), "*", false);
        }
    }

    public final void a(int i2) {
        if (this.k != null) {
            new Handler().postDelayed(new Runnable() {
                public final void run() {
                    a.this.k.hide();
                }
            }, (long) i2);
        }
    }

    public final void a(String str, String str2, boolean z) {
        Queue<d> queue;
        if (!this.f) {
            WebView a2 = a(z);
            if (a2 != null) {
                d dVar = new d(str, str2, a2);
                b bVar = this.e;
                if (!bVar.c && dVar.b()) {
                    StringBuilder sb = new StringBuilder(".....fullscreen queued message was : ");
                    sb.append(dVar.c);
                    sb.append(" ");
                    sb.append(dVar.a);
                    queue = bVar.a;
                } else if (bVar.d || dVar.b()) {
                    StringBuilder sb2 = new StringBuilder("Preparing to send message id: ");
                    sb2.append(dVar.c);
                    sb2.append(" ");
                    sb2.append(dVar.a);
                    dVar.a();
                } else {
                    StringBuilder sb3 = new StringBuilder(".....checkout queued message was : ");
                    sb3.append(dVar.c);
                    sb3.append(" ");
                    sb3.append(dVar.a);
                    queue = bVar.b;
                }
                queue.add(dVar);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void b() {
        if (this.e != null && !d()) {
            a(a("event", "sdk:hidden"), "*", false);
        }
    }

    public final void b(String str, String str2, boolean z) {
        if (!this.f) {
            new d(str, str2, a(z)).a();
        }
    }

    public final boolean c() {
        return this.f;
    }

    public final boolean d() {
        return !(this.g instanceof c);
    }
}
