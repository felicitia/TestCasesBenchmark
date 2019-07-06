package com.salesforce.marketingcloud.registration;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.io.Serializable;

@Deprecated
public abstract class Attribute implements Parcelable, Serializable, Comparable<Attribute> {
    @RestrictTo({Scope.LIBRARY})
    public static Attribute a(String str, String str2) {
        return new b(str, str2);
    }

    /* renamed from: a */
    public final int compareTo(Attribute attribute) {
        if (key() == null || attribute == null || attribute.key() == null) {
            return 0;
        }
        return key().compareToIgnoreCase(attribute.key());
    }

    @NonNull
    public abstract String key();

    @NonNull
    public abstract String value();
}
