package com.contextlogic.wish.activity.productdetails;

import android.content.Intent;
import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.dailybonus.DailyLoginBonusStampPopupDialog;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.activity.pricechop.PriceChopMessageBottomSheet;
import com.contextlogic.wish.activity.pricewatch.PriceWatchActivity;
import com.contextlogic.wish.activity.productdetails.bundles.BundlesHeader.AddToCartCallback;
import com.contextlogic.wish.activity.productdetails.groupbuy.GroupBuyDialogFragment;
import com.contextlogic.wish.activity.rewards.redesign.RewardProductRedemptionServiceDelegate;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.BuyerGuaranteeInfo;
import com.contextlogic.wish.api.model.PriceChopProductDetail;
import com.contextlogic.wish.api.model.ProductDetailsRelatedRowSpec;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.api.model.WishCommerceLoanTabSpec;
import com.contextlogic.wish.api.model.WishDailyLoginStampSpec;
import com.contextlogic.wish.api.model.WishGroupBuyInfo;
import com.contextlogic.wish.api.model.WishGroupBuyRowInfo;
import com.contextlogic.wish.api.model.WishPaymentStructureSelectionSpec;
import com.contextlogic.wish.api.model.WishPriceWatchChangeInfo;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishProductBadge;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.api.model.WishRatingSummary;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.model.WishUsersCurrentlyViewing;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.AddToPriceWatchService;
import com.contextlogic.wish.api.service.standalone.AddToWishlistService;
import com.contextlogic.wish.api.service.standalone.BaseFeedApiService;
import com.contextlogic.wish.api.service.standalone.BaseFeedApiService.FeedExtraDataBundle;
import com.contextlogic.wish.api.service.standalone.CreateGroupBuyService;
import com.contextlogic.wish.api.service.standalone.CreateGroupBuyService.FailureCallback;
import com.contextlogic.wish.api.service.standalone.GetBuyerGuaranteeService;
import com.contextlogic.wish.api.service.standalone.GetGroupBuysService;
import com.contextlogic.wish.api.service.standalone.GetMerchantRatingsService;
import com.contextlogic.wish.api.service.standalone.GetProductRatingsService;
import com.contextlogic.wish.api.service.standalone.GetProductService;
import com.contextlogic.wish.api.service.standalone.GetProductService.FeedExtraInfo;
import com.contextlogic.wish.api.service.standalone.GetRecommendedWishlistsService;
import com.contextlogic.wish.api.service.standalone.GetRelatedProductBoostProductsService;
import com.contextlogic.wish.api.service.standalone.GetRelatedProductFeedService;
import com.contextlogic.wish.api.service.standalone.GetRelatedProductFeedService.ExpressProductSuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetUsersCurrentlyViewingService;
import com.contextlogic.wish.api.service.standalone.JoinGroupBuyService;
import com.contextlogic.wish.api.service.standalone.PriceChopCreateService;
import com.contextlogic.wish.api.service.standalone.PriceChopCreateService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.ProductWishService;
import com.contextlogic.wish.api.service.standalone.RemoveFromPriceWatchService;
import com.contextlogic.wish.api.service.standalone.RemoveFromWishlistService;
import com.contextlogic.wish.api.service.standalone.RemoveUpvoteReviewService;
import com.contextlogic.wish.api.service.standalone.UpdateCartService;
import com.contextlogic.wish.api.service.standalone.UpvoteReviewService;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.dialog.addtocartoffer.AddToCartOfferDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.popupanimation.bundleadded.BundleAddedDialogFragment;
import com.contextlogic.wish.dialog.popupanimation.itemadded.ItemChangeToPriceWatchDialogFragment;
import com.contextlogic.wish.ui.badge.BadgeDescriptionBottomSheet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDetailsServiceFragment extends BaseProductFeedServiceFragment {
    private AddToPriceWatchService mAddToPriceWatchService;
    private AddToWishlistService mAddToWishlistService;
    /* access modifiers changed from: private */
    public Runnable mApiCaller;
    /* access modifiers changed from: private */
    public CreateGroupBuyService mCreateGroupBuyService;
    /* access modifiers changed from: private */
    public int mCurrentMessageIndex;
    /* access modifiers changed from: private */
    public WishUsersCurrentlyViewing mCurrentlyViewingInfo;
    private GetBuyerGuaranteeService mGetBuyerGuaranteeService;
    private GetGroupBuysService mGetGroupBuyService;
    private GetMerchantRatingsService mGetMerchantRatingsService;
    /* access modifiers changed from: private */
    public GetProductRatingsService mGetProductRatingsService;
    private GetProductService mGetProductService;
    private GetRecommendedWishlistsService mGetRecommendedWishlistsService;
    private GetRelatedProductFeedService mGetRelatedProductFeedService;
    /* access modifiers changed from: private */
    public GetUsersCurrentlyViewingService mGetUsersCurrentlyViewingService;
    /* access modifiers changed from: private */
    public JoinGroupBuyService mJoinGroupBuyService;
    private PriceChopCreateService mPriceChopCreateService;
    private ProductWishService mProductWishService;
    private RemoveFromPriceWatchService mRemoveFromPriceWatchService;
    private RemoveFromWishlistService mRemoveFromWishlistService;
    private RemoveUpvoteReviewService mRemoveUpvoteReviewService;
    private RewardProductRedemptionServiceDelegate mRewardProductRedemptionDelegate;
    private UpdateCartService mUpdateCartService;
    private UpvoteReviewService mUpvoteReviewService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetProductService = new GetProductService();
        this.mProductWishService = new ProductWishService();
        this.mAddToWishlistService = new AddToWishlistService();
        this.mGetMerchantRatingsService = new GetMerchantRatingsService();
        this.mGetProductRatingsService = new GetProductRatingsService();
        this.mRemoveFromWishlistService = new RemoveFromWishlistService();
        this.mUpdateCartService = new UpdateCartService();
        this.mGetUsersCurrentlyViewingService = new GetUsersCurrentlyViewingService();
        this.mRemoveUpvoteReviewService = new RemoveUpvoteReviewService();
        this.mUpvoteReviewService = new UpvoteReviewService();
        this.mGetGroupBuyService = new GetGroupBuysService();
        this.mJoinGroupBuyService = new JoinGroupBuyService();
        this.mCreateGroupBuyService = new CreateGroupBuyService();
        this.mGetRecommendedWishlistsService = new GetRecommendedWishlistsService();
        this.mGetRelatedProductFeedService = new GetRelatedProductFeedService();
        this.mGetBuyerGuaranteeService = new GetBuyerGuaranteeService();
        this.mPriceChopCreateService = new PriceChopCreateService();
        this.mAddToPriceWatchService = new AddToPriceWatchService();
        this.mRemoveFromPriceWatchService = new RemoveFromPriceWatchService();
        this.mRewardProductRedemptionDelegate = new RewardProductRedemptionServiceDelegate(this);
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetProductService.cancelAllRequests();
        this.mProductWishService.cancelAllRequests();
        this.mAddToWishlistService.cancelAllRequests();
        this.mGetMerchantRatingsService.cancelAllRequests();
        this.mGetProductRatingsService.cancelAllRequests();
        this.mRemoveFromWishlistService.cancelAllRequests();
        this.mUpdateCartService.cancelAllRequests();
        this.mGetUsersCurrentlyViewingService.cancelAllRequests();
        this.mRemoveUpvoteReviewService.cancelAllRequests();
        this.mUpvoteReviewService.cancelAllRequests();
        this.mGetGroupBuyService.cancelAllRequests();
        this.mJoinGroupBuyService.cancelAllRequests();
        this.mCreateGroupBuyService.cancelAllRequests();
        this.mGetRecommendedWishlistsService.cancelAllRequests();
        this.mGetRelatedProductFeedService.cancelAllRequests();
        this.mGetBuyerGuaranteeService.cancelAllRequests();
        this.mPriceChopCreateService.cancelAllRequests();
        this.mAddToPriceWatchService.cancelAllRequests();
        this.mRemoveFromPriceWatchService.cancelAllRequests();
        this.mRewardProductRedemptionDelegate.cancelAllRequests();
    }

    public void createAndSharePriceChop(String str, PriceChopProductDetail priceChopProductDetail) {
        if (!priceChopProductDetail.isRunning() || priceChopProductDetail.getShareTitle() == null || priceChopProductDetail.getShareBody() == null) {
            this.mPriceChopCreateService.requestService(str, new SuccessCallback() {
                public void onSuccess(final PriceChopProductDetail priceChopProductDetail) {
                    ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                        public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                            productDetailsFragment.handlePriceChopDetailLoaded(priceChopProductDetail);
                            ProductDetailsServiceFragment.this.showShareDialog(priceChopProductDetail);
                        }
                    });
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                    ProductDetailsServiceFragment.this.showErrorMessage(str);
                }
            });
        } else {
            showShareDialog(priceChopProductDetail);
        }
    }

    /* access modifiers changed from: private */
    public void showShareDialog(final PriceChopProductDetail priceChopProductDetail) {
        if (priceChopProductDetail != null) {
            if (ExperimentDataCenter.getInstance().shouldShowPriceChopMessage()) {
                getBaseActivity().startDialog(PriceChopMessageBottomSheet.create(priceChopProductDetail));
            } else {
                withActivity(new ActivityTask<BaseActivity>() {
                    public void performTask(BaseActivity baseActivity) {
                        baseActivity.showShareDialog(priceChopProductDetail.getShareTitle(), priceChopProductDetail.getShareBody());
                    }
                });
            }
        }
    }

    public void loadBundledProduct(String str, HashMap<String, String> hashMap) {
        this.mGetProductService.requestService(str, true, hashMap, new GetProductService.SuccessCallback() {
            public void onSuccess(final WishProduct wishProduct, FeedExtraInfo feedExtraInfo) {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        productDetailsFragment.handleBundledProductLoaded(wishProduct);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultCodeFailureCallback() {
            public void onFailure(String str, int i) {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        productDetailsFragment.handleBundledProductsFailed();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadProduct(final String str, HashMap<String, String> hashMap) {
        this.mGetProductService.requestService(str, hashMap, new GetProductService.SuccessCallback() {
            public void onSuccess(final WishProduct wishProduct, FeedExtraInfo feedExtraInfo) {
                if (feedExtraInfo.appIndexingData != null) {
                    ProductDetailsServiceFragment.this.trackGoogleAppIndexAction(feedExtraInfo.appIndexingData);
                }
                ProductDetailsServiceFragment.this.mCurrentlyViewingInfo = wishProduct.getUsersCurrentlyViewingInfo();
                ProductDetailsServiceFragment.this.loadCurrentlyViewingUsers(str);
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        if (wishProduct.getScreenshotShareInfo() != null) {
                            baseActivity.setScreenshotShareInfo(wishProduct.getScreenshotShareInfo());
                        }
                        productDetailsFragment.handleLoadingSuccess(wishProduct);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultCodeFailureCallback() {
            public void onFailure(String str, final int i) {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        productDetailsFragment.handleLoadingFailure(i >= 10);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadProductRatings(String str, int i, int i2, FilterType filterType) {
        final String str2 = str;
        final int i3 = i;
        final int i4 = i2;
        final FilterType filterType2 = filterType;
        AnonymousClass14 r0 = new UiTask<BaseActivity, ProductDetailsFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                productDetailsFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
                    public void performTask(ProductDetailsActivity productDetailsActivity) {
                        ProductDetailsServiceFragment.this.mGetProductRatingsService.requestService(str2, i3, i4, filterType2, productDetailsActivity.getProductRatingId(), new GetProductRatingsService.SuccessCallback() {
                            public void onSuccess(WishRatingSummary wishRatingSummary, ArrayList<WishRating> arrayList, int i, int i2, boolean z) {
                                ProductDetailsServiceFragment productDetailsServiceFragment = ProductDetailsServiceFragment.this;
                                final WishRatingSummary wishRatingSummary2 = wishRatingSummary;
                                final ArrayList<WishRating> arrayList2 = arrayList;
                                final boolean z2 = z;
                                final int i3 = i;
                                final int i4 = i2;
                                AnonymousClass1 r1 = new UiTask<BaseActivity, ProductDetailsFragment>() {
                                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                                        productDetailsFragment.handleRatingsLoaded(wishRatingSummary2, arrayList2, z2, i3, i4, true);
                                    }
                                };
                                productDetailsServiceFragment.withUiFragment(r1, "FragmentTagMainContent");
                            }
                        }, new DefaultFailureCallback() {
                            public void onFailure(String str) {
                                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                                        productDetailsFragment.handleRatingsFailed(true);
                                    }
                                }, "FragmentTagMainContent");
                            }
                        });
                    }
                });
            }
        };
        withUiFragment(r0, "FragmentTagMainContent");
    }

    public void loadMerchantRatings(String str, int i, int i2, FilterType filterType) {
        this.mGetMerchantRatingsService.requestService(str, i, i2, filterType, new GetMerchantRatingsService.SuccessCallback() {
            public void onSuccess(WishRatingSummary wishRatingSummary, ArrayList<WishRating> arrayList, int i, int i2, boolean z) {
                ProductDetailsServiceFragment productDetailsServiceFragment = ProductDetailsServiceFragment.this;
                final WishRatingSummary wishRatingSummary2 = wishRatingSummary;
                final ArrayList<WishRating> arrayList2 = arrayList;
                final boolean z2 = z;
                final int i3 = i;
                final int i4 = i2;
                AnonymousClass1 r1 = new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        productDetailsFragment.handleRatingsLoaded(wishRatingSummary2, arrayList2, z2, i3, i4, false);
                    }
                };
                productDetailsServiceFragment.withUiFragment(r1, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        productDetailsFragment.handleRatingsFailed(false);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void removeProductRatingUpvote(String str) {
        this.mRemoveUpvoteReviewService.requestService(str, new DefaultSuccessCallback() {
            public void onSuccess() {
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
            }
        });
    }

    public void productRatingUpvote(String str) {
        this.mUpvoteReviewService.requestService(str, new DefaultSuccessCallback() {
            public void onSuccess() {
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
            }
        });
    }

    public void addBundledProductToCart(String str, String str2, String str3, String str4, final AddToCartCallback addToCartCallback) {
        showLoadingSpinner();
        this.mUpdateCartService.requestService(str, str2, str3, 1, true, false, str4, new UpdateCartService.SuccessCallback() {
            public void onSuccess(final WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishCommerceLoanTabSpec wishCommerceLoanTabSpec, WishPaymentStructureSelectionSpec wishPaymentStructureSelectionSpec) {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        ProductDetailsServiceFragment.this.hideLoadingSpinner();
                        int i = 0;
                        while (i < wishCart.getItems().size()) {
                            if (!((WishCartItem) wishCart.getItems().get(i)).wasJustAdded()) {
                                i++;
                            } else {
                                final WishCartItem wishCartItem = (WishCartItem) wishCart.getItems().get(i);
                                if (wishCart.getAddToCartOfferApplied() == null || wishCart.getAddToCartOfferApplied().isExpired()) {
                                    addToCartCallback.onSuccess(wishCartItem);
                                    return;
                                }
                                final AddToCartOfferDialogFragment createAddToCartOfferDialog = AddToCartOfferDialogFragment.createAddToCartOfferDialog(wishCart.getAddToCartOfferApplied());
                                if (createAddToCartOfferDialog != null) {
                                    ProductDetailsServiceFragment.this.withActivity(new ActivityTask<BaseActivity>() {
                                        public void performTask(BaseActivity baseActivity) {
                                            baseActivity.startDialog(createAddToCartOfferDialog, new BaseDialogCallback() {
                                                public void onCancel(BaseDialogFragment baseDialogFragment) {
                                                    addToCartCallback.onSuccess(wishCartItem);
                                                }

                                                public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                                                    addToCartCallback.onSuccess(wishCartItem);
                                                }
                                            });
                                        }
                                    });
                                    return;
                                } else {
                                    addToCartCallback.onSuccess(wishCartItem);
                                    return;
                                }
                            }
                        }
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ADD_TO_CART_WITHOUT_CART_REDIRECT_FAILURE);
                ProductDetailsServiceFragment.this.hideLoadingSpinner();
                ProductDetailsServiceFragment.this.showErrorMessage(ProductDetailsServiceFragment.this.getString(R.string.could_not_add_to_cart));
            }
        });
    }

    public void loadCurrentlyViewingUsers(final String str) {
        this.mApiCaller = new Runnable() {
            public void run() {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, final ProductDetailsFragment productDetailsFragment) {
                        if (ProductDetailsServiceFragment.this.mCurrentMessageIndex < ProductDetailsServiceFragment.this.mCurrentlyViewingInfo.getMessageList().size()) {
                            productDetailsFragment.handleCurrentlyViewingLoadingSuccess((String) ProductDetailsServiceFragment.this.mCurrentlyViewingInfo.getMessageList().get(ProductDetailsServiceFragment.this.mCurrentMessageIndex));
                            ProductDetailsServiceFragment.this.mCurrentMessageIndex = ProductDetailsServiceFragment.this.mCurrentMessageIndex + 1;
                            if (ProductDetailsServiceFragment.this.mCurrentlyViewingInfo != null && ProductDetailsServiceFragment.this.mCurrentlyViewingInfo.getRefreshRateMillis() > 0 && !ProductDetailsServiceFragment.this.mGetUsersCurrentlyViewingService.isPending()) {
                                ProductDetailsServiceFragment.this.getHandler().postDelayed(ProductDetailsServiceFragment.this.mApiCaller, ProductDetailsServiceFragment.this.mCurrentlyViewingInfo.getRefreshRateMillis());
                                return;
                            }
                            return;
                        }
                        ProductDetailsServiceFragment.this.mGetUsersCurrentlyViewingService.requestService(str, new GetUsersCurrentlyViewingService.SuccessCallback() {
                            public void onSuccess(WishUsersCurrentlyViewing wishUsersCurrentlyViewing) {
                                ProductDetailsServiceFragment.this.mCurrentlyViewingInfo = wishUsersCurrentlyViewing;
                                productDetailsFragment.handleCurrentlyViewingLoadingSuccess(ProductDetailsServiceFragment.this.mCurrentlyViewingInfo.getMessage());
                                ProductDetailsServiceFragment.this.mCurrentMessageIndex = 0;
                                if (ProductDetailsServiceFragment.this.mCurrentlyViewingInfo != null && ProductDetailsServiceFragment.this.mCurrentlyViewingInfo.getRefreshRateMillis() > 0) {
                                    ProductDetailsServiceFragment.this.getHandler().postDelayed(ProductDetailsServiceFragment.this.mApiCaller, ProductDetailsServiceFragment.this.mCurrentlyViewingInfo.getRefreshRateMillis());
                                }
                            }
                        }, null);
                    }
                }, "FragmentTagMainContent");
            }
        };
        if (this.mCurrentlyViewingInfo != null && this.mCurrentlyViewingInfo.getRefreshRateMillis() > 0) {
            getHandler().postDelayed(this.mApiCaller, this.mCurrentlyViewingInfo.getRefreshRateMillis());
        }
    }

    public void stopLoadingCurrentlyViewingUsers() {
        getHandler().removeCallbacks(this.mApiCaller);
    }

    public boolean isWishPending(String str) {
        return this.mProductWishService.isPending(str) || this.mRemoveFromWishlistService.isPending();
    }

    public void showBundleAddedDialogFragment(ArrayList<WishProduct> arrayList, ArrayList<WishCartItem> arrayList2) {
        final BundleAddedDialogFragment createBundleAddedDialogFragment = BundleAddedDialogFragment.createBundleAddedDialogFragment(arrayList, arrayList2);
        if (createBundleAddedDialogFragment != null) {
            withActivity(new ActivityTask<BaseActivity>() {
                public void performTask(BaseActivity baseActivity) {
                    baseActivity.startDialog(createBundleAddedDialogFragment, new BaseDialogCallback() {
                        public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        }

                        public void onCancel(BaseDialogFragment baseDialogFragment) {
                            ProductDetailsServiceFragment.this.withActivity(new ActivityTask<BaseActivity>() {
                                public void performTask(BaseActivity baseActivity) {
                                    baseActivity.getActionBarManager().updateCartIcon(true);
                                }
                            });
                        }
                    });
                }
            });
        }
    }

    public boolean isMerchantRatingsPending() {
        return this.mGetMerchantRatingsService.isPending();
    }

    public boolean isProductRatingsPending() {
        return this.mGetProductRatingsService.isPending();
    }

    public void cancelLoadingMerchantRatings() {
        this.mGetMerchantRatingsService.cancelAllRequests();
    }

    public void cancelLoadingProductRatings() {
        this.mGetProductRatingsService.cancelAllRequests();
    }

    /* access modifiers changed from: private */
    public void createGroupBuyResponseHandler(boolean z, boolean z2, String str, ArrayList<WishGroupBuyRowInfo> arrayList, WishGroupBuyInfo wishGroupBuyInfo) {
        hideLoadingSpinner();
        final boolean z3 = z2;
        final String str2 = str;
        final boolean z4 = z;
        final ArrayList<WishGroupBuyRowInfo> arrayList2 = arrayList;
        final WishGroupBuyInfo wishGroupBuyInfo2 = wishGroupBuyInfo;
        AnonymousClass27 r0 = new UiTask<BaseActivity, ProductDetailsFragment>() {
            public void performTask(BaseActivity baseActivity, final ProductDetailsFragment productDetailsFragment) {
                if (z3) {
                    productDetailsFragment.addProductToCart(productDetailsFragment.getLoadedProduct(), Source.GROUP_BUY_CREATE);
                } else {
                    ProductDetailsServiceFragment.this.withActivity(new ActivityTask<BaseActivity>() {
                        public void performTask(BaseActivity baseActivity) {
                            baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str2), new BaseDialogCallback() {
                                public void onCancel(BaseDialogFragment baseDialogFragment) {
                                    if (z4) {
                                        productDetailsFragment.handleGroupBuysLoaded(arrayList2, wishGroupBuyInfo2);
                                        return;
                                    }
                                    ProductDetailsServiceFragment.this.showGroupBuyDialog(arrayList2, wishGroupBuyInfo2);
                                    if (ExperimentDataCenter.getInstance().shouldSeeGroupBuy()) {
                                        productDetailsFragment.handleGroupBuysLoaded(arrayList2, wishGroupBuyInfo2);
                                    }
                                }

                                public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                                    if (z4) {
                                        productDetailsFragment.handleGroupBuysLoaded(arrayList2, wishGroupBuyInfo2);
                                        return;
                                    }
                                    ProductDetailsServiceFragment.this.showGroupBuyDialog(arrayList2, wishGroupBuyInfo2);
                                    if (ExperimentDataCenter.getInstance().shouldSeeGroupBuy()) {
                                        productDetailsFragment.handleGroupBuysLoaded(arrayList2, wishGroupBuyInfo2);
                                    }
                                }
                            });
                        }
                    });
                }
            }
        };
        withUiFragment(r0, "FragmentTagMainContent");
    }

    public void createGroupBuy(final boolean z) {
        showLoadingSpinner();
        withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                ProductDetailsServiceFragment.this.mCreateGroupBuyService.requestService(productDetailsFragment.getLoadedProduct().getProductId(), new CreateGroupBuyService.SuccessCallback() {
                    public void onSuccess(boolean z, String str, ArrayList<WishGroupBuyRowInfo> arrayList, WishGroupBuyInfo wishGroupBuyInfo) {
                        ProductDetailsServiceFragment.this.createGroupBuyResponseHandler(z, z, str, arrayList, wishGroupBuyInfo);
                    }
                }, new FailureCallback() {
                    public void onFailure(String str) {
                        ProductDetailsServiceFragment.this.hideLoadingSpinner();
                        ProductDetailsServiceFragment.this.showErrorMessage(str);
                    }
                });
            }
        }, "FragmentTagMainContent");
    }

    /* access modifiers changed from: private */
    public void joinGroupBuyResponseHandler(boolean z, Boolean bool, String str, ArrayList<WishGroupBuyRowInfo> arrayList, WishGroupBuyInfo wishGroupBuyInfo) {
        final Boolean bool2 = bool;
        final String str2 = str;
        final boolean z2 = z;
        final ArrayList<WishGroupBuyRowInfo> arrayList2 = arrayList;
        final WishGroupBuyInfo wishGroupBuyInfo2 = wishGroupBuyInfo;
        AnonymousClass29 r0 = new UiTask<BaseActivity, ProductDetailsFragment>() {
            public void performTask(BaseActivity baseActivity, final ProductDetailsFragment productDetailsFragment) {
                ProductDetailsServiceFragment.this.hideLoadingSpinner();
                if (bool2.booleanValue()) {
                    productDetailsFragment.addProductToCart(productDetailsFragment.getLoadedProduct(), Source.GROUP_BUY_JOIN);
                } else {
                    ProductDetailsServiceFragment.this.withActivity(new ActivityTask<BaseActivity>() {
                        public void performTask(BaseActivity baseActivity) {
                            baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str2), new BaseDialogCallback() {
                                public void onCancel(BaseDialogFragment baseDialogFragment) {
                                    if (z2) {
                                        productDetailsFragment.handleGroupBuysLoaded(arrayList2, wishGroupBuyInfo2);
                                        return;
                                    }
                                    if (ExperimentDataCenter.getInstance().shouldSeeGroupBuy()) {
                                        productDetailsFragment.handleGroupBuysLoaded(arrayList2, wishGroupBuyInfo2);
                                    }
                                    ProductDetailsServiceFragment.this.showGroupBuyDialog(arrayList2, wishGroupBuyInfo2);
                                }

                                public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                                    if (z2) {
                                        productDetailsFragment.handleGroupBuysLoaded(arrayList2, wishGroupBuyInfo2);
                                        return;
                                    }
                                    if (ExperimentDataCenter.getInstance().shouldSeeGroupBuy()) {
                                        productDetailsFragment.handleGroupBuysLoaded(arrayList2, wishGroupBuyInfo2);
                                    }
                                    ProductDetailsServiceFragment.this.showGroupBuyDialog(arrayList2, wishGroupBuyInfo2);
                                }
                            });
                        }
                    });
                }
            }
        };
        withUiFragment(r0, "FragmentTagMainContent");
    }

    public void joinGroupBuy(final String str, final boolean z) {
        showLoadingSpinner();
        withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                ProductDetailsServiceFragment.this.mJoinGroupBuyService.requestService(str, productDetailsFragment.getLoadedProduct().getProductId(), new JoinGroupBuyService.SuccessCallback() {
                    public void onSuccess(Boolean bool, String str, ArrayList<WishGroupBuyRowInfo> arrayList, WishGroupBuyInfo wishGroupBuyInfo) {
                        ProductDetailsServiceFragment.this.joinGroupBuyResponseHandler(z, bool, str, arrayList, wishGroupBuyInfo);
                    }
                }, new JoinGroupBuyService.FailureCallback() {
                    public void onFailure(String str) {
                        ProductDetailsServiceFragment.this.hideLoadingSpinner();
                        ProductDetailsServiceFragment.this.showErrorMessage(str);
                    }
                });
            }
        }, "FragmentTagMainContent");
    }

    /* access modifiers changed from: private */
    public void showGroupBuyDialog(final ArrayList<WishGroupBuyRowInfo> arrayList, final WishGroupBuyInfo wishGroupBuyInfo) {
        withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                final GroupBuyDialogFragment createGroupBuyDialog = GroupBuyDialogFragment.createGroupBuyDialog(arrayList, wishGroupBuyInfo, productDetailsFragment.getLoadedProduct());
                if (createGroupBuyDialog != null) {
                    ProductDetailsServiceFragment.this.withActivity(new ActivityTask<BaseActivity>() {
                        public void performTask(BaseActivity baseActivity) {
                            baseActivity.startDialog(createGroupBuyDialog, new BaseDialogCallback() {
                                public void onCancel(BaseDialogFragment baseDialogFragment) {
                                }

                                public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                                    if (bundle != null) {
                                        String string = bundle.getString("ResultSelectedGroupBuyId");
                                        if (string != null) {
                                            ProductDetailsServiceFragment.this.joinGroupBuy(string, false);
                                        } else if (bundle.getBoolean("ResultSelectedCreateGroupBuy")) {
                                            ProductDetailsServiceFragment.this.createGroupBuy(false);
                                        }
                                    }
                                }
                            });
                        }
                    });
                }
            }
        }, "FragmentTagMainContent");
    }

    public void loadGroupBuys(String str, final boolean z) {
        if (z) {
            showLoadingSpinner();
        }
        this.mGetGroupBuyService.requestService(str, new GetGroupBuysService.SuccessCallback() {
            public void onSuccess(final ArrayList<WishGroupBuyRowInfo> arrayList, final WishGroupBuyInfo wishGroupBuyInfo) {
                ProductDetailsServiceFragment.this.hideLoadingSpinner();
                if (!z) {
                    ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                        public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                            productDetailsFragment.handleGroupBuysLoaded(arrayList, wishGroupBuyInfo);
                        }
                    }, "FragmentTagMainContent");
                    return;
                }
                ProductDetailsServiceFragment.this.showGroupBuyDialog(arrayList, wishGroupBuyInfo);
                if (ExperimentDataCenter.getInstance().shouldSeeGroupBuy()) {
                    ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                        public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                            productDetailsFragment.handleGroupBuysLoaded(arrayList, wishGroupBuyInfo);
                        }
                    }, "FragmentTagMainContent");
                }
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                if (!ExperimentDataCenter.getInstance().shouldSeeGroupBuy() || z) {
                    ProductDetailsServiceFragment.this.hideLoadingSpinner();
                    ProductDetailsServiceFragment.this.showErrorMessage(str);
                }
            }
        });
    }

    public void loadRecommendedWishlists(String str) {
        this.mGetRecommendedWishlistsService.requestService(str, new GetRecommendedWishlistsService.SuccessCallback() {
            public void onSuccess(ArrayList<WishWishlist> arrayList, ArrayList<WishUser> arrayList2, String str, String str2) {
                ProductDetailsServiceFragment productDetailsServiceFragment = ProductDetailsServiceFragment.this;
                final ArrayList<WishWishlist> arrayList3 = arrayList;
                final ArrayList<WishUser> arrayList4 = arrayList2;
                final String str3 = str;
                final String str4 = str2;
                AnonymousClass1 r1 = new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        productDetailsFragment.handleRecommendedWishlistsLoaded(arrayList3, arrayList4, str3, str4);
                    }
                };
                productDetailsServiceFragment.withUiFragment(r1, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        productDetailsFragment.handleRecommendedWishlistsFailed();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadRelatedExpressShippingProducts(String str, int i, long j) {
        this.mGetRelatedProductFeedService.requestService(str, i, j, "express", (BaseFeedApiService.SuccessCallback<Object>) new BaseFeedApiService.SuccessCallback<Object>() {
            public void onSuccess(final ArrayList<Object> arrayList, final boolean z, final int i, FeedExtraDataBundle feedExtraDataBundle) {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        productDetailsFragment.handleRelatedProductsLoaded(arrayList, z, i);
                    }
                }, "FragmentTagMainContent");
            }
        }, (DefaultFailureCallback) new DefaultFailureCallback() {
            public void onFailure(String str) {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        productDetailsFragment.handleRelatedProductsFailed();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadRelatedExpressShippingProductsRow(String str, int i, long j) {
        if (!this.mGetRelatedProductFeedService.isPending()) {
            this.mGetRelatedProductFeedService.requestService(str, i, j, "express", (ExpressProductSuccessCallback) new ExpressProductSuccessCallback() {
                public void onSuccess(final ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec, int i, boolean z) {
                    ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                        public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                            productDetailsFragment.handleRelatedExpressItemsLoaded(productDetailsRelatedRowSpec);
                        }
                    }, "FragmentTagMainContent");
                }
            }, (DefaultFailureCallback) new DefaultFailureCallback() {
                public void onFailure(String str) {
                    ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                        public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                            productDetailsFragment.handleRelatedExpressItemsFailed();
                            productDetailsFragment.handleRelatedProductsFailed();
                        }
                    }, "FragmentTagMainContent");
                }
            });
        }
    }

    public void showBadgeDescriptionDialog(List<WishProductBadge> list) {
        BadgeDescriptionBottomSheet.create(getBaseActivity(), list).show();
    }

    public void loadBuyerGuaranteeInfo() {
        this.mGetBuyerGuaranteeService.requestService(new GetBuyerGuaranteeService.SuccessCallback() {
            public void onSuccess(final BuyerGuaranteeInfo buyerGuaranteeInfo) {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        productDetailsFragment.handleBuyerGuaranteeInfoLoaded(buyerGuaranteeInfo);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        productDetailsFragment.handleBuyerGuaranteeInfoFailed();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadRelatedProductBoostProducts(int i, String str, int i2, int i3) {
        this.mGetRelatedProductBoostService.requestService(str, i2, (long) i3, new GetRelatedProductBoostProductsService.SuccessCallback() {
            public void onSuccess(final ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec) {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        productDetailsFragment.handleRelatedProductBoostItemsLoaded(productDetailsRelatedRowSpec);
                    }
                });
            }
        }, null);
    }

    public void loadVisuallySimilarProductRow(String str, int i, int i2) {
        this.mGetRelatedProductFeedService.requestService(str, i, (long) i2, "visual_recommendation", (ExpressProductSuccessCallback) new ExpressProductSuccessCallback() {
            public void onSuccess(final ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec, int i, boolean z) {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        productDetailsFragment.handleVisuallySimilarItemsLoaded(productDetailsRelatedRowSpec);
                    }
                }, "FragmentTagMainContent");
            }
        }, (DefaultFailureCallback) new DefaultFailureCallback() {
            public void onFailure(String str) {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        productDetailsFragment.handleVisuallySimilarItemsLoadFailed();
                        productDetailsFragment.handleRelatedProductsFailed();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void addToPriceWatch(String str) {
        this.mAddToPriceWatchService.requestService(str, new AddToPriceWatchService.SuccessCallback() {
            public void onSuccess(final WishPriceWatchChangeInfo wishPriceWatchChangeInfo, final boolean z) {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        if (!z) {
                            productDetailsFragment.refreshPriceWatchState(true);
                            ProductDetailsServiceFragment.this.showPriceWatchDialogFragment(wishPriceWatchChangeInfo);
                            return;
                        }
                        ProductDetailsServiceFragment.this.showLimitReachedDialog(wishPriceWatchChangeInfo);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                final String string = WishApplication.getInstance().getString(R.string.price_watch_failed_to_add);
                ProductDetailsServiceFragment.this.withActivity(new ActivityTask<BaseActivity>() {
                    public void performTask(BaseActivity baseActivity) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(string));
                    }
                });
            }
        });
    }

    public void showPriceWatchDialogFragment(WishPriceWatchChangeInfo wishPriceWatchChangeInfo) {
        if (wishPriceWatchChangeInfo != null) {
            final ItemChangeToPriceWatchDialogFragment createItemChangeToPriceWatchDialogFragment = ItemChangeToPriceWatchDialogFragment.createItemChangeToPriceWatchDialogFragment(wishPriceWatchChangeInfo.getTitle(), wishPriceWatchChangeInfo.getBody(), wishPriceWatchChangeInfo.showLink());
            if (createItemChangeToPriceWatchDialogFragment != null) {
                withActivity(new ActivityTask<BaseActivity>() {
                    public void performTask(BaseActivity baseActivity) {
                        baseActivity.startDialog(createItemChangeToPriceWatchDialogFragment);
                    }
                });
            }
        }
    }

    public void showLimitReachedDialog(WishPriceWatchChangeInfo wishPriceWatchChangeInfo) {
        if (wishPriceWatchChangeInfo != null) {
            final MultiButtonDialogFragment createMultiButtonYesDialog = MultiButtonDialogFragment.createMultiButtonYesDialog(wishPriceWatchChangeInfo.getTitle(), wishPriceWatchChangeInfo.getBody(), getResources().getString(R.string.price_watch_go_to), R.drawable.main_button_selector);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PRICE_WATCH_ITEM_LIMIT_DIALOG);
            withActivity(new ActivityTask<BaseActivity>() {
                public void performTask(final BaseActivity baseActivity) {
                    baseActivity.startDialog(createMultiButtonYesDialog, new BaseDialogCallback() {
                        public void onCancel(BaseDialogFragment baseDialogFragment) {
                        }

                        public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRICE_WATCH_ITEM_LIMIT_DIALOG_ACTION);
                            Intent intent = new Intent();
                            intent.setClass(baseActivity, PriceWatchActivity.class);
                            intent.putExtra("ExtraShowBackButton", true);
                            ProductDetailsServiceFragment.this.startActivity(intent);
                        }
                    });
                }
            });
        }
    }

    public void removeFromPriceWatch(String str) {
        this.mRemoveFromPriceWatchService.requestService(str, new RemoveFromPriceWatchService.SuccessCallback() {
            public void onSuccess(final WishPriceWatchChangeInfo wishPriceWatchChangeInfo) {
                ProductDetailsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                        productDetailsFragment.refreshPriceWatchState(false);
                        ProductDetailsServiceFragment.this.showPriceWatchDialogFragment(wishPriceWatchChangeInfo);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                final String string = WishApplication.getInstance().getString(R.string.price_watch_failed_to_remove);
                ProductDetailsServiceFragment.this.withActivity(new ActivityTask<BaseActivity>() {
                    public void performTask(BaseActivity baseActivity) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(string));
                    }
                });
            }
        });
    }

    public void redeemRewardProduct(WishProduct wishProduct, DefaultSuccessCallback defaultSuccessCallback) {
        this.mRewardProductRedemptionDelegate.redeemRewardProduct(wishProduct, defaultSuccessCallback);
    }

    public void showDailyLoginPopup(final WishDailyLoginStampSpec wishDailyLoginStampSpec) {
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.startDialog(DailyLoginBonusStampPopupDialog.createDailyLoginBonusStampPopupDialog(wishDailyLoginStampSpec, false));
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_LOGIN_SPLASH_IN_PRODUCT_DETAILS);
            }
        });
    }
}
