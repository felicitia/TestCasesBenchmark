package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.Provider;
import javax.crypto.KeyAgreement;

public final class uy implements uv<KeyAgreement> {
    public final /* synthetic */ Object a(String str, Provider provider) throws GeneralSecurityException {
        return provider == null ? KeyAgreement.getInstance(str) : KeyAgreement.getInstance(str, provider);
    }
}
