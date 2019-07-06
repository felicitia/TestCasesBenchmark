package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.firebase.appindexing.d;
import com.google.firebase.appindexing.d.b;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;
import org.apache.commons.math3.geometry.VectorFormat;

@Class(creator = "ThingCreator")
public final class Thing extends AbstractSafeParcelable implements ReflectedParcelable, d {
    public static final Creator<Thing> CREATOR = new zzaa();
    @Field(getter = "getType", id = 4)
    private final String type;
    @Field(getter = "getPropertyBundle", id = 1)
    private final Bundle zzaw;
    @Field(getter = "getMetadata", id = 2)
    private final zza zzcf;
    @Field(getter = "getVersionCode", id = 1000)
    private final int zzdf;
    @Field(getter = "getUrl", id = 3)
    private final String zzdg;

    @Class(creator = "MetadataCreator")
    @Reserved({1000})
    public static class zza extends AbstractSafeParcelable implements b {
        public static final Creator<zza> CREATOR = new zzv();
        @Field(getter = "getScore", id = 2)
        private final int score;
        @Field(getter = "getPropertyBundle", id = 4)
        private final Bundle zzaw;
        @Field(getter = "getWorksOffline", id = 1)
        private final boolean zzcd;
        @Field(getter = "getAccountEmail", id = 3)
        private final String zzce;

        @Constructor
        public zza(@Param(id = 1) boolean z, @Param(id = 2) int i, @Param(id = 3) String str, @Param(id = 4) Bundle bundle) {
            this.zzcd = z;
            this.score = i;
            this.zzce = str;
            if (bundle == null) {
                bundle = new Bundle();
            }
            this.zzaw = bundle;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("worksOffline: ");
            sb.append(this.zzcd);
            sb.append(", score: ");
            sb.append(this.score);
            if (!this.zzce.isEmpty()) {
                sb.append(", accountEmail: ");
                sb.append(this.zzce);
            }
            if (this.zzaw != null && !this.zzaw.isEmpty()) {
                sb.append(", Properties { ");
                Thing.zza(this.zzaw, sb);
                sb.append(VectorFormat.DEFAULT_SUFFIX);
            }
            return sb.toString();
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeBoolean(parcel, 1, this.zzcd);
            SafeParcelWriter.writeInt(parcel, 2, this.score);
            SafeParcelWriter.writeString(parcel, 3, this.zzce, false);
            SafeParcelWriter.writeBundle(parcel, 4, this.zzaw, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }

        public final Bundle zzd() {
            return this.zzaw;
        }
    }

    @Constructor
    public Thing(@Param(id = 1000) int i, @Param(id = 1) Bundle bundle, @Param(id = 2) zza zza2, @Param(id = 3) String str, @Param(id = 4) String str2) {
        this.zzdf = i;
        this.zzaw = bundle;
        this.zzcf = zza2;
        this.zzdg = str;
        this.type = str2;
        this.zzaw.setClassLoader(getClass().getClassLoader());
    }

    public Thing(@NonNull Bundle bundle, @NonNull zza zza2, String str, @NonNull String str2) {
        this.zzdf = 10;
        this.zzaw = bundle;
        this.zzcf = zza2;
        this.zzdg = str;
        this.type = str2;
    }

    /* access modifiers changed from: private */
    public static void zza(@NonNull Bundle bundle, @NonNull StringBuilder sb) {
        String obj;
        try {
            Set keySet = bundle.keySet();
            String[] strArr = (String[]) keySet.toArray(new String[keySet.size()]);
            Arrays.sort(strArr, p.a);
            for (String str : strArr) {
                sb.append("{ key: '");
                sb.append(str);
                sb.append("' value: ");
                Object obj2 = bundle.get(str);
                if (obj2 == null) {
                    obj = "<null>";
                } else if (obj2.getClass().isArray()) {
                    sb.append("[ ");
                    for (int i = 0; i < Array.getLength(obj2); i++) {
                        sb.append("'");
                        sb.append(Array.get(obj2, i));
                        sb.append("' ");
                    }
                    obj = "]";
                } else {
                    obj = obj2.toString();
                }
                sb.append(obj);
                sb.append(" } ");
            }
        } catch (RuntimeException unused) {
            sb.append("<error>");
        }
    }

    static final /* synthetic */ int zzc(String str, String str2) {
        if (str == null) {
            return str2 == null ? 0 : -1;
        }
        if (str2 == null) {
            return 1;
        }
        return str.compareTo(str2);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.type.equals("Thing") ? "Indexable" : this.type);
        sb.append(" { { id: ");
        if (this.zzdg == null) {
            str = "<null>";
        } else {
            sb.append("'");
            sb.append(this.zzdg);
            str = "'";
        }
        sb.append(str);
        sb.append(" } Properties { ");
        zza(this.zzaw, sb);
        sb.append("} ");
        sb.append("Metadata { ");
        sb.append(this.zzcf.toString());
        sb.append(" } ");
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zzaw, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzcf, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzdg, false);
        SafeParcelWriter.writeString(parcel, 4, this.type, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzdf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zza zzk() {
        return this.zzcf;
    }
}
