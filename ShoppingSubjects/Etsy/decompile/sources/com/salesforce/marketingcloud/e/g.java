package com.salesforce.marketingcloud.e;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.j;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo({Scope.LIBRARY})
public final class g {
    public static final DecimalFormat a = new DecimalFormat("#.########", new DecimalFormatSymbols(Locale.ENGLISH));
    /* access modifiers changed from: private */
    public static final String b = j.a(g.class);
    private static final char[] c = "0123456789ABCDEF".toCharArray();
    private static final SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
    private static final TimeZone e = TimeZone.getTimeZone("UTC");
    private static Boolean f;

    public static final class a {
        public Date a(JSONObject jSONObject, String str) {
            try {
                return g.a(jSONObject.getString(str));
            } catch (Exception e) {
                j.c(g.b, e, "Unable to parse date from json payload", new Object[0]);
                return null;
            }
        }

        public void a(JSONObject jSONObject, String str, Date date) {
            try {
                jSONObject.put(str, g.a(date));
            } catch (JSONException e) {
                j.c(g.b, e, "Unable to put date in json payload", new Object[0]);
            }
        }
    }

    public static class b {
        public Map<String, String> a(JSONObject jSONObject, String str) {
            Map<String, String> emptyMap = Collections.emptyMap();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(str);
                int length = jSONArray.length();
                if (length > 0) {
                    Map<String, String> hashMap = new HashMap<>(length);
                    for (int i = 0; i < length; i++) {
                        try {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            hashMap.put(jSONObject2.getString(ResponseConstants.KEY), jSONObject2.getString(ResponseConstants.VALUE));
                        } catch (JSONException e) {
                            try {
                                j.c(g.b, e, "Unable parse entry in list [%s]", str);
                            } catch (JSONException e2) {
                                e = e2;
                                emptyMap = hashMap;
                            }
                        }
                    }
                    return hashMap;
                }
            } catch (JSONException e3) {
                e = e3;
                j.c(g.b, e, "Unable to read %s from json", str);
                return emptyMap;
            }
            return emptyMap;
        }

