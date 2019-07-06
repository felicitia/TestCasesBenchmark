package com.contextlogic.wish.activity.settings.notifications;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.activity.settings.notifications.NotificationSettingsAdapter.DataProvider;
import com.contextlogic.wish.activity.settings.notifications.NotificationSettingsAdapter.OnItemSelectedListener;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishNotificationPreference;
import com.contextlogic.wish.api.model.WishNotificationPreference.PreferenceType;
import com.contextlogic.wish.cache.StateStoreCache;
import java.util.ArrayList;
import java.util.Iterator;

public class NotificationSettingsFragment extends LoadingUiFragment<NotificationSettingsActivity> {
    protected BaseAdapter mAdapter;
    protected ArrayList<WishNotificationPreference> mFilteredNotificationPreferences;
    protected boolean mHasSmsControl;
    protected boolean mIsUserSmsBlocked;
    protected ListView mListView;
    protected ArrayList<WishNotificationPreference> mNotificationPreferences;
    protected boolean mPushNotiPrefLoadingComplete;
    protected boolean mSmsNotiPrefLoadingComplete;

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.notification_settings_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public static NotificationSettingsFragment create(PreferenceType preferenceType) {
        NotificationSettingsFragment notificationSettingsFragment = new NotificationSettingsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ArgPrefType", preferenceType.getValue());
        notificationSettingsFragment.setArguments(bundle);
        return notificationSettingsFragment;
    }

    public void initializeLoadingContentView(final View view) {
        withActivity(new ActivityTask<NotificationSettingsActivity>() {
            public void performTask(NotificationSettingsActivity notificationSettingsActivity) {
                NotificationSettingsFragment.this.init(view, notificationSettingsActivity);
            }
        });
    }

    /* access modifiers changed from: protected */
    public final void initializeValues() {
        if (getSavedInstanceState() != null) {
            updateNotificationPreferences(StateStoreCache.getInstance().getParcelableList(getSavedInstanceState(), "SavedStateData", WishNotificationPreference.class));
        }
    }

