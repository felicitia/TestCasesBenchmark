package com.google.android.gms.internal.firebase-perf;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.UUID;

public class zzr implements Parcelable {
    public static final Creator<zzr> CREATOR = new zzs();
    private String zzbx;
    private boolean zzby;

    public static zzr zzr() {
        String replaceAll = UUID.randomUUID().toString().replaceAll("\\-", "");
        new zzr(replaceAll).zzby = false;
        return new zzr(replaceAll);
    }

    public int describeContents() {
        return 0;
    }

    private zzr(String str) {
        this.zzby = false;
        this.zzbx = str;
    }

    public final String zzs() {
        return this.zzbx;
    }

    public final zzas zzt() {
        zzas zzas = new zzas();
        zzas.zzgv = this.zzbx;
        ArrayList arrayList = new ArrayList();
        if (this.zzby) {
            arrayList.add(Integer.valueOf(1));
        }
        if (arrayList.size() == 0) {
            arrayList.add(Integer.valueOf(0));
        }
        int[] iArr = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            iArr[i] = ((Integer) arrayList.get(i)).intValue();
        }
        zzas.zzgw = iArr;
        return zzas;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzbx);
        parcel.writeByte(this.zzby ? (byte) 1 : 0);
    }

    private zzr(Parcel parcel) {
        boolean z = false;
        this.zzby = false;
        this.zzbx = parcel.readString();
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.zzby = z;
    }

    /* synthetic */ zzr(Parcel parcel, zzs zzs) {
        this(parcel);
    }
}
