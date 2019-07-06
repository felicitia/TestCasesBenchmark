package com.contextlogic.wish.activity.managepayments;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishCreditCardInfo;
import com.contextlogic.wish.databinding.ManagePaymentCellBinding;
import com.contextlogic.wish.ui.recyclerview.BindingHolder;
import com.contextlogic.wish.ui.recyclerview.adapter.ItemModel;
import com.contextlogic.wish.ui.recyclerview.adapter.ViewHolderCreator;
import com.contextlogic.wish.util.CreditCardUtil;

public class ManagePaymentCellItemModel implements ItemModel<BindingHolder<ManagePaymentCellBinding>> {
    /* access modifiers changed from: private */
    public ManagePaymentCellItemModelCallback mCallback;
    private WishCreditCardInfo mCreditCardInfo;
    private boolean mHideBottomBoder;
    private boolean mIsSelected;
    private boolean mSelectable;
    private boolean mShowDeleteButton;

    public interface ManagePaymentCellItemModelCallback {
        void onDeleteClicked(ManagePaymentCellItemModel managePaymentCellItemModel);

        void onEditClicked(ManagePaymentCellItemModel managePaymentCellItemModel);

        void onSelected(ManagePaymentCellItemModel managePaymentCellItemModel);
    }

    public int getLayoutResId() {
        return R.layout.manage_payment_cell;
    }

    public void onViewRecycled(BindingHolder<ManagePaymentCellBinding> bindingHolder) {
    }

    public ManagePaymentCellItemModel(WishCreditCardInfo wishCreditCardInfo, ManagePaymentCellItemModelCallback managePaymentCellItemModelCallback) {
        this.mCreditCardInfo = wishCreditCardInfo;
        this.mCallback = managePaymentCellItemModelCallback;
    }

    public void setHideBottomBorder(boolean z) {
        this.mHideBottomBoder = z;
    }

    public void setSelectable(boolean z) {
        this.mSelectable = z;
    }

    public void setShowDeleteButton(boolean z) {
        this.mShowDeleteButton = z;
    }

    public void setIsSelected(boolean z) {
        this.mIsSelected = z;
    }

    public boolean isSelected() {
        return this.mIsSelected;
    }

    public WishCreditCardInfo getCreditCardInfo() {
        return this.mCreditCardInfo;
    }

    public ViewHolderCreator<BindingHolder<ManagePaymentCellBinding>> getViewHolderCreator() {
        return new ViewHolderCreator<BindingHolder<ManagePaymentCellBinding>>() {
            public BindingHolder<ManagePaymentCellBinding> createViewHolder(View view) {
                return new BindingHolder<>(view);
            }
        };
    }

    public void onBindViewHolder(BindingHolder<ManagePaymentCellBinding> bindingHolder) {
        Context context = bindingHolder.itemView.getContext();
        ManagePaymentCellBinding managePaymentCellBinding = (ManagePaymentCellBinding) bindingHolder.getBinding();
        managePaymentCellBinding.paymentCreditCardIcon.setImageResource(CreditCardUtil.cardImageForCardType(this.mCreditCardInfo.getCardType()));
        managePaymentCellBinding.paymentCreditCardNumberText.setText(context.getString(R.string.payment_visa_text, new Object[]{this.mCreditCardInfo.getLastFourDigits()}));
        managePaymentCellBinding.paymentEditText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ManagePaymentCellItemModel.this.mCallback.onEditClicked(ManagePaymentCellItemModel.this);
            }
        });
        int i = 8;
        managePaymentCellBinding.paymentPrimaryText.setVisibility(this.mIsSelected ? 0 : 8);
        if (this.mSelectable) {
            managePaymentCellBinding.paymentRadioButton.setVisibility(0);
            managePaymentCellBinding.paymentRadioButton.setChecked(this.mIsSelected);
            OnClickListener createSelectClickListener = createSelectClickListener();
            managePaymentCellBinding.getRoot().setOnClickListener(createSelectClickListener);
            managePaymentCellBinding.paymentRadioButton.setOnClickListener(createSelectClickListener);
        } else {
            managePaymentCellBinding.paymentRadioButton.setVisibility(8);
        }
        if (this.mShowDeleteButton) {
            managePaymentCellBinding.paymentDeleteText.setVisibility(0);
            managePaymentCellBinding.paymentDeleteText.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (ManagePaymentCellItemModel.this.mCallback != null) {
                        ManagePaymentCellItemModel.this.mCallback.onDeleteClicked(ManagePaymentCellItemModel.this);
                    }
                }
            });
        } else {
            managePaymentCellBinding.paymentDeleteText.setVisibility(8);
        }
        View view = managePaymentCellBinding.paymentBottomBorder;
        if (!this.mHideBottomBoder) {
            i = 0;
        }
        view.setVisibility(i);
    }

    private OnClickListener createSelectClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                ManagePaymentCellItemModel.this.mCallback.onSelected(ManagePaymentCellItemModel.this);
            }
        };
    }
}
