package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.VisibleForTesting;

@bu
public final class fu {
    public static Uri a(Uri uri, Context context) {
        if (!ao.B().f(context) || !TextUtils.isEmpty(uri.getQueryParameter("fbs_aeid"))) {
            return uri;
        }
        String j = ao.B().j(context);
        Uri a = a(uri.toString(), "fbs_aeid", j);
        ao.B().d(context, j);
        return a;
    }

    @VisibleForTesting
    private static Uri a(String str, String str2, String str3) {
        int indexOf = str.indexOf("&adurl");
        if (indexOf == -1) {
            indexOf = str.indexOf("?adurl");
        }
        if (indexOf == -1) {
            return Uri.parse(str).buildUpon().appendQueryParameter(str2, str3).build();
        }
        int i = indexOf + 1;
        StringBuilder sb = new StringBuilder(str.substring(0, i));
        sb.append(str2);
        sb.append("=");
        sb.append(str3);
        sb.append("&");
        sb.append(str.substring(i));
        return Uri.parse(sb.toString());
    }

    public static String a(String str, Context context) {
        if (ao.B().a(context)) {
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            String j = ao.B().j(context);
            if (j == null) {
                return str;
            }
            if (((Boolean) ajh.f().a(akl.at)).booleanValue()) {
                String str2 = (String) ajh.f().a(akl.au);
                if (str.contains(str2)) {
                    if (ao.e().d(str)) {
                        ao.B().d(context, j);
                        return str.replace(str2, j);
                    } else if (ao.e().e(str)) {
                        ao.B().e(context, j);
                        return str.replace(str2, j);
                    }
                }
            } else if (!str.contains("fbs_aeid")) {
                if (ao.e().d(str)) {
                    ao.B().d(context, j);
                    return a(str, "fbs_aeid", j).toString();
                } else if (ao.e().e(str)) {
                    ao.B().e(context, j);
                    str = a(str, "fbs_aeid", j).toString();
                }
            }
        }
        return str;
    }

    public static String b(String str, Context context) {
        if (ao.B().a(context)) {
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            String j = ao.B().j(context);
            if (j == null || !ao.e().e(str)) {
                return str;
            }
            if (((Boolean) ajh.f().a(akl.at)).booleanValue()) {
                String str2 = (String) ajh.f().a(akl.au);
                if (str.contains(str2)) {
                    return str.replace(str2, j);
                }
            } else if (!str.contains("fbs_aeid")) {
                str = a(str, "fbs_aeid", j).toString();
            }
        }
        return str;
    }
}
