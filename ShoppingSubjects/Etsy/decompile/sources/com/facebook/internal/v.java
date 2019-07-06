package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.login.DefaultAudience;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: NativeProtocol */
public final class v {
    private static final String a = "com.facebook.internal.v";
    /* access modifiers changed from: private */
    public static final List<e> b = e();
    private static final List<e> c = f();
    private static final Map<String, List<e>> d = g();
    /* access modifiers changed from: private */
    public static final AtomicBoolean e = new AtomicBoolean(false);
    private static final List<Integer> f = Arrays.asList(new Integer[]{Integer.valueOf(20170417), Integer.valueOf(20160327), Integer.valueOf(20141218), Integer.valueOf(20141107), Integer.valueOf(20141028), Integer.valueOf(20141001), Integer.valueOf(20140701), Integer.valueOf(20140324), Integer.valueOf(20140204), Integer.valueOf(20131107), Integer.valueOf(20130618), Integer.valueOf(20130502), Integer.valueOf(20121101)});

    /* compiled from: NativeProtocol */
    private static class a extends e {
        /* access modifiers changed from: protected */
        public String a() {
            return "com.facebook.arstudio.player";
        }

        /* access modifiers changed from: protected */
        public String b() {
            return null;
        }

        private a() {
            super();
        }
    }

    /* compiled from: NativeProtocol */
    private static class b extends e {
        /* access modifiers changed from: protected */
        public String a() {
            return "com.facebook.lite";
        }

        /* access modifiers changed from: protected */
        public String b() {
            return "com.facebook.lite.platform.LoginGDPDialogActivity";
        }

        private b() {
            super();
        }
    }

    /* compiled from: NativeProtocol */
    private static class c extends e {
        /* access modifiers changed from: protected */
        public String a() {
            return "com.facebook.katana";
        }

        /* access modifiers changed from: protected */
        public String b() {
            return "com.facebook.katana.ProxyAuth";
        }

        private c() {
            super();
        }
    }

    /* compiled from: NativeProtocol */
    private static class d extends e {
        /* access modifiers changed from: protected */
        public String a() {
            return "com.facebook.orca";
        }

        /* access modifiers changed from: protected */
        public String b() {
            return null;
        }

        private d() {
            super();
        }
    }

    /* compiled from: NativeProtocol */
    private static abstract class e {
        private TreeSet<Integer> a;

        /* access modifiers changed from: protected */
        public abstract String a();

        /* access modifiers changed from: protected */
        public abstract String b();

        private e() {
        }

        public TreeSet<Integer> c() {
            if (this.a == null) {
                a(false);
            }
            return this.a;
        }

        /* access modifiers changed from: private */
        public synchronized void a(boolean z) {
            if (!z) {
                try {
                    if (this.a == null) {
                    }
                } finally {
                }
            }
            this.a = v.b(this);
        }
    }

    /* compiled from: NativeProtocol */
    public static class f {
        /* access modifiers changed from: private */
        public e a;
        /* access modifiers changed from: private */
        public int b;

        public static f a(e eVar, int i) {
            f fVar = new f();
            fVar.a = eVar;
            fVar.b = i;
            return fVar;
        }

        public static f a() {
            f fVar = new f();
            fVar.b = -1;
            return fVar;
        }

        private f() {
        }

        public int b() {
            return this.b;
        }
    }

    /* compiled from: NativeProtocol */
    private static class g extends e {
        /* access modifiers changed from: protected */
        public String a() {
            return "com.facebook.wakizashi";
        }

        /* access modifiers changed from: protected */
        public String b() {
            return "com.facebook.katana.ProxyAuth";
        }

        private g() {
            super();
        }
    }

