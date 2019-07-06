package com.contextlogic.wish.activity.feed;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.DialogTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.activity.dailybonus.DailyLoginBonusDialogFragment;
import com.contextlogic.wish.activity.dailybonus.DailyLoginBonusStampPopupDialog;
import com.contextlogic.wish.activity.feed.dailyraffle.EnterRaffleDialogFragment;
import com.contextlogic.wish.activity.feed.filter.FilterFragment;
import com.contextlogic.wish.activity.orderconfirmed.InviteCouponDialogFragment;
import com.contextlogic.wish.activity.productdetails.ProductDetailsFragment;
import com.contextlogic.wish.activity.productdetails.ProductDetailsRelatedItemsFragment;
import com.contextlogic.wish.activity.profile.wishlist.CreateWishlistDialogFragment;
import com.contextlogic.wish.activity.profile.wishlist.SelectWishlistDialogFragment;
import com.contextlogic.wish.activity.profile.wishlist.WishlistFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.MerchantFeedItem;
import com.contextlogic.wish.api.model.ProductDetailsRelatedRowSpec;
import com.contextlogic.wish.api.model.WishBrandFilter;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.api.model.WishCurrentDailyGiveawayInfo;
import com.contextlogic.wish.api.model.WishDailyLoginStampSpec;
import com.contextlogic.wish.api.model.WishDealDashInfo;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishInfoDailyGiveawayInfo;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishSignupFreeGiftCart;
import com.contextlogic.wish.api.model.WishUpcomingDailyGiveawayInfo;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.AddToWishlistService;
import com.contextlogic.wish.api.service.standalone.BaseFeedApiService;
import com.contextlogic.wish.api.service.standalone.BaseFeedApiService.FeedExtraDataBundle;
import com.contextlogic.wish.api.service.standalone.ClaimDailyGiveawayService;
import com.contextlogic.wish.api.service.standalone.ClaimFreeSignupGiftService;
import com.contextlogic.wish.api.service.standalone.DeleteWishlistService;
import com.contextlogic.wish.api.service.standalone.EnterDailyRaffleService;
import com.contextlogic.wish.api.service.standalone.GetBrandFeedService;
import com.contextlogic.wish.api.service.standalone.GetCurrentDailyGiveawayService;
import com.contextlogic.wish.api.service.standalone.GetDailyGiveawayInfoService;
import com.contextlogic.wish.api.service.standalone.GetFeedService;
import com.contextlogic.wish.api.service.standalone.GetFilteredFeedService;
import com.contextlogic.wish.api.service.standalone.GetFilteredFeedService.FeedContext;
import com.contextlogic.wish.api.service.standalone.GetFilteredFeedService.FeedExtraInfo;
import com.contextlogic.wish.api.service.standalone.GetFilteredFeedService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetHomePageRowsService;
import com.contextlogic.wish.api.service.standalone.GetHomePageRowsService.FailureCallback;
import com.contextlogic.wish.api.service.standalone.GetMerchantFeedService;
import com.contextlogic.wish.api.service.standalone.GetProductService;
import com.contextlogic.wish.api.service.standalone.GetRelatedProductBoostProductsService;
import com.contextlogic.wish.api.service.standalone.GetRelatedProductFeedService;
import com.contextlogic.wish.api.service.standalone.GetUpcomingDailyGiveawayService;
import com.contextlogic.wish.api.service.standalone.GetUserWishlistsService;
import com.contextlogic.wish.api.service.standalone.GetWishlistNameSuggestionService;
import com.contextlogic.wish.api.service.standalone.GetWishlistProductsService;
import com.contextlogic.wish.api.service.standalone.MerchantsFeedTabService;
import com.contextlogic.wish.api.service.standalone.ProductSearchService;
import com.contextlogic.wish.api.service.standalone.RecentlyViewedMerchantsService;
import com.contextlogic.wish.api.service.standalone.RemoveFromWishlistService;
import com.contextlogic.wish.api.service.standalone.RenameWishlistService;
import com.contextlogic.wish.api.service.standalone.SpinDealDashService;
import com.contextlogic.wish.api.service.standalone.StartDealDashService;
import com.contextlogic.wish.api.service.standalone.TopRatedMerchantsService;
import com.contextlogic.wish.api.service.standalone.TrendingMerchantsService;
import com.contextlogic.wish.api.service.standalone.WishlistFollowService;
import com.contextlogic.wish.api.service.standalone.WishlistFollowStateService;
import com.contextlogic.wish.api.service.standalone.WishlistUnfollowService;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.dialog.freegift.GiftConfirmedDialogFragment;
import com.contextlogic.wish.dialog.freegift.GiftInCartDialogFragment;
import com.contextlogic.wish.dialog.freegift.OrderConfirmedDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.ImageSize;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.social.facebook.FacebookManager;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.util.PreferenceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class BaseProductFeedServiceFragment extends ServiceFragment<BaseActivity> {
    /* access modifiers changed from: private */
    public AddToWishlistService mAddToWishListService;
    private ClaimDailyGiveawayService mClaimDailyGiveawayService;
    private ClaimFreeSignupGiftService mClaimFreeSignupGiftService;
    /* access modifiers changed from: private */
    public DeleteWishlistService mDeleteWishlistService;
    private EnterDailyRaffleService mEnterDailyRaffleService;
    private GetBrandFeedService mGetBrandFeedService;
    private GetCurrentDailyGiveawayService mGetCurrentDailyGiveawayService;
    private GetFeedService mGetFeedService;
    private GetFilteredFeedService mGetFilteredFeedService;
    /* access modifiers changed from: private */
    public GetHomePageRowsService mGetHomePageRowsService;
    private GetDailyGiveawayInfoService mGetInfoDailyGiveawayService;
    private GetMerchantFeedService mGetMerchantFeedService;
    private GetProductService mGetProductService;
    protected GetRelatedProductBoostProductsService mGetRelatedProductBoostService;
    /* access modifiers changed from: private */
    public GetRelatedProductFeedService mGetRelatedProductFeedService;
    private GetUpcomingDailyGiveawayService mGetUpcomingDailyGiveawayService;
    private GetUserWishlistsService mGetUserWishlistsService;
    private GetWishlistNameSuggestionService mGetWishlistNameSuggestionService;
    private GetWishlistProductsService mGetWishlistProductsService;
    private int mLastFeedRequestIndex;
    /* access modifiers changed from: private */
    public int mLoadRelatedProductsCount;
    private ProductSearchService mProductSearchService;
    private RecentlyViewedMerchantsService mRecentlyViewedMerchantsService;
    /* access modifiers changed from: private */
    public RemoveFromWishlistService mRemoveFromWishlistService;
    /* access modifiers changed from: private */
    public RenameWishlistService mRenameWishlistService;
    private SpinDealDashService mSpinDealDashService;
    private StartDealDashService mStartDealDashService;
    private TopRatedMerchantsService mTopRatedMerchantsService;
    private TrendingMerchantsService mTrendingMerchantsService;
    private WishlistFollowService mWishlistFollowService;
    private WishlistFollowStateService mWishlistFollowStateService;
    private WishlistUnfollowService mWishlistUnfollowService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetFilteredFeedService = new GetFilteredFeedService();
        this.mGetWishlistProductsService = new GetWishlistProductsService();
        this.mProductSearchService = new ProductSearchService();
        this.mGetFeedService = new GetFeedService();
        this.mGetBrandFeedService = new GetBrandFeedService();
        this.mGetMerchantFeedService = new GetMerchantFeedService();
        this.mStartDealDashService = new StartDealDashService();
        this.mSpinDealDashService = new SpinDealDashService();
        this.mGetRelatedProductFeedService = new GetRelatedProductFeedService();
        this.mRenameWishlistService = new RenameWishlistService();
        this.mDeleteWishlistService = new DeleteWishlistService();
        this.mAddToWishListService = new AddToWishlistService();
        this.mRemoveFromWishlistService = new RemoveFromWishlistService();
        this.mGetWishlistNameSuggestionService = new GetWishlistNameSuggestionService();
        this.mGetUserWishlistsService = new GetUserWishlistsService();
        this.mClaimFreeSignupGiftService = new ClaimFreeSignupGiftService();
        this.mGetHomePageRowsService = new GetHomePageRowsService();
        this.mClaimDailyGiveawayService = new ClaimDailyGiveawayService();
        this.mGetCurrentDailyGiveawayService = new GetCurrentDailyGiveawayService();
        this.mGetUpcomingDailyGiveawayService = new GetUpcomingDailyGiveawayService();
        this.mGetInfoDailyGiveawayService = new GetDailyGiveawayInfoService();
        this.mGetProductService = new GetProductService();
        this.mEnterDailyRaffleService = new EnterDailyRaffleService();
        this.mRecentlyViewedMerchantsService = new RecentlyViewedMerchantsService();
        this.mTopRatedMerchantsService = new TopRatedMerchantsService();
        this.mTrendingMerchantsService = new TrendingMerchantsService();
        this.mWishlistFollowStateService = new WishlistFollowStateService();
        this.mWishlistFollowService = new WishlistFollowService();
        this.mWishlistUnfollowService = new WishlistUnfollowService();
        this.mGetRelatedProductBoostService = new GetRelatedProductBoostProductsService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetFilteredFeedService.cancelAllRequests();
        this.mGetWishlistProductsService.cancelAllRequests();
        this.mProductSearchService.cancelAllRequests();
        this.mGetFeedService.cancelAllRequests();
        this.mGetBrandFeedService.cancelAllRequests();
        this.mGetMerchantFeedService.cancelAllRequests();
        this.mStartDealDashService.cancelAllRequests();
        this.mSpinDealDashService.cancelAllRequests();
        this.mGetRelatedProductFeedService.cancelAllRequests();
        this.mRenameWishlistService.cancelAllRequests();
        this.mDeleteWishlistService.cancelAllRequests();
        this.mAddToWishListService.cancelAllRequests();
        this.mRemoveFromWishlistService.cancelAllRequests();
        this.mGetWishlistNameSuggestionService.cancelAllRequests();
        this.mGetUserWishlistsService.cancelAllRequests();
        this.mClaimFreeSignupGiftService.cancelAllRequests();
        this.mGetHomePageRowsService.cancelAllRequests();
        this.mClaimDailyGiveawayService.cancelAllRequests();
        this.mGetCurrentDailyGiveawayService.cancelAllRequests();
        this.mGetUpcomingDailyGiveawayService.cancelAllRequests();
        this.mGetInfoDailyGiveawayService.cancelAllRequests();
        this.mGetProductService.cancelAllRequests();
        this.mEnterDailyRaffleService.cancelAllRequests();
        this.mRecentlyViewedMerchantsService.cancelAllRequests();
        this.mTopRatedMerchantsService.cancelAllRequests();
        this.mTrendingMerchantsService.cancelAllRequests();
        this.mWishlistFollowStateService.cancelAllRequests();
        this.mWishlistFollowService.cancelAllRequests();
        this.mWishlistUnfollowService.cancelAllRequests();
        this.mGetRelatedProductBoostService.cancelAllRequests();
    }

    public boolean isLoadingProducts(int i) {
        return (this.mGetFilteredFeedService.isPending() && this.mLastFeedRequestIndex == i) || this.mGetWishlistProductsService.isPending() || (this.mProductSearchService.isPending() && this.mLastFeedRequestIndex == i) || this.mGetFeedService.isPending() || this.mGetBrandFeedService.isPending() || this.mGetMerchantFeedService.isPending() || this.mGetRelatedProductFeedService.isPending();
    }

    public void loadFilterFeedProducts(final int i, final int i2, FeedContext feedContext) {
        if (i == 0 || feedContext.requestId != null) {
            this.mLastFeedRequestIndex = i;
            this.mGetFilteredFeedService.requestService(i2, 30, feedContext, new SuccessCallback() {
                public void onSuccess(ArrayList<WishProduct> arrayList, int i, boolean z, final FeedExtraInfo feedExtraInfo) {
                    BaseProductFeedServiceFragment baseProductFeedServiceFragment = BaseProductFeedServiceFragment.this;
                    final FeedExtraInfo feedExtraInfo2 = feedExtraInfo;
                    final ArrayList<WishProduct> arrayList2 = arrayList;
                    final int i2 = i;
                    final boolean z2 = z;
                    AnonymousClass1 r1 = new UiTask<BaseActivity, ProductFeedFragment>() {
                        public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                            boolean z = feedExtraInfo2.mainCategories != null && feedExtraInfo2.mainCategories.size() > 0;
                            if (z) {
                                productFeedFragment.updateMainCategories(feedExtraInfo2);
                            } else if (feedExtraInfo2.brandedFilter != null) {
                                productFeedFragment.updateFilter(feedExtraInfo2.brandedFilter);
                            }
                            productFeedFragment.handleLoadingSuccess(i, arrayList2, i2, z2, z, feedExtraInfo2, null);
                        }
                    };
                    baseProductFeedServiceFragment.withUiFragment(r1, "FragmentTagMainContent");
                    BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, FilterFragment>() {
                        public void performTask(BaseActivity baseActivity, FilterFragment filterFragment) {
                            if (feedExtraInfo.mainCategories != null && feedExtraInfo.mainCategories.size() > 0) {
                                filterFragment.updateMainCategories(feedExtraInfo.mainCategories);
                            } else if (feedExtraInfo.brandedFilter != null) {
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(feedExtraInfo.brandedFilter);
                                filterFragment.updateMainCategories(arrayList);
                            }
                        }
                    }, "FragmentTagRightDrawer");
                    BaseProductFeedServiceFragment.this.handleFeedLoaded();
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                    BaseProductFeedServiceFragment.this.handleDefaultFailure(i, i2, str);
                    BaseProductFeedServiceFragment.this.handleFeedLoaded();
                }
            });
        }
    }

    public void loadWishlistProducts(String str, final int i) {
        this.mGetWishlistProductsService.requestService(str, i, 30, new GetWishlistProductsService.SuccessCallback() {
            public void onSuccess(final ArrayList<WishProduct> arrayList, final boolean z, final int i) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        productFeedFragment.handleLoadingSuccess(0, arrayList, i, z);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                BaseProductFeedServiceFragment.this.handleDefaultFailure(i, str);
            }
        });
    }

    public void loadSearchResult(final int i, final String str, final int i2, ProductSearchService.FeedContext feedContext) {
        this.mLastFeedRequestIndex = i;
        this.mProductSearchService.requestService(str, i2, 30, feedContext, new ProductSearchService.SuccessCallback() {
            public void onSuccess(final ArrayList<WishProduct> arrayList, final int i, final ProductSearchService.FeedExtraInfo feedExtraInfo) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        if (feedExtraInfo.appIndexingData != null) {
                            BaseProductFeedServiceFragment.this.trackGoogleAppIndexAction(feedExtraInfo.appIndexingData);
                        }
                        productFeedFragment.handleLoadingSuccess(i, arrayList, i, i > feedExtraInfo.totalCount);
                    }
                }, "FragmentTagMainContent");
                boolean z = i2 == 0;
                if (!arrayList.isEmpty() && z) {
                    FacebookManager.getInstance().logSearch(arrayList, str);
                    GoogleAnalyticsLogger.getInstance().logSearch(str);
                }
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                BaseProductFeedServiceFragment.this.handleDefaultFailure(i, i2, str);
            }
        });
    }

    public void loadFeedProducts(final int i, GetFeedService.FeedContext feedContext) {
        this.mGetFeedService.requestService(i, 30, feedContext, new GetFeedService.SuccessCallback() {
            public void onSuccess(ArrayList<Object> arrayList, boolean z, int i, GetFeedService.FeedExtraInfo feedExtraInfo) {
                BaseProductFeedServiceFragment baseProductFeedServiceFragment = BaseProductFeedServiceFragment.this;
                final GetFeedService.FeedExtraInfo feedExtraInfo2 = feedExtraInfo;
                final ArrayList<Object> arrayList2 = arrayList;
                final int i2 = i;
                final boolean z2 = z;
                AnonymousClass1 r1 = new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        if (feedExtraInfo2.tagTitle != null) {
                            productFeedFragment.updateActionBarTitle(feedExtraInfo2.tagTitle);
                        }
                        productFeedFragment.handleLoadingSuccess(0, arrayList2, i2, z2);
                    }
                };
                baseProductFeedServiceFragment.withUiFragment(r1, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                BaseProductFeedServiceFragment.this.handleDefaultFailure(i, str);
            }
        });
    }

    public void loadRelatedProducts(int i, final int i2, final FeedContext feedContext) {
        withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
            public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                productDetailsFragment.getLoadedProduct();
                BaseProductFeedServiceFragment.this.mGetRelatedProductFeedService.requestService(feedContext.requestId, i2, 30, new BaseFeedApiService.SuccessCallback<Object>() {
                    public void onSuccess(final ArrayList<Object> arrayList, final boolean z, final int i, FeedExtraDataBundle feedExtraDataBundle) {
                        BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                            public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                                productDetailsFragment.handleRelatedProductsLoaded(arrayList, z, i);
                                BaseProductFeedServiceFragment.this.mLoadRelatedProductsCount = BaseProductFeedServiceFragment.this.mLoadRelatedProductsCount + 1;
                            }
                        }, "FragmentTagMainContent");
                    }
                }, new DefaultFailureCallback() {
                    public void onFailure(String str) {
                        BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsFragment>() {
                            public void performTask(BaseActivity baseActivity, ProductDetailsFragment productDetailsFragment) {
                                productDetailsFragment.handleRelatedProductsFailed();
                            }
                        }, "FragmentTagMainContent");
                    }
                });
            }
        }, "FragmentTagMainContent");
    }

    public void loadBrandFeed(WishBrandFilter wishBrandFilter, final int i) {
        this.mGetBrandFeedService.requestService(wishBrandFilter, i, 30, new BaseFeedApiService.SuccessCallback<Object>() {
            public void onSuccess(final ArrayList<Object> arrayList, final boolean z, final int i, FeedExtraDataBundle feedExtraDataBundle) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        productFeedFragment.handleLoadingSuccess(0, arrayList, i, z);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                BaseProductFeedServiceFragment.this.handleDefaultFailure(i, str);
            }
        });
    }

    public void loadMerchantFeed(WishBrandFilter wishBrandFilter, final int i, GetMerchantFeedService.FeedContext feedContext) {
        this.mGetMerchantFeedService.requestService(wishBrandFilter, i, 30, feedContext, new BaseFeedApiService.SuccessCallback<Object>() {
            public void onSuccess(ArrayList<Object> arrayList, boolean z, int i, FeedExtraDataBundle feedExtraDataBundle) {
                BaseProductFeedServiceFragment baseProductFeedServiceFragment = BaseProductFeedServiceFragment.this;
                final FeedExtraDataBundle feedExtraDataBundle2 = feedExtraDataBundle;
                final ArrayList<Object> arrayList2 = arrayList;
                final int i2 = i;
                final boolean z2 = z;
                AnonymousClass1 r1 = new UiTask<BaseActivity, BaseProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseProductFeedFragment baseProductFeedFragment) {
                        baseProductFeedFragment.updateFeedExtraDataBundle(feedExtraDataBundle2);
                        baseProductFeedFragment.handleLoadingSuccess(0, arrayList2, i2, z2);
                    }
                };
                baseProductFeedServiceFragment.withUiFragment(r1, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                BaseProductFeedServiceFragment.this.handleDefaultFailure(i, str);
            }
        });
    }

    public void loadWishlistFollowState(String str) {
        this.mWishlistFollowStateService.requestService(str, new WishlistFollowStateService.SuccessCallback() {
            public void onSuccess(final WishWishlist wishWishlist, final boolean z, final boolean z2) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishlistFragment>() {
                    public void performTask(BaseActivity baseActivity, WishlistFragment wishlistFragment) {
                        wishlistFragment.handleGetWishlistFollowState(wishWishlist, z, z2);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                BaseProductFeedServiceFragment.this.handleDefaultFailure(0, str);
            }
        });
    }

    public void followUnFollowWishlist(String str, boolean z) {
        if (z) {
            this.mWishlistFollowService.requestService(str, new DefaultSuccessCallback() {
                public void onSuccess() {
                    BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishlistFragment>() {
                        public void performTask(BaseActivity baseActivity, WishlistFragment wishlistFragment) {
                            wishlistFragment.handleGetWishlistFollowState(null, true, true);
                        }
                    }, "FragmentTagMainContent");
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                    BaseProductFeedServiceFragment.this.handleDefaultFailure(0, str);
                }
            });
        } else {
            this.mWishlistUnfollowService.requestService(str, new DefaultSuccessCallback() {
                public void onSuccess() {
                    BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishlistFragment>() {
                        public void performTask(BaseActivity baseActivity, WishlistFragment wishlistFragment) {
                            wishlistFragment.handleGetWishlistFollowState(null, false, true);
                        }
                    }, "FragmentTagMainContent");
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                    BaseProductFeedServiceFragment.this.handleDefaultFailure(0, str);
                }
            });
        }
    }

    public void startDealDash(int i, int i2) {
        this.mStartDealDashService.requestService(i, i2, new StartDealDashService.SuccessCallback() {
            public void onSuccess(final WishDealDashInfo wishDealDashInfo) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        productFeedFragment.handleStartDealDashSuccess(wishDealDashInfo);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        productFeedFragment.handleStartDealDashFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void spinDealDash(int i) {
        this.mSpinDealDashService.requestService(i, new SpinDealDashService.SuccessCallback() {
            public void onSuccess(final WishDealDashInfo wishDealDashInfo) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        productFeedFragment.handleSaveDealDashSpinResultSuccess(wishDealDashInfo);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        productFeedFragment.handleSaveDealDashSpinResultFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void renameWishlist(final WishWishlist wishWishlist) {
        final CreateWishlistDialogFragment createWishlistDialogFragment = new CreateWishlistDialogFragment();
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.startDialog(createWishlistDialogFragment, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        final String string = bundle.getString("ResultName");
                        BaseProductFeedServiceFragment.this.mRenameWishlistService.requestService(wishWishlist.getWishlistId(), string, new DefaultSuccessCallback() {
                            public void onSuccess() {
                                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, BaseProductFeedFragment>() {
                                    public void performTask(BaseActivity baseActivity, BaseProductFeedFragment baseProductFeedFragment) {
                                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonOkDialog(BaseProductFeedServiceFragment.this.getString(R.string.done), String.format(BaseProductFeedServiceFragment.this.getString(R.string.wishlist_renamed), new Object[]{string})));
                                        baseProductFeedFragment.handleRenameWishlistSuccess(string);
                                    }
                                }, "FragmentTagMainContent");
                            }
                        }, new DefaultFailureCallback() {
                            public void onFailure(String str) {
                                BaseProductFeedServiceFragment.this.handleWishlistActionFailure(str);
                            }
                        });
                    }
                });
                createWishlistDialogFragment.setInitialName(wishWishlist.getName());
            }
        });
    }

    public void deleteWishlist(final WishWishlist wishWishlist, final int i) {
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                if (i > 0) {
                    baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(BaseProductFeedServiceFragment.this.getString(R.string.wishlist_deleted_error)));
                } else {
                    baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonYesNoDialog(baseActivity.getString(R.string.delete_entire_wishlist), baseActivity.getString(R.string.are_you_sure_delete_wishlist)), new BaseDialogCallback() {
                        public void onCancel(BaseDialogFragment baseDialogFragment) {
                        }

                        public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                            if (i == 1) {
                                BaseProductFeedServiceFragment.this.mDeleteWishlistService.requestService(wishWishlist.getWishlistId(), new DefaultSuccessCallback() {
                                    public void onSuccess() {
                                        BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, BaseProductFeedFragment>() {
                                            public void performTask(BaseActivity baseActivity, BaseProductFeedFragment baseProductFeedFragment) {
                                                baseProductFeedFragment.handleDeleteWishlistSuccess();
                                            }
                                        }, "FragmentTagMainContent");
                                    }
                                }, new DefaultFailureCallback() {
                                    public void onFailure(String str) {
                                        BaseProductFeedServiceFragment.this.handleWishlistActionFailure(str);
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }

    public void moveProducts(final ArrayList<String> arrayList) {
        final SelectWishlistDialogFragment selectWishlistDialogFragment = new SelectWishlistDialogFragment();
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.startDialog(selectWishlistDialogFragment, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 2000) {
                            BaseProductFeedServiceFragment.this.createAndAddToWishlist(arrayList);
                        } else if (bundle != null) {
                            WishWishlist wishWishlist = (WishWishlist) bundle.getParcelable("ResultWishlist");
                            if (wishWishlist != null) {
                                BaseProductFeedServiceFragment.this.addToWishlist(arrayList, wishWishlist.getWishlistId());
                            }
                        }
                    }
                });
            }
        });
    }

    public void addToWishlist(ArrayList<String> arrayList, String str) {
        this.mAddToWishListService.requestService(arrayList, str, (AddToWishlistService.SuccessCallback) new AddToWishlistService.SuccessCallback() {
            public void onSuccess(final WishWishlist wishWishlist) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, BaseProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseProductFeedFragment baseProductFeedFragment) {
                        baseProductFeedFragment.handleWishlistProductActionSuccess(wishWishlist.getName());
                    }
                }, "FragmentTagMainContent");
            }
        }, (DefaultFailureCallback) new DefaultFailureCallback() {
            public void onFailure(String str) {
                BaseProductFeedServiceFragment.this.handleWishlistActionFailure(str);
            }
        });
    }

    public void createAndAddToWishlist(final ArrayList<String> arrayList) {
        final CreateWishlistDialogFragment createWishlistDialogFragment = new CreateWishlistDialogFragment();
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.startDialog(createWishlistDialogFragment, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        BaseProductFeedServiceFragment.this.mAddToWishListService.requestService(arrayList, (String) null, false, bundle.getString("ResultName"), (AddToWishlistService.SuccessCallback) new AddToWishlistService.SuccessCallback() {
                            public void onSuccess(final WishWishlist wishWishlist) {
                                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, BaseProductFeedFragment>() {
                                    public void performTask(BaseActivity baseActivity, BaseProductFeedFragment baseProductFeedFragment) {
                                        baseProductFeedFragment.handleWishlistProductActionSuccess(wishWishlist.getName());
                                    }
                                }, "FragmentTagMainContent");
                            }
                        }, (DefaultFailureCallback) new DefaultFailureCallback() {
                            public void onFailure(String str) {
                                BaseProductFeedServiceFragment.this.handleWishlistActionFailure(str);
                            }
                        });
                    }
                });
            }
        });
        createWishlistDialogFragment.loadNameSuggestions((String) arrayList.get(0));
    }

    public void removeFromWishlist(final ArrayList<String> arrayList, final String str) {
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonYesNoDialog(BaseProductFeedServiceFragment.this.getString(R.string.are_you_sure), BaseProductFeedServiceFragment.this.getString(R.string.are_you_sure_remove_products)), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            BaseProductFeedServiceFragment.this.mRemoveFromWishlistService.requestService(arrayList, str, new DefaultSuccessCallback() {
                                public void onSuccess() {
                                    BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, BaseProductFeedFragment>() {
                                        public void performTask(BaseActivity baseActivity, BaseProductFeedFragment baseProductFeedFragment) {
                                            baseProductFeedFragment.handleWishlistProductActionSuccess();
                                        }
                                    }, "FragmentTagMainContent");
                                }
                            }, new DefaultFailureCallback() {
                                public void onFailure(String str) {
                                    BaseProductFeedServiceFragment.this.handleWishlistActionFailure(str);
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    public void loadDialogWishlists(int i) {
        this.mGetUserWishlistsService.requestService(ProfileDataCenter.getInstance().getUserId(), i, 30, 1, true, new GetUserWishlistsService.SuccessCallback() {
            public void onSuccess(final ArrayList<WishWishlist> arrayList, final int i, final boolean z) {
                BaseProductFeedServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, BaseDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseDialogFragment baseDialogFragment) {
                        if (baseDialogFragment instanceof SelectWishlistDialogFragment) {
                            ((SelectWishlistDialogFragment) baseDialogFragment).handleLoadingSuccess(arrayList, i, z);
                        }
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                BaseProductFeedServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, BaseDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseDialogFragment baseDialogFragment) {
                        BaseProductFeedServiceFragment.this.handleWishlistActionFailure(str);
                        baseDialogFragment.cancel();
                    }
                });
            }
        });
    }

    public void getWishlistNameSuggestion(String str) {
        this.mGetWishlistNameSuggestionService.requestService(str, new GetWishlistNameSuggestionService.SuccessCallback() {
            public void onSuccess(final ArrayList<String> arrayList) {
                BaseProductFeedServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, BaseDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseDialogFragment baseDialogFragment) {
                        if (baseDialogFragment instanceof CreateWishlistDialogFragment) {
                            ((CreateWishlistDialogFragment) baseDialogFragment).updateSuggestions(arrayList);
                        }
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
            }
        });
    }

    public void claimFreeGift(String str, String str2) {
        this.mClaimFreeSignupGiftService.requestService(str, str2, false, true, new ClaimFreeSignupGiftService.SuccessCallback() {
            public void onSuccess(WishSignupFreeGiftCart wishSignupFreeGiftCart) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, BaseProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseProductFeedFragment baseProductFeedFragment) {
                        baseProductFeedFragment.handleClaimFreeGiftSuccess();
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, BaseProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseProductFeedFragment baseProductFeedFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void claimDailyGiveaway(String str, String str2, final WishImage wishImage) {
        showLoadingSpinner();
        this.mClaimDailyGiveawayService.requestService(str, str2, new ClaimDailyGiveawayService.SuccessCallback() {
            public void onSuccess(final WishCart wishCart, final String str, final String str2) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, BaseProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseProductFeedFragment baseProductFeedFragment) {
                        BaseProductFeedServiceFragment.this.hideLoadingSpinner();
                        if (wishCart != null) {
                            int i = 0;
                            while (true) {
                                if (i >= wishCart.getItems().size()) {
                                    break;
                                } else if (((WishCartItem) wishCart.getItems().get(i)).wasJustAdded()) {
                                    BaseProductFeedServiceFragment.this.showDailyGiveawayPopup(false, ((WishCartItem) wishCart.getItems().get(i)).getImage(), str, str2);
                                    break;
                                } else {
                                    i++;
                                }
                            }
                            baseActivity.getActionBarManager().updateCartIcon(true);
                            return;
                        }
                        BaseProductFeedServiceFragment.this.showDailyGiveawayPopup(true, wishImage, str, str2);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, BaseProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseProductFeedFragment baseProductFeedFragment) {
                        BaseProductFeedServiceFragment.this.hideLoadingSpinner();
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void showErrorMessage(final String str) {
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
            }
        });
    }

    public void handleWishlistActionFailure(final String str) {
        withUiFragment(new UiTask<BaseActivity, BaseProductFeedFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedFragment baseProductFeedFragment) {
                baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
            }
        }, "FragmentTagMainContent");
    }

    /* access modifiers changed from: private */
    public void handleDefaultFailure(int i, String str) {
        handleDefaultFailure(0, i, str);
    }

    /* access modifiers changed from: private */
    public void handleDefaultFailure(final int i, final int i2, final String str) {
        withUiFragment(new UiTask<BaseActivity, BaseProductFeedFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedFragment baseProductFeedFragment) {
                if (i2 == 0) {
                    baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                }
                baseProductFeedFragment.handleLoadingErrored(i);
            }
        }, "FragmentTagMainContent");
    }

    public void showLoadingSpinner() {
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.showLoadingDialog();
            }
        });
    }

    public void hideLoadingSpinner() {
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.hideLoadingDialog();
            }
        });
    }

    public void requestHomePageRow(int i, long j, int i2) {
        final int i3 = i;
        final long j2 = j;
        final int i4 = i2;
        AnonymousClass46 r0 = new UiTask<BaseActivity, ProductFeedFragment>() {
            public void performTask(BaseActivity baseActivity, final ProductFeedFragment productFeedFragment) {
                BaseProductFeedServiceFragment.this.mGetHomePageRowsService.requestService(i3, j2, i4, new GetHomePageRowsService.SuccessCallback() {
                    public void onServiceSucceeded(int i, int i2, int i3) {
                        productFeedFragment.handleHomeRowLoadingSuccess(i, i2, i3);
                    }
                }, new FailureCallback() {
                    public void onServiceFailed(int i) {
                        productFeedFragment.handleHomeRowLoadingFailure(i);
                    }
                });
            }
        };
        withUiFragment(r0, "FragmentTagMainContent");
    }

    public void showInviteCouponDialog() {
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.startDialog(new InviteCouponDialogFragment(), new BaseDialogCallback() {
                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                    }

                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                        BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                            public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                                if (PreferenceUtil.getBoolean("NeverShowInviteCouponPopup")) {
                                    productFeedFragment.hideInviteButton();
                                }
                            }
                        }, "FragmentTagMainContent");
                    }
                });
            }
        });
    }

    public void showDailyGiveawayPopup(boolean z, WishImage wishImage, String str, String str2) {
        final boolean z2 = z;
        final String str3 = str;
        final String str4 = str2;
        final WishImage wishImage2 = wishImage;
        AnonymousClass48 r0 = new UiTask<BaseActivity, UiFragment>() {
            public void performTask(final BaseActivity baseActivity, UiFragment uiFragment) {
                MultiButtonDialogChoice multiButtonDialogChoice;
                if (z2) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_GIVEAWAY_NO_STOCK_DIALOG, BaseProductFeedServiceFragment.this.getGiveawayImpressionInfo());
                    multiButtonDialogChoice = new MultiButtonDialogChoice(0, uiFragment.getString(R.string.back_to_giveaway), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                } else {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_GIVEAWAY_SUCCESS_DIALOG, BaseProductFeedServiceFragment.this.getGiveawayImpressionInfo());
                    multiButtonDialogChoice = new MultiButtonDialogChoice(1, uiFragment.getString(R.string.continue_shopping), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(multiButtonDialogChoice);
                baseActivity.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(str3).setSubTitle(str4).setImageSize(ImageSize.SMALL).setButtons(arrayList).setWishImage(wishImage2).setCancelable(true).hideXButton().build(), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        switch (i) {
                            case 0:
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_NO_STOCK_DIALOG_SHOP, BaseProductFeedServiceFragment.this.getGiveawayImpressionInfo());
                                Intent intent = new Intent();
                                intent.setClass(baseActivity, BrowseActivity.class);
                                intent.putExtra("ExtraCategoryId", "daily_giveaway__tab");
                                baseActivity.startActivity(intent);
                                return;
                            case 1:
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_SUCCESS_DIALOG_SHOP, BaseProductFeedServiceFragment.this.getGiveawayImpressionInfo());
                                Intent intent2 = new Intent();
                                intent2.setClass(baseActivity, BrowseActivity.class);
                                baseActivity.startActivity(intent2);
                                return;
                            default:
                                return;
                        }
                    }
                });
            }
        };
        withUiFragment(r0, "FragmentTagMainContent");
    }

    /* access modifiers changed from: private */
    public HashMap<String, String> getGiveawayImpressionInfo() {
        HashMap<String, String> hashMap = new HashMap<>();
        if (ExperimentDataCenter.getInstance().shouldShowDailyRaffle()) {
            hashMap.put("GiveawayType", "DailyRaffle");
        } else {
            hashMap.put("GiveawayType", "DailyGiveaway");
        }
        return hashMap;
    }

    public void loadCurrentDailyGiveaway() {
        this.mGetCurrentDailyGiveawayService.requestService(new GetCurrentDailyGiveawayService.SuccessCallback() {
            public void onSuccess(final WishCurrentDailyGiveawayInfo wishCurrentDailyGiveawayInfo) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        productFeedFragment.handleCurrentDailyGiveawaySuccess(wishCurrentDailyGiveawayInfo);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        BaseProductFeedServiceFragment.this.handleDefaultFailure(0, str);
                        productFeedFragment.handleCurrentDailyGiveawayFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void showFreeGiftConfirmedDialog(final WishProduct wishProduct, final WishSignupFreeGiftCart wishSignupFreeGiftCart) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_FREE_GIFT_APPLIED_MODAL);
        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.IMPRESSION_FIRST_FREE_GIFT_SUCCESS);
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.startDialog(GiftConfirmedDialogFragment.createGiftConfirmedDialog(wishProduct, wishSignupFreeGiftCart.getModalTitle(), wishSignupFreeGiftCart.getModalMessage(), wishSignupFreeGiftCart.getModalButtonText()));
            }
        });
    }

    public void showOrderConfirmedDialog(final WishShippingInfo wishShippingInfo) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_FREE_GIFT_APPLIED_MODAL);
        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.IMPRESSION_FIRST_FREE_GIFT_SUCCESS);
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.startDialog(OrderConfirmedDialogFragment.getDialog(wishShippingInfo.getFormattedString()));
            }
        });
    }

    public void loadUpcomingDailyGiveaway() {
        this.mGetUpcomingDailyGiveawayService.requestService(new GetUpcomingDailyGiveawayService.SuccessCallback() {
            public void onSuccess(final WishUpcomingDailyGiveawayInfo wishUpcomingDailyGiveawayInfo) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        productFeedFragment.handleUpcomingDailyGiveawaySuccess(wishUpcomingDailyGiveawayInfo);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        BaseProductFeedServiceFragment.this.handleDefaultFailure(0, str);
                        productFeedFragment.handleUpcomingDailyGiveawayFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadInfoDailyGiveaway() {
        this.mGetInfoDailyGiveawayService.requestService(new GetDailyGiveawayInfoService.SuccessCallback() {
            public void onSuccess(final WishInfoDailyGiveawayInfo wishInfoDailyGiveawayInfo) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        productFeedFragment.handleInfoDailyGiveawaySuccess(wishInfoDailyGiveawayInfo);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        BaseProductFeedServiceFragment.this.handleDefaultFailure(0, str);
                        productFeedFragment.handleInfoDailyGiveawayFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadVisuallyRelatedProducts(final int i, final int i2, int i3, FeedContext feedContext) {
        this.mGetRelatedProductFeedService.requestService(feedContext.requestId, i2, (long) i3, "visual_recommendation", (BaseFeedApiService.SuccessCallback<Object>) new BaseFeedApiService.SuccessCallback<Object>() {
            public void onSuccess(final ArrayList<Object> arrayList, final boolean z, final int i, FeedExtraDataBundle feedExtraDataBundle) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsRelatedItemsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsRelatedItemsFragment productDetailsRelatedItemsFragment) {
                        ArrayList arrayList = new ArrayList();
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            Object next = it.next();
                            if (next instanceof WishProduct) {
                                arrayList.add((WishProduct) next);
                            }
                        }
                        productDetailsRelatedItemsFragment.handleLoadingSuccess(i, arrayList, i, z);
                    }
                }, "FragmentTagMainContent");
            }
        }, (DefaultFailureCallback) new DefaultFailureCallback() {
            public void onFailure(final String str) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsRelatedItemsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsRelatedItemsFragment productDetailsRelatedItemsFragment) {
                        BaseProductFeedServiceFragment.this.handleDefaultFailure(i2, str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadRelatedExpressShippingProducts(final int i, final int i2, int i3, FeedContext feedContext) {
        this.mGetRelatedProductFeedService.requestService(feedContext.requestId, i2, (long) i3, "express", (BaseFeedApiService.SuccessCallback<Object>) new BaseFeedApiService.SuccessCallback<Object>() {
            public void onSuccess(ArrayList<Object> arrayList, boolean z, int i, FeedExtraDataBundle feedExtraDataBundle) {
                final String str = feedExtraDataBundle.bannerText;
                BaseProductFeedServiceFragment baseProductFeedServiceFragment = BaseProductFeedServiceFragment.this;
                final ArrayList<Object> arrayList2 = arrayList;
                final int i2 = i;
                final boolean z2 = z;
                AnonymousClass1 r0 = new UiTask<BaseActivity, ProductDetailsRelatedItemsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsRelatedItemsFragment productDetailsRelatedItemsFragment) {
                        ArrayList arrayList = new ArrayList();
                        Iterator it = arrayList2.iterator();
                        while (it.hasNext()) {
                            Object next = it.next();
                            if (next instanceof WishProduct) {
                                arrayList.add((WishProduct) next);
                            }
                        }
                        productDetailsRelatedItemsFragment.handleLoadingSuccess(i, arrayList, i2, z2, str);
                    }
                };
                baseProductFeedServiceFragment.withUiFragment(r0, "FragmentTagMainContent");
            }
        }, (DefaultFailureCallback) new DefaultFailureCallback() {
            public void onFailure(final String str) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductDetailsRelatedItemsFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductDetailsRelatedItemsFragment productDetailsRelatedItemsFragment) {
                        BaseProductFeedServiceFragment.this.handleDefaultFailure(i, i2, str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void showGiftInCartDialog(final WishProduct wishProduct) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_FREE_GIFT_IN_CART_MODAL);
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.startDialog(GiftInCartDialogFragment.createGiftInCartDialogFragment(wishProduct));
            }
        });
    }

    public void showDailyLoginStampRowDialog(final WishDailyLoginStampSpec wishDailyLoginStampSpec) {
        withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.startDialog(DailyLoginBonusStampPopupDialog.createDailyLoginBonusStampPopupDialog(wishDailyLoginStampSpec, true));
            }
        });
    }

    public void showDailyLoginDialog(final WishDailyLoginStampSpec wishDailyLoginStampSpec, final DialogInterface dialogInterface) {
        if (wishDailyLoginStampSpec != null) {
            withActivity(new ActivityTask<BaseActivity>() {
                public void performTask(BaseActivity baseActivity) {
                    DailyLoginBonusDialogFragment createDailyLoginBonusDialogFragment = DailyLoginBonusDialogFragment.createDailyLoginBonusDialogFragment(wishDailyLoginStampSpec);
                    createDailyLoginBonusDialogFragment.setDismissListener(dialogInterface);
                    baseActivity.startDialog(createDailyLoginBonusDialogFragment);
                }
            });
        }
    }

    public void claimGiveaway(WishProduct wishProduct) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_FEED_CLAIM);
        claimProduct(wishProduct);
    }

    private void claimProduct(final WishProduct wishProduct) {
        withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
            public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                baseActivity.hideLoadingDialog();
                productFeedFragment.addProductToCart(wishProduct, Source.DAILY_GIVEAWAY);
            }
        });
    }

    public void enterDailyRaffle(String str, final WishImage wishImage, final LoadingPageView loadingPageView) {
        showLoadingSpinner();
        this.mEnterDailyRaffleService.requestService(str, new EnterDailyRaffleService.SuccessCallback() {
            public void onSuccess(final String str, final String str2, final int i) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, BaseProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseProductFeedFragment baseProductFeedFragment) {
                        BaseProductFeedServiceFragment.this.hideLoadingSpinner();
                        BaseProductFeedServiceFragment.this.showDailyRafflePopup(wishImage, str, str2, i);
                        loadingPageView.reload();
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        BaseProductFeedServiceFragment.this.handleDefaultFailure(0, str);
                        productFeedFragment.handleCurrentDailyGiveawayFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void showDailyRafflePopup(WishImage wishImage, String str, String str2, int i) {
        final WishImage wishImage2 = wishImage;
        final String str3 = str;
        final String str4 = str2;
        final int i2 = i;
        AnonymousClass67 r0 = new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                baseActivity.startDialog(EnterRaffleDialogFragment.createEnterRaffleDialogFragment(wishImage2, str3, str4, i2));
            }
        };
        withActivity(r0);
    }

    private MerchantsFeedTabService.SuccessCallback createMerchantsFeedTabSuccessCallback(final MerchantsFeedTabService.SuccessCallback successCallback) {
        return new MerchantsFeedTabService.SuccessCallback() {
            public void onSuccess(final boolean z, final List<MerchantFeedItem> list) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UiFragment>() {
                    public void performTask(BaseActivity baseActivity, UiFragment uiFragment) {
                        successCallback.onSuccess(z, list);
                    }
                }, "FragmentTagMainContent");
            }
        };
    }

    private DefaultFailureCallback createMerchantsFeedTabFailureCallback(final DefaultFailureCallback defaultFailureCallback) {
        return new DefaultFailureCallback() {
            public void onFailure(final String str) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UiFragment>() {
                    public void performTask(BaseActivity baseActivity, UiFragment uiFragment) {
                        if (defaultFailureCallback != null) {
                            defaultFailureCallback.onFailure(str);
                        }
                    }
                }, "FragmentTagMainContent");
            }
        };
    }

    public void fetchRecentlyViewedMerchants(int i, int i2, MerchantsFeedTabService.SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback) {
        this.mRecentlyViewedMerchantsService.requestService(i, i2, createMerchantsFeedTabSuccessCallback(successCallback), createMerchantsFeedTabFailureCallback(defaultFailureCallback));
    }

    public void fetchTopRatedMerchants(int i, int i2, MerchantsFeedTabService.SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback) {
        this.mTopRatedMerchantsService.requestService(i, i2, createMerchantsFeedTabSuccessCallback(successCallback), createMerchantsFeedTabFailureCallback(defaultFailureCallback));
    }

    public void fetchTrendingMerchants(int i, int i2, MerchantsFeedTabService.SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback) {
        this.mTrendingMerchantsService.requestService(i, i2, createMerchantsFeedTabSuccessCallback(successCallback), createMerchantsFeedTabFailureCallback(defaultFailureCallback));
    }

    public void loadRelatedProductBoostProducts(final int i, String str, final int i2, final int i3) {
        this.mGetRelatedProductBoostService.requestService(str, i2, (long) i3, new GetRelatedProductBoostProductsService.SuccessCallback() {
            public void onSuccess(ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec) {
                final ArrayList arrayList;
                if (productDetailsRelatedRowSpec.getProducts() == null) {
                    arrayList = new ArrayList();
                } else {
                    arrayList = new ArrayList(productDetailsRelatedRowSpec.getProducts());
                }
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        productFeedFragment.handleLoadingSuccess(i, arrayList, i2 + i3, arrayList.isEmpty());
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                BaseProductFeedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        productFeedFragment.handleLoadingErrored(i);
                    }
                });
            }
        });
    }

    public void loadProductVariations(WishProduct wishProduct, GetProductService.SuccessCallback successCallback, DefaultCodeFailureCallback defaultCodeFailureCallback) {
        this.mGetProductService.requestService(wishProduct.getProductId(), null, successCallback, defaultCodeFailureCallback);
    }
}
