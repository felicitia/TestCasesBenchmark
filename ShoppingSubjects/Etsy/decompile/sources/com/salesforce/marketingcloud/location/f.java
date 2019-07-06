package com.salesforce.marketingcloud.location;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

public abstract class f {
    @RestrictTo({Scope.LIBRARY})
    public static f a(String str, float f, double d, double d2, int i) {
        c cVar = new c(str, f, d, d2, i);
        return cVar;
    }

    public abstract String a();

    public abstract float b();

    public abstract double c();

    public abstract double d();

    public abstract int e();
}
