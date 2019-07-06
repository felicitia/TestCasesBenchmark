package com.google.firebase.appindexing;

import android.os.Bundle;
import com.google.android.gms.internal.icing.zzgb.zza;
import com.google.firebase.appindexing.builders.IndexableBuilder;
import com.google.firebase.appindexing.internal.Thing;

public interface Indexable {

    public static class Builder extends IndexableBuilder<Builder> {
        public Builder() {
            this("Thing");
        }

        public Builder(String str) {
            super(str);
        }
    }

    public interface Metadata {

        public static final class Builder {
            private int score = zza.zzdf().getScore();
            private final Bundle zzaw = new Bundle();
            private boolean zzcd = zza.zzdf().zzdd();
            private String zzce = zza.zzdf().zzde();

            public final Thing.zza zzi() {
                return new Thing.zza(this.zzcd, this.score, this.zzce, this.zzaw);
            }
        }
    }
}
