package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.android.volley.RequestQueue;
import java.io.File;

public class Volley {
    public static final String DEFAULT_CACHE_DIR = "volley";

    public static RequestQueue newRequestQueue(Context context, HttpStack httpStack, int i, String str) {
        String str2;
        if (TextUtils.isEmpty(str) || TextUtils.getTrimmedLength(str) == 0) {
            str = DEFAULT_CACHE_DIR;
        }
        File file = new File(context.getCacheDir(), str);
        try {
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            StringBuilder sb = new StringBuilder();
            sb.append(packageName);
            sb.append("/");
            sb.append(packageInfo.versionCode);
            str2 = sb.toString();
        } catch (NameNotFoundException unused) {
            str2 = "volley/0";
        }
        if (httpStack == null) {
            if (VERSION.SDK_INT >= 9) {
                httpStack = new HurlStack();
            } else {
                httpStack = new HttpClientStack(AndroidHttpClient.newInstance(str2));
            }
        }
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(file, i), new BasicNetwork(httpStack));
        requestQueue.start();
        return requestQueue;
    }

    public static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, DiskBasedCache.DEFAULT_DISK_USAGE_BYTES);
    }

    public static RequestQueue newRequestQueue(Context context, int i) {
        return newRequestQueue(context, null, i, DEFAULT_CACHE_DIR);
    }
}
