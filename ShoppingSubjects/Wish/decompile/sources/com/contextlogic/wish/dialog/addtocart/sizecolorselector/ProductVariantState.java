package com.contextlogic.wish.dialog.addtocart.sizecolorselector;

public class ProductVariantState {
    private DataType mDataType;
    protected SizeColorSelectorDialogFragment mDialogFragment;
    private boolean mEnabled;
    private BaseProductVariantSwatch mListener;
    private String mName;
    private String mPrice;
    private boolean mSelected;
    private String mSelectedSize;
    protected StateType mStateType;
    private int mValueInPoints = -1;
    private boolean mWishExpress;

    public enum DataType {
        COLOR,
        SIZE
    }

    public enum StateType {
        COLOR,
        TEXT
    }

    public ProductVariantState(SizeColorSelectorDialogFragment sizeColorSelectorDialogFragment, DataType dataType, String str) {
        this.mDialogFragment = sizeColorSelectorDialogFragment;
        this.mDataType = dataType;
        this.mName = str;
        this.mStateType = StateType.TEXT;
        if (this.mDataType == DataType.COLOR) {
            setEnabled(this.mDialogFragment.isColorAndSelectedSizeInStock(str));
            setWishExpress(this.mDialogFragment.isExpressShippingEligibleForColor(str));
        } else if (this.mDataType == DataType.SIZE) {
            setEnabled(this.mDialogFragment.isSizeInStock(str));
            setWishExpress(this.mDialogFragment.isExpressShippingEligibleForSize(str));
        }
    }

    public void setSelected(boolean z) {
        this.mSelected = z;
        if (this.mListener != null) {
            this.mListener.setSelected(z);
        }
    }

    public void setOnSelectListener(BaseProductVariantSwatch baseProductVariantSwatch) {
        this.mListener = baseProductVariantSwatch;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
    }

    public void setWishExpress(boolean z) {
        this.mWishExpress = z;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    public String getName() {
        return this.mName;
    }

    public boolean getWishExpress() {
        return this.mWishExpress;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public void setPrice(String str) {
        this.mPrice = str;
    }

    public int getValueInPoints() {
        return this.mValueInPoints;
    }

    public boolean showValueInPoints() {
        return this.mValueInPoints > 0;
    }

    public void setValueInPoints(int i) {
        this.mValueInPoints = i;
    }

    public String getPreSelectedSizeSwatch() {
        if (this.mDataType == DataType.COLOR) {
            return this.mSelectedSize;
        }
        return null;
    }

    public void setPreSelectedSizeSwatch(String str) {
        if (this.mDataType == DataType.COLOR) {
            this.mSelectedSize = str;
        }
    }
}
