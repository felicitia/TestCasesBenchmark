package com.contextlogic.wish.activity.feed;

import android.content.Intent;
import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;
import com.contextlogic.wish.activity.productdetails.ProductDetailsFragment;
import com.contextlogic.wish.activity.productdetails.ProductDetailsFragment.ProductDetailsCallback;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.standalone.BaseFeedApiService.FeedExtraDataBundle;
import com.contextlogic.wish.api.service.standalone.GetProductService.SuccessCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.addtocart.AddToCartDialogFragment;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.dialog.addtocart.sizecolorselector.SizeColorSelectorDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationInterface;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.viewpager.BaseTabStripInterface;
import java.util.ArrayList;

public abstract class BaseProductFeedFragment<A extends BaseActivity> extends LoadingUiFragment<A> implements BottomNavigationInterface, BaseTabStripInterface {
    public boolean canFeedViewPullToRefresh() {
        return true;
    }

    public void clearInitialProductInfo(int i) {
    }

    public void clearSavedInstanceState(int i) {
    }

    public DataMode getDataMode() {
        return null;
    }

    public BaseInitialProductWrapper getInitialProductInfo(int i) {
        return null;
    }

    public ProductDetailsCallback getProductDetailsCallback() {
        return null;
    }

    public String getProductId() {
        return null;
    }

    public Bundle getSavedInstanceState(int i) {
        return null;
    }

    public abstract int getTabAreaOffset();

    public abstract int getTabAreaSize();

    public void handleDeleteWishlistSuccess() {
    }

    public void handleLoadingErrored(int i) {
    }

    public abstract void handleLoadingSuccess(int i, ArrayList<WishProduct> arrayList, int i2, boolean z);

    public void handleRenameWishlistSuccess(String str) {
    }

    public void handleWishlistProductActionSuccess() {
    }

    public void handleWishlistProductActionSuccess(String str) {
    }

    public abstract void loadProducts(int i, String str, int i2);

    public void setEditModeEnabled(boolean z) {
    }

    public void showBackToTopButton() {
    }

    public void updateFeedExtraDataBundle(FeedExtraDataBundle feedExtraDataBundle) {
    }

    public Source getSource() {
        return Source.DEFAULT;
    }

    public void handleClaimFreeGiftSuccess() {
        Intent intent = new Intent();
        intent.setClass(WishApplication.getInstance(), CartActivity.class);
        startActivity(intent);
    }

    public void addProductToCart(WishProduct wishProduct) {
        addProductToCart(wishProduct, Source.DEFAULT);
    }

    public void addProductToCart(WishProduct wishProduct, Source source) {
        if (wishProduct != null) {
            if (!wishProduct.isInStock()) {
                withActivity(new ActivityTask<A>() {
                    public void performTask(A a) {
                        a.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(a.getString(R.string.this_item_is_out_of_stock)));
                    }
                });
            } else if (wishProduct.isCommerceProduct()) {
                if (wishProduct.canShowAddToCartModal()) {
                    showAddToCartModal(wishProduct, source);
                } else {
                    performAddToCart(wishProduct, wishProduct.getCommerceDefaultVariationId(), wishProduct.getDefaultShippingOptionId(wishProduct.getDefaultCommerceVariationId()), wishProduct.getAddToCartOfferId(), source);
                }
            }
        }
    }

    private void showAddToCartModal(final WishProduct wishProduct, final Source source) {
        final String addToCartOfferId = wishProduct.getAddToCartOfferId();
        if (this instanceof ProductDetailsFragment) {
            ((ProductDetailsFragment) this).getAddToCartButtonText();
        }
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                BaseDialogFragment baseDialogFragment;
                if (ExperimentDataCenter.getInstance().shouldShowSizeColorSelector() || ExperimentDataCenter.getInstance().shouldShowSizeColorSelectorWithTextSwatchesOnly()) {
                    baseDialogFragment = SizeColorSelectorDialogFragment.createSizeColorSelectorDialogFragment(wishProduct, source, true);
                } else {
                    baseDialogFragment = AddToCartDialogFragment.createAddToCartDialog(wishProduct, source, true);
                }
                if (baseDialogFragment != null) {
                    a.startDialog(baseDialogFragment, new BaseDialogCallback() {
                        public void onCancel(BaseDialogFragment baseDialogFragment) {
                        }

                        public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                            String string = bundle.getString("ResultVariationId");
                            BaseProductFeedFragment.this.performAddToCart(wishProduct, string, wishProduct.getDefaultShippingOptionId(string), addToCartOfferId, source);
                        }
                    });
                }
            }
        });
    }

    public void performAddToCart(final WishProduct wishProduct, final String str, String str2, String str3, Source source) {
        if (source == Source.FREE_GIFT) {
            withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
                public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                    baseProductFeedServiceFragment.claimFreeGift(wishProduct.getCommerceProductId(), str);
                }
            });
        } else if (source == Source.DAILY_GIVEAWAY) {
            withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
                public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                    baseProductFeedServiceFragment.claimDailyGiveaway(wishProduct.getCommerceProductId(), str, wishProduct.getImage());
                }
            });
        } else {
            if (wishProduct.getAuthorizedBrand() != null) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BRANDED_PRODUCT_ADD_TO_CART);
            }
            final WishProduct wishProduct2 = wishProduct;
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            AnonymousClass5 r0 = new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
                public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                    baseProductFeedServiceFragment.addItemToCart(wishProduct2, str4, str5, str6, wishProduct2.getValue());
                }
            };
            withServiceFragment(r0);
        }
    }

    public void enterDailyRaffle(final WishProduct wishProduct, final LoadingPageView loadingPageView) {
        withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                if (wishProduct == null || loadingPageView == null) {
                    baseProductFeedServiceFragment.showErrorMessage(BaseProductFeedFragment.this.getString(R.string.something_went_wrong));
                } else {
                    baseProductFeedServiceFragment.enterDailyRaffle(wishProduct.getCommerceProductId(), wishProduct.getImage(), loadingPageView);
                }
            }
        });
    }

    public void claimGiveaway(final WishProduct wishProduct) {
        withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                if (wishProduct == null) {
                    baseProductFeedServiceFragment.showErrorMessage(BaseProductFeedFragment.this.getString(R.string.something_went_wrong));
                } else {
                    baseProductFeedServiceFragment.claimGiveaway(wishProduct);
                }
            }
        });
    }

    public void popInBottomNavigation(final boolean z) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                if ((a instanceof DrawerActivity) && a.shouldUseDynamicBottomNavigationLayout()) {
                    ((DrawerActivity) a).popInBottomNavigation(z);
                }
            }
        });
    }

    public void popOutBottomNavigation(final boolean z) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                if ((a instanceof DrawerActivity) && a.shouldUseDynamicBottomNavigationLayout()) {
                    ((DrawerActivity) a).popOutBottomNavigation(z);
                }
            }
        });
    }

    public void loadProductVariations(final WishProduct wishProduct, final SuccessCallback successCallback, final DefaultCodeFailureCallback defaultCodeFailureCallback) {
        if (wishProduct != null) {
            withServiceFragment(new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
                public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                    baseProductFeedServiceFragment.showLoadingSpinner();
                    baseProductFeedServiceFragment.loadProductVariations(wishProduct, successCallback, defaultCodeFailureCallback);
                }
            });
        }
    }

    public void showUnableToAddToCartDialog() {
        withActivity(new ActivityTask<A>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.hideLoadingDialog();
                baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(BaseProductFeedFragment.this.getString(R.string.could_not_add_to_cart)));
            }
        });
    }
}
