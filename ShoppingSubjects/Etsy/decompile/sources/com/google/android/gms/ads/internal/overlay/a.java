package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hd;

@bu
public final class a {
    private static boolean a(Context context, Intent intent, k kVar) {
        String str = "Launching an intent: ";
        try {
            String valueOf = String.valueOf(intent.toURI());
            gv.a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            ao.e();
            hd.a(context, intent);
            if (kVar != null) {
                kVar.zzbl();
            }
            return true;
        } catch (ActivityNotFoundException e) {
            gv.e(e.getMessage());
            return false;
        }
    }

    public static boolean a(Context context, zzc zzc, k kVar) {
        int i;
        String str;
        if (zzc == null) {
            str = "No intent data for launcher overlay.";
        } else {
            akl.a(context);
            if (zzc.intent != null) {
                return a(context, zzc.intent, kVar);
            }
            Intent intent = new Intent();
            if (TextUtils.isEmpty(zzc.url)) {
                str = "Open GMSG did not contain a URL.";
            } else {
                if (!TextUtils.isEmpty(zzc.mimeType)) {
                    intent.setDataAndType(Uri.parse(zzc.url), zzc.mimeType);
                } else {
                    intent.setData(Uri.parse(zzc.url));
                }
                intent.setAction("android.intent.action.VIEW");
                if (!TextUtils.isEmpty(zzc.packageName)) {
                    intent.setPackage(zzc.packageName);
                }
                if (!TextUtils.isEmpty(zzc.zzbxj)) {
                    String[] split = zzc.zzbxj.split("/", 2);
                    if (split.length < 2) {
                        String str2 = "Could not parse component name from open GMSG: ";
                        String valueOf = String.valueOf(zzc.zzbxj);
                        gv.e(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                        return false;
                    }
                    intent.setClassName(split[0], split[1]);
                }
                String str3 = zzc.zzbxk;
                if (!TextUtils.isEmpty(str3)) {
                    try {
                        i = Integer.parseInt(str3);
                    } catch (NumberFormatException unused) {
                        gv.e("Could not parse intent flags.");
                        i = 0;
                    }
                    intent.addFlags(i);
                }
                if (((Boolean) ajh.f().a(akl.cN)).booleanValue()) {
                    intent.addFlags(ErrorDialogData.BINDER_CRASH);
                    intent.putExtra("android.support.customtabs.extra.user_opt_out", true);
                } else {
                    if (((Boolean) ajh.f().a(akl.cM)).booleanValue()) {
                        ao.e();
                        hd.b(context, intent);
                    }
                }
                return a(context, intent, kVar);
            }
        }
        gv.e(str);
        return false;
    }
}
