package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.ArrayList;
import java.util.List;

@bu
final class amt {
    /* access modifiers changed from: private */
    public final List<ant> a = new ArrayList();

    amt() {
    }

    /* access modifiers changed from: 0000 */
    public final void a(anu anu) {
        Handler handler = hd.a;
        for (ant ans : this.a) {
            handler.post(new ans(this, ans, anu));
        }
        this.a.clear();
    }
}
