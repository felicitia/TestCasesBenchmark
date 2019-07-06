package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public final class qj {
    private static final CopyOnWriteArrayList<qi> a = new CopyOnWriteArrayList<>();

    public static qi a(String str) throws GeneralSecurityException {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            qi qiVar = (qi) it.next();
            if (qiVar.a(str)) {
                return qiVar;
            }
        }
        String str2 = "No KMS client does support: ";
        String valueOf = String.valueOf(str);
        throw new GeneralSecurityException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }
}
