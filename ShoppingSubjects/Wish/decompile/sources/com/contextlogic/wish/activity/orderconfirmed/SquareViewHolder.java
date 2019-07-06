package com.contextlogic.wish.activity.orderconfirmed;

import android.content.Context;
import android.view.View.OnClickListener;
import com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedView.AdjustableSquareView;

public class SquareViewHolder extends OrderConfirmedView {

    public static class Builder extends com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedView.Builder {
        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTitle(String str) {
            this.mTitle = str;
            return this;
        }

        public Builder setButtonText(String str) {
            this.mButtonText = str;
            return this;
        }

        public Builder setBackgroundDrawableId(int i) {
            this.mBackgroundDrawableId = i;
            return this;
        }

        public Builder setAdjustableSquareView(AdjustableSquareView adjustableSquareView) {
            this.mAdjustableSquareView = adjustableSquareView;
            return this;
        }

        public Builder setOnClickListener(OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
            return this;
        }

        public SquareViewHolder build() {
            SquareViewHolder squareViewHolder = new SquareViewHolder(this.mContext);
            squareViewHolder.initialize(this);
            return squareViewHolder;
        }
    }

    private SquareViewHolder(Context context) {
        super(context);
    }
}
