package defpackage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.ConnectionSpec;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.TlsVersion;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: h reason: default package */
/* compiled from: GA */
public final class h extends Activity {
    public Timer a = null;
    public l b = null;
    /* access modifiers changed from: private */
    public final HashMap<String, Runnable> c = new HashMap<>();
    /* access modifiers changed from: private */
    public final Handler d = new Handler();
    /* access modifiers changed from: private */
    public OkHttpClient e;
    private long f = 0;
    /* access modifiers changed from: private */
    public n g = null;
    private p h = null;

    /* renamed from: h$a */
    /* compiled from: GA */
    class a extends AsyncTask<String, Void, Void> {
        private a() {
        }

        /* synthetic */ a(h hVar, byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            String str = ((String[]) objArr)[0];
            if (str == null || str.length() == 0) {
                h.this.g.b("11");
                h.this.c();
            } else {
                h.this.g.b("10");
                String a2 = a(str);
                if (a2 == null || a2.length() < 20) {
                    al.b("M19: M21");
                    h.this.c();
                } else {
                    h.this.g.b("40");
                    if (h.this.b.k.get()) {
                        h.a(h.this, str, a2);
                    }
                }
            }
            return null;
        }

        private String a(String str) {
            String str2 = "";
            try {
                Builder url = new Builder().url(str);
                h.this.a(url);
                if (h.this.b.a() == null || !h.c(h.this.b.a())) {
                    h.this.g.b("13");
                    throw new Exception("M14");
                }
                h.this.g.b("20");
                String a2 = h.this.a(h.this.g, h.this.b.c());
                if (!a2.isEmpty()) {
                    url.addHeader("X-Cbt", a2);
                }
                url.addHeader(c.a, c.c);
                Response execute = FirebasePerfOkHttpClient.execute(h.this.e.newCall(url.build()));
                if (execute == null) {
                    h.this.g.b("23");
                    throw new Exception("M7: ");
                } else if (!execute.isSuccessful()) {
                    h.this.g.b("22");
                    StringBuilder sb = new StringBuilder("M16: ");
                    sb.append(execute.code());
                    throw new Exception(sb.toString());
                } else {
                    try {
                        String string = execute.body().string();
                        try {
                            execute.body().close();
                            return string;
                        } catch (IOException e) {
                            e = e;
                            str2 = string;
                            h.this.g.b("23");
                            StringBuilder sb2 = new StringBuilder("M8: ");
                            sb2.append(e);
                            throw new Exception(sb2.toString());
                        } catch (Exception e2) {
                            e = e2;
                            str2 = string;
                            try {
                                StringBuilder sb3 = new StringBuilder("M19: ");
                                sb3.append(e.getLocalizedMessage());
                                al.b(sb3.toString());
                                h.this.c();
                            } catch (Throwable unused) {
                            }
                            return str2;
                        } catch (Throwable unused2) {
                            str2 = string;
                            return str2;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        h.this.g.b("23");
                        StringBuilder sb22 = new StringBuilder("M8: ");
                        sb22.append(e);
                        throw new Exception(sb22.toString());
                    }
                }
            } catch (IOException e4) {
                h.this.g.b("21");
                StringBuilder sb4 = new StringBuilder("M15: ");
                sb4.append(e4);
                throw new Exception(sb4.toString());
            } catch (Exception e5) {
                e = e5;
                StringBuilder sb32 = new StringBuilder("M19: ");
                sb32.append(e.getLocalizedMessage());
                al.b(sb32.toString());
                h.this.c();
                return str2;
            }
        }
    }

