package com.contextlogic.wish.activity.settings.changecurrency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.List;

public class ChangeCurrencyAdapter extends ArrayAdapter<String> {
    private List<String> mOptions;
    private String mSelectedChoice;
    private List<String> mSymbols;

    static class ItemRowHolder {
        ThemedTextView rowText;

        ItemRowHolder() {
        }
    }

    public ChangeCurrencyAdapter(Context context, List<String> list, List<String> list2) {
        super(context, R.layout.change_currency_dialog_fragment_row);
        this.mOptions = list;
        this.mSymbols = list2;
    }

    public void setSelectedChoice(String str) {
        this.mSelectedChoice = str;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        if (view == null) {
            ItemRowHolder itemRowHolder2 = new ItemRowHolder();
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.change_currency_dialog_fragment_row, viewGroup, false);
            itemRowHolder2.rowText = (ThemedTextView) inflate.findViewById(R.id.change_currency_dialog_fragment_row_option);
            inflate.setTag(itemRowHolder2);
            View view2 = inflate;
            itemRowHolder = itemRowHolder2;
            view = view2;
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        String str = (String) this.mOptions.get(i);
        String str2 = (String) this.mSymbols.get(i);
        if (str.equals(this.mSelectedChoice)) {
            view.setBackgroundResource(R.color.gray6);
        } else {
            view.setBackgroundResource(R.drawable.dialog_fragment_row_selector);
        }
        itemRowHolder.rowText.setText(WishApplication.getInstance().getString(R.string.change_currency_selection_dialog_item_text, new Object[]{str, str2}));
        return view;
    }

    public int getCount() {
        if (this.mOptions != null) {
            return this.mOptions.size();
        }
        return 0;
    }

    public String getItem(int i) {
        if (this.mOptions == null || i < 0 || i >= this.mOptions.size()) {
            return null;
        }
        return (String) this.mOptions.get(i);
    }

    public String getSymbol(int i) {
        if (i < 0 || i >= getCount()) {
            return null;
        }
        return (String) this.mSymbols.get(i);
    }
}
