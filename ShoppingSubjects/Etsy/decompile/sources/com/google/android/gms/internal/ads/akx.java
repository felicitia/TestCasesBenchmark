package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@bu
public final class akx {
    private final Map<String, akw> a = new HashMap();
    @Nullable
    private final aky b;

    public akx(@Nullable aky aky) {
        this.b = aky;
    }

    @Nullable
    public final aky a() {
        return this.b;
    }

    public final void a(String str, akw akw) {
        this.a.put(str, akw);
    }

    public final void a(String str, String str2, long j) {
        aky aky = this.b;
        akw akw = (akw) this.a.get(str2);
        String[] strArr = {str};
        if (!(aky == null || akw == null)) {
            aky.a(akw, j, strArr);
        }
        Map<String, akw> map = this.a;
        aky aky2 = this.b;
        map.put(str, aky2 == null ? null : aky2.a(j));
    }
}
