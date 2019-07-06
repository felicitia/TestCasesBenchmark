package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

@KeepName
@Class(creator = "CommonWalletObjectCreator")
@Reserved({1})
public class CommonWalletObject extends AbstractSafeParcelable {
    public static final Creator<CommonWalletObject> CREATOR = new zzb();
    @Field(id = 4)
    String name;
    @Field(id = 10)
    int state;
    @Field(id = 2)
    String zzce;
    @Field(id = 5)
    String zzcg;
    @Field(id = 6)
    String zzcj;
    @Field(id = 7)
    String zzck;
    @Field(id = 8)
    String zzcl;
    @Field(id = 9)
    String zzcm;
    @Field(id = 3)
    String zzcn;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 11)
    ArrayList<WalletObjectMessage> zzco;
    @Field(id = 12)
    TimeInterval zzcp;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 13)
    ArrayList<LatLng> zzcq;
    @Field(id = 14)
    String zzcr;
    @Field(id = 15)
    String zzcs;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 16)
    ArrayList<LabelValueRow> zzct;
    @Field(id = 17)
    boolean zzcu;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 18)
    ArrayList<UriData> zzcv;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 19)
    ArrayList<TextModuleData> zzcw;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 20)
    ArrayList<UriData> zzcx;

    public final class a {
        private a() {
        }

        public final a a(String str) {
            CommonWalletObject.this.zzce = str;
            return this;
        }

        public final CommonWalletObject a() {
            return CommonWalletObject.this;
        }
    }

    CommonWalletObject() {
        this.zzco = ArrayUtils.newArrayList();
        this.zzcq = ArrayUtils.newArrayList();
        this.zzct = ArrayUtils.newArrayList();
        this.zzcv = ArrayUtils.newArrayList();
        this.zzcw = ArrayUtils.newArrayList();
        this.zzcx = ArrayUtils.newArrayList();
    }

    @Constructor
    CommonWalletObject(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) String str4, @Param(id = 6) String str5, @Param(id = 7) String str6, @Param(id = 8) String str7, @Param(id = 9) String str8, @Param(id = 10) int i, @Param(id = 11) ArrayList<WalletObjectMessage> arrayList, @Param(id = 12) TimeInterval timeInterval, @Param(id = 13) ArrayList<LatLng> arrayList2, @Param(id = 14) String str9, @Param(id = 15) String str10, @Param(id = 16) ArrayList<LabelValueRow> arrayList3, @Param(id = 17) boolean z, @Param(id = 18) ArrayList<UriData> arrayList4, @Param(id = 19) ArrayList<TextModuleData> arrayList5, @Param(id = 20) ArrayList<UriData> arrayList6) {
        this.zzce = str;
        this.zzcn = str2;
        this.name = str3;
        this.zzcg = str4;
        this.zzcj = str5;
        this.zzck = str6;
        this.zzcl = str7;
        this.zzcm = str8;
        this.state = i;
        this.zzco = arrayList;
        this.zzcp = timeInterval;
        this.zzcq = arrayList2;
        this.zzcr = str9;
        this.zzcs = str10;
        this.zzct = arrayList3;
        this.zzcu = z;
        this.zzcv = arrayList4;
        this.zzcw = arrayList5;
        this.zzcx = arrayList6;
    }

    public static a zze() {
        return new a();
    }

    public final String getBarcodeAlternateText() {
        return this.zzcj;
    }

    public final String getBarcodeLabel() {
        return this.zzcm;
    }

    public final String getBarcodeType() {
        return this.zzck;
    }

    public final String getBarcodeValue() {
        return this.zzcl;
    }

    public final String getClassId() {
        return this.zzcn;
    }

    public final String getId() {
        return this.zzce;
    }

    public final ArrayList<UriData> getImageModuleDataMainImageUris() {
        return this.zzcv;
    }

    public final String getInfoModuleDataHexBackgroundColor() {
        return this.zzcs;
    }

    public final String getInfoModuleDataHexFontColor() {
        return this.zzcr;
    }

    public final ArrayList<LabelValueRow> getInfoModuleDataLabelValueRows() {
        return this.zzct;
    }

    public final boolean getInfoModuleDataShowLastUpdateTime() {
        return this.zzcu;
    }

    public final String getIssuerName() {
        return this.zzcg;
    }

    public final ArrayList<UriData> getLinksModuleDataUris() {
        return this.zzcx;
    }

    public final ArrayList<LatLng> getLocations() {
        return this.zzcq;
    }

    public final ArrayList<WalletObjectMessage> getMessages() {
        return this.zzco;
    }

    public final String getName() {
        return this.name;
    }

    public final int getState() {
        return this.state;
    }

    public final ArrayList<TextModuleData> getTextModulesData() {
        return this.zzcw;
    }

    public final TimeInterval getValidTimeInterval() {
        return this.zzcp;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzce, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzcn, false);
        SafeParcelWriter.writeString(parcel, 4, this.name, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzcg, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzcj, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzck, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzcl, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzcm, false);
        SafeParcelWriter.writeInt(parcel, 10, this.state);
        SafeParcelWriter.writeTypedList(parcel, 11, this.zzco, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzcp, i, false);
        SafeParcelWriter.writeTypedList(parcel, 13, this.zzcq, false);
        SafeParcelWriter.writeString(parcel, 14, this.zzcr, false);
        SafeParcelWriter.writeString(parcel, 15, this.zzcs, false);
        SafeParcelWriter.writeTypedList(parcel, 16, this.zzct, false);
        SafeParcelWriter.writeBoolean(parcel, 17, this.zzcu);
        SafeParcelWriter.writeTypedList(parcel, 18, this.zzcv, false);
        SafeParcelWriter.writeTypedList(parcel, 19, this.zzcw, false);
        SafeParcelWriter.writeTypedList(parcel, 20, this.zzcx, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
