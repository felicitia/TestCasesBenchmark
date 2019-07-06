package com.onfido.c.a.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Process;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class b {

    public static class a extends ThreadPoolExecutor {
        public a() {
            super(1, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new c());
        }
    }

    /* renamed from: com.onfido.c.a.b.b$b reason: collision with other inner class name */
    private static class C0011b extends Thread {
        private static final AtomicInteger a = new AtomicInteger(1);

        public C0011b(Runnable runnable) {
            StringBuilder sb = new StringBuilder();
            sb.append("Segment-");
            sb.append(a.getAndIncrement());
            super(runnable, sb.toString());
        }

        public void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    public static class c implements ThreadFactory {
        public Thread newThread(Runnable runnable) {
            return new C0011b(runnable);
        }
    }

    public static class d<K, V> extends ConcurrentHashMap<K, V> {
        public d() {
        }

        public d(Map<? extends K, ? extends V> map) {
            super(map);
        }

        public V put(K k, V v) {
            if (k == null || v == null) {
                return null;
            }
            return super.put(k, v);
        }

        public void putAll(Map<? extends K, ? extends V> map) {
            for (Entry entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    public static BufferedReader a(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public static InputStream a(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException unused) {
            return httpURLConnection.getErrorStream();
        }
    }

    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" == null");
        throw new NullPointerException(sb.toString());
    }

    public static String a(Context context) {
        String string = Secure.getString(context.getContentResolver(), "android_id");
        if (!a((CharSequence) string) && !"9774d56d682e549c".equals(string) && !"unknown".equals(string) && !"000000000000000".equals(string)) {
            return string;
        }
        if (!a((CharSequence) Build.SERIAL)) {
            return Build.SERIAL;
        }
        if (a(context, "android.permission.READ_PHONE_STATE") && b(context, "android.hardware.telephony")) {
            String deviceId = ((TelephonyManager) c(context, "phone")).getDeviceId();
            if (!a((CharSequence) deviceId)) {
                return deviceId;
            }
        }
        return UUID.randomUUID().toString();
    }

    public static String a(BufferedReader bufferedReader) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return sb.toString();
            }
            sb.append(readLine);
        }
    }

    public static String a(String str, String str2) {
        if (!a((CharSequence) str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(" cannot be null or empty");
        throw new NullPointerException(sb.toString());
    }

    public static String a(Date date) {
        return b(date);
    }

    public static <T> List<T> a(List<T> list) {
        return a((Collection) list) ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(list));
    }

    public static <T> Map<String, T> a() {
        return new d();
    }

    public static void a(SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2) {
        Editor edit = sharedPreferences2.edit();
        for (Entry entry : sharedPreferences.getAll().entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                edit.putString(str, (String) value);
            } else if (value instanceof Set) {
                edit.putStringSet(str, (Set) value);
            } else if (value instanceof Integer) {
                edit.putInt(str, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                edit.putLong(str, ((Long) value).longValue());
            } else if (value instanceof Float) {
                edit.putFloat(str, ((Float) value).floatValue());
            } else if (value instanceof Boolean) {
                edit.putBoolean(str, ((Boolean) value).booleanValue());
            }
        }
        edit.apply();
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(File file) {
        if (!file.exists() && !file.mkdirs() && !file.isDirectory()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not create directory at ");
            sb.append(file);
            throw new IOException(sb.toString());
        }
    }

    public static boolean a(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static boolean a(CharSequence charSequence) {
        return b(charSequence) || c(charSequence) == 0;
    }

    public static boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean a(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean a(Map map) {
        return map == null || map.size() == 0;
    }

    public static String b(InputStream inputStream) {
        return a(a(inputStream));
    }

    public static String b(Date date) {
        return a.a(date);
    }

    public static <K, V> Map<K, V> b(Map<K, V> map) {
        return Collections.unmodifiableMap(new LinkedHashMap(map));
    }

    public static boolean b(Context context) {
        if (!a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) c(context, "connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static boolean b(Context context, String str) {
        return context.getPackageManager().hasSystemFeature(str);
    }

    private static boolean b(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    private static int c(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) <= ' ') {
            i++;
        }
        while (length > i && charSequence.charAt(length - 1) <= ' ') {
            length--;
        }
        return length - i;
    }

    public static <T> T c(Context context, String str) {
        return context.getSystemService(str);
    }

    public static SharedPreferences d(Context context, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("analytics-android-");
        sb.append(str);
        return context.getSharedPreferences(sb.toString(), 0);
    }
}
