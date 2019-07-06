package com.contextlogic.wish.activity.settings.accountsettings.countrysettings;

import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;

public class CountrySettingsFragment extends UiFragment<CountrySettingsActivity> {
    private CountrySettingsAdapter mAdapter;
    private ListView mListView;
    private String mOldUserCountryCode;
    private String mUserCountryCode;

    public int getLayoutResourceId() {
        return R.layout.country_settings_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public void initialize() {
        this.mListView = (ListView) findViewById(R.id.country_settings_fragment_listview);
        this.mUserCountryCode = ProfileDataCenter.getInstance().getCountryCode();
        this.mAdapter = new CountrySettingsAdapter((CountrySettingsActivity) getBaseActivity(), this);
        this.mListView.setAdapter(this.mAdapter);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_COUNTRY_CHANGE);
    }

    public void handleSettingSuccess() {
        ProfileDataCenter.getInstance().refresh();
    }

    public void handleSettingFailure() {
        this.mUserCountryCode = this.mOldUserCountryCode;
        refreshAdapter();
    }

    public String getCountryCode() {
        return this.mUserCountryCode;
    }

    public void refreshAdapter() {
        this.mAdapter.notifyDataSetChanged();
    }

    public void changeCountry(final String str) {
        this.mOldUserCountryCode = this.mUserCountryCode;
        this.mUserCountryCode = str;
        withServiceFragment(new ServiceTask<BaseActivity, CountrySettingsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CountrySettingsServiceFragment countrySettingsServiceFragment) {
                countrySettingsServiceFragment.changeCountry(str);
            }
        });
        refreshAdapter();
    }
}
