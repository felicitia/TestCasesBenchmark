package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;

@bu
final class pm {
    private static final String[] a = {"UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] b = {"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};

    pm() {
    }

    private static void a(String str, String str2, String str3) {
        String str4;
        if (((Boolean) ajh.f().a(akl.bs)).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putString(NotificationCompat.CATEGORY_ERROR, str);
            bundle.putString(ResponseConstants.CODE, str2);
            String str5 = "host";
            if (!TextUtils.isEmpty(str3)) {
                Uri parse = Uri.parse(str3);
                if (parse.getHost() != null) {
                    str4 = parse.getHost();
                    bundle.putString(str5, str4);
                }
            }
            str4 = "";
            bundle.putString(str5, str4);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i, String str) {
        String str2;
        if (i < 0) {
            int i2 = (-i) - 1;
            if (i2 < a.length) {
                str2 = a[i2];
                a("http_err", str2, str);
            }
        }
        str2 = String.valueOf(i);
        a("http_err", str2, str);
    }

    /* access modifiers changed from: 0000 */
    public final void a(SslError sslError) {
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            a("ssl_err", (primaryError < 0 || primaryError >= b.length) ? String.valueOf(primaryError) : b[primaryError], sslError.getUrl());
        }
    }
}