    /* access modifiers changed from: protected */
    public void init(View view, BaseActivity baseActivity) {
        final PreferenceType preferenceType = getPreferenceType();
        this.mHasSmsControl = preferenceType == PreferenceType.PUSH && ExperimentDataCenter.getInstance().shouldSeeSmsSettings() && !TextUtils.isEmpty(ConfigDataCenter.getInstance().getUserPhoneNumber());
        this.mNotificationPreferences = new ArrayList<>();
        this.mListView = (ListView) view.findViewById(R.id.notification_settings_fragment_listview);
        this.mListView.setBackgroundResource(R.color.gray6);
        this.mListView.setDivider(getResources().getDrawable(R.drawable.default_listview_divider));
        this.mAdapter = new NotificationSettingsAdapter(baseActivity, new DataProvider() {
            public ArrayList<WishNotificationPreference> getData() {
                return NotificationSettingsFragment.this.getNotificationPreferences();
            }

            public boolean hasSmsPreference() {
                return NotificationSettingsFragment.this.mHasSmsControl;
            }

            public String getUserPhoneNumber() {
                return ConfigDataCenter.getInstance().getUserPhoneNumber();
            }

            public boolean isUserSmsBlocked() {
                return NotificationSettingsFragment.this.mIsUserSmsBlocked;
            }
        }, new OnItemSelectedListener() {
            public void onPushNotiPrefItemSelected(WishNotificationPreference wishNotificationPreference, boolean z) {
                ArrayList arrayList = new ArrayList(wishNotificationPreference.getPreferencesSelected());
                arrayList.set(preferenceType.ordinal(), Boolean.valueOf(z));
                NotificationSettingsFragment.this.changeNotificationPreference(wishNotificationPreference, arrayList);
            }

            public void onSmsNotiPrefItemSelected(final boolean z) {
                NotificationSettingsFragment.this.withServiceFragment(new ServiceTask<BaseActivity, NotificationSettingsServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, NotificationSettingsServiceFragment notificationSettingsServiceFragment) {
                        notificationSettingsServiceFragment.changeSmsPreference(!z);
                    }
                });
            }
        }, preferenceType);
        setupHeaderAndFooter(baseActivity, this.mListView, preferenceType);
        this.mListView.setAdapter(this.mAdapter);
        initializeValues();
    }

    private void setupHeaderAndFooter(Context context, ListView listView, PreferenceType preferenceType) {
        LayoutInflater from = LayoutInflater.from(context);
        if (preferenceType == PreferenceType.PUSH && !this.mHasSmsControl) {
            listView.addHeaderView(from.inflate(R.layout.push_notification_settings_header, listView, false));
        }
        View view = new View(context);
        view.setLayoutParams(new LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.divide_small)));
        view.setBackgroundResource(R.color.gray5);
        listView.addFooterView(view);
    }

    public ArrayList<WishNotificationPreference> getNotificationPreferences() {
        PreferenceType preferenceType = getPreferenceType();
        if (this.mFilteredNotificationPreferences == null && this.mNotificationPreferences != null && !this.mNotificationPreferences.isEmpty()) {
            this.mFilteredNotificationPreferences = new ArrayList<>();
            Iterator it = this.mNotificationPreferences.iterator();
            while (it.hasNext()) {
                WishNotificationPreference wishNotificationPreference = (WishNotificationPreference) it.next();
                if (wishNotificationPreference.isPreferenceSupported(preferenceType)) {
                    this.mFilteredNotificationPreferences.add(wishNotificationPreference);
                }
            }
        }
        return this.mFilteredNotificationPreferences;
    }

    private PreferenceType getPreferenceType() {
        PreferenceType fromInteger = getArguments() != null ? PreferenceType.fromInteger(getArguments().getInt("ArgPrefType")) : null;
        return fromInteger == null ? PreferenceType.PUSH : fromInteger;
    }

    public void handleResume() {
        super.handleResume();
        if (!getLoadingPageView().isLoadingComplete()) {
            getLoadingPageView().reload();
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        if (getLoadingPageView() != null && getLoadingPageView().isLoadingComplete()) {
            bundle.putString("SavedStateData", StateStoreCache.getInstance().storeParcelableList(this.mNotificationPreferences));
        }
    }

    public void handleReload() {
        updateNotificationPreferences(null);
        withServiceFragment(new ServiceTask<BaseActivity, NotificationSettingsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, NotificationSettingsServiceFragment notificationSettingsServiceFragment) {
                notificationSettingsServiceFragment.loadNotificationPreferences();
                if (NotificationSettingsFragment.this.mHasSmsControl) {
                    notificationSettingsServiceFragment.loadSmsPreference();
                } else {
                    NotificationSettingsFragment.this.mSmsNotiPrefLoadingComplete = true;
                }
            }
        });
    }

    public boolean hasItems() {
        return this.mNotificationPreferences.size() > 0;
    }

    public void handleLoadingErrored() {
        getLoadingPageView().markLoadingErrored();
    }

    public void handlePushNotiPrefLoadingSuccess(ArrayList<WishNotificationPreference> arrayList) {
        if (arrayList.size() > 0) {
            updateNotificationPreferences(arrayList);
        }
        this.mPushNotiPrefLoadingComplete = true;
        if (this.mSmsNotiPrefLoadingComplete) {
            getLoadingPageView().markLoadingComplete();
        }
    }

    public void handleSmsNotiPrefLoadingSuccess(boolean z) {
        this.mIsUserSmsBlocked = z;
        this.mSmsNotiPrefLoadingComplete = true;
        if (this.mPushNotiPrefLoadingComplete) {
            getLoadingPageView().markLoadingComplete();
        }
    }

    public void updateNotificationPreferences(ArrayList<WishNotificationPreference> arrayList) {
        this.mNotificationPreferences.clear();
        if (arrayList != null) {
            this.mNotificationPreferences.addAll(arrayList);
        }
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void changeNotificationPreference(final WishNotificationPreference wishNotificationPreference, ArrayList<Boolean> arrayList) {
        wishNotificationPreference.setPreferencesSelected(arrayList);
        withServiceFragment(new ServiceTask<BaseActivity, NotificationSettingsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, NotificationSettingsServiceFragment notificationSettingsServiceFragment) {
                notificationSettingsServiceFragment.saveNotificationPreference(wishNotificationPreference);
            }
        });
        this.mAdapter.notifyDataSetChanged();
    }
}
