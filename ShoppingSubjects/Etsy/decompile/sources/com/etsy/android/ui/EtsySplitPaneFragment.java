package com.etsy.android.ui;

import android.support.v4.app.Fragment;
import com.etsy.android.ui.nav.b;
import com.etsy.android.ui.nav.c;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.core.BaseSplitPaneFragment;

public abstract class EtsySplitPaneFragment<T> extends BaseSplitPaneFragment<T, b, c> {
    public e getNav() {
        return e.a((Fragment) this);
    }
}
