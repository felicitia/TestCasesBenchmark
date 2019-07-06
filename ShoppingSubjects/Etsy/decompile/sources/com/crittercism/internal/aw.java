package com.crittercism.internal;

import android.support.annotation.NonNull;
import java.net.URL;
import java.util.List;

public final class aw implements bi {
    private av a;
    private String b = bh.a.a();

    public static class a extends ce {
        public a(av avVar) {
            super(avVar);
        }

        public final bz a(as asVar, List<? extends bi> list) {
            URL url = asVar.g;
            if (url == null) {
                cm.d("no base url for dhub config, will try reporting again later");
                return null;
            }
            StringBuilder sb = new StringBuilder("/config/v1/appId/");
            sb.append(this.a.e);
            URL url2 = new URL(url, sb.toString());
            list.size();
            if (list.size() == 0) {
                cm.d("received no config requests. empty payload request will be generated.");
            } else if (list.size() >= 2) {
                cm.d("expected only one config request event.");
            }
            this.b.put("CRProtocolVersion", "1.2.0");
            this.b.put("CRSentAt", cp.a.a());
            return bz.a(url2, this.b);
        }
    }

    public final /* bridge */ /* synthetic */ Object g() {
        return null;
    }

    public aw(@NonNull av avVar) {
        this.a = avVar;
    }

    public final String f() {
        return this.b;
    }
}
