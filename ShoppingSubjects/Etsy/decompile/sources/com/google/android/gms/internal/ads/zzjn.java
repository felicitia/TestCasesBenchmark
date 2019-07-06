package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.d;
import com.google.android.gms.ads.l;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@bu
@Class(creator = "AdSizeParcelCreator")
@Reserved({1})
public class zzjn extends AbstractSafeParcelable {
    public static final Creator<zzjn> CREATOR = new zzjo();
    @Field(id = 3)
    public final int height;
    @Field(id = 4)
    public final int heightPixels;
    @Field(id = 6)
    public final int width;
    @Field(id = 7)
    public final int widthPixels;
    @Field(id = 2)
    public final String zzarb;
    @Field(id = 5)
    public final boolean zzarc;
    @Field(id = 8)
    public final zzjn[] zzard;
    @Field(id = 9)
    public final boolean zzare;
    @Field(id = 10)
    public final boolean zzarf;
    @Field(id = 11)
    public boolean zzarg;

    public zzjn() {
        this("interstitial_mb", 0, 0, true, 0, 0, null, false, false, false);
    }

    public zzjn(Context context, d dVar) {
        this(context, new d[]{dVar});
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x007d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzjn(android.content.Context r13, com.google.android.gms.ads.d[] r14) {
        /*
            r12 = this;
            r12.<init>()
            r0 = 0
            r1 = r14[r0]
            r12.zzarc = r0
            boolean r2 = r1.c()
            r12.zzarf = r2
            boolean r2 = r12.zzarf
            if (r2 == 0) goto L_0x0023
            com.google.android.gms.ads.d r2 = com.google.android.gms.ads.d.a
            int r2 = r2.b()
            r12.width = r2
            com.google.android.gms.ads.d r2 = com.google.android.gms.ads.d.a
            int r2 = r2.a()
        L_0x0020:
            r12.height = r2
            goto L_0x002e
        L_0x0023:
            int r2 = r1.b()
            r12.width = r2
            int r2 = r1.a()
            goto L_0x0020
        L_0x002e:
            int r2 = r12.width
            r3 = -1
            r4 = 1
            if (r2 != r3) goto L_0x0036
            r2 = r4
            goto L_0x0037
        L_0x0036:
            r2 = r0
        L_0x0037:
            int r3 = r12.height
            r5 = -2
            if (r3 != r5) goto L_0x003e
            r3 = r4
            goto L_0x003f
        L_0x003e:
            r3 = r0
        L_0x003f:
            android.content.res.Resources r5 = r13.getResources()
            android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
            if (r2 == 0) goto L_0x0080
            com.google.android.gms.internal.ads.ajh.a()
            boolean r6 = com.google.android.gms.internal.ads.jp.g(r13)
            if (r6 == 0) goto L_0x0066
            com.google.android.gms.internal.ads.ajh.a()
            boolean r6 = com.google.android.gms.internal.ads.jp.h(r13)
            if (r6 == 0) goto L_0x0066
            int r6 = r5.widthPixels
            com.google.android.gms.internal.ads.ajh.a()
            int r7 = com.google.android.gms.internal.ads.jp.i(r13)
            int r6 = r6 - r7
            goto L_0x0068
        L_0x0066:
            int r6 = r5.widthPixels
        L_0x0068:
            r12.widthPixels = r6
            int r6 = r12.widthPixels
            float r6 = (float) r6
            float r7 = r5.density
            float r6 = r6 / r7
            double r6 = (double) r6
            int r8 = (int) r6
            double r9 = (double) r8
            double r6 = r6 - r9
            r9 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            int r11 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r11 < 0) goto L_0x008d
            int r8 = r8 + 1
            goto L_0x008d
        L_0x0080:
            int r8 = r12.width
            com.google.android.gms.internal.ads.ajh.a()
            int r6 = r12.width
            int r6 = com.google.android.gms.internal.ads.jp.a(r5, r6)
            r12.widthPixels = r6
        L_0x008d:
            if (r3 == 0) goto L_0x0094
            int r6 = zzd(r5)
            goto L_0x0096
        L_0x0094:
            int r6 = r12.height
        L_0x0096:
            com.google.android.gms.internal.ads.ajh.a()
            int r5 = com.google.android.gms.internal.ads.jp.a(r5, r6)
            r12.heightPixels = r5
            if (r2 != 0) goto L_0x00b0
            if (r3 == 0) goto L_0x00a4
            goto L_0x00b0
        L_0x00a4:
            boolean r2 = r12.zzarf
            if (r2 == 0) goto L_0x00ab
            java.lang.String r1 = "320x50_mb"
            goto L_0x00cb
        L_0x00ab:
            java.lang.String r1 = r1.toString()
            goto L_0x00cb
        L_0x00b0:
            r1 = 26
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            r2.append(r8)
            java.lang.String r1 = "x"
            r2.append(r1)
            r2.append(r6)
            java.lang.String r1 = "_as"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
        L_0x00cb:
            r12.zzarb = r1
            int r1 = r14.length
            if (r1 <= r4) goto L_0x00e7
            int r1 = r14.length
            com.google.android.gms.internal.ads.zzjn[] r1 = new com.google.android.gms.internal.ads.zzjn[r1]
            r12.zzard = r1
            r1 = r0
        L_0x00d6:
            int r2 = r14.length
            if (r1 >= r2) goto L_0x00ea
            com.google.android.gms.internal.ads.zzjn[] r2 = r12.zzard
            com.google.android.gms.internal.ads.zzjn r3 = new com.google.android.gms.internal.ads.zzjn
            r4 = r14[r1]
            r3.<init>(r13, r4)
            r2[r1] = r3
            int r1 = r1 + 1
            goto L_0x00d6
        L_0x00e7:
            r13 = 0
            r12.zzard = r13
        L_0x00ea:
            r12.zzare = r0
            r12.zzarg = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjn.<init>(android.content.Context, com.google.android.gms.ads.d[]):void");
    }

    public zzjn(zzjn zzjn, zzjn[] zzjnArr) {
        this(zzjn.zzarb, zzjn.height, zzjn.heightPixels, zzjn.zzarc, zzjn.width, zzjn.widthPixels, zzjnArr, zzjn.zzare, zzjn.zzarf, zzjn.zzarg);
    }

    @Constructor
    zzjn(@Param(id = 2) String str, @Param(id = 3) int i, @Param(id = 4) int i2, @Param(id = 5) boolean z, @Param(id = 6) int i3, @Param(id = 7) int i4, @Param(id = 8) zzjn[] zzjnArr, @Param(id = 9) boolean z2, @Param(id = 10) boolean z3, @Param(id = 11) boolean z4) {
        this.zzarb = str;
        this.height = i;
        this.heightPixels = i2;
        this.zzarc = z;
        this.width = i3;
        this.widthPixels = i4;
        this.zzard = zzjnArr;
        this.zzare = z2;
        this.zzarf = z3;
        this.zzarg = z4;
    }

    public static int zzb(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }

    public static int zzc(DisplayMetrics displayMetrics) {
        return (int) (((float) zzd(displayMetrics)) * displayMetrics.density);
    }

    private static int zzd(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        if (i <= 400) {
            return 32;
        }
        return i <= 720 ? 50 : 90;
    }

    public static zzjn zzf(Context context) {
        zzjn zzjn = new zzjn("320x50_mb", 0, 0, false, 0, 0, null, true, false, false);
        return zzjn;
    }

    public static zzjn zzhx() {
        zzjn zzjn = new zzjn("reward_mb", 0, 0, true, 0, 0, null, false, false, false);
        return zzjn;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzarb, false);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 4, this.heightPixels);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzarc);
        SafeParcelWriter.writeInt(parcel, 6, this.width);
        SafeParcelWriter.writeInt(parcel, 7, this.widthPixels);
        SafeParcelWriter.writeTypedArray(parcel, 8, this.zzard, i, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzare);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzarf);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzarg);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final d zzhy() {
        return l.a(this.width, this.height, this.zzarb);
    }
}
