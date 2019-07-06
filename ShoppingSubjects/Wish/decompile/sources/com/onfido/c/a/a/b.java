package com.onfido.c.a.a;

import com.onfido.c.a.t;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public abstract class b extends t {

    public static abstract class a<P extends b, B extends a> {
        private String a;
        private Date b;
        private Map<String, Object> c;
        private Map<String, Object> d;
        private String e;
        private String f;

        a() {
        }

        /* access modifiers changed from: 0000 */
        public abstract B a();

        public B a(String str) {
            this.f = com.onfido.c.a.b.b.a(str, "anonymousId");
            return a();
        }

        public B a(Map<String, ?> map) {
            com.onfido.c.a.b.b.a(map, "context");
            this.c = Collections.unmodifiableMap(new LinkedHashMap(map));
            return a();
        }

        /* access modifiers changed from: 0000 */
        public abstract P a(String str, Date date, Map<String, Object> map, Map<String, Object> map2, String str2, String str3);

        public B b(String str) {
            this.e = com.onfido.c.a.b.b.a(str, "userId");
            return a();
        }

        public B b(Map<String, ?> map) {
            if (com.onfido.c.a.b.b.a((Map) map)) {
                return a();
            }
            if (this.d == null) {
                this.d = new LinkedHashMap();
            }
            this.d.putAll(map);
            return a();
        }

        public P b() {
            if (!com.onfido.c.a.b.b.a((CharSequence) this.e) || !com.onfido.c.a.b.b.a((CharSequence) this.f)) {
                Map emptyMap = com.onfido.c.a.b.b.a((Map) this.d) ? Collections.emptyMap() : com.onfido.c.a.b.b.b(this.d);
                if (com.onfido.c.a.b.b.a((CharSequence) this.a)) {
                    this.a = UUID.randomUUID().toString();
                }
                if (this.b == null) {
                    this.b = new Date();
                }
                if (com.onfido.c.a.b.b.a((Map) this.c)) {
                    this.c = Collections.emptyMap();
                }
                return a(this.a, this.b, this.c, emptyMap, this.e, this.f);
            }
            throw new NullPointerException("either userId or anonymousId is required");
        }
    }

    /* renamed from: com.onfido.c.a.a.b$b reason: collision with other inner class name */
    public enum C0010b {
        browser,
        mobile,
        server
    }

    public enum c {
        alias,
        group,
        identify,
        screen,
        track
    }

    b(c cVar, String str, Date date, Map<String, Object> map, Map<String, Object> map2, String str2, String str3) {
        put("channel", C0010b.mobile);
        put("type", cVar);
        put("messageId", str);
        put("timestamp", com.onfido.c.a.b.b.b(date));
        put("context", map);
        put("integrations", map2);
        if (!com.onfido.c.a.b.b.a((CharSequence) str2)) {
            put("userId", str2);
        }
        put("anonymousId", str3);
    }

    /* renamed from: a */
    public b b(String str, Object obj) {
        super.b(str, obj);
        return this;
    }

    public c b() {
        return (c) a(c.class, "type");
    }

    public String c() {
        return b("userId");
    }

    public t d() {
        return a("integrations");
    }
}
