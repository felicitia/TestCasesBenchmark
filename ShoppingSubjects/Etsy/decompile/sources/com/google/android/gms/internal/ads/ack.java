package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;

public final class ack {
    private static final String[] e = {"/aclk", "/pcs/click"};
    private String a = "googleads.g.doubleclick.net";
    private String b = "/pagead/ads";
    private String c = "ad.doubleclick.net";
    private String[] d = {".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private acg f;

    public ack(acg acg) {
        this.f = acg;
    }

    private final Uri a(Uri uri, Context context, String str, boolean z, View view, Activity activity) throws zzcj {
        try {
            boolean c2 = c(uri);
            if (c2) {
                if (uri.toString().contains("dc_ms=")) {
                    throw new zzcj("Parameter already exists: dc_ms");
                }
            } else if (uri.getQueryParameter("ms") != null) {
                throw new zzcj("Query parameter already exists: ms");
            }
            String a2 = z ? this.f.a(context, str, view, activity) : this.f.a(context);
            if (c2) {
                String str2 = "dc_ms";
                String uri2 = uri.toString();
                int indexOf = uri2.indexOf(";adurl");
                if (indexOf != -1) {
                    int i = indexOf + 1;
                    StringBuilder sb = new StringBuilder(uri2.substring(0, i));
                    sb.append(str2);
                    sb.append("=");
                    sb.append(a2);
                    sb.append(";");
                    sb.append(uri2.substring(i));
                    return Uri.parse(sb.toString());
                }
                String encodedPath = uri.getEncodedPath();
                int indexOf2 = uri2.indexOf(encodedPath);
                StringBuilder sb2 = new StringBuilder(uri2.substring(0, encodedPath.length() + indexOf2));
                sb2.append(";");
                sb2.append(str2);
                sb2.append("=");
                sb2.append(a2);
                sb2.append(";");
                sb2.append(uri2.substring(indexOf2 + encodedPath.length()));
                return Uri.parse(sb2.toString());
            }
            String str3 = "ms";
            String uri3 = uri.toString();
            int indexOf3 = uri3.indexOf("&adurl");
            if (indexOf3 == -1) {
                indexOf3 = uri3.indexOf("?adurl");
            }
            if (indexOf3 == -1) {
                return uri.buildUpon().appendQueryParameter(str3, a2).build();
            }
            int i2 = indexOf3 + 1;
            StringBuilder sb3 = new StringBuilder(uri3.substring(0, i2));
            sb3.append(str3);
            sb3.append("=");
            sb3.append(a2);
            sb3.append("&");
            sb3.append(uri3.substring(i2));
            return Uri.parse(sb3.toString());
        } catch (UnsupportedOperationException unused) {
            throw new zzcj("Provided Uri is not in a valid state");
        }
    }

    private final boolean c(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            return uri.getHost().equals(this.c);
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public final Uri a(Uri uri, Context context) throws zzcj {
        return a(uri, context, null, false, null, null);
    }

    public final Uri a(Uri uri, Context context, View view, Activity activity) throws zzcj {
        try {
            return a(uri, context, uri.getQueryParameter("ai"), true, view, activity);
        } catch (UnsupportedOperationException unused) {
            throw new zzcj("Provided Uri is not in a valid state");
        }
    }

    public final acg a() {
        return this.f;
    }

    public final void a(MotionEvent motionEvent) {
        this.f.a(motionEvent);
    }

    public final boolean a(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            String host = uri.getHost();
            for (String endsWith : this.d) {
                if (host.endsWith(endsWith)) {
                    return true;
                }
            }
        } catch (NullPointerException unused) {
        }
        return false;
    }

    public final boolean b(Uri uri) {
        if (a(uri)) {
            for (String endsWith : e) {
                if (uri.getPath().endsWith(endsWith)) {
                    return true;
                }
            }
        }
        return false;
    }
}
