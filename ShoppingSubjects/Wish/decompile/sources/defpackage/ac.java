package defpackage;

import android.icu.util.TimeZone;
import android.os.Build.VERSION;
import java.util.Currency;
import java.util.Locale;

/* renamed from: ac reason: default package */
/* compiled from: GA */
public final class ac extends q {
    private final int b;

    public ac(int i) {
        this.b = i;
    }

    public final void c() {
        switch (this.b) {
            case 0:
                super.a(Locale.getDefault().getCountry());
                return;
            case 1:
                super.a(Locale.getDefault().getLanguage());
                return;
            case 2:
                if (VERSION.SDK_INT >= 24) {
                    super.a(TimeZone.getDefault().getDisplayName(true, 0));
                    return;
                } else {
                    super.a(java.util.TimeZone.getDefault().getDisplayName(true, 0));
                    return;
                }
            case 3:
                if (VERSION.SDK_INT < 24) {
                    super.a(Currency.getInstance(Locale.getDefault()).getCurrencyCode());
                    break;
                } else {
                    super.a(android.icu.util.Currency.getInstance(Locale.getDefault()).getCurrencyCode());
                    return;
                }
        }
    }
}
