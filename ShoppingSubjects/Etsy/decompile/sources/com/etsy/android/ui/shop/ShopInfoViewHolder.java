package com.etsy.android.ui.shop;

import android.app.Activity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.apiv3.AppreciationPhotoFeature;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ClickableImageView;
import com.etsy.android.uikit.view.RatingIconView;

public class ShopInfoViewHolder extends ViewHolder {
    /* access modifiers changed from: private */
    public Activity mContext;
    private View mDivider;
    private ClickableImageView mShopIcon;
    private TextView mShopLocation;
    private TextView mShopName;
    private TextView mShopRatingCount;
    private RatingIconView mShopRatingView;

    public ShopInfoViewHolder(View view, Activity activity) {
        super(view);
        this.mShopName = (TextView) view.findViewById(R.id.shop_name);
        this.mShopLocation = (TextView) view.findViewById(R.id.shop_location);
        this.mShopIcon = (ClickableImageView) view.findViewById(R.id.shop_icon);
        this.mShopRatingView = (RatingIconView) view.findViewById(R.id.shop_rating);
        this.mShopRatingCount = (TextView) view.findViewById(R.id.rating_count);
        this.mDivider = view.findViewById(R.id.divider);
        this.mContext = activity;
    }

    public void bind(final AppreciationPhotoFeature appreciationPhotoFeature, c cVar) {
        this.mShopName.setText(appreciationPhotoFeature.getShopName());
        cVar.a(appreciationPhotoFeature.getShopIconUrl(), (ImageView) this.mShopIcon);
        String shopLocation = appreciationPhotoFeature.getShopLocation();
        if (!TextUtils.isEmpty(shopLocation)) {
            this.mShopLocation.setText(shopLocation);
            this.mShopLocation.setVisibility(0);
            if (this.mDivider != null) {
                this.mDivider.setVisibility(0);
                this.mDivider.getLayoutParams().height = this.mShopLocation.getLineHeight();
            }
        } else {
            this.mShopLocation.setVisibility(8);
            if (this.mDivider != null) {
                this.mDivider.setVisibility(8);
            }
        }
        this.mShopRatingView.setRating(appreciationPhotoFeature.getShopAverageRating());
        TextView textView = this.mShopRatingCount;
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(appreciationPhotoFeature.getShopTotalRatings());
        sb.append(")");
        textView.setText(sb.toString());
        this.mShopIcon.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                e.a(ShopInfoViewHolder.this.mContext).b(appreciationPhotoFeature.getShopId());
            }
        });
    }
}
