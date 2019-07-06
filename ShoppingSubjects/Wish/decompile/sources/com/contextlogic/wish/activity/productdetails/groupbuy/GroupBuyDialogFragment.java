package com.contextlogic.wish.activity.productdetails.groupbuy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.productdetails.groupbuy.GroupBuyView.BuyCallback;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishGroupBuyInfo;
import com.contextlogic.wish.api.model.WishGroupBuyRowInfo;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.DisplayUtil;
import java.util.ArrayList;

public class GroupBuyDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    private LinearLayout mContainer;
    /* access modifiers changed from: private */
    public WishGroupBuyInfo mGroupBuyInfo;
    /* access modifiers changed from: private */
    public GroupBuyView mGroupBuyView;
    /* access modifiers changed from: private */
    public ArrayList<WishGroupBuyRowInfo> mGroupBuys;
    /* access modifiers changed from: private */
    public WishProduct mProduct;
    private ThemedTextView mTitleView;
    private AutoReleasableImageView mXButton;

    public int getGravity() {
        return 81;
    }

    public static GroupBuyDialogFragment<BaseActivity> createGroupBuyDialog(ArrayList<WishGroupBuyRowInfo> arrayList, WishGroupBuyInfo wishGroupBuyInfo, WishProduct wishProduct) {
        if (arrayList == null) {
            return null;
        }
        GroupBuyDialogFragment<BaseActivity> groupBuyDialogFragment = new GroupBuyDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ArgumentGroupBuyRows", arrayList);
        bundle.putParcelable("ArgumentGroupBuyInfo", wishGroupBuyInfo);
        bundle.putParcelable("ArgumentGroupBuyProduct", wishProduct);
        groupBuyDialogFragment.setArguments(bundle);
        return groupBuyDialogFragment;
    }

    private boolean initializeState() {
        Bundle arguments = getArguments();
        this.mGroupBuys = arguments.getParcelableArrayList("ArgumentGroupBuyRows");
        this.mGroupBuyInfo = (WishGroupBuyInfo) arguments.getParcelable("ArgumentGroupBuyInfo");
        this.mProduct = (WishProduct) arguments.getParcelable("ArgumentGroupBuyProduct");
        return (this.mGroupBuys == null || this.mGroupBuyInfo == null || this.mProduct == null) ? false : true;
    }

    /* access modifiers changed from: private */
    public void handlePurchase(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("ResultSelectedGroupBuyId", str);
        makeSelection(bundle);
    }

    /* access modifiers changed from: private */
    public void handleCreateGroupBuy() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("ResultSelectedCreateGroupBuy", true);
        makeSelection(bundle);
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (!initializeState()) {
            return null;
        }
        this.mContainer = (LinearLayout) layoutInflater.inflate(R.layout.group_buy_dialog_fragment, viewGroup, false);
        ScrollView scrollView = (ScrollView) this.mContainer.findViewById(R.id.group_buy_dialog_fragment_group_buy_container);
        this.mTitleView = (ThemedTextView) this.mContainer.findViewById(R.id.group_buy_dialog_fragment_title);
        this.mTitleView.setText(this.mGroupBuyInfo.getTitle());
        this.mXButton = (AutoReleasableImageView) this.mContainer.findViewById(R.id.group_buy_dialog_fragment_x_button);
        this.mXButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GroupBuyDialogFragment.this.handleCancel();
            }
        });
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                GroupBuyDialogFragment.this.mGroupBuyView = new GroupBuyView(a);
                GroupBuyDialogFragment.this.mGroupBuyView.setup(GroupBuyDialogFragment.this.mGroupBuys, GroupBuyDialogFragment.this.mGroupBuyInfo, GroupBuyDialogFragment.this.mProduct, a, new BuyCallback() {
                    public void onBuy(String str) {
                        GroupBuyDialogFragment.this.restoreImages();
                        GroupBuyDialogFragment.this.handlePurchase(str);
                    }

                    public void onCreate() {
                        GroupBuyDialogFragment.this.releaseImages();
                        GroupBuyDialogFragment.this.handleCreateGroupBuy();
                    }
                });
            }
        });
        scrollView.addView(this.mGroupBuyView);
        return this.mContainer;
    }

    public int getDialogWidth() {
        int displayWidth = DisplayUtil.getDisplayWidth();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.bottom_dialog_fragment_max_width);
        return displayWidth > dimensionPixelSize ? dimensionPixelSize : displayWidth;
    }

    public void releaseImages() {
        if (this.mGroupBuyView != null) {
            this.mGroupBuyView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mGroupBuyView != null) {
            this.mGroupBuyView.restoreImages();
        }
    }

    /* access modifiers changed from: private */
    public void handleCancel() {
        releaseImages();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_GROUP_BUY_CLOSE_DIALOG);
        cancel();
    }
}
