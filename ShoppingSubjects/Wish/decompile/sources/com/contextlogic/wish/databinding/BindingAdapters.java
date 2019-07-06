package com.contextlogic.wish.databinding;

import android.widget.TextView;
import com.contextlogic.wish.util.DrawableUtil;

public final class BindingAdapters {
    public static void tintCompoundDrawables(TextView textView, int i) {
        DrawableUtil.tintCompoundDrawables(textView, i);
    }
}
