package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.Provider;

public final class uz implements uv<KeyFactory> {
    public final /* synthetic */ Object a(String str, Provider provider) throws GeneralSecurityException {
        return provider == null ? KeyFactory.getInstance(str) : KeyFactory.getInstance(str, provider);
    }
}
