package com.paypal.android.sdk.onetouch.core.metadata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.util.Base64;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public final class ai {
    private static final boolean a = Boolean.valueOf(System.getProperty("dyson.debug.mode", Boolean.FALSE.toString())).booleanValue();
    private static final boolean b = Boolean.valueOf(System.getProperty("prd.debug.mode", Boolean.FALSE.toString())).booleanValue();
    private static final String f;
    private static final Uri g;

    static {
        Uri uri;
        StringBuilder sb = new StringBuilder();
        sb.append(h.class.getSimpleName());
        sb.append(".");
        sb.append(ai.class.getSimpleName());
        f = sb.toString();
        try {
            uri = Uri.parse("content://com.google.android.gsf.gservices");
        } catch (Exception unused) {
            uri = null;
        }
        g = uri;
    }

    private ai() {
    }

    public static Location a(LocationManager locationManager) {
        Location location = null;
        try {
            List providers = locationManager.getProviders(true);
            int size = providers.size() - 1;
            while (size >= 0) {
                Location lastKnownLocation = locationManager.getLastKnownLocation((String) providers.get(size));
                if (lastKnownLocation != null) {
                    return lastKnownLocation;
                }
                size--;
                location = lastKnownLocation;
            }
        } catch (RuntimeException unused) {
        }
        return location;
    }

    public static a a(Context context) {
        a aVar = new a();
        aVar.a(context.getPackageName());
        try {
            aVar.b(context.getPackageManager().getPackageInfo(aVar.a(), 0).versionName);
        } catch (NameNotFoundException unused) {
        }
        return aVar;
    }

    public static <T> T a(Object obj, Class<T> cls) {
        if (obj == null || !cls.isAssignableFrom(obj.getClass())) {
            return null;
        }
        return cls.cast(obj);
    }

    public static <T> T a(Map<String, Object> map, Class<T> cls, String str, T t) {
        if (map == null) {
            return t;
        }
        Object obj = map.get(str);
        if (obj == null) {
            return t;
        }
        if (cls.isAssignableFrom(obj.getClass())) {
            return cls.cast(obj);
        }
        StringBuilder sb = new StringBuilder("cannot parse data for ");
        sb.append(str);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder("cannot parse data for ");
        sb3.append(str);
        a(6, "PRD", sb2, (Throwable) new Exception(sb3.toString()));
        return t;
    }

    public static String a() {
        String str = "";
        try {
            d dVar = new d();
            StringBuilder sb = new StringBuilder();
            sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            sb.append("/Android/data/com.ebay.lid/");
            dVar.a(sb.toString());
            String str2 = "fb.bin";
            String b2 = dVar.b(str2);
            try {
                if ("".equals(b2.trim())) {
                    str = b(Boolean.TRUE.booleanValue());
                    dVar.a(str2, str.getBytes("UTF-8"));
                    return str;
                }
            } catch (Exception unused) {
            }
            str = b2;
        } catch (Exception unused2) {
        }
        return str;
    }

    public static String a(Context context, String str, String str2) {
        try {
            new StringBuilder("entering getMetadata loading name=").append(str);
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                new StringBuilder("leaving getMetadata successfully loading name=").append(str);
                return applicationInfo.metaData.getString(str);
            }
        } catch (NameNotFoundException unused) {
            new StringBuilder("load metadata in manifest failed, name=").append(str);
        }
        new StringBuilder("leaving getMetadata with default value,name=").append(str);
        return null;
    }

    public static String a(String str) {
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        instance.update(str.getBytes());
        byte[] digest = instance.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : digest) {
            stringBuffer.append(Integer.toString((b2 & 255) + 256, 16).substring(1));
        }
        return stringBuffer.toString().substring(0, 32);
    }

    public static String a(Map<String, Object> map, String str, String str2) {
        return (String) a(map, String.class, str, (T) null);
    }

    public static List<String> a(boolean z) {
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        if (!(inetAddress instanceof Inet6Address) || z) {
                            arrayList.add(hostAddress);
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    public static void a(int i, String str, String str2) {
        if (b) {
            Log.println(i, str, str2);
        }
    }

    public static void a(int i, String str, String str2, Throwable th) {
        if (b) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(10);
            sb.append(Log.getStackTraceString(th));
            Log.println(6, str, sb.toString());
        }
    }

    public static void a(String str, String str2) {
    }

    public static void a(String str, String str2, Throwable th) {
    }

    public static void a(String str, JSONObject jSONObject) {
        if (a && jSONObject != null) {
            jSONObject.toString();
        }
    }

    public static boolean a(Context context, String str) {
        try {
            if (context.getApplicationContext().checkCallingOrSelfPermission(str) == 0) {
                return true;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static boolean a(PackageManager packageManager, Intent intent) {
        List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        return queryIntentActivities != null && queryIntentActivities.size() > 0;
    }

    public static boolean a(Map<String, Object> map, String str, Boolean bool) {
        return ((Boolean) a(map, Boolean.class, str, (T) bool)).booleanValue();
    }

    public static String b() {
        List a2 = a(false);
        return a2.isEmpty() ? "" : (String) a2.get(0);
    }

    public static String b(Context context) {
        if (g != null && a(context, "com.google.android.providers.gsf.permission.READ_GSERVICES")) {
            Cursor query = context.getContentResolver().query(g, null, null, new String[]{"android_id"}, null);
            if (query == null) {
                return null;
            }
            try {
                if (query.moveToFirst()) {
                    if (query.getColumnCount() >= 2) {
                        String hexString = Long.toHexString(Long.parseLong(query.getString(1)));
                        query.close();
                        return hexString;
                    }
                }
                return null;
            } catch (NumberFormatException unused) {
            } finally {
                query.close();
            }
        }
        return null;
    }

    public static String b(Context context, String str) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
            } else {
                bufferedReader.close();
                return new String(Base64.decode(sb.toString(), 0), "UTF-8");
            }
        }
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if ((charAt >= '0' && charAt <= '9') || ((charAt >= 'A' && charAt <= 'F') || (charAt >= 'a' && charAt <= 'f'))) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str.charAt(i2));
                int parseInt = Integer.parseInt(sb2.toString(), 16);
                i += parseInt;
                arrayList.add(Integer.valueOf(parseInt));
            }
        }
        int i3 = i + 1;
        int size = arrayList.size() % 4;
        for (int i4 = 0; i4 < arrayList.size() - size; i4 += 4) {
            sb.append(Integer.toHexString((((Integer) arrayList.get((((Integer) arrayList.get(i4 + 3)).intValue() % 4) + i4)).intValue() + i3) % 16));
        }
        if (sb.toString().length() == 0) {
            return null;
        }
        return sb.toString().length() >= 4 ? sb.toString().substring(0, 4) : sb.toString();
    }

    public static String b(boolean z) {
        return z ? UUID.randomUUID().toString() : UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean b(String str, String str2) {
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        new StringBuilder("Cached version is ").append(str);
        new StringBuilder("default version is ").append(str2);
        int i = 0;
        while (i < split.length && i < split2.length && split[i].equals(split2[i])) {
            i++;
        }
        return Integer.valueOf(Integer.signum((i >= split.length || i >= split2.length) ? split.length - split2.length : Integer.valueOf(split[i]).compareTo(Integer.valueOf(split2[i])))).intValue() >= 0;
    }

    public static long c() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
        } catch (IllegalArgumentException e) {
            e.getLocalizedMessage();
            return 0;
        }
    }

    public static String c(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("RiskManagerAG", 0);
        String string = sharedPreferences.getString("RiskManagerAG", "");
        Editor edit = sharedPreferences.edit();
        if (str == null || str.equals(string)) {
            if (!string.equals("")) {
                return string;
            }
            str = b(Boolean.TRUE.booleanValue());
        }
        edit.putString("RiskManagerAG", str);
        edit.commit();
        return str;
    }

    public static void c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("RiskManagerCT", 0);
        int i = sharedPreferences.getInt("RiskManagerCT", 0);
        Editor edit = sharedPreferences.edit();
        int i2 = 1;
        if (i > 0 && i < Integer.MAX_VALUE) {
            i2 = 1 + i;
        }
        edit.putInt("RiskManagerCT", i2);
        edit.commit();
    }

    public static String d() {
        if (VERSION.SDK_INT >= 14) {
            String property = System.getProperty("http.proxyHost");
            if (property != null) {
                String property2 = System.getProperty("http.proxyPort");
                if (property2 != null) {
                    StringBuilder sb = new StringBuilder("host=");
                    sb.append(property);
                    sb.append(",port=");
                    sb.append(property2);
                    return sb.toString();
                }
            }
        }
        return null;
    }

    public static String d(Context context) {
        int i = context.getSharedPreferences("RiskManagerCT", 0).getInt("RiskManagerCT", 0);
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        return sb.toString();
    }

    public static String e() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                if (networkInterface.isUp() && networkInterface.getInterfaceAddresses().size() != 0) {
                    String name = networkInterface.getName();
                    if (name.startsWith("ppp") || name.startsWith("tun") || name.startsWith("tap")) {
                        return name;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }
}
