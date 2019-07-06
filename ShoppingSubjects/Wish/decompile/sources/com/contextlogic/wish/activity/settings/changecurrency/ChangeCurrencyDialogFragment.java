package com.contextlogic.wish.activity.settings.changecurrency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.util.DisplayUtil;
import java.util.ArrayList;

public class ChangeCurrencyDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    /* access modifiers changed from: private */
    public ChangeCurrencyAdapter mAdapter;
    private View mContainer;
    private ListView mListView;
    private ArrayList<String> mOptions;
    private String mSelectedChoice;
    private ArrayList<String> mSymbols;

    public int getGravity() {
        return 81;
    }

    public static ChangeCurrencyDialogFragment createChangeCurrencyDialog(ArrayList<String> arrayList, ArrayList<String> arrayList2, String str) {
        ChangeCurrencyDialogFragment changeCurrencyDialogFragment = new ChangeCurrencyDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("ArgumentOptions", arrayList);
        bundle.putStringArrayList("ArgumentSymbols", arrayList2);
        bundle.putString("ArgumentPrepickedChoices", str);
        changeCurrencyDialogFragment.setArguments(bundle);
        return changeCurrencyDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (!initializeState()) {
            return null;
        }
        this.mContainer = layoutInflater.inflate(R.layout.change_currency_dialog_fragment, viewGroup, false);
        this.mListView = (ListView) this.mContainer.findViewById(R.id.change_currency_dialog_fragment_list_view);
        this.mAdapter = new ChangeCurrencyAdapter(getContext(), this.mOptions, this.mSymbols);
        this.mAdapter.setSelectedChoice(this.mSelectedChoice);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                String item = ChangeCurrencyDialogFragment.this.mAdapter.getItem(i);
                String symbol = ChangeCurrencyDialogFragment.this.mAdapter.getSymbol(i);
                if (item != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("ResultNewCurrency", item);
                    bundle.putString("ResultNewCurrencySymbol", symbol);
                    ChangeCurrencyDialogFragment.this.makeSelection(bundle);
                }
            }
        });
        return this.mContainer;
    }

    private boolean initializeState() {
        Bundle arguments = getArguments();
        this.mOptions = arguments.getStringArrayList("ArgumentOptions");
        this.mSymbols = arguments.getStringArrayList("ArgumentSymbols");
        this.mSelectedChoice = arguments.getString("ArgumentPrepickedChoices");
        return (this.mOptions == null || this.mSymbols == null || this.mOptions.size() != this.mSymbols.size() || this.mSelectedChoice == null) ? false : true;
    }

    public int getDialogWidth() {
        int displayWidth = DisplayUtil.getDisplayWidth();
        int maxDialogWidth = getMaxDialogWidth();
        return displayWidth > maxDialogWidth ? maxDialogWidth : displayWidth;
    }
}
