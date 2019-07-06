package com.contextlogic.wish.activity.settings.datacontrol;

import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.application.WishApplication;
import com.facebook.FacebookSdk;

public class DataControlSettingsAdapter extends BaseAdapter {
    private DataControlSettingsActivity mBaseActivity;
    /* access modifiers changed from: private */
    public DataControlSettingsFragment mFragment;

    static class ItemRowHolder {
        TextView rowBigText;
        TextView rowSmallText;
        SwitchCompat rowSwitch;

        ItemRowHolder() {
        }
    }

    public int getCount() {
        return 1;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public boolean isEnabled(int i) {
        return true;
    }

    public DataControlSettingsAdapter(DataControlSettingsActivity dataControlSettingsActivity, DataControlSettingsFragment dataControlSettingsFragment) {
        this.mBaseActivity = dataControlSettingsActivity;
        this.mFragment = dataControlSettingsFragment;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = this.mBaseActivity.getLayoutInflater();
        ItemRowHolder itemRowHolder = new ItemRowHolder();
        View inflate = layoutInflater.inflate(R.layout.data_control_settings_fragment_row, viewGroup, false);
        itemRowHolder.rowBigText = (TextView) inflate.findViewById(R.id.data_control_settings_fragment_row_big_text);
        itemRowHolder.rowSmallText = (TextView) inflate.findViewById(R.id.data_control_settings_fragment_row_small_text);
        itemRowHolder.rowSwitch = (SwitchCompat) inflate.findViewById(R.id.data_control_settings_fragment_row_switch);
        itemRowHolder.rowBigText.setText(WishApplication.getInstance().getString(R.string.facebook));
        itemRowHolder.rowSmallText.setText(WishApplication.getInstance().getString(R.string.facebook_data_control));
        itemRowHolder.rowSwitch.setOnCheckedChangeListener(null);
        itemRowHolder.rowSwitch.setChecked(!FacebookSdk.getLimitEventAndDataUsage(WishApplication.getInstance()));
        itemRowHolder.rowSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_DATA_CONTROL_ON);
                } else {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_DATA_CONTROL_OFF);
                }
                DataControlSettingsAdapter.this.mFragment.changeFacebookDataControlState(z);
            }
        });
        return inflate;
    }
}
