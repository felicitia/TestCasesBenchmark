package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.LinkedList;

@bu
final class anz {
    private final LinkedList<aoa> a = new LinkedList<>();
    /* access modifiers changed from: private */
    public zzjj b;
    /* access modifiers changed from: private */
    public final String c;
    private final int d;
    private boolean e;

    anz(zzjj zzjj, String str, int i) {
        Preconditions.checkNotNull(zzjj);
        Preconditions.checkNotNull(str);
        this.b = zzjj;
        this.c = str;
        this.d = i;
    }

    /* access modifiers changed from: 0000 */
    public final aoa a(@Nullable zzjj zzjj) {
        if (zzjj != null) {
            this.b = zzjj;
        }
        return (aoa) this.a.remove();
    }

    /* access modifiers changed from: 0000 */
    public final zzjj a() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public final void a(ams ams, zzjj zzjj) {
        this.a.add(new aoa(this, ams, zzjj));
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(ams ams) {
        aoa aoa = new aoa(this, ams);
        this.a.add(aoa);
        return aoa.a();
    }

    /* access modifiers changed from: 0000 */
    public final int b() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public final String c() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public final int d() {
        return this.a.size();
    }

    /* access modifiers changed from: 0000 */
    public final int e() {
        Iterator it = this.a.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((aoa) it.next()).e) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    public final int f() {
        Iterator it = this.a.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((aoa) it.next()).a()) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    public final void g() {
        this.e = true;
    }

    /* access modifiers changed from: 0000 */
    public final boolean h() {
        return this.e;
    }
}
