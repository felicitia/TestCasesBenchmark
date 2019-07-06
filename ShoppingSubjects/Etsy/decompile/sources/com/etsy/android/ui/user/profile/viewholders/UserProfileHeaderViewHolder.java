package com.etsy.android.ui.user.profile.viewholders;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.apiv3.UserProfileV3;
import com.etsy.android.lib.util.af;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.text.ClickableSpanTouchListener;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.text.NumberFormat;

public class UserProfileHeaderViewHolder extends ViewHolder {
    private final View mAdminBadge;
    private final ImageView mAvatar;
    private final int mAvatarWidth;
    boolean mDidInitialBind;
    private final TextView mFollowersButton;
    private final TextView mFollowingButton;
    private final TextView mLocation;
    private final TextView mName;
    final TextView mUserBio;
    final View mUserBioReadMore;

    public UserProfileHeaderViewHolder(View view) {
        super(view);
        this.mAvatar = (ImageView) view.findViewById(R.id.avatar);
        this.mName = (TextView) view.findViewById(R.id.username);
        this.mLocation = (TextView) view.findViewById(R.id.user_location);
        this.mAdminBadge = view.findViewById(R.id.admin_badge);
        this.mFollowersButton = (TextView) view.findViewById(R.id.followers_button);
        this.mFollowingButton = (TextView) view.findViewById(R.id.following_button);
        this.mUserBio = (TextView) view.findViewById(R.id.user_bio);
        this.mUserBioReadMore = view.findViewById(R.id.user_bio_read_more);
        this.mAvatarWidth = view.getResources().getDimensionPixelOffset(R.dimen.user_avatar_image_large);
    }

    public void bind(final UserProfileV3 userProfileV3, boolean z, c cVar, @NonNull final FragmentActivity fragmentActivity) {
        if (userProfileV3 != null) {
            String displayName = userProfileV3.getDisplayName();
            String location = userProfileV3.getLocation();
            this.mAdminBadge.setVisibility(userProfileV3.isAdmin() ? 0 : 8);
            cVar.b(userProfileV3.getAvatarUrl(), this.mAvatar, this.mAvatarWidth);
            this.mName.setText(displayName);
            NumberFormat instance = NumberFormat.getInstance();
            Resources resources = fragmentActivity.getResources();
            int followerCount = userProfileV3.getFollowerCount();
            this.mFollowersButton.setText(resources.getQuantityString(R.plurals.follower_counts, followerCount, new Object[]{instance.format((long) followerCount)}));
            this.mFollowersButton.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    e.a(fragmentActivity).a().a(userProfileV3.getUserId(), userProfileV3.getLoginName(), 1);
                }
            });
            this.mFollowingButton.setText(resources.getString(R.string.following_count, new Object[]{instance.format((long) userProfileV3.getFollowingCount())}));
            this.mFollowingButton.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    e.a(fragmentActivity).a().a(userProfileV3.getUserId(), userProfileV3.getLoginName(), 0);
                }
            });
            if (!this.mDidInitialBind) {
                if (af.a(location)) {
                    this.mLocation.setVisibility(0);
                    this.mLocation.setText(location);
                } else {
                    this.mLocation.setVisibility(8);
                }
                final String trim = userProfileV3.getBio().trim();
                if (!TextUtils.isEmpty(trim)) {
                    this.mUserBio.setVisibility(0);
                    this.mUserBio.setText(trim);
                    EtsyLinkify.a((Context) fragmentActivity, this.mUserBio);
                    this.mUserBio.setMovementMethod(null);
                    this.mUserBio.setClickable(true);
                    this.mUserBio.setOnTouchListener(new ClickableSpanTouchListener());
                    this.mUserBio.post(new Runnable() {
                        public void run() {
                            boolean z = true;
                            UserProfileHeaderViewHolder.this.mDidInitialBind = true;
                            Layout layout = UserProfileHeaderViewHolder.this.mUserBio.getLayout();
                            if ((layout == null || layout.getLineCount() <= 2) && layout != null) {
                                z = false;
                            }
                            if (z) {
                                UserProfileHeaderViewHolder.this.mUserBioReadMore.setVisibility(0);
                                UserProfileHeaderViewHolder.this.mUserBioReadMore.setOnClickListener(new TrackingOnClickListener() {
                                    public void onViewClick(View view) {
                                        e.a(fragmentActivity).a().b(fragmentActivity.getResources().getString(R.string.about), trim);
                                    }
                                });
                            }
                        }
                    });
                } else {
                    this.mUserBio.setVisibility(8);
                }
            }
        }
    }
}
