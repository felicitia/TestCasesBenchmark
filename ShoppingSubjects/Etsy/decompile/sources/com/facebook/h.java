package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.internal.aa;
import com.facebook.internal.t;
import com.facebook.internal.z;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LegacyTokenHelper */
final class h {
    private static final String a = "h";
    private String b;
    private SharedPreferences c;

    public h(Context context) {
        this(context, null);
    }

    public h(Context context, String str) {
        aa.a((Object) context, ResponseConstants.CONTEXT);
        if (z.a(str)) {
            str = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
        }
        this.b = str;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.c = context.getSharedPreferences(this.b, 0);
    }

    public Bundle a() {
        Bundle bundle = new Bundle();
        for (String str : this.c.getAll().keySet()) {
            try {
                a(str, bundle);
            } catch (JSONException e) {
                LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                String str2 = a;
                StringBuilder sb = new StringBuilder();
                sb.append("Error reading cached value for key: '");
                sb.append(str);
                sb.append("' -- ");
                sb.append(e);
                t.a(loggingBehavior, 5, str2, sb.toString());
                return null;
            }
        }
        return bundle;
    }

    public void b() {
        this.c.edit().clear().apply();
    }

    public static boolean a(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        String string = bundle.getString("com.facebook.TokenCachingStrategy.Token");
        if (string == null || string.length() == 0 || bundle.getLong("com.facebook.TokenCachingStrategy.ExpirationDate", 0) == 0) {
            return false;
        }
        return true;
    }

    public static String b(Bundle bundle) {
        aa.a((Object) bundle, "bundle");
        return bundle.getString("com.facebook.TokenCachingStrategy.Token");
    }

    public static AccessTokenSource c(Bundle bundle) {
        aa.a((Object) bundle, "bundle");
        if (bundle.containsKey("com.facebook.TokenCachingStrategy.AccessTokenSource")) {
            return (AccessTokenSource) bundle.getSerializable("com.facebook.TokenCachingStrategy.AccessTokenSource");
        }
        return bundle.getBoolean("com.facebook.TokenCachingStrategy.IsSSO") ? AccessTokenSource.FACEBOOK_APPLICATION_WEB : AccessTokenSource.WEB_VIEW;
    }

    public static String d(Bundle bundle) {
        aa.a((Object) bundle, "bundle");
        return bundle.getString("com.facebook.TokenCachingStrategy.ApplicationId");
    }

    static Date a(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        long j = bundle.getLong(str, Long.MIN_VALUE);
        if (j == Long.MIN_VALUE) {
            return null;
        }
        return new Date(j);
    }

