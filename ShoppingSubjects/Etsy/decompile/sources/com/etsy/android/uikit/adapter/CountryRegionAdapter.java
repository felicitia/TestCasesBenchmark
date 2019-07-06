package com.etsy.android.uikit.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsSpinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.Region;
import com.etsy.android.lib.models.ShippingTemplateEntry;
import com.etsy.android.lib.models.editable.EditableShippingTemplateEntry;
import com.etsy.android.lib.util.CountryUtil;
import com.etsy.android.lib.util.CountryUtil.EverywhereCountry;
import com.etsy.android.lib.util.u;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountryRegionAdapter extends ArrayAdapter<Object> {
    private static final int STATE_HAS_HEADER = 1;
    private static final int STATE_NO_HEADER = 2;
    private static final int STATE_UNKNOWN = 0;
    private List<Country> mCountries;
    private boolean mDisableEverywhere;
    private Set<Country> mDisabledCountries;
    private Set<Region> mDisabledRegions;
    private EverywhereCountry mEverywhereCountry;
    private int[] mHeaderCache;
    private final boolean mLeftAlignPrompt;
    private Set<String> mPrimaryIsoCodes;
    private List<Region> mRegions;
    private int mResource;
    private boolean mShowEverywhereCountry;
    private View mSpinnerView;
    private Country mTopCountry;

    private static class a {
        View a;
        TextView b;
        TextView c;

        private a() {
        }

        public static a a(View view, boolean z) {
            a aVar = new a();
            aVar.a = view;
            aVar.b = (TextView) view.findViewById(i.item_label);
            aVar.c = (TextView) view.findViewById(i.item_divider_label);
            aVar.c.setOnClickListener(null);
            if (z) {
                aVar.b.setPadding(0, aVar.b.getPaddingTop(), aVar.b.getPaddingRight(), aVar.b.getPaddingBottom());
                aVar.c.setPadding(0, aVar.c.getPaddingTop(), aVar.c.getPaddingRight(), aVar.c.getPaddingBottom());
            }
            return aVar;
        }
    }

    public boolean areAllItemsEnabled() {
        return true;
    }

    @Deprecated
    public CountryRegionAdapter(Context context, List<Country> list, List<Region> list2, Country country) {
        this(context, list, list2, country, false);
    }

    public CountryRegionAdapter(Context context, List<Country> list, List<Region> list2, Country country, @LayoutRes int i) {
        this(context, list, list2, country, true);
        this.mResource = i;
    }

    public CountryRegionAdapter(Context context, List<Country> list, List<Region> list2, Country country, boolean z) {
        super(context, k.list_item_with_header);
        this.mHeaderCache = new int[0];
        this.mLeftAlignPrompt = z;
        if (z) {
            this.mResource = k.spinner_item_dark_grey_no_padding;
        } else {
            this.mResource = k.spinner_item_dark_grey;
        }
        this.mDisabledCountries = new HashSet();
        this.mDisabledRegions = new HashSet();
        this.mPrimaryIsoCodes = CountryUtil.c();
        this.mEverywhereCountry = new EverywhereCountry(context.getString(o.shipping_everywhere_else));
        this.mTopCountry = country;
        this.mCountries = list;
        this.mRegions = list2;
        setupData();
    }

    public void setRegionsAndCountries(List<Region> list, List<Country> list2) {
        this.mCountries = list2;
        this.mRegions = list;
        clear();
        setupData();
    }

    public int getEverywherePosition() {
        return super.getPosition(this.mEverywhereCountry);
    }

    public void setTopCountry(Country country) {
        if (this.mTopCountry != null) {
            this.mCountries.add(this.mTopCountry);
        }
        this.mTopCountry = country;
        clear();
        setupData();
    }

    /* access modifiers changed from: protected */
    public void internalAdd(Object obj) {
        super.add(obj);
    }

    /* access modifiers changed from: protected */
    public void setupData() {
        if (!(this.mTopCountry == null || this.mCountries == null)) {
            this.mCountries.remove(this.mTopCountry);
        }
        if (this.mTopCountry != null) {
            super.add(this.mTopCountry);
        }
        if (this.mShowEverywhereCountry) {
            super.add(this.mEverywhereCountry);
        }
        if (this.mRegions != null) {
            Collections.sort(this.mRegions);
            super.addAll(this.mRegions);
        }
        if (this.mCountries != null) {
            Collections.sort(this.mCountries);
            super.addAll(this.mCountries);
        }
        this.mHeaderCache = new int[getCount()];
        notifyDataSetChanged();
    }

    public void setSelectedEntries(List<EditableShippingTemplateEntry> list) {
        if (list != null) {
            clearSelectedEntries();
            for (ShippingTemplateEntry shippingTemplateEntry : list) {
                if (shippingTemplateEntry.shipsEverywhere()) {
                    this.mDisableEverywhere = true;
                } else if (shippingTemplateEntry.isDestinationRegionSet()) {
                    this.mDisabledRegions.add(shippingTemplateEntry.getDestinationRegion());
                } else if (shippingTemplateEntry.isDestinationCountrySet()) {
                    this.mDisabledCountries.add(shippingTemplateEntry.getDestinationCountry());
                }
            }
            notifyDataSetChanged();
        }
    }

    public void clearSelectedEntries() {
        this.mDisableEverywhere = false;
        this.mDisabledCountries.clear();
        this.mDisabledRegions.clear();
    }

    public boolean isEnabled(int i) {
        Object item = getItem(i);
        if (item instanceof EverywhereCountry) {
            return !this.mDisableEverywhere;
        }
        if (item instanceof Country) {
            return !this.mDisabledCountries.contains(item);
        }
        if (item instanceof Region) {
            return !this.mDisabledRegions.contains(item);
        }
        return super.isEnabled(i);
    }

    public void add(Object obj) {
        throw new UnsupportedOperationException("This adapter is not configured to add or remove data after initialization");
    }

    public void addAll(Collection collection) {
        throw new UnsupportedOperationException("This adapter is not configured to add or remove data after initialization");
    }

    public void addAll(Object[] objArr) {
        throw new UnsupportedOperationException("This adapter is not configured to add or remove data after initialization");
    }

    public void remove(Object obj) {
        throw new UnsupportedOperationException("This adapter is not configured to add or remove data after initialization");
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!(viewGroup instanceof AbsSpinner)) {
            return buildView(i, view, viewGroup, this.mLeftAlignPrompt);
        }
        if (this.mSpinnerView == null) {
            this.mSpinnerView = LayoutInflater.from(getContext()).inflate(this.mResource, viewGroup, false);
        }
        Object item = getItem(i);
        if (item instanceof Region) {
            ((TextView) this.mSpinnerView.findViewById(16908308)).setText(((Region) item).getRegionName());
        } else if (item instanceof Country) {
            ((TextView) this.mSpinnerView.findViewById(16908308)).setText(((Country) item).getName());
        }
        return this.mSpinnerView;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        View buildView = buildView(i, view, viewGroup, false);
        buildView.setEnabled(isEnabled(i));
        buildView.setClickable(!isEnabled(i));
        buildView.findViewById(i.item_label).setEnabled(isEnabled(i));
        return buildView;
    }

    private View buildView(int i, View view, ViewGroup viewGroup, boolean z) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(k.list_item_with_header, viewGroup, false);
            aVar = a.a(view, z);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        Object item = getItem(u.a(i, 0, getCount() - 1));
        boolean z2 = item instanceof Country;
        if (z2) {
            bindCountryView(aVar, (Country) item);
        } else if (item instanceof Region) {
            bindRegionView(aVar, (Region) item);
        }
        aVar.b.setEnabled(isEnabled(i));
        if (shouldShowHeader(i)) {
            aVar.c.setVisibility(0);
            if (item instanceof Region) {
                aVar.c.setText(o.regions);
            } else if (z2) {
                if (this.mPrimaryIsoCodes.contains(((Country) item).getIsoCountryCode())) {
                    aVar.c.setText(o.countries);
                } else {
                    aVar.c.setText("");
                }
            }
        } else {
            aVar.c.setVisibility(8);
        }
        return view;
    }

    private boolean shouldShowHeader(int i) {
        switch (this.mHeaderCache[i]) {
            case 1:
                break;
            case 2:
                return false;
            default:
                if (i != 0) {
                    Object item = getItem(i);
                    Object item2 = getItem(i - 1);
                    if ((item instanceof Region) && (item2 instanceof Country)) {
                        this.mHeaderCache[i] = 1;
                        break;
                    } else {
                        boolean z = item instanceof Country;
                        if (!z || !(item2 instanceof Region)) {
                            if (!(item instanceof EverywhereCountry)) {
                                if (z && (item2 instanceof Country)) {
                                    if (this.mPrimaryIsoCodes.contains(((Country) item2).getIsoCountryCode()) && !this.mPrimaryIsoCodes.contains(((Country) item).getIsoCountryCode())) {
                                        this.mHeaderCache[i] = 1;
                                        break;
                                    } else {
                                        this.mHeaderCache[i] = 2;
                                        return false;
                                    }
                                } else {
                                    this.mHeaderCache[i] = 2;
                                    return false;
                                }
                            } else {
                                this.mHeaderCache[i] = 2;
                                return false;
                            }
                        } else {
                            this.mHeaderCache[i] = 1;
                            break;
                        }
                    }
                } else if (getItem(i) instanceof Region) {
                    this.mHeaderCache[i] = 1;
                    break;
                } else {
                    this.mHeaderCache[i] = 2;
                    return false;
                }
                break;
        }
        return true;
    }

    private void bindRegionView(a aVar, Region region) {
        aVar.b.setText(region.getRegionName());
    }

    private void bindCountryView(a aVar, Country country) {
        aVar.b.setText(country.getName());
    }

    public void showEverywhereCountry(boolean z) {
        this.mShowEverywhereCountry = z;
        clear();
        setupData();
    }
}
