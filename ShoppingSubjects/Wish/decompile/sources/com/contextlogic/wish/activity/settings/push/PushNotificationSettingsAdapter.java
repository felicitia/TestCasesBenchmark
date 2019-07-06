package com.contextlogic.wish.activity.settings.push;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishPushPreference;

public class PushNotificationSettingsAdapter extends BaseAdapter {
    private PushNotificationSettingsActivity mBaseActivity;
    /* access modifiers changed from: private */
    public PushNotificationSettingsFragment mFragment;

    static class ItemRowHolder {
        CheckBox rowCheckbox;
        TextView rowText;

        ItemRowHolder() {
        }
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

    public PushNotificationSettingsAdapter(PushNotificationSettingsActivity pushNotificationSettingsActivity, PushNotificationSettingsFragment pushNotificationSettingsFragment) {
        this.mBaseActivity = pushNotificationSettingsActivity;
        this.mFragment = pushNotificationSettingsFragment;
    }

    public int getCount() {
        return this.mFragment.getPushPreferences().size();
    }

    public WishPushPreference getItem(int i) {
        return (WishPushPreference) this.mFragment.getPushPreferences().get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        if (view == null) {
            LayoutInflater layoutInflater = this.mBaseActivity.getLayoutInflater();
            itemRowHolder = new ItemRowHolder();
            view = layoutInflater.inflate(R.layout.push_notification_settings_fragment_row, viewGroup, false);
            itemRowHolder.rowText = (TextView) view.findViewById(R.id.push_notification_settings_fragment_row_text);
            itemRowHolder.rowCheckbox = (CheckBox) view.findViewById(R.id.push_notification_settings_fragment_row_checkbox);
            view.setTag(itemRowHolder);
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        final WishPushPreference item = getItem(i);
        itemRowHolder.rowText.setText(item.getName());
        itemRowHolder.rowCheckbox.setOnCheckedChangeListener(null);
        itemRowHolder.rowCheckbox.setChecked(item.isPreferenceSelected());
        itemRowHolder.rowCheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PushNotificationSettingsAdapter.this.mFragment.changePushPreference(item, z);
            }
        });
        return view;
    }
}
