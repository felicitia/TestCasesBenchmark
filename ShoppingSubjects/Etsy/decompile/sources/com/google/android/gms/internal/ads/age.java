package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@bu
@TargetApi(14)
public final class age extends Thread {
    private boolean a = false;
    private boolean b = false;
    private boolean c = false;
    private final Object d;
    private final afz e;
    private final bs f;
    private final int g;
    private final int h;
    private final int i;
    private final int j;
    private final int k;
    private final int l;
    private final int m;
    private final int n;
    private final String o;
    private final boolean p;

    public age(afz afz, bs bsVar) {
        this.e = afz;
        this.f = bsVar;
        this.d = new Object();
        this.h = ((Integer) ajh.f().a(akl.R)).intValue();
        this.i = ((Integer) ajh.f().a(akl.S)).intValue();
        this.j = ((Integer) ajh.f().a(akl.T)).intValue();
        this.k = ((Integer) ajh.f().a(akl.U)).intValue();
        this.l = ((Integer) ajh.f().a(akl.X)).intValue();
        this.m = ((Integer) ajh.f().a(akl.Z)).intValue();
        this.n = ((Integer) ajh.f().a(akl.aa)).intValue();
        this.g = ((Integer) ajh.f().a(akl.V)).intValue();
        this.o = (String) ajh.f().a(akl.ac);
        this.p = ((Boolean) ajh.f().a(akl.ae)).booleanValue();
        setName("ContentFetchTask");
    }

