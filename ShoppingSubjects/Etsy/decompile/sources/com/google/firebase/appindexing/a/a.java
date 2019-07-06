package com.google.firebase.appindexing.a;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appindexing.a.a;
import com.google.firebase.appindexing.d;
import com.google.firebase.appindexing.internal.Thing;
import com.google.firebase.appindexing.internal.Thing.zza;
import com.google.firebase.appindexing.internal.o;
import java.util.Arrays;

public class a<T extends a<?>> {
    private final Bundle a = new Bundle();
    private final String b;
    private zza c;
    private String d;

    protected a(@NonNull String str) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotEmpty(str);
        this.b = str;
    }

    public static void a(@NonNull Bundle bundle, @NonNull String str, @NonNull String... strArr) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(strArr);
        String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
        if (strArr2.length > 0) {
            int i = 0;
            for (int i2 = 0; i2 < Math.min(strArr2.length, 100); i2++) {
                strArr2[i] = strArr2[i2];
                if (strArr2[i2] == null) {
                    StringBuilder sb = new StringBuilder(59);
                    sb.append("String at ");
                    sb.append(i2);
                    sb.append(" is null and is ignored by put method.");
                    o.a(sb.toString());
                } else {
                    int i3 = 20000;
                    if (strArr2[i].length() > 20000) {
                        StringBuilder sb2 = new StringBuilder(53);
                        sb2.append("String at ");
                        sb2.append(i2);
                        sb2.append(" is too long, truncating string.");
                        o.a(sb2.toString());
                        String str2 = strArr2[i];
                        if (str2.length() > 20000) {
                            if (Character.isHighSurrogate(str2.charAt(19999)) && Character.isLowSurrogate(str2.charAt(20000))) {
                                i3 = 19999;
                            }
                            str2 = str2.substring(0, i3);
                        }
                        strArr2[i] = str2;
                    }
                    i++;
                }
            }
            if (i > 0) {
                bundle.putStringArray(str, (String[]) a((S[]) (String[]) Arrays.copyOfRange(strArr2, 0, i)));
            }
            return;
        }
        o.a("String array is empty and is ignored by put method.");
    }

    private static <S> S[] a(S[] sArr) {
        if (sArr.length < 100) {
            return sArr;
        }
        o.a("Input Array of elements is too big, cutting off.");
        return Arrays.copyOf(sArr, 100);
    }

    public final T a(@NonNull String str) {
        Preconditions.checkNotNull(str);
        return a(ResponseConstants.NAME, str);
    }

    public T a(@NonNull String str, @NonNull String... strArr) {
        a(this.a, str, strArr);
        return this;
    }

    public final d a() {
        return new Thing(new Bundle(this.a), this.c == null ? new com.google.firebase.appindexing.d.b.a().a() : this.c, this.d, this.b);
    }

    public final T b(@NonNull String str) {
        Preconditions.checkNotNull(str);
        this.d = str;
        return this;
    }
}
