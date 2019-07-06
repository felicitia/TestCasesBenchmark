package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.Provider;

public final class vc implements uv<MessageDigest> {
    public final /* synthetic */ Object a(String str, Provider provider) throws GeneralSecurityException {
        return provider == null ? MessageDigest.getInstance(str) : MessageDigest.getInstance(str, provider);
    }
}
