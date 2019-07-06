package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class oi {
    private static final Pattern a = Pattern.compile("^\\uFEFF?\\s*(\\s*<!--([^-]|(?!-->))*-->)*\\s*<!DOCTYPE(\\s)+html(|(\\s)+[^>]*)>", 2);
    private static final Pattern b = Pattern.compile("^\\uFEFF?\\s*(\\s*<!--([^-]|(?!-->))*-->)*?\\s*<!DOCTYPE[^>]*>", 2);

    public static String a() {
        String str = (String) ajh.f().a(akl.L);
        String str2 = "12.4.51-000";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ResponseConstants.VERSION, str);
            jSONObject.put("sdk", "Google Mobile Ads");
            jSONObject.put("sdkVersion", str2);
            StringBuilder sb = new StringBuilder();
            sb.append("<script>");
            sb.append("Object.defineProperty(window,'MRAID_ENV',{get:function(){return ");
            sb.append(jSONObject.toString());
            sb.append("}});");
            sb.append("</script>");
            return sb.toString();
        } catch (JSONException e) {
            gv.c("Unable to build MRAID_ENV", e);
            return null;
        }
    }

    public static String a(@NonNull String str, @Nullable String... strArr) {
        if (strArr.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        Matcher matcher = a.matcher(str);
        int i = 0;
        if (matcher.find()) {
            int end = matcher.end();
            sb.append(str.substring(0, end));
            int length = strArr.length;
            while (i < length) {
                String str2 = strArr[i];
                if (str2 != null) {
                    sb.append(str2);
                }
                i++;
            }
            str = str.substring(end);
        } else if (!b.matcher(str).find()) {
            int length2 = strArr.length;
            while (i < length2) {
                String str3 = strArr[i];
                if (str3 != null) {
                    sb.append(str3);
                }
                i++;
            }
        }
        sb.append(str);
        return sb.toString();
    }
}
