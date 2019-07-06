package com.contextlogic.wish.activity.invitepopup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.invitecoupon.InviteCouponView;
import com.contextlogic.wish.activity.invitecoupon.InviteCouponView.OnDismissListener;
import com.contextlogic.wish.activity.invitecoupon.InviteCouponView.OnNeverShowListener;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;

public class InviteCouponPopupDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    public int getDialogHeight() {
        return -1;
    }

    public int getDialogWidth() {
        return -1;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.invite_coupon_popup_dialog_fragment, viewGroup, false);
        InviteCouponView inviteCouponView = (InviteCouponView) inflate.findViewById(R.id.invite_coupon_popup_dialog_fragment_view);
        inviteCouponView.setOnDismissListener(new OnDismissListener() {
            public void onDismiss() {
                InviteCouponPopupDialogFragment.this.cancel();
            }
        });
        inviteCouponView.setup(true);
        inviteCouponView.setOnNeverShowListener(new OnNeverShowListener() {
            public void onNeverShow() {
                InviteCouponPopupDialogFragment.this.withServiceFragment(new ServiceTask<BaseActivity, ServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                        serviceFragment.setNeverShowInviteCoupon();
                    }
                });
            }
        });
        ((AutoReleasableImageView) inflate.findViewById(R.id.invite_coupon_popup_dialog_fragment_x)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                InviteCouponPopupDialogFragment.this.cancel();
            }
        });
        return inflate;
    }
}
