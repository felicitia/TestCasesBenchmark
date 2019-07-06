package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;

public class GoogleSignatureVerifier {
    private static GoogleSignatureVerifier zzbv;
    private final Context mContext;

    private GoogleSignatureVerifier(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static GoogleSignatureVerifier getInstance(Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (zzbv == null) {
                GoogleCertificates.init(context);
                zzbv = new GoogleSignatureVerifier(context);
            }
        }
        return zzbv;
    }

    private static CertData zza(PackageInfo packageInfo, CertData... certDataArr) {
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzb zzb = new zzb(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < certDataArr.length; i++) {
            if (certDataArr[i].equals(zzb)) {
                return certDataArr[i];
            }
        }
        return null;
    }

    private final zzg zza(PackageInfo packageInfo) {
        String str;
        boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
        if (packageInfo == null) {
            str = "null pkg";
        } else if (packageInfo.signatures.length != 1) {
            str = "single cert required";
        } else {
            zzb zzb = new zzb(packageInfo.signatures[0].toByteArray());
            String str2 = packageInfo.packageName;
            zzg zza = GoogleCertificates.zza(str2, zzb, honorsDebugCertificates);
            if (!zza.zzbl || packageInfo.applicationInfo == null || (packageInfo.applicationInfo.flags & 2) == 0 || (honorsDebugCertificates && !GoogleCertificates.zza(str2, zzb, false).zzbl)) {
                return zza;
            }
            str = "debuggable release cert app rejected";
        }
        return zzg.zze(str);
    }

    private final zzg zzb(int i) {
        String[] packagesForUid = Wrappers.packageManager(this.mContext).getPackagesForUid(i);
        if (packagesForUid == null || packagesForUid.length == 0) {
            return zzg.zze("no pkgs");
        }
        zzg zzg = null;
        for (String zzf : packagesForUid) {
            zzg = zzf(zzf);
            if (zzg.zzbl) {
                return zzg;
            }
        }
        return zzg;
    }

    private final zzg zzf(String str) {
        try {
            return zza(Wrappers.packageManager(this.mContext).getPackageInfo(str, 64));
        } catch (NameNotFoundException unused) {
            String str2 = "no pkg ";
            String valueOf = String.valueOf(str);
            return zzg.zze(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
    }

    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (isGooglePublicSignedPackage(packageInfo, false)) {
            return true;
        }
        if (isGooglePublicSignedPackage(packageInfo, true)) {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
        return false;
    }

    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo, boolean z) {
        if (!(packageInfo == null || packageInfo.signatures == null)) {
            if (zza(packageInfo, z ? zzd.zzbg : new CertData[]{zzd.zzbg[0]}) != null) {
                return true;
            }
        }
        return false;
    }

    public boolean isUidGoogleSigned(int i) {
        zzg zzb = zzb(i);
        zzb.zzi();
        return zzb.zzbl;
    }
}
