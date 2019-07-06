package com.contextlogic.wish.activity.settings.datacontrol;

import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.UiFragment;
import com.facebook.FacebookSdk;

public class DataControlSettingsFragment extends UiFragment<DataControlSettingsActivity> {
    private DataControlSettingsAdapter mAdapter;
    private ListView mListView;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.data_control_settings_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mListView = (ListView) findViewById(R.id.data_control_settings_fragment_listview);
        this.mAdapter = new DataControlSettingsAdapter((DataControlSettingsActivity) getBaseActivity(), this);
        this.mListView.setAdapter(this.mAdapter);
    }

    public void changeFacebookDataControlState(final boolean z) {
        withActivity(new ActivityTask<DataControlSettingsActivity>() {
            public void performTask(DataControlSettingsActivity dataControlSettingsActivity) {
                FacebookSdk.setLimitEventAndDataUsage(dataControlSettingsActivity, !z);
            }
        });
    }
}
