package com.contextlogic.wish.activity.settings.accountsettings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishSettingItem;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.util.LocaleUtil;
import java.util.ArrayList;

public class AccountSettingsAdapter extends BaseAdapter {
    private ArrayList<Object> mAccountSettingItems;
    private AccountSettingsActivity mBaseActivity;
    private Object mDeleteAccountItemMarker;
    private AccountSettingsFragment mFragment;

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

    public AccountSettingsAdapter(AccountSettingsActivity accountSettingsActivity, AccountSettingsFragment accountSettingsFragment) {
        this.mBaseActivity = accountSettingsActivity;
        this.mFragment = accountSettingsFragment;
        setupAccountSettings();
    }

    private void setupAccountSettings() {
        this.mDeleteAccountItemMarker = new Object();
        this.mAccountSettingItems = new ArrayList<>();
        this.mAccountSettingItems.addAll(ConfigDataCenter.getInstance().getAccountSettings());
        this.mAccountSettingItems.add(this.mDeleteAccountItemMarker);
    }

    public boolean isDeleteAccountItem(Object obj) {
        return obj == this.mDeleteAccountItemMarker;
    }

    public boolean isCountryItem(Object obj) {
        return ((WishSettingItem) obj).action().endsWith("change-country");
    }

    public boolean isSettingItem(Object obj) {
        return obj instanceof WishSettingItem;
    }

    public int getCount() {
        return this.mAccountSettingItems.size();
    }

    public Object getItem(int i) {
        if (i < this.mAccountSettingItems.size()) {
            return this.mAccountSettingItems.get(i);
        }
        return null;
    }

    private String getCountryCode() {
        return ProfileDataCenter.getInstance().getCountryCode();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        Object item = getItem(i);
        if (view == null) {
            LayoutInflater layoutInflater = this.mBaseActivity.getLayoutInflater();
            itemRowHolder = new ItemRowHolder();
            view = layoutInflater.inflate(R.layout.account_settings_fragment_row, viewGroup, false);
            itemRowHolder.rowText = (TextView) view.findViewById(R.id.account_settings_fragment_row_text);
            itemRowHolder.rowImage = (AutoReleasableImageView) view.findViewById(R.id.account_settings_fragment_row_image);
            view.setTag(itemRowHolder);
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        if (isDeleteAccountItem(item)) {
            itemRowHolder.rowText.setText(this.mBaseActivity.getString(R.string.deactivate_account));
        } else if (isCountryItem(item)) {
            itemRowHolder.rowText.setText(this.mBaseActivity.getString(R.string.country_or_region));
            int resIdFromCountryCode = LocaleUtil.getResIdFromCountryCode(getCountryCode());
            itemRowHolder.rowImage.setVisibility(0);
            itemRowHolder.rowImage.setImageResource(resIdFromCountryCode);
        } else if (isSettingItem(item)) {
            itemRowHolder.rowText.setText(((WishSettingItem) item).getName());
        }
        return view;
    }
}
