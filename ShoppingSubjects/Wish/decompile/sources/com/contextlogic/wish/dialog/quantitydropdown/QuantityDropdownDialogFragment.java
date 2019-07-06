package com.contextlogic.wish.dialog.quantitydropdown;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.util.DisplayUtil;
import java.util.ArrayList;

public class QuantityDropdownDialogFragment extends BaseDialogFragment {
    public int getGravity() {
        return 81;
    }

    public static QuantityDropdownDialogFragment createQuantityDropdownDialogFragment(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("INVENTORY_KEY", i);
        QuantityDropdownDialogFragment quantityDropdownDialogFragment = new QuantityDropdownDialogFragment();
        quantityDropdownDialogFragment.setArguments(bundle);
        return quantityDropdownDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.quantity_dropdown_popup_dialog_fragment, viewGroup, false);
        ListView listView = (ListView) inflate.findViewById(R.id.quantity_dropdown_dialog_fragment_list_view);
        inflate.findViewById(R.id.quantity_dropdown_select_amount_list_container).setBackground(new ColorDrawable(getContext().getResources().getColor(R.color.white)));
        final boolean shouldShowDropdownQuantityWithRemoveView = ExperimentDataCenter.getInstance().shouldShowDropdownQuantityWithRemoveView();
        ArrayList arrayList = new ArrayList();
        int i = getArguments() != null ? getArguments().getInt("INVENTORY_KEY") : 15;
        for (int i2 = shouldShowDropdownQuantityWithRemoveView; i2 <= i; i2++) {
            arrayList.add(String.valueOf(i2));
        }
        listView.setAdapter(new ArrayAdapter(getContext(), R.layout.simple_list_view_text_view_item, R.id.simple_list_view_text_view, arrayList));
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Bundle bundle = new Bundle();
                if (shouldShowDropdownQuantityWithRemoveView) {
                    i++;
                }
                bundle.putInt("QUANTITY_KEY", i);
                QuantityDropdownDialogFragment.this.makeSelection(bundle);
            }
        });
        return inflate;
    }

    public int getDialogWidth() {
        int displayWidth = DisplayUtil.getDisplayWidth();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.bottom_dialog_fragment_max_width);
        return displayWidth > dimensionPixelSize ? dimensionPixelSize : displayWidth;
    }
}