    /* renamed from: h$b */
    /* compiled from: GA */
    class b extends AsyncTask<String, Void, Void> {
        b() {
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public Void doInBackground(String... strArr) {
            if (strArr.length == 5) {
                try {
                    String str = strArr[1];
                    String str2 = strArr[2];
                    String str3 = strArr[3];
                    String str4 = strArr[4];
                    if (str4 == null || str4.isEmpty() || "undefined".equalsIgnoreCase(str4)) {
                        if (str != null && !str.isEmpty() && !"undefined".equalsIgnoreCase(str)) {
                            if (!"null".equalsIgnoreCase(str)) {
                                if (str2 != null && !str2.isEmpty() && !"undefined".equalsIgnoreCase(str2)) {
                                    if (!"null".equalsIgnoreCase(str2)) {
                                        if (str3 != null && !"null".equalsIgnoreCase(str3)) {
                                            if (!str3.isEmpty()) {
                                                byte[] a2 = al.a(str);
                                                byte[] a3 = al.a(str2);
                                                if (a2.length == 0) {
                                                    h.this.g.b("24");
                                                    throw new InvalidObjectException("M22");
                                                } else if (a3.length == 0) {
                                                    h.this.g.b("25");
                                                    throw new InvalidObjectException("M23");
                                                } else {
                                                    h.a(h.this, strArr[0], a2, a3, str3);
                                                }
                                            }
                                        }
                                        h.this.g.b("32");
                                        throw new InvalidObjectException("M41");
                                    }
                                }
                                h.this.g.b("25");
                                throw new InvalidObjectException("M23");
                            }
                        }
                        h.this.g.b("24");
                        throw new InvalidObjectException("M22");
                    }
                    h.this.g.b("29");
                    StringBuilder sb = new StringBuilder("M24: ");
                    sb.append(str4);
                    throw new InvalidObjectException(sb.toString());
                } catch (InvalidObjectException e) {
                    StringBuilder sb2 = new StringBuilder("M19: ");
                    sb2.append(e.getLocalizedMessage());
                    al.b(sb2.toString());
                    h.this.c();
                } catch (Exception e2) {
                    StringBuilder sb3 = new StringBuilder("M19: ");
                    sb3.append(e2);
                    al.b(sb3.toString());
                    h.this.c();
                }
            } else {
                h.this.g.b("41");
                h.this.c();
            }
            return null;
        }
    }

    private h() {
    }

