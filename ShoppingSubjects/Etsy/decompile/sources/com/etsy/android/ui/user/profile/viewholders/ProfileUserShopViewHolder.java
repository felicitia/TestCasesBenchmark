package com.etsy.android.ui.user.profile.viewholders;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ClickableImageView;
import com.etsy.android.uikit.view.RatingIconView;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ProfileUserShopViewHolder extends ViewHolder {
    private final TextView mHeader;
    private final String mHeaderText;
    private final TextView mNumberTransactions;
    private final TextView mOpenDate;
    private final SimpleDateFormat mOpenDateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
    private final ClickableImageView mShopIcon;
    private final TextView mShopName;
    private final TextView mShopRatingCount;
    private final RatingIconView mShopRatingView;

    public ProfileUserShopViewHolder(View view) {
        super(view);
        this.mShopName = (TextView) view.findViewById(R.id.shop_name);
        this.mShopIcon = (ClickableImageView) view.findViewById(R.id.shop_icon);
        this.mShopRatingView = (RatingIconView) view.findViewById(R.id.shop_rating);
        this.mShopRatingCount = (TextView) view.findViewById(R.id.rating_count);
        this.mHeader = (TextView) view.findViewById(R.id.shop_info_header);
        this.mOpenDate = (TextView) view.findViewById(R.id.shop_open_date);
        this.mNumberTransactions = (TextView) view.findViewById(R.id.shop_num_transactions);
        this.mHeaderText = view.getContext().getString(R.string.shop_info_header);
    }

    public void bind(final ShopCard shopCard, String str, int i, boolean z, final c cVar) {
        this.mShopName.setText(shopCard.getShopName());
        if (z) {
            this.mHeader.setText(R.string.nav_shop);
        } else {
            this.mHeader.setText(String.format(this.mHeaderText, new Object[]{str}));
        }
        if (shopCard.hasIcon()) {
            this.mShopIcon.post(new Runnable() {
                public void run() {
                    ProfileUserShopViewHolder.this.bindShopIcon(shopCard, cVar);
                }
            });
            this.mShopIcon.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    e.a((Activity) (FragmentActivity) ProfileUserShopViewHolder.this.itemView.getContext()).b(shopCard.getShopId());
                }
            });
        } else {
            this.mShopIcon.setVisibility(8);
        }
        this.mNumberTransactions.setText(this.itemView.getContext().getResources().getQuantityString(R.plurals.sales_pl_nt, i, new Object[]{Integer.valueOf(i)}));
        this.mOpenDate.setText(this.itemView.getContext().getString(R.string.since, new Object[]{this.mOpenDateFormat.format(shopCard.getOpenDate())}));
        if (shopCard.getAverageRating() > 0.0d) {
            this.mShopRatingView.setRating((float) shopCard.getAverageRating());
            TextView textView = this.mShopRatingCount;
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(shopCard.getNumRatings());
            sb.append(")");
            textView.setText(sb.toString());
            return;
        }
        this.mShopRatingView.setVisibility(8);
        this.mShopRatingCount.setVisibility(8);
    }

    /* access modifiers changed from: 0000 */
    public void bindShopIcon(ShopCard shopCard, c cVar) {
        int width = this.mShopIcon.getWidth();
        int height = this.mShopIcon.getHeight();
        cVar.a(c.a(width, height, shopCard.getIcon()), this.mShopIcon, width, height);
    }
}
