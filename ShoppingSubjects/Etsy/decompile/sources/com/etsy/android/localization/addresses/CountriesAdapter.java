package com.etsy.android.localization.addresses;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.etsy.android.localization.addresses.e.b;
import java.util.ArrayList;

public class CountriesAdapter implements SpinnerAdapter {
    ArrayList<d> countries;
    LayoutInflater inflater;

    private static class a {
        View a;
        TextView b;

        a(View view) {
            this.a = view;
            this.a.setTag(this);
            this.b = (TextView) view.findViewById(16908308);
        }
    }

    public long getItemId(int i) {
        return 0;
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public boolean hasStableIds() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
    }

    CountriesAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.countries = c.a(context).a();
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return createView(i, view, viewGroup);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return createView(i, view, viewGroup);
    }

    private View createView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this.inflater.inflate(b.support_simple_spinner_dropdown_item, viewGroup, false));
        } else {
            aVar = (a) view.getTag();
        }
        aVar.b.setText(getItem(i).a());
        return aVar.a;
    }

    public int getCount() {
        return this.countries.size();
    }

    public d getItem(int i) {
        return (d) this.countries.get(i);
    }

    public int getPosition(String str) {
        for (int i = 0; i < this.countries.size(); i++) {
            if (((d) this.countries.get(i)).e.toLowerCase().equals(str.toLowerCase())) {
                return i;
            }
        }
        return 0;
    }
}