    @VisibleForTesting
    private final agi a(@Nullable View view, afy afy) {
        boolean z;
        if (view == null) {
            return new agi(this, 0, 0);
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new agi(this, 0, 0);
            }
            afy.b(text.toString(), globalVisibleRect, view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
            return new agi(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof nn)) {
            afy.g();
            WebView webView = (WebView) view;
            if (!PlatformVersion.isAtLeastKitKat()) {
                z = false;
            } else {
                afy.g();
                webView.post(new agg(this, afy, webView, globalVisibleRect));
                z = true;
            }
            return z ? new agi(this, 0, 1) : new agi(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new agi(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < viewGroup.getChildCount(); i4++) {
                agi a2 = a(viewGroup.getChildAt(i4), afy);
                i2 += a2.a;
                i3 += a2.b;
            }
            return new agi(this, i2, i3);
        }
    }

    @VisibleForTesting
    private static boolean e() {
        boolean z = false;
        try {
            Context b2 = ao.h().b();
            if (b2 == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) b2.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) b2.getSystemService("keyguard");
            if (activityManager != null) {
                if (keyguardManager != null) {
                    List runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses != null) {
                        Iterator it = runningAppProcesses.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            RunningAppProcessInfo runningAppProcessInfo = (RunningAppProcessInfo) it.next();
                            if (Process.myPid() == runningAppProcessInfo.pid) {
                                if (runningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode()) {
                                    PowerManager powerManager = (PowerManager) b2.getSystemService("power");
                                    if (powerManager == null ? false : powerManager.isScreenOn()) {
                                        z = true;
                                    }
                                }
                            }
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return z;
        } catch (Throwable th) {
            ao.i().a(th, "ContentFetchTask.isInForeground");
            return false;
        }
    }

    private final void f() {
        synchronized (this.d) {
            this.b = true;
            boolean z = this.b;
            StringBuilder sb = new StringBuilder(42);
            sb.append("ContentFetchThread: paused, mPause = ");
            sb.append(z);
            gv.b(sb.toString());
        }
    }

    public final void a() {
        synchronized (this.d) {
            if (this.a) {
                gv.b("Content hash thread already started, quiting...");
                return;
            }
            this.a = true;
            start();
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void a(View view) {
        try {
            afy afy = new afy(this.h, this.i, this.j, this.k, this.l, this.m, this.n);
            Context b2 = ao.h().b();
            if (b2 != null && !TextUtils.isEmpty(this.o)) {
                String str = (String) view.getTag(b2.getResources().getIdentifier((String) ajh.f().a(akl.ab), "id", b2.getPackageName()));
                if (str != null && str.equals(this.o)) {
                    return;
                }
            }
            agi a2 = a(view, afy);
            afy.h();
            if (a2.a != 0 || a2.b != 0) {
                if (a2.b != 0 || afy.j() != 0) {
                    if (a2.b != 0 || !this.e.a(afy)) {
                        this.e.c(afy);
                    }
                }
            }
        } catch (Exception e2) {
            gv.b("Exception in fetchContentOnUIThread", e2);
            this.f.a(e2, "ContentFetchTask.fetchContent");
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void a(afy afy, WebView webView, String str, boolean z) {
        afy.f();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString("text");
                if (this.p || TextUtils.isEmpty(webView.getTitle())) {
                    afy.a(optString, z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                } else {
                    String title = webView.getTitle();
                    StringBuilder sb = new StringBuilder(1 + String.valueOf(title).length() + String.valueOf(optString).length());
                    sb.append(title);
                    sb.append("\n");
                    sb.append(optString);
                    afy.a(sb.toString(), z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                }
            }
            if (afy.a()) {
                this.e.b(afy);
            }
        } catch (JSONException unused) {
            gv.b("Json string may be malformed.");
        } catch (Throwable th) {
            gv.a("Failed to get webview content.", th);
            this.f.a(th, "ContentFetchTask.processWebViewContent");
        }
    }

    public final afy b() {
        return this.e.a();
    }

    public final void c() {
        synchronized (this.d) {
            this.b = false;
            this.d.notifyAll();
            gv.b("ContentFetchThread: wakeup");
        }
    }

    public final boolean d() {
        return this.b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0068, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0069, code lost:
        com.google.android.gms.internal.ads.gv.b("Error in ContentFetchTask", r0);
        r4.f.a(r0, "ContentFetchTask.run");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0076, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0077, code lost:
        com.google.android.gms.internal.ads.gv.b("Error in ContentFetchTask", r0);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x007f */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0076 A[ExcHandler: InterruptedException (r0v1 'e' java.lang.InterruptedException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007f A[LOOP:1: B:30:0x007f->B:42:0x007f, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r4 = this;
        L_0x0000:
            boolean r0 = e()     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            if (r0 == 0) goto L_0x0059
            com.google.android.gms.internal.ads.aga r0 = com.google.android.gms.ads.internal.ao.h()     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            android.app.Activity r0 = r0.a()     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            if (r0 != 0) goto L_0x0019
            java.lang.String r0 = "ContentFetchThread: no activity. Sleeping."
            com.google.android.gms.internal.ads.gv.b(r0)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
        L_0x0015:
            r4.f()     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            goto L_0x005f
        L_0x0019:
            if (r0 == 0) goto L_0x005f
            r1 = 0
            android.view.Window r2 = r0.getWindow()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            if (r2 == 0) goto L_0x004c
            android.view.Window r2 = r0.getWindow()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            android.view.View r2 = r2.getDecorView()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            if (r2 == 0) goto L_0x004c
            android.view.Window r0 = r0.getWindow()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            android.view.View r0 = r0.getDecorView()     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            r2 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r0 = r0.findViewById(r2)     // Catch:{ Exception -> 0x003d, InterruptedException -> 0x0076 }
            r1 = r0
            goto L_0x004c
        L_0x003d:
            r0 = move-exception
            com.google.android.gms.internal.ads.gf r2 = com.google.android.gms.ads.internal.ao.i()     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            java.lang.String r3 = "ContentFetchTask.extractContent"
            r2.a(r0, r3)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            java.lang.String r0 = "Failed getting root view of activity. Content not extracted."
            com.google.android.gms.internal.ads.gv.b(r0)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
        L_0x004c:
            if (r1 == 0) goto L_0x005f
            if (r1 == 0) goto L_0x005f
            com.google.android.gms.internal.ads.agf r0 = new com.google.android.gms.internal.ads.agf     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            r0.<init>(r4, r1)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            r1.post(r0)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            goto L_0x005f
        L_0x0059:
            java.lang.String r0 = "ContentFetchTask: sleeping"
            com.google.android.gms.internal.ads.gv.b(r0)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            goto L_0x0015
        L_0x005f:
            int r0 = r4.g     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            int r0 = r0 * 1000
            long r0 = (long) r0     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x0076, Exception -> 0x0068 }
            goto L_0x007c
        L_0x0068:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.ads.gv.b(r1, r0)
            com.google.android.gms.internal.ads.bs r1 = r4.f
            java.lang.String r2 = "ContentFetchTask.run"
            r1.a(r0, r2)
            goto L_0x007c
        L_0x0076:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.ads.gv.b(r1, r0)
        L_0x007c:
            java.lang.Object r0 = r4.d
            monitor-enter(r0)
        L_0x007f:
            boolean r1 = r4.b     // Catch:{ all -> 0x0091 }
            if (r1 == 0) goto L_0x008e
            java.lang.String r1 = "ContentFetchTask: waiting"
            com.google.android.gms.internal.ads.gv.b(r1)     // Catch:{ InterruptedException -> 0x007f }
            java.lang.Object r1 = r4.d     // Catch:{ InterruptedException -> 0x007f }
            r1.wait()     // Catch:{ InterruptedException -> 0x007f }
            goto L_0x007f
        L_0x008e:
            monitor-exit(r0)     // Catch:{ all -> 0x0091 }
            goto L_0x0000
        L_0x0091:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0091 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.age.run():void");
    }
}
