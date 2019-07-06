package com.contextlogic.wish.activity.orderconfirmed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.invitecoupon.InviteCouponView;
import com.contextlogic.wish.activity.invitecoupon.InviteCouponView.OnDismissListener;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;

public class InviteCouponDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.invite_coupon_dialog_fragment, viewGroup, false);
        InviteCouponView inviteCouponView = (InviteCouponView) inflate.findViewById(R.id.invite_coupon_dialog_fragment_view);
        inviteCouponView.setOnDismissListener(new OnDismissListener() {
            public void onDismiss() {
                InviteCouponDialogFragment.this.cancel();
            }
        });
        inviteCouponView.setup(false);
        ((AutoReleasableImageView) inflate.findViewById(R.id.invite_coupon_dialog_fragment_x)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                InviteCouponDialogFragment.this.cancel();
            }
        });
        return inflate;
    }
}
