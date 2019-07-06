package androidx.work.impl.a.a;

import android.content.Context;
import android.support.annotation.NonNull;
import androidx.work.NetworkType;
import androidx.work.impl.a.b;
import androidx.work.impl.b.j;

/* compiled from: NetworkUnmeteredController */
public class g extends c<b> {
    public g(Context context) {
        super(androidx.work.impl.a.b.g.a(context).c());
    }

    /* access modifiers changed from: 0000 */
    public boolean a(@NonNull j jVar) {
        return jVar.j.a() == NetworkType.UNMETERED;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public boolean b(@NonNull b bVar) {
        return !bVar.a() || bVar.c();
    }
}