        public void a(JSONObject jSONObject, String str, Map<String, String> map) {
            if (map != null) {
                JSONArray jSONArray = new JSONArray();
                try {
                    if (!map.isEmpty()) {
                        for (Entry entry : map.entrySet()) {
                            try {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put(ResponseConstants.KEY, entry.getKey());
                                jSONObject2.put(ResponseConstants.VALUE, entry.getValue());
                                jSONArray.put(jSONObject2);
                            } catch (JSONException e) {
                                j.c(g.b, e, "Unable to add entry from %s map", str);
                            }
                        }
                    }
                    jSONObject.put(str, jSONArray);
                } catch (JSONException e2) {
                    j.c(g.b, e2, "Unable to add %s to json structure", str);
                }
            }
        }
    }

    public static final class c extends b {
        public void a(JSONObject jSONObject, String str, Map<String, String> map) {
            if (map != null) {
                super.a(jSONObject, str, new TreeMap(map));
            }
        }
    }

    public static class d extends f {
        public void a(JSONObject jSONObject, String str, Set<String> set) {
            if (set != null) {
                super.a(jSONObject, str, new TreeSet(set));
            }
        }
    }

    public static final class e {
        public void a(JSONObject jSONObject, String str, List<String> list) {
            try {
                if (!list.isEmpty()) {
                    jSONObject.put(str, new JSONArray(list));
                }
            } catch (JSONException e) {
                j.c(g.b, e, "Failed to convert Tags into JSONArray for Registration payload.", new Object[0]);
            }
        }
    }

    public static class f {
        public Set<String> a(JSONObject jSONObject, String str) {
            TreeSet treeSet = new TreeSet();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    String string = jSONArray.getString(i);
                    if (!TextUtils.isEmpty(string)) {
                        treeSet.add(string);
                    }
                }
            } catch (JSONException e) {
                j.c(g.b, e, "Failed to get Tags from JSON.", new Object[0]);
            }
            return treeSet;
        }

        public void a(JSONObject jSONObject, String str, Set<String> set) {
            try {
                jSONObject.put(str, new JSONArray(set));
            } catch (JSONException e) {
                j.c(g.b, e, "Failed to convert Tags into JSONArray for Registration payload.", new Object[0]);
            }
        }
    }

    private g() {
    }

    private static String a(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str2);
            byte[] bytes = str.getBytes(str3);
            instance.update(bytes, 0, bytes.length);
            return a(instance.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e2) {
            com.google.a.a.a.a.a.a.a(e2);
            return null;
        }
    }

    public static String a(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(date);
    }

    public static String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        if (map.isEmpty()) {
            return "";
        }
        HashMap hashMap = new HashMap(map);
        StringBuilder sb = new StringBuilder();
        for (Entry entry : hashMap.entrySet()) {
            a(sb, (String) entry.getKey(), (String) entry.getValue());
        }
        return sb.toString();
    }

    public static synchronized String a(Set<String> set) {
        synchronized (g.class) {
            if (set == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            for (String append : set) {
                sb.append(append);
                sb.append("^|^");
            }
            String sb2 = sb.toString();
            return sb2;
        }
    }

    private static String a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b2 = bArr[i] & 255;
            int i2 = i * 2;
            cArr[i2] = c[b2 >>> 4];
            cArr[i2 + 1] = c[b2 & 15];
        }
        return new String(cArr);
    }

    public static Date a(String str) {
        if (str == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.parse(str);
    }

    private static void a(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append("^|^");
        sb.append(str2);
        sb.append("^|^");
    }

    public static boolean a() {
        return VERSION.SDK_INT > 25 || "O".equals(VERSION.CODENAME) || VERSION.CODENAME.startsWith("OMR");
    }

    public static boolean a(Context context) {
        boolean z = false;
        try {
            if (context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).targetSdkVersion >= 26) {
                z = true;
            }
            return z;
        } catch (NameNotFoundException | NullPointerException e2) {
            j.c(b, e2, "Unable to get application info to verify target SDK.", new Object[0]);
            return false;
        }
    }

    public static int b() {
        return TimeZone.getDefault().getOffset(new Date().getTime()) / 1000;
    }

    public static String b(String str) {
        String str2 = "";
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] bytes = str.getBytes();
            instance.update(bytes, 0, bytes.length);
            return new BigInteger(1, instance.digest()).toString(16);
        } catch (Throwable th) {
            j.c(b, th, "md5 failed", new Object[0]);
            return str2;
        }
    }

    public static boolean b(Context context) {
        if (f == null) {
            try {
                Class.forName("android.app.ActivityThread").getMethod("currentPackageName", new Class[0]);
                String name = context.getApplicationContext().getClass().getName();
                StringBuilder sb = new StringBuilder();
                sb.append(".");
                sb.append(context.getApplicationContext().getClass().getSimpleName());
                String replace = name.replace(sb.toString(), "");
                StringBuilder sb2 = new StringBuilder();
                sb2.append(replace);
                sb2.append(".BuildConfig");
                Field field = Class.forName(sb2.toString()).getField("DEBUG");
                field.setAccessible(true);
                f = Boolean.valueOf(field.getBoolean(null));
                j.b(b, "Debug determined through reflection: %s", f);
            } catch (Throwable th) {
                f = Boolean.valueOf(c(context));
                j.b(b, "Debug determination failed with Exception [%s] and so set to: %s", th.getMessage(), f);
            }
        }
        return f.booleanValue();
    }

    public static Map<String, String> c(String str) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("\\^\\|\\^");
            for (int i = 0; i < split.length; i += 2) {
                int i2 = i + 1;
                hashMap.put(split[i], i2 < split.length ? split[i2] : "");
            }
        }
        return hashMap;
    }

    private static boolean c(Context context) {
        try {
            if ((context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.flags & 2) != 0) {
                return true;
            }
        } catch (NameNotFoundException unused) {
            j.d(b, "Failed to determine if app was in debug mode.", new Object[0]);
        }
        return false;
    }

    public static Set<String> d(String str) {
        String[] split;
        TreeSet treeSet = new TreeSet();
        if (str != null && !TextUtils.isEmpty(str)) {
            for (String str2 : str.split("\\^\\|\\^")) {
                if (str2 != null && !str2.isEmpty()) {
                    treeSet.add(str2);
                }
            }
        }
        return treeSet;
    }

    static String e(String str) {
        return a(str, "SHA-256", "UTF-8");
    }

    @RestrictTo({Scope.LIBRARY})
    public static String f(String str) {
        return a(str, "MD5", "UTF-8");
    }
}
