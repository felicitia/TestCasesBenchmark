package com.google.firebase.appindexing.builders;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appindexing.Indexable;
import com.google.firebase.appindexing.Indexable.Metadata.Builder;
import com.google.firebase.appindexing.builders.IndexableBuilder;
import com.google.firebase.appindexing.internal.Thing;
import com.google.firebase.appindexing.internal.Thing.zza;
import com.google.firebase.appindexing.internal.zzu;
import java.util.Arrays;

public class IndexableBuilder<T extends IndexableBuilder<?>> {
    private final String type;
    private String url;
    private final Bundle zzaw = new Bundle();
    private zza zzcf;

    protected IndexableBuilder(String str) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotEmpty(str);
        this.type = str;
    }

    public static void zza(Bundle bundle, String str, String... strArr) {
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
                    zzu.zze(sb.toString());
                } else {
                    int i3 = 20000;
                    if (strArr2[i].length() > 20000) {
                        StringBuilder sb2 = new StringBuilder(53);
                        sb2.append("String at ");
                        sb2.append(i2);
                        sb2.append(" is too long, truncating string.");
                        zzu.zze(sb2.toString());
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
                bundle.putStringArray(str, (String[]) zza((String[]) Arrays.copyOfRange(strArr2, 0, i)));
            }
            return;
        }
        zzu.zze("String array is empty and is ignored by put method.");
    }

    private static <S> S[] zza(S[] sArr) {
        if (sArr.length < 100) {
            return sArr;
        }
        zzu.zze("Input Array of elements is too big, cutting off.");
        return Arrays.copyOf(sArr, 100);
    }

    public final Indexable build() {
        return new Thing(new Bundle(this.zzaw), this.zzcf == null ? new Builder().zzi() : this.zzcf, this.url, this.type);
    }

    public T put(String str, String... strArr) {
        zza(this.zzaw, str, strArr);
        return this;
    }

    public final T setName(String str) {
        Preconditions.checkNotNull(str);
        return put("name", str);
    }

    public final T setUrl(String str) {
        Preconditions.checkNotNull(str);
        this.url = str;
        return this;
    }
}
