package com.google.android.gms.common.util;

import java.util.ArrayList;

public abstract class ObjectPoolBase<T> {
    private final ArrayList<T> zzaag;
    private final int zzaah;

    public ObjectPoolBase(int i) {
        this.zzaag = new ArrayList<>(i);
        this.zzaah = i;
    }

    public final T aquire() {
        synchronized (this.zzaag) {
            int size = this.zzaag.size();
            if (size > 0) {
                T remove = this.zzaag.remove(size - 1);
                return remove;
            }
            T newObject = newObject();
            return newObject;
        }
    }

    /* access modifiers changed from: protected */
    public boolean cleanUpObject(T t) {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract T newObject();

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0051, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean release(T r6) {
        /*
            r5 = this;
            java.util.ArrayList<T> r0 = r5.zzaag
            monitor-enter(r0)
            java.util.ArrayList<T> r1 = r5.zzaag     // Catch:{ all -> 0x0052 }
            int r1 = r1.size()     // Catch:{ all -> 0x0052 }
            r2 = 0
            r3 = r2
        L_0x000b:
            if (r3 >= r1) goto L_0x003e
            java.util.ArrayList<T> r4 = r5.zzaag     // Catch:{ all -> 0x0052 }
            java.lang.Object r4 = r4.get(r3)     // Catch:{ all -> 0x0052 }
            if (r4 != r6) goto L_0x003b
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0052 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0052 }
            r2 = 25
            java.lang.String r3 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0052 }
            int r3 = r3.length()     // Catch:{ all -> 0x0052 }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0052 }
            r3.<init>(r2)     // Catch:{ all -> 0x0052 }
            java.lang.String r2 = "Object released already: "
            r3.append(r2)     // Catch:{ all -> 0x0052 }
            r3.append(r6)     // Catch:{ all -> 0x0052 }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x0052 }
            r1.<init>(r6)     // Catch:{ all -> 0x0052 }
            throw r1     // Catch:{ all -> 0x0052 }
        L_0x003b:
            int r3 = r3 + 1
            goto L_0x000b
        L_0x003e:
            int r3 = r5.zzaah     // Catch:{ all -> 0x0052 }
            if (r1 >= r3) goto L_0x0050
            boolean r1 = r5.cleanUpObject(r6)     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0050
            java.util.ArrayList<T> r1 = r5.zzaag     // Catch:{ all -> 0x0052 }
            r1.add(r6)     // Catch:{ all -> 0x0052 }
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            r6 = 1
            return r6
        L_0x0050:
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            return r2
        L_0x0052:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.ObjectPoolBase.release(java.lang.Object):boolean");
    }
}
