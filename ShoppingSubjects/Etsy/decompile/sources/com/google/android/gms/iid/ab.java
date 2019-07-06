package com.google.android.gms.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.g;

abstract class ab<T> {
    final int a;
    final g<T> b = new g<>();
    final int c;
    final Bundle d;

    ab(int i, int i2, Bundle bundle) {
        this.a = i;
        this.c = i2;
        this.d = bundle;
    }

    /* access modifiers changed from: 0000 */
    public abstract void a(Bundle bundle);

    /* access modifiers changed from: 0000 */
    public final void a(zzaa zzaa) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(zzaa);
            StringBuilder sb = new StringBuilder(14 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
            sb.append("Failing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.b.a((Exception) zzaa);
    }

    /* access modifiers changed from: 0000 */
    public abstract boolean a();

    public String toString() {
        int i = this.c;
        int i2 = this.a;
        a();
        StringBuilder sb = new StringBuilder(55);
        sb.append("Request { what=");
        sb.append(i);
        sb.append(" id=");
        sb.append(i2);
        sb.append(" oneWay=false}");
        return sb.toString();
    }
}