    private void a(String str, Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject(this.c.getString(str, "{}"));
        String string = jSONObject.getString("valueType");
        if (string.equals("bool")) {
            bundle.putBoolean(str, jSONObject.getBoolean(ResponseConstants.VALUE));
            return;
        }
        int i = 0;
        if (string.equals("bool[]")) {
            JSONArray jSONArray = jSONObject.getJSONArray(ResponseConstants.VALUE);
            boolean[] zArr = new boolean[jSONArray.length()];
            while (i < zArr.length) {
                zArr[i] = jSONArray.getBoolean(i);
                i++;
            }
            bundle.putBooleanArray(str, zArr);
        } else if (string.equals("byte")) {
            bundle.putByte(str, (byte) jSONObject.getInt(ResponseConstants.VALUE));
        } else if (string.equals("byte[]")) {
            JSONArray jSONArray2 = jSONObject.getJSONArray(ResponseConstants.VALUE);
            byte[] bArr = new byte[jSONArray2.length()];
            while (i < bArr.length) {
                bArr[i] = (byte) jSONArray2.getInt(i);
                i++;
            }
            bundle.putByteArray(str, bArr);
        } else if (string.equals("short")) {
            bundle.putShort(str, (short) jSONObject.getInt(ResponseConstants.VALUE));
        } else if (string.equals("short[]")) {
            JSONArray jSONArray3 = jSONObject.getJSONArray(ResponseConstants.VALUE);
            short[] sArr = new short[jSONArray3.length()];
            while (i < sArr.length) {
                sArr[i] = (short) jSONArray3.getInt(i);
                i++;
            }
            bundle.putShortArray(str, sArr);
        } else if (string.equals("int")) {
            bundle.putInt(str, jSONObject.getInt(ResponseConstants.VALUE));
        } else if (string.equals("int[]")) {
            JSONArray jSONArray4 = jSONObject.getJSONArray(ResponseConstants.VALUE);
            int[] iArr = new int[jSONArray4.length()];
            while (i < iArr.length) {
                iArr[i] = jSONArray4.getInt(i);
                i++;
            }
            bundle.putIntArray(str, iArr);
        } else if (string.equals("long")) {
            bundle.putLong(str, jSONObject.getLong(ResponseConstants.VALUE));
        } else if (string.equals("long[]")) {
            JSONArray jSONArray5 = jSONObject.getJSONArray(ResponseConstants.VALUE);
            long[] jArr = new long[jSONArray5.length()];
            while (i < jArr.length) {
                jArr[i] = jSONArray5.getLong(i);
                i++;
            }
            bundle.putLongArray(str, jArr);
        } else if (string.equals("float")) {
            bundle.putFloat(str, (float) jSONObject.getDouble(ResponseConstants.VALUE));
        } else if (string.equals("float[]")) {
            JSONArray jSONArray6 = jSONObject.getJSONArray(ResponseConstants.VALUE);
            float[] fArr = new float[jSONArray6.length()];
            while (i < fArr.length) {
                fArr[i] = (float) jSONArray6.getDouble(i);
                i++;
            }
            bundle.putFloatArray(str, fArr);
        } else if (string.equals("double")) {
            bundle.putDouble(str, jSONObject.getDouble(ResponseConstants.VALUE));
        } else if (string.equals("double[]")) {
            JSONArray jSONArray7 = jSONObject.getJSONArray(ResponseConstants.VALUE);
            double[] dArr = new double[jSONArray7.length()];
            while (i < dArr.length) {
                dArr[i] = jSONArray7.getDouble(i);
                i++;
            }
            bundle.putDoubleArray(str, dArr);
        } else if (string.equals("char")) {
            String string2 = jSONObject.getString(ResponseConstants.VALUE);
            if (string2 != null && string2.length() == 1) {
                bundle.putChar(str, string2.charAt(0));
            }
        } else if (string.equals("char[]")) {
            JSONArray jSONArray8 = jSONObject.getJSONArray(ResponseConstants.VALUE);
            char[] cArr = new char[jSONArray8.length()];
            for (int i2 = 0; i2 < cArr.length; i2++) {
                String string3 = jSONArray8.getString(i2);
                if (string3 != null && string3.length() == 1) {
                    cArr[i2] = string3.charAt(0);
                }
            }
            bundle.putCharArray(str, cArr);
        } else if (string.equals("string")) {
            bundle.putString(str, jSONObject.getString(ResponseConstants.VALUE));
        } else if (string.equals("stringList")) {
            JSONArray jSONArray9 = jSONObject.getJSONArray(ResponseConstants.VALUE);
            int length = jSONArray9.length();
            ArrayList arrayList = new ArrayList(length);
            while (i < length) {
                Object obj = jSONArray9.get(i);
                arrayList.add(i, obj == JSONObject.NULL ? null : (String) obj);
                i++;
            }
            bundle.putStringArrayList(str, arrayList);
        } else if (string.equals("enum")) {
            try {
                bundle.putSerializable(str, Enum.valueOf(Class.forName(jSONObject.getString("enumType")), jSONObject.getString(ResponseConstants.VALUE)));
            } catch (ClassNotFoundException | IllegalArgumentException unused) {
            }
        }
    }
}
