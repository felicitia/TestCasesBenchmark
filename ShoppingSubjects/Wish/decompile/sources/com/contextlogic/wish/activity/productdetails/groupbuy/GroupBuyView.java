package com.contextlogic.wish.activity.productdetails.groupbuy;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.textviewer.TextViewerActivity;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishGroupBuyInfo;
import com.contextlogic.wish.api.model.WishGroupBuyRowInfo;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.Recyclable;
import java.util.ArrayList;

public class GroupBuyView extends LinearLayout implements ImageRestorable, Recyclable {
    private LinearLayout mContainer;
    private ThemedTextView mDescriptionView;
    private ThemedTextView mExtraDescriptionView;
    private ThemedTextView mExtraLearnMore;
    private ArrayList<GroupBuyItemView> mGroupBuyItemViews;
    private ThemedTextView mListPrice;
    private ThemedTextView mListPriceSwapped;
    private LinearLayout mPriceContainer;
    private LinearLayout mPriceContainerSwapped;
    private WishProduct mProduct;
    private View mRootLayout;
    private View mSpacerView;
    private ThemedTextView mSubtitle;
    private ThemedTextView mYourPrice;
    private ThemedTextView mYourPriceSwapped;

    public interface BuyCallback {
        void onBuy(String str);

        void onCreate();
    }

    public GroupBuyView(Context context) {
        this(context, null);
    }

