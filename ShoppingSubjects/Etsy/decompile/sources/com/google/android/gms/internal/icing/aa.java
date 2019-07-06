package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.ah.c;
import java.io.IOException;
import java.util.Map.Entry;

final class aa extends z<Object> {
    aa() {
    }

    /* access modifiers changed from: 0000 */
    public final int a(Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final ac<Object> a(Object obj) {
        return ((c) obj).zzhs;
    }

    /* access modifiers changed from: 0000 */
    public final void a(df dfVar, Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final void a(Object obj, ac<Object> acVar) {
        ((c) obj).zzhs = acVar;
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(bl blVar) {
        return blVar instanceof c;
    }

    /* access modifiers changed from: 0000 */
    public final ac<Object> b(Object obj) {
        ac<Object> a = a(obj);
        if (!a.d()) {
            return a;
        }
        ac<Object> acVar = (ac) a.clone();
        a(obj, acVar);
        return acVar;
    }

    /* access modifiers changed from: 0000 */
    public final void c(Object obj) {
        a(obj).c();
    }
}
