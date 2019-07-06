package com.bumptech.glide.load.b;

import android.content.Context;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: GenericLoaderFactory */
public class c {
    private static final l c = new l() {
        public String toString() {
            return "NULL_MODEL_LOADER";
        }

        public com.bumptech.glide.load.a.c a(Object obj, int i, int i2) {
            throw new NoSuchMethodError("This should never be called!");
        }
    };
    private final Map<Class, Map<Class, m>> a = new HashMap();
    private final Map<Class, Map<Class, l>> b = new HashMap();
    private final Context d;

    public c(Context context) {
        this.d = context.getApplicationContext();
    }

    public synchronized <T, Y> m<T, Y> a(Class<T> cls, Class<Y> cls2, m<T, Y> mVar) {
        m<T, Y> mVar2;
        this.b.clear();
        Map map = (Map) this.a.get(cls);
        if (map == null) {
            map = new HashMap();
            this.a.put(cls, map);
        }
        mVar2 = (m) map.put(cls2, mVar);
        if (mVar2 != null) {
            Iterator it = this.a.values().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((Map) it.next()).containsValue(mVar2)) {
                        mVar2 = null;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return mVar2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0028, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized <T, Y> com.bumptech.glide.load.b.l<T, Y> a(java.lang.Class<T> r3, java.lang.Class<Y> r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            com.bumptech.glide.load.b.l r0 = r2.c(r3, r4)     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0014
            com.bumptech.glide.load.b.l r3 = c     // Catch:{ all -> 0x0029 }
            boolean r3 = r3.equals(r0)     // Catch:{ all -> 0x0029 }
            if (r3 == 0) goto L_0x0012
            r3 = 0
            monitor-exit(r2)
            return r3
        L_0x0012:
            monitor-exit(r2)
            return r0
        L_0x0014:
            com.bumptech.glide.load.b.m r1 = r2.d(r3, r4)     // Catch:{ all -> 0x0029 }
            if (r1 == 0) goto L_0x0024
            android.content.Context r0 = r2.d     // Catch:{ all -> 0x0029 }
            com.bumptech.glide.load.b.l r0 = r1.a(r0, r2)     // Catch:{ all -> 0x0029 }
            r2.a(r3, r4, r0)     // Catch:{ all -> 0x0029 }
            goto L_0x0027
        L_0x0024:
            r2.b(r3, r4)     // Catch:{ all -> 0x0029 }
        L_0x0027:
            monitor-exit(r2)
            return r0
        L_0x0029:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.b.c.a(java.lang.Class, java.lang.Class):com.bumptech.glide.load.b.l");
    }

    private <T, Y> void b(Class<T> cls, Class<Y> cls2) {
        a(cls, cls2, c);
    }

    private <T, Y> void a(Class<T> cls, Class<Y> cls2, l<T, Y> lVar) {
        Map map = (Map) this.b.get(cls);
        if (map == null) {
            map = new HashMap();
            this.b.put(cls, map);
        }
        map.put(cls2, lVar);
    }

    private <T, Y> l<T, Y> c(Class<T> cls, Class<Y> cls2) {
        Map map = (Map) this.b.get(cls);
        if (map != null) {
            return (l) map.get(cls2);
        }
        return null;
    }

    private <T, Y> m<T, Y> d(Class<T> cls, Class<Y> cls2) {
        Map map = (Map) this.a.get(cls);
        m<T, Y> mVar = map != null ? (m) map.get(cls2) : null;
        if (mVar == null) {
            for (Class cls3 : this.a.keySet()) {
                if (cls3.isAssignableFrom(cls)) {
                    Map map2 = (Map) this.a.get(cls3);
                    if (map2 != null) {
                        mVar = (m) map2.get(cls2);
                        if (mVar != null) {
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return mVar;
    }
}
