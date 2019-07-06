package com.contextlogic.wish.activity.orderconfirmed;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedFragment.GroupBuyInfo;
import com.contextlogic.wish.activity.productdetails.groupbuy.GroupBuyItemView;
import com.contextlogic.wish.api.model.WishGroupBuyRowInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class GroupBuyConfirmedItemView extends LinearLayout {
    private GroupBuyItemView mGroupBuyItemView;
    private NetworkImageView mImage;
    private ThemedTextView mMessage;
    private ThemedTextView mTitle;

    public GroupBuyConfirmedItemView(Context context) {
        this(context, null);
    }

    public GroupBuyConfirmedItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.order_confirmed_group_buy_view, this);
        inflate.setLayoutParams(new LayoutParams(-1, -2));
        setOrientation(1);
        setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.light_gray_3));
        setPadding(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.order_confirmed_view_spacing));
        this.mImage = (NetworkImageView) inflate.findViewById(R.id.order_confirmed_group_buy_image);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.order_confirmed_group_buy_title);
        this.mGroupBuyItemView = (GroupBuyItemView) inflate.findViewById(R.id.order_confirmed_group_buy_row);
        this.mMessage = (ThemedTextView) inflate.findViewById(R.id.order_confirmed_group_buy_info);
    }

    public void setup(GroupBuyInfo groupBuyInfo) {
        this.mTitle.setText(groupBuyInfo.getTitle());
        this.mImage.setImage(groupBuyInfo.getProductImage());
        WishGroupBuyRowInfo wishGroupBuyRowInfo = new WishGroupBuyRowInfo(groupBuyInfo.getExpiry(), groupBuyInfo.getUserImage(), groupBuyInfo.getUserName(), groupBuyInfo.getUserName(), null, null, null);
        this.mGroupBuyItemView.setup(wishGroupBuyRowInfo, groupBuyInfo.getPrice().toFormattedString(), null);
        this.mMessage.setText(groupBuyInfo.getMessage());
    }

    public void releaseImages() {
        if (this.mGroupBuyItemView != null) {
            this.mGroupBuyItemView.releaseImages();
        }
        if (this.mImage != null) {
            this.mImage.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mGroupBuyItemView != null) {
            this.mGroupBuyItemView.restoreImages();
        }
        if (this.mImage != null) {
            this.mImage.restoreImages();
        }
    }
}
