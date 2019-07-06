package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.dh;
import java.io.IOException;

public class dh<M extends dh<M>> extends dm {
    protected dj d;

    /* access modifiers changed from: protected */
    public int a() {
        if (this.d != null) {
            for (int i = 0; i < this.d.a(); i++) {
                this.d.a(i).a();
            }
        }
        return 0;
    }

    public void a(dg dgVar) throws IOException {
        if (this.d != null) {
            for (int i = 0; i < this.d.a(); i++) {
                this.d.a(i).a(dgVar);
            }
        }
    }

    public final /* synthetic */ dm c() throws CloneNotSupportedException {
        return (dh) clone();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        dh dhVar = (dh) super.clone();
        dl.a(this, dhVar);
        return dhVar;
    }
}
