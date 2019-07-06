package com.google.firebase.appindexing;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appindexing.internal.zza;
import com.google.firebase.appindexing.internal.zzb;

public interface Action {

    public static class Builder {
        private final Bundle zzaw = new Bundle();
        private final String zzbu;
        private String zzbv;
        private String zzbw;
        private String zzbx;
        private zzb zzby;
        private String zzbz;

        public Builder(String str) {
            this.zzbu = str;
        }

        public Action build() {
            Preconditions.checkNotNull(this.zzbv, "setObject is required before calling build().");
            Preconditions.checkNotNull(this.zzbw, "setObject is required before calling build().");
            zza zza = new zza(this.zzbu, this.zzbv, this.zzbw, this.zzbx, this.zzby == null ? new Builder().zzh() : this.zzby, this.zzbz, this.zzaw);
            return zza;
        }

        public Builder setObject(String str, String str2) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(str2);
            this.zzbv = str;
            this.zzbw = str2;
            return this;
        }
    }

    public interface Metadata {

        public static class Builder {
            private boolean zzca = true;
            private boolean zzcb = false;

            public final zzb zzh() {
                zzb zzb = new zzb(this.zzca, null, null, null, false);
                return zzb;
            }
        }
    }
}
