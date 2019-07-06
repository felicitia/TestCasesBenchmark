package com.google.android.gms.gcm;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import java.util.Map;

public class a {
    private static a a;
    private final Context b;
    private final Map<String, Map<String, Boolean>> c = new ArrayMap();

    private a(Context context) {
        this.b = context;
    }

    public static a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a(context.getApplicationContext());
            }
            aVar = a;
        }
        return aVar;
    }

    /* access modifiers changed from: 0000 */
    public final synchronized boolean a(String str) {
        return this.c.containsKey(str);
    }

    /* access modifiers changed from: 0000 */
    public final synchronized boolean a(String str, String str2) {
        Map map = (Map) this.c.get(str2);
        if (map == null) {
            map = new ArrayMap();
            this.c.put(str2, map);
        }
        return map.put(str, Boolean.valueOf(false)) == null;
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void b(String str, String str2) {
        Map map = (Map) this.c.get(str2);
        if (map != null) {
            if ((map.remove(str) != null) && map.isEmpty()) {
                this.c.remove(str2);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final synchronized boolean c(String str, String str2) {
        Map map = (Map) this.c.get(str2);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }
}
