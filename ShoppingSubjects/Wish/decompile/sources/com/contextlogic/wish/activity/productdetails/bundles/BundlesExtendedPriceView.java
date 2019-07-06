package com.contextlogic.wish.activity.productdetails.bundles;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;

public class BundlesExtendedPriceView extends RelativeLayout implements BundlesViewConnector {
    private ArrayList<WishProduct> mBundledProducts;
    private ThemedButton mBuyButton;
    private View mLayout;
    private int mNumberOfSelectedProducts;
    private ThemedTextView mPriceTextView;
    private WishLocalizedCurrencyValue mTotalPrice;
    private ThemedTextView mTotalPriceTextView;

    public BundlesExtendedPriceView(Context context) {
        this(context, null);
    }

    public BundlesExtendedPriceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public void init() {
        this.mLayout = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.bundles_extended_price_view, this);
    }

    public void setup(final BundlesView bundlesView, ArrayList<WishProduct> arrayList) {
        this.mTotalPriceTextView = (ThemedTextView) this.mLayout.findViewById(R.id.bundles_extended_price_view_total_price_text);
        this.mPriceTextView = (ThemedTextView) this.mLayout.findViewById(R.id.bundles_extended_price_view_price);
        this.mBuyButton = (ThemedButton) this.mLayout.findViewById(R.id.bundles_extended_price_view_buy_button);
        this.mBuyButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                bundlesView.handleBuy();
            }
        });
        this.mBundledProducts = arrayList;
        this.mNumberOfSelectedProducts = arrayList.size();
        setBackgroundResource(R.drawable.bundles_extended_bottom_border);
        updateUi(arrayList);
    }

    public void updateUi(ArrayList<WishProduct> arrayList) {
        setBuyButtonText();
        setInitialPrice(arrayList);
    }

    private void setBuyButtonText() {
        if (this.mNumberOfSelectedProducts > 0) {
            this.mBuyButton.setEnabled(true);
        } else {
            this.mBuyButton.setEnabled(false);
        }
        switch (this.mNumberOfSelectedProducts) {
            case 0:
                return;
            case 1:
                this.mBuyButton.setText(R.string.buy);
                return;
            case 2:
                this.mBuyButton.setText(R.string.buy_both);
                return;
            case 3:
                this.mBuyButton.setText(R.string.buy_all_three);
                return;
            default:
                this.mBuyButton.setText(R.string.buy);
                return;
        }
    }

    private void setPriceText() {
        int i = 8;
        this.mTotalPriceTextView.setVisibility(this.mTotalPrice.getValue() > 0.0d ? 0 : 8);
        ThemedTextView themedTextView = this.mPriceTextView;
        if (this.mTotalPrice.getValue() > 0.0d) {
            i = 0;
        }
        themedTextView.setVisibility(i);
        this.mPriceTextView.setText(this.mTotalPrice.toFormattedString());
    }

    private void setInitialPrice(ArrayList<WishProduct> arrayList) {
        this.mTotalPrice = ((WishProduct) arrayList.get(0)).getCommerceValue();
        for (int i = 1; i < arrayList.size(); i++) {
            this.mTotalPrice = this.mTotalPrice.add(((WishProduct) arrayList.get(i)).getCommerceValue());
        }
        setPriceText();
    }

    public void handleCheckboxTapped(int i, boolean z) {
        if (z) {
            this.mTotalPrice = this.mTotalPrice.add(((WishProduct) this.mBundledProducts.get(i)).getCommerceValue());
            this.mNumberOfSelectedProducts++;
        } else {
            this.mTotalPrice = this.mTotalPrice.subtract(((WishProduct) this.mBundledProducts.get(i)).getCommerceValue());
            this.mNumberOfSelectedProducts--;
        }
        setPriceText();
        setBuyButtonText();
    }
}
