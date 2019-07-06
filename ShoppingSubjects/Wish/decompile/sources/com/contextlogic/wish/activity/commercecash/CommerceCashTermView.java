package com.contextlogic.wish.activity.commercecash;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.webview.WebViewActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCommerceCashTerms.Term;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.Iterator;

public class CommerceCashTermView extends LinearLayout {
    private static String TERMS_PLACEHOLDER = "<terms_link>";
    private LinearLayout mTermContainer;
    private ThemedTextView mTermTitleText;

    public CommerceCashTermView(Context context) {
        this(context, null);
    }

    public CommerceCashTermView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        ((LayoutInflater) WishApplication.getInstance().getSystemService("layout_inflater")).inflate(R.layout.commerce_cash_term_view, this, true);
        this.mTermContainer = (LinearLayout) findViewById(R.id.commerce_cash_term_view_container);
        this.mTermTitleText = (ThemedTextView) findViewById(R.id.commerce_cash_term_view_title);
    }

    public void setup(Term term, int i) {
        if (i > 0) {
            LayoutParams layoutParams = (LayoutParams) this.mTermContainer.getLayoutParams();
            layoutParams.setMargins(0, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.commerce_cash_term_padding), 0, 0);
            this.mTermContainer.setLayoutParams(layoutParams);
        }
        this.mTermTitleText.setText(term.getTitle());
        Resources resources = WishApplication.getInstance().getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.eight_padding);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.sixteen_padding);
        Iterator it = term.getParagraphs().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            ThemedTextView themedTextView = new ThemedTextView(getContext());
            if (i2 == 0) {
                themedTextView.setPadding(0, dimensionPixelSize, 0, 0);
            } else {
                themedTextView.setPadding(0, dimensionPixelSize2, 0, 0);
            }
            SpannableString processParagraph = processParagraph(str);
            themedTextView.setTextSize(0, (float) resources.getDimensionPixelSize(R.dimen.text_size_body));
            themedTextView.setText(processParagraph);
            themedTextView.setMovementMethod(LinkMovementMethod.getInstance());
            themedTextView.setLinkTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
            this.mTermContainer.addView(themedTextView);
            i2++;
        }
    }

    private SpannableString processParagraph(String str) {
        final String string = WishApplication.getInstance().getResources().getString(R.string.terms_of_use);
        int indexOf = str.indexOf(TERMS_PLACEHOLDER);
        if (indexOf == -1) {
            return new SpannableString(str);
        }
        SpannableString spannableString = new SpannableString(str.replace(TERMS_PLACEHOLDER, string));
        spannableString.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_TERMS_COMMERCE_CASH_TERMS_VIEW);
                Intent intent = new Intent();
                intent.setClass(CommerceCashTermView.this.getContext(), WebViewActivity.class);
                intent.putExtra("ExtraUrl", WebViewActivity.getTermsUrl());
                intent.putExtra("ExtraActionBarTitle", string);
                CommerceCashTermView.this.getContext().startActivity(intent);
            }
        }, indexOf, string.length() + indexOf, 33);
        return spannableString;
    }
}
