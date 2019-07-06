package androidx.work.impl.a.a;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import androidx.work.NetworkType;
import androidx.work.impl.a.b;
import androidx.work.impl.a.b.g;
import androidx.work.impl.b.j;

/* compiled from: NetworkConnectedController */
public class d extends c<b> {
    public d(Context context) {
        super(g.a(context).c());
    }

    /* access modifiers changed from: 0000 */
    public boolean a(@NonNull j jVar) {
        return jVar.j.a() == NetworkType.CONNECTED;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public boolean b(@NonNull b bVar) {
        boolean z = true;
        if (VERSION.SDK_INT < 26) {
            return !bVar.a();
        }
        if (bVar.a() && bVar.b()) {
            z = false;
        }
        return z;
    }
}
