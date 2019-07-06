package com.onfido.c.a.a;

import com.onfido.c.a.a.b.c;
import com.onfido.c.a.b.b;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class g extends b {

    public static class a extends com.onfido.c.a.a.b.a<g, a> {
        private String a;
        private String b;
        private Map<String, Object> c;

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public g a(String str, Date date, Map<String, Object> map, Map<String, Object> map2, String str2, String str3) {
            if (!b.a((CharSequence) this.a) || !b.a((CharSequence) this.b)) {
                Map<String, Object> map3 = this.c;
                if (b.a((Map) map3)) {
                    map3 = Collections.emptyMap();
                }
                String str4 = str;
                Date date2 = date;
                Map<String, Object> map4 = map;
                Map<String, Object> map5 = map2;
                String str5 = str2;
                String str6 = str3;
                g gVar = new g(str4, date2, map4, map5, str5, str6, this.a, this.b, map3);
                return gVar;
            }
            throw new NullPointerException("either name or category is required");
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: c */
        public a a() {
            return this;
        }

        public a c(String str) {
            this.a = str;
            return this;
        }

        public a c(Map<String, ?> map) {
            b.a(map, "properties");
            this.c = Collections.unmodifiableMap(new LinkedHashMap(map));
            return this;
        }

        @Deprecated
        public a d(String str) {
            this.b = str;
            return this;
        }
    }

    g(String str, Date date, Map<String, Object> map, Map<String, Object> map2, String str2, String str3, String str4, String str5, Map<String, Object> map3) {
        super(c.screen, str, date, map, map2, str2, str3);
        if (!b.a((CharSequence) str4)) {
            put("name", str4);
        }
        if (!b.a((CharSequence) str5)) {
            put("category", str5);
        }
        put("properties", map3);
    }

    @Deprecated
    public String a() {
        return b("category");
    }

    public String e() {
        return b("name");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ScreenPayload{name=\"");
        sb.append(e());
        sb.append(",category=\"");
        sb.append(a());
        sb.append("\"}");
        return sb.toString();
    }
}
