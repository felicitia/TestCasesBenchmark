package androidx.work.impl.utils;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.impl.Extras.a;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: StartWorkRunnable */
public class f implements Runnable {
    private androidx.work.impl.f a;
    private String b;
    private a c;

    public f(androidx.work.impl.f fVar, String str, a aVar) {
        this.a = fVar;
        this.b = str;
        this.c = aVar;
    }

    public void run() {
        this.a.g().a(this.b, this.c);
    }
}
