package com.contextlogic.wish.activity.settings.accountsettings;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.settings.accountsettings.countrysettings.CountrySettingsActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishSettingItem;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLink.TargetType;
import com.contextlogic.wish.link.DeepLinkManager;

public class AccountSettingsFragment extends UiFragment<AccountSettingsActivity> {
    /* access modifiers changed from: private */
    public AccountSettingsAdapter mAdapter;
    private ListView mListView;

    /* renamed from: com.contextlogic.wish.activity.settings.accountsettings.AccountSettingsFragment$3 reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType = new int[TargetType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.contextlogic.wish.link.DeepLink$TargetType[] r0 = com.contextlogic.wish.link.DeepLink.TargetType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType = r0
                int[] r0 = $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.contextlogic.wish.link.DeepLink$TargetType r1 = com.contextlogic.wish.link.DeepLink.TargetType.UPDATE_PROFILE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.contextlogic.wish.link.DeepLink$TargetType r1 = com.contextlogic.wish.link.DeepLink.TargetType.CHANGE_EMAIL     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.contextlogic.wish.link.DeepLink$TargetType r1 = com.contextlogic.wish.link.DeepLink.TargetType.CHANGE_PASSWORD     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.contextlogic.wish.link.DeepLink$TargetType r1 = com.contextlogic.wish.link.DeepLink.TargetType.CHANGE_PHONE_NUMBER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.settings.accountsettings.AccountSettingsFragment.AnonymousClass3.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.account_settings_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public void startCountryActivityForCountry(AccountSettingsActivity accountSettingsActivity) {
        Intent intent = new Intent();
        intent.setClass(accountSettingsActivity, CountrySettingsActivity.class);
        accountSettingsActivity.startActivityForResult(intent, accountSettingsActivity.addResultCodeCallback(new ActivityResultCallback() {
            public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                AccountSettingsFragment.this.refreshAdapter();
            }
        }));
    }

    public void refreshAdapter() {
        this.mAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mListView = (ListView) findViewById(R.id.account_settings_fragment_listview);
        this.mAdapter = new AccountSettingsAdapter((AccountSettingsActivity) getBaseActivity(), this);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                final Object item = AccountSettingsFragment.this.mAdapter.getItem(i);
                if (item == null) {
                    return;
                }
                if (AccountSettingsFragment.this.mAdapter.isDeleteAccountItem(item)) {
                    AccountSettingsFragment.this.withServiceFragment(new ServiceTask<BaseActivity, AccountSettingsServiceFragment>() {
                        public void performTask(BaseActivity baseActivity, AccountSettingsServiceFragment accountSettingsServiceFragment) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_DELETE_ACCOUNT);
                            accountSettingsServiceFragment.handleDeleteAccount();
                        }
                    });
                } else if (AccountSettingsFragment.this.mAdapter.isCountryItem(item)) {
                    AccountSettingsFragment.this.withActivity(new ActivityTask<AccountSettingsActivity>() {
                        public void performTask(AccountSettingsActivity accountSettingsActivity) {
                            AccountSettingsFragment.this.startCountryActivityForCountry(accountSettingsActivity);
                        }
                    });
                } else if (AccountSettingsFragment.this.mAdapter.isSettingItem(item)) {
                    AccountSettingsFragment.this.withActivity(new ActivityTask<AccountSettingsActivity>() {
                        public void performTask(AccountSettingsActivity accountSettingsActivity) {
                            switch (AnonymousClass3.$SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType[new DeepLink(((WishSettingItem) item).action()).getTargetType().ordinal()]) {
                                case 1:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_UPDATE_PROFILE);
                                    break;
                                case 2:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_CHANGE_EMAIL);
                                    break;
                                case 3:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_CHANGE_PASSWORD);
                                    break;
                                case 4:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SETTINGS_CHANGE_PHONE_NUMBER);
                                    break;
                            }
                            DeepLinkManager.processDeepLink(accountSettingsActivity, new DeepLink(((WishSettingItem) item).action()));
                        }
                    });
                }
            }
        });
    }
}
