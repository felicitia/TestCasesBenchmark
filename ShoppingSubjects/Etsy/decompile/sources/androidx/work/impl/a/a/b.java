package androidx.work.impl.a.a;

import android.content.Context;
import android.support.annotation.NonNull;
import androidx.work.impl.a.b.g;
import androidx.work.impl.b.j;

/* compiled from: BatteryNotLowController */
public class b extends c<Boolean> {
    public b(Context context) {
        super(g.a(context).b());
    }

    /* access modifiers changed from: 0000 */
    public boolean a(@NonNull j jVar) {
        return jVar.j.d();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public boolean b(@NonNull Boolean bool) {
        return !bool.booleanValue();
    }
}
