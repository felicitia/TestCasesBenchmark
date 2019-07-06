package com.contextlogic.wish.activity.orderconfirmed;

import android.content.Context;
import android.view.View.OnClickListener;
import com.contextlogic.wish.api.model.WishImage;

public class HorizontalProductScroller extends OrderConfirmedView {

    public static class Builder extends com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedView.Builder {
        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTitle(String str) {
            this.mTitle = str;
            return this;
        }

        public Builder setWishImage(WishImage wishImage) {
            this.mWishImage = wishImage;
            return this;
        }

        public Builder setButtonText(String str) {
            this.mButtonText = str;
            return this;
        }

        public Builder setOnClickListener(OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setIsLastInlist(boolean z) {
            this.mIsLastInList = z;
            return this;
        }

        public HorizontalProductScroller build() {
            HorizontalProductScroller horizontalProductScroller = new HorizontalProductScroller(this.mContext);
            horizontalProductScroller.initialize(this);
            return horizontalProductScroller;
        }
    }

    private HorizontalProductScroller(Context context) {
        super(context);
    }
}
