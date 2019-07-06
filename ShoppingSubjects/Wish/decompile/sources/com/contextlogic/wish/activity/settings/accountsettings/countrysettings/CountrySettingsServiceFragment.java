package com.contextlogic.wish.activity.settings.accountsettings.countrysettings;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.ChangeCountryService;
import com.contextlogic.wish.api.service.standalone.ChangeCountryService.SuccessCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;

public class CountrySettingsServiceFragment extends ServiceFragment<CountrySettingsActivity> {
    private ChangeCountryService mChangeCountryService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mChangeCountryService = new ChangeCountryService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mChangeCountryService.cancelAllRequests();
    }

    /* access modifiers changed from: private */
    public void handleDefaultFailure(final String str) {
        withUiFragment(new UiTask<BaseActivity, CountrySettingsFragment>() {
            public void performTask(BaseActivity baseActivity, CountrySettingsFragment countrySettingsFragment) {
                baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                countrySettingsFragment.handleSettingFailure();
            }
        }, "FragmentTagMainContent");
    }

    public void changeCountry(String str) {
        this.mChangeCountryService.requestService(str, new SuccessCallback() {
            public void onSuccess() {
                CountrySettingsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CountrySettingsFragment>() {
                    public void performTask(BaseActivity baseActivity, CountrySettingsFragment countrySettingsFragment) {
                        countrySettingsFragment.handleSettingSuccess();
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                if (str == null) {
                    str = WishApplication.getInstance().getResources().getString(R.string.error_updating_your_country);
                }
                CountrySettingsServiceFragment.this.handleDefaultFailure(str);
            }
        });
    }
}
