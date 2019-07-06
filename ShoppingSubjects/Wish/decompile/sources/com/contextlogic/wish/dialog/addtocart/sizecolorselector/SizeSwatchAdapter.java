package com.contextlogic.wish.dialog.addtocart.sizecolorselector;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;

public class SizeSwatchAdapter extends BaseSwatchAdapter {
    public SizeSwatchAdapter(Context context, ArrayList<ProductVariantState> arrayList, RecyclerView recyclerView, ThemedTextView themedTextView, SizeColorSelectorDialogFragment sizeColorSelectorDialogFragment, boolean z) {
        super(context, arrayList, recyclerView, themedTextView, sizeColorSelectorDialogFragment, z);
    }

    public boolean onItemClick(int i) {
        if (!super.onItemClick(i)) {
            return false;
        }
        this.mSelectorDialogFragment.refreshEnabledOptions();
        return true;
    }

    public void setPreselectedSwatch(int i) {
        this.mOldSwatch = i;
    }
}
