package com.google.android.gms.ads.formats;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.zzjp;
import com.google.android.gms.internal.ads.zzla;
import com.google.android.gms.internal.ads.zzlb;

@bu
@Class(creator = "PublisherAdViewOptionsCreator")
public final class PublisherAdViewOptions extends AbstractSafeParcelable {
    public static final Creator<PublisherAdViewOptions> CREATOR = new zzc();
    @Field(getter = "getManualImpressionsEnabled", id = 1)
    private final boolean zzvm;
    @Nullable
    @Field(getter = "getAppEventListenerBinder", id = 2, type = "android.os.IBinder")
    private final zzla zzvn;
    @Nullable
    private com.google.android.gms.ads.doubleclick.a zzvo;

    public static final class a {
        /* access modifiers changed from: private */
        public boolean a;
        /* access modifiers changed from: private */
        public com.google.android.gms.ads.doubleclick.a b;
    }

    private PublisherAdViewOptions(a aVar) {
        this.zzvm = aVar.a;
        this.zzvo = aVar.b;
        this.zzvn = this.zzvo != null ? new zzjp(this.zzvo) : null;
    }

    @Constructor
    PublisherAdViewOptions(@Param(id = 1) boolean z, @Nullable @Param(id = 2) IBinder iBinder) {
        this.zzvm = z;
        this.zzvn = iBinder != null ? zzlb.zzd(iBinder) : null;
    }

    @Nullable
    public final com.google.android.gms.ads.doubleclick.a getAppEventListener() {
        return this.zzvo;
    }

    public final boolean getManualImpressionsEnabled() {
        return this.zzvm;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, getManualImpressionsEnabled());
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzvn == null ? null : this.zzvn.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final zzla zzbg() {
        return this.zzvn;
    }
}
