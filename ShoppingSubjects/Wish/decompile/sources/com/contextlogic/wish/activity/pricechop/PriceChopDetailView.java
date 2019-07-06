package com.contextlogic.wish.activity.pricechop;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.activity.productdetails.ProductDetailsFragment;
import com.contextlogic.wish.activity.productdetails.ProductDetailsServiceFragment;
import com.contextlogic.wish.activity.webview.WebViewActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.PriceChopProductDetail;
import com.contextlogic.wish.util.DrawableUtil;
import com.contextlogic.wish.util.Truss;

public class PriceChopDetailView extends ConstraintLayout {
    private TextView mBodyText;
    private ProductDetailsFragment mFragment;
    private ViewGroup mPriceChopButton;
    private PriceChopStatusBarView mPriceChopStatusBarView;
    private TextView mTitleText;

    public PriceChopDetailView(Context context) {
        super(context);
        init();
    }

    public PriceChopDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PriceChopDetailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.price_chop_detail_view, this, true);
        this.mTitleText = (TextView) findViewById(R.id.price_chop_detail_title);
        this.mBodyText = (TextView) findViewById(R.id.price_chop_detail_body);
        this.mPriceChopStatusBarView = (PriceChopStatusBarView) findViewById(R.id.price_chop_detail_status_bar);
        this.mPriceChopButton = (ViewGroup) findViewById(R.id.price_chop_detail_chop_button);
        DrawableUtil.tintCompoundDrawables((TextView) findViewById(R.id.price_chop_detail_caption), ContextCompat.getColor(getContext(), R.color.gray3));
    }

    public void setup(ProductDetailsFragment productDetailsFragment, final String str, final PriceChopProductDetail priceChopProductDetail) {
        this.mFragment = productDetailsFragment;
        this.mTitleText.setText(priceChopProductDetail.getTitle());
        this.mBodyText.setMovementMethod(LinkMovementMethod.getInstance());
        this.mBodyText.setText(createBodyText(priceChopProductDetail));
        this.mPriceChopStatusBarView.setup(priceChopProductDetail.getPriceChopStatuses());
        this.mPriceChopButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PriceChopDetailView.this.handleShare(str, priceChopProductDetail);
            }
        });
    }

    private CharSequence createBodyText(PriceChopProductDetail priceChopProductDetail) {
        return new Truss().append(priceChopProductDetail.getBody()).append(" ").pushSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.main_primary))).pushSpan(new ClickableSpan() {
            public void onClick(View view) {
                PriceChopDetailView.this.handleLearnMore();
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        }).append(getResources().getString(R.string.learn_more)).build();
    }

    /* access modifiers changed from: private */
    public void handleLearnMore() {
        Intent intent = new Intent();
        intent.setClass(getContext(), WebViewActivity.class);
        intent.putExtra("ExtraUrl", WebViewActivity.getPriceChopRules());
        intent.putExtra("ExtraHideActionBarItems", true);
        intent.putExtra("ExtraActionBarTitle", getResources().getString(R.string.price_chop_rules));
        getContext().startActivity(intent);
    }

    /* access modifiers changed from: private */
    public void handleShare(String str, PriceChopProductDetail priceChopProductDetail) {
        ((ProductDetailsServiceFragment) ((ProductDetailsActivity) this.mFragment.getBaseActivity()).getServiceFragment()).createAndSharePriceChop(str, priceChopProductDetail);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PRICE_CHOP_DETAIL_SHARE);
    }
}
