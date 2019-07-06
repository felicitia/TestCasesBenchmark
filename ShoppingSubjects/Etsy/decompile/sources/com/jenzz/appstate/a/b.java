package com.jenzz.appstate.a;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.jenzz.appstate.a;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestrictTo({Scope.LIBRARY})
/* compiled from: CompositeAppStateListener */
class b implements a {
    @NonNull
    private final List<a> a = new CopyOnWriteArrayList();

    b() {
    }

    public void a() {
        for (a a2 : this.a) {
            a2.a();
        }
    }

    public void b() {
        for (a b : this.a) {
            b.b();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(@NonNull a aVar) {
        this.a.add(aVar);
    }
}
