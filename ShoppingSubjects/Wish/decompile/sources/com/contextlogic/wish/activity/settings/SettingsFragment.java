package com.contextlogic.wish.activity.settings;

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
import com.contextlogic.wish.activity.developer.DeveloperSettingsActivity;
import com.contextlogic.wish.activity.settings.accountsettings.AccountSettingsActivity;
import com.contextlogic.wish.activity.settings.changecurrency.ChangeCurrencyActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishSettingItem;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLink.TargetType;
import com.contextlogic.wish.link.DeepLinkManager;

public class SettingsFragment extends UiFragment<SettingsActivity> {
    /* access modifiers changed from: private */
    public SettingsAdapter mAdapter;
    private ListView mListView;

    /* renamed from: com.contextlogic.wish.activity.settings.SettingsFragment$4 reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType = new int[TargetType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.contextlogic.wish.link.DeepLink$TargetType[] r0 = com.contextlogic.wish.link.DeepLink.TargetType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType = r0
                int[] r0 = $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.contextlogic.wish.link.DeepLink$TargetType r1 = com.contextlogic.wish.link.DeepLink.TargetType.MANAGE_ADDRESSES     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.contextlogic.wish.link.DeepLink$TargetType r1 = com.contextlogic.wish.link.DeepLink.TargetType.MANAGE_PAYMENTS     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.contextlogic.wish.link.DeepLink$TargetType r1 = com.contextlogic.wish.link.DeepLink.TargetType.RATE_APP     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.contextlogic.wish.link.DeepLink$TargetType r1 = com.contextlogic.wish.link.DeepLink.TargetType.NOTIFICATION_SETTINGS     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.contextlogic.wish.link.DeepLink$TargetType r1 = com.contextlogic.wish.link.DeepLink.TargetType.EMAIL_NOTIFICATION_SETTINGS     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType     // Catch:{ NoSuchFieldError -> 0x004b }
                com.contextlogic.wish.link.DeepLink$TargetType r1 = com.contextlogic.wish.link.DeepLink.TargetType.ACCOUNT_SETTINGS     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.contextlogic.wish.link.DeepLink$TargetType r1 = com.contextlogic.wish.link.DeepLink.TargetType.PUSH_NOTIFICATION_SETTINGS     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.contextlogic.wish.link.DeepLink$TargetType r1 = com.contextlogic.wish.link.DeepLink.TargetType.DATA_CONTROL_SETTINGS     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType     // Catch:{ NoSuchFieldError -> 0x006e }
                com.contextlogic.wish.link.DeepLink$TargetType r1 = com.contextlogic.wish.link.DeepLink.TargetType.CHANGE_PROFILE_PICTURE     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = $SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType     // Catch:{ NoSuchFieldError -> 0x007a }
                com.contextlogic.wish.link.DeepLink$TargetType r1 = com.contextlogic.wish.link.DeepLink.TargetType.CHANGE_CURRENCY     // Catch:{ NoSuchFieldError -> 0x007a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007a }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007a }
            L_0x007a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.settings.SettingsFragment.AnonymousClass4.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.settings_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public void refreshAdapter() {
        this.mAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void startAccountSettingsForCountry(SettingsActivity settingsActivity) {
        Intent intent = new Intent();
        intent.setClass(settingsActivity, AccountSettingsActivity.class);
        settingsActivity.startActivityForResult(intent, settingsActivity.addResultCodeCallback(new ActivityResultCallback() {
            public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                SettingsFragment.this.refreshAdapter();
            }
        }));
    }

    /* access modifiers changed from: private */
    public void startCurrencySettings(final SettingsActivity settingsActivity) {
        Intent intent = new Intent();
        intent.setClass(settingsActivity, ChangeCurrencyActivity.class);
        settingsActivity.startActivityForResult(intent, settingsActivity.addResultCodeCallback(new ActivityResultCallback() {
            public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                if (i2 == -1) {
                    settingsActivity.setResult(-1, intent);
                }
            }
        }));
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mListView = (ListView) findViewById(R.id.settings_fragment_listview);
        this.mAdapter = new SettingsAdapter((SettingsActivity) getBaseActivity(), this);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                final Object item = SettingsFragment.this.mAdapter.getItem(i);
                if (item == null) {
                    return;
                }
                if (SettingsFragment.this.mAdapter.isLogoutItem(item)) {
                    SettingsFragment.this.withServiceFragment(new ServiceTask<BaseActivity, SettingsServiceFragment>() {
                        public void performTask(BaseActivity baseActivity, SettingsServiceFragment settingsServiceFragment) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_LOG_OUT);
                            settingsServiceFragment.handleLogout();
                        }
                    });
                } else if (SettingsFragment.this.mAdapter.isDeveloperSettingsItem(item)) {
                    SettingsFragment.this.withActivity(new ActivityTask<SettingsActivity>() {
                        public void performTask(SettingsActivity settingsActivity) {
                            Intent intent = new Intent();
                            intent.setClass(settingsActivity, DeveloperSettingsActivity.class);
                            settingsActivity.startActivity(intent);
                        }
                    });
                } else if (SettingsFragment.this.mAdapter.isSettingItem(item)) {
                    SettingsFragment.this.withActivity(new ActivityTask<SettingsActivity>() {
                        public void performTask(SettingsActivity settingsActivity) {
                            switch (AnonymousClass4.$SwitchMap$com$contextlogic$wish$link$DeepLink$TargetType[new DeepLink(((WishSettingItem) item).action()).getTargetType().ordinal()]) {
                                case 1:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_MANAGE_ADDRESSES);
                                    break;
                                case 2:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_MANAGE_PAYMENTS);
                                    break;
                                case 3:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_RATE_APP);
                                    break;
                                case 4:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_NOTIFICATION_PREFS);
                                    break;
                                case 5:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_EMAIL_NOTIFICATION_SETTINGS);
                                    break;
                                case 6:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_ACCOUNT_PREFS);
                                    SettingsFragment.this.startAccountSettingsForCountry(settingsActivity);
                                    return;
                                case 7:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_PUSH_PREFS);
                                    break;
                                case 8:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_DATA_CONTROL);
                                    break;
                                case 9:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_CHANGE_PROFILE_PIC);
                                    break;
                                case 10:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_CHANGE_CURRENCY);
                                    SettingsFragment.this.startCurrencySettings(settingsActivity);
                                    return;
                            }
                            DeepLinkManager.processDeepLink(settingsActivity, new DeepLink(((WishSettingItem) item).action()));
                        }
                    });
                }
            }
        });
        this.mListView.addFooterView(new SettingsFooter(getContext()));
    }
}
