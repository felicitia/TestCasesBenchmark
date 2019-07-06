package com.salesforce.marketingcloud.e;

import android.content.Context;
import android.support.annotation.NonNull;
import java.security.GeneralSecurityException;
import java.text.NumberFormat;

public class e extends a {
    public e(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3) {
        super(context, str, str2, str3, 10000);
    }

    /* access modifiers changed from: protected */
    public final String a(@NonNull Context context, @NonNull String str) {
        int i;
        String string = context.getSharedPreferences("ETPush", 0).getString("install_date_enc", null);
        if (string == null) {
            throw new GeneralSecurityException("Unable to get old salt.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("29200FA5-DF79-4C3F-BC0F-E2FF3CE6199A");
        try {
            i = NumberFormat.getInstance().parse(g.b(sb.toString())).intValue();
        } catch (Exception unused) {
            i = 200;
        }
        return g.b(string.substring(Integer.valueOf(String.valueOf(i).substring(0, 1)).intValue()));
    }
}
