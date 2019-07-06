package com.etsy.android.ui.core.listingpanel;

import android.support.annotation.StringRes;
import android.support.v4.app.FragmentTransaction;
import com.etsy.android.R;

/* compiled from: CanadianPostalCodeSettings */
public class b implements y {
    @StringRes
    public int a() {
        return R.string.shipping_postal_code_hint;
    }

    public int b() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }
}
