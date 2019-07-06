package com.contextlogic.wish.activity.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishSettingItem;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import java.util.ArrayList;

public class SettingsAdapter extends BaseAdapter {
    private SettingsActivity mBaseActivity;
    private Object mDeveloperSettingsMarker;
    private SettingsFragment mFragment;
    private Object mLogoutItemMarker;
    private ArrayList<Object> mSettingItems;

    static class ItemRowHolder {
        AutoReleasableImageView rowImage;
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

    public SettingsAdapter(SettingsActivity settingsActivity, SettingsFragment settingsFragment) {
        this.mBaseActivity = settingsActivity;
        this.mFragment = settingsFragment;
        setupSettings();
    }

    public boolean isLogoutItem(Object obj) {
        return obj == this.mLogoutItemMarker;
    }

    public boolean isDeveloperSettingsItem(Object obj) {
        return obj == this.mDeveloperSettingsMarker;
    }

    public boolean isSettingItem(Object obj) {
        return obj instanceof WishSettingItem;
    }

    public boolean isAccountSettingItem(Object obj) {
        return ((WishSettingItem) obj).action().endsWith("account-settings");
    }

    private void setupSettings() {
        this.mLogoutItemMarker = new Object();
        this.mDeveloperSettingsMarker = new Object();
        this.mSettingItems = new ArrayList<>();
        this.mSettingItems.addAll(ConfigDataCenter.getInstance().getSettings());
        this.mSettingItems.add(this.mLogoutItemMarker);
        if (ProfileDataCenter.getInstance().isAdmin()) {
            this.mSettingItems.add(this.mDeveloperSettingsMarker);
        }
    }

    public int getCount() {
        return this.mSettingItems.size();
    }

    public Object getItem(int i) {
        if (i < this.mSettingItems.size()) {
            return this.mSettingItems.get(i);
        }
        return null;
    }

    private String countryCodeToFileName(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("flag_");
        sb.append(str.toLowerCase());
        return sb.toString();
    }

    private String getCountryCode() {
        return ProfileDataCenter.getInstance().getCountryCode();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        if (view == null) {
            LayoutInflater layoutInflater = this.mBaseActivity.getLayoutInflater();
            itemRowHolder = new ItemRowHolder();
            view = layoutInflater.inflate(R.layout.settings_fragment_row, viewGroup, false);
            itemRowHolder.rowText = (TextView) view.findViewById(R.id.settings_fragment_row_text);
            itemRowHolder.rowImage = (AutoReleasableImageView) view.findViewById(R.id.settings_fragment_row_image);
            view.setTag(itemRowHolder);
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        itemRowHolder.rowImage.setVisibility(8);
        Object item = getItem(i);
        if (isLogoutItem(item)) {
            itemRowHolder.rowText.setText(this.mBaseActivity.getString(R.string.logout));
        } else if (isDeveloperSettingsItem(item)) {
            itemRowHolder.rowText.setText(this.mBaseActivity.getString(R.string.developer_settings));
        } else if (isSettingItem(item)) {
            itemRowHolder.rowText.setText(((WishSettingItem) item).getName());
            if (isAccountSettingItem(item)) {
                int identifier = WishApplication.getInstance().getResources().getIdentifier(countryCodeToFileName(getCountryCode()), "drawable", this.mBaseActivity.getApplicationContext().getPackageName());
                itemRowHolder.rowImage.setVisibility(0);
                itemRowHolder.rowImage.setImageResource(identifier);
            }
        }
        return view;
    }
}
