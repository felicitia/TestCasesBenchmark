package com.etsy.android.uikit.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.etsy.android.lib.config.a;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.logger.f;

/* compiled from: CleanupUtil */
public class c {
    private static final String a = f.a(c.class);

    public static void a(View view) {
        if (a.a().d().c(b.bb) && view != null) {
            try {
                if (view.getBackground() != null) {
                    view.getBackground().setCallback(null);
                }
                if (view instanceof AdapterView) {
                    if (!(view instanceof Spinner)) {
                        ((AdapterView) view).setOnItemClickListener(null);
                    }
                    ((AdapterView) view).setOnItemLongClickListener(null);
                    ((AdapterView) view).setOnItemSelectedListener(null);
                } else {
                    view.setOnTouchListener(null);
                    view.setOnClickListener(null);
                }
                if ((view instanceof ViewGroup) && !(view instanceof AdapterView)) {
                    for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                        a(((ViewGroup) view).getChildAt(i));
                    }
                    ((ViewGroup) view).removeAllViews();
                }
            } catch (Throwable unused) {
            }
        }
    }
}
