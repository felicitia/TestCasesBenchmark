package com.contextlogic.wish.dialog.addtocart;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ArrayAdapter;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.addtocart.AddToCartDialogFragment.AddToCartState;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;
import java.util.List;

public class AddToCartAdapter extends ArrayAdapter<String> {
    private List<String> mOptions;
    private WishProduct mProduct;
    private String mSelectedColor;
    private String mSelectedSize;
    private final Source mSource;
    private int mState;
    private ArrayList<AddToCartState> mStates;

    static class ViewHolder {
        AutoReleasableImageView fastShippingIcon;
        ThemedTextView option;
        ThemedTextView optionSubTitle;
        ThemedTextView rightSideCrossedOutInfoSection;
        ThemedTextView rightSideInfoSection;

        ViewHolder() {
        }
    }

    public AddToCartAdapter(Context context, WishProduct wishProduct, ArrayList<AddToCartState> arrayList, Source source) {
        super(context, R.layout.add_to_cart_dialog_fragment_row);
        this.mProduct = wishProduct;
        this.mSource = source;
        this.mStates = arrayList;
        setState(0);
    }

    private AddToCartState getState() {
        return (AddToCartState) this.mStates.get(this.mState);
    }

    private void setOptions(List<String> list) {
        this.mOptions = new ArrayList(list);
        if (ExperimentDataCenter.getInstance().shouldRemoveUnavailableItemSelections()) {
            ArrayList arrayList = new ArrayList();
            for (String str : this.mOptions) {
                if (isInStock(str)) {
                    arrayList.add(str);
                }
            }
            this.mOptions = arrayList;
        } else if (ExperimentDataCenter.getInstance().shouldPushDownUnavailableItemSelections()) {
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (String str2 : this.mOptions) {
                if (isInStock(str2)) {
                    arrayList2.add(str2);
                } else {
                    arrayList3.add(str2);
                }
            }
            this.mOptions = arrayList2;
            this.mOptions.addAll(arrayList3);
        }
        notifyDataSetChanged();
    }

    public void setState(int i) {
        this.mState = i;
        if (getState() == AddToCartState.COLOR) {
            setOptions(this.mProduct.getVariationColors());
        } else if (getState() == AddToCartState.SIZE) {
            setOptions(this.mProduct.getVariationSizes());
        }
    }

    public void setSelectedColor(String str) {
        this.mSelectedColor = str;
    }

    public String getSelectedColor() {
        return this.mSelectedColor;
    }

    public void setSelectedSize(String str) {
        this.mSelectedSize = str;
    }

    public String getSelectedSize() {
        return this.mSelectedSize;
    }

    public void resetSelectedSize() {
        setSelectedSize(null);
    }

