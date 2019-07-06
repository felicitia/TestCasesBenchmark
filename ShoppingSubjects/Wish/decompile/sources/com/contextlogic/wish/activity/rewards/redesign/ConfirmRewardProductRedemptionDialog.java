package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.cart.shipping.AddressBookAdapter.AddressBookType;
import com.contextlogic.wish.activity.cart.shipping.AddressBookRowView;
import com.contextlogic.wish.activity.cart.shipping.StandaloneManageAddressesActivity;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.IntentUtil;

public class ConfirmRewardProductRedemptionDialog<A extends BaseActivity> extends BaseDialogFragment<A> {
    private AddressBookRowView mAddressRowView;

    public static ConfirmRewardProductRedemptionDialog create(int i, String str, WishShippingInfo wishShippingInfo) {
        Bundle bundle = new Bundle();
        bundle.putInt("ArgValueInPoints", i);
        bundle.putString("ArgImageUrl", str);
        bundle.putParcelable("ArgShipingInfo", wishShippingInfo);
        ConfirmRewardProductRedemptionDialog confirmRewardProductRedemptionDialog = new ConfirmRewardProductRedemptionDialog();
        confirmRewardProductRedemptionDialog.setArguments(bundle);
        return confirmRewardProductRedemptionDialog;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        int i = arguments.getInt("ArgValueInPoints", -1);
        String string = arguments.getString("ArgImageUrl");
        WishShippingInfo wishShippingInfo = (WishShippingInfo) arguments.getParcelable("ArgShipingInfo");
        if (i <= 0 || TextUtils.isEmpty(string) || wishShippingInfo == null) {
            return null;
        }
        View inflate = layoutInflater.inflate(R.layout.confirm_reward_product_redemption_dialog, viewGroup, false);
        String quantityString = getResources().getQuantityString(R.plurals.redeem_for_points_question, i, new Object[]{Integer.valueOf(i)});
        String string2 = getString(R.string.redeem_cannot_be_undone);
        View findViewById = inflate.findViewById(R.id.confirm_reward_product_redemption_dialog_x);
        NetworkImageView networkImageView = (NetworkImageView) inflate.findViewById(R.id.confirm_reward_product_redemption_dialog_image);
        ThemedTextView themedTextView = (ThemedTextView) inflate.findViewById(R.id.confirm_reward_product_redemption_dialog_title);
        ThemedTextView themedTextView2 = (ThemedTextView) inflate.findViewById(R.id.confirm_reward_product_redemption_dialog_subtitle);
        this.mAddressRowView = (AddressBookRowView) inflate.findViewById(R.id.confirm_reward_product_redemption_dialog_address);
        View findViewById2 = inflate.findViewById(R.id.confirm_reward_product_redemption_dialog_done_button);
        findViewById.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ConfirmRewardProductRedemptionDialog.this.dismissAllowingStateLoss();
            }
        });
        networkImageView.setImageUrl(string);
        themedTextView.setText(quantityString);
        themedTextView2.setText(string2);
        updateCurrentShippingInfo(wishShippingInfo);
        this.mAddressRowView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ConfirmRewardProductRedemptionDialog.this.startStandaloneManagerAddressesActivity();
            }
        });
        findViewById2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ConfirmRewardProductRedemptionDialog.this.makeSelection(1);
            }
        });
        return inflate;
    }

    /* access modifiers changed from: private */
    public void startStandaloneManagerAddressesActivity() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                Intent intent = new Intent();
                int addResultCodeCallback = a.addResultCodeCallback(new ActivityResultCallback() {
                    public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                        WishShippingInfo wishShippingInfo = IntentUtil.safeToUnparcel(intent) ? (WishShippingInfo) intent.getParcelableExtra("ResultExtraCurrentShipping") : null;
                        if (wishShippingInfo != null) {
                            ConfirmRewardProductRedemptionDialog.this.updateCurrentShippingInfo(wishShippingInfo);
                        }
                    }
                });
                intent.setClass(a, StandaloneManageAddressesActivity.class);
                a.startActivityForResult(intent, addResultCodeCallback);
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateCurrentShippingInfo(WishShippingInfo wishShippingInfo) {
        if (this.mAddressRowView != null) {
            this.mAddressRowView.setup(wishShippingInfo, AddressBookType.rewardConfirmation, null);
        }
    }
}
