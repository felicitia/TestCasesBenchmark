package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.Provider;
import javax.crypto.Cipher;

public final class ux implements uv<Cipher> {
    public final /* synthetic */ Object a(String str, Provider provider) throws GeneralSecurityException {
        return provider == null ? Cipher.getInstance(str) : Cipher.getInstance(str, provider);
    }
}
