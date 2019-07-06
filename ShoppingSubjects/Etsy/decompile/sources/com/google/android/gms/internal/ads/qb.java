package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.tn.a;

public final class qb {
    public static tn a(String str, String str2, String str3, int i, boolean z) {
        a a = tn.f().a(str2);
        String str4 = "type.googleapis.com/google.crypto.tink.";
        String valueOf = String.valueOf(str3);
        return (tn) a.b(valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4)).a(0).a(true).c(str).c();
    }
}
