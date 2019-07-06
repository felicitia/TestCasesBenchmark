package com.etsy.android.uikit.ui.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.n;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.config.g;
import com.etsy.android.lib.core.e;
import com.etsy.android.lib.core.e.c;
import com.etsy.android.lib.core.f.a;
import com.etsy.android.lib.core.http.body.BaseHttpBody;
import com.etsy.android.lib.core.http.body.FormBody;
import com.etsy.android.lib.core.http.loader.NetworkLoader;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.url.a.b.d;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.DeviceNotification;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.NotificationSettings;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.bughunt.PushNotificationSetting;
import com.etsy.android.lib.requests.DeviceRequest;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.p;
import com.etsy.android.uikit.ui.core.NetworkLoaderFragment;
import com.etsy.android.uikit.util.TrackingOnCheckedChangeListener;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.List;

public class NotificationSettingsFragment extends NetworkLoaderFragment {
    private static final int GET_SETTINGS_REQUEST_ID = 1;
    /* access modifiers changed from: private */
    public static final String TAG = f.a(NotificationSettingsFragment.class);
    private static final int UPDATE_SETTING_REQUEST_ID = 2;
    protected final int REQUEST_CODE_RINGTONE = 1;
    protected View mCardRingtone;
    protected final OnCheckedChangeListener mCheckedChangedListener = new TrackingOnCheckedChangeListener() {
        @SuppressLint({"MissingPermission"})
        public void onViewCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (compoundButton.getId() == i.switch_sound) {
                NotificationSettingsFragment.this.switchSoundCheckedChanged(z);
            } else if (compoundButton.getId() == i.switch_vibrate) {
                if (z) {
                    ((Vibrator) NotificationSettingsFragment.this.getActivity().getSystemService("vibrator")).vibrate(300);
                }
                SharedPreferencesUtility.c(NotificationSettingsFragment.this.getActivity(), "notification_vibrate", z);
            } else if (compoundButton.getId() == i.switch_light) {
                SharedPreferencesUtility.c(NotificationSettingsFragment.this.getActivity(), "notification_led", z);
            } else if (NotificationSettingsFragment.this.mConfigGroupNotificationSettings) {
                NotificationSettingsFragment.this.updateNotificationSettingGroup(compoundButton);
            } else {
                NotificationSettingsFragment.this.serverOnCheckedChanged(compoundButton);
            }
        }
    };
    private final OnClickListener mClickListener = new TrackingOnClickListener() {
        public void onViewClick(View view) {
            if (view.getId() == i.card_ringtone) {
                Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
                intent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", true);
                intent.putExtra("android.intent.extra.ringtone.DEFAULT_URI", NotificationSettingsFragment.this.mUriEtsyNotification);
                intent.putExtra("android.intent.extra.ringtone.SHOW_SILENT", false);
                String a2 = SharedPreferencesUtility.a((Context) NotificationSettingsFragment.this.getActivity(), "notification_ringtone", (String) null);
                intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", a2 == null ? NotificationSettingsFragment.this.mUriEtsyNotification : Uri.parse(a2));
                NotificationSettingsFragment.this.startActivityForResult(intent, 1);
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mConfigGroupNotificationSettings;
    protected View mErrorView;
    protected View mLoadingView;
    /* access modifiers changed from: private */
    public NotificationSettings mNotificationSettings;
    /* access modifiers changed from: private */
    public List<PushNotificationSetting> mNotificationSettingsGroups;
    protected LinearLayout mServerDrivenNotificationSettingsSection;
    protected SwitchCompat mSwitchLight;
    protected SwitchCompat mSwitchSound;
    protected SwitchCompat mSwitchVibrate;
    protected TextView mTxtSelectedRingtone;
    protected TextView mTxtServerDrivenSettingsSectionHeader;
    protected Uri mUriEtsyNotification;

    @NonNull
    public String getTrackingName() {
        return "push_notifications";
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(k.fragment_notification_settings, viewGroup, false);
        this.mConfigGroupNotificationSettings = getConfigMap().c(b.bz);
        this.mTxtServerDrivenSettingsSectionHeader = (TextView) inflate.findViewById(i.server_driven_settings_section_header);
        this.mTxtServerDrivenSettingsSectionHeader.setText(getServerDrivenSettingsSectionHeaderTextResId());
        this.mServerDrivenNotificationSettingsSection = (LinearLayout) inflate.findViewById(i.server_driven_settings_section);
        this.mLoadingView = inflate.findViewById(i.loading_notification_settings);
        this.mErrorView = inflate.findViewById(i.error_view);
        this.mErrorView.findViewById(i.btn_retry_endless).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                NotificationSettingsFragment.this.requestNotificationSettings();
            }
        });
        this.mUriEtsyNotification = p.a((Context) getActivity(), n.notification);
        this.mCardRingtone = inflate.findViewById(i.card_ringtone);
        this.mCardRingtone.setOnClickListener(this.mClickListener);
        this.mTxtSelectedRingtone = (TextView) inflate.findViewById(i.txt_selected_ringtone);
        this.mSwitchVibrate = (SwitchCompat) inflate.findViewById(i.switch_vibrate);
        this.mSwitchLight = (SwitchCompat) inflate.findViewById(i.switch_light);
        this.mSwitchSound = (SwitchCompat) inflate.findViewById(i.switch_sound);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        setRingtoneText(SharedPreferencesUtility.a((Context) getActivity(), "notification_ringtone", this.mUriEtsyNotification.toString()));
        this.mSwitchVibrate.setChecked(SharedPreferencesUtility.b((Context) getActivity(), "notification_vibrate", true));
        this.mSwitchLight.setChecked(SharedPreferencesUtility.b((Context) getActivity(), "notification_led", true));
        this.mSwitchSound.setChecked(SharedPreferencesUtility.b((Context) getActivity(), "notification_sound", true));
        this.mCardRingtone.setVisibility(this.mSwitchSound.isChecked() ? 0 : 8);
        this.mSwitchVibrate.setOnCheckedChangeListener(this.mCheckedChangedListener);
        this.mSwitchLight.setOnCheckedChangeListener(this.mCheckedChangedListener);
        this.mSwitchSound.setOnCheckedChangeListener(this.mCheckedChangedListener);
    }

    public void onPause() {
        super.onPause();
        this.mSwitchVibrate.setOnCheckedChangeListener(null);
        this.mSwitchLight.setOnCheckedChangeListener(null);
        this.mSwitchSound.setOnCheckedChangeListener(null);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mConfigGroupNotificationSettings) {
            if (this.mNotificationSettingsGroups == null) {
                getNotificationSettingsGroups();
            } else {
                fillNotificationSettingsView();
            }
        } else if (this.mNotificationSettings == null) {
            requestNotificationSettings();
        } else {
            fillNotificationSettingsView();
        }
    }

    public void requestNotificationSettings() {
        e a = e.a((EtsyRequest<Result>) DeviceRequest.getNotificationSettings(g.a().b()));
        a.a((c) new c() {
            public void a() {
                NotificationSettingsFragment.this.showLoadingView();
            }
        });
        a.a((com.etsy.android.lib.core.f.c<Result>) new com.etsy.android.lib.core.f.c<NotificationSettings>() {
            public void a(List<NotificationSettings> list, int i, com.etsy.android.lib.core.k<NotificationSettings> kVar) {
                NotificationSettingsFragment.this.mNotificationSettings = (NotificationSettings) list.get(0);
                NotificationSettingsFragment.this.fillNotificationSettingsView();
                NotificationSettingsFragment.this.showServerSettingList();
            }
        });
        a.a((com.etsy.android.lib.core.f.b) new com.etsy.android.lib.core.f.b() {
            public void a(int i, String str, com.etsy.android.lib.core.k kVar) {
                NotificationSettingsFragment.this.showErrorView();
                f.e(NotificationSettingsFragment.TAG, "Error requesting notification settings :(");
            }
        });
        a.a((a) new a() {
            public void a(com.etsy.android.lib.core.k kVar) {
                NotificationSettingsFragment.this.showErrorView();
                f.e(NotificationSettingsFragment.TAG, "Empty response requesting notification settings :|");
            }
        });
        getRequestQueue().a((Object) this, (com.etsy.android.lib.core.g<Result>) a.a());
    }

    /* access modifiers changed from: protected */
    public void switchSoundCheckedChanged(boolean z) {
        SharedPreferencesUtility.c(getActivity(), "notification_sound", z);
        this.mCardRingtone.setVisibility(z ? 0 : 8);
    }

    /* access modifiers changed from: protected */
    public void serverOnCheckedChanged(final CompoundButton compoundButton) {
        DeviceNotification deviceNotification = (DeviceNotification) compoundButton.getTag(i.tag_device_notification);
        deviceNotification.setPhoneEnabled(compoundButton.isChecked());
        e a = e.a((EtsyRequest<Result>) DeviceRequest.updateNotificationSetting(g.a().b(), deviceNotification));
        a.a(a.a);
        a.a((com.etsy.android.lib.core.f.b) new com.etsy.android.lib.core.f.b() {
            final SwitchCompat a = ((SwitchCompat) compoundButton);

            public void a(int i, String str, com.etsy.android.lib.core.k kVar) {
                View view = NotificationSettingsFragment.this.getView();
                if (this.a != null && view != null) {
                    this.a.setOnCheckedChangeListener(null);
                    this.a.setChecked(!this.a.isChecked());
                    this.a.setOnCheckedChangeListener(NotificationSettingsFragment.this.mCheckedChangedListener);
                    aj.a(view, (CharSequence) NotificationSettingsFragment.this.getString(o.update_notification_setting_error_message));
                }
            }
        });
        getRequestQueue().a((Object) this, (com.etsy.android.lib.core.g<Result>) a.a());
    }

    private void getNotificationSettingsGroups() {
        loadDataFromNetwork(1, ((EtsyApiV3Request.a) EtsyApiV3Request.a.a(PushNotificationSetting.class, d.a(g.a().b())).a(0)).d(), (NetworkLoader.b<ResultType>) new NetworkLoader.a<PushNotificationSetting>() {
            public void a(@NonNull List<PushNotificationSetting> list, int i, @NonNull com.etsy.android.lib.core.a.a<PushNotificationSetting> aVar) {
                if (!list.isEmpty()) {
                    NotificationSettingsFragment.this.mNotificationSettingsGroups = list;
                    NotificationSettingsFragment.this.fillNotificationSettingsView();
                    NotificationSettingsFragment.this.showServerSettingList();
                    return;
                }
                NotificationSettingsFragment.this.showErrorView();
                f.e(NotificationSettingsFragment.TAG, "Empty response requesting notification settings :|");
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<PushNotificationSetting> aVar) {
                NotificationSettingsFragment.this.showErrorView();
                f.e(NotificationSettingsFragment.TAG, "Error requesting notification settings :(");
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateNotificationSettingGroup(final CompoundButton compoundButton) {
        PushNotificationSetting pushNotificationSetting = (PushNotificationSetting) compoundButton.getTag();
        pushNotificationSetting.setEnabled(compoundButton.isChecked());
        loadDataFromNetwork(2, ((EtsyApiV3Request.a) ((EtsyApiV3Request.a) EtsyApiV3Request.a.a(EmptyResult.class, d.b(g.a().b())).a((BaseHttpBody) (FormBody) ((FormBody.a) ((FormBody.a) new FormBody.a().b(ResponseConstants.KEY, pushNotificationSetting.getKey())).b(ResponseConstants.ENABLED, Boolean.toString(pushNotificationSetting.isEnabled()))).f())).a(1)).d(), (NetworkLoader.b<ResultType>) new NetworkLoader.a<EmptyResult>() {
            public void a(@NonNull List<EmptyResult> list, int i, @NonNull com.etsy.android.lib.core.a.a<EmptyResult> aVar) {
                f.c(NotificationSettingsFragment.TAG, "Successfully updated setting");
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<EmptyResult> aVar) {
                SwitchCompat switchCompat = (SwitchCompat) compoundButton;
                View view = NotificationSettingsFragment.this.getView();
                if (switchCompat != null && view != null) {
                    switchCompat.setOnCheckedChangeListener(null);
                    switchCompat.setChecked(!switchCompat.isChecked());
                    switchCompat.setOnCheckedChangeListener(NotificationSettingsFragment.this.mCheckedChangedListener);
                    aj.a(view, (CharSequence) NotificationSettingsFragment.this.getString(o.update_notification_setting_error_message));
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void fillNotificationSettingsView() {
        this.mServerDrivenNotificationSettingsSection.removeAllViews();
        if (this.mConfigGroupNotificationSettings) {
            if (this.mNotificationSettingsGroups != null && !this.mNotificationSettingsGroups.isEmpty()) {
                for (PushNotificationSetting pushNotificationSetting : this.mNotificationSettingsGroups) {
                    ViewGroup viewGroup = (ViewGroup) getActivity().getLayoutInflater().inflate(k.item_notification_settings_group, this.mServerDrivenNotificationSettingsSection, false);
                    ((TextView) viewGroup.findViewById(i.title)).setText(pushNotificationSetting.getTitle());
                    SwitchCompat switchCompat = (SwitchCompat) viewGroup.findViewById(i.zwitch);
                    switchCompat.setText(pushNotificationSetting.getDescription());
                    switchCompat.setChecked(pushNotificationSetting.isEnabled());
                    switchCompat.setOnCheckedChangeListener(this.mCheckedChangedListener);
                    switchCompat.setTag(pushNotificationSetting);
                    this.mServerDrivenNotificationSettingsSection.addView(viewGroup);
                }
            }
        } else if (this.mNotificationSettings != null) {
            for (DeviceNotification deviceNotification : this.mNotificationSettings.getDeviceNotifications()) {
                SwitchCompat switchCompat2 = (SwitchCompat) getActivity().getLayoutInflater().inflate(k.item_notificationsettings_switch, this.mServerDrivenNotificationSettingsSection, false);
                switchCompat2.setText(deviceNotification.getText());
                switchCompat2.setChecked(deviceNotification.isPhoneEnabled());
                switchCompat2.setOnCheckedChangeListener(this.mCheckedChangedListener);
                switchCompat2.setTag(i.tag_device_notification, deviceNotification);
                this.mServerDrivenNotificationSettingsSection.addView(switchCompat2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void showLoadingView() {
        this.mLoadingView.setVisibility(0);
        this.mErrorView.setVisibility(8);
        this.mServerDrivenNotificationSettingsSection.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void showErrorView() {
        this.mLoadingView.setVisibility(8);
        this.mErrorView.setVisibility(0);
        this.mServerDrivenNotificationSettingsSection.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void showServerSettingList() {
        this.mLoadingView.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mServerDrivenNotificationSettingsSection.setVisibility(0);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1 && intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
            if (parcelableExtra != null) {
                String obj = parcelableExtra.toString();
                f.c(TAG, obj);
                if (getActivity() != null) {
                    SharedPreferencesUtility.b((Context) getActivity(), "notification_ringtone", obj);
                }
                setRingtoneText(obj);
            }
        }
    }

    private void setRingtoneText(String str) {
        if (!str.startsWith("android.resource://com.etsy.android")) {
            Ringtone ringtone = RingtoneManager.getRingtone(getActivity(), Uri.parse(str));
            if (ringtone != null) {
                this.mTxtSelectedRingtone.setText(ringtone.getTitle(getActivity()));
                return;
            }
            return;
        }
        this.mTxtSelectedRingtone.setText("Default Ringtone");
    }

    /* access modifiers changed from: protected */
    @StringRes
    public int getServerDrivenSettingsSectionHeaderTextResId() {
        return this.mConfigGroupNotificationSettings ? o.notification_settings_section_header : o.notify_me_when;
    }
}
