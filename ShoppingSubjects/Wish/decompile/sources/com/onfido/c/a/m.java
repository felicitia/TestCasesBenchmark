package com.onfido.c.a;

import android.content.Context;
import java.util.Collections;
import java.util.Map;

class m extends t {

    static class a extends a<m> {
        a(Context context, d dVar, String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("project-settings-plan-");
            sb.append(str);
            super(context, dVar, sb.toString(), str, m.class);
        }

        /* renamed from: a */
        public m b(Map<String, Object> map) {
            return new m(map);
        }
    }

    m(Map<String, Object> map) {
        super(Collections.unmodifiableMap(map));
    }

    static m a(Map<String, Object> map) {
        map.put("timestamp", Long.valueOf(System.currentTimeMillis()));
        return new m(map);
    }

    /* access modifiers changed from: 0000 */
    public long a() {
        return a("timestamp", 0);
    }

    /* access modifiers changed from: 0000 */
    public t b() {
        return a("plan");
    }

    /* access modifiers changed from: 0000 */
    public t c() {
        t b = b();
        if (b == null) {
            return null;
        }
        return b.a("track");
    }

    /* access modifiers changed from: 0000 */
    public t d() {
        return a("integrations");
    }
}
