package com.contextlogic.wish.activity.settings.accountsettings.countrysettings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.util.AddressUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class CountrySettingsAdapter extends BaseAdapter {
    private CountrySettingsActivity mBaseActivity;
    private HashMap<String, String> mCountryData;
    private ArrayList<String> mCountrySettingsItems;
    /* access modifiers changed from: private */
    public CountrySettingsFragment mFragment;

    static class ItemRowHolder {
        RadioButton rowRadioButton;
        TextView rowText;

        ItemRowHolder() {
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public CountrySettingsAdapter(CountrySettingsActivity countrySettingsActivity, CountrySettingsFragment countrySettingsFragment) {
        this.mBaseActivity = countrySettingsActivity;
        this.mFragment = countrySettingsFragment;
        setupCountrySettings();
    }

    private void setupCountrySettings() {
        this.mCountrySettingsItems = new ArrayList<>();
        this.mCountrySettingsItems.addAll(AddressUtil.getCountries().values());
        Collections.sort(this.mCountrySettingsItems);
        this.mCountryData = AddressUtil.getCountries();
    }

    /* access modifiers changed from: private */
    public String countryToCountryCode(String str) {
        if (this.mCountryData.containsValue(str)) {
            for (Entry entry : this.mCountryData.entrySet()) {
                if (((String) entry.getValue()).equals(str)) {
                    return (String) entry.getKey();
                }
            }
        }
        return null;
    }

    public int getCount() {
        return this.mCountrySettingsItems.size();
    }

    public Object getItem(int i) {
        if (i < this.mCountrySettingsItems.size()) {
            return (String) this.mCountrySettingsItems.get(i);
        }
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        if (view == null) {
            LayoutInflater layoutInflater = this.mBaseActivity.getLayoutInflater();
            itemRowHolder = new ItemRowHolder();
            view = layoutInflater.inflate(R.layout.country_settings_fragment_row, viewGroup, false);
            itemRowHolder.rowText = (TextView) view.findViewById(R.id.country_settings_fragment_row_text);
            itemRowHolder.rowRadioButton = (RadioButton) view.findViewById(R.id.country_settings_fragment_row_radio_button);
            view.setTag(itemRowHolder);
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        final Object item = getItem(i);
        itemRowHolder.rowText.setText(item.toString());
        itemRowHolder.rowRadioButton.setTag(Integer.valueOf(i));
        itemRowHolder.rowRadioButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CountrySettingsAdapter.this.mFragment.changeCountry(CountrySettingsAdapter.this.countryToCountryCode(item.toString()));
            }
        });
        if (this.mFragment.getCountryCode() != null) {
            itemRowHolder.rowRadioButton.setChecked(this.mFragment.getCountryCode().equals(countryToCountryCode(item.toString())));
        } else {
            itemRowHolder.rowRadioButton.setChecked(false);
        }
        return view;
    }
}
