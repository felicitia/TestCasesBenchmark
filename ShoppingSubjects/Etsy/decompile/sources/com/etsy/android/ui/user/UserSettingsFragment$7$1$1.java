package com.etsy.android.ui.user;

import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.util.CurrencyUtil;
import java.util.HashMap;
import java.util.Locale;

class UserSettingsFragment$7$1$1 extends HashMap<AnalyticsLogAttribute, Object> {
    final /* synthetic */ AnonymousClass1 this$2;
    final /* synthetic */ Locale val$defaultLocale;

    UserSettingsFragment$7$1$1(AnonymousClass1 r1, Locale locale) {
        this.this$2 = r1;
        this.val$defaultLocale = locale;
        put(AnalyticsLogAttribute.LANGUAGE, this.val$defaultLocale.getLanguage());
        put(AnalyticsLogAttribute.CURRENCY, CurrencyUtil.c());
    }
}
