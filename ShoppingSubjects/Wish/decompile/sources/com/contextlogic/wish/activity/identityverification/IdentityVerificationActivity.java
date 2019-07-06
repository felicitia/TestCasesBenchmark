package com.contextlogic.wish.activity.identityverification;

import android.content.Intent;
import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.OnfidoCreateCheckService;
import com.contextlogic.wish.api.service.standalone.OnfidoUpdateUserStateService;
import com.contextlogic.wish.api.service.standalone.OnfidoUpdateUserStateService.UserState;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.http.ServerConfig;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.ExitCode;
import com.onfido.android.sdk.capture.Onfido;
import com.onfido.android.sdk.capture.Onfido.OnfidoResultListener;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.OnfidoFactory;
import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.android.sdk.capture.ui.options.CaptureScreenStep;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.android.sdk.capture.upload.Captures;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.Applicant;
import java.util.ArrayList;
import java.util.HashMap;

public class IdentityVerificationActivity extends FullScreenActivity {
    protected static final int ONFIDO_REQUEST_CODE = (Math.abs(IdentityVerificationActivity.class.getName().hashCode()) % 65535);
    /* access modifiers changed from: private */
    public OnfidoCreateCheckService mCreateCheckService;
    private Onfido mOnfido;
    /* access modifiers changed from: private */
    public OnfidoUpdateUserStateService mUpdateUserStateService;

    /* access modifiers changed from: protected */
    public boolean isHeadlessActivity() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean requiresAuthentication() {
        return false;
    }

    public void handleOnCreate(Bundle bundle) {
        FlowStep flowStep;
        super.handleOnCreate(bundle);
        this.mCreateCheckService = new OnfidoCreateCheckService();
        this.mUpdateUserStateService = new OnfidoUpdateUserStateService();
        Bundle extras = getIntent().getExtras();
        String string = (extras == null || extras.isEmpty()) ? null : extras.getString("ExtraApplicantId");
        this.mOnfido = OnfidoFactory.create(WishApplication.getInstance()).getClient();
        int i = ServerConfig.getInstance().isTestHost() ? R.string.onfido_sdk_token_sandbox : R.string.onfido_sdk_token;
        if ("MX".equalsIgnoreCase(ProfileDataCenter.getInstance().getCountryCode())) {
            flowStep = new CaptureScreenStep(DocumentType.NATIONAL_IDENTITY_CARD, CountryCode.MX);
        } else if ("BR".equalsIgnoreCase(ProfileDataCenter.getInstance().getCountryCode())) {
            flowStep = new CaptureScreenStep(DocumentType.DRIVING_LICENCE, CountryCode.BR);
        } else {
            flowStep = FlowStep.CAPTURE_DOCUMENT;
        }
        OnfidoConfig build = OnfidoConfig.builder().withToken(getString(i)).withApplicant(string).withCustomFlow(new FlowStep[]{FlowStep.WELCOME, flowStep}).build();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ONFIDO_SDK_ENTER_FLOW);
        showLoadingDialog();
        this.mOnfido.startActivityForResult(this, ONFIDO_REQUEST_CODE, build);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mOnfido != null && i == ONFIDO_REQUEST_CODE) {
            this.mOnfido.handleActivityResult(i2, intent, new OnfidoResultListener() {
                public void userCompleted(Applicant applicant, Captures captures) {
                    final String id = applicant.getId();
                    IdentityVerificationActivity.this.mCreateCheckService.requestService(id, new DefaultSuccessCallback() {
                        public void onSuccess() {
                            HashMap hashMap = new HashMap();
                            hashMap.put("applicant_id", id);
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ONFIDO_SDK_SUBMIT_CHECK_SUCCESS, hashMap);
                            IdentityVerificationActivity.this.hideLoadingDialog();
                            IdentityVerificationActivity.this.showSuccessDialog();
                        }
                    }, new DefaultFailureCallback() {
                        public void onFailure(String str) {
                            HashMap hashMap = new HashMap();
                            hashMap.put("applicant_id", id);
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ONFIDO_SDK_SUBMIT_CHECK_FAILURE, hashMap);
                            IdentityVerificationActivity.this.mUpdateUserStateService.requestService(UserState.ERROR, null, null);
                            IdentityVerificationActivity.this.hideLoadingDialog();
                            IdentityVerificationActivity.this.showErrorDialog();
                        }
                    });
                }

                public void userExited(ExitCode exitCode, Applicant applicant) {
                    HashMap hashMap = new HashMap();
                    if (!(applicant == null || applicant.getId() == null)) {
                        hashMap.put("applicant_id", applicant.getId());
                    }
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ONFIDO_SDK_USER_EXITED, hashMap);
                    IdentityVerificationActivity.this.mUpdateUserStateService.requestService(UserState.ABANDONED, null, null);
                    IdentityVerificationActivity.this.hideLoadingDialog();
                    IdentityVerificationActivity.this.startHomeActivity();
                }

                public void onError(OnfidoException onfidoException, Applicant applicant) {
                    HashMap hashMap = new HashMap();
                    if (!(applicant == null || applicant.getId() == null)) {
                        hashMap.put("applicant_id", applicant.getId());
                    }
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ONFIDO_SDK_ERROR, hashMap);
                    IdentityVerificationActivity.this.mUpdateUserStateService.requestService(UserState.ERROR, null, null);
                    IdentityVerificationActivity.this.hideLoadingDialog();
                    IdentityVerificationActivity.this.showErrorDialog();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void showSuccessDialog() {
        MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, getString(R.string.onfido_resume_browsing), R.color.white, R.color.main_primary, BackgroundType.COLOR, ChoiceType.DEFAULT);
        ArrayList arrayList = new ArrayList();
        arrayList.add(multiButtonDialogChoice);
        startDialog(new MultiButtonDialogFragmentBuilder().setTitle(getString(R.string.almost_finished_title)).setSubTitle(getString(R.string.onfido_success_message)).setButtons(arrayList).setCancelable(false).build(), new BaseDialogCallback() {
            public void onCancel(BaseDialogFragment baseDialogFragment) {
                IdentityVerificationActivity.this.startHomeActivity();
            }

            public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                IdentityVerificationActivity.this.startHomeActivity();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showErrorDialog() {
        startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(getString(R.string.general_error)), new BaseDialogCallback() {
            public void onCancel(BaseDialogFragment baseDialogFragment) {
                IdentityVerificationActivity.this.startHomeActivity();
            }

            public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                IdentityVerificationActivity.this.startHomeActivity();
            }
        });
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new IdentityVerificationFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.identity_verification);
    }
}
