package com.contextlogic.wish.dialog.popupanimation.bundleadded;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.util.DisplayUtil;
import java.util.ArrayList;

public class BundleAddedImageView extends LinearLayout {
    private final Context mContext;
    private ArrayList<NetworkImageView> mProductImages;

    public BundleAddedImageView(Context context) {
        this(context, null);
    }

    public BundleAddedImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public void setup(ArrayList<WishCartItem> arrayList) {
        int i;
        this.mProductImages = new ArrayList<>();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.eight_padding);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.bundle_added_dialog_popup_image_size);
        int dimensionPixelSize4 = getResources().getDimensionPixelSize(R.dimen.bundles_product_image_separator);
        float displayWidth = (float) DisplayUtil.getDisplayWidth();
        int i2 = dimensionPixelSize2 * 2;
        float dimensionPixelSize5 = ((displayWidth - ((float) getResources().getDimensionPixelSize(R.dimen.bundles_content_padding))) - ((float) ((arrayList.size() - 1) * (getResources().getDimensionPixelSize(R.dimen.bundles_product_image_separator) + i2)))) - ((float) i2);
        if (arrayList.size() == 2) {
            i = dimensionPixelSize;
        } else {
            i = (((((int) displayWidth) - (arrayList.size() * dimensionPixelSize3)) - ((arrayList.size() - 1) * dimensionPixelSize4)) - (dimensionPixelSize * 2)) / ((arrayList.size() - 1) * 2);
        }
        setLayoutParams(new LayoutParams(-1, -2));
        int min = Math.min(dimensionPixelSize3, (int) dimensionPixelSize5);
        LayoutParams layoutParams = new LayoutParams(min, min);
        setGravity(17);
        setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        LayoutParams layoutParams2 = new LayoutParams(dimensionPixelSize4, dimensionPixelSize4);
        layoutParams2.setMargins(i, 0, i, 0);
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            WishCartItem wishCartItem = (WishCartItem) arrayList.get(i3);
            NetworkImageView networkImageView = new NetworkImageView(this.mContext);
            networkImageView.setLayoutParams(layoutParams);
            networkImageView.setImage(wishCartItem.getImage());
            addView(networkImageView);
            this.mProductImages.add(networkImageView);
            if (i3 < arrayList.size() - 1) {
                AutoReleasableImageView autoReleasableImageView = new AutoReleasableImageView(this.mContext);
                autoReleasableImageView.setImageResource(R.drawable.dark_plus);
                autoReleasableImageView.setLayoutParams(layoutParams2);
                addView(autoReleasableImageView);
            }
        }
    }
}
