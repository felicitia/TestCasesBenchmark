package com.contextlogic.wish.activity.settings.push;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.api.model.WishPushPreference;
import com.contextlogic.wish.cache.StateStoreCache;
import java.util.ArrayList;

public class PushNotificationSettingsFragment extends LoadingUiFragment<PushNotificationSettingsActivity> {
    private PushNotificationSettingsAdapter mAdapter;
    private ListView mListView;
    private ArrayList<WishPushPreference> mPushPreferences;

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.push_notification_settings_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public void initializeLoadingContentView(View view) {
        this.mPushPreferences = new ArrayList<>();
        this.mListView = (ListView) view.findViewById(R.id.push_notification_settings_fragment_listview);
        this.mAdapter = new PushNotificationSettingsAdapter((PushNotificationSettingsActivity) getBaseActivity(), this);
        this.mListView.setAdapter(this.mAdapter);
        initializeValues();
    }

    private void initializeValues() {
        if (getSavedInstanceState() != null) {
            updatePushPreferences(StateStoreCache.getInstance().getParcelableList(getSavedInstanceState(), "SavedStateData", WishPushPreference.class));
        }
    }

    public void handleResume() {
        super.handleResume();
        if (!getLoadingPageView().isLoadingComplete()) {
            getLoadingPageView().reload();
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        if (getLoadingPageView() != null && getLoadingPageView().isLoadingComplete()) {
            bundle.putString("SavedStateData", StateStoreCache.getInstance().storeParcelableList(this.mPushPreferences));
        }
    }

    public void handleReload() {
        updatePushPreferences(null);
        withServiceFragment(new ServiceTask<BaseActivity, PushNotificationSettingsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, PushNotificationSettingsServiceFragment pushNotificationSettingsServiceFragment) {
                pushNotificationSettingsServiceFragment.loadPushPreferences();
            }
        });
    }

    public boolean hasItems() {
        return this.mPushPreferences.size() > 0;
    }

    public ArrayList<WishPushPreference> getPushPreferences() {
        return this.mPushPreferences;
    }

    public void handleLoadingErrored() {
        getLoadingPageView().markLoadingErrored();
    }

    public void handleLoadingSuccess(ArrayList<WishPushPreference> arrayList) {
        updatePushPreferences(arrayList);
    }

    public void updatePushPreferences(ArrayList<WishPushPreference> arrayList) {
        this.mPushPreferences.clear();
        if (arrayList != null) {
            this.mPushPreferences.addAll(arrayList);
            getLoadingPageView().markLoadingComplete();
        }
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void changePushPreference(final WishPushPreference wishPushPreference, boolean z) {
        wishPushPreference.setPreferenceSelected(z);
        withServiceFragment(new ServiceTask<BaseActivity, PushNotificationSettingsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, PushNotificationSettingsServiceFragment pushNotificationSettingsServiceFragment) {
                pushNotificationSettingsServiceFragment.savePushPreference(wishPushPreference);
            }
        });
    }
}
