package com.etsy.android.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.R;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.EtsyCurrency;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;
import com.etsy.android.ui.user.CurrencySelectFragment.a;
import com.etsy.android.uikit.nav.FragmentNavigator.AnimationMode;
import java.util.HashMap;
import java.util.Locale;

public class CurrencySelectionActivity extends BOENavDrawerActivity {
    public static final String EXTRA_CURRENCY_CODE = "curr_code";
    public static final String EXTRA_CURRENCY_SYMBOL = "curr_symbol";

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(R.string.prefs_set_currency_title);
        e.a((FragmentActivity) this).f().a(AnimationMode.FADE).a((a) new a() {
            public void a(EtsyCurrency etsyCurrency) {
                final Locale locale = Locale.getDefault();
                CurrencySelectionActivity.this.getAnalyticsContext().a("set_locale_preferences", new HashMap<AnalyticsLogAttribute, Object>() {
                    {
                        put(AnalyticsLogAttribute.LANGUAGE, locale.getLanguage());
                        put(AnalyticsLogAttribute.CURRENCY, CurrencyUtil.c());
                    }
                });
                Intent intent = CurrencySelectionActivity.this.getIntent();
                intent.putExtra(CurrencySelectionActivity.EXTRA_CURRENCY_CODE, etsyCurrency.getUnit().getCurrencyCode());
                intent.putExtra(CurrencySelectionActivity.EXTRA_CURRENCY_SYMBOL, etsyCurrency.getUnit().getCurrencySymbol());
                CurrencySelectionActivity.this.setResult(-1, intent);
                CurrencySelectionActivity.this.finish();
            }

            public void a() {
                CurrencySelectionActivity.this.setResult(0);
                CurrencySelectionActivity.this.finish();
            }
        });
    }
}
