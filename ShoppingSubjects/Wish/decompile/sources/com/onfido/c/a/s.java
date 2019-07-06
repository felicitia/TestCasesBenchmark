package com.onfido.c.a;

import android.content.Context;
import com.onfido.c.a.b.b.d;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class s extends t {

    static class a extends a<s> {
        a(Context context, d dVar, String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("traits-");
            sb.append(str);
            super(context, dVar, sb.toString(), str, s.class);
        }

        /* renamed from: a */
        public s b(Map<String, Object> map) {
            return new s(new d(map));
        }
    }

    public s() {
    }

    s(Map<String, Object> map) {
        super(map);
    }

    static s a() {
        s sVar = new s(new d());
        sVar.a(UUID.randomUUID().toString());
        return sVar;
    }

    /* access modifiers changed from: 0000 */
    public s a(String str) {
        return b("anonymousId", str);
    }

    /* renamed from: a */
    public s b(String str, Object obj) {
        super.b(str, obj);
        return this;
    }

    public s b() {
        return new s(Collections.unmodifiableMap(new LinkedHashMap(this)));
    }

    public String c() {
        return b("userId");
    }

    public String d() {
        return b("anonymousId");
    }
}
