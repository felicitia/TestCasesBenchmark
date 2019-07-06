package com.threatmetrix.TrustDefender.internal;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

class TB {

    /* renamed from: byte reason: not valid java name */
    private static final Pattern f529byte = Pattern.compile("^[a-zA-Z0-9]{8}$");

    /* renamed from: char reason: not valid java name */
    private static final Map<String, String> f530char;

    /* renamed from: do reason: not valid java name */
    static final Map<String, String> f531do;

    /* renamed from: for reason: not valid java name */
    static final String f532for = null;

    /* renamed from: if reason: not valid java name */
    static final String f533if = P.m250for();

    /* renamed from: new reason: not valid java name */
    static final String f534new = TL.m331if(TB.class);

    /* renamed from: case reason: not valid java name */
    final String f535case;

    /* renamed from: int reason: not valid java name */
    final String f536int;

    /* renamed from: try reason: not valid java name */
    final String f537try;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("Cache-Control", "no-cache, no-store, must-revalidate, no-transform");
        f531do = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.putAll(hashMap);
        hashMap2.put("Accept-Language", f533if);
        f530char = Collections.unmodifiableMap(hashMap2);
    }

    TB(String str, String str2, String str3) {
        this.f536int = str3;
        this.f537try = str2;
        this.f535case = str;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final String m324for(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://");
        sb.append(this.f535case);
        sb.append("/fp/");
        sb.append(str2);
        if (NK.m203byte(str)) {
            sb.append(";CIS3SID=");
            sb.append(str);
        }
        return sb.toString();
    }

    /* renamed from: do reason: not valid java name */
    static boolean m320do(String str) {
        if (str == null) {
            str = "h-sdk.online-metrix.net";
        }
        try {
            new URL("https://".concat(String.valueOf(str)));
            return false;
        } catch (MalformedURLException e) {
            TL.m337int(f534new, "Invalid hostname ".concat(String.valueOf(str)), e);
            return true;
        }
    }

    /* renamed from: for reason: not valid java name */
    static boolean m322for(String str) {
        if (str != null && !str.isEmpty() && f529byte.matcher(str).find()) {
            return true;
        }
        TL.m332if(f534new, "Invalid org_id");
        return false;
    }

    /* renamed from: int reason: not valid java name */
    static Map<String, String> m323int(K7 k7) {
        HashMap hashMap = new HashMap();
        hashMap.putAll(f530char);
        String str = null;
        boolean z = NK.m203byte(null);
        if (k7 != null) {
            hashMap.put("Referer", k7.f272byte);
            if (NK.m203byte(k7.f276else)) {
                str = k7.f276else;
            }
            StringBuilder sb = new StringBuilder("thx_guid=");
            sb.append(k7.f273case);
            hashMap.put("Cookie", sb.toString());
        }
        if (str != null) {
            hashMap.put("User-Agent", str);
        }
        return hashMap;
    }

    /* renamed from: for reason: not valid java name */
    static Map<String, String> m321for(K7 k7) {
        Map<String, String> map = m323int(k7);
        map.put("Content-Type", "application/x-www-form-urlencoded");
        return map;
    }
}
