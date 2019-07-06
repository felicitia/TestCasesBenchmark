package com.contextlogic.wish.activity.buyerguarantee;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.returnpolicy.ReturnPolicyActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.PageItemHolder;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class BuyerGuaranteeItemRowView extends LinearLayout implements ImageRestorable {
    private ThemedTextView mAction;
    private ThemedTextView mBody;
    /* access modifiers changed from: private */
    public BaseFragment mFragment;
    private NetworkImageView mImage;
    private ThemedTextView mTitle;

    public BuyerGuaranteeItemRowView(Context context, BaseFragment baseFragment) {
        super(context);
        this.mFragment = baseFragment;
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.buyer_guarantee_item_row_view, this);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.buyer_guarantee_row_subtitle);
        this.mBody = (ThemedTextView) inflate.findViewById(R.id.buyer_guarantee_row_body);
        this.mImage = (NetworkImageView) inflate.findViewById(R.id.buyer_guarantee_row_image);
        this.mAction = (ThemedTextView) inflate.findViewById(R.id.buyer_guarantee_row_action);
    }

    public void setup(PageItemHolder pageItemHolder) {
        if (pageItemHolder != null) {
            this.mTitle.setText(pageItemHolder.getTitle());
            this.mBody.setText(pageItemHolder.getBody());
            this.mImage.setImageUrl(pageItemHolder.getImgUrl());
            if (pageItemHolder.getAction() == null || pageItemHolder.getAction().isEmpty()) {
                this.mAction.setVisibility(8);
            } else {
                this.mAction.setVisibility(0);
                this.mAction.setText(pageItemHolder.getAction());
                setActionClickListener();
            }
        }
    }

    private void setActionClickListener() {
        this.mAction.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BuyerGuaranteeItemRowView.this.mFragment.withActivity(new ActivityTask<BuyerGuaranteeActivity>() {
                    public void performTask(BuyerGuaranteeActivity buyerGuaranteeActivity) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BUYER_GUARANTEE_VIEW_WEB_RETURN_POLICY);
                        Intent intent = new Intent();
                        intent.setClass(BuyerGuaranteeItemRowView.this.getContext(), ReturnPolicyActivity.class);
                        buyerGuaranteeActivity.startActivity(intent);
                    }
                });
            }
        });
    }

    public void releaseImages() {
        if (this.mImage != null) {
            this.mImage.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mImage != null) {
            this.mImage.restoreImages();
        }
    }
}
