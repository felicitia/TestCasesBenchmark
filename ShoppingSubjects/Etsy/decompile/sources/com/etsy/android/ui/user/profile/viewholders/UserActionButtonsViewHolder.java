package com.etsy.android.ui.user.profile.viewholders;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.UserProfileV3;
import com.etsy.android.ui.nav.b;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.d;
import com.etsy.android.uikit.view.ProgressButton;

public class UserActionButtonsViewHolder extends ViewHolder {
    final ProgressButton mFollowButton;
    private final ProgressButton mMessageButton;

    public UserActionButtonsViewHolder(View view) {
        super(view);
        this.mFollowButton = (ProgressButton) view.findViewById(R.id.follow_button);
        this.mMessageButton = (ProgressButton) view.findViewById(R.id.message_button);
        this.mMessageButton.setText((int) R.string.convo_message_hint);
    }

    public void bind(final UserProfileV3 userProfileV3, final FragmentActivity fragmentActivity) {
        updateFollowButtonState(userProfileV3.isFollowing());
        ((View) this.mMessageButton.getParent()).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                String loginName = userProfileV3.getLoginName();
                if (v.a().e()) {
                    Bundle bundle = new Bundle();
                    bundle.putString(ResponseConstants.USERNAME, loginName);
                    e.a(fragmentActivity).a().e(bundle);
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString(ResponseConstants.USERNAME, loginName);
                ((b) e.a(fragmentActivity).a().a(view)).a(EtsyAction.CONTACT_USER, bundle2);
            }
        });
        final com.etsy.android.ui.util.b bVar = new com.etsy.android.ui.util.b(fragmentActivity, this, "people_account");
        ((View) this.mFollowButton.getParent()).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (v.a().e()) {
                    bVar.a(userProfileV3.getUserId(), !userProfileV3.isFollowing(), new d.b() {
                        public void a() {
                            if (UserActionButtonsViewHolder.this.mFollowButton != null) {
                                UserActionButtonsViewHolder.this.mFollowButton.hideLoading();
                                userProfileV3.setIsFollowing(true);
                                UserActionButtonsViewHolder.this.updateFollowButtonState(true);
                            }
                        }

                        public void b() {
                            if (UserActionButtonsViewHolder.this.mFollowButton != null) {
                                UserActionButtonsViewHolder.this.mFollowButton.hideLoading();
                                userProfileV3.setIsFollowing(false);
                                UserActionButtonsViewHolder.this.updateFollowButtonState(false);
                            }
                        }
                    });
                } else {
                    ((b) e.a(fragmentActivity).a().a(view)).a(EtsyAction.FOLLOW, String.valueOf(userProfileV3.getUserId()));
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void updateFollowButtonState(boolean z) {
        this.mFollowButton.setText(z ? R.string.following : R.string.follow);
        this.mFollowButton.setDrawableLeft(z ? R.drawable.sk_ic_check : R.drawable.sk_ic_add);
        this.mFollowButton.setDrawableTint(this.mFollowButton.getResources().getColor(R.color.sk_gray_50));
    }
}
