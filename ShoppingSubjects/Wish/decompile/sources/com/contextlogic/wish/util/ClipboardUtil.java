package com.contextlogic.wish.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import com.contextlogic.wish.application.WishApplication;

public class ClipboardUtil {
    public static boolean copyToClipboard(CharSequence charSequence) {
        return copyToClipboard(charSequence, charSequence);
    }

    public static boolean copyToClipboard(CharSequence charSequence, CharSequence charSequence2) {
        ClipboardManager clipboardManager = (ClipboardManager) WishApplication.getInstance().getSystemService("clipboard");
        if (clipboardManager == null) {
            return false;
        }
        clipboardManager.setPrimaryClip(ClipData.newPlainText(charSequence, charSequence2));
        return true;
    }
}