    public h(l lVar, n nVar) {
        this.b = lVar;
        this.g = nVar;
        ConnectionSpec build = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1).build();
        OkHttpClient.Builder writeTimeout = new OkHttpClient.Builder().addInterceptor(new b()).connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS);
        if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT < 21) {
            writeTimeout.connectionSpecs(Collections.unmodifiableList(Arrays.asList(new ConnectionSpec[]{build, ConnectionSpec.CLEARTEXT})));
            try {
                TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance.init(null);
                TrustManager[] trustManagers = instance.getTrustManagers();
                if (trustManagers.length == 1 || (trustManagers[0] instanceof X509TrustManager)) {
                    X509TrustManager x509TrustManager = (X509TrustManager) trustManagers[0];
                    writeTimeout.sslSocketFactory(new k(x509TrustManager), x509TrustManager);
                }
            } catch (Exception unused) {
            }
        }
        this.e = writeTimeout.build();
    }

    /* access modifiers changed from: private */
    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    public WebView b(Context context) {
        WebView webView;
        try {
            webView = new WebView(context);
            try {
                WebChromeClient webChromeClient = new WebChromeClient();
                webView.removeAllViews();
                webView.setWillNotDraw(true);
                webView.setVisibility(8);
                webView.setWebChromeClient(webChromeClient);
                WebSettings settings = webView.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setAllowContentAccess(false);
                settings.setCacheMode(2);
                settings.setUserAgentString(al.e());
                if (VERSION.SDK_INT >= 19) {
                    webView.setImportantForAccessibility(4);
                }
                webView.addJavascriptInterface(new e(new b(), this.h), "jsag");
                if (VERSION.SDK_INT <= 21) {
                    webView.loadUrl("about:blank");
                }
            } catch (Exception e2) {
                e = e2;
                StringBuilder sb = new StringBuilder("M12: ");
                sb.append(e);
                al.b(sb.toString());
                this.g.b("14");
                c();
                return webView;
            }
        } catch (Exception e3) {
            e = e3;
            webView = null;
            StringBuilder sb2 = new StringBuilder("M12: ");
            sb2.append(e);
            al.b(sb2.toString());
            this.g.b("14");
            c();
            return webView;
        }
        return webView;
    }

    public final void a(final Runnable runnable) {
        if (this.c.containsKey("AGTimer")) {
            Runnable runnable2 = (Runnable) this.c.get("AGTimer");
            this.c.remove("AGTimer");
            this.d.removeCallbacks(runnable2);
        }
        AnonymousClass1 r0 = new Runnable() {
            public final void run() {
                h.this.d.post(runnable);
                h.this.c.remove("AGTimer");
            }
        };
        this.c.put("AGTimer", r0);
        this.d.postDelayed(r0, 1000);
    }

    public final void a() {
        if (this.a != null) {
            this.a.cancel();
            this.a.purge();
            this.a = null;
        }
    }

    public final void a(boolean z, String str) {
        Boolean valueOf = Boolean.valueOf(false);
        m c2 = this.b.c();
        if (z || !c2.a()) {
            valueOf = Boolean.valueOf(true);
        } else if (System.currentTimeMillis() - c2.timeOfStateCreation >= this.b.i) {
            valueOf = Boolean.valueOf(true);
        }
        String str2 = this.b.a;
        if (valueOf.booleanValue() && !this.b.k.get() && str2 != null && str2.length() >= 8) {
            al.b("M1");
            if (this.b.a() != null) {
                this.h = new p(this.b);
                p pVar = this.h;
                pVar.c.clear();
                pVar.b.clear();
                Context a2 = pVar.a.a();
                if (a2 != null) {
                    pVar.b.put(6, new ae(a2, 5));
                    pVar.b.put(8, new ag(a2, 4));
                    pVar.b.put(9, new ag(a2, 3));
                    pVar.b.put(10, new ag(a2, 2));
                    pVar.b.put(11, new ag(a2, 0));
                    pVar.b.put(12, new ag(a2, 1));
                    pVar.b.put(14, new u(a2, 1));
                    pVar.b.put(15, new u(a2, 0));
                    pVar.b.put(21, new ai(a2, 0));
                    pVar.b.put(22, new ai(a2, 1));
                    pVar.b.put(23, new ai(a2, 2));
                    pVar.b.put(25, new y(a2, 0));
                    pVar.b.put(26, new y(a2, 1));
                    pVar.b.put(27, new y(a2, 2));
                    pVar.b.put(28, new y(a2, 3));
                    pVar.b.put(30, new y(a2, 4));
                    pVar.b.put(31, new y(a2, 5));
                    pVar.b.put(36, new ae(a2, 0));
                    pVar.b.put(37, new ae(a2, 1));
                    pVar.b.put(38, new ae(a2, 2));
                    pVar.b.put(39, new ae(a2, 3));
                    pVar.b.put(40, new u(a2, 2));
                    pVar.b.put(41, new u(a2, 4));
                    pVar.b.put(42, new u(a2, 6));
                    pVar.b.put(43, new u(a2, 5));
                    pVar.b.put(44, new u(a2, 3));
                    pVar.b.put(54, new ae(a2, 4));
                    pVar.b.put(58, new aj(a2, 0));
                    pVar.b.put(59, new aj(a2, 1));
                    pVar.b.put(60, new ab(a2));
                }
                pVar.b.put(0, new v(0));
                pVar.b.put(1, new v(1));
                pVar.b.put(2, new v(2));
                pVar.b.put(3, new v(3));
                pVar.b.put(4, new v(4));
                pVar.b.put(5, new v(5));
                pVar.b.put(7, new v(6));
                pVar.b.put(13, new v(7));
                pVar.b.put(16, new w(0));
                pVar.b.put(17, new w(1));
                pVar.b.put(18, new w(2));
                pVar.b.put(19, new w(3));
                pVar.b.put(20, new w(4));
                pVar.b.put(29, new w(5));
                pVar.b.put(32, new ac(0));
                pVar.b.put(33, new ac(1));
                pVar.b.put(34, new ac(2));
                pVar.b.put(35, new ac(3));
                pVar.b.put(52, new x(1));
                pVar.b.put(53, new x(0));
                pVar.b.put(55, new v(8));
                pVar.b.put(56, new v(9));
                pVar.b.put(57, new v(10));
                Context a3 = pVar.a.a();
                if (a3 != null) {
                    pVar.c.put(45, new t(a3));
                    pVar.c.put(46, new ad(a3));
                    pVar.c.put(48, new af(a3));
                    pVar.c.put(49, new ah(a3));
                    pVar.c.put(50, new z(a3));
                    pVar.c.put(51, new aa(a3));
                }
                p pVar2 = this.h;
                int i = 0;
                while (i < pVar2.c.size()) {
                    r rVar = (r) pVar2.c.valueAt(i);
                    i++;
                    HandlerThread handlerThread = new HandlerThread(Integer.toHexString(i));
                    handlerThread.start();
                    pVar2.d.add(handlerThread);
                    new Handler(handlerThread.getLooper()).post(new Runnable(rVar) {
                        private /* synthetic */ r a;

                        {
                            this.a = r2;
                        }

                        public final void run() {
                            try {
                                this.a.c();
                            } catch (Throwable th) {
                                p.this.e.add(Log.getStackTraceString(th));
                            }
                        }
                    });
                }
                HandlerThread handlerThread2 = new HandlerThread(Integer.toHexString(0));
                handlerThread2.start();
                pVar2.d.add(handlerThread2);
                new Handler(handlerThread2.getLooper()).post(new Runnable() {
                    public final void run() {
                        for (int i = 0; i < p.this.b.size(); i++) {
                            r rVar = (r) p.this.b.valueAt(i);
                            if (rVar != null) {
                                try {
                                    rVar.c();
                                } catch (Throwable th) {
                                    p.this.e.add(Log.getStackTraceString(th));
                                }
                            }
                        }
                    }
                });
            }
            this.b.a(true);
            this.f = System.currentTimeMillis();
            this.g.a(str);
            new a(this, 0).execute(new String[]{str2});
        }
    }

    /* access modifiers changed from: private */
    public void a(Builder builder) {
        Map<String, String> map = this.b.e;
        if (map != null && map.size() > 0) {
            for (Entry entry : map.entrySet()) {
                builder.addHeader((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    public final String a(byte[] bArr, byte[] bArr2) {
        StringBuilder sb = new StringBuilder();
        sb.append(b(bArr, bArr2));
        ByteBuffer allocate = ByteBuffer.allocate(15);
        ByteBuffer allocate2 = ByteBuffer.allocate(9);
        allocate2.put("2.6.2".getBytes());
        allocate2.flip();
        allocate.put(Integer.valueOf(1).byteValue());
        allocate.put(allocate2.array());
        allocate.put(Integer.toString(this.b.l.get() ? 1 : 0).getBytes());
        allocate.put(this.g.a());
        allocate.flip();
        String a2 = al.a(allocate.array());
        if (sb.length() > 0 && a2.length() > 0) {
            sb.append(";");
            sb.append(a2);
        }
        return sb.toString();
    }

    private static String b(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        int i = 0;
        while (i < bArr3.length) {
            bArr3[i] = i < bArr.length ? bArr[i] : bArr2[i - bArr.length];
            i++;
        }
        return al.a(bArr3);
    }

    /* access modifiers changed from: private */
    public static boolean c(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public final void b() {
        if (this.h != null) {
            this.h.a();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        b();
        this.f = 0;
        this.b.a(false);
    }

    public final String a(n nVar, m mVar) {
        SecureRandom secureRandom = new SecureRandom();
        ByteBuffer allocate = ByteBuffer.allocate(28);
        byte[] bArr = new byte[10];
        byte[] bArr2 = new byte[10];
        byte[] bytes = "0A".getBytes();
        byte[] bytes2 = "0A".getBytes();
        secureRandom.nextBytes(bArr);
        secureRandom.nextBytes(bArr2);
        allocate.put(bArr);
        allocate.put(bytes);
        allocate.put(nVar.a());
        allocate.put(bytes2);
        allocate.put(bArr2);
        allocate.flip();
        return a(allocate.array(), mVar.uuid);
    }

    static /* synthetic */ void d(h hVar) {
        hVar.a();
        hVar.a = new Timer();
        hVar.a.scheduleAtFixedRate(new TimerTask() {
            public final void run() {
                h.this.a(false, "20");
            }
        }, 0, hVar.b.j);
    }

    static /* synthetic */ void a(h hVar, final String str, final String str2) {
        try {
            final Context a2 = hVar.b.a();
            if (a2 != null) {
                hVar.runOnUiThread(new Runnable() {
                    @SuppressLint({"AddJavascriptInterface"})
                    public final void run() {
                        final WebView a2 = h.this.b(a2);
                        if (a2 != null) {
                            if (VERSION.SDK_INT >= 19) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(str2);
                                sb.append(";");
                                sb.append(ak.A());
                                a2.evaluateJavascript(sb.toString(), new ValueCallback<String>() {
                                    public final /* synthetic */ void onReceiveValue(Object obj) {
                                        try {
                                            JSONObject jSONObject = new JSONObject((String) obj);
                                            String optString = jSONObject.optString("uuid", "undefined");
                                            String optString2 = jSONObject.optString("sk1", "undefined");
                                            String optString3 = jSONObject.optString("ip", "undefined");
                                            String optString4 = jSONObject.optString("err", "undefined");
                                            new b().execute(new String[]{str, optString, optString2, optString3, optString4});
                                        } catch (JSONException e) {
                                            h.this.g.b("26");
                                            StringBuilder sb = new StringBuilder("M26: ");
                                            sb.append(e);
                                            al.b(sb.toString());
                                            h.this.c();
                                        } catch (Exception e2) {
                                            StringBuilder sb2 = new StringBuilder("M19: ");
                                            sb2.append(e2);
                                            al.b(sb2.toString());
                                            h.this.c();
                                        } catch (Throwable th) {
                                            a2.destroy();
                                            throw th;
                                        }
                                        a2.destroy();
                                    }
                                });
                                return;
                            }
                            try {
                                a2.loadUrl(String.format(Locale.US, ak.B(), new Object[]{str2, str}));
                            } catch (Exception e) {
                                StringBuilder sb2 = new StringBuilder("M19: ");
                                sb2.append(e);
                                al.b(sb2.toString());
                                h.this.c();
                            }
                        }
                    }
                });
            } else {
                hVar.g.b("12");
                throw new Exception("M13: ");
            }
        } catch (Exception e2) {
            StringBuilder sb = new StringBuilder("M19: ");
            sb.append(e2);
            al.b(sb.toString());
            hVar.c();
        }
    }

    static /* synthetic */ void a(h hVar, String str, byte[] bArr, byte[] bArr2, String str2) {
        RequestBody requestBody;
        if (str2 != null) {
            try {
                if (str2.length() > 0 && !"undefined".equalsIgnoreCase(str2)) {
                    requestBody = RequestBody.create(MediaType.parse(c.k), str2);
                    Builder post = new Builder().url(str).post(requestBody);
                    hVar.a(post);
                    if (hVar.b.a() != null || !c(hVar.b.a())) {
                        hVar.g.b("13");
                        throw new Exception("M14");
                    }
                    hVar.g.b("80");
                    String a2 = hVar.a(hVar.g, hVar.b.c());
                    if (!a2.isEmpty()) {
                        post.addHeader("X-Cbt", a2);
                    }
                    post.addHeader(c.a, c.c);
                    Response execute = FirebasePerfOkHttpClient.execute(hVar.e.newCall(post.build()));
                    if (execute == null) {
                        hVar.g.b("84");
                        throw new Exception("M7: ");
                    } else if (!execute.isSuccessful()) {
                        hVar.g.b("83");
                        StringBuilder sb = new StringBuilder("M18: ");
                        sb.append(execute.code());
                        throw new Exception(sb.toString());
                    } else {
                        String string = execute.body().string();
                        execute.body().close();
                        String optString = new JSONObject(string).optString(c.b, "undefined");
                        if (optString != null) {
                            if (!optString.isEmpty() && !"null".equalsIgnoreCase(optString) && !"undefined".equalsIgnoreCase(optString)) {
                                byte[] a3 = al.a(optString);
                                if (a3.length == 0) {
                                    hVar.g.b("85");
                                    throw new InvalidObjectException("M9: ");
                                }
                                byte[] a4 = al.a(a3, bArr2);
                                if (a4 == null || a4.length <= 0) {
                                    hVar.g.b("87");
                                    throw new InvalidObjectException("M35: ");
                                }
                                if (hVar.b.a(new m(a4, bArr, hVar.f))) {
                                    hVar.b.d();
                                    hVar.b.b(false);
                                    hVar.g.b("FF");
                                    LocalBroadcastManager.getInstance(hVar.b.a()).sendBroadcast(new Intent("com.apiguard.action.VLA_GENERATED"));
                                    al.b("M2");
                                    new StringBuilder("Time taken to complete in MilliSeconds: ").append(System.currentTimeMillis() - hVar.f);
                                    al.a();
                                    hVar.c();
                                    return;
                                }
                                hVar.g.b("86");
                                throw new InvalidObjectException("M36");
                            }
                        }
                        hVar.g.b("85");
                        throw new InvalidObjectException("M9: ");
                    }
                }
            } catch (IOException e2) {
                hVar.g.b("82");
                StringBuilder sb2 = new StringBuilder("M17: ");
                sb2.append(e2);
                throw new Exception(sb2.toString());
            } catch (IOException e3) {
                hVar.g.b("84");
                StringBuilder sb3 = new StringBuilder("M9: ");
                sb3.append(e3);
                throw new Exception(sb3.toString());
            } catch (JSONException e4) {
                hVar.g.b("84");
                StringBuilder sb4 = new StringBuilder("M26: ");
                sb4.append(e4);
                throw new Exception(sb4.toString());
            } catch (JSONException e5) {
                hVar.g.b("81");
                StringBuilder sb5 = new StringBuilder("M26: ");
                sb5.append(e5);
                throw new Exception(sb5.toString());
            } catch (InvalidObjectException e6) {
                StringBuilder sb6 = new StringBuilder("M20: ");
                sb6.append(e6.getLocalizedMessage());
                al.b(sb6.toString());
                hVar.c();
                return;
            } catch (Exception e7) {
                StringBuilder sb7 = new StringBuilder("M20: ");
                sb7.append(e7);
                al.b(sb7.toString());
                hVar.c();
                return;
            } catch (Throwable th) {
                hVar.c();
                throw th;
            }
        }
        MediaType parse = MediaType.parse(c.l);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("hmac", al.a(al.a("".getBytes(), bArr2)));
        jSONObject.put("payload", "");
        jSONObject.put("uuid", al.a(bArr));
        requestBody = RequestBody.create(parse, jSONObject.toString());
        Builder post2 = new Builder().url(str).post(requestBody);
        hVar.a(post2);
        if (hVar.b.a() != null) {
        }
        hVar.g.b("13");
        throw new Exception("M14");
    }
}
