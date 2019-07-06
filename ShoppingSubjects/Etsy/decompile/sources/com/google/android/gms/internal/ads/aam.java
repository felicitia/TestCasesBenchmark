package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.aam;
import java.io.IOException;

public abstract class aam<M extends aam<M>> extends aar {
    protected aao Y;

    /* access modifiers changed from: protected */
    public int a() {
        if (this.Y == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.Y.a(); i2++) {
            i += this.Y.b(i2).a();
        }
        return i;
    }

    public void a(aal aal) throws IOException {
        if (this.Y != null) {
            for (int i = 0; i < this.Y.a(); i++) {
                this.Y.b(i).a(aal);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a(aaj aaj, int i) throws IOException {
        int j = aaj.j();
        if (!aaj.b(i)) {
            return false;
        }
        int i2 = i >>> 3;
        aat aat = new aat(i, aaj.a(j, aaj.j() - j));
        aap aap = null;
        if (this.Y == null) {
            this.Y = new aao();
        } else {
            aap = this.Y.a(i2);
        }
        if (aap == null) {
            aap = new aap();
            this.Y.a(i2, aap);
        }
        aap.a(aat);
        return true;
    }

    public final /* synthetic */ aar c() throws CloneNotSupportedException {
        return (aam) clone();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        aam aam = (aam) super.clone();
        aaq.a(this, aam);
        return aam;
    }
}
