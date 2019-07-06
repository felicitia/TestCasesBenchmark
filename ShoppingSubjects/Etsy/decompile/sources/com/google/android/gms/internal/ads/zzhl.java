package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.List;

@bu
@Class(creator = "CacheOfferingCreator")
@Reserved({1})
public final class zzhl extends AbstractSafeParcelable {
    public static final Creator<zzhl> CREATOR = new zzhm();
    @Nullable
    @Field(id = 2)
    public final String url;
    @Field(id = 3)
    private final long zzajv;
    @Field(id = 4)
    private final String zzajw;
    @Field(id = 5)
    private final String zzajx;
    @Field(id = 6)
    private final String zzajy;
    @Field(id = 7)
    private final Bundle zzajz;
    @Field(id = 8)
    private final boolean zzaka;
    @Field(id = 9)
    private long zzakb;

    @Constructor
    zzhl(@Nullable @Param(id = 2) String str, @Param(id = 3) long j, @Param(id = 4) String str2, @Param(id = 5) String str3, @Param(id = 6) String str4, @Param(id = 7) Bundle bundle, @Param(id = 8) boolean z, @Param(id = 9) long j2) {
        this.url = str;
        this.zzajv = j;
        if (str2 == null) {
            str2 = "";
        }
        this.zzajw = str2;
        if (str3 == null) {
            str3 = "";
        }
        this.zzajx = str3;
        if (str4 == null) {
            str4 = "";
        }
        this.zzajy = str4;
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzajz = bundle;
        this.zzaka = z;
        this.zzakb = j2;
    }

    @Nullable
    public static zzhl zzaa(String str) {
        return zzd(Uri.parse(str));
    }

    @Nullable
    public static zzhl zzd(Uri uri) {
        try {
            if (!"gcache".equals(uri.getScheme())) {
                return null;
            }
            List pathSegments = uri.getPathSegments();
            if (pathSegments.size() != 2) {
                int size = pathSegments.size();
                StringBuilder sb = new StringBuilder(62);
                sb.append("Expected 2 path parts for namespace and id, found :");
                sb.append(size);
                gv.e(sb.toString());
                return null;
            }
            String str = (String) pathSegments.get(0);
            String str2 = (String) pathSegments.get(1);
            String host = uri.getHost();
            String queryParameter = uri.getQueryParameter("url");
            boolean equals = "1".equals(uri.getQueryParameter("read_only"));
            String queryParameter2 = uri.getQueryParameter("expiration");
            long parseLong = queryParameter2 == null ? 0 : Long.parseLong(queryParameter2);
            Bundle bundle = new Bundle();
            for (String str3 : ao.g().a(uri)) {
                if (str3.startsWith("tag.")) {
                    bundle.putString(str3.substring(4), uri.getQueryParameter(str3));
                }
            }
            zzhl zzhl = new zzhl(queryParameter, parseLong, host, str, str2, bundle, equals, 0);
            return zzhl;
        } catch (NullPointerException | NumberFormatException e) {
            gv.c("Unable to parse Uri into cache offering.", e);
            return null;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.url, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzajv);
        SafeParcelWriter.writeString(parcel, 4, this.zzajw, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzajx, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzajy, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzajz, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzaka);
        SafeParcelWriter.writeLong(parcel, 9, this.zzakb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
