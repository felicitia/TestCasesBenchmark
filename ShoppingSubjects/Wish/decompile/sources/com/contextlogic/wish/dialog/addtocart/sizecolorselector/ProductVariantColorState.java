package com.contextlogic.wish.dialog.addtocart.sizecolorselector;

import com.contextlogic.wish.dialog.addtocart.sizecolorselector.ProductVariantState.DataType;
import com.contextlogic.wish.dialog.addtocart.sizecolorselector.ProductVariantState.StateType;
import java.util.List;

public class ProductVariantColorState extends ProductVariantState {
    private List<Integer> mColors;
    private boolean mForceImage;
    private int mForcedImageResId;

    public ProductVariantColorState(SizeColorSelectorDialogFragment sizeColorSelectorDialogFragment, String str, List<Integer> list) {
        super(sizeColorSelectorDialogFragment, DataType.COLOR, str);
        this.mStateType = StateType.COLOR;
        setEnabled(this.mDialogFragment.isColorInStock(str));
        setWishExpress(this.mDialogFragment.isExpressShippingEligibleForColor(str));
        this.mColors = list;
        this.mForceImage = false;
    }

    public ProductVariantColorState(SizeColorSelectorDialogFragment sizeColorSelectorDialogFragment, String str, int i) {
        super(sizeColorSelectorDialogFragment, DataType.COLOR, str);
        this.mStateType = StateType.COLOR;
        setEnabled(this.mDialogFragment.isColorInStock(str));
        setWishExpress(this.mDialogFragment.isExpressShippingEligibleForColor(str));
        this.mForceImage = true;
        this.mForcedImageResId = i;
    }

    public List<Integer> getColors() {
        return this.mColors;
    }

    public boolean getForceImage() {
        return this.mForceImage;
    }

    public int getForcedImageResId() {
        return this.mForcedImageResId;
    }
}
