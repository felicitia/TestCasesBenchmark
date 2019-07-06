package com.contextlogic.wish.dialog.addtocart.sizecolorselector;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.dialog.addtocart.sizecolorselector.ProductVariantState.StateType;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class BaseSwatchAdapter extends Adapter<ViewHolder> {
    protected RecyclerView mAttachedRecyclerView;
    private Context mContext;
    protected int mNewSwatch;
    protected int mOldSwatch;
    private ArrayList<ProductVariantState> mOriginalSwatches = new ArrayList<>(this.mStates);
    protected TextView mSelectedField;
    protected SizeColorSelectorDialogFragment mSelectorDialogFragment;
    protected boolean mShouldShowPriceLabels;
    private ArrayList<ProductVariantState> mStates;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        BaseProductVariantSwatch mBaseSwatch;

        public ViewHolder(BaseProductVariantSwatch baseProductVariantSwatch) {
            super(baseProductVariantSwatch);
            this.mBaseSwatch = baseProductVariantSwatch;
        }
    }

    public BaseSwatchAdapter(Context context, ArrayList<ProductVariantState> arrayList, RecyclerView recyclerView, ThemedTextView themedTextView, SizeColorSelectorDialogFragment sizeColorSelectorDialogFragment, boolean z) {
        this.mContext = context;
        this.mStates = arrayList;
        this.mSelectedField = themedTextView;
        this.mSelectorDialogFragment = sizeColorSelectorDialogFragment;
        this.mAttachedRecyclerView = recyclerView;
        this.mShouldShowPriceLabels = z;
        this.mNewSwatch = -1;
        this.mOldSwatch = -1;
        sort();
    }

    public int getItemCount() {
        return this.mStates.size();
    }

    public int getItemViewType(int i) {
        return getItem(i).mStateType.ordinal();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == StateType.TEXT.ordinal()) {
            return new ViewHolder(new TextProductVariantSwatch(this.mContext));
        }
        return new ViewHolder(new ColorProductVariantSwatch(this.mContext));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ProductVariantState item = getItem(i);
        BaseProductVariantSwatch baseProductVariantSwatch = viewHolder.mBaseSwatch;
        baseProductVariantSwatch.setEnabled(item.isEnabled());
        baseProductVariantSwatch.setSelected(item.isSelected());
        if (item.mStateType == StateType.COLOR && (baseProductVariantSwatch instanceof ColorProductVariantSwatch) && (item instanceof ProductVariantColorState)) {
            ProductVariantColorState productVariantColorState = (ProductVariantColorState) item;
            if (productVariantColorState.getForceImage()) {
                ((ColorProductVariantSwatch) baseProductVariantSwatch).setForcedImage(productVariantColorState.getForcedImageResId(), item.isEnabled());
            } else {
                ((ColorProductVariantSwatch) baseProductVariantSwatch).setColors(productVariantColorState.getColors());
            }
        } else if (item.mStateType == StateType.TEXT && (baseProductVariantSwatch instanceof TextProductVariantSwatch)) {
            ((TextProductVariantSwatch) baseProductVariantSwatch).setText(item.getName());
        }
        baseProductVariantSwatch.setWishExpress(item.getWishExpress());
        item.setOnSelectListener(baseProductVariantSwatch);
        final int adapterPosition = viewHolder.getAdapterPosition();
        baseProductVariantSwatch.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseSwatchAdapter.this.onItemClick(adapterPosition);
            }
        });
        if (!this.mShouldShowPriceLabels) {
            return;
        }
        if (item.showValueInPoints()) {
            baseProductVariantSwatch.setPriceText(this.mContext.getString(R.string.pts_amount, new Object[]{Integer.valueOf(item.getValueInPoints())}));
            return;
        }
        baseProductVariantSwatch.setPriceText(item.getPrice());
    }

    public ProductVariantState getItem(int i) {
        WishLocalizedCurrencyValue wishLocalizedCurrencyValue = null;
        if (i >= getItemCount() || i < 0) {
            return null;
        }
        ProductVariantState productVariantState = (ProductVariantState) this.mStates.get(i);
        if (this.mShouldShowPriceLabels) {
            int i2 = -1;
            if (this instanceof SizeSwatchAdapter) {
                WishLocalizedCurrencyValue price = this.mSelectorDialogFragment.getPrice(null, productVariantState.getName());
                wishLocalizedCurrencyValue = price;
                i2 = this.mSelectorDialogFragment.getValueInPoints(null, productVariantState.getName());
            } else if (this instanceof ColorSwatchAdapter) {
                wishLocalizedCurrencyValue = this.mSelectorDialogFragment.getPrice(productVariantState.getName(), productVariantState.getPreSelectedSizeSwatch());
                i2 = this.mSelectorDialogFragment.getValueInPoints(productVariantState.getName(), productVariantState.getPreSelectedSizeSwatch());
            }
            if (wishLocalizedCurrencyValue != null) {
                productVariantState.setPrice(wishLocalizedCurrencyValue.toFormattedString());
            }
            productVariantState.setValueInPoints(i2);
        }
        return productVariantState;
    }

    public boolean onItemClick(int i) {
        ProductVariantState item = getItem(this.mOldSwatch);
        if (item != null) {
            item.setSelected(false);
        }
        ProductVariantState item2 = getItem(i);
        if (item2 == null || item2.isSelected()) {
            return false;
        }
        item2.setSelected(true);
        this.mSelectedField.setText(item2.getName());
        this.mOldSwatch = i;
        this.mNewSwatch = -1;
        this.mSelectorDialogFragment.setSelections();
        return true;
    }

    public boolean hasSelection() {
        return this.mOldSwatch != -1;
    }

    public String getSelectedSwatch() {
        if (hasSelection()) {
            return ((ProductVariantState) this.mStates.get(this.mOldSwatch)).getName();
        }
        return null;
    }

    public int getFirstAvailableIndex() {
        for (int i = 0; i < getItemCount(); i++) {
            if (getItem(i).isEnabled()) {
                return i;
            }
        }
        return -1;
    }

    public void autoSelectFirstAvailableState() {
        int firstAvailableIndex = getFirstAvailableIndex();
        if (firstAvailableIndex != -1) {
            this.mNewSwatch = firstAvailableIndex;
            onItemClick(this.mNewSwatch);
        }
    }

    public void sort() {
        this.mStates = new ArrayList<>(this.mOriginalSwatches);
        Collections.sort(this.mStates, new Comparator<ProductVariantState>() {
            public int compare(ProductVariantState productVariantState, ProductVariantState productVariantState2) {
                if (productVariantState.isEnabled() && !productVariantState2.isEnabled()) {
                    return -1;
                }
                if (!productVariantState.isEnabled() && productVariantState2.isEnabled()) {
                    return 1;
                }
                if (productVariantState.mStateType == StateType.COLOR && productVariantState2.mStateType == StateType.TEXT) {
                    return -1;
                }
                if (productVariantState.mStateType == StateType.TEXT && productVariantState2.mStateType == StateType.COLOR) {
                    return 1;
                }
                return 0;
            }
        });
        notifyDataSetChanged();
    }
}
