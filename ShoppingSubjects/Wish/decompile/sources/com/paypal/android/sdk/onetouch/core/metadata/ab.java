package com.paypal.android.sdk.onetouch.core.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ab {
    private List<aa> c;
    private List<aa> d;

    static {
        ab.class.getSimpleName();
    }

    private ab() {
        this.c = Collections.synchronizedList(new ArrayList());
        this.d = Collections.synchronizedList(new ArrayList());
    }

    /* synthetic */ ab(byte b) {
        this();
    }

    public static ab a() {
        return ad.a;
    }

    private void b() {
        if (!this.d.isEmpty()) {
            synchronized (this) {
                if (!this.d.isEmpty()) {
                    aa aaVar = (aa) this.d.get(0);
                    this.d.remove(0);
                    this.c.add(aaVar);
                    new Thread(aaVar).start();
                }
            }
        }
    }

    public final void a(aa aaVar) {
        this.d.add(aaVar);
        if (this.c.size() < 3) {
            b();
        }
    }

    public final void b(aa aaVar) {
        this.c.remove(aaVar);
        b();
    }
}
