package com.etsy.android.ui.core.listingpanel;

/* compiled from: CanadianPostalCodeValidator */
public class c implements z {
    public int a() {
        return 7;
    }

    public boolean a(String str) {
        return str.matches("^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$");
    }
}
