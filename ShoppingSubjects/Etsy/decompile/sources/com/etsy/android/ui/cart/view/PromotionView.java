package com.etsy.android.ui.cart.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.cart.Promotion;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class PromotionView extends LinearLayout {
    private View mBtnRemoveDiscount;
    private TextView mDiscountText;
    private TextView mPriceText;
    private boolean mShowPrice;

    public PromotionView(Context context) {
        super(context);
        init();
    }

    public PromotionView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PromotionView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @TargetApi(21)
    public PromotionView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_promotion, this).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
            }
        });
        this.mDiscountText = (TextView) findViewById(R.id.promotion_txt);
        this.mBtnRemoveDiscount = findViewById(R.id.btn_remove_promotion);
        this.mPriceText = (TextView) findViewById(R.id.txt_price);
    }

    public void bind(@NonNull Promotion promotion, @Nullable OnClickListener onClickListener) {
        StringBuilder sb = new StringBuilder();
        sb.append("<b>");
        sb.append(promotion.getCode());
        sb.append(":</b> ");
        sb.append(promotion.getDescription());
        Spanned fromHtml = Html.fromHtml(sb.toString());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(fromHtml);
        if (this.mShowPrice) {
            this.mPriceText.setVisibility(0);
            this.mPriceText.setText(promotion.getPrice().toString());
        }
        this.mDiscountText.setText(spannableStringBuilder);
        int i = 8;
        this.mPriceText.setVisibility(this.mShowPrice ? 0 : 8);
        View view = this.mBtnRemoveDiscount;
        if (onClickListener != null) {
            i = 0;
        }
        view.setVisibility(i);
        this.mBtnRemoveDiscount.setOnClickListener(onClickListener);
    }

    public void setShowPrice(boolean z) {
        this.mShowPrice = z;
    }
}
