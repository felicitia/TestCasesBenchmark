package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zx extends zv<zw, zw> {
    zx() {
    }

    private static void a(Object obj, zw zwVar) {
        ((xh) obj).zzdtt = zwVar;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object a() {
        return zw.b();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object a(Object obj) {
        zw zwVar = (zw) obj;
        zwVar.c();
        return zwVar;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(Object obj, int i, int i2) {
        ((zw) obj).a((i << 3) | 5, (Object) Integer.valueOf(i2));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(Object obj, int i, long j) {
        ((zw) obj).a(i << 3, (Object) Long.valueOf(j));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(Object obj, int i, zzbah zzbah) {
        ((zw) obj).a((i << 3) | 2, (Object) zzbah);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(Object obj, int i, Object obj2) {
        ((zw) obj).a((i << 3) | 3, (Object) (zw) obj2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(Object obj, aai aai) throws IOException {
        ((zw) obj).b(aai);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(Object obj, Object obj2) {
        a(obj, (zw) obj2);
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(zc zcVar) {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object b(Object obj) {
        return ((xh) obj).zzdtt;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(Object obj, int i, long j) {
        ((zw) obj).a((i << 3) | 1, (Object) Long.valueOf(j));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(Object obj, aai aai) throws IOException {
        ((zw) obj).a(aai);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(Object obj, Object obj2) {
        a(obj, (zw) obj2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object c(Object obj) {
        zw zwVar = ((xh) obj).zzdtt;
        if (zwVar != zw.a()) {
            return zwVar;
        }
        zw b = zw.b();
        a(obj, b);
        return b;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object c(Object obj, Object obj2) {
        zw zwVar = (zw) obj;
        zw zwVar2 = (zw) obj2;
        return zwVar2.equals(zw.a()) ? zwVar : zw.a(zwVar, zwVar2);
    }

    /* access modifiers changed from: 0000 */
    public final void d(Object obj) {
        ((xh) obj).zzdtt.c();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ int e(Object obj) {
        return ((zw) obj).d();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ int f(Object obj) {
        return ((zw) obj).e();
    }
}
