package com.etsy.android.ui.core.listingpanel;

import com.etsy.android.lib.config.a;
import com.etsy.android.lib.config.b;
import java.util.Locale;

/* compiled from: PostalCodeValidatorFactory */
public class aa {
    private boolean a = a.a().d().c(b.cx);

    public z a(String str) {
        if (str.equals(Locale.US.getCountry())) {
            return new ac();
        }
        if (!this.a || !str.equals(Locale.CANADA.getCountry())) {
            return null;
        }
        return new c();
    }

    public y b(String str) {
        if (str.equals(Locale.US.getCountry())) {
            return new ab();
        }
        if (!this.a || !str.equals(Locale.CANADA.getCountry())) {
            return null;
        }
        return new b();
    }

    public x c(String str) {
        if (!this.a || !str.equals(Locale.CANADA.getCountry())) {
            return null;
        }
        return new a();
    }
}
