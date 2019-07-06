package com.etsy.android.ui.adapters;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.Option;
import com.etsy.android.uikit.adapter.BaseModelArrayAdapter;
import java.util.List;

public class VariationsSpinnerAdapter extends BaseModelArrayAdapter<Option> implements SpinnerAdapter {
    private final List<Option> mOptions;
    private int mStoredSelection = -1;

    public VariationsSpinnerAdapter(FragmentActivity fragmentActivity, List<Option> list) {
        super(fragmentActivity, 0, null);
        this.mOptions = list;
    }

    public void setSelection(int i) {
        this.mStoredSelection = i;
    }

    public int getSelection() {
        return this.mStoredSelection;
    }

    public int getCount() {
        return this.mOptions.size();
    }

    public Option getItem(int i) {
        return (Option) this.mOptions.get(i);
    }

    public long getItemId(int i) {
        if (i > this.mOptions.size() - 1) {
            return ((Option) this.mOptions.get(this.mOptions.size() - 1)).getValueId();
        }
        return ((Option) this.mOptions.get(i)).getValueId();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Option option;
        TextView textView = (TextView) view;
        if (i > this.mOptions.size() - 1) {
            option = (Option) this.mOptions.get(this.mOptions.size() - 1);
        } else {
            option = (Option) this.mOptions.get(i);
        }
        if (textView == null) {
            textView = (TextView) LayoutInflater.from(getActivityContext()).inflate(R.layout.spinner_item_black, viewGroup, false);
        }
        textView.setText(option.getFormattedValue());
        return textView;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        Option option = (Option) this.mOptions.get(i);
        if (textView == null) {
            textView = (TextView) LayoutInflater.from(getActivityContext()).inflate(R.layout.spinner_dropdown_item, viewGroup, false);
        }
        textView.setText(option.getFormattedValue());
        if (!option.isAvailable()) {
            textView.setEnabled(false);
            textView.setClickable(true);
        } else {
            textView.setEnabled(true);
            textView.setClickable(false);
        }
        return textView;
    }
}