    public GroupBuyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public GroupBuyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void init() {
        this.mRootLayout = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.group_buy_view, this);
        this.mContainer = (LinearLayout) this.mRootLayout.findViewById(R.id.group_buy_view_items_container);
        setOrientation(1);
        setGravity(17);
        setLayoutParams(new LayoutParams(-1, -2));
        setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.white));
        this.mDescriptionView = (ThemedTextView) this.mRootLayout.findViewById(R.id.group_buy_view_description);
        this.mSubtitle = (ThemedTextView) this.mRootLayout.findViewById(R.id.group_buy_view_subtitle);
        this.mExtraDescriptionView = (ThemedTextView) this.mRootLayout.findViewById(R.id.group_buy_view_extra_description);
        this.mExtraLearnMore = (ThemedTextView) this.mRootLayout.findViewById(R.id.group_buy_view_extra_learn_more);
        this.mPriceContainer = (LinearLayout) this.mRootLayout.findViewById(R.id.group_buy_price_container);
        this.mPriceContainerSwapped = (LinearLayout) this.mRootLayout.findViewById(R.id.group_buy_price_container_swapped);
        this.mYourPrice = (ThemedTextView) this.mRootLayout.findViewById(R.id.group_buy_your_price);
        this.mListPrice = (ThemedTextView) this.mRootLayout.findViewById(R.id.group_buy_list_price);
        this.mYourPriceSwapped = (ThemedTextView) this.mRootLayout.findViewById(R.id.group_buy_your_price_swapped);
        this.mListPriceSwapped = (ThemedTextView) this.mRootLayout.findViewById(R.id.group_buy_list_price_swapped);
        this.mSpacerView = this.mRootLayout.findViewById(R.id.group_buy_view_spacer);
        this.mListPrice.setPaintFlags(this.mListPrice.getPaintFlags() | 16);
        this.mListPriceSwapped.setPaintFlags(this.mListPrice.getPaintFlags() | 16);
        this.mGroupBuyItemViews = new ArrayList<>();
        this.mDescriptionView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
    }

    private void hideUiElements() {
        this.mPriceContainer.setVisibility(8);
        this.mPriceContainerSwapped.setVisibility(8);
    }

    public void setup(ArrayList<WishGroupBuyRowInfo> arrayList, WishGroupBuyInfo wishGroupBuyInfo, WishProduct wishProduct, BaseActivity baseActivity, BuyCallback buyCallback) {
        final BuyCallback buyCallback2 = buyCallback;
        hideUiElements();
        releaseImages();
        this.mProduct = wishProduct;
        this.mContainer.removeAllViews();
        this.mGroupBuyItemViews.clear();
        String str = ExperimentDataCenter.getInstance().shouldSeeGroupBuyRedesign() ? arrayList.size() == 0 ? wishGroupBuyInfo.getCreateInstruction() : wishProduct.getGroupBuyJoinDescription() : wishGroupBuyInfo.getDescription();
        if (str != null) {
            this.mDescriptionView.setText(str);
        } else {
            this.mDescriptionView.setVisibility(8);
        }
        String groupBuyExtraDescription = wishProduct.getGroupBuyExtraDescription();
        if (groupBuyExtraDescription == null || arrayList.size() != 0) {
            this.mExtraDescriptionView.setVisibility(8);
        } else {
            this.mExtraDescriptionView.setText(groupBuyExtraDescription);
        }
        if (ExperimentDataCenter.getInstance().shouldSeeGroupBuyRedesign()) {
            String subtitle = wishGroupBuyInfo.getSubtitle();
            if (subtitle != null && arrayList.size() == 0) {
                this.mSubtitle.setVisibility(0);
                this.mSubtitle.setText(subtitle);
            }
        }
        String learnMoreText = wishGroupBuyInfo.getLearnMoreText();
        if (learnMoreText == null || arrayList.size() != 0 || ExperimentDataCenter.getInstance().shouldSeeGroupBuyRedesign()) {
            WishGroupBuyInfo wishGroupBuyInfo2 = wishGroupBuyInfo;
            this.mExtraLearnMore.setVisibility(8);
        } else {
            this.mExtraLearnMore.setText(learnMoreText);
            final WishGroupBuyInfo wishGroupBuyInfo3 = wishGroupBuyInfo;
            final BaseActivity baseActivity2 = baseActivity;
            this.mExtraLearnMore.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    GroupBuyView.this.showLearnMore(baseActivity2, wishGroupBuyInfo3);
                }
            });
        }
        this.mYourPrice.setText(wishProduct.getGroupBuyPrice().toFormattedString());
        this.mListPrice.setText(wishProduct.getValue().toFormattedString());
        this.mYourPriceSwapped.setText(wishProduct.getGroupBuyPrice().toFormattedString());
        this.mListPriceSwapped.setText(wishProduct.getValue().toFormattedString());
        if (ExperimentDataCenter.getInstance().shouldShowCrossedOutPriceToLeftInProductDetail()) {
            this.mPriceContainerSwapped.setVisibility(0);
            this.mPriceContainer.setVisibility(8);
        } else {
            this.mPriceContainer.setVisibility(0);
            this.mPriceContainerSwapped.setVisibility(8);
        }
        if (arrayList.size() != 0) {
            this.mContainer.setVisibility(0);
            for (int i = 0; i < arrayList.size(); i++) {
                GroupBuyItemView groupBuyItemView = new GroupBuyItemView(getContext());
                groupBuyItemView.setup((WishGroupBuyRowInfo) arrayList.get(i), wishProduct.getGroupBuyPrice().toFormattedString(), buyCallback2);
                this.mContainer.addView(groupBuyItemView);
                this.mGroupBuyItemViews.add(groupBuyItemView);
            }
        } else if (!wishGroupBuyInfo.canCreate() || wishGroupBuyInfo.getCreatorName() == null || wishGroupBuyInfo.getCreatorTitle() == null) {
            setVisibility(8);
        } else {
            WishGroupBuyRowInfo wishGroupBuyRowInfo = new WishGroupBuyRowInfo(null, wishGroupBuyInfo.getCreatorImage(), wishGroupBuyInfo.getCreatorName(), wishGroupBuyInfo.getCreatorTitle(), null, wishGroupBuyInfo.getCreateButtonText(), null);
            GroupBuyItemView groupBuyItemView2 = new GroupBuyItemView(getContext());
            groupBuyItemView2.setup(wishGroupBuyRowInfo, wishProduct.getGroupBuyPrice().toFormattedString(), new BuyCallback() {
                public void onCreate() {
                }

                public void onBuy(String str) {
                    buyCallback2.onCreate();
                }
            });
            groupBuyItemView2.fadeView();
            this.mContainer.addView(groupBuyItemView2);
            this.mContainer.setVisibility(0);
            this.mGroupBuyItemViews.add(groupBuyItemView2);
        }
    }

    public void releaseImages() {
        for (int i = 0; i < this.mGroupBuyItemViews.size(); i++) {
            ((GroupBuyItemView) this.mGroupBuyItemViews.get(i)).releaseImages();
        }
    }

    public void restoreImages() {
        for (int i = 0; i < this.mGroupBuyItemViews.size(); i++) {
            ((GroupBuyItemView) this.mGroupBuyItemViews.get(i)).restoreImages();
        }
    }

    public void recycle() {
        releaseImages();
    }

    public void learnMoreSetup() {
        this.mPriceContainer.setVisibility(8);
        this.mPriceContainerSwapped.setVisibility(8);
        this.mDescriptionView.setVisibility(8);
        this.mSpacerView.setVisibility(8);
        ((GroupBuyItemView) this.mGroupBuyItemViews.get(0)).learnMoreSetup();
    }

    /* access modifiers changed from: private */
    public void showLearnMore(BaseActivity baseActivity, WishGroupBuyInfo wishGroupBuyInfo) {
        Intent intent = new Intent();
        intent.setClass(baseActivity, TextViewerActivity.class);
        intent.putExtra("ExtraTitle", wishGroupBuyInfo.getTitle());
        intent.putExtra("ExtraContent", wishGroupBuyInfo.getLearnMoreDetail());
        baseActivity.startActivity(intent);
    }
}
