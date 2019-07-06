package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

@Class(creator = "PasswordSpecificationCreator")
@Reserved({1000})
public final class PasswordSpecification extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<PasswordSpecification> CREATOR = new zzm();
    public static final PasswordSpecification zzdg = new a().a(12, 16).a("abcdefghijkmnopqrstxyzABCDEFGHJKLMNPQRSTXY3456789").a("abcdefghijkmnopqrstxyz", 1).a("ABCDEFGHJKLMNPQRSTXY", 1).a("3456789", 1).a();
    private static final PasswordSpecification zzdh = new a().a(12, 16).a("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890").a("abcdefghijklmnopqrstuvwxyz", 1).a("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1).a("1234567890", 1).a();
    @VisibleForTesting
    @Field(id = 1)
    private final String zzdi;
    @VisibleForTesting
    @Field(id = 2)
    private final List<String> zzdj;
    @VisibleForTesting
    @Field(id = 3)
    private final List<Integer> zzdk;
    @VisibleForTesting
    @Field(id = 4)
    private final int zzdl;
    @VisibleForTesting
    @Field(id = 5)
    private final int zzdm;
    private final int[] zzdn;
    private final Random zzdo;

    public static class a {
        private final TreeSet<Character> a = new TreeSet<>();
        private final List<String> b = new ArrayList();
        private final List<Integer> c = new ArrayList();
        private int d = 12;
        private int e = 16;

        private static TreeSet<Character> a(String str, String str2) {
            char[] charArray;
            if (TextUtils.isEmpty(str)) {
                throw new zze(String.valueOf(str2).concat(" cannot be null or empty"));
            }
            TreeSet<Character> treeSet = new TreeSet<>();
            for (char c2 : str.toCharArray()) {
                if (PasswordSpecification.zzd(c2, 32, 126)) {
                    throw new zze(String.valueOf(str2).concat(" must only contain ASCII printable characters"));
                }
                treeSet.add(Character.valueOf(c2));
            }
            return treeSet;
        }

        public final a a(int i, int i2) {
            this.d = 12;
            this.e = 16;
            return this;
        }

        public final a a(@NonNull String str) {
            this.a.addAll(a(str, "allowedChars"));
            return this;
        }

        public final a a(@NonNull String str, int i) {
            this.b.add(PasswordSpecification.zzd(a(str, "requiredChars")));
            this.c.add(Integer.valueOf(1));
            return this;
        }

        public final PasswordSpecification a() {
            if (this.a.isEmpty()) {
                throw new zze("no allowed characters specified");
            }
            int i = 0;
            for (Integer intValue : this.c) {
                i += intValue.intValue();
            }
            if (i > this.e) {
                throw new zze("required character count cannot be greater than the max password size");
            }
            boolean[] zArr = new boolean[95];
            for (String charArray : this.b) {
                char[] charArray2 = charArray.toCharArray();
                int length = charArray2.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        char c2 = charArray2[i2];
                        int i3 = c2 - ' ';
                        if (zArr[i3]) {
                            StringBuilder sb = new StringBuilder(58);
                            sb.append("character ");
                            sb.append(c2);
                            sb.append(" occurs in more than one required character set");
                            throw new zze(sb.toString());
                        }
                        zArr[i3] = true;
                        i2++;
                    }
                }
            }
            PasswordSpecification passwordSpecification = new PasswordSpecification(PasswordSpecification.zzd(this.a), this.b, this.c, this.d, this.e);
            return passwordSpecification;
        }
    }

    public static class zze extends Error {
        public zze(String str) {
            super(str);
        }
    }

    @Constructor
    PasswordSpecification(@Param(id = 1) String str, @Param(id = 2) List<String> list, @Param(id = 3) List<Integer> list2, @Param(id = 4) int i, @Param(id = 5) int i2) {
        this.zzdi = str;
        this.zzdj = Collections.unmodifiableList(list);
        this.zzdk = Collections.unmodifiableList(list2);
        this.zzdl = i;
        this.zzdm = i2;
        int[] iArr = new int[95];
        Arrays.fill(iArr, -1);
        int i3 = 0;
        for (String charArray : this.zzdj) {
            char[] charArray2 = charArray.toCharArray();
            int length = charArray2.length;
            for (int i4 = 0; i4 < length; i4++) {
                iArr[charArray2[i4] - ' '] = i3;
            }
            i3++;
        }
        this.zzdn = iArr;
        this.zzdo = new SecureRandom();
    }

    /* access modifiers changed from: private */
    public static String zzd(Collection<Character> collection) {
        char[] cArr = new char[collection.size()];
        int i = 0;
        for (Character charValue : collection) {
            int i2 = i + 1;
            cArr[i] = charValue.charValue();
            i = i2;
        }
        return new String(cArr);
    }

    /* access modifiers changed from: private */
    public static boolean zzd(int i, int i2, int i3) {
        return i < 32 || i > 126;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzdi, false);
        SafeParcelWriter.writeStringList(parcel, 2, this.zzdj, false);
        SafeParcelWriter.writeIntegerList(parcel, 3, this.zzdk, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzdl);
        SafeParcelWriter.writeInt(parcel, 5, this.zzdm);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
