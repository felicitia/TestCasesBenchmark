package com.crittercism.internal;

import android.support.annotation.NonNull;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public final class ax implements bi {
    private av a;
    private String b = bh.a.a();

    public static class a extends ce {
        public a(av avVar) {
            super(avVar);
        }

        public final bz a(as asVar, List<? extends bi> list) {
            list.size();
            if (list.size() == 0) {
                cm.d("received no region lookup requests. empty payload request will be generated.");
            } else if (list.size() >= 2) {
                cm.d("expected only one region lookup request event.");
            }
            HashMap hashMap = new HashMap();
            hashMap.put("CRVersion", "5.8.10");
            hashMap.put("CRProtocolVersion", "1.2.0");
            StringBuilder sb = new StringBuilder("/v1/appids/");
            sb.append(this.a.e);
            return bz.a(new URL(asVar.f, sb.toString()), hashMap);
        }
    }

    public final Object g() {
        return null;
    }

    public ax(@NonNull av avVar) {
        this.a = avVar;
    }

    public final String f() {
        return this.b;
    }
}
