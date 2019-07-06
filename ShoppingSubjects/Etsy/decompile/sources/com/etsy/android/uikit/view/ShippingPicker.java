package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.Region;
import com.etsy.android.lib.models.apiv3.StructuredShopShippingEstimate;
import com.etsy.android.lib.util.CountryUtil;
import com.etsy.android.uikit.adapter.CountryRegionAdapter;
import com.etsy.android.uikit.view.SwitchToggle.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShippingPicker extends LinearLayout {
    /* access modifiers changed from: private */
    public AppCompatSpinner mCustomMax;
    /* access modifiers changed from: private */
    public ArrayAdapter<Integer> mCustomMaxAdapter;
    /* access modifiers changed from: private */
    public AppCompatSpinner mCustomMin;
    /* access modifiers changed from: private */
    public ArrayAdapter<Integer> mCustomMinAdapter;
    private ArrayList<Integer> mMaxCustomValues = new ArrayList<>(Arrays.asList(new Integer[]{Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(25), Integer.valueOf(26), Integer.valueOf(27), Integer.valueOf(28), Integer.valueOf(29), Integer.valueOf(30)}));
    private ArrayList<Integer> mMinCustomValues = new ArrayList<>(Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(25), Integer.valueOf(26), Integer.valueOf(27), Integer.valueOf(28), Integer.valueOf(29)}));
    private List<Region> mRegions;
    /* access modifiers changed from: private */
    public StructuredShopShippingEstimate mShippingEstimate;
    private AppCompatSpinner mSpinner;
    private SwitchToggle mUnitToggle;

    public ShippingPicker(Context context) {
        super(context);
        init();
    }

    public ShippingPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ShippingPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @TargetApi(21)
    public ShippingPicker(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        setOrientation(1);
        LayoutInflater.from(getContext()).inflate(k.custom_shipping_spinner, this, true);
        this.mSpinner = (AppCompatSpinner) findViewById(i.shipping_spinner);
        this.mCustomMin = (AppCompatSpinner) findViewById(i.min_spinner);
        this.mCustomMax = (AppCompatSpinner) findViewById(i.max_spinner);
        this.mUnitToggle = (SwitchToggle) findViewById(i.unit_toggle);
        this.mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                Object item = adapterView.getAdapter().getItem(i);
                if (item instanceof Country) {
                    Country country = (Country) item;
                    ShippingPicker.this.mShippingEstimate.setCountryId(country.getCountryId());
                    ShippingPicker.this.mShippingEstimate.setType("country");
                    ShippingPicker.this.mShippingEstimate.setDisplayName(country.getName());
                } else if (item instanceof Region) {
                    Region region = (Region) item;
                    ShippingPicker.this.mShippingEstimate.setRegionCode(region.getRegionCode());
                    ShippingPicker.this.mShippingEstimate.setType("region");
                    ShippingPicker.this.mShippingEstimate.setDisplayName(region.getRegionName());
                }
            }
        });
    }

    public void setEnabled(boolean z, List<StructuredShopShippingEstimate> list) {
        super.setEnabled(z);
        this.mSpinner.setEnabled(z);
        if (z) {
            setSpinnerAdapter(list);
        }
    }

    public void setShippingEstimate(StructuredShopShippingEstimate structuredShopShippingEstimate, List<Region> list) {
        this.mShippingEstimate = structuredShopShippingEstimate;
        if (!this.mShippingEstimate.isSet()) {
            this.mShippingEstimate.setMin(3);
            this.mShippingEstimate.setMax(5);
            this.mShippingEstimate.setUnit(StructuredShopShippingEstimate.UNIT_DAYS);
        }
        this.mCustomMinAdapter = setupCustomPickerAdapter(this.mShippingEstimate.getMin(), this.mCustomMin, this.mMinCustomValues);
        this.mCustomMaxAdapter = setupCustomPickerAdapter(this.mShippingEstimate.getMax(), this.mCustomMax, this.mMaxCustomValues);
        this.mUnitToggle.setChecked(StructuredShopShippingEstimate.UNIT_DAYS.equals(this.mShippingEstimate.getUnit()));
        this.mCustomMin.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                int intValue = ((Integer) ShippingPicker.this.mCustomMinAdapter.getItem(i)).intValue();
                ShippingPicker.this.mShippingEstimate.setMin(intValue);
                if (intValue >= ((Integer) ShippingPicker.this.mCustomMax.getSelectedItem()).intValue()) {
                    ShippingPicker.this.mCustomMax.setSelection(i);
                    ShippingPicker.this.mShippingEstimate.setMax(((Integer) ShippingPicker.this.mCustomMax.getSelectedItem()).intValue());
                }
            }
        });
        this.mCustomMax.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                int intValue = ((Integer) ShippingPicker.this.mCustomMaxAdapter.getItem(i)).intValue();
                ShippingPicker.this.mShippingEstimate.setMax(intValue);
                if (intValue <= ((Integer) ShippingPicker.this.mCustomMin.getSelectedItem()).intValue()) {
                    ShippingPicker.this.mCustomMin.setSelection(i);
                    ShippingPicker.this.mShippingEstimate.setMin(((Integer) ShippingPicker.this.mCustomMin.getSelectedItem()).intValue());
                }
            }
        });
        this.mUnitToggle.setOnCheckedChangeListener(new a() {
            public void a(SwitchToggle switchToggle, boolean z) {
                ShippingPicker.this.mShippingEstimate.setUnit(z ? StructuredShopShippingEstimate.UNIT_DAYS : StructuredShopShippingEstimate.UNIT_WEEKS);
            }
        });
        this.mRegions = list;
        setSpinnerAdapter();
    }

    private void setSpinnerAdapter() {
        setSpinnerAdapter(new ArrayList());
    }

    private void setSpinnerAdapter(@NonNull List<StructuredShopShippingEstimate> list) {
        Country country = this.mShippingEstimate.toCountry();
        Region region = this.mShippingEstimate.toRegion();
        ArrayList arrayList = new ArrayList(CountryUtil.d());
        ArrayList arrayList2 = new ArrayList(this.mRegions);
        for (StructuredShopShippingEstimate structuredShopShippingEstimate : list) {
            Region region2 = structuredShopShippingEstimate.toRegion();
            if (region2 != null && !region2.equals(region)) {
                arrayList2.remove(region2);
            }
            Country country2 = structuredShopShippingEstimate.toCountry();
            if (country2 != null && !country2.equals(country)) {
                arrayList.remove(country2);
            }
        }
        CountryRegionAdapter countryRegionAdapter = new CountryRegionAdapter(getContext(), (List<Country>) arrayList, (List<Region>) arrayList2, (Country) null, k.spinner_item_blue_large_no_padding);
        this.mSpinner.setAdapter((SpinnerAdapter) countryRegionAdapter);
        if (country != null) {
            this.mSpinner.setSelection(countryRegionAdapter.getPosition(country));
        } else if (region != null) {
            this.mSpinner.setSelection(countryRegionAdapter.getPosition(region));
        }
    }

    private ArrayAdapter<Integer> setupCustomPickerAdapter(int i, AppCompatSpinner appCompatSpinner, ArrayList<Integer> arrayList) {
        if (arrayList.indexOf(Integer.valueOf(i)) == -1) {
            arrayList.add(Integer.valueOf(i));
            Collections.sort(arrayList);
        }
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(getContext(), k.spinner_item_blue_large_no_padding, arrayList);
        arrayAdapter.setDropDownViewResource(k.spinner_dropdown_item);
        appCompatSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        appCompatSpinner.setSelection(arrayAdapter.getPosition(Integer.valueOf(i)));
        return arrayAdapter;
    }
}
