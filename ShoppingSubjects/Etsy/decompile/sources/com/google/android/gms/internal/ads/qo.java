package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.tp.b;
import java.security.GeneralSecurityException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class qo {
    private static final Logger a = Logger.getLogger(qo.class.getName());
    private static final ConcurrentMap<String, qg> b = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Boolean> c = new ConcurrentHashMap();
    private static final ConcurrentMap<String, qa> d = new ConcurrentHashMap();

    public static <P> qa<P> a(String str) throws GeneralSecurityException {
        String valueOf;
        String str2;
        if (str == null) {
            throw new IllegalArgumentException("catalogueName must be non-null.");
        }
        qa<P> qaVar = (qa) d.get(str.toLowerCase());
        if (qaVar != null) {
            return qaVar;
        }
        String format = String.format("no catalogue found for %s. ", new Object[]{str});
        if (str.toLowerCase().startsWith("tinkaead")) {
            format = String.valueOf(format).concat("Maybe call AeadConfig.init().");
        }
        if (str.toLowerCase().startsWith("tinkdeterministicaead")) {
            valueOf = String.valueOf(format);
            str2 = "Maybe call DeterministicAeadConfig.init().";
        } else if (str.toLowerCase().startsWith("tinkstreamingaead")) {
            valueOf = String.valueOf(format);
            str2 = "Maybe call StreamingAeadConfig.init().";
        } else if (str.toLowerCase().startsWith("tinkhybriddecrypt") || str.toLowerCase().startsWith("tinkhybridencrypt")) {
            valueOf = String.valueOf(format);
            str2 = "Maybe call HybridConfig.init().";
        } else if (str.toLowerCase().startsWith("tinkmac")) {
            valueOf = String.valueOf(format);
            str2 = "Maybe call MacConfig.init().";
        } else if (str.toLowerCase().startsWith("tinkpublickeysign") || str.toLowerCase().startsWith("tinkpublickeyverify")) {
            valueOf = String.valueOf(format);
            str2 = "Maybe call SignatureConfig.init().";
        } else {
            if (str.toLowerCase().startsWith("tink")) {
                valueOf = String.valueOf(format);
                str2 = "Maybe call TinkConfig.init().";
            }
            throw new GeneralSecurityException(format);
        }
        format = valueOf.concat(str2);
        throw new GeneralSecurityException(format);
    }

    public static <P> qm<P> a(qh qhVar, qg<P> qgVar) throws GeneralSecurityException {
        qp.b(qhVar.a());
        qm<P> qmVar = new qm<>();
        for (b bVar : qhVar.a().b()) {
            if (bVar.c() == zzaxl.ENABLED) {
                qn a2 = qmVar.a(a(bVar.b().a(), bVar.b().b()), bVar);
                if (bVar.d() == qhVar.a().a()) {
                    qmVar.a(a2);
                }
            }
        }
        return qmVar;
    }

    public static <P> yk a(String str, yk ykVar) throws GeneralSecurityException {
        qg b2 = b(str);
        if (((Boolean) c.get(str)).booleanValue()) {
            return b2.b(ykVar);
        }
        String str2 = "newKey-operation not permitted for key type ";
        String valueOf = String.valueOf(str);
        throw new GeneralSecurityException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }

    public static <P> zzaxi a(tl tlVar) throws GeneralSecurityException {
        qg b2 = b(tlVar.a());
        if (((Boolean) c.get(tlVar.a())).booleanValue()) {
            return b2.c(tlVar.b());
        }
        String str = "newKey-operation not permitted for key type ";
        String valueOf = String.valueOf(tlVar.a());
        throw new GeneralSecurityException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    private static <P> P a(String str, zzbah zzbah) throws GeneralSecurityException {
        return b(str).a(zzbah);
    }

    public static <P> P a(String str, byte[] bArr) throws GeneralSecurityException {
        return a(str, zzbah.zzo(bArr));
    }

    public static synchronized <P> void a(String str, qa<P> qaVar) throws GeneralSecurityException {
        synchronized (qo.class) {
            if (d.containsKey(str.toLowerCase())) {
                if (!qaVar.getClass().equals(((qa) d.get(str.toLowerCase())).getClass())) {
                    Logger logger = a;
                    Level level = Level.WARNING;
                    String str2 = "com.google.crypto.tink.Registry";
                    String str3 = "addCatalogue";
                    String str4 = "Attempted overwrite of a catalogueName catalogue for name ";
                    String valueOf = String.valueOf(str);
                    logger.logp(level, str2, str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
                    StringBuilder sb = new StringBuilder(47 + String.valueOf(str).length());
                    sb.append("catalogue for name ");
                    sb.append(str);
                    sb.append(" has been already registered");
                    throw new GeneralSecurityException(sb.toString());
                }
            }
            d.put(str.toLowerCase(), qaVar);
        }
    }

    public static <P> void a(String str, qg<P> qgVar) throws GeneralSecurityException {
        a(str, qgVar, true);
    }

    public static synchronized <P> void a(String str, qg<P> qgVar, boolean z) throws GeneralSecurityException {
        synchronized (qo.class) {
            if (qgVar == null) {
                try {
                    throw new IllegalArgumentException("key manager must be non-null.");
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                if (b.containsKey(str)) {
                    qg b2 = b(str);
                    boolean booleanValue = ((Boolean) c.get(str)).booleanValue();
                    if (!qgVar.getClass().equals(b2.getClass()) || (!booleanValue && z)) {
                        Logger logger = a;
                        Level level = Level.WARNING;
                        String str2 = "com.google.crypto.tink.Registry";
                        String str3 = "registerKeyManager";
                        String str4 = "Attempted overwrite of a registered key manager for key type ";
                        String valueOf = String.valueOf(str);
                        logger.logp(level, str2, str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
                        throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", new Object[]{str, b2.getClass().getName(), qgVar.getClass().getName()}));
                    }
                }
                b.put(str, qgVar);
                c.put(str, Boolean.valueOf(z));
            }
        }
    }

    private static <P> qg<P> b(String str) throws GeneralSecurityException {
        qg<P> qgVar = (qg) b.get(str);
        if (qgVar != null) {
            return qgVar;
        }
        StringBuilder sb = new StringBuilder(78 + String.valueOf(str).length());
        sb.append("No key manager found for key type: ");
        sb.append(str);
        sb.append(".  Check the configuration of the registry.");
        throw new GeneralSecurityException(sb.toString());
    }

    public static <P> yk b(tl tlVar) throws GeneralSecurityException {
        qg b2 = b(tlVar.a());
        if (((Boolean) c.get(tlVar.a())).booleanValue()) {
            return b2.b(tlVar.b());
        }
        String str = "newKey-operation not permitted for key type ";
        String valueOf = String.valueOf(tlVar.a());
        throw new GeneralSecurityException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    public static <P> P b(String str, yk ykVar) throws GeneralSecurityException {
        return b(str).a(ykVar);
    }
}
