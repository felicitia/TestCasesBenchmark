package com.threatmetrix.TrustDefender.internal;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class X extends HashMap<String, String> {

    /* renamed from: int reason: not valid java name */
    private static final String f605int = TL.m331if(X.class);

    /* renamed from: do reason: not valid java name */
    public final HashMap<String, Integer> f606do = new HashMap<>();

    /* renamed from: if reason: not valid java name */
    int f607if = 0;

    /* renamed from: for reason: not valid java name */
    public final X m374for(String str, String str2, boolean z) {
        if (z && NK.m203byte(str2)) {
            put(str, str2.toLowerCase(Locale.US));
        } else if (NK.m203byte(str2)) {
            put(str, str2);
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: new reason: not valid java name */
    public final X m376new(Map<String, String> map) {
        if (map instanceof X) {
            for (String str : map.keySet()) {
                Integer num = (Integer) ((X) map).f606do.get(str);
                if (num != null) {
                    String str2 = (String) map.get(str);
                    this.f606do.put(str, num);
                    m374for(str, str2, false);
                } else {
                    m374for(str, (String) map.get(str), false);
                }
            }
        } else {
            putAll(map);
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final String m375if() {
        StringBuilder sb = new StringBuilder();
        for (String str : keySet()) {
            String str2 = (String) get(str);
            if (str2 != null && !str2.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(str);
                Integer num = (Integer) this.f606do.get(str);
                if (num != null && str2.length() > num.intValue()) {
                    str2 = str2.substring(0, num.intValue());
                }
                if (num == null && this.f607if != 0 && str2.length() > this.f607if) {
                    str2 = str2.substring(0, this.f607if);
                }
                sb.append("=");
                sb.append(NK.m223new(str2));
            }
        }
        return sb.toString();
    }
}
