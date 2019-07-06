package com.contextlogic.wish.util;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;

public class StoreUtil {
    private static Uri getPlayStoreUri(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("market://details?id=");
        sb.append(str);
        return Uri.parse(sb.toString());
    }

    public static Uri getStoreUri() {
        return getStoreUri(WishApplication.getInstance().getPackageName());
    }

    public static Uri getStoreUri(String str) {
        return getPlayStoreUri(str);
    }

    public static void startStoreActivity(BaseActivity baseActivity) {
        try {
            baseActivity.startActivity(new Intent("android.intent.action.VIEW", getStoreUri()));
        } catch (ActivityNotFoundException unused) {
            baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(baseActivity.getString(R.string.store_error)));
        }
    }
}
