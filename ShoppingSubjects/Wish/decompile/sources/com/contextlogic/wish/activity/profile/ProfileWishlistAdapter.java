package com.contextlogic.wish.activity.profile;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.profile.wishlist.WishlistHelper;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.WishTooltip;
import com.contextlogic.wish.dialog.WishTooltip.WishTooltipListener;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.listview.ListViewTabStrip.TextTabProvider;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import com.contextlogic.wish.util.TabletUtil;
import java.util.ArrayList;

public class ProfileWishlistAdapter extends ArrayAdapter<WishWishlist> implements TextTabProvider {
    /* access modifiers changed from: private */
    public Context mContext;
    protected ImageHttpPrefetcher mImagePrefetcher;
    protected ListView mListView;
    /* access modifiers changed from: private */
    public ProfileFragment mProfileFragment;
    private boolean mTooltipScheduled;
    protected ArrayList<WishWishlist> mWishlists = new ArrayList<>();

    static class ItemRowHolder {
        ArrayList<NetworkImageView> imageViews;
        ImageView privateIcon;
        LinearLayout row1;
        LinearLayout row2;
        TextView subTitleText;
        TextView titleText;
        TextView viewCountText;

        ItemRowHolder() {
        }

        public NetworkImageView getImage(int i) {
            return (NetworkImageView) this.imageViews.get(i);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public ProfileWishlistAdapter(Context context, ListView listView, ProfileFragment profileFragment) {
        super(context, R.layout.profile_fragment_my_wishlist_row);
        this.mContext = context;
        this.mProfileFragment = profileFragment;
        this.mListView = listView;
    }

    public void setWishlists(ArrayList<WishWishlist> arrayList) {
        this.mWishlists = arrayList;
        notifyDataSetChanged();
    }

    public int getCount() {
        if (this.mWishlists == null) {
            return 0;
        }
        return this.mWishlists.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        if (view == null) {
            view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.profile_fragment_my_wishlist_row, viewGroup, false);
            itemRowHolder = new ItemRowHolder();
            itemRowHolder.titleText = (TextView) view.findViewById(R.id.fragment_wishlist_profile_item_title);
            itemRowHolder.subTitleText = (TextView) view.findViewById(R.id.fragment_wishlist_profile_item_sub_title);
            itemRowHolder.viewCountText = (TextView) view.findViewById(R.id.fragment_wishlist_profile_item_view_count);
            itemRowHolder.row1 = (LinearLayout) view.findViewById(R.id.fragment_wishlist_profile_item_images_row1);
            itemRowHolder.row2 = (LinearLayout) view.findViewById(R.id.fragment_wishlist_profile_item_images_row2);
            itemRowHolder.privateIcon = (ImageView) view.findViewById(R.id.fragment_wishlist_private_icon);
            float dimension = this.mProfileFragment.getResources().getDimension(R.dimen.eight_padding);
            LayoutParams layoutParams = new LayoutParams(-1, (int) ((((float) DisplayUtil.getDisplayWidth()) - (((float) (getNumColumn() - 1)) * dimension)) / ((float) getNumColumn())));
            itemRowHolder.row1.setLayoutParams(layoutParams);
            itemRowHolder.row2.setLayoutParams(layoutParams);
            itemRowHolder.imageViews = new ArrayList<>();
            int i2 = 0;
            while (i2 < getNumColumn() * 2) {
                NetworkImageView networkImageView = new NetworkImageView(getContext());
                LayoutParams layoutParams2 = new LayoutParams(0, -1);
                layoutParams2.weight = 1.0f;
                layoutParams2.setMargins((i2 == 0 || i2 == getNumColumn()) ? 0 : (int) dimension, 0, 0, (int) dimension);
                networkImageView.setLayoutParams(layoutParams2);
                networkImageView.setImagePrefetcher(this.mImagePrefetcher);
                itemRowHolder.imageViews.add(networkImageView);
                if (i2 < getNumColumn()) {
                    itemRowHolder.row1.addView(networkImageView);
                } else {
                    itemRowHolder.row2.addView(networkImageView);
                }
                i2++;
            }
            view.setTag(itemRowHolder);
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        final WishWishlist wishWishlist = (WishWishlist) this.mWishlists.get(i);
        itemRowHolder.titleText.setText(wishWishlist.getName());
        itemRowHolder.subTitleText.setText(WishApplication.getInstance().getResources().getQuantityString(R.plurals.item, wishWishlist.getProductCount(), new Object[]{Integer.valueOf(wishWishlist.getProductCount())}));
        if (!wishWishlist.isPrivate()) {
            itemRowHolder.privateIcon.setVisibility(8);
        } else {
            itemRowHolder.privateIcon.setVisibility(0);
        }
        if (wishWishlist.getUserId().equals(ProfileDataCenter.getInstance().getUserId())) {
            itemRowHolder.viewCountText.setVisibility(0);
            itemRowHolder.viewCountText.setText(WishApplication.getInstance().getResources().getQuantityString(R.plurals.wishlist_view_count, wishWishlist.getViewCount(), new Object[]{Integer.valueOf(wishWishlist.getViewCount())}));
        } else {
            itemRowHolder.viewCountText.setVisibility(8);
        }
        for (int i3 = 0; i3 < itemRowHolder.imageViews.size(); i3++) {
            itemRowHolder.getImage(i3).setImage(null);
            itemRowHolder.getImage(i3).setImageBitmap(null);
            itemRowHolder.getImage(i3).setBackgroundColor(getContext().getResources().getColor(R.color.light_gray_3));
        }
        ArrayList productPreviews = wishWishlist.getProductPreviews();
        int i4 = 0;
        while (i4 < wishWishlist.getProductPreviews().size() && i4 < itemRowHolder.imageViews.size()) {
            itemRowHolder.getImage(i4).setImage(((WishProduct) productPreviews.get(i4)).getImage());
            i4++;
        }
        final WishlistMenuItemClickListener wishlistMenuItemClickListener = new WishlistMenuItemClickListener(this, i);
        final ImageButton imageButton = (ImageButton) view.findViewById(R.id.fragment_wishlist_menu_button);
        if (this.mProfileFragment.isCurrentUser()) {
            imageButton.setVisibility(0);
            imageButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PreferenceUtil.setBoolean("HideProfileShareWishlistTooltip", true);
                    PopupMenu popupMenu = new PopupMenu(ProfileWishlistAdapter.this.mContext, view);
                    popupMenu.setOnMenuItemClickListener(wishlistMenuItemClickListener);
                    MenuInflater menuInflater = popupMenu.getMenuInflater();
                    if (ExperimentDataCenter.getInstance().shouldShowWishlistShare()) {
                        menuInflater.inflate(R.menu.wishlist_actions_share, popupMenu.getMenu());
                    } else {
                        menuInflater.inflate(R.menu.wishlist_actions, popupMenu.getMenu());
                    }
                    if (wishWishlist.isPrivate()) {
                        popupMenu.getMenu().findItem(R.id.profile_wishlist_private).setTitle(WishApplication.getInstance().getString(R.string.wishlist_make_public));
                    } else {
                        popupMenu.getMenu().findItem(R.id.profile_wishlist_private).setTitle(WishApplication.getInstance().getString(R.string.wishlist_make_private));
                    }
                    popupMenu.show();
                }
            });
            if (ExperimentDataCenter.getInstance().shouldShowWishlistShare() && !this.mTooltipScheduled && !PreferenceUtil.getBoolean("HideProfileShareWishlistTooltip", false)) {
                this.mProfileFragment.withActivity(new ActivityTask<ProfileActivity>() {
                    public void performTask(ProfileActivity profileActivity) {
                        WishTooltip.make(ProfileWishlistAdapter.this.mContext.getString(R.string.wishlist_share_overflow_button_tooltip), 2).setTargetViewOverlay(WishTooltip.createSimpleCircleOverlay(ProfileWishlistAdapter.this.getContext())).setCallback(new WishTooltipListener() {
                            public void clickedOutsideTooltip() {
                            }

                            public void clickedTooltip() {
                                imageButton.performClick();
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PROFILE_SHARE_WISHLIST_OVERFLOW_BUTTON_TOOLTIP);
                            }
                        }).showWhenReady(profileActivity, imageButton);
                    }
                });
                this.mTooltipScheduled = true;
            }
        } else {
            imageButton.setVisibility(8);
        }
        return view;
    }

    public String getPageTitle() {
        return WishApplication.getInstance().getString(R.string.wishlist);
    }

    /* access modifiers changed from: protected */
    public int getNumColumn() {
        return TabletUtil.isTablet() ? 5 : 3;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public void releaseImages() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                Object tag = this.mListView.getChildAt(i).getTag();
                if (tag != null && (tag instanceof ItemRowHolder)) {
                    ItemRowHolder itemRowHolder = (ItemRowHolder) tag;
                    for (int i2 = 0; i2 < itemRowHolder.imageViews.size(); i2++) {
                        itemRowHolder.getImage(i2).releaseImages();
                    }
                }
            }
        }
    }

    public void restoreImages() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                Object tag = this.mListView.getChildAt(i).getTag();
                if (tag != null && (tag instanceof ItemRowHolder)) {
                    ItemRowHolder itemRowHolder = (ItemRowHolder) tag;
                    for (int i2 = 0; i2 < itemRowHolder.imageViews.size(); i2++) {
                        itemRowHolder.getImage(i2).restoreImages();
                    }
                }
            }
        }
    }

    public WishWishlist getItem(int i) {
        if (i < 0 || i >= getCount()) {
            return null;
        }
        return (WishWishlist) this.mWishlists.get(i);
    }

    public void togglePrivacy(int i) {
        WishWishlist item = getItem(i);
        this.mProfileFragment.setWishlistPrivacy(i, item.getWishlistId(), !item.isPrivate());
    }

    public void setWishlistPrivacySuccess(int i, boolean z) {
        getItem(i).setPrivate(z);
        notifyDataSetChanged();
    }

    public void renameWishlist(int i) {
        this.mProfileFragment.renameWishlist(getItem(i));
    }

    public void shareWishlist(final int i) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PROFILE_SHARE_WISHLIST_OVERFLOW_BUTTON_MENU);
        final WishWishlist item = getItem(i);
        if (item != null) {
            if (!item.isPrivate()) {
                WishlistHelper.triggerShareIntent(this.mContext, item);
            } else {
                this.mProfileFragment.withActivity(new ActivityTask<ProfileActivity>() {
                    public void performTask(ProfileActivity profileActivity) {
                        ArrayList arrayList = new ArrayList();
                        MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(1, ProfileWishlistAdapter.this.mContext.getString(R.string.wishlist_make_public), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                        arrayList.add(multiButtonDialogChoice);
                        MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(2, ProfileWishlistAdapter.this.mContext.getString(R.string.cancel), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                        arrayList.add(multiButtonDialogChoice2);
                        profileActivity.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(ProfileWishlistAdapter.this.mContext.getString(R.string.wishlist_share_failure_private_title)).setSubTitle(ProfileWishlistAdapter.this.mContext.getString(R.string.wishlist_share_failure_private_message_profile)).setButtons(arrayList).hideXButton().setCancelable(true).build(), new BaseDialogCallback() {
                            public void onCancel(BaseDialogFragment baseDialogFragment) {
                            }

                            public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                                if (i == 1) {
                                    ProfileWishlistAdapter.this.mProfileFragment.setWishlistPrivacy(i, item.getWishlistId(), true ^ item.isPrivate());
                                }
                            }
                        });
                    }
                });
            }
        }
    }
}
