package com.contextlogic.wish.util;

import android.content.SharedPreferences;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.application.WishApplication;
import java.util.ArrayList;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PreferenceUtil {
    private static HashSet<String> SECURE_PREFERENCES;

    private static SharedPreferences getPreferences() {
        return WishApplication.getInstance().getSharedPreferences("WishPref", 0);
    }

    private static synchronized boolean isSecure(String str) {
        boolean contains;
        synchronized (PreferenceUtil.class) {
            if (SECURE_PREFERENCES == null) {
                SECURE_PREFERENCES = new HashSet<>();
                SECURE_PREFERENCES.add("user_login_email");
                SECURE_PREFERENCES.add("user_login_password");
            }
            contains = SECURE_PREFERENCES.contains(str);
        }
        return contains;
    }

    private static String getInsecureString(String str) {
        return getPreferences().getString(str, null);
    }

    private static void setInsecureString(String str, String str2) {
        if (str2 == null) {
            getPreferences().edit().remove(str).apply();
        } else {
            getPreferences().edit().putString(str, str2).apply();
        }
    }

    private static String getSecureString(String str) {
        String string = getPreferences().getString(SecureUtil.encrypt(str), null);
        if (string != null) {
            return SecureUtil.decrypt(string);
        }
        return null;
    }

    private static void setSecureString(String str, String str2) {
        if (str2 == null) {
            getPreferences().edit().remove(SecureUtil.encrypt(str)).apply();
            return;
        }
        getPreferences().edit().putString(SecureUtil.encrypt(str), SecureUtil.encrypt(str2)).apply();
    }

    public static String getString(String str) {
        return getString(str, null);
    }

    public static void setString(String str, String str2) {
        if (isSecure(str)) {
            setSecureString(str, str2);
        } else {
            setInsecureString(str, str2);
        }
    }

    public static String getString(String str, String str2) {
        String str3;
        if (isSecure(str)) {
            str3 = getSecureString(str);
        } else {
            str3 = getInsecureString(str);
        }
        return str3 != null ? str3 : str2;
    }

    public static void setLong(String str, long j) {
        getPreferences().edit().putLong(str, j).apply();
    }

    public static long getLong(String str, long j) {
        return getPreferences().getLong(str, j);
    }

    public static void setBoolean(String str, boolean z) {
        getPreferences().edit().putBoolean(str, z).apply();
    }

    public static boolean getBoolean(String str, boolean z) {
        return getPreferences().getBoolean(str, z);
    }

    public static boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public static JSONObject getSecureJSONObject(String str) {
        String secureString = getSecureString(str);
        if (secureString == null) {
            return null;
        }
        try {
            return new JSONObject(secureString);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void setSecureJSONObject(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            setSecureString(str, null);
        } else {
            setSecureString(str, jSONObject.toString());
        }
    }

    public static JSONObject getInsecureJSONObject(String str) {
        String string = getString(str);
        if (string == null) {
            return null;
        }
        try {
            return new JSONObject(string);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void setInsecureJSONObject(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            setString(str, null);
        } else {
            setString(str, jSONObject.toString());
        }
    }

    public static <T> void setArray(String str, ArrayList<T> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            setString(str, null);
            return;
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < arrayList.size(); i++) {
            jSONArray.put(arrayList.get(i));
        }
        setString(str, jSONArray.toString());
    }

    public static <T> ArrayList<T> getArray(String str) {
        String string = getString(str, null);
        ArrayList<T> arrayList = new ArrayList<>();
        if (string != null) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.opt(i));
                }
            } catch (JSONException unused) {
            }
        }
        return arrayList;
    }

    public static void setIntegerArray(String str, ArrayList<Integer> arrayList) {
        setArray(str, arrayList);
    }

    public static ArrayList<Integer> getIntegerArray(String str) {
        return getArray(str);
    }

    public static void setStringArray(String str, ArrayList<String> arrayList) {
        setArray(str, arrayList);
    }

    public static ArrayList<String> getStringArray(String str) {
        return getArray(str);
    }

    public static PaymentProcessor getCreditCardProcessorPreference() {
        String string = getString("DevSettingsCreditCardProcessor");
        if (string == null) {
            return null;
        }
        PaymentProcessor creditCardProcessor = PaymentProcessor.getCreditCardProcessor(Integer.parseInt(string), PaymentProcessor.Unknown);
        if (creditCardProcessor == PaymentProcessor.Unknown) {
            return null;
        }
        return creditCardProcessor;
    }
}
