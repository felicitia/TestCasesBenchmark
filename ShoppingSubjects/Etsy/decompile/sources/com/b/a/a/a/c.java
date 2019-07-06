package com.b.a.a.a;

import com.crashlytics.android.answers.CustomEvent;
import java.util.HashMap;
import java.util.Map;

/* compiled from: KitEvent */
public class c {
    private final String a;
    private final Map<String, Object> b = new HashMap();

    public c(String str) {
        this.a = str;
    }

    public c a(String str, String str2) {
        this.b.put(str, str2);
        return this;
    }

    /* access modifiers changed from: 0000 */
    public CustomEvent a() {
        CustomEvent customEvent = new CustomEvent(this.a);
        for (String str : this.b.keySet()) {
            Object obj = this.b.get(str);
            if (obj instanceof String) {
                customEvent.putCustomAttribute(str, (String) obj);
            } else if (obj instanceof Number) {
                customEvent.putCustomAttribute(str, (Number) obj);
            }
        }
        return customEvent;
    }
}
