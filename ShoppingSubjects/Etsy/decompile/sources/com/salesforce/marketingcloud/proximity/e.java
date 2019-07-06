package com.salesforce.marketingcloud.proximity;

import android.os.Parcelable;

public abstract class e implements Parcelable {
    public static e a(com.salesforce.marketingcloud.messages.e eVar) {
        try {
            return a(eVar.a(), eVar.d(), eVar.e(), eVar.f());
        } catch (Exception unused) {
            return null;
        }
    }

    public static e a(String str, String str2, int i, int i2) {
        return new d(str, str2, i, i2);
    }

    public abstract String a();

    public abstract String b();

    public abstract int c();

    public abstract int d();
}
