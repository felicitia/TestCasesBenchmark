package com.google.android.gms.internal.plus;

import android.os.Parcelable.Creator;

public final class zzs implements Creator<zzr> {
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00be, code lost:
        r3.add(java.lang.Integer.valueOf(r2));
        r15 = r30;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x014f, code lost:
        r3.add(java.lang.Integer.valueOf(r2));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r32) {
        /*
            r31 = this;
            r0 = r32
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r32)
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            r2 = 0
            r4 = 0
            r9 = r2
            r13 = r9
            r16 = r13
            r20 = r16
            r23 = r20
            r24 = r23
            r28 = r24
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r10 = r8
            r11 = r10
            r12 = r11
            r14 = r12
            r15 = r14
            r17 = r15
            r18 = r17
            r19 = r18
            r21 = r19
            r22 = r21
            r25 = r22
            r26 = r25
            r27 = r26
            r4 = r28
        L_0x0034:
            int r2 = r32.dataPosition()
            if (r2 >= r1) goto L_0x0158
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r32)
            int r29 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r29) {
                case 1: goto L_0x0148;
                case 2: goto L_0x0140;
                case 3: goto L_0x0133;
                case 4: goto L_0x012b;
                case 5: goto L_0x0123;
                case 6: goto L_0x011b;
                case 7: goto L_0x010e;
                case 8: goto L_0x0105;
                case 9: goto L_0x00fc;
                case 10: goto L_0x0045;
                case 11: goto L_0x0045;
                case 12: goto L_0x00f3;
                case 13: goto L_0x0045;
                case 14: goto L_0x00ea;
                case 15: goto L_0x00dd;
                case 16: goto L_0x00d3;
                case 17: goto L_0x0045;
                case 18: goto L_0x00c9;
                case 19: goto L_0x00b0;
                case 20: goto L_0x00a6;
                case 21: goto L_0x009c;
                case 22: goto L_0x0091;
                case 23: goto L_0x0086;
                case 24: goto L_0x007c;
                case 25: goto L_0x0072;
                case 26: goto L_0x0068;
                case 27: goto L_0x005e;
                case 28: goto L_0x0053;
                case 29: goto L_0x004b;
                default: goto L_0x0045;
            }
        L_0x0045:
            r30 = r15
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0034
        L_0x004b:
            boolean r28 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            r2 = 29
            goto L_0x014f
        L_0x0053:
            r30 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zzg> r15 = com.google.android.gms.internal.plus.zzr.zzg.CREATOR
            java.util.ArrayList r27 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r15)
            r2 = 28
            goto L_0x00be
        L_0x005e:
            r30 = r15
            java.lang.String r26 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 27
            goto L_0x014f
        L_0x0068:
            r30 = r15
            java.lang.String r25 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 26
            goto L_0x014f
        L_0x0072:
            r30 = r15
            int r24 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            r2 = 25
            goto L_0x014f
        L_0x007c:
            r30 = r15
            int r23 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            r2 = 24
            goto L_0x014f
        L_0x0086:
            r30 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zzf> r15 = com.google.android.gms.internal.plus.zzr.zzf.CREATOR
            java.util.ArrayList r22 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r15)
            r2 = 23
            goto L_0x00be
        L_0x0091:
            r30 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zze> r15 = com.google.android.gms.internal.plus.zzr.zze.CREATOR
            java.util.ArrayList r21 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r15)
            r2 = 22
            goto L_0x00be
        L_0x009c:
            r30 = r15
            int r20 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            r2 = 21
            goto L_0x014f
        L_0x00a6:
            r30 = r15
            java.lang.String r19 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 20
            goto L_0x014f
        L_0x00b0:
            r30 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zzd> r15 = com.google.android.gms.internal.plus.zzr.zzd.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r15)
            r18 = r2
            com.google.android.gms.internal.plus.zzr$zzd r18 = (com.google.android.gms.internal.plus.zzr.zzd) r18
            r2 = 19
        L_0x00be:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3.add(r2)
            r15 = r30
            goto L_0x0034
        L_0x00c9:
            r30 = r15
            java.lang.String r17 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 18
            goto L_0x014f
        L_0x00d3:
            r30 = r15
            boolean r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            r2 = 16
            goto L_0x014f
        L_0x00dd:
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zzc> r15 = com.google.android.gms.internal.plus.zzr.zzc.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r15)
            r15 = r2
            com.google.android.gms.internal.plus.zzr$zzc r15 = (com.google.android.gms.internal.plus.zzr.zzc) r15
            r2 = 15
            goto L_0x014f
        L_0x00ea:
            r30 = r15
            java.lang.String r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 14
            goto L_0x014f
        L_0x00f3:
            r30 = r15
            int r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            r2 = 12
            goto L_0x014f
        L_0x00fc:
            r30 = r15
            java.lang.String r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 9
            goto L_0x014f
        L_0x0105:
            r30 = r15
            java.lang.String r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 8
            goto L_0x014f
        L_0x010e:
            r30 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zzb> r10 = com.google.android.gms.internal.plus.zzr.zzb.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r10)
            r10 = r2
            com.google.android.gms.internal.plus.zzr$zzb r10 = (com.google.android.gms.internal.plus.zzr.zzb) r10
            r2 = 7
            goto L_0x014f
        L_0x011b:
            r30 = r15
            int r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            r2 = 6
            goto L_0x014f
        L_0x0123:
            r30 = r15
            java.lang.String r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 5
            goto L_0x014f
        L_0x012b:
            r30 = r15
            java.lang.String r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 4
            goto L_0x014f
        L_0x0133:
            r30 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zza> r6 = com.google.android.gms.internal.plus.zzr.zza.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r6)
            r6 = r2
            com.google.android.gms.internal.plus.zzr$zza r6 = (com.google.android.gms.internal.plus.zzr.zza) r6
            r2 = 3
            goto L_0x014f
        L_0x0140:
            r30 = r15
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 2
            goto L_0x014f
        L_0x0148:
            r30 = r15
            int r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            r2 = 1
        L_0x014f:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3.add(r2)
            goto L_0x0034
        L_0x0158:
            r30 = r15
            int r2 = r32.dataPosition()
            if (r2 == r1) goto L_0x0179
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException r2 = new com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException
            r3 = 37
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Overread allowed size end="
            r4.append(r3)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r2.<init>(r1, r0)
            throw r2
        L_0x0179:
            com.google.android.gms.internal.plus.zzr r0 = new com.google.android.gms.internal.plus.zzr
            r2 = r0
            r15 = r30
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.plus.zzs.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzr[i];
    }
}
