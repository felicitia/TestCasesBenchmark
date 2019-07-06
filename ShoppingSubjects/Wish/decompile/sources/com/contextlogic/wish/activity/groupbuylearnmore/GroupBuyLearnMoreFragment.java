package com.contextlogic.wish.activity.groupbuylearnmore;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.productdetails.groupbuy.GroupBuyItemView;
import com.contextlogic.wish.activity.productdetails.groupbuy.GroupBuyView;
import com.contextlogic.wish.activity.productdetails.groupbuy.GroupBuyView.BuyCallback;
import com.contextlogic.wish.api.model.WishGroupBuyInfo;
import com.contextlogic.wish.api.model.WishGroupBuyRowInfo;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;

public class GroupBuyLearnMoreFragment extends UiFragment<GroupBuyLearnMoreActivity> {
    private GroupBuyView mCreateView;
    /* access modifiers changed from: private */
    public WishGroupBuyRowInfo mGroupBuy;
    /* access modifiers changed from: private */
    public WishGroupBuyInfo mGroupBuyInfo;
    private GroupBuyItemView mJoinView;
    /* access modifiers changed from: private */
    public WishProduct mProduct;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.group_buy_learn_more_fragment;
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        withActivity(new ActivityTask<GroupBuyLearnMoreActivity>() {
            public void performTask(GroupBuyLearnMoreActivity groupBuyLearnMoreActivity) {
                GroupBuyLearnMoreFragment.this.mGroupBuyInfo = groupBuyLearnMoreActivity.getGroupBuyInfo();
                GroupBuyLearnMoreFragment.this.mGroupBuy = groupBuyLearnMoreActivity.getGroupBuy();
                GroupBuyLearnMoreFragment.this.mProduct = groupBuyLearnMoreActivity.getProduct();
            }
        });
        ThemedTextView themedTextView = (ThemedTextView) findViewById(R.id.group_buy_learn_more_fragment_join_title);
        ThemedTextView themedTextView2 = (ThemedTextView) findViewById(R.id.group_buy_learn_more_fragment_join_text);
        ThemedTextView themedTextView3 = (ThemedTextView) findViewById(R.id.group_buy_learn_more_fragment_create_title);
        ThemedTextView themedTextView4 = (ThemedTextView) findViewById(R.id.group_buy_learn_more_fragment_create_text);
        ThemedTextView themedTextView5 = (ThemedTextView) findViewById(R.id.group_buy_learn_more_fragment_receive_title);
        ThemedTextView themedTextView6 = (ThemedTextView) findViewById(R.id.group_buy_learn_more_fragment_receive_text);
        ((ThemedTextView) findViewById(R.id.group_buy_learn_more_fragment_title)).setText(this.mGroupBuyInfo.getLearnMoreTitle());
        themedTextView.setText(this.mGroupBuyInfo.getLearnMoreJoinTitle());
        themedTextView2.setText(this.mGroupBuyInfo.getLearnMoreJoinText());
        themedTextView3.setText(this.mGroupBuyInfo.getLearnMoreCreateTitle());
        themedTextView4.setText(this.mGroupBuyInfo.getLearnMoreCreateText());
        themedTextView5.setText(this.mGroupBuyInfo.getLearnMoreReceiveTitle());
        themedTextView6.setText(this.mGroupBuyInfo.getLearnMoreReceiveText());
        this.mJoinView = (GroupBuyItemView) findViewById(R.id.group_buy_learn_more_fragment_join_view);
        this.mJoinView.setup(this.mGroupBuy, "", new BuyCallback() {
            public void onBuy(String str) {
            }

            public void onCreate() {
            }
        });
        this.mJoinView.learnMoreSetup();
        this.mCreateView = (GroupBuyView) findViewById(R.id.group_buy_learn_more_fragment_create_view);
        this.mCreateView.setup(new ArrayList(), this.mGroupBuyInfo, this.mProduct, null, new BuyCallback() {
            public void onBuy(String str) {
            }

            public void onCreate() {
            }
        });
        this.mCreateView.learnMoreSetup();
    }

    public void releaseImages() {
        this.mJoinView.releaseImages();
        this.mCreateView.releaseImages();
    }

    public void restoreImages() {
        this.mJoinView.restoreImages();
        this.mCreateView.restoreImages();
    }
}
