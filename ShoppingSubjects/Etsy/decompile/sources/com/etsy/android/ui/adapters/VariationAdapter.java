package com.etsy.android.ui.adapters;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.Option;
import com.etsy.android.lib.util.aj;
import com.etsy.android.uikit.adapter.BaseModelArrayAdapter;
import java.util.Collection;
import java.util.List;

public class VariationAdapter extends BaseModelArrayAdapter<Option> {
    public VariationAdapter(FragmentActivity fragmentActivity, List<Option> list) {
        super(fragmentActivity, R.layout.list_item_text, null);
        addAll((Collection<? extends T>) list);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        boolean z;
        if (view == null) {
            view = getLayoutInflater().inflate(getLayoutId(), null);
        }
        Option option = (Option) getItem(i);
        TextView textView = (TextView) view.findViewById(R.id.item_label);
        if (option != null) {
            textView.setText(option.getFormattedValue());
            z = option.isAvailable();
        } else {
            z = false;
        }
        if (!z) {
            textView.setEnabled(false);
            textView.setClickable(true);
            aj.a((View) textView, 0.6f);
        } else {
            textView.setEnabled(true);
            textView.setClickable(false);
        }
        return view;
    }
}
