package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog.Builder;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;
import org.apache.commons.math3.geometry.VectorFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class hd {
    public static final Handler a = new zzakc(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final Object b = new Object();
    /* access modifiers changed from: private */
    public boolean c = true;
    /* access modifiers changed from: private */
    public String d;
    private boolean e = false;
    private boolean f = false;
    private Pattern g;
    private Pattern h;

    public static Bitmap a(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    @VisibleForTesting
    public static Bundle a(age age) {
        String str;
        String str2;
        String str3;
        if (age == null) {
            return null;
        }
        if (!((Boolean) ajh.f().a(akl.W)).booleanValue()) {
            if (!((Boolean) ajh.f().a(akl.Y)).booleanValue()) {
                return null;
            }
        }
        if (ao.i().l().b() && ao.i().l().d()) {
            return null;
        }
        if (age.d()) {
            age.c();
        }
        afy b2 = age.b();
        if (b2 != null) {
            str3 = b2.b();
            str2 = b2.c();
            str = b2.d();
            if (str3 != null) {
                ao.i().l().a(str3);
            }
            if (str != null) {
                ao.i().l().b(str);
            }
        } else {
            str3 = ao.i().l().c();
            str = ao.i().l().e();
            str2 = null;
        }
        Bundle bundle = new Bundle(1);
        if (str != null) {
            if (((Boolean) ajh.f().a(akl.Y)).booleanValue() && !ao.i().l().d()) {
                bundle.putString("v_fp_vertical", str);
            }
        }
        if (str3 != null) {
            if (((Boolean) ajh.f().a(akl.W)).booleanValue() && !ao.i().l().b()) {
                bundle.putString("fingerprint", str3);
                if (!str3.equals(str2)) {
                    bundle.putString("v_fp", str2);
                }
            }
        }
        if (!bundle.isEmpty()) {
            return bundle;
        }
        return null;
    }

    public static DisplayMetrics a(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static WebResourceResponse a(HttpURLConnection httpURLConnection) throws IOException {
        String str;
        ao.e();
        String contentType = httpURLConnection.getContentType();
        String trim = TextUtils.isEmpty(contentType) ? "" : contentType.split(";")[0].trim();
        ao.e();
        String contentType2 = httpURLConnection.getContentType();
        if (!TextUtils.isEmpty(contentType2)) {
            String[] split = contentType2.split(";");
            if (split.length != 1) {
                int i = 1;
                while (true) {
                    if (i >= split.length) {
                        break;
                    }
                    if (split[i].trim().startsWith("charset")) {
                        String[] split2 = split[i].trim().split("=");
                        if (split2.length > 1) {
                            str = split2[1].trim();
                            break;
                        }
                    }
                    i++;
                }
            }
        }
        str = "";
        String str2 = str;
        Map headerFields = httpURLConnection.getHeaderFields();
        HashMap hashMap = new HashMap(headerFields.size());
        for (Entry entry : headerFields.entrySet()) {
            if (!(entry.getKey() == null || entry.getValue() == null || ((List) entry.getValue()).size() <= 0)) {
                hashMap.put((String) entry.getKey(), (String) ((List) entry.getValue()).get(0));
            }
        }
        return ao.g().a(trim, str2, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage(), hashMap, httpURLConnection.getInputStream());
    }

    public static PopupWindow a(View view, int i, int i2, boolean z) {
        return new PopupWindow(view, i, i2, false);
    }

    public static String a() {
        return UUID.randomUUID().toString();
    }

    public static String a(Context context, View view, zzjn zzjn) {
        if (!((Boolean) ajh.f().a(akl.ak)).booleanValue()) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(ResponseConstants.WIDTH, zzjn.width);
            jSONObject2.put(ResponseConstants.HEIGHT, zzjn.height);
            jSONObject.put(ResponseConstants.SIZE, jSONObject2);
            jSONObject.put("activity", l(context));
            if (!zzjn.zzarc) {
                JSONArray jSONArray = new JSONArray();
                while (view != null) {
                    ViewParent parent = view.getParent();
                    if (parent != null) {
                        int i = -1;
                        if (parent instanceof ViewGroup) {
                            i = ((ViewGroup) parent).indexOfChild(view);
                        }
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("type", parent.getClass().getName());
                        jSONObject3.put("index_of_child", i);
                        jSONArray.put(jSONObject3);
                    }
                    view = (parent == null || !(parent instanceof View)) ? null : (View) parent;
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("parents", jSONArray);
                }
            }
            return jSONObject.toString();
        } catch (JSONException e2) {
            gv.c("Fail to get view hierarchy json", e2);
            return null;
        }
    }

    public static String a(InputStreamReader inputStreamReader) throws IOException {
        StringBuilder sb = new StringBuilder(8192);
        char[] cArr = new char[2048];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read == -1) {
                return sb.toString();
            }
            sb.append(cArr, 0, read);
        }
    }

    public static String a(String str) {
        return Uri.parse(str).buildUpon().query(null).build().toString();
    }

    public static Map<String, String> a(Uri uri) {
        if (uri == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String str : ao.g().a(uri)) {
            hashMap.put(str, uri.getQueryParameter(str));
        }
        return hashMap;
    }

    private final JSONArray a(Collection<?> collection) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object a2 : collection) {
            a(jSONArray, a2);
        }
        return jSONArray;
    }

    private final JSONObject a(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            a(jSONObject, str, bundle.get(str));
        }
        return jSONObject;
    }

    public static void a(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Throwable unused) {
            intent.addFlags(ErrorDialogData.BINDER_CRASH);
            context.startActivity(intent);
        }
    }

    @TargetApi(18)
    public static void a(Context context, Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            if (((Boolean) ajh.f().a(akl.cL)).booleanValue()) {
                b(context, intent);
            }
            bundle.putString("com.android.browser.application_id", context.getPackageName());
            context.startActivity(intent);
            String uri2 = uri.toString();
            StringBuilder sb = new StringBuilder(26 + String.valueOf(uri2).length());
            sb.append("Opening ");
            sb.append(uri2);
            sb.append(" in a new browser.");
            gv.b(sb.toString());
        } catch (ActivityNotFoundException e2) {
            gv.b("No browser is found.", e2);
        }
    }

    public static void a(Context context, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        a(context, str, (List<String>) arrayList);
    }

    public static void a(Context context, String str, List<String> list) {
        for (String jdVar : list) {
            new jd(context, str, jdVar).c();
        }
    }

    public static void a(Context context, Throwable th) {
        if (context != null) {
            boolean z = false;
            try {
                z = ((Boolean) ajh.f().a(akl.c)).booleanValue();
            } catch (IllegalStateException unused) {
            }
            if (z) {
                CrashUtils.addDynamiteErrorToDropBox(context, th);
            }
        }
    }

    public static void a(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            runnable.run();
        } else {
            hb.a(runnable);
        }
    }

    private final void a(JSONArray jSONArray, Object obj) throws JSONException {
        Object a2;
        if (obj instanceof Bundle) {
            a2 = a((Bundle) obj);
        } else if (obj instanceof Map) {
            a2 = a((Map) obj);
        } else if (obj instanceof Collection) {
            a2 = a((Collection) obj);
        } else if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            JSONArray jSONArray2 = new JSONArray();
            for (Object a3 : objArr) {
                a(jSONArray2, a3);
            }
            jSONArray.put(jSONArray2);
            return;
        } else {
            jSONArray.put(obj);
            return;
        }
        jSONArray.put(a2);
    }

    private final void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
        Collection asList;
        Object a2;
        if (obj instanceof Bundle) {
            a2 = a((Bundle) obj);
        } else if (obj instanceof Map) {
            a2 = a((Map) obj);
        } else {
            if (obj instanceof Collection) {
                if (str == null) {
                    str = "null";
                }
                asList = (Collection) obj;
            } else if (obj instanceof Object[]) {
                asList = Arrays.asList((Object[]) obj);
            } else {
                jSONObject.put(str, obj);
                return;
            }
            a2 = a(asList);
        }
        jSONObject.put(str, a2);
    }

    @VisibleForTesting
    private static boolean a(int i, int i2, int i3) {
        return Math.abs(i - i2) <= i3;
    }

    @TargetApi(24)
    public static boolean a(Activity activity, Configuration configuration) {
        ajh.a();
        int a2 = jp.a((Context) activity, configuration.screenHeightDp);
        int a3 = jp.a((Context) activity, configuration.screenWidthDp);
        DisplayMetrics a4 = a((WindowManager) activity.getApplicationContext().getSystemService("window"));
        int i = a4.heightPixels;
        int i2 = a4.widthPixels;
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", AppBuild.ANDROID_PLATFORM);
        int dimensionPixelSize = identifier > 0 ? activity.getResources().getDimensionPixelSize(identifier) : 0;
        int round = ((int) Math.round(((double) activity.getResources().getDisplayMetrics().density) + 0.5d)) * ((Integer) ajh.f().a(akl.cX)).intValue();
        return a(i, a2 + dimensionPixelSize, round) && a(i2, a3, round);
    }

    public static boolean a(Context context) {
        String str;
        boolean z;
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        try {
            ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
            if (resolveActivity == null || resolveActivity.activityInfo == null) {
                str = "Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.";
            } else {
                String str2 = "com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".";
                if ((resolveActivity.activityInfo.configChanges & 16) == 0) {
                    gv.e(String.format(str2, new Object[]{"keyboard"}));
                    z = false;
                } else {
                    z = true;
                }
                if ((resolveActivity.activityInfo.configChanges & 32) == 0) {
                    gv.e(String.format(str2, new Object[]{"keyboardHidden"}));
                    z = false;
                }
                if ((resolveActivity.activityInfo.configChanges & 128) == 0) {
                    gv.e(String.format(str2, new Object[]{"orientation"}));
                    z = false;
                }
                if ((resolveActivity.activityInfo.configChanges & 256) == 0) {
                    gv.e(String.format(str2, new Object[]{"screenLayout"}));
                    z = false;
                }
                if ((resolveActivity.activityInfo.configChanges & 512) == 0) {
                    gv.e(String.format(str2, new Object[]{"uiMode"}));
                    z = false;
                }
                if ((resolveActivity.activityInfo.configChanges & 1024) == 0) {
                    gv.e(String.format(str2, new Object[]{"screenSize"}));
                    z = false;
                }
                if ((resolveActivity.activityInfo.configChanges & 2048) != 0) {
                    return z;
                }
                str = String.format(str2, new Object[]{"smallestScreenSize"});
            }
            gv.e(str);
            return false;
        } catch (Exception e2) {
            gv.c("Could not verify that com.google.android.gms.ads.AdActivity is declared in AndroidManifest.xml", e2);
            ao.i().a((Throwable) e2, "AdUtil.hasAdActivity");
            return false;
        }
    }

    public static boolean a(Context context, String str) {
        return Wrappers.packageManager(context).checkPermission(str, context.getPackageName()) == 0;
    }

    public static boolean a(ClassLoader classLoader, Class<?> cls, String str) {
        try {
            return cls.isAssignableFrom(Class.forName(str, false, classLoader));
        } catch (Throwable unused) {
            return false;
        }
    }

    public static int[] a(Activity activity) {
        Window window = activity.getWindow();
        if (window != null) {
            View findViewById = window.findViewById(16908290);
            if (findViewById != null) {
                return new int[]{findViewById.getWidth(), findViewById.getHeight()};
            }
        }
        return e();
    }

    public static int b(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            String valueOf = String.valueOf(e2);
            StringBuilder sb = new StringBuilder(22 + String.valueOf(valueOf).length());
            sb.append("Could not parse value:");
            sb.append(valueOf);
            gv.e(sb.toString());
            return 0;
        }
    }

    public static Bitmap b(View view) {
        if (view == null) {
            return null;
        }
        Bitmap f2 = f(view);
        if (f2 == null) {
            f2 = e(view);
        }
        return f2;
    }

    public static String b() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return str2;
        }
        StringBuilder sb = new StringBuilder(1 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append(str);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(str2);
        return sb.toString();
    }

    @TargetApi(18)
    public static void b(Context context, Intent intent) {
        if (intent != null && PlatformVersion.isAtLeastJellyBeanMR2()) {
            Bundle extras = intent.getExtras() != null ? intent.getExtras() : new Bundle();
            extras.putBinder(CustomTabsIntent.EXTRA_SESSION, null);
            extras.putString("com.android.browser.application_id", context.getPackageName());
            intent.putExtras(extras);
        }
    }

    public static void b(Context context, String str, String str2) {
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            openFileOutput.write(str2.getBytes("UTF-8"));
            openFileOutput.close();
        } catch (Exception e2) {
            gv.b("Error writing to file in internal storage.", e2);
        }
    }

    public static Bundle c() {
        Bundle bundle = new Bundle();
        try {
            if (((Boolean) ajh.f().a(akl.C)).booleanValue()) {
                MemoryInfo memoryInfo = new MemoryInfo();
                Debug.getMemoryInfo(memoryInfo);
                bundle.putParcelable("debug_memory_info", memoryInfo);
            }
            if (((Boolean) ajh.f().a(akl.D)).booleanValue()) {
                Runtime runtime = Runtime.getRuntime();
                bundle.putLong("runtime_free_memory", runtime.freeMemory());
                bundle.putLong("runtime_max_memory", runtime.maxMemory());
                bundle.putLong("runtime_total_memory", runtime.totalMemory());
            }
            bundle.putInt("web_view_count", ao.i().k());
            return bundle;
        } catch (Exception e2) {
            gv.c("Unable to gather memory stats", e2);
            return bundle;
        }
    }

    @Nullable
    public static WebResourceResponse c(Context context, String str, String str2) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("User-Agent", ao.e().b(context, str));
            hashMap.put("Cache-Control", "max-stale=3600");
            String str3 = (String) new in(context).a(str2, (Map<String, String>) hashMap).get(60, TimeUnit.SECONDS);
            if (str3 != null) {
                return new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(str3.getBytes("UTF-8")));
            }
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e2) {
            gv.c("Could not fetch MRAID JS.", e2);
        }
        return null;
    }

    public static String c(Context context, String str) {
        try {
            return new String(IOUtils.readInputStreamFully(context.openFileInput(str), true), "UTF-8");
        } catch (IOException unused) {
            gv.b("Error reading from internal storage.");
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0016 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean c(android.view.View r2) {
        /*
            android.view.View r2 = r2.getRootView()
            r0 = 0
            if (r2 == 0) goto L_0x0012
            android.content.Context r2 = r2.getContext()
            boolean r1 = r2 instanceof android.app.Activity
            if (r1 == 0) goto L_0x0012
            android.app.Activity r2 = (android.app.Activity) r2
            goto L_0x0013
        L_0x0012:
            r2 = r0
        L_0x0013:
            r1 = 0
            if (r2 != 0) goto L_0x0017
            return r1
        L_0x0017:
            android.view.Window r2 = r2.getWindow()
            if (r2 != 0) goto L_0x001e
            goto L_0x0022
        L_0x001e:
            android.view.WindowManager$LayoutParams r0 = r2.getAttributes()
        L_0x0022:
            if (r0 == 0) goto L_0x002d
            int r2 = r0.flags
            r0 = 524288(0x80000, float:7.34684E-40)
            r2 = r2 & r0
            if (r2 == 0) goto L_0x002d
            r2 = 1
            return r2
        L_0x002d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.hd.c(android.view.View):boolean");
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }

    public static int d(@Nullable View view) {
        if (view == null) {
            return -1;
        }
        ViewParent parent = view.getParent();
        while (parent != null && !(parent instanceof AdapterView)) {
            parent = parent.getParent();
        }
        if (parent == null) {
            return -1;
        }
        return ((AdapterView) parent).getPositionForView(view);
    }

    private static String d() {
        StringBuilder sb = new StringBuilder(256);
        sb.append("Mozilla/5.0 (Linux; U; Android");
        if (VERSION.RELEASE != null) {
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(VERSION.RELEASE);
        }
        sb.append(VectorFormat.DEFAULT_SEPARATOR);
        sb.append(Locale.getDefault());
        if (Build.DEVICE != null) {
            sb.append(VectorFormat.DEFAULT_SEPARATOR);
            sb.append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                sb.append(" Build/");
                sb.append(Build.DISPLAY);
            }
        }
        sb.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return sb.toString();
    }

    @VisibleForTesting
    protected static String d(Context context) {
        try {
            return new WebView(context).getSettings().getUserAgentString();
        } catch (Throwable unused) {
            return d();
        }
    }

    public static Builder e(Context context) {
        return new Builder(context);
    }

    private static Bitmap e(@NonNull View view) {
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            if (width != 0) {
                if (height != 0) {
                    Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.RGB_565);
                    Canvas canvas = new Canvas(createBitmap);
                    view.layout(0, 0, width, height);
                    view.draw(canvas);
                    return createBitmap;
                }
            }
            gv.e("Width or height of view is zero");
            return null;
        } catch (RuntimeException e2) {
            gv.b("Fail to capture the webview", e2);
            return null;
        }
    }

    private static int[] e() {
        return new int[]{0, 0};
    }

    private static Bitmap f(@NonNull View view) {
        Bitmap bitmap = null;
        try {
            boolean isDrawingCacheEnabled = view.isDrawingCacheEnabled();
            view.setDrawingCacheEnabled(true);
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                bitmap = Bitmap.createBitmap(drawingCache);
            }
            view.setDrawingCacheEnabled(isDrawingCacheEnabled);
            return bitmap;
        } catch (RuntimeException e2) {
            gv.b("Fail to capture the web view", e2);
            return null;
        }
    }

    public static ajw f(Context context) {
        return new ajw(context);
    }

    public static boolean g(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null) {
                return false;
            }
            List runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            Iterator it = runningAppProcesses.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                RunningAppProcessInfo runningAppProcessInfo = (RunningAppProcessInfo) it.next();
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (runningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode()) {
                        PowerManager powerManager = (PowerManager) context.getSystemService("power");
                        if (powerManager == null ? false : powerManager.isScreenOn()) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Throwable unused) {
        }
    }

    public static Bitmap h(Context context) {
        Bitmap bitmap;
        if (!(context instanceof Activity)) {
            return null;
        }
        try {
            if (((Boolean) ajh.f().a(akl.bS)).booleanValue()) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    bitmap = f(window.getDecorView().getRootView());
                }
                return null;
            }
            bitmap = e(((Activity) context).getWindow().getDecorView());
            return bitmap;
        } catch (RuntimeException e2) {
            gv.b("Fail to capture screen shot", e2);
        }
    }

    public static int i(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return 0;
        }
        return applicationInfo.targetSdkVersion;
    }

    @TargetApi(16)
    public static boolean j(Context context) {
        if (context == null || !PlatformVersion.isAtLeastJellyBean()) {
            return false;
        }
        KeyguardManager m = m(context);
        if (m != null && m.isKeyguardLocked()) {
            return true;
        }
        return false;
    }

    public static boolean k(Context context) {
        try {
            context.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi");
            return false;
        } catch (ClassNotFoundException unused) {
            return true;
        } catch (Throwable th) {
            gv.b("Error loading class.", th);
            ao.i().a(th, "AdUtil.isLiteSdk");
            return false;
        }
    }

    private static String l(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return null;
            }
            List runningTasks = activityManager.getRunningTasks(1);
            if (runningTasks != null && !runningTasks.isEmpty()) {
                RunningTaskInfo runningTaskInfo = (RunningTaskInfo) runningTasks.get(0);
                if (!(runningTaskInfo == null || runningTaskInfo.topActivity == null)) {
                    return runningTaskInfo.topActivity.getClassName();
                }
            }
            return null;
        } catch (Exception unused) {
        }
    }

    @Nullable
    private static KeyguardManager m(Context context) {
        Object systemService = context.getSystemService("keyguard");
        if (systemService == null || !(systemService instanceof KeyguardManager)) {
            return null;
        }
        return (KeyguardManager) systemService;
    }

    public final JSONObject a(@Nullable Bundle bundle, JSONObject jSONObject) {
        if (bundle != null) {
            try {
                return a(bundle);
            } catch (JSONException e2) {
                gv.b("Error converting Bundle to JSON", e2);
            }
        }
        return null;
    }

    public final JSONObject a(Map<String, ?> map) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : map.keySet()) {
                a(jSONObject, str, map.get(str));
            }
            return jSONObject;
        } catch (ClassCastException e2) {
            String str2 = "Could not convert map to JSON: ";
            String valueOf = String.valueOf(e2.getMessage());
            throw new JSONException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
    }

    public final void a(Context context, String str, WebSettings webSettings) {
        webSettings.setUserAgentString(b(context, str));
    }

    public final void a(Context context, @Nullable String str, String str2, Bundle bundle, boolean z) {
        if (z) {
            ao.e();
            bundle.putString("device", b());
            bundle.putString("eids", TextUtils.join(",", akl.a()));
        }
        ajh.a();
        jp.a(context, str, str2, bundle, z, new hg(this, context, str));
    }

    public final void a(Context context, String str, boolean z, HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", b(context, str));
        httpURLConnection.setUseCaches(false);
    }

    public final void a(Context context, List<String> list) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!TextUtils.isEmpty(abf.a(activity))) {
                if (list == null) {
                    gv.a("Cannot ping urls: empty list.");
                } else if (!ala.a(context)) {
                    gv.a("Cannot ping url because custom tabs is not supported");
                } else {
                    ala ala = new ala();
                    ala.a((alb) new he(this, list, ala, context));
                    ala.b(activity);
                }
            }
        }
    }

    public final boolean a(View view, Context context) {
        Context applicationContext = context.getApplicationContext();
        return a(view, applicationContext != null ? (PowerManager) applicationContext.getSystemService("power") : null, m(context));
    }

    public final boolean a(View view, PowerManager powerManager, KeyguardManager keyguardManager) {
        boolean z;
        if (!ao.e().c) {
            if (keyguardManager == null ? false : keyguardManager.inKeyguardRestrictedInputMode()) {
                if (!((Boolean) ajh.f().a(akl.bo)).booleanValue() || !c(view)) {
                    z = false;
                    if (view.getVisibility() == 0 && view.isShown()) {
                        if ((powerManager != null || powerManager.isScreenOn()) && z) {
                            if (((Boolean) ajh.f().a(akl.bm)).booleanValue() || view.getLocalVisibleRect(new Rect()) || view.getGlobalVisibleRect(new Rect())) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        z = true;
        if (((Boolean) ajh.f().a(akl.bm)).booleanValue()) {
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x003b, code lost:
        continue;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x001d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0045 */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0025 A[Catch:{ Exception -> 0x00b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0059 A[Catch:{ Exception -> 0x00b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005e A[Catch:{ Exception -> 0x00b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a8 A[Catch:{ Exception -> 0x00b7 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String b(android.content.Context r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.b
            monitor-enter(r0)
            java.lang.String r1 = r4.d     // Catch:{ all -> 0x00d3 }
            if (r1 == 0) goto L_0x000b
            java.lang.String r5 = r4.d     // Catch:{ all -> 0x00d3 }
            monitor-exit(r0)     // Catch:{ all -> 0x00d3 }
            return r5
        L_0x000b:
            if (r6 != 0) goto L_0x0013
            java.lang.String r5 = d()     // Catch:{ all -> 0x00d3 }
            monitor-exit(r0)     // Catch:{ all -> 0x00d3 }
            return r5
        L_0x0013:
            com.google.android.gms.internal.ads.hj r1 = com.google.android.gms.ads.internal.ao.g()     // Catch:{ Exception -> 0x001d }
            java.lang.String r1 = r1.a(r5)     // Catch:{ Exception -> 0x001d }
            r4.d = r1     // Catch:{ Exception -> 0x001d }
        L_0x001d:
            java.lang.String r1 = r4.d     // Catch:{ all -> 0x00d3 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x00d3 }
            if (r1 == 0) goto L_0x006e
            com.google.android.gms.internal.ads.ajh.a()     // Catch:{ all -> 0x00d3 }
            boolean r1 = com.google.android.gms.internal.ads.jp.b()     // Catch:{ all -> 0x00d3 }
            if (r1 != 0) goto L_0x0068
            r1 = 0
            r4.d = r1     // Catch:{ all -> 0x00d3 }
            android.os.Handler r1 = a     // Catch:{ all -> 0x00d3 }
            com.google.android.gms.internal.ads.hf r2 = new com.google.android.gms.internal.ads.hf     // Catch:{ all -> 0x00d3 }
            r2.<init>(r4, r5)     // Catch:{ all -> 0x00d3 }
            r1.post(r2)     // Catch:{ all -> 0x00d3 }
        L_0x003b:
            java.lang.String r1 = r4.d     // Catch:{ all -> 0x00d3 }
            if (r1 != 0) goto L_0x006e
            java.lang.Object r1 = r4.b     // Catch:{ InterruptedException -> 0x0045 }
            r1.wait()     // Catch:{ InterruptedException -> 0x0045 }
            goto L_0x003b
        L_0x0045:
            java.lang.String r1 = d()     // Catch:{ all -> 0x00d3 }
            r4.d = r1     // Catch:{ all -> 0x00d3 }
            java.lang.String r1 = "Interrupted, use default user agent: "
            java.lang.String r2 = r4.d     // Catch:{ all -> 0x00d3 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x00d3 }
            int r3 = r2.length()     // Catch:{ all -> 0x00d3 }
            if (r3 == 0) goto L_0x005e
            java.lang.String r1 = r1.concat(r2)     // Catch:{ all -> 0x00d3 }
            goto L_0x0064
        L_0x005e:
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x00d3 }
            r2.<init>(r1)     // Catch:{ all -> 0x00d3 }
            r1 = r2
        L_0x0064:
            com.google.android.gms.internal.ads.gv.e(r1)     // Catch:{ all -> 0x00d3 }
            goto L_0x003b
        L_0x0068:
            java.lang.String r1 = d(r5)     // Catch:{ all -> 0x00d3 }
            r4.d = r1     // Catch:{ all -> 0x00d3 }
        L_0x006e:
            java.lang.String r1 = r4.d     // Catch:{ all -> 0x00d3 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00d3 }
            r2 = 10
            java.lang.String r3 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00d3 }
            int r3 = r3.length()     // Catch:{ all -> 0x00d3 }
            int r2 = r2 + r3
            java.lang.String r3 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x00d3 }
            int r3 = r3.length()     // Catch:{ all -> 0x00d3 }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d3 }
            r3.<init>(r2)     // Catch:{ all -> 0x00d3 }
            r3.append(r1)     // Catch:{ all -> 0x00d3 }
            java.lang.String r1 = " (Mobile; "
            r3.append(r1)     // Catch:{ all -> 0x00d3 }
            r3.append(r6)     // Catch:{ all -> 0x00d3 }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x00d3 }
            r4.d = r6     // Catch:{ all -> 0x00d3 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r5 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r5)     // Catch:{ Exception -> 0x00b7 }
            boolean r5 = r5.isCallerInstantApp()     // Catch:{ Exception -> 0x00b7 }
            if (r5 == 0) goto L_0x00c1
            java.lang.String r5 = r4.d     // Catch:{ Exception -> 0x00b7 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x00b7 }
            java.lang.String r6 = ";aia"
            java.lang.String r5 = r5.concat(r6)     // Catch:{ Exception -> 0x00b7 }
            r4.d = r5     // Catch:{ Exception -> 0x00b7 }
            goto L_0x00c1
        L_0x00b7:
            r5 = move-exception
            com.google.android.gms.internal.ads.gf r6 = com.google.android.gms.ads.internal.ao.i()     // Catch:{ all -> 0x00d3 }
            java.lang.String r1 = "AdUtil.getUserAgent"
            r6.a(r5, r1)     // Catch:{ all -> 0x00d3 }
        L_0x00c1:
            java.lang.String r5 = r4.d     // Catch:{ all -> 0x00d3 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x00d3 }
            java.lang.String r6 = ")"
            java.lang.String r5 = r5.concat(r6)     // Catch:{ all -> 0x00d3 }
            r4.d = r5     // Catch:{ all -> 0x00d3 }
            java.lang.String r5 = r4.d     // Catch:{ all -> 0x00d3 }
            monitor-exit(r0)     // Catch:{ all -> 0x00d3 }
            return r5
        L_0x00d3:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00d3 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.hd.b(android.content.Context, java.lang.String):java.lang.String");
    }

    public final void b(Context context, String str, String str2, Bundle bundle, boolean z) {
        if (((Boolean) ajh.f().a(akl.br)).booleanValue()) {
            a(context, str, str2, bundle, z);
        }
    }

    public final boolean b(Context context) {
        if (this.e) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver(new hi(this, null), intentFilter);
        this.e = true;
        return true;
    }

    public final int[] b(Activity activity) {
        int[] a2 = a(activity);
        ajh.a();
        ajh.a();
        return new int[]{jp.b((Context) activity, a2[0]), jp.b((Context) activity, a2[1])};
    }

    public final boolean c(Context context) {
        if (this.f) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.ads.intent.DEBUG_LOGGING_ENABLEMENT_CHANGED");
        context.getApplicationContext().registerReceiver(new hh(this, null), intentFilter);
        this.f = true;
        return true;
    }

    public final int[] c(Activity activity) {
        int[] iArr;
        Window window = activity.getWindow();
        if (window != null) {
            View findViewById = window.findViewById(16908290);
            if (findViewById != null) {
                iArr = new int[]{findViewById.getTop(), findViewById.getBottom()};
                ajh.a();
                ajh.a();
                return new int[]{jp.b((Context) activity, iArr[0]), jp.b((Context) activity, iArr[1])};
            }
        }
        iArr = e();
        ajh.a();
        ajh.a();
        return new int[]{jp.b((Context) activity, iArr[0]), jp.b((Context) activity, iArr[1])};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0023, code lost:
        if (((java.lang.String) com.google.android.gms.internal.ads.ajh.f().a(com.google.android.gms.internal.ads.akl.aq)).equals(r3.g.pattern()) == false) goto L_0x0025;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean d(java.lang.String r4) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            monitor-enter(r3)     // Catch:{ PatternSyntaxException -> 0x0046 }
            java.util.regex.Pattern r0 = r3.g     // Catch:{ all -> 0x0043 }
            if (r0 == 0) goto L_0x0025
            com.google.android.gms.internal.ads.akb<java.lang.String> r0 = com.google.android.gms.internal.ads.akl.aq     // Catch:{ all -> 0x0043 }
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x0043 }
            java.lang.Object r0 = r2.a(r0)     // Catch:{ all -> 0x0043 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0043 }
            java.util.regex.Pattern r2 = r3.g     // Catch:{ all -> 0x0043 }
            java.lang.String r2 = r2.pattern()     // Catch:{ all -> 0x0043 }
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x0043 }
            if (r0 != 0) goto L_0x0037
        L_0x0025:
            com.google.android.gms.internal.ads.akb<java.lang.String> r0 = com.google.android.gms.internal.ads.akl.aq     // Catch:{ all -> 0x0043 }
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x0043 }
            java.lang.Object r0 = r2.a(r0)     // Catch:{ all -> 0x0043 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0043 }
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)     // Catch:{ all -> 0x0043 }
            r3.g = r0     // Catch:{ all -> 0x0043 }
        L_0x0037:
            java.util.regex.Pattern r0 = r3.g     // Catch:{ all -> 0x0043 }
            java.util.regex.Matcher r4 = r0.matcher(r4)     // Catch:{ all -> 0x0043 }
            boolean r4 = r4.matches()     // Catch:{ all -> 0x0043 }
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            return r4
        L_0x0043:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            throw r4     // Catch:{ PatternSyntaxException -> 0x0046 }
        L_0x0046:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.hd.d(java.lang.String):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0023, code lost:
        if (((java.lang.String) com.google.android.gms.internal.ads.ajh.f().a(com.google.android.gms.internal.ads.akl.ar)).equals(r3.h.pattern()) == false) goto L_0x0025;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean e(java.lang.String r4) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            monitor-enter(r3)     // Catch:{ PatternSyntaxException -> 0x0046 }
            java.util.regex.Pattern r0 = r3.h     // Catch:{ all -> 0x0043 }
            if (r0 == 0) goto L_0x0025
            com.google.android.gms.internal.ads.akb<java.lang.String> r0 = com.google.android.gms.internal.ads.akl.ar     // Catch:{ all -> 0x0043 }
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x0043 }
            java.lang.Object r0 = r2.a(r0)     // Catch:{ all -> 0x0043 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0043 }
            java.util.regex.Pattern r2 = r3.h     // Catch:{ all -> 0x0043 }
            java.lang.String r2 = r2.pattern()     // Catch:{ all -> 0x0043 }
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x0043 }
            if (r0 != 0) goto L_0x0037
        L_0x0025:
            com.google.android.gms.internal.ads.akb<java.lang.String> r0 = com.google.android.gms.internal.ads.akl.ar     // Catch:{ all -> 0x0043 }
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x0043 }
            java.lang.Object r0 = r2.a(r0)     // Catch:{ all -> 0x0043 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0043 }
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)     // Catch:{ all -> 0x0043 }
            r3.h = r0     // Catch:{ all -> 0x0043 }
        L_0x0037:
            java.util.regex.Pattern r0 = r3.h     // Catch:{ all -> 0x0043 }
            java.util.regex.Matcher r4 = r0.matcher(r4)     // Catch:{ all -> 0x0043 }
            boolean r4 = r4.matches()     // Catch:{ all -> 0x0043 }
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            return r4
        L_0x0043:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            throw r4     // Catch:{ PatternSyntaxException -> 0x0046 }
        L_0x0046:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.hd.e(java.lang.String):boolean");
    }
}
