package com.google.android.gms.internal.icing;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.a.d.a;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.zip.CRC32;

@Class(creator = "UsageInfoCreator")
@Reserved({1000})
public final class zzx extends AbstractSafeParcelable {
    public static final Creator<zzx> CREATOR = new zzz();
    @Field(id = 1)
    private final zzj zzaj;
    @Field(id = 2)
    private final long zzak;
    @Field(id = 3)
    private int zzal;
    @Field(id = 4)
    private final String zzam;
    @Field(id = 5)
    private final zzg zzan;
    @Field(defaultValue = "false", id = 6)
    private final boolean zzao;
    @Field(defaultValue = "-1", id = 7)
    private int zzap;
    @Field(id = 8)
    private int zzaq;

    @Constructor
    zzx(@Param(id = 1) zzj zzj, @Param(id = 2) long j, @Param(id = 3) int i, @Param(id = 4) String str, @Param(id = 5) zzg zzg, @Param(id = 6) boolean z, @Param(id = 7) int i2, @Param(id = 8) int i3) {
        this.zzaj = zzj;
        this.zzak = j;
        this.zzal = i;
        this.zzam = str;
        this.zzan = zzg;
        this.zzao = z;
        this.zzap = i2;
        this.zzaq = i3;
    }

    @VisibleForTesting
    public zzx(String str, Intent intent, String str2, Uri uri, String str3, List<a> list, int i) {
        this(zza(str, intent), System.currentTimeMillis(), 0, null, zza(intent, str2, uri, null, list).a(), false, -1, 1);
    }

    @VisibleForTesting
    public static dq zza(Intent intent, String str, Uri uri, String str2, List<a> list) {
        dq dqVar = new dq();
        dqVar.a(new zzl(str, new ds("title").b(true).b(ResponseConstants.NAME).a(), "text1"));
        if (uri != null) {
            dqVar.a(new zzl(uri.toString(), new ds("web_url").a(true).b("url").a()));
        }
        if (list != null) {
            c cVar = new c();
            d[] dVarArr = new d[list.size()];
            for (int i = 0; i < dVarArr.length; i++) {
                dVarArr[i] = new d();
                a aVar = (a) list.get(i);
                dVarArr[i].a = aVar.a.toString();
                dVarArr[i].c = aVar.c;
                if (aVar.b != null) {
                    dVarArr[i].b = aVar.b.toString();
                }
            }
            cVar.a = dVarArr;
            dqVar.a(new zzl(dm.a((dm) cVar), new ds("outlinks").a(true).b(".private:outLinks").a("blob").a()));
        }
        String action = intent.getAction();
        if (action != null) {
            dqVar.a(zza("intent_action", action));
        }
        String dataString = intent.getDataString();
        if (dataString != null) {
            dqVar.a(zza("intent_data", dataString));
        }
        ComponentName component = intent.getComponent();
        if (component != null) {
            dqVar.a(zza("intent_activity", component.getClassName()));
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String string = extras.getString("intent_extra_data_key");
            if (string != null) {
                dqVar.a(zza("intent_extra_data", string));
            }
        }
        return dqVar.a(str2).a(true);
    }

    public static zzj zza(String str, Intent intent) {
        return new zzj(str, "", zza(intent));
    }

    private static zzl zza(String str, String str2) {
        return new zzl(str2, new ds(str).a(true).a(), str);
    }

    private static String zza(Intent intent) {
        String uri = intent.toUri(1);
        CRC32 crc32 = new CRC32();
        try {
            crc32.update(uri.getBytes("UTF-8"));
            return Long.toHexString(crc32.getValue());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public final String toString() {
        return String.format(Locale.US, "UsageInfo[documentId=%s, timestamp=%d, usageType=%d, status=%d]", new Object[]{this.zzaj, Long.valueOf(this.zzak), Integer.valueOf(this.zzal), Integer.valueOf(this.zzaq)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzaj, i, false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzak);
        SafeParcelWriter.writeInt(parcel, 3, this.zzal);
        SafeParcelWriter.writeString(parcel, 4, this.zzam, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzan, i, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzao);
        SafeParcelWriter.writeInt(parcel, 7, this.zzap);
        SafeParcelWriter.writeInt(parcel, 8, this.zzaq);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
