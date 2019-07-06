package com.google.android.exoplayer2.drm;

import android.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class ClearKeyUtil {
    private static final Pattern REQUEST_KIDS_PATTERN = Pattern.compile("\"kids\":\\[\"(.*?)\"]");

    public static byte[] adjustRequestData(byte[] bArr) {
        if (Util.SDK_INT >= 27) {
            return bArr;
        }
        String fromUtf8Bytes = Util.fromUtf8Bytes(bArr);
        Matcher matcher = REQUEST_KIDS_PATTERN.matcher(fromUtf8Bytes);
        if (!matcher.find()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to adjust request data: ");
            sb.append(fromUtf8Bytes);
            Log.e("ClearKeyUtil", sb.toString());
            return bArr;
        }
        int start = matcher.start(1);
        int end = matcher.end(1);
        StringBuilder sb2 = new StringBuilder(fromUtf8Bytes);
        base64ToBase64Url(sb2, start, end);
        return Util.getUtf8Bytes(sb2.toString());
    }

    public static byte[] adjustResponseData(byte[] bArr) {
        if (Util.SDK_INT >= 27) {
            return bArr;
        }
        try {
            JSONObject jSONObject = new JSONObject(Util.fromUtf8Bytes(bArr));
            JSONArray jSONArray = jSONObject.getJSONArray("keys");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                jSONObject2.put("k", base64UrlToBase64(jSONObject2.getString("k")));
                jSONObject2.put("kid", base64UrlToBase64(jSONObject2.getString("kid")));
            }
            return Util.getUtf8Bytes(jSONObject.toString());
        } catch (JSONException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to adjust response data: ");
            sb.append(Util.fromUtf8Bytes(bArr));
            Log.e("ClearKeyUtil", sb.toString(), e);
            return bArr;
        }
    }

    private static void base64ToBase64Url(StringBuilder sb, int i, int i2) {
        while (i < i2) {
            char charAt = sb.charAt(i);
            if (charAt == '+') {
                sb.setCharAt(i, '-');
            } else if (charAt == '/') {
                sb.setCharAt(i, '_');
            }
            i++;
        }
    }

    private static String base64UrlToBase64(String str) {
        return str.replace('-', '+').replace('_', '/');
    }
}
