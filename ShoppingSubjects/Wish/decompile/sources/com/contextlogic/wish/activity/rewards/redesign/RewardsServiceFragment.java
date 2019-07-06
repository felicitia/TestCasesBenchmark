package com.contextlogic.wish.activity.rewards.redesign;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.rewards.RewardsActivity;
import com.contextlogic.wish.activity.rewards.redesign.RewardsDashboardView.RewardState;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishRedeemableRewardItem;
import com.contextlogic.wish.api.model.WishRewardsDashboardInfo;
import com.contextlogic.wish.api.model.WishRewardsHelpInfo;
import com.contextlogic.wish.api.model.WishRewardsRedeemableInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetRedeemableRewardsDashboardInfo;
import com.contextlogic.wish.api.service.standalone.GetRedeemableRewardsHelpInfoService;
import com.contextlogic.wish.api.service.standalone.GetRedeemableRewardsInfoService;
import com.contextlogic.wish.api.service.standalone.GetRedeemableRewardsInfoService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.RedeemRewardWithPointsService;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.ImageSize;
import java.util.ArrayList;

public class RewardsServiceFragment extends ServiceFragment<RewardsActivity> {
    private GetRedeemableRewardsDashboardInfo mGetRedeemableRewardsDashboardInfo;
    private GetRedeemableRewardsHelpInfoService mGetRedeemableRewardsHelpInfoService;
    private GetRedeemableRewardsInfoService mGetRedeemableRewardsInfoService;
    /* access modifiers changed from: private */
    public RedeemRewardWithPointsService mRedeemRewardService;
    private RewardProductRedemptionServiceDelegate mRewardProductRedemptionDelegate;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetRedeemableRewardsInfoService = new GetRedeemableRewardsInfoService();
        this.mGetRedeemableRewardsDashboardInfo = new GetRedeemableRewardsDashboardInfo();
        this.mGetRedeemableRewardsHelpInfoService = new GetRedeemableRewardsHelpInfoService();
        this.mRedeemRewardService = new RedeemRewardWithPointsService();
        this.mRewardProductRedemptionDelegate = new RewardProductRedemptionServiceDelegate(this);
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetRedeemableRewardsInfoService.cancelAllRequests();
        this.mGetRedeemableRewardsDashboardInfo.cancelAllRequests();
        this.mGetRedeemableRewardsHelpInfoService.cancelAllRequests();
        this.mRedeemRewardService.cancelAllRequests();
        this.mRewardProductRedemptionDelegate.cancelAllRequests();
    }

    public void cancelLoadingRedeemableRewardsInfo() {
        this.mGetRedeemableRewardsInfoService.cancelAllRequests();
    }

    public void cancelLoadingDashboardInfo() {
        this.mGetRedeemableRewardsDashboardInfo.cancelAllRequests();
    }

    public void cancelLoadingHelpInfo() {
        this.mGetRedeemableRewardsHelpInfoService.cancelAllRequests();
    }

    public void showRedeemCouponDialog(WishRedeemableRewardItem wishRedeemableRewardItem) {
        final RedeemRewardDialogFragment createRedeemRewardDialog = RedeemRewardDialogFragment.createRedeemRewardDialog(wishRedeemableRewardItem);
        withActivity(new ActivityTask<RewardsActivity>() {
            public void performTask(RewardsActivity rewardsActivity) {
                rewardsActivity.startDialog(createRedeemRewardDialog);
            }
        });
    }

    public void getRedeemableRewardsInfo() {
        this.mGetRedeemableRewardsInfoService.requestService(new SuccessCallback() {
            public void onSuccess(final WishRewardsRedeemableInfo wishRewardsRedeemableInfo) {
                RewardsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, RewardsFragment>() {
                    public void performTask(BaseActivity baseActivity, RewardsFragment rewardsFragment) {
                        rewardsFragment.handleLoadingRedeemableRewardsSuccess(wishRewardsRedeemableInfo);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                RewardsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, RewardsFragment>() {
                    public void performTask(BaseActivity baseActivity, RewardsFragment rewardsFragment) {
                        rewardsFragment.handleLoadingRedeemableRewardsFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void getRedeemableRewardsDashboardInfo(int i, RewardState rewardState, boolean z) {
        this.mGetRedeemableRewardsDashboardInfo.requestService(i, 20, rewardState.getValue(), z, new GetRedeemableRewardsDashboardInfo.SuccessCallback() {
            public void onSuccess(WishRewardsDashboardInfo wishRewardsDashboardInfo, ArrayList<WishRedeemableRewardItem> arrayList, boolean z, int i, String str) {
                RewardsServiceFragment rewardsServiceFragment = RewardsServiceFragment.this;
                final WishRewardsDashboardInfo wishRewardsDashboardInfo2 = wishRewardsDashboardInfo;
                final ArrayList<WishRedeemableRewardItem> arrayList2 = arrayList;
                final boolean z2 = z;
                final int i2 = i;
                final String str2 = str;
                AnonymousClass1 r1 = new UiTask<BaseActivity, RewardsFragment>() {
                    public void performTask(BaseActivity baseActivity, RewardsFragment rewardsFragment) {
                        rewardsFragment.handleLoadingDashboardInfoSuccess(wishRewardsDashboardInfo2, arrayList2, z2, i2, str2);
                    }
                };
                rewardsServiceFragment.withUiFragment(r1, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                RewardsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, RewardsFragment>() {
                    public void performTask(BaseActivity baseActivity, RewardsFragment rewardsFragment) {
                        rewardsFragment.handleLoadingDashboardInfoFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void getRedeemableRewardsHelpInfoService() {
        this.mGetRedeemableRewardsHelpInfoService.requestService(new GetRedeemableRewardsHelpInfoService.SuccessCallback() {
            public void onSuccess(final WishRewardsHelpInfo wishRewardsHelpInfo) {
                RewardsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, RewardsFragment>() {
                    public void performTask(BaseActivity baseActivity, RewardsFragment rewardsFragment) {
                        rewardsFragment.handleLoadingHelpInfoSuccess(wishRewardsHelpInfo);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                RewardsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, RewardsFragment>() {
                    public void performTask(BaseActivity baseActivity, RewardsFragment rewardsFragment) {
                        rewardsFragment.handleLoadingHelpInfoFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void redeemReward(final int i) {
        showLoadingSpinner();
        withActivity(new ActivityTask<RewardsActivity>() {
            public void performTask(final RewardsActivity rewardsActivity) {
                RewardsServiceFragment.this.mRedeemRewardService.requestService(i, new RedeemRewardWithPointsService.SuccessCallback() {
                    public void onSuccess(final WishRewardsRedeemableInfo wishRewardsRedeemableInfo) {
                        RewardsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, RewardsFragment>() {
                            public void performTask(BaseActivity baseActivity, RewardsFragment rewardsFragment) {
                                rewardsFragment.handleLoadingRedeemableRewardsSuccess(wishRewardsRedeemableInfo);
                                RewardsServiceFragment.this.showRewardRedeemedDialog(wishRewardsRedeemableInfo);
                                rewardsFragment.reloadDashboardView();
                            }
                        }, "FragmentTagMainContent");
                        RewardsServiceFragment.this.hideLoadingSpinner();
                    }
                }, new DefaultFailureCallback() {
                    public void onFailure(String str) {
                        RewardsServiceFragment.this.hideLoadingSpinner();
                        rewardsActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                });
            }
        });
    }

    public void showAutomaticApplyDialog() {
        withUiFragment(new UiTask<BaseActivity, RewardsFragment>() {
            public void performTask(BaseActivity baseActivity, RewardsFragment rewardsFragment) {
                ArrayList arrayList = new ArrayList();
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, RewardsServiceFragment.this.getResources().getString(R.string.okay), R.color.white, R.color.main_primary, BackgroundType.COLOR, ChoiceType.DEFAULT);
                arrayList.add(multiButtonDialogChoice);
                baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonDialog(RewardsServiceFragment.this.getString(R.string.reward_automatically_applied_dialog_title), RewardsServiceFragment.this.getString(R.string.reward_automatically_applied_dialog_description), 0, 0, true, true, arrayList, ImageSize.SMALL, null), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        baseDialogFragment.dismiss();
                    }
                });
            }
        }, "FragmentTagMainContent");
    }

    /* access modifiers changed from: private */
    public void showRewardRedeemedDialog(WishRewardsRedeemableInfo wishRewardsRedeemableInfo) {
        final RedeemableRewardsConfirmationDialogFragment createRedeemableRewardsConfirmationDialogFragment = RedeemableRewardsConfirmationDialogFragment.createRedeemableRewardsConfirmationDialogFragment(wishRewardsRedeemableInfo.getRedeemTitle(), wishRewardsRedeemableInfo.getmRedeemedDescription());
        withActivity(new ActivityTask<RewardsActivity>() {
            public void performTask(RewardsActivity rewardsActivity) {
                rewardsActivity.startDialog(createRedeemableRewardsConfirmationDialogFragment, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        RewardsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, RewardsFragment>() {
                            public void performTask(BaseActivity baseActivity, RewardsFragment rewardsFragment) {
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_REDEEMABLE_REWARD_CONFIRMATION_DIALOG);
                                rewardsFragment.switchToPosition(0, true);
                            }
                        }, "FragmentTagMainContent");
                    }
                });
            }
        });
    }

    public boolean isLoadingRewards() {
        return this.mGetRedeemableRewardsDashboardInfo.isPending();
    }

    public void redeemRewardProduct(WishProduct wishProduct, DefaultSuccessCallback defaultSuccessCallback) {
        this.mRewardProductRedemptionDelegate.redeemRewardProduct(wishProduct, defaultSuccessCallback);
    }
}
