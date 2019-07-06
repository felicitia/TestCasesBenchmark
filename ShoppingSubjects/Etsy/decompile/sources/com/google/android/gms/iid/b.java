package com.google.android.gms.iid;

import android.os.Bundle;
import android.util.Log;

final class b extends ab<Bundle> {
    b(int i, int i2, Bundle bundle) {
        super(i, 1, bundle);
    }

    /* access modifiers changed from: 0000 */
    public final void a(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("data");
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(bundle2);
            StringBuilder sb = new StringBuilder(16 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
            sb.append("Finishing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.b.a(bundle2);
    }

    /* access modifiers changed from: 0000 */
    public final boolean a() {
        return false;
    }
}
