package com.contextlogic.wish.activity.profile.wishlist;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.DialogTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.actionbar.ActionBarItem;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;
import com.contextlogic.wish.activity.productdetails.ProductDetailsFragment.ProductDetailsCallback;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.button.ToggleLoadingButton;
import com.contextlogic.wish.ui.button.ToggleLoadingButton.ButtonMode;
import com.contextlogic.wish.ui.button.ToggleLoadingButton.OnToggleLoadingButtonClickListener;
import java.util.ArrayList;

public class WishlistFragment extends ProductFeedFragment implements OnToggleLoadingButtonClickListener {
    private View mEditBar;
    private WishlistUserFollowView mWishListUserFollowView;
    /* access modifiers changed from: private */
    public WishWishlist mWishlist;
    /* access modifiers changed from: private */
    public String mWishlistId;

    public boolean canFeedViewPullToRefresh() {
        return false;
    }

    public boolean canShowFeedCategories() {
        return false;
    }

    public boolean isFeedFilterable() {
        return false;
    }

    public void initializeLoadingContentView(View view) {
        super.initializeLoadingContentView(view);
        withActivity(new ActivityTask<WishlistActivity>() {
            public void performTask(WishlistActivity wishlistActivity) {
                WishlistFragment.this.mWishlist = wishlistActivity.getWishlist();
                WishlistFragment.this.mWishlistId = WishlistFragment.this.mWishlist != null ? WishlistFragment.this.mWishlist.getWishlistId() : wishlistActivity.getWishlistId();
            }
        });
        this.mEditBar = view.findViewById(R.id.base_product_feed_fragment_edit_view);
        ((ThemedButton) view.findViewById(R.id.base_product_feed_fragment_edit_move_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishlistFragment.this.handleMoveProducts();
            }
        });
        ((ThemedButton) view.findViewById(R.id.base_product_feed_fragment_edit_delete_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishlistFragment.this.handleDeleteProducts();
            }
        });
        ((ThemedButton) view.findViewById(R.id.base_product_feed_fragment_edit_cancel_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishlistFragment.this.setEditModeEnabled(false);
            }
        });
        this.mWishListUserFollowView = new WishlistUserFollowView(getContext());
        if (this.mWishlist != null) {
            setupUserFollowView(this.mWishlist, true);
        }
        withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                baseProductFeedServiceFragment.loadWishlistFollowState(WishlistFragment.this.mWishlistId);
            }
        });
    }

    public void setupUserFollowView(WishWishlist wishWishlist, boolean z) {
        if (isCurrentUser()) {
            if (!z) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MY_WISHLIST_PAGE);
            }
        } else if (wishWishlist.getUserObject() != null) {
            this.mWishListUserFollowView.setup(wishWishlist, this);
            this.mAdapter.addCustomHeader(this.mWishListUserFollowView, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void refreshActionBar() {
        setupBaseActionBar();
        if (ExperimentDataCenter.getInstance().shouldRemoveCartFromWishlist()) {
            removeCartItemInActionBar();
        }
        withActivity(new ActivityTask<WishlistActivity>() {
            public void performTask(WishlistActivity wishlistActivity) {
                if (ExperimentDataCenter.getInstance().shouldShowWishlistShare()) {
                    wishlistActivity.getActionBarManager().addActionBarItem(new ActionBarItem(WishlistFragment.this.getString(R.string.share), 2004, (int) R.drawable.action_bar_share));
                }
                if (wishlistActivity.canEditWishlist()) {
                    if (WishlistFragment.this.mWishlist == null) {
                        WishlistFragment.this.mWishlist = wishlistActivity.getWishlist();
                    }
                    if (WishlistFragment.this.mWishlist.getProductCount() > 0) {
                        wishlistActivity.getActionBarManager().addActionBarItem(new ActionBarItem(WishlistFragment.this.getString(R.string.edit), 2001, (int) R.drawable.action_bar_edit));
                    }
                    if (wishlistActivity.canRenameWishlist()) {
                        wishlistActivity.getActionBarManager().addActionBarItem(new ActionBarItem(WishlistFragment.this.getString(R.string.rename), 2002));
                    }
                    wishlistActivity.getActionBarManager().addActionBarItem(new ActionBarItem(WishlistFragment.this.getString(R.string.delete), 2003));
                }
            }
        });
    }

    public boolean handleActionBarItemSelected(int i) {
        if (i == 2001) {
            handleEditWishlist();
            return true;
        } else if (i == 2002) {
            handleRenameWishlist();
            return true;
        } else if (i == 2003) {
            handleDeleteWishlist();
            return true;
        } else if (i != 2004) {
            return super.handleActionBarItemSelected(i);
        } else {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PROFILE_SHARE_WISHLIST_ACTIVITY_ACTION_BAR);
            shareWishlist(this.mWishlist);
            return true;
        }
    }

    public void shareWishlist(WishWishlist wishWishlist) {
        if (!wishWishlist.isPrivate()) {
            WishlistHelper.triggerShareIntent(getContext(), wishWishlist);
        } else {
            withActivity(new ActivityTask<WishlistActivity>() {
                public void performTask(WishlistActivity wishlistActivity) {
                    ArrayList arrayList = new ArrayList();
                    MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(1, WishlistFragment.this.getString(R.string.ok), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                    arrayList.add(multiButtonDialogChoice);
                    wishlistActivity.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(WishlistFragment.this.getString(R.string.wishlist_share_failure_private_title)).setSubTitle(WishlistFragment.this.getString(R.string.wishlist_share_failure_private_message)).setButtons(arrayList).setCancelable(true).build());
                }
            });
        }
    }

    public void handleEditWishlist() {
        setEditModeEnabled(true);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_EDIT_WISHLIST);
    }

    public void handleRenameWishlist() {
        withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                baseProductFeedServiceFragment.renameWishlist(WishlistFragment.this.mWishlist);
            }
        });
    }

    public void handleDeleteWishlist() {
        withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                baseProductFeedServiceFragment.deleteWishlist(WishlistFragment.this.mWishlist, WishlistFragment.this.mAdapter.getCurrentFeedView().getProducts().size());
            }
        });
    }

    public void handleRenameWishlistSuccess(final String str) {
        withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                baseActivity.getActionBarManager().setTitle(str);
                baseActivity.setResult(1001);
            }
        });
    }

    public void handleDeleteWishlistSuccess() {
        withActivity(new ActivityTask<WishlistActivity>() {
            public void performTask(WishlistActivity wishlistActivity) {
                wishlistActivity.setResult(1000);
                wishlistActivity.finishActivity();
            }
        });
    }

    public void handleWishlistProductActionSuccess() {
        withDialogFragment(new DialogTask() {
            public void performTask(BaseActivity baseActivity, BaseDialogFragment baseDialogFragment) {
                baseDialogFragment.cancel();
            }
        });
        withActivity(new ActivityTask<WishlistActivity>() {
            public void performTask(WishlistActivity wishlistActivity) {
                wishlistActivity.setResult(1001);
            }
        });
        setEditModeEnabled(false);
        this.mAdapter.reloadAll();
    }

    /* access modifiers changed from: private */
    public void handleMoveProducts() {
        if (getSelectedProductIds().size() > 0) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISHLIST_MOVE_PRODUCT);
            withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
                public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                    baseProductFeedServiceFragment.moveProducts(WishlistFragment.this.getSelectedProductIds());
                }
            });
        }
    }

    public void handleWishlistProductActionSuccess(String str) {
        handleWishlistProductActionSuccess();
    }

    public void handleGetWishlistFollowState(WishWishlist wishWishlist, boolean z, boolean z2) {
        if (wishWishlist != null) {
            this.mWishlist = wishWishlist;
            setupUserFollowView(this.mWishlist, false);
            withActivity(new ActivityTask<WishlistActivity>() {
                public void performTask(WishlistActivity wishlistActivity) {
                    wishlistActivity.getActionBarManager().setTitle(WishlistFragment.this.mWishlist.getName());
                }
            });
        }
        if (this.mWishListUserFollowView == null) {
            return;
        }
        if (z2) {
            this.mWishListUserFollowView.setFollowState(z);
        } else {
            this.mWishListUserFollowView.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void handleDeleteProducts() {
        if (getSelectedProductIds().size() > 0) {
            withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
                public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISHLIST_REMOVE_PRODUCT);
                    baseProductFeedServiceFragment.removeFromWishlist(WishlistFragment.this.getSelectedProductIds(), WishlistFragment.this.mWishlist.getWishlistId());
                }
            });
        }
    }

    public void setEditModeEnabled(boolean z) {
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
            if (z) {
                popOutBottomNavigation(true);
            } else {
                popInBottomNavigation(true);
            }
        }
        if (z) {
            this.mEditBar.setVisibility(0);
        } else {
            this.mEditBar.setVisibility(8);
        }
        this.mAdapter.setEditModeEnabled(z);
    }

    public Source getSource() {
        return Source.DEFAULT;
    }

    public ProductDetailsCallback getProductDetailsCallback() {
        return new ProductDetailsCallback() {
            public void onUnWished() {
                if (WishlistFragment.this.mAdapter != null && WishlistFragment.this.mAdapter.getCurrentFeedView() != null) {
                    WishlistFragment.this.mAdapter.getCurrentFeedView().reload();
                }
            }
        };
    }

    public boolean isCurrentUser() {
        return this.mWishlist != null && ProfileDataCenter.getInstance().getUserId().equals(this.mWishlist.getUserId());
    }

    public DataMode getDataMode() {
        return DataMode.Wishlist;
    }

    /* access modifiers changed from: protected */
    public String getMainRequestId() {
        return this.mWishlistId;
    }

    public void onToggleLoadingButtonClicked(final ToggleLoadingButton toggleLoadingButton, boolean z) {
        if (z) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISHLIST_FOLLOW_BUTTON_FROM_WISHLIST);
            toggleLoadingButton.setButtonMode(ButtonMode.UnselectedLoading);
            withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
                public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                    baseProductFeedServiceFragment.followUnFollowWishlist(WishlistFragment.this.mWishlist.getWishlistId(), true);
                }
            });
            return;
        }
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISHLIST_UNFOLLOW_BUTTON_FROM_WISHLIST);
        withActivity(new ActivityTask<WishlistActivity>() {
            public void performTask(WishlistActivity wishlistActivity) {
                wishlistActivity.startDialog(MultiButtonDialogFragment.createMultiButtonYesNoDialog(wishlistActivity.getString(R.string.wishlist_unfollow_message, new Object[]{WishlistFragment.this.mWishlist.getName()}), null), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISHLIST_UNFOLLOW_BUTTON_CANCEL);
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISHLIST_UNFOLLOW_BUTTON_CONFIRM);
                            toggleLoadingButton.setButtonMode(ButtonMode.SelectedLoading);
                            WishlistFragment.this.withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
                                public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                                    baseProductFeedServiceFragment.followUnFollowWishlist(WishlistFragment.this.mWishlist.getWishlistId(), false);
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}
