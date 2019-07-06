package com.contextlogic.wish.dialog.addtocart.sizecolorselector;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;

public class ColorSwatchAdapter extends BaseSwatchAdapter {
    public ColorSwatchAdapter(Context context, ArrayList<ProductVariantState> arrayList, RecyclerView recyclerView, ThemedTextView themedTextView, SizeColorSelectorDialogFragment sizeColorSelectorDialogFragment, boolean z) {
        super(context, arrayList, recyclerView, themedTextView, sizeColorSelectorDialogFragment, z);
    }

    public boolean onItemClick(int i) {
        if (!super.onItemClick(i)) {
            return false;
        }
        this.mSelectorDialogFragment.getProduct().setDefaultCommerceVariationId(this.mSelectorDialogFragment.getCurrentVariation());
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void refreshEnabledOptions(String str) {
        String str2;
        if (this.mOldSwatch != -1) {
            str2 = getItem(this.mOldSwatch).getName();
            this.mOldSwatch = -1;
        } else {
            str2 = null;
        }
        int i = 0;
        for (int i2 = 0; i2 < getItemCount(); i2++) {
            ProductVariantState item = getItem(i2);
            if (this.mSelectorDialogFragment.isColorAndSelectedSizeInStock(item.getName())) {
                item.setEnabled(true);
                if (this.mSelectorDialogFragment.isExpressShippingEligibleForColor(item.getName())) {
                    item.setWishExpress(true);
                } else {
                    item.setWishExpress(false);
                }
            } else {
                item.setEnabled(false);
                item.setWishExpress(false);
            }
            item.setSelected(false);
            item.setPreSelectedSizeSwatch(str);
        }
        this.mSelectorDialogFragment.getProduct().setDefaultCommerceVariationId(this.mSelectorDialogFragment.getCurrentVariation());
        sort();
        while (true) {
            if (i >= getItemCount()) {
                break;
            }
            ProductVariantState item2 = getItem(i);
            if (item2 != null && item2.isEnabled()) {
                if (this.mNewSwatch == -1) {
                    this.mNewSwatch = i;
                }
                if (item2.getName().equals(str2)) {
                    this.mNewSwatch = i;
                    break;
                }
            }
            i++;
        }
        if (this.mNewSwatch != -1) {
            this.mAttachedRecyclerView.post(new Runnable() {
                public void run() {
                    ColorSwatchAdapter.this.onItemClick(ColorSwatchAdapter.this.mNewSwatch);
                    ColorSwatchAdapter.this.mAttachedRecyclerView.setAdapter(ColorSwatchAdapter.this);
                }
            });
        }
    }
}
