package com.contextlogic.wish.activity.profile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.button.FollowButton;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.button.ToggleLoadingButton;
import com.contextlogic.wish.ui.button.ToggleLoadingButton.ButtonMode;
import com.contextlogic.wish.ui.button.ToggleLoadingButton.OnToggleLoadingButtonClickListener;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.listview.ListViewTabStrip;
import com.contextlogic.wish.ui.listview.ListViewTabStrip.OnTabClickListener;
import com.contextlogic.wish.ui.view.ProfileImageView;
import com.contextlogic.wish.ui.view.SlidingTabStrip.TabType;
import com.contextlogic.wish.util.AddressUtil;
import com.contextlogic.wish.util.FontUtil;
import java.util.ArrayList;

public class ProfileHeaderView extends RelativeLayout implements OnToggleLoadingButtonClickListener {
    private NetworkImageView mBackgroundProfileImage;
    private Context mContext;
    private TextView mCountryText;
    private View mEditProfilePictureButton;
    private FollowButton mFollowButton;
    private View mFollowerArea;
    private TextView mFollowerCount;
    private TextView mFollowerText;
    private View mFollowingArea;
    private TextView mFollowingText;
    private TextView mNameText;
    /* access modifiers changed from: private */
    public ProfileHeaderInterface mProfileHeaderInterface;
    private RelativeLayout mProfileImageHeader;
    private ProfileImageView mProfileImageView;
    private TextView mSavesCountText;
    private TextView mSavesText;
    /* access modifiers changed from: private */
    public int mSelectedTabIndex;
    private boolean mShouldShowFollowedWishlists;
    private TextView mStarBadgeText;
    private ListViewTabStrip mTabStrip;
    private RelativeLayout mTopArea;
    private WishUser mUser;
    private View mWishlistSectionContainer;
    /* access modifiers changed from: private */
    public ThemedButton mWishlistSectionSwitchFollowed;
    /* access modifiers changed from: private */
    public ThemedButton mWishlistSectionSwitchMine;

    public enum HeaderTabState {
        WISHLISTS,
        REVIEWS,
        PHOTOS
    }

    interface ProfileHeaderInterface {
        void handleFollowButtonClicked(boolean z);

        void handleFollowedWishlistClicked();

        void handleFollowerClicked();

        void handleFollowingClicked();

        void handleMyWishlistClicked();

        void handleProfilePictureClicked();

        boolean isCurrentUser();

        void tabSelected(HeaderTabState headerTabState);
    }

    public enum WishlistSectionState {
        USER,
        FOLLOWED
    }

    public ProfileHeaderView(Context context) {
        this(context, null);
    }

