package com.etsy.android.ui.core.listingpanel;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

/* compiled from: CanadianPostalCodeFormatter */
public class a implements x {
    public String a(String str, int i) {
        if (i > str.length() || str.length() != 3) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        return sb.toString();
    }
}