    private static List<e> e() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new c());
        arrayList.add(new g());
        return arrayList;
    }

    private static List<e> f() {
        ArrayList arrayList = new ArrayList(e());
        arrayList.add(0, new a());
        return arrayList;
    }

    private static Map<String, List<e>> g() {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new d());
        hashMap.put("com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG", b);
        hashMap.put("com.facebook.platform.action.request.FEED_DIALOG", b);
        hashMap.put("com.facebook.platform.action.request.LIKE_DIALOG", b);
        hashMap.put("com.facebook.platform.action.request.APPINVITES_DIALOG", b);
        hashMap.put("com.facebook.platform.action.request.MESSAGE_DIALOG", arrayList);
        hashMap.put("com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG", arrayList);
        hashMap.put("com.facebook.platform.action.request.CAMERA_EFFECT", c);
        hashMap.put("com.facebook.platform.action.request.SHARE_STORY", b);
        return hashMap;
    }

    static Intent a(Context context, Intent intent, e eVar) {
        if (intent == null) {
            return null;
        }
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity != null && i.a(context, resolveActivity.activityInfo.packageName)) {
            return intent;
        }
        return null;
    }

    static Intent b(Context context, Intent intent, e eVar) {
        if (intent == null) {
            return null;
        }
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService != null && i.a(context, resolveService.serviceInfo.packageName)) {
            return intent;
        }
        return null;
    }

    public static Intent a(Context context, String str, Collection<String> collection, String str2, boolean z, boolean z2, DefaultAudience defaultAudience, String str3, String str4) {
        b bVar = new b();
        return a(context, a((e) bVar, str, collection, str2, z, z2, defaultAudience, str3, str4), (e) bVar);
    }

    private static Intent a(e eVar, String str, Collection<String> collection, String str2, boolean z, boolean z2, DefaultAudience defaultAudience, String str3, String str4) {
        String b2 = eVar.b();
        if (b2 == null) {
            return null;
        }
        Intent putExtra = new Intent().setClassName(eVar.a(), b2).putExtra("client_id", str);
        putExtra.putExtra("facebook_sdk_version", com.facebook.f.h());
        if (!z.a(collection)) {
            putExtra.putExtra("scope", TextUtils.join(",", collection));
        }
        if (!z.a(str2)) {
            putExtra.putExtra("e2e", str2);
        }
        putExtra.putExtra(ResponseConstants.STATE, str3);
        putExtra.putExtra("response_type", "token,signed_request");
        putExtra.putExtra("return_scopes", "true");
        if (z2) {
            putExtra.putExtra("default_audience", defaultAudience.getNativeProtocolAudience());
        }
        putExtra.putExtra("legacy_override", com.facebook.f.g());
        putExtra.putExtra("auth_type", str4);
        return putExtra;
    }

    public static Intent b(Context context, String str, Collection<String> collection, String str2, boolean z, boolean z2, DefaultAudience defaultAudience, String str3, String str4) {
        for (e eVar : b) {
            Intent a2 = a(context, a(eVar, str, collection, str2, z, z2, defaultAudience, str3, str4), eVar);
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }

    public static final int a() {
        return ((Integer) f.get(0)).intValue();
    }

    public static boolean a(int i) {
        return f.contains(Integer.valueOf(i)) && i >= 20140701;
    }

    public static Intent a(Context context, String str, String str2, f fVar, Bundle bundle) {
        if (fVar == null) {
            return null;
        }
        e a2 = fVar.a;
        if (a2 == null) {
            return null;
        }
        Intent a3 = a(context, new Intent().setAction("com.facebook.platform.PLATFORM_ACTIVITY").setPackage(a2.a()).addCategory("android.intent.category.DEFAULT"), a2);
        if (a3 == null) {
            return null;
        }
        a(a3, str, str2, fVar.b, bundle);
        return a3;
    }

    public static void a(Intent intent, String str, String str2, int i, Bundle bundle) {
        String j = com.facebook.f.j();
        String k = com.facebook.f.k();
        intent.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", i).putExtra("com.facebook.platform.protocol.PROTOCOL_ACTION", str2).putExtra("com.facebook.platform.extra.APPLICATION_ID", j);
        if (a(i)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("action_id", str);
            z.a(bundle2, "app_name", k);
            intent.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
            if (bundle == null) {
                bundle = new Bundle();
            }
            intent.putExtra("com.facebook.platform.protocol.METHOD_ARGS", bundle);
            return;
        }
        intent.putExtra("com.facebook.platform.protocol.CALL_ID", str);
        if (!z.a(k)) {
            intent.putExtra("com.facebook.platform.extra.APPLICATION_NAME", k);
        }
        intent.putExtras(bundle);
    }

    public static Intent a(Intent intent, Bundle bundle, FacebookException facebookException) {
        UUID b2 = b(intent);
        if (b2 == null) {
            return null;
        }
        Intent intent2 = new Intent();
        intent2.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", a(intent));
        Bundle bundle2 = new Bundle();
        bundle2.putString("action_id", b2.toString());
        if (facebookException != null) {
            bundle2.putBundle("error", a(facebookException));
        }
        intent2.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
        if (bundle != null) {
            intent2.putExtra("com.facebook.platform.protocol.RESULT_ARGS", bundle);
        }
        return intent2;
    }

    public static Intent a(Context context) {
        for (e eVar : b) {
            Intent b2 = b(context, new Intent("com.facebook.platform.PLATFORM_SERVICE").setPackage(eVar.a()).addCategory("android.intent.category.DEFAULT"), eVar);
            if (b2 != null) {
                return b2;
            }
        }
        return null;
    }

    public static int a(Intent intent) {
        return intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0);
    }

    public static UUID b(Intent intent) {
        String str;
        UUID uuid;
        if (intent == null) {
            return null;
        }
        if (a(a(intent))) {
            Bundle bundleExtra = intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
            str = bundleExtra != null ? bundleExtra.getString("action_id") : null;
        } else {
            str = intent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
        }
        if (str != null) {
            try {
                uuid = UUID.fromString(str);
            } catch (IllegalArgumentException unused) {
            }
            return uuid;
        }
        uuid = null;
        return uuid;
    }

    public static Bundle c(Intent intent) {
        if (!a(a(intent))) {
            return null;
        }
        return intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
    }

    public static Bundle d(Intent intent) {
        if (!a(a(intent))) {
            return intent.getExtras();
        }
        return intent.getBundleExtra("com.facebook.platform.protocol.METHOD_ARGS");
    }

    public static Bundle e(Intent intent) {
        int a2 = a(intent);
        Bundle extras = intent.getExtras();
        return (!a(a2) || extras == null) ? extras : extras.getBundle("com.facebook.platform.protocol.RESULT_ARGS");
    }

    public static boolean f(Intent intent) {
        Bundle c2 = c(intent);
        if (c2 != null) {
            return c2.containsKey("error");
        }
        return intent.hasExtra("com.facebook.platform.status.ERROR_TYPE");
    }

    public static Bundle g(Intent intent) {
        if (!f(intent)) {
            return null;
        }
        Bundle c2 = c(intent);
        if (c2 != null) {
            return c2.getBundle("error");
        }
        return intent.getExtras();
    }

    public static FacebookException a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString("error_type");
        if (string == null) {
            string = bundle.getString("com.facebook.platform.status.ERROR_TYPE");
        }
        String string2 = bundle.getString("error_description");
        if (string2 == null) {
            string2 = bundle.getString("com.facebook.platform.status.ERROR_DESCRIPTION");
        }
        if (string == null || !string.equalsIgnoreCase("UserCanceled")) {
            return new FacebookException(string2);
        }
        return new FacebookOperationCanceledException(string2);
    }

    public static Bundle a(FacebookException facebookException) {
        if (facebookException == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("error_description", facebookException.toString());
        if (facebookException instanceof FacebookOperationCanceledException) {
            bundle.putString("error_type", "UserCanceled");
        }
        return bundle;
    }

    public static int b(int i) {
        return a(b, new int[]{i}).b();
    }

    public static f a(String str, int[] iArr) {
        return a((List) d.get(str), iArr);
    }

    private static f a(List<e> list, int[] iArr) {
        b();
        if (list == null) {
            return f.a();
        }
        for (e eVar : list) {
            int a2 = a(eVar.c(), a(), iArr);
            if (a2 != -1) {
                return f.a(eVar, a2);
            }
        }
        return f.a();
    }

    public static void b() {
        if (e.compareAndSet(false, true)) {
            com.facebook.f.d().execute(new Runnable() {
                public void run() {
                    try {
                        for (e a : v.b) {
                            a.a(true);
                        }
                    } finally {
                        v.e.set(false);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        android.util.Log.e(a, "Failed to query content resolver.");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0051 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.TreeSet<java.lang.Integer> b(com.facebook.internal.v.e r9) {
        /*
            java.util.TreeSet r0 = new java.util.TreeSet
            r0.<init>()
            android.content.Context r1 = com.facebook.f.f()
            android.content.ContentResolver r2 = r1.getContentResolver()
            r1 = 1
            java.lang.String[] r4 = new java.lang.String[r1]
            java.lang.String r1 = "version"
            r3 = 0
            r4[r3] = r1
            android.net.Uri r1 = c(r9)
            r8 = 0
            android.content.Context r5 = com.facebook.f.f()     // Catch:{ all -> 0x0078 }
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch:{ all -> 0x0078 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0078 }
            r6.<init>()     // Catch:{ all -> 0x0078 }
            java.lang.String r9 = r9.a()     // Catch:{ all -> 0x0078 }
            r6.append(r9)     // Catch:{ all -> 0x0078 }
            java.lang.String r9 = ".provider.PlatformProvider"
            r6.append(r9)     // Catch:{ all -> 0x0078 }
            java.lang.String r9 = r6.toString()     // Catch:{ all -> 0x0078 }
            android.content.pm.ProviderInfo r9 = r5.resolveContentProvider(r9, r3)     // Catch:{ RuntimeException -> 0x003c }
            goto L_0x0045
        L_0x003c:
            r9 = move-exception
            java.lang.String r3 = a     // Catch:{ all -> 0x0078 }
            java.lang.String r5 = "Failed to query content resolver."
            android.util.Log.e(r3, r5, r9)     // Catch:{ all -> 0x0078 }
            r9 = r8
        L_0x0045:
            if (r9 == 0) goto L_0x0072
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r1
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ IllegalArgumentException | NullPointerException | SecurityException -> 0x0051 }
            r8 = r9
            goto L_0x0058
        L_0x0051:
            java.lang.String r9 = a     // Catch:{ all -> 0x0078 }
            java.lang.String r1 = "Failed to query content resolver."
            android.util.Log.e(r9, r1)     // Catch:{ all -> 0x0078 }
        L_0x0058:
            if (r8 == 0) goto L_0x0072
        L_0x005a:
            boolean r9 = r8.moveToNext()     // Catch:{ all -> 0x0078 }
            if (r9 == 0) goto L_0x0072
            java.lang.String r9 = "version"
            int r9 = r8.getColumnIndex(r9)     // Catch:{ all -> 0x0078 }
            int r9 = r8.getInt(r9)     // Catch:{ all -> 0x0078 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0078 }
            r0.add(r9)     // Catch:{ all -> 0x0078 }
            goto L_0x005a
        L_0x0072:
            if (r8 == 0) goto L_0x0077
            r8.close()
        L_0x0077:
            return r0
        L_0x0078:
            r9 = move-exception
            if (r8 == 0) goto L_0x007e
            r8.close()
        L_0x007e:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.v.b(com.facebook.internal.v$e):java.util.TreeSet");
    }

    public static int a(TreeSet<Integer> treeSet, int i, int[] iArr) {
        int i2 = -1;
        int length = iArr.length - 1;
        Iterator descendingIterator = treeSet.descendingIterator();
        int i3 = length;
        int i4 = -1;
        while (descendingIterator.hasNext()) {
            int intValue = ((Integer) descendingIterator.next()).intValue();
            i4 = Math.max(i4, intValue);
            while (i3 >= 0 && iArr[i3] > intValue) {
                i3--;
            }
            if (i3 < 0) {
                return -1;
            }
            if (iArr[i3] == intValue) {
                if (i3 % 2 == 0) {
                    i2 = Math.min(i4, i);
                }
                return i2;
            }
        }
        return -1;
    }

    private static Uri c(e eVar) {
        StringBuilder sb = new StringBuilder();
        sb.append("content://");
        sb.append(eVar.a());
        sb.append(".provider.PlatformProvider/versions");
        return Uri.parse(sb.toString());
    }
}
