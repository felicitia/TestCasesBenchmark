package com.etsy.android.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout.LayoutParams;
import com.etsy.android.R;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.util.CountryUtil;
import com.etsy.android.lib.util.CountryUtil.EverywhereCountry;
import com.etsy.android.lib.util.CountryUtil.a;
import com.etsy.android.lib.util.CountryUtil.b;
import com.etsy.android.lib.util.CountryUtil.c;
import com.etsy.android.lib.util.aj;
import com.etsy.android.ui.EtsyCommonListFragment;
import com.etsy.android.uikit.adapter.CountriesAdapter;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.parceler.d;

public class CountryDialogFragment extends EtsyCommonListFragment implements OnItemClickListener, a {
    private static final String COUNTRIES = "countries";
    private static final String ENABLED_COUNTRIES = "enabled_countries";
    private ArrayList<Country> mCountries;
    private CountriesAdapter mCountriesAdapter;
    private int mDividerCountryPosition;
    private ArrayList<Country> mEnabledCountries;
    private b mListener;
    private IDialogFragment mParentDialog;

    public static CountryDialogFragment newInstance(b bVar, ArrayList<Country> arrayList) {
        CountryDialogFragment countryDialogFragment = new CountryDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("countries", d.a(arrayList));
        countryDialogFragment.setArguments(bundle);
        countryDialogFragment.setDialogSelectedListener(bVar);
        return countryDialogFragment;
    }

    public static CountryDialogFragment newInstance(b bVar, ArrayList<Country> arrayList, ArrayList<Country> arrayList2) {
        CountryDialogFragment countryDialogFragment = new CountryDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("countries", d.a(arrayList));
        bundle.putParcelable(ENABLED_COUNTRIES, d.a(arrayList2));
        countryDialogFragment.setArguments(bundle);
        countryDialogFragment.setDialogSelectedListener(bVar);
        return countryDialogFragment;
    }

    private void setDialogSelectedListener(b bVar) {
        this.mListener = bVar;
    }

    public void setCountries(ArrayList<Country> arrayList) {
        setDialogCountries(arrayList);
    }

    public void setEnabledCountries(ArrayList<Country> arrayList) {
        this.mEnabledCountries = arrayList;
        setDialogCountries(this.mCountries);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCountries((ArrayList) d.a(getArguments().getParcelable("countries")));
        setEnabledCountries((ArrayList) d.a(getArguments().getParcelable(ENABLED_COUNTRIES)));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.mCountries == null) {
            setDialogCountries(CountryUtil.d());
        }
        if (this.mCountries.size() == 0) {
            getRequestQueue().a((g<Result>) new c<Result>(this));
            showLoadingView();
        } else if (this.mCountries.size() == 1 && (this.mCountries.get(0) instanceof EverywhereCountry)) {
            if (CountryUtil.a()) {
                this.mCountries.addAll(CountryUtil.d());
                setDialogCountries(this.mCountries);
            } else {
                getRequestQueue().a((g<Result>) new c<Result>(this));
                showLoadingView();
            }
        }
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mParentDialog = (IDialogFragment) getParentFragment();
        this.mParentDialog.setWindowMode(WindowMode.WRAP);
        CountriesAdapter countriesAdapter = new CountriesAdapter(getActivity(), getAnalyticsContext(), this.mCountries, this.mDividerCountryPosition, this.mEnabledCountries);
        this.mCountriesAdapter = countriesAdapter;
        this.mListView.setAdapter(this.mCountriesAdapter);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setDivider(getResources().getDrawable(R.drawable.list_divider_padded));
        this.mListView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.divider_height));
        this.mListView.setLayoutParams(new LayoutParams(-1, -2));
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.mListener.a((Country) this.mCountriesAdapter.getItem(i));
        this.mParentDialog.dismissAllowingStateLoss();
    }

    public void onCountriesLoaded(ArrayList<Country> arrayList) {
        setDialogCountries(arrayList);
        if (getConfigMap().c(com.etsy.android.lib.config.b.j)) {
            this.mCountriesAdapter.clear();
        }
        this.mCountriesAdapter.addAll((Collection<? extends T>) this.mCountries);
        this.mCountriesAdapter.notifyDataSetChanged();
        showListView();
    }

    public void onCountriesError() {
        if (this.mActivity != null) {
            aj.b(this.mActivity.getApplicationContext(), (int) R.string.country_whoops);
        }
        if (this.mParentDialog != null && !this.mParentDialog.isDetached()) {
            this.mParentDialog.dismissAllowingStateLoss();
        }
    }

    private void setDialogCountries(ArrayList<Country> arrayList) {
        if (getConfigMap().c(com.etsy.android.lib.config.b.j) && arrayList != null) {
            Collections.sort(arrayList);
            this.mDividerCountryPosition = CountryUtil.a(arrayList);
            if (this.mCountriesAdapter != null) {
                this.mCountriesAdapter.setDividerCountryPosition(this.mDividerCountryPosition);
                this.mCountriesAdapter.setEnabledCountries(this.mEnabledCountries);
            }
        }
        this.mCountries = arrayList;
    }
}
