package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.appindexing.Indexable;
import com.google.firebase.appindexing.Indexable.Metadata;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;

public final class Thing extends AbstractSafeParcelable implements ReflectedParcelable, Indexable {
    public static final Creator<Thing> CREATOR = new zzaa();
    private final String type;
    private final Bundle zzaw;
    private final zza zzcf;
    private final int zzdf;
    private final String zzdg;

    public static class zza extends AbstractSafeParcelable implements Metadata {
        public static final Creator<zza> CREATOR = new zzv();
        private final int score;
        private final Bundle zzaw;
        private final boolean zzcd;
        private final String zzce;

        public zza(boolean z, int i, String str, Bundle bundle) {
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
                sb.append("}");
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

    public Thing(int i, Bundle bundle, zza zza2, String str, String str2) {
        this.zzdf = i;
        this.zzaw = bundle;
        this.zzcf = zza2;
        this.zzdg = str;
        this.type = str2;
        this.zzaw.setClassLoader(getClass().getClassLoader());
    }

    public Thing(Bundle bundle, zza zza2, String str, String str2) {
        this.zzdf = 10;
        this.zzaw = bundle;
        this.zzcf = zza2;
        this.zzdg = str;
        this.type = str2;
    }

    /* access modifiers changed from: private */
    public static void zza(Bundle bundle, StringBuilder sb) {
        String obj;
        try {
            Set keySet = bundle.keySet();
            String[] strArr = (String[]) keySet.toArray(new String[keySet.size()]);
            Arrays.sort(strArr, zzz.zzdh);
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
        sb.append("}");
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
