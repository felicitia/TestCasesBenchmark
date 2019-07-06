package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.uv;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;

public final class uu<T_WRAPPER extends uv<T_ENGINE>, T_ENGINE> {
    public static final uu<ux, Cipher> a = new uu<>(new ux());
    public static final uu<vb, Mac> b = new uu<>(new vb());
    public static final uu<uy, KeyAgreement> c = new uu<>(new uy());
    public static final uu<va, KeyPairGenerator> d = new uu<>(new va());
    public static final uu<uz, KeyFactory> e = new uu<>(new uz());
    private static final Logger f = Logger.getLogger(uu.class.getName());
    private static final List<Provider> g;
    private static final uu<vd, Signature> h = new uu<>(new vd());
    private static final uu<vc, MessageDigest> i = new uu<>(new vc());
    private T_WRAPPER j;
    private List<Provider> k = g;
    private boolean l = true;

    static {
        if (vm.a()) {
            String[] strArr = {"GmsCore_OpenSSL", "AndroidOpenSSL"};
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < 2; i2++) {
                String str = strArr[i2];
                Provider provider = Security.getProvider(str);
                if (provider != null) {
                    arrayList.add(provider);
                } else {
                    f.logp(Level.INFO, "com.google.crypto.tink.subtle.EngineFactory", "toProviderList", String.format("Provider %s not available", new Object[]{str}));
                }
            }
            g = arrayList;
        } else {
            g = new ArrayList();
        }
    }

    private uu(T_WRAPPER t_wrapper) {
        this.j = t_wrapper;
    }

    private final boolean a(String str, Provider provider) {
        try {
            this.j.a(str, provider);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public final T_ENGINE a(String str) throws GeneralSecurityException {
        T_WRAPPER t_wrapper;
        Provider provider;
        Iterator it = this.k.iterator();
        while (true) {
            if (it.hasNext()) {
                provider = (Provider) it.next();
                if (a(str, provider)) {
                    t_wrapper = this.j;
                    break;
                }
            } else if (this.l) {
                t_wrapper = this.j;
                provider = null;
            } else {
                throw new GeneralSecurityException("No good Provider found.");
            }
        }
        return t_wrapper.a(str, provider);
    }
}
