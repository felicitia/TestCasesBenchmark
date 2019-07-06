package com.etsy.android.vespa.a;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.vespa.b;

/* compiled from: WebUrlClickHandler */
public class g extends b<String> {
    public g(FragmentActivity fragmentActivity, @NonNull com.etsy.android.lib.logger.b bVar) {
        super(fragmentActivity, bVar);
    }

    public void a(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.putExtra("com.android.browser.application_id", d().getPackageName());
        try {
            d().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            com.etsy.android.lib.logger.legacy.b.a().a("WebUrlClickHandler", e.getMessage());
        }
    }
}
