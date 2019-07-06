package com.contextlogic.wish.activity.orderconfirmed;

import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedFragment.GroupBuyInfo;
import com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedFragment.ProductInfo;
import com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedFragment.WishlistInfo;
import com.contextlogic.wish.api.model.WishPriceWatchSpec;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetDealDashActiveService;
import com.contextlogic.wish.api.service.standalone.GetOrderConfirmedGroupBuysService;
import com.contextlogic.wish.api.service.standalone.GetOrderConfirmedGroupBuysService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetOrderConfirmedProductService;
import com.contextlogic.wish.api.service.standalone.GetPriceWatchService;
import com.contextlogic.wish.api.service.standalone.GetProductService;
import com.contextlogic.wish.api.service.standalone.GetProductService.FeedExtraInfo;
import com.contextlogic.wish.api.service.standalone.GetWishlistHourlyDealService;
import java.util.ArrayList;

public class OrderConfirmedServiceFragment extends ServiceFragment<OrderConfirmedActivity> {
    private GetDealDashActiveService mGetDealDashActiveService;
    private GetOrderConfirmedGroupBuysService mGetGroupBuys;
    private GetOrderConfirmedProductService mGetOrderConfirmedProductService;
    private GetPriceWatchService mGetPriceWatchService;
    private GetProductService mGetProductService;
    private GetWishlistHourlyDealService mGetWishlistHourlyDealService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetOrderConfirmedProductService = new GetOrderConfirmedProductService();
        this.mGetDealDashActiveService = new GetDealDashActiveService();
        this.mGetWishlistHourlyDealService = new GetWishlistHourlyDealService();
        this.mGetGroupBuys = new GetOrderConfirmedGroupBuysService();
        this.mGetPriceWatchService = new GetPriceWatchService();
        this.mGetProductService = new GetProductService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetOrderConfirmedProductService.cancelAllRequests();
        this.mGetDealDashActiveService.cancelAllRequests();
        this.mGetGroupBuys.cancelAllRequests();
        this.mGetPriceWatchService.cancelAllRequests();
        this.mGetProductService.cancelAllRequests();
    }

    public void loadGroupBuys(String str) {
        this.mGetGroupBuys.requestService(str, new SuccessCallback() {
            public void onSuccess(final ArrayList<GroupBuyInfo> arrayList) {
                OrderConfirmedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, OrderConfirmedFragment>() {
                    public void performTask(BaseActivity baseActivity, OrderConfirmedFragment orderConfirmedFragment) {
                        orderConfirmedFragment.handleGroupBuysInfo(arrayList);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                OrderConfirmedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, OrderConfirmedFragment>() {
                    public void performTask(BaseActivity baseActivity, OrderConfirmedFragment orderConfirmedFragment) {
                        orderConfirmedFragment.handleGroupBuyInfoFailed(str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadAllProductInfo(String str) {
        this.mGetOrderConfirmedProductService.requestService(str, new GetOrderConfirmedProductService.SuccessCallback() {
            public void onSuccess(ArrayList<ProductInfo> arrayList, WishShippingInfo wishShippingInfo, String str, String str2, String str3, String str4) {
                OrderConfirmedServiceFragment orderConfirmedServiceFragment = OrderConfirmedServiceFragment.this;
                final ArrayList<ProductInfo> arrayList2 = arrayList;
                final WishShippingInfo wishShippingInfo2 = wishShippingInfo;
                final String str5 = str;
                final String str6 = str2;
                final String str7 = str3;
                final String str8 = str4;
                AnonymousClass1 r0 = new UiTask<BaseActivity, OrderConfirmedFragment>() {
                    public void performTask(BaseActivity baseActivity, OrderConfirmedFragment orderConfirmedFragment) {
                        orderConfirmedFragment.handleAllProductInfo(arrayList2, wishShippingInfo2, str5, str6, str7, str8);
                    }
                };
                orderConfirmedServiceFragment.withUiFragment(r0, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                OrderConfirmedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, OrderConfirmedFragment>() {
                    public void performTask(BaseActivity baseActivity, OrderConfirmedFragment orderConfirmedFragment) {
                        orderConfirmedFragment.handleAllProductInfoFailed(str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadDealDashStatus() {
        this.mGetDealDashActiveService.requestService(new GetDealDashActiveService.SuccessCallback() {
            public void onSuccess(final boolean z) {
                OrderConfirmedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, OrderConfirmedFragment>() {
                    public void performTask(BaseActivity baseActivity, OrderConfirmedFragment orderConfirmedFragment) {
                        orderConfirmedFragment.handleDealDashStatus(z);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                OrderConfirmedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, OrderConfirmedFragment>() {
                    public void performTask(BaseActivity baseActivity, OrderConfirmedFragment orderConfirmedFragment) {
                        orderConfirmedFragment.handleDealDashStatusFailed(str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadWishlistHourlyDeals() {
        this.mGetWishlistHourlyDealService.requestService(new GetWishlistHourlyDealService.SuccessCallback() {
            public void onSuccess(final WishlistInfo wishlistInfo) {
                OrderConfirmedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, OrderConfirmedFragment>() {
                    public void performTask(BaseActivity baseActivity, OrderConfirmedFragment orderConfirmedFragment) {
                        orderConfirmedFragment.handleWishlistHourlyDeals(wishlistInfo);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                OrderConfirmedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, OrderConfirmedFragment>() {
                    public void performTask(BaseActivity baseActivity, OrderConfirmedFragment orderConfirmedFragment) {
                        orderConfirmedFragment.handleWishlistHourlyDealsFailed(str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadPriceWatchInfo() {
        this.mGetPriceWatchService.requestService(true, new GetPriceWatchService.SuccessCallback() {
            public void onSuccess(final WishPriceWatchSpec wishPriceWatchSpec) {
                OrderConfirmedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, OrderConfirmedFragment>() {
                    public void performTask(BaseActivity baseActivity, OrderConfirmedFragment orderConfirmedFragment) {
                        orderConfirmedFragment.handlePriceWatchInfoLoaded(wishPriceWatchSpec);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                OrderConfirmedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, OrderConfirmedFragment>() {
                    public void performTask(BaseActivity baseActivity, OrderConfirmedFragment orderConfirmedFragment) {
                        orderConfirmedFragment.handlePriceWatchInfoFailed(str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void showInviteCouponDialog() {
        withActivity(new ActivityTask<OrderConfirmedActivity>() {
            public void performTask(OrderConfirmedActivity orderConfirmedActivity) {
                orderConfirmedActivity.startDialog(new InviteCouponDialogFragment());
            }
        });
    }

    public void getProduct(String str) {
        this.mGetProductService.requestService(str, null, new GetProductService.SuccessCallback() {
            public void onSuccess(final WishProduct wishProduct, FeedExtraInfo feedExtraInfo) {
                OrderConfirmedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, OrderConfirmedFragment>() {
                    public void performTask(BaseActivity baseActivity, OrderConfirmedFragment orderConfirmedFragment) {
                        if (wishProduct.getScreenshotShareInfo() != null) {
                            baseActivity.setScreenshotShareInfo(wishProduct.getScreenshotShareInfo());
                        }
                        orderConfirmedFragment.handleProductLoadingSuccess(wishProduct);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultCodeFailureCallback() {
            public void onFailure(String str, int i) {
                OrderConfirmedServiceFragment.this.withUiFragment(new UiTask<BaseActivity, OrderConfirmedFragment>() {
                    public void performTask(BaseActivity baseActivity, OrderConfirmedFragment orderConfirmedFragment) {
                        orderConfirmedFragment.handleLoadingFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }
}