    public void resetSelectedColor() {
        setSelectedColor(null);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.add_to_cart_dialog_fragment_row, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.option = (ThemedTextView) view.findViewById(R.id.add_to_cart_dialog_fragment_row_option);
            viewHolder.optionSubTitle = (ThemedTextView) view.findViewById(R.id.add_to_cart_dialog_fragment_row_option_subtitle);
            viewHolder.rightSideInfoSection = (ThemedTextView) view.findViewById(R.id.add_to_cart_dialog_fragment_row_right_side_info_section);
            viewHolder.rightSideCrossedOutInfoSection = (ThemedTextView) view.findViewById(R.id.add_to_cart_dialog_fragment_row_right_side_extra_info_section);
            viewHolder.fastShippingIcon = (AutoReleasableImageView) view.findViewById(R.id.add_to_cart_dialog_fragment_fast_shipping_image);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.option.setVisibility(0);
        viewHolder.optionSubTitle.setVisibility(8);
        viewHolder.rightSideInfoSection.setVisibility(8);
        viewHolder.rightSideCrossedOutInfoSection.setVisibility(8);
        viewHolder.fastShippingIcon.setVisibility(8);
        Resources resources = getContext().getResources();
        if (this.mProduct.getPriceChopProductDetail() != null && this.mProduct.getPriceChopProductDetail().isRunning()) {
            viewHolder.rightSideInfoSection.setTextColor(resources.getColor(R.color.price_chop_yellow));
            viewHolder.rightSideCrossedOutInfoSection.setTextColor(resources.getColor(R.color.price_chop_yellow));
        }
        String str = (String) this.mOptions.get(i);
        if (isExpressShippingEligible(str)) {
            viewHolder.fastShippingIcon.setVisibility(0);
        }
        viewHolder.option.setTextColor(getContext().getResources().getColor(R.color.text_primary));
        viewHolder.option.setPaintFlags(viewHolder.option.getPaintFlags() & -17);
        if (!isInStock(str)) {
            viewHolder.option.setPaintFlags(viewHolder.option.getPaintFlags() | 16);
            viewHolder.option.setTextColor(getContext().getResources().getColor(R.color.text_secondary));
        }
        if (isSelected(str)) {
            view.setBackgroundColor(getContext().getResources().getColor(R.color.light_gray_3));
        } else {
            view.setBackgroundResource(R.drawable.dialog_fragment_row_selector);
        }
        WishLocalizedCurrencyValue price = getPrice(str);
        int pointsValue = getPointsValue(str);
        if (this.mSource == Source.POINTS_REDEMPTION && pointsValue > 0) {
            viewHolder.rightSideInfoSection.setVisibility(0);
            viewHolder.rightSideInfoSection.setText(getContext().getString(R.string.pts_amount, new Object[]{Integer.valueOf(pointsValue)}));
            viewHolder.rightSideInfoSection.setTextColor(ContextCompat.getColor(getContext(), R.color.main_primary));
        } else if (price != null && this.mSource != Source.DAILY_GIVEAWAY) {
            viewHolder.rightSideInfoSection.setVisibility(0);
            if (this.mProduct.getPriceReplacementText() != null) {
                viewHolder.rightSideInfoSection.setText(this.mProduct.getPriceReplacementText());
            } else if (this.mSource == Source.FREE_GIFT || price.getValue() <= 0.0d) {
                viewHolder.rightSideInfoSection.setText(getContext().getString(R.string.free));
            } else {
                viewHolder.rightSideInfoSection.setText(price.toFormattedString());
            }
        } else if (price != null && ExperimentDataCenter.getInstance().shouldShowDailyRaffle() && price.getValue() > 0.0d) {
            viewHolder.rightSideInfoSection.setVisibility(0);
            viewHolder.rightSideInfoSection.setText(price.toFormattedString());
        }
        if (getRightSideExtraInfo(str) != null) {
            viewHolder.rightSideCrossedOutInfoSection.setVisibility(0);
            viewHolder.rightSideCrossedOutInfoSection.setText(this.mProduct.getGroupBuyRebateText());
            viewHolder.rightSideInfoSection.setTextSize(0, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.text_size_body));
            viewHolder.rightSideCrossedOutInfoSection.setTextSize(0, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.text_size_body));
        }
        view.setLayoutParams(new LayoutParams(-1, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.dialog_fragment_tall_row_height)));
        viewHolder.option.changeTypefaceToNormal();
        viewHolder.option.setText(str);
        int max = Math.max(Math.max(Math.max(0, getLeftRightPaddingDimension(viewHolder.fastShippingIcon)), getLeftRightPaddingDimension(viewHolder.rightSideInfoSection)), getLeftRightPaddingDimension(viewHolder.rightSideCrossedOutInfoSection));
        viewHolder.option.setPaddingRelative(max, 0, max, 0);
        return view;
    }

    public int getCount() {
        return this.mOptions.size();
    }

    public String getItem(int i) {
        int position = getPosition(i);
        if (this.mOptions == null || position < 0 || position >= this.mOptions.size()) {
            return null;
        }
        return (String) this.mOptions.get(position);
    }

    private int getLeftRightPaddingDimension(View view) {
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
        if (view.getVisibility() != 0) {
            return 0;
        }
        view.measure(0, 0);
        return view.getMeasuredWidth() + dimensionPixelSize;
    }

    public boolean isInStock(String str) {
        if (hasSizeOnly()) {
            return this.mProduct.isInStock(this.mProduct.getVariationId(str, null));
        }
        if (hasColorOnly()) {
            return this.mProduct.isInStock(this.mProduct.getVariationId(null, str));
        }
        if (getState() == AddToCartState.SIZE) {
            return this.mProduct.isSatisfyingVariationInStock(str, this.mSelectedColor);
        }
        return this.mProduct.isSatisfyingVariationInStock(this.mSelectedSize, str);
    }

    public boolean isExpressShippingEligible(String str) {
        if (hasSizeOnly()) {
            return this.mProduct.isExpressShippingEligible(this.mProduct.getVariationId(str, null));
        }
        if (hasColorOnly()) {
            return this.mProduct.isExpressShippingEligible(this.mProduct.getVariationId(null, str));
        }
        if (getState() == AddToCartState.SIZE) {
            return this.mProduct.isSatisfyingVariationExpressShippingEligible(str, this.mSelectedColor);
        }
        return this.mProduct.isSatisfyingVariationExpressShippingEligible(this.mSelectedSize, str);
    }

    private WishLocalizedCurrencyValue getPrice(String str) {
        String str2;
        if (hasSizeOnly()) {
            str2 = this.mProduct.getVariationId(str, null);
        } else if (hasColorOnly()) {
            str2 = this.mProduct.getVariationId(null, str);
        } else if (this.mSelectedSize == null && this.mSelectedColor == null) {
            return null;
        } else {
            if (getState() == AddToCartState.SIZE) {
                str2 = this.mProduct.getVariationId(str, this.mSelectedColor);
            } else {
                str2 = this.mProduct.getVariationId(this.mSelectedSize, str);
            }
        }
        if (str2 == null) {
            return null;
        }
        if (this.mSource == Source.GROUP_BUY_JOIN) {
            return this.mProduct.getVariationGroupPrice(str2);
        }
        return this.mProduct.getVariationPrice(str2);
    }

    private int getPointsValue(String str) {
        String str2;
        if (hasSizeOnly()) {
            str2 = this.mProduct.getVariationId(str, null);
        } else if (hasColorOnly()) {
            str2 = this.mProduct.getVariationId(null, str);
        } else if (this.mSelectedSize == null && this.mSelectedColor == null) {
            return -1;
        } else {
            if (getState() == AddToCartState.SIZE) {
                str2 = this.mProduct.getVariationId(str, this.mSelectedColor);
            } else {
                str2 = this.mProduct.getVariationId(this.mSelectedSize, str);
            }
        }
        if (str2 != null) {
            return this.mProduct.getValueInPointsForVariation(str2);
        }
        return -1;
    }

    private String getRightSideExtraInfo(String str) {
        String str2;
        if (hasSizeOnly()) {
            str2 = this.mProduct.getVariationId(str, null);
        } else if (hasColorOnly()) {
            str2 = this.mProduct.getVariationId(null, str);
        } else if (this.mSelectedSize == null && this.mSelectedColor == null) {
            return null;
        } else {
            if (getState() == AddToCartState.SIZE) {
                str2 = this.mProduct.getVariationId(str, this.mSelectedColor);
            } else {
                str2 = this.mProduct.getVariationId(this.mSelectedSize, str);
            }
        }
        if (str2 == null || this.mSource != Source.GROUP_BUY_CREATE) {
            return null;
        }
        return this.mProduct.getGroupBuyRebateText();
    }

    private boolean isSelected(String str) {
        boolean z = true;
        if (getState() == AddToCartState.SIZE) {
            if (this.mSelectedSize == null || !this.mSelectedSize.equals(str)) {
                z = false;
            }
            return z;
        } else if (getState() != AddToCartState.COLOR) {
            return false;
        } else {
            if (this.mSelectedColor == null || !this.mSelectedColor.equals(str)) {
                z = false;
            }
            return z;
        }
    }

    public boolean hasSizeOnly() {
        boolean z = false;
        if (this.mSelectedColor != null) {
            return false;
        }
        if (this.mStates.contains(AddToCartState.SIZE) && !this.mStates.contains(AddToCartState.COLOR)) {
            z = true;
        }
        return z;
    }

    private boolean hasColorOnly() {
        boolean z = false;
        if (this.mSelectedSize != null) {
            return false;
        }
        if (this.mStates.contains(AddToCartState.COLOR) && !this.mStates.contains(AddToCartState.SIZE)) {
            z = true;
        }
        return z;
    }

    private int getPosition(int i) {
        return i - (this.mSource.equals(Source.GROUP_BUY_CREATE) ? 1 : 0);
    }

    public String getHeaderText() {
        if (!this.mSource.equals(Source.GROUP_BUY_CREATE) || this.mOptions == null || this.mOptions.size() <= 0 || getRightSideExtraInfo((String) this.mOptions.get(0)) == null) {
            return null;
        }
        return this.mProduct.getGroupBuyAddToCartModalText();
    }
}
