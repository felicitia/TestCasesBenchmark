package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.ICertData.Stub;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@Class(creator = "GoogleCertificatesQueryCreator")
public class GoogleCertificatesQuery extends AbstractSafeParcelable {
    public static final Creator<GoogleCertificatesQuery> CREATOR = new GoogleCertificatesQueryCreator();
    @Field(getter = "getCallingPackage", id = 1)
    private final String zzbh;
    @Field(getter = "getCallingCertificateBinder", id = 2, type = "android.os.IBinder")
    private final CertData zzbi;
    @Field(getter = "getAllowTestKeys", id = 3)
    private final boolean zzbj;

    @Constructor
    GoogleCertificatesQuery(@Param(id = 1) String str, @Param(id = 2) IBinder iBinder, @Param(id = 3) boolean z) {
        this.zzbh = str;
        this.zzbi = zza(iBinder);
        this.zzbj = z;
    }

    GoogleCertificatesQuery(String str, CertData certData, boolean z) {
        this.zzbh = str;
        this.zzbi = certData;
        this.zzbj = z;
    }

    private static CertData zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        try {
            IObjectWrapper bytesWrapped = Stub.asInterface(iBinder).getBytesWrapped();
            byte[] bArr = bytesWrapped == null ? null : (byte[]) ObjectWrapper.unwrap(bytesWrapped);
            if (bArr != null) {
                return new zzb(bArr);
            }
            Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
            return null;
        } catch (RemoteException e) {
            Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", e);
            return null;
        }
    }

    public boolean getAllowTestKeys() {
        return this.zzbj;
    }

    public IBinder getCallingCertificateBinder() {
        if (this.zzbi != null) {
            return this.zzbi.asBinder();
        }
        Log.w("GoogleCertificatesQuery", "certificate binder is null");
        return null;
    }

    public String getCallingPackage() {
        return this.zzbh;
    }

    public CertData getCertificate() {
        return this.zzbi;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getCallingPackage(), false);
        SafeParcelWriter.writeIBinder(parcel, 2, getCallingCertificateBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 3, getAllowTestKeys());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
