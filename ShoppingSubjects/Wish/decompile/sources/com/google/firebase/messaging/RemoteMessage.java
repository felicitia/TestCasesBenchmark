package com.google.firebase.messaging;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Map;

public final class RemoteMessage extends AbstractSafeParcelable {
    public static final Creator<RemoteMessage> CREATOR = new zzd();
    Bundle zzdp;
    private Map<String, String> zzdq;
    private Notification zzdr;

    public static class Notification {
        private final String tag;
        private final String zzds;
        private final String zzdt;
        private final String[] zzdu;
        private final String zzdv;
        private final String zzdw;
        private final String[] zzdx;
        private final String zzdy;
        private final String zzdz;
        private final String zzea;
        private final String zzeb;
        private final Uri zzec;

        private Notification(Bundle bundle) {
            this.zzds = zza.zza(bundle, "gcm.n.title");
            this.zzdt = zza.zzb(bundle, "gcm.n.title");
            this.zzdu = zze(bundle, "gcm.n.title");
            this.zzdv = zza.zza(bundle, "gcm.n.body");
            this.zzdw = zza.zzb(bundle, "gcm.n.body");
            this.zzdx = zze(bundle, "gcm.n.body");
            this.zzdy = zza.zza(bundle, "gcm.n.icon");
            this.zzdz = zza.zzi(bundle);
            this.tag = zza.zza(bundle, "gcm.n.tag");
            this.zzea = zza.zza(bundle, "gcm.n.color");
            this.zzeb = zza.zza(bundle, "gcm.n.click_action");
            this.zzec = zza.zzg(bundle);
        }

        private static String[] zze(Bundle bundle, String str) {
            Object[] zzc = zza.zzc(bundle, str);
            if (zzc == null) {
                return null;
            }
            String[] strArr = new String[zzc.length];
            for (int i = 0; i < zzc.length; i++) {
                strArr[i] = String.valueOf(zzc[i]);
            }
            return strArr;
        }

        public String getBody() {
            return this.zzdv;
        }
    }

    public RemoteMessage(Bundle bundle) {
        this.zzdp = bundle;
    }

    public final Map<String, String> getData() {
        if (this.zzdq == null) {
            this.zzdq = new ArrayMap();
            for (String str : this.zzdp.keySet()) {
                Object obj = this.zzdp.get(str);
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (!str.startsWith("google.") && !str.startsWith("gcm.") && !str.equals("from") && !str.equals("message_type") && !str.equals("collapse_key")) {
                        this.zzdq.put(str, str2);
                    }
                }
            }
        }
        return this.zzdq;
    }

    public final String getFrom() {
        return this.zzdp.getString("from");
    }

    public final Notification getNotification() {
        if (this.zzdr == null && zza.zzf(this.zzdp)) {
            this.zzdr = new Notification(this.zzdp);
        }
        return this.zzdr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzdp, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
