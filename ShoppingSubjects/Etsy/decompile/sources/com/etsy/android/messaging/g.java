package com.etsy.android.messaging;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.nav.NotificationActivity;

/* compiled from: NotificationHandler */
public class g {
    private final Context a;

    public g(Context context) {
        this.a = context;
    }

    public Intent a(@Nullable Uri uri, Bundle bundle) {
        Intent intent = new Intent(this.a, NotificationActivity.class);
        intent.setAction("com.etsy.android.action.NOTIFICATION");
        intent.setFlags(872415232);
        if (bundle.containsKey(ResponseConstants.API_PATH)) {
            intent.setData(a(bundle));
        } else if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtras(bundle);
        return intent;
    }

    private Uri a(Bundle bundle) {
        String string = bundle.getString(ResponseConstants.API_PATH, "");
        String string2 = bundle.getString("title", "");
        String string3 = bundle.getString(ResponseConstants.EVENT_NAME, "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        if (!string.startsWith("/")) {
            StringBuilder sb = new StringBuilder();
            sb.append("/");
            sb.append(string);
            string = sb.toString();
        }
        return new Builder().scheme("etsy").authority("listing-landing-page").appendQueryParameter(ResponseConstants.API_PATH, string).appendQueryParameter("title", string2).appendQueryParameter(ResponseConstants.EVENT_NAME, string3).build();
    }
}
