package com.etsy.android.uikit.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.util.CountryUtil.EverywhereCountry;
import com.etsy.android.lib.util.aj;
import com.etsy.android.stylekit.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CountriesAdapter extends BaseModelArrayAdapter<Country> {
    private int mDividerCountryPosition;
    private ArrayList<Country> mEnabledCountries;
    @NonNull
    private w mViewTracker;

    private static class a {
        ViewStub a;
        View b;
        TextView c;

        private a() {
        }
    }

    public CountriesAdapter(FragmentActivity fragmentActivity, @NonNull w wVar, List<Country> list, int i, ArrayList<Country> arrayList) {
        super(fragmentActivity, k.list_item_text, null);
        this.mViewTracker = wVar;
        setDividerCountryPosition(i);
        setEnabledCountries(arrayList);
        addAll((Collection<? extends T>) list);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (this.mViewTracker.c().c(b.j)) {
            Country country = (Country) getItem(i);
            if (country != null) {
                if (view == null) {
                    view = getLayoutInflater().inflate(getLayoutId(), null);
                    aVar = new a();
                    aVar.c = (TextView) view.findViewById(i.item_label);
                    aVar.a = (ViewStub) view.findViewById(i.list_item_divider);
                    aVar.b = view.findViewById(i.list_item_divider_inflated);
                    view.setTag(aVar);
                } else {
                    aVar = (a) view.getTag();
                }
                updateRowDivider(aVar, view, i);
                updateRowText(aVar, country);
                updateRowStatus(view, country);
            }
        } else {
            if (view == null) {
                view = getLayoutInflater().inflate(getLayoutId(), null);
            }
            Country country2 = (Country) getItem(i);
            TextView textView = (TextView) view.findViewById(i.item_label);
            if (country2 != null) {
                textView.setText(country2.getName());
                if (country2 instanceof EverywhereCountry) {
                    e.a(textView, o.sk_typeface_bold);
                } else {
                    e.a(textView, o.sk_typeface_normal);
                }
            }
        }
        return view;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return getView(i, view, viewGroup);
    }

    public boolean isEnabled(int i) {
        if (!this.mViewTracker.c().c(b.j) || this.mEnabledCountries == null) {
            return true;
        }
        return this.mEnabledCountries.contains(getItem(i));
    }

    private void updateRowDivider(a aVar, View view, int i) {
        if (i != this.mDividerCountryPosition || i == 0) {
            if (aVar.b != null) {
                aVar.b.setVisibility(8);
            }
            if (aVar.a != null) {
                aVar.a.setVisibility(8);
            }
        } else if (aVar.b != null) {
            aVar.b.setVisibility(0);
        } else {
            aVar.a = (ViewStub) view.findViewById(i.list_item_divider);
            if (aVar.a != null) {
                aVar.a.setVisibility(0);
                aVar.b = view.findViewById(i.list_item_divider_inflated);
            }
        }
    }

    private void updateRowText(a aVar, Country country) {
        aVar.c.setText(country.getName());
        if (country instanceof EverywhereCountry) {
            e.a(aVar.c, o.sk_typeface_bold);
        } else {
            e.a(aVar.c, o.sk_typeface_normal);
        }
    }

    private void updateRowStatus(View view, Country country) {
        if (this.mEnabledCountries == null) {
            view.setEnabled(true);
            aj.a(view, 1.0f);
        } else if (!this.mEnabledCountries.contains(country)) {
            aj.a(view);
        } else {
            aj.b(view);
        }
    }

    public void setDividerCountryPosition(int i) {
        this.mDividerCountryPosition = i;
    }

    public void setEnabledCountries(ArrayList<Country> arrayList) {
        this.mEnabledCountries = arrayList;
    }
}
