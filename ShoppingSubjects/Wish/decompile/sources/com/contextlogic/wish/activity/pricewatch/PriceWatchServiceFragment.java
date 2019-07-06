package com.contextlogic.wish.activity.pricewatch;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishPriceWatchChangeInfo;
import com.contextlogic.wish.api.model.WishPriceWatchSpec;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetPriceWatchService;
import com.contextlogic.wish.api.service.standalone.GetPriceWatchService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetProductService;
import com.contextlogic.wish.api.service.standalone.GetProductService.FeedExtraInfo;
import com.contextlogic.wish.api.service.standalone.RemoveFromPriceWatchService;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;

public class PriceWatchServiceFragment extends ServiceFragment<PriceWatchActivity> {
    private GetPriceWatchService mGetPriceWatchService;
    private GetProductService mGetProductService;
    /* access modifiers changed from: private */
    public RemoveFromPriceWatchService mRemoveFromPriceWatchService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetPriceWatchService = new GetPriceWatchService();
        this.mRemoveFromPriceWatchService = new RemoveFromPriceWatchService();
        this.mGetProductService = new GetProductService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        this.mGetPriceWatchService.cancelAllRequests();
        this.mRemoveFromPriceWatchService.cancelAllRequests();
        this.mGetProductService.cancelAllRequests();
    }

    public void loadInfo() {
        this.mGetPriceWatchService.requestService(new SuccessCallback() {
            public void onSuccess(final WishPriceWatchSpec wishPriceWatchSpec) {
                PriceWatchServiceFragment.this.withUiFragment(new UiTask<BaseActivity, PriceWatchFragment>() {
                    public void performTask(BaseActivity baseActivity, PriceWatchFragment priceWatchFragment) {
                        priceWatchFragment.handleInfoLoaded(wishPriceWatchSpec);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                PriceWatchServiceFragment.this.withUiFragment(new UiTask<BaseActivity, PriceWatchFragment>() {
                    public void performTask(BaseActivity baseActivity, PriceWatchFragment priceWatchFragment) {
                        String string = str == null ? PriceWatchServiceFragment.this.getString(R.string.general_error) : str;
                        priceWatchFragment.getLoadingPageView().markLoadingErrored();
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(string));
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void removeItemFromPriceWatch(final String str) {
        withActivity(new ActivityTask<PriceWatchActivity>() {
            public void performTask(PriceWatchActivity priceWatchActivity) {
                priceWatchActivity.startDialog(MultiButtonDialogFragment.createMultiButtonYesNoDialog(priceWatchActivity.getString(R.string.price_watch_are_you_sure_remove), null), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            PriceWatchServiceFragment.this.mRemoveFromPriceWatchService.requestService(str, new RemoveFromPriceWatchService.SuccessCallback() {
                                public void onSuccess(WishPriceWatchChangeInfo wishPriceWatchChangeInfo) {
                                    PriceWatchServiceFragment.this.withUiFragment(new UiTask<BaseActivity, PriceWatchFragment>() {
                                        public void performTask(BaseActivity baseActivity, PriceWatchFragment priceWatchFragment) {
                                            if (priceWatchFragment != null) {
                                                priceWatchFragment.reload();
                                            }
                                        }
                                    }, "FragmentTagMainContent");
                                }
                            }, new DefaultFailureCallback() {
                                public void onFailure(String str) {
                                    final String string = WishApplication.getInstance().getString(R.string.price_watch_failed_to_remove);
                                    PriceWatchServiceFragment.this.withActivity(new ActivityTask<PriceWatchActivity>() {
                                        public void performTask(PriceWatchActivity priceWatchActivity) {
                                            priceWatchActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(string));
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    public void getProduct(String str) {
        this.mGetProductService.requestService(str, null, new GetProductService.SuccessCallback() {
            public void onSuccess(final WishProduct wishProduct, FeedExtraInfo feedExtraInfo) {
                PriceWatchServiceFragment.this.withUiFragment(new UiTask<BaseActivity, PriceWatchFragment>() {
                    public void performTask(BaseActivity baseActivity, PriceWatchFragment priceWatchFragment) {
                        priceWatchFragment.handleProductLoadingSuccess(wishProduct);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultCodeFailureCallback() {
            public void onFailure(String str, int i) {
                PriceWatchServiceFragment.this.withUiFragment(new UiTask<BaseActivity, PriceWatchFragment>() {
                    public void performTask(BaseActivity baseActivity, PriceWatchFragment priceWatchFragment) {
                        priceWatchFragment.handleLoadingFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }
}
