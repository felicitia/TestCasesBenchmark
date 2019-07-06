package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

final class ri {
    public static zzayv a(zzawy zzawy) throws GeneralSecurityException {
        switch (rj.b[zzawy.ordinal()]) {
            case 1:
                return zzayv.NIST_P256;
            case 2:
                return zzayv.NIST_P384;
            case 3:
                return zzayv.NIST_P521;
            default:
                String valueOf = String.valueOf(zzawy);
                StringBuilder sb = new StringBuilder(20 + String.valueOf(valueOf).length());
                sb.append("unknown curve type: ");
                sb.append(valueOf);
                throw new GeneralSecurityException(sb.toString());
        }
    }

    public static zzayw a(zzawk zzawk) throws GeneralSecurityException {
        switch (rj.c[zzawk.ordinal()]) {
            case 1:
                return zzayw.UNCOMPRESSED;
            case 2:
                return zzayw.COMPRESSED;
            default:
                String valueOf = String.valueOf(zzawk);
                StringBuilder sb = new StringBuilder(22 + String.valueOf(valueOf).length());
                sb.append("unknown point format: ");
                sb.append(valueOf);
                throw new GeneralSecurityException(sb.toString());
        }
    }

    public static String a(zzaxa zzaxa) throws NoSuchAlgorithmException {
        switch (rj.a[zzaxa.ordinal()]) {
            case 1:
                return "HmacSha1";
            case 2:
                return "HmacSha256";
            case 3:
                return "HmacSha512";
            default:
                String valueOf = String.valueOf(zzaxa);
                StringBuilder sb = new StringBuilder(27 + String.valueOf(valueOf).length());
                sb.append("hash unsupported for HMAC: ");
                sb.append(valueOf);
                throw new NoSuchAlgorithmException(sb.toString());
        }
    }

    public static void a(sr srVar) throws GeneralSecurityException {
        ur.a(a(srVar.a().a()));
        a(srVar.a().b());
        if (srVar.c() == zzawk.UNKNOWN_FORMAT) {
            throw new GeneralSecurityException("unknown EC point format");
        }
        qo.a(srVar.b().a());
    }
}
