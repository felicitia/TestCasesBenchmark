package com.crittercism.internal;

import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.requests.HttpUtil;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.entity.mime.MIME;
import org.json.JSONObject;

public class bz {
    public final URL a;
    public final String b;
    public final Map<String, String> c;
    private final byte[] d;

    public bz(String str, URL url, byte[] bArr, Map<String, String> map) {
        this.b = str;
        this.a = url;
        this.d = bArr;
        this.c = new HashMap(map);
    }

    public static Map<String, String> a(av avVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("CRAppId", avVar.e);
        hashMap.put("CRAppVersion", avVar.a.a);
        hashMap.put("CRVersion", "5.8.10");
        hashMap.put("CRPlatform", AppBuild.ANDROID_PLATFORM);
        hashMap.put("CRDevelopmentPlatform", avVar.h);
        hashMap.put("CRDeviceId", avVar.h());
        return hashMap;
    }

    public static bz a(URL url, Map<String, String> map) {
        return new bz(BaseHttpRequest.GET, url, null, map);
    }

    public static bz a(URL url, JSONObject jSONObject, Map<String, String> map) {
        map.put(MIME.CONTENT_TYPE, HttpUtil.JSON_CONTENT_TYPE);
        return new bz(BaseHttpRequest.POST, url, jSONObject.toString().getBytes("UTF8"), map);
    }

    public byte[] a() {
        return this.d;
    }
}
