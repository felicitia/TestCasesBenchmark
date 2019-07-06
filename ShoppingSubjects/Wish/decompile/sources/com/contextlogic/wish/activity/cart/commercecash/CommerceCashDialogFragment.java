package com.contextlogic.wish.activity.cart.commercecash;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.dialog.popupanimation.PopupAnimationDialogFragment;

public class CommerceCashDialogFragment<A extends BaseActivity> extends PopupAnimationDialogFragment {
    private String mMessage;

    public int getDimColor() {
        return R.color.transparent;
    }

    /* access modifiers changed from: protected */
    public int getLayout() {
        return R.layout.commerce_cash_popup_animation_holder;
    }

    public static CommerceCashDialogFragment<BaseActivity> createCommerceCashDialogFragment(String str) {
        CommerceCashDialogFragment<BaseActivity> commerceCashDialogFragment = new CommerceCashDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putString("ArgumentMesage", str);
        commerceCashDialogFragment.setArguments(bundle);
        return commerceCashDialogFragment;
    }

    /* access modifiers changed from: protected */
    public void handleArguments(Bundle bundle) {
        this.mMessage = bundle.getString("ArgumentMesage");
    }

    /* access modifiers changed from: protected */
    public View getPopupView() {
        return new CommerceCashPopupView(getContext(), this.mMessage);
    }

    /* access modifiers changed from: protected */
    public ViewGroup getPopupHolder(View view) {
        return (ViewGroup) view.findViewById(R.id.commerce_cash_popup_holder);
    }

    /* access modifiers changed from: protected */
    public int getPopupHeight() {
        return getResources().getDimensionPixelOffset(R.dimen.commerce_cash_popup_dialog_height);
    }
}
