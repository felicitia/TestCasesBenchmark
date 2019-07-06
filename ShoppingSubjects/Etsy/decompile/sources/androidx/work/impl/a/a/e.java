package androidx.work.impl.a.a;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import androidx.work.NetworkType;
import androidx.work.impl.a.b;
import androidx.work.impl.a.b.g;
import androidx.work.impl.b.j;

/* compiled from: NetworkMeteredController */
public class e extends c<b> {
    public e(Context context) {
        super(g.a(context).c());
    }

    /* access modifiers changed from: 0000 */
    public boolean a(@NonNull j jVar) {
        return jVar.j.a() == NetworkType.METERED;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public boolean b(@NonNull b bVar) {
        boolean z = true;
        if (VERSION.SDK_INT < 26) {
            androidx.work.e.b("NetworkMeteredCtrlr", "Metered network constraint is not supported before API 26, only checking for connected state.", new Throwable[0]);
            return !bVar.a();
        }
        if (bVar.a() && bVar.c()) {
            z = false;
        }
        return z;
    }
}
