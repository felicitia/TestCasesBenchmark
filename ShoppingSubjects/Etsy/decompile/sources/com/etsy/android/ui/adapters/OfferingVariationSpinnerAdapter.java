package com.etsy.android.ui.adapters;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.OfferingOption;
import com.etsy.android.uikit.adapter.BaseModelArrayAdapter;
import java.util.List;

public class OfferingVariationSpinnerAdapter extends BaseModelArrayAdapter<OfferingOption> implements SpinnerAdapter {
    private final List<OfferingOption> mOptions;
    private int mStoredSelection = -1;

    public OfferingVariationSpinnerAdapter(FragmentActivity fragmentActivity, List<OfferingOption> list) {
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

    public OfferingOption getItem(int i) {
        return (OfferingOption) this.mOptions.get(i);
    }

    public long getItemId(int i) {
        if (i > this.mOptions.size() - 1) {
            return ((OfferingOption) this.mOptions.get(this.mOptions.size() - 1)).getValue().getIdAsLong();
        }
        return ((OfferingOption) this.mOptions.get(i)).getValue().getIdAsLong();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        OfferingOption offeringOption;
        TextView textView = (TextView) view;
        if (i > this.mOptions.size() - 1) {
            offeringOption = (OfferingOption) this.mOptions.get(this.mOptions.size() - 1);
        } else {
            offeringOption = (OfferingOption) this.mOptions.get(i);
        }
        if (textView == null) {
            textView = (TextView) LayoutInflater.from(getActivityContext()).inflate(R.layout.spinner_item_black, viewGroup, false);
        }
        textView.setText(offeringOption.getFormattedDisplayValue());
        return textView;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        OfferingOption offeringOption = (OfferingOption) this.mOptions.get(i);
        if (textView == null) {
            textView = (TextView) LayoutInflater.from(getActivityContext()).inflate(R.layout.spinner_dropdown_item, viewGroup, false);
        }
        textView.setText(offeringOption.getFormattedDisplayValue());
        if (!offeringOption.isEnabled()) {
            textView.setEnabled(false);
            textView.setClickable(true);
        } else {
            textView.setEnabled(true);
            textView.setClickable(false);
        }
        return textView;
    }
}
