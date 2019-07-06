package com.facebook.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.os.StatFs;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.view.autofill.AutofillManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.f;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* compiled from: Utility */
public final class z {
    private static int a = 0;
    private static long b = -1;
    private static long c = -1;
    private static long d = -1;
    private static String e = "";
    private static String f = "";
    private static String g = "NoCarrier";

    /* compiled from: Utility */
    public interface a {
        void a(FacebookException facebookException);

        void a(JSONObject jSONObject);
    }

    /* compiled from: Utility */
    public interface b<T, K> {
        K a(T t);
    }

    /* compiled from: Utility */
    public static class c {
        List<String> a;
        List<String> b;

        public c(List<String> list, List<String> list2) {
            this.a = list;
            this.b = list2;
        }

        public List<String> a() {
            return this.a;
        }

        public List<String> b() {
            return this.b;
        }
    }

    public static <T> boolean a(Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public static String a(String str, String str2) {
        return a(str) ? str2 : str;
    }

    public static <T> Collection<T> a(T... tArr) {
        return Collections.unmodifiableCollection(Arrays.asList(tArr));
    }

    public static String b(String str) {
        return c("MD5", str);
    }

    public static String a(byte[] bArr) {
        return a("SHA-1", bArr);
    }

    private static String c(String str, String str2) {
        return a(str, str2.getBytes());
    }

    private static String a(String str, byte[] bArr) {
        try {
            return a(MessageDigest.getInstance(str), bArr);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    private static String a(MessageDigest messageDigest, byte[] bArr) {
        messageDigest.update(bArr);
        byte[] digest = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b2 : digest) {
            sb.append(Integer.toHexString((b2 >> 4) & 15));
            sb.append(Integer.toHexString((b2 >> 0) & 15));
        }
        return sb.toString();
    }

    public static Uri a(String str, String str2, Bundle bundle) {
        Builder builder = new Builder();
        builder.scheme("https");
        builder.authority(str);
        builder.path(str2);
        if (bundle != null) {
            for (String str3 : bundle.keySet()) {
                Object obj = bundle.get(str3);
                if (obj instanceof String) {
                    builder.appendQueryParameter(str3, (String) obj);
                }
            }
        }
        return builder.build();
    }

    public static Bundle c(String str) {
        Bundle bundle = new Bundle();
        if (!a(str)) {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                try {
                    if (split2.length == 2) {
                        bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), URLDecoder.decode(split2[1], "UTF-8"));
                    } else if (split2.length == 1) {
                        bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), "");
                    }
                } catch (UnsupportedEncodingException e2) {
                    a("FacebookSDK", (Exception) e2);
                }
            }
        }
        return bundle;
    }

    public static void a(Bundle bundle, String str, String str2) {
        if (!a(str2)) {
            bundle.putString(str, str2);
        }
    }

    public static void a(Bundle bundle, String str, Uri uri) {
        if (uri != null) {
            a(bundle, str, uri.toString());
        }
    }

    public static boolean a(Bundle bundle, String str, Object obj) {
        if (obj == null) {
            bundle.remove(str);
        } else if (obj instanceof Boolean) {
            bundle.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof boolean[]) {
            bundle.putBooleanArray(str, (boolean[]) obj);
        } else if (obj instanceof Double) {
            bundle.putDouble(str, ((Double) obj).doubleValue());
        } else if (obj instanceof double[]) {
            bundle.putDoubleArray(str, (double[]) obj);
        } else if (obj instanceof Integer) {
            bundle.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof int[]) {
            bundle.putIntArray(str, (int[]) obj);
        } else if (obj instanceof Long) {
            bundle.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof long[]) {
            bundle.putLongArray(str, (long[]) obj);
        } else if (obj instanceof String) {
            bundle.putString(str, (String) obj);
        } else if (obj instanceof JSONArray) {
            bundle.putString(str, obj.toString());
        } else if (!(obj instanceof JSONObject)) {
            return false;
        } else {
            bundle.putString(str, obj.toString());
        }
        return true;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(URLConnection uRLConnection) {
        if (uRLConnection != null && (uRLConnection instanceof HttpURLConnection)) {
            ((HttpURLConnection) uRLConnection).disconnect();
        }
    }

    public static String a(Context context) {
        aa.a((Object) context, ResponseConstants.CONTEXT);
        f.a(context);
        return f.j();
    }

    public static Object a(JSONObject jSONObject, String str, String str2) throws JSONException {
        Object opt = jSONObject.opt(str);
        if (opt != null && (opt instanceof String)) {
            opt = new JSONTokener((String) opt).nextValue();
        }
        if (opt == null || (opt instanceof JSONObject) || (opt instanceof JSONArray)) {
            return opt;
        }
        if (str2 != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt(str2, opt);
            return jSONObject2;
        }
        throw new FacebookException("Got an unexpected non-JSON object.");
    }

    public static String a(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader;
        BufferedInputStream bufferedInputStream;
        Throwable th;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                inputStreamReader = new InputStreamReader(bufferedInputStream);
                try {
                    StringBuilder sb = new StringBuilder();
                    char[] cArr = new char[2048];
                    while (true) {
                        int read = inputStreamReader.read(cArr);
                        if (read != -1) {
                            sb.append(cArr, 0, read);
                        } else {
                            String sb2 = sb.toString();
                            a((Closeable) bufferedInputStream);
                            a((Closeable) inputStreamReader);
                            return sb2;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    a((Closeable) bufferedInputStream);
                    a((Closeable) inputStreamReader);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStreamReader = null;
                a((Closeable) bufferedInputStream);
                a((Closeable) inputStreamReader);
                throw th;
            }
        } catch (Throwable th4) {
            bufferedInputStream = null;
            th = th4;
            inputStreamReader = null;
            a((Closeable) bufferedInputStream);
            a((Closeable) inputStreamReader);
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(java.io.InputStream r6, java.io.OutputStream r7) throws java.io.IOException {
        /*
            r0 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0025 }
            r1.<init>(r6)     // Catch:{ all -> 0x0025 }
            r0 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x0023 }
            r2 = 0
            r3 = r2
        L_0x000c:
            int r4 = r1.read(r0)     // Catch:{ all -> 0x0023 }
            r5 = -1
            if (r4 == r5) goto L_0x0018
            r7.write(r0, r2, r4)     // Catch:{ all -> 0x0023 }
            int r3 = r3 + r4
            goto L_0x000c
        L_0x0018:
            if (r1 == 0) goto L_0x001d
            r1.close()
        L_0x001d:
            if (r6 == 0) goto L_0x0022
            r6.close()
        L_0x0022:
            return r3
        L_0x0023:
            r7 = move-exception
            goto L_0x0027
        L_0x0025:
            r7 = move-exception
            r1 = r0
        L_0x0027:
            if (r1 == 0) goto L_0x002c
            r1.close()
        L_0x002c:
            if (r6 == 0) goto L_0x0031
            r6.close()
        L_0x0031:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.z.a(java.io.InputStream, java.io.OutputStream):int");
    }

    private static void a(Context context, String str) {
        CookieSyncManager.createInstance(context).sync();
        CookieManager instance = CookieManager.getInstance();
        String cookie = instance.getCookie(str);
        if (cookie != null) {
            for (String split : cookie.split(";")) {
                String[] split2 = split.split("=");
                if (split2.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(split2[0].trim());
                    sb.append("=;expires=Sat, 1 Jan 2000 00:00:01 UTC;");
                    instance.setCookie(str, sb.toString());
                }
            }
            instance.removeExpiredCookie();
        }
    }

    public static void b(Context context) {
        a(context, "facebook.com");
        a(context, ".facebook.com");
        a(context, "https://facebook.com");
        a(context, "https://.facebook.com");
    }

    public static void a(String str, Exception exc) {
        if (f.b() && str != null && exc != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(exc.getClass().getSimpleName());
            sb.append(": ");
            sb.append(exc.getMessage());
            Log.d(str, sb.toString());
        }
    }

    public static void b(String str, String str2) {
        if (f.b() && str != null && str2 != null) {
            Log.d(str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (f.b() && !a(str)) {
            Log.d(str, str2, th);
        }
    }

    public static <T> boolean a(T t, T t2) {
        if (t != null) {
            return t.equals(t2);
        }
        return t2 == null;
    }

    public static String a(JSONObject jSONObject, String str) {
        return jSONObject != null ? jSONObject.optString(str, "") : "";
    }

    public static JSONObject b(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject(str);
        }
        return null;
    }

    public static JSONArray c(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            return jSONObject.optJSONArray(str);
        }
        return null;
    }

    public static void a(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File a2 : listFiles) {
                        a(a2);
                    }
                }
            }
            file.delete();
        }
    }

    public static <T> List<T> b(T... tArr) {
        ArrayList arrayList = new ArrayList();
        for (T t : tArr) {
            if (t != null) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static List<String> a(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    public static Set<String> b(JSONArray jSONArray) throws JSONException {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(jSONArray.getString(i));
        }
        return hashSet;
    }

    public static void a(JSONObject jSONObject, b bVar, String str, boolean z) throws JSONException {
        if (!(bVar == null || bVar.a() == null)) {
            jSONObject.put("attribution", bVar.a());
        }
        if (!(bVar == null || bVar.b() == null)) {
            jSONObject.put("advertiser_id", bVar.b());
            jSONObject.put("advertiser_tracking_enabled", !bVar.d());
        }
        if (!(bVar == null || bVar.c() == null)) {
            jSONObject.put("installer_package", bVar.c());
        }
        jSONObject.put("anon_id", str);
        jSONObject.put("application_tracking_enabled", !z);
    }

    public static void a(JSONObject jSONObject, Context context) throws JSONException {
        int i;
        Locale locale;
        int i2;
        int i3;
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("a2");
        g(context);
        String packageName = context.getPackageName();
        String str = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            i = packageInfo.versionCode;
            try {
                str = packageInfo.versionName;
            } catch (NameNotFoundException unused) {
            }
        } catch (NameNotFoundException unused2) {
            i = -1;
        }
        jSONArray.put(packageName);
        jSONArray.put(i);
        jSONArray.put(str);
        jSONArray.put(VERSION.RELEASE);
        jSONArray.put(Build.MODEL);
        try {
            locale = context.getResources().getConfiguration().locale;
        } catch (Exception unused3) {
            locale = Locale.getDefault();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(locale.getLanguage());
        sb.append("_");
        sb.append(locale.getCountry());
        jSONArray.put(sb.toString());
        jSONArray.put(e);
        jSONArray.put(g);
        double d2 = 0.0d;
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                defaultDisplay.getMetrics(displayMetrics);
                i2 = displayMetrics.widthPixels;
                try {
                    i3 = displayMetrics.heightPixels;
                    try {
                        d2 = (double) displayMetrics.density;
                    } catch (Exception unused4) {
                    }
                } catch (Exception unused5) {
                    i3 = 0;
                }
                jSONArray.put(i2);
                jSONArray.put(i3);
                jSONArray.put(String.format("%.2f", new Object[]{Double.valueOf(d2)}));
                jSONArray.put(b());
                jSONArray.put(c);
                jSONArray.put(d);
                jSONArray.put(f);
                jSONObject.put("extinfo", jSONArray.toString());
            }
        } catch (Exception unused6) {
        }
        i2 = 0;
        i3 = 0;
        jSONArray.put(i2);
        jSONArray.put(i3);
        jSONArray.put(String.format("%.2f", new Object[]{Double.valueOf(d2)}));
        jSONArray.put(b());
        jSONArray.put(c);
        jSONArray.put(d);
        jSONArray.put(f);
        jSONObject.put("extinfo", jSONArray.toString());
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static Method a(String str, String str2, Class<?>... clsArr) {
        try {
            return a(Class.forName(str), str2, clsArr);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static Object a(Object obj, Method method, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException unused) {
            return null;
        } catch (InvocationTargetException unused2) {
            return null;
        }
    }

    public static String c(Context context) {
        if (context == null) {
            return "null";
        }
        if (context == context.getApplicationContext()) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return context.getClass().getSimpleName();
    }

    public static <T, K> List<K> a(List<T> list, b<T, K> bVar) {
        List<K> list2 = null;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (T a2 : list) {
            Object a3 = bVar.a(a2);
            if (a3 != null) {
                arrayList.add(a3);
            }
        }
        if (arrayList.size() != 0) {
            list2 = arrayList;
        }
        return list2;
    }

    public static String a(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }

    public static boolean b(Uri uri) {
        return uri != null && ("http".equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(uri.getScheme()) || "fbstaging".equalsIgnoreCase(uri.getScheme()));
    }

    public static boolean c(Uri uri) {
        return uri != null && ResponseConstants.CONTENT.equalsIgnoreCase(uri.getScheme());
    }

    public static boolean d(Uri uri) {
        return uri != null && ResponseConstants.FILE.equalsIgnoreCase(uri.getScheme());
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long e(android.net.Uri r9) {
        /*
            r0 = 0
            android.content.Context r1 = com.facebook.f.f()     // Catch:{ all -> 0x0027 }
            android.content.ContentResolver r2 = r1.getContentResolver()     // Catch:{ all -> 0x0027 }
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r9
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0027 }
            java.lang.String r0 = "_size"
            int r0 = r9.getColumnIndex(r0)     // Catch:{ all -> 0x0025 }
            r9.moveToFirst()     // Catch:{ all -> 0x0025 }
            long r0 = r9.getLong(r0)     // Catch:{ all -> 0x0025 }
            if (r9 == 0) goto L_0x0024
            r9.close()
        L_0x0024:
            return r0
        L_0x0025:
            r0 = move-exception
            goto L_0x002b
        L_0x0027:
            r9 = move-exception
            r8 = r0
            r0 = r9
            r9 = r8
        L_0x002b:
            if (r9 == 0) goto L_0x0030
            r9.close()
        L_0x0030:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.z.e(android.net.Uri):long");
    }

    public static Date a(Bundle bundle, String str, Date date) {
        long j;
        if (bundle == null) {
            return null;
        }
        Object obj = bundle.get(str);
        if (obj instanceof Long) {
            j = ((Long) obj).longValue();
        } else if (!(obj instanceof String)) {
            return null;
        } else {
            try {
                j = Long.parseLong((String) obj);
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        if (j == 0) {
            return new Date(Long.MAX_VALUE);
        }
        return new Date(date.getTime() + (j * 1000));
    }

    public static void a(Parcel parcel, Map<String, String> map) {
        if (map == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(map.size());
        for (Entry entry : map.entrySet()) {
            parcel.writeString((String) entry.getKey());
            parcel.writeString((String) entry.getValue());
        }
    }

    public static Map<String, String> a(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < readInt; i++) {
            hashMap.put(parcel.readString(), parcel.readString());
        }
        return hashMap;
    }

    public static boolean a(AccessToken accessToken) {
        return accessToken != null && accessToken.equals(AccessToken.getCurrentAccessToken());
    }

    public static void a(final String str, final a aVar) {
        JSONObject a2 = w.a(str);
        if (a2 != null) {
            aVar.a(a2);
            return;
        }
        AnonymousClass1 r0 = new com.facebook.GraphRequest.b() {
            public void a(GraphResponse graphResponse) {
                if (graphResponse.a() != null) {
                    aVar.a(graphResponse.a().getException());
                    return;
                }
                w.a(str, graphResponse.b());
                aVar.a(graphResponse.b());
            }
        };
        GraphRequest e2 = e(str);
        e2.a((com.facebook.GraphRequest.b) r0);
        e2.j();
    }

    public static JSONObject d(String str) {
        JSONObject a2 = w.a(str);
        if (a2 != null) {
            return a2;
        }
        GraphResponse i = e(str).i();
        if (i.a() != null) {
            return null;
        }
        return i.b();
    }

    private static GraphRequest e(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,name,first_name,middle_name,last_name,link");
        bundle.putString(AccessToken.ACCESS_TOKEN_KEY, str);
        GraphRequest graphRequest = new GraphRequest(null, "me", bundle, HttpMethod.GET, null);
        return graphRequest;
    }

    private static int b() {
        if (a > 0) {
            return a;
        }
        try {
            File[] listFiles = new File("/sys/devices/system/cpu/").listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return Pattern.matches("cpu[0-9]+", str);
                }
            });
            if (listFiles != null) {
                a = listFiles.length;
            }
        } catch (Exception unused) {
        }
        if (a <= 0) {
            a = Math.max(Runtime.getRuntime().availableProcessors(), 1);
        }
        return a;
    }

    private static void g(Context context) {
        if (b == -1 || System.currentTimeMillis() - b >= 1800000) {
            b = System.currentTimeMillis();
            c();
            h(context);
            f();
            e();
        }
    }

    private static void c() {
        try {
            TimeZone timeZone = TimeZone.getDefault();
            e = timeZone.getDisplayName(timeZone.inDaylightTime(new Date()), 0);
            f = timeZone.getID();
        } catch (AssertionError | Exception unused) {
        }
    }

    private static void h(Context context) {
        if (g.equals("NoCarrier")) {
            try {
                g = ((TelephonyManager) context.getSystemService(ResponseConstants.PHONE)).getNetworkOperatorName();
            } catch (Exception unused) {
            }
        }
    }

    private static boolean d() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    private static void e() {
        try {
            if (d()) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                d = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
            }
            d = a((double) d);
        } catch (Exception unused) {
        }
    }

    private static void f() {
        try {
            if (d()) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                c = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
            }
            c = a((double) c);
        } catch (Exception unused) {
        }
    }

    private static long a(double d2) {
        return Math.round(d2 / 1.073741824E9d);
    }

    public static c a(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONObject("permissions").getJSONArray("data");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        ArrayList arrayList2 = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            String optString = optJSONObject.optString("permission");
            if (optString != null && !optString.equals("installed")) {
                String optString2 = optJSONObject.optString("status");
                if (optString2 != null) {
                    if (optString2.equals("granted")) {
                        arrayList.add(optString);
                    } else if (optString2.equals("declined")) {
                        arrayList2.add(optString);
                    }
                }
            }
        }
        return new c(arrayList, arrayList2);
    }

    public static String a(int i) {
        return new BigInteger(i * 5, new Random()).toString(32);
    }

    public static boolean d(Context context) {
        return e(context);
    }

    public static boolean e(Context context) {
        boolean z = false;
        if (VERSION.SDK_INT < 26) {
            return false;
        }
        AutofillManager autofillManager = (AutofillManager) context.getSystemService(AutofillManager.class);
        if (autofillManager != null && autofillManager.isAutofillSupported() && autofillManager.isEnabled()) {
            z = true;
        }
        return z;
    }

    public static boolean f(Context context) {
        if (VERSION.SDK_INT >= 27) {
            return context.getPackageManager().hasSystemFeature("android.hardware.type.pc");
        }
        return Build.DEVICE != null && Build.DEVICE.matches(".+_cheets|cheets_.+");
    }

    public static Locale a() {
        try {
            return f.f().getResources().getConfiguration().locale;
        } catch (Exception unused) {
            return Locale.getDefault();
        }
    }
}
