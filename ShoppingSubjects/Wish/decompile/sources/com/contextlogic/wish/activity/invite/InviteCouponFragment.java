package com.contextlogic.wish.activity.invite;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.invitecoupon.InviteCouponView;
import com.contextlogic.wish.activity.invitecoupon.InviteCouponView.OnDismissListener;

public class InviteCouponFragment extends UiFragment<InviteCouponActivity> {
    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.invite_coupon_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        InviteCouponView inviteCouponView = (InviteCouponView) findViewById(R.id.invite_coupon_fragment_view);
        inviteCouponView.setOnDismissListener(new OnDismissListener() {
            public void onDismiss() {
                InviteCouponFragment.this.withActivity(new ActivityTask<InviteCouponActivity>() {
                    public void performTask(InviteCouponActivity inviteCouponActivity) {
                        inviteCouponActivity.finishActivity();
                    }
                });
            }
        });
        inviteCouponView.hideDismiss();
        inviteCouponView.setup(true);
    }
}
