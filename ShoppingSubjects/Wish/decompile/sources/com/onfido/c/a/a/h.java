package com.onfido.c.a.a;

import com.onfido.c.a.a.b.c;
import com.onfido.c.a.b.b;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class h extends b {

    public static class a extends com.onfido.c.a.a.b.a<h, a> {
        private String a;
        private Map<String, Object> b;

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public h a(String str, Date date, Map<String, Object> map, Map<String, Object> map2, String str2, String str3) {
            b.a(this.a, "event");
            Map<String, Object> map3 = this.b;
            if (b.a((Map) map3)) {
                map3 = Collections.emptyMap();
            }
            String str4 = str;
            Date date2 = date;
            Map<String, Object> map4 = map;
            Map<String, Object> map5 = map2;
            String str5 = str2;
            String str6 = str3;
            h hVar = new h(str4, date2, map4, map5, str5, str6, this.a, map3);
            return hVar;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: c */
        public a a() {
            return this;
        }

        public a c(String str) {
            this.a = b.a(str, "event");
            return this;
        }

        public a c(Map<String, ?> map) {
            b.a(map, "properties");
            this.b = Collections.unmodifiableMap(new LinkedHashMap(map));
            return this;
        }
    }

    h(String str, Date date, Map<String, Object> map, Map<String, Object> map2, String str2, String str3, String str4, Map<String, Object> map3) {
        super(c.track, str, date, map, map2, str2, str3);
        put("event", str4);
        put("properties", map3);
    }

    public String a() {
        return b("event");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TrackPayload{event=\"");
        sb.append(a());
        sb.append("\"}");
        return sb.toString();
    }
}
