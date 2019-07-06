package com.contextlogic.wish.activity.productdetails.bundles;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;

public class BundlesExtendPriceView extends RelativeLayout {
    private ThemedTextView mPrice;
    private ThemedTextView mText;

    public BundlesExtendPriceView(Context context) {
        this(context, null);
    }

    public BundlesExtendPriceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.bundles_extend_price_view, this);
        this.mText = (ThemedTextView) inflate.findViewById(R.id.bundles_extend_price_view_text);
        this.mPrice = (ThemedTextView) inflate.findViewById(R.id.bundles_extend_price_view_price);
    }

    public void setup(ArrayList<WishProduct> arrayList, final BundlesView bundlesView) {
        setText(arrayList);
        setPrice(arrayList);
        int dimension = (int) getResources().getDimension(R.dimen.screen_padding);
        setPadding(dimension, dimension, dimension, dimension);
        setBackgroundResource(R.drawable.bundles_bottom_border);
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BUNDLES_EXPAND);
                bundlesView.extendView();
            }
        });
    }

    private void setText(ArrayList<WishProduct> arrayList) {
        if (arrayList.size() == 3) {
            this.mText.setText(getResources().getString(R.string.buy_all_three_colon));
        } else {
            this.mText.setText(getResources().getString(R.string.buy_both_colon));
        }
    }

    private void setPrice(ArrayList<WishProduct> arrayList) {
        WishLocalizedCurrencyValue commerceValue = ((WishProduct) arrayList.get(0)).getCommerceValue();
        for (int i = 1; i < arrayList.size(); i++) {
            commerceValue = commerceValue.add(((WishProduct) arrayList.get(i)).getCommerceValue());
        }
        if (commerceValue.getValue() > 0.0d) {
            this.mPrice.setText(commerceValue.toFormattedString());
        } else {
            this.mPrice.setText(getResources().getString(R.string.free));
        }
    }
}
