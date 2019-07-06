package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.e;
import java.io.IOException;

public abstract class e<M extends e<M>> extends j {
    protected g a;

    /* access modifiers changed from: protected */
    public int a() {
        if (this.a == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.a.a(); i2++) {
            i += this.a.b(i2).a();
        }
        return i;
    }

    public void a(d dVar) throws IOException {
        if (this.a != null) {
            for (int i = 0; i < this.a.a(); i++) {
                this.a.b(i).a(dVar);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a(c cVar, int i) throws IOException {
        int i2 = cVar.i();
        if (!cVar.b(i)) {
            return false;
        }
        int i3 = i >>> 3;
        l lVar = new l(i, cVar.a(i2, cVar.i() - i2));
        h hVar = null;
        if (this.a == null) {
            this.a = new g();
        } else {
            hVar = this.a.a(i3);
        }
        if (hVar == null) {
            hVar = new h();
            this.a.a(i3, hVar);
        }
        hVar.a(lVar);
        return true;
    }

    public final /* synthetic */ j b() throws CloneNotSupportedException {
        return (e) clone();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        e eVar = (e) super.clone();
        i.a(this, eVar);
        return eVar;
    }
}
