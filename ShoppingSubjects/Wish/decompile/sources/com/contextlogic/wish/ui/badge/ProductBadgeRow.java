package com.contextlogic.wish.ui.badge;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishProductBadge;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class ProductBadgeRow extends RelativeLayout {
    public ProductBadgeRow(Context context, WishProductBadge wishProductBadge) {
        this(context, null, wishProductBadge);
    }

    public ProductBadgeRow(Context context, AttributeSet attributeSet, WishProductBadge wishProductBadge) {
        super(context, attributeSet);
        init(context, wishProductBadge);
    }

    private void init(Context context, WishProductBadge wishProductBadge) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.product_badge_bordered_view_row, this);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.product_badge_row_holder);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(ContextCompat.getColor(context, wishProductBadge.getBackgroundBadgeColor()));
        gradientDrawable.setStroke(getResources().getDimensionPixelOffset(R.dimen.outlined_cell_border_thickness), ContextCompat.getColor(context, wishProductBadge.getBadgeColor()));
        linearLayout.setBackground(gradientDrawable);
        AutoReleasableImageView autoReleasableImageView = (AutoReleasableImageView) inflate.findViewById(R.id.product_badge_row_image);
        ThemedTextView themedTextView = (ThemedTextView) inflate.findViewById(R.id.product_badge_row_title_text);
        ThemedTextView themedTextView2 = (ThemedTextView) inflate.findViewById(R.id.product_badge_row_message_text);
        autoReleasableImageView.setImageResource(wishProductBadge.getBadgeIcon());
        themedTextView.setText(wishProductBadge.getTitle());
        themedTextView2.setText(wishProductBadge.getMessage());
        themedTextView.setTextColor(ContextCompat.getColor(context, wishProductBadge.getBadgeColor()));
        themedTextView2.setTextColor(ContextCompat.getColor(context, wishProductBadge.getBadgeColor()));
    }
}
