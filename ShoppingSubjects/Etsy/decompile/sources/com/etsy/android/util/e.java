package com.etsy.android.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

/* compiled from: SOELauncherUtil */
public class e {
    public static void a(Context context, @NonNull b bVar, EtsyId etsyId) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("shares/");
            sb.append(etsyId.getId());
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append("sellonetsy://");
            sb3.append(sb2);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb3.toString()));
            intent.addFlags(ErrorDialogData.BINDER_CRASH);
            intent.putExtra("shop_share_id", etsyId.getId());
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setData(Uri.parse(bVar.c().b(com.etsy.android.lib.config.b.cu)));
            context.startActivity(intent2);
        }
    }
}
