package com.google.firebase.appindexing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.firebase.appindexing.internal.Thing.zza;

public interface d {

    public static class a extends com.google.firebase.appindexing.a.a<a> {
        public a() {
            this("Thing");
        }

        public a(@NonNull String str) {
            super(str);
        }
    }

    public interface b {

        public static final class a {
            private boolean a = com.google.android.gms.internal.icing.Cdo.a.n().k();
            private int b = com.google.android.gms.internal.icing.Cdo.a.n().l();
            private String c = com.google.android.gms.internal.icing.Cdo.a.n().m();
            private final Bundle d = new Bundle();

            public final zza a() {
                return new zza(this.a, this.b, this.c, this.d);
            }
        }
    }
}