    public ProfileHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.profile_fragment_header, this);
        this.mTabStrip = (ListViewTabStrip) inflate.findViewById(R.id.profile_fragment_tab_strip);
        this.mTabStrip.setTabTypes(new TabType[]{TabType.TEXT_TAB, TabType.TEXT_TAB, TabType.TEXT_TAB});
        this.mTabStrip.setAlignJustify(true);
        this.mSelectedTabIndex = 0;
        this.mProfileImageView = (ProfileImageView) inflate.findViewById(R.id.profile_fragment_profile_image_view);
        this.mBackgroundProfileImage = (NetworkImageView) inflate.findViewById(R.id.profile_fragment_header_profile_image_background);
        this.mStarBadgeText = (TextView) inflate.findViewById(R.id.wish_star_profile_text);
        this.mNameText = (TextView) inflate.findViewById(R.id.profile_fragment_header_name_text);
        this.mFollowButton = (FollowButton) inflate.findViewById(R.id.wish_star_profile_follow_button);
        this.mEditProfilePictureButton = inflate.findViewById(R.id.profile_fragment_header_edit_profile_picture_button);
        this.mFollowerCount = (TextView) inflate.findViewById(R.id.profile_fragment_header_follower_count);
        this.mFollowingText = (TextView) inflate.findViewById(R.id.profile_fragment_header_following_count);
        this.mCountryText = (TextView) inflate.findViewById(R.id.profile_fragment_header_location_text);
        this.mSavesCountText = (TextView) inflate.findViewById(R.id.profile_fragment_header_saves_count_text);
        this.mSavesText = (TextView) inflate.findViewById(R.id.profile_fragment_saves_text);
        this.mFollowerText = (TextView) inflate.findViewById(R.id.profile_fragment_header_follower_string);
        this.mProfileImageHeader = (RelativeLayout) inflate.findViewById(R.id.profile_fragment_header_image_container);
        this.mTopArea = (RelativeLayout) inflate.findViewById(R.id.layoutTop);
        this.mFollowerArea = inflate.findViewById(R.id.profile_fragment_header_follower_section);
        this.mFollowingArea = inflate.findViewById(R.id.profile_fragment_header_following_section);
        this.mWishlistSectionContainer = inflate.findViewById(R.id.profile_frgment_switch_wishlist_section_button_container);
        this.mWishlistSectionSwitchMine = (ThemedButton) inflate.findViewById(R.id.profile_frgment_switch_wishlist_section_button_mine);
        this.mWishlistSectionSwitchFollowed = (ThemedButton) inflate.findViewById(R.id.profile_frgment_switch_wishlist_section_button_followed);
    }

    public void setup(WishUser wishUser, ProfileHeaderInterface profileHeaderInterface, ArrayList<? extends BaseAdapter> arrayList) {
        this.mUser = wishUser;
        this.mProfileHeaderInterface = profileHeaderInterface;
        this.mStarBadgeText.setVisibility(wishUser.isWishStar() ? 0 : 8);
        this.mTabStrip.setup(arrayList, new OnTabClickListener() {
            public void onTabSelected(int i) {
                HeaderTabState headerTabState = HeaderTabState.WISHLISTS;
                if (i == HeaderTabState.WISHLISTS.ordinal()) {
                    headerTabState = HeaderTabState.WISHLISTS;
                } else if (i == HeaderTabState.REVIEWS.ordinal()) {
                    headerTabState = HeaderTabState.REVIEWS;
                } else if (i == HeaderTabState.PHOTOS.ordinal()) {
                    headerTabState = HeaderTabState.PHOTOS;
                }
                ProfileHeaderView.this.mSelectedTabIndex = i;
                ProfileHeaderView.this.onSelectTab(headerTabState);
            }
        });
        this.mNameText.setText(this.mUser.getFirstName());
        if (profileHeaderInterface.isCurrentUser()) {
            this.mEditProfilePictureButton.setVisibility(0);
        } else {
            this.mEditProfilePictureButton.setVisibility(8);
        }
        if (ExperimentDataCenter.getInstance().shouldSeeFollowingZeroState() && !this.mProfileHeaderInterface.isCurrentUser()) {
            this.mFollowButton.setButtonMode(wishUser.isFollowing() ? ButtonMode.Selected : ButtonMode.Unselected);
            this.mFollowButton.setOnFollowButtonClickListener(this);
            this.mFollowButton.setVisibility(0);
        }
        this.mFollowerCount.setText(Integer.toString(this.mUser.getFollowersCount()));
        this.mFollowerText.setText(WishApplication.getInstance().getResources().getQuantityString(R.plurals.number_of_followers, this.mUser.getWishesCount()));
        if (this.mProfileHeaderInterface.isCurrentUser()) {
            this.mSavesCountText.setText(Integer.toString(this.mUser.getWishesCount()));
        } else {
            this.mSavesCountText.setText(Integer.toString(this.mUser.getPublicWishesCount()));
        }
        this.mSavesText.setText(WishApplication.getInstance().getResources().getQuantityString(R.plurals.number_of_saves, this.mUser.getWishesCount()));
        this.mFollowingText.setText(Integer.toString(this.mUser.getFollowingCount()));
        if (this.mUser.getCountryCode() != null) {
            this.mCountryText.setVisibility(0);
            this.mCountryText.setText(AddressUtil.getCountryName(this.mUser.getCountryCode()));
        } else {
            this.mCountryText.setVisibility(8);
        }
        this.mProfileImageView.setup(this.mUser.getProfileImage(), this.mUser.getName());
        if (!(this.mBackgroundProfileImage == null || this.mUser.getProfileImage() == null)) {
            this.mBackgroundProfileImage.setImage(this.mUser.getProfileImage());
        }
        this.mProfileImageHeader.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProfileHeaderView.this.mProfileHeaderInterface.handleProfilePictureClicked();
            }
        });
        this.mFollowerArea.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProfileHeaderView.this.mProfileHeaderInterface.handleFollowerClicked();
            }
        });
        this.mFollowingArea.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProfileHeaderView.this.mProfileHeaderInterface.handleFollowingClicked();
            }
        });
        this.mTopArea.getLayoutParams().height = (int) (((double) this.mContext.getResources().getDisplayMetrics().heightPixels) * 0.15d);
        this.mTopArea.invalidate();
        this.mTopArea.requestLayout();
        customizeTabStrip();
        refreshTabStripFontColors();
        this.mWishlistSectionSwitchMine.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!ProfileHeaderView.this.mWishlistSectionSwitchMine.isSelected()) {
                    ProfileHeaderView.this.mProfileHeaderInterface.handleMyWishlistClicked();
                    ProfileHeaderView.this.updateWishlistSectionTab(false);
                }
            }
        });
        this.mWishlistSectionSwitchFollowed.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!ProfileHeaderView.this.mWishlistSectionSwitchFollowed.isSelected()) {
                    ProfileHeaderView.this.mProfileHeaderInterface.handleFollowedWishlistClicked();
                    ProfileHeaderView.this.updateWishlistSectionTab(true);
                }
            }
        });
        this.mWishlistSectionSwitchMine.setSelected(true);
        this.mShouldShowFollowedWishlists = this.mUser.getUserId().equals(ProfileDataCenter.getInstance().getUserId());
        if (this.mShouldShowFollowedWishlists) {
            this.mWishlistSectionContainer.setVisibility(0);
        } else {
            this.mWishlistSectionContainer.setVisibility(8);
        }
    }

    public void onToggleLoadingButtonClicked(ToggleLoadingButton toggleLoadingButton, boolean z) {
        this.mProfileHeaderInterface.handleFollowButtonClicked(z);
    }

    /* access modifiers changed from: private */
    public void onSelectTab(HeaderTabState headerTabState) {
        refreshTabStripFontColors();
        this.mProfileHeaderInterface.tabSelected(headerTabState);
        if (headerTabState != HeaderTabState.WISHLISTS || !this.mShouldShowFollowedWishlists) {
            this.mWishlistSectionContainer.setVisibility(8);
        } else {
            this.mWishlistSectionContainer.setVisibility(0);
        }
    }

    public void updateWishlistSectionTab(boolean z) {
        this.mWishlistSectionSwitchMine.setSelected(!z);
        this.mWishlistSectionSwitchFollowed.setSelected(z);
        this.mWishlistSectionSwitchMine.setTypeface(FontUtil.getTypefaceForStyle(z ^ true ? 1 : 0));
        this.mWishlistSectionSwitchFollowed.setTypeface(FontUtil.getTypefaceForStyle(z ? 1 : 0));
    }

    public void updateProfileImage(WishUser wishUser) {
        this.mUser = wishUser;
        if (this.mProfileImageView.getProfileImage() != null) {
            this.mProfileImageView.updateProfileImage(this.mUser.getProfileImage());
        }
        if (this.mBackgroundProfileImage != null) {
            this.mBackgroundProfileImage.setImage(this.mUser.getProfileImage());
        }
    }

    public void customizeTabStrip() {
        int dimensionPixelOffset = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.tab_strip_indicator_height);
        int dimensionPixelOffset2 = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.tab_strip_underline_height);
        int dimensionPixelOffset3 = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.tab_strip_text_size);
        this.mTabStrip.setBackgroundResource(R.color.white);
        this.mTabStrip.setIndicatorColorResource(R.color.main_primary);
        this.mTabStrip.setDividerColorResource(R.color.white);
        this.mTabStrip.setTextColorResource(R.color.gray1);
        this.mTabStrip.setUnderlineHeight(dimensionPixelOffset2);
        this.mTabStrip.setUnderlineColorResource(R.color.gray5);
        this.mTabStrip.setIndicatorHeight(dimensionPixelOffset);
        this.mTabStrip.setTextSize(dimensionPixelOffset3);
        LinearLayout linearLayout = (LinearLayout) this.mTabStrip.getChildAt(0);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            ((TextView) linearLayout.getChildAt(i)).setSingleLine(false);
        }
    }

    public void refreshTabStripFontColors() {
        LinearLayout linearLayout = (LinearLayout) this.mTabStrip.getChildAt(0);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i);
            if (i == this.mSelectedTabIndex) {
                textView.setTextColor(getResources().getColor(R.color.gray1));
                textView.setTypeface(FontUtil.getTypefaceForStyle(1));
            } else {
                textView.setTextColor(getResources().getColor(R.color.gray4));
                textView.setTypeface(FontUtil.getTypefaceForStyle(0));
            }
        }
    }

    public void setFollowButtonMode(ButtonMode buttonMode) {
        this.mFollowButton.setButtonMode(buttonMode);
    }
}
