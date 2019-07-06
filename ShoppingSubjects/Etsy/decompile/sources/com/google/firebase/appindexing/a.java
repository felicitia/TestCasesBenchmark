package com.google.firebase.appindexing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appindexing.internal.zza;
import com.google.firebase.appindexing.internal.zzb;

public interface a {

    /* renamed from: com.google.firebase.appindexing.a$a reason: collision with other inner class name */
    public static class C0150a {
        private final Bundle a = new Bundle();
        private final String b;
        private String c;
        private String d;
        private String e;
        private zzb f;
        private String g;

        public C0150a(@NonNull String str) {
            this.b = str;
        }

        public C0150a a(@NonNull C0151a aVar) {
            Preconditions.checkNotNull(aVar);
            this.f = aVar.a();
            return this;
        }

        public C0150a a(@NonNull String str, @NonNull String str2) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(str2);
            this.c = str;
            this.d = str2;
            return this;
        }

        public a a() {
            Preconditions.checkNotNull(this.c, "setObject is required before calling build().");
            Preconditions.checkNotNull(this.d, "setObject is required before calling build().");
            zza zza = new zza(this.b, this.c, this.d, this.e, this.f == null ? new C0151a().a() : this.f, this.g, this.a);
            return zza;
        }
    }

    public interface b {

        /* renamed from: com.google.firebase.appindexing.a$b$a reason: collision with other inner class name */
        public static class C0151a {
            private boolean a = true;
            private boolean b = false;

            public C0151a a(boolean z) {
                this.a = z;
                return this;
            }

            public final zzb a() {
                zzb zzb = new zzb(this.a, null, null, null, false);
                return zzb;
            }
        }
    }
}
