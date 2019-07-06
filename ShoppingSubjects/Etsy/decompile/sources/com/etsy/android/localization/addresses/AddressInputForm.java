package com.etsy.android.localization.addresses;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import com.etsy.android.localization.addresses.e.c;
import com.etsy.android.localization.addresses.e.d;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class AddressInputForm extends LinearLayout {
    private a address;
    /* access modifiers changed from: private */
    public a addressFieldChangedListener;
    private CountriesAdapter countriesAdapter;
    private Spinner countriesSpinner;
    private ArrayList<LinearLayout> fieldLineViews = new ArrayList<>();
    private Map<AddressFieldType, AddressFieldView> fieldViews = new HashMap();
    private ArrayList<AddressFieldType> hiddenFields = new ArrayList<>();
    private int maxTextFieldLength = 50;
    private int maxZipFieldLength = 20;
    private Button saveButton;
    private OnClickListener saveClickListener;
    private boolean showSaveButton = true;
    private Map<AddressFieldType, Boolean> validFields = new HashMap();

    public interface a {
        void a(AddressFieldType addressFieldType, String str);
    }

    public AddressInputForm(Context context) {
        super(context);
        init(context, null);
    }

    public AddressInputForm(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public AddressInputForm(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    @RequiresApi(api = 21)
    public AddressInputForm(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.address = new a();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.AddressInputForm, 0, 0);
            this.showSaveButton = obtainStyledAttributes.getBoolean(d.AddressInputForm_showSaveButton, true);
            this.maxTextFieldLength = obtainStyledAttributes.getInt(d.AddressInputForm_maxTextFieldLength, this.maxTextFieldLength);
            this.maxZipFieldLength = obtainStyledAttributes.getInt(d.AddressInputForm_maxZipFieldLength, this.maxZipFieldLength);
            obtainStyledAttributes.recycle();
        }
        setOrientation(1);
        displayForm();
    }

    public void hideFields(AddressFieldType... addressFieldTypeArr) {
        for (AddressFieldType add : addressFieldTypeArr) {
            this.hiddenFields.add(add);
        }
    }

    public void buildForm(String str) {
        addCountrySpinner(str);
        d addressFormatterData = getAddressFormatterData();
        String[] fieldLines = getFieldLines(addressFormatterData);
        createMissingFieldLineViews(fieldLines.length);
        setupFieldLineViewsForReUse();
        for (int i = 0; i < fieldLines.length; i++) {
            String str2 = fieldLines[i];
            LinearLayout fieldLineView = getFieldLineView(i);
            for (String replace : str2.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
                AddressFieldType fromString = AddressFieldType.fromString(replace.replace("%", ""));
                AddressFieldView fieldView = getFieldView(fromString);
                TextInputEditText editTextView = fieldView.getEditTextView();
                editTextView.setHint(getFieldLabel(fromString, addressFormatterData));
                editTextView.setText(getFieldValue(fromString));
                fieldLineView.addView(fieldView);
                this.fieldViews.put(fromString, fieldView);
                setTextChangedListenerOnView(fromString);
            }
            addView(fieldLineView);
        }
        if (this.showSaveButton) {
            addSaveButton();
        }
    }

    private void createMissingFieldLineViews(int i) {
        int size = i - this.fieldLineViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(0);
            this.fieldLineViews.add(linearLayout);
        }
    }

    private void setupFieldLineViewsForReUse() {
        Iterator it = this.fieldLineViews.iterator();
        while (it.hasNext()) {
            ((LinearLayout) it.next()).removeAllViews();
        }
    }

    private LinearLayout getFieldLineView(int i) {
        return (LinearLayout) this.fieldLineViews.get(i);
    }

    private AddressFieldView getFieldView(AddressFieldType addressFieldType) {
        if (this.fieldViews.containsKey(addressFieldType)) {
            return (AddressFieldView) this.fieldViews.get(addressFieldType);
        }
        AddressFieldView addressFieldView = new AddressFieldView(getContext());
        this.fieldViews.put(addressFieldType, addressFieldView);
        return addressFieldView;
    }

    private String[] getFieldLines(d dVar) {
        String str = dVar.p;
        if (this.hiddenFields.size() > 0) {
            Iterator it = this.hiddenFields.iterator();
            while (it.hasNext()) {
                str = c.a(str, (AddressFieldType) it.next());
            }
        }
        return str.split("\\n");
    }

    private void addCountrySpinner(String str) {
        this.countriesSpinner = getCountriesSpinner();
        this.countriesAdapter = getCountriesAdapter();
        final int position = this.countriesAdapter.getPosition(str);
        this.countriesSpinner.setSelection(position);
        this.countriesSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (position != i) {
                    AddressInputForm.this.displayForm(AddressInputForm.this.getAddressFromViews());
                }
            }
        });
        addView(this.countriesSpinner);
    }

    private Spinner getCountriesSpinner() {
        if (this.countriesSpinner == null) {
            this.countriesSpinner = new Spinner(getContext());
            this.countriesAdapter = getCountriesAdapter();
            this.countriesSpinner.setAdapter(this.countriesAdapter);
        }
        return this.countriesSpinner;
    }

    private CountriesAdapter getCountriesAdapter() {
        if (this.countriesAdapter == null) {
            this.countriesAdapter = new CountriesAdapter(getContext());
        }
        return this.countriesAdapter;
    }

    private void addSaveButton() {
        if (this.saveButton == null) {
            this.saveButton = new Button(getContext());
            this.saveButton.setText(getResources().getString(c.label_save_button));
            if (this.saveClickListener != null) {
                setSaveOnClickListener();
            }
        }
        addView(this.saveButton);
    }

    private void setSaveOnClickListener() {
        if (this.saveButton != null) {
            this.saveButton.setOnClickListener(this.saveClickListener);
        }
    }

    public void setSaveClickListener(OnClickListener onClickListener) {
        this.saveClickListener = onClickListener;
        setSaveOnClickListener();
    }

    public Map<AddressFieldType, AddressFieldView> getFieldViews() {
        return this.fieldViews;
    }

    private void setTextChangedListenerOnView(final AddressFieldType addressFieldType) {
        EditText editText = getFieldView(addressFieldType).getEditText();
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void afterTextChanged(Editable editable) {
                    AddressInputForm.this.setFieldValidity(addressFieldType, Boolean.valueOf(editable.toString().length() <= AddressInputForm.this.getMaxFieldLength(addressFieldType)));
                    if (AddressInputForm.this.addressFieldChangedListener != null) {
                        AddressInputForm.this.addressFieldChangedListener.a(addressFieldType, editable.toString());
                    }
                }
            });
        }
    }

    public void setOnAddressFieldChanged(a aVar) {
        this.addressFieldChangedListener = aVar;
    }

    public void setMaxTextFieldLength(int i) {
        this.maxTextFieldLength = i;
    }

    public void setMaxZipFieldLength(int i) {
        this.maxZipFieldLength = i;
    }

    /* access modifiers changed from: private */
    public void setFieldValidity(AddressFieldType addressFieldType, Boolean bool) {
        this.validFields.put(addressFieldType, bool);
    }

    public Boolean isValidAddress() {
        return Boolean.valueOf(!this.validFields.values().contains(Boolean.valueOf(false)));
    }

    /* access modifiers changed from: private */
    public int getMaxFieldLength(AddressFieldType addressFieldType) {
        return addressFieldType.equals(AddressFieldType.ZIP) ? this.maxZipFieldLength : this.maxTextFieldLength;
    }

    public a getAddress() {
        setAddress(getAddressFromViews());
        return this.address;
    }

    /* access modifiers changed from: private */
    public a getAddressFromViews() {
        HashMap hashMap = new HashMap();
        hashMap.put(AddressFieldType.COUNTRY_NAME, this.countriesSpinner.getSelectedItem().toString());
        for (AddressFieldType addressFieldType : this.fieldViews.keySet()) {
            hashMap.put(addressFieldType, ((TextInputLayout) this.fieldViews.get(addressFieldType)).getEditText().getText().toString());
        }
        return new a(hashMap);
    }

    private d getAddressFormatterData() {
        return this.countriesAdapter.getItem(this.countriesSpinner.getSelectedItemPosition());
    }

    private String getFieldValue(AddressFieldType addressFieldType) {
        return this.address.a(addressFieldType);
    }

    public void setAddress(a aVar) {
        this.address = aVar;
        displayForm(this.address);
    }

    private void displayForm() {
        buildForm(Locale.getDefault().getDisplayCountry());
    }

    /* access modifiers changed from: private */
    public void displayForm(a aVar) {
        removeViews(0, getChildCount());
        buildForm(aVar.g);
    }

    private String getFieldLabel(AddressFieldType addressFieldType, d dVar) {
        String a2 = dVar.a(addressFieldType);
        if (a2 == null || a2.equals("")) {
            a2 = addressFieldType.toString();
        }
        return getResources().getString(getResourceReference(a2));
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getResourceReference(java.lang.String r4) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            switch(r0) {
                case -1630368479: goto L_0x0101;
                case -1473230347: goto L_0x00f6;
                case -1354575542: goto L_0x00eb;
                case -1179387371: goto L_0x00e0;
                case -1023312151: goto L_0x00d5;
                case -995420099: goto L_0x00ca;
                case -987485392: goto L_0x00bf;
                case -982450997: goto L_0x00b4;
                case -161037277: goto L_0x00aa;
                case 110997: goto L_0x009e;
                case 120609: goto L_0x0093;
                case 3002509: goto L_0x0087;
                case 3053931: goto L_0x007c;
                case 3373707: goto L_0x0071;
                case 95753866: goto L_0x0065;
                case 109757585: goto L_0x005a;
                case 265211103: goto L_0x004f;
                case 288961422: goto L_0x0044;
                case 352933202: goto L_0x0038;
                case 424210646: goto L_0x002d;
                case 848184146: goto L_0x0021;
                case 1903646917: goto L_0x0015;
                case 2002972369: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x010c
        L_0x0009:
            java.lang.String r0 = "post_town"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 8
            goto L_0x010d
        L_0x0015:
            java.lang.String r0 = "apt_suite_unit"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 22
            goto L_0x010d
        L_0x0021:
            java.lang.String r0 = "department"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 12
            goto L_0x010d
        L_0x002d:
            java.lang.String r0 = "second_zip"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 3
            goto L_0x010d
        L_0x0038:
            java.lang.String r0 = "address_line_2"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 21
            goto L_0x010d
        L_0x0044:
            java.lang.String r0 = "district"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 7
            goto L_0x010d
        L_0x004f:
            java.lang.String r0 = "second_line"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 2
            goto L_0x010d
        L_0x005a:
            java.lang.String r0 = "state"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 5
            goto L_0x010d
        L_0x0065:
            java.lang.String r0 = "do_si"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 11
            goto L_0x010d
        L_0x0071:
            java.lang.String r0 = "name"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 0
            goto L_0x010d
        L_0x007c:
            java.lang.String r0 = "city"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 4
            goto L_0x010d
        L_0x0087:
            java.lang.String r0 = "area"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 9
            goto L_0x010d
        L_0x0093:
            java.lang.String r0 = "zip"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 6
            goto L_0x010d
        L_0x009e:
            java.lang.String r0 = "pin"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 19
            goto L_0x010d
        L_0x00aa:
            java.lang.String r0 = "first_line"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 1
            goto L_0x010d
        L_0x00b4:
            java.lang.String r0 = "postal"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 20
            goto L_0x010d
        L_0x00bf:
            java.lang.String r0 = "province"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 18
            goto L_0x010d
        L_0x00ca:
            java.lang.String r0 = "parish"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 16
            goto L_0x010d
        L_0x00d5:
            java.lang.String r0 = "oblast"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 15
            goto L_0x010d
        L_0x00e0:
            java.lang.String r0 = "island"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 14
            goto L_0x010d
        L_0x00eb:
            java.lang.String r0 = "county"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 10
            goto L_0x010d
        L_0x00f6:
            java.lang.String r0 = "prefecture"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 17
            goto L_0x010d
        L_0x0101:
            java.lang.String r0 = "emirate"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x010c
            r0 = 13
            goto L_0x010d
        L_0x010c:
            r0 = -1
        L_0x010d:
            switch(r0) {
                case 0: goto L_0x016b;
                case 1: goto L_0x0168;
                case 2: goto L_0x0165;
                case 3: goto L_0x0162;
                case 4: goto L_0x015f;
                case 5: goto L_0x015c;
                case 6: goto L_0x0159;
                case 7: goto L_0x0156;
                case 8: goto L_0x0153;
                case 9: goto L_0x0150;
                case 10: goto L_0x014d;
                case 11: goto L_0x014a;
                case 12: goto L_0x0147;
                case 13: goto L_0x0144;
                case 14: goto L_0x0141;
                case 15: goto L_0x013e;
                case 16: goto L_0x013b;
                case 17: goto L_0x0138;
                case 18: goto L_0x0135;
                case 19: goto L_0x0132;
                case 20: goto L_0x012f;
                case 21: goto L_0x012c;
                case 22: goto L_0x0129;
                default: goto L_0x0110;
            }
        L_0x0110:
            java.lang.String r0 = "address_formatting"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "no resource for address field label: "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            android.util.Log.d(r0, r4)
            int r4 = com.etsy.android.localization.addresses.e.c.app_name
            return r4
        L_0x0129:
            int r4 = com.etsy.android.localization.addresses.e.c.label_apt_suite_unit
            return r4
        L_0x012c:
            int r4 = com.etsy.android.localization.addresses.e.c.label_address_line_2
            return r4
        L_0x012f:
            int r4 = com.etsy.android.localization.addresses.e.c.label_postal
            return r4
        L_0x0132:
            int r4 = com.etsy.android.localization.addresses.e.c.label_pin
            return r4
        L_0x0135:
            int r4 = com.etsy.android.localization.addresses.e.c.label_province
            return r4
        L_0x0138:
            int r4 = com.etsy.android.localization.addresses.e.c.label_prefecture
            return r4
        L_0x013b:
            int r4 = com.etsy.android.localization.addresses.e.c.label_parish
            return r4
        L_0x013e:
            int r4 = com.etsy.android.localization.addresses.e.c.label_oblast
            return r4
        L_0x0141:
            int r4 = com.etsy.android.localization.addresses.e.c.label_island
            return r4
        L_0x0144:
            int r4 = com.etsy.android.localization.addresses.e.c.label_emirate
            return r4
        L_0x0147:
            int r4 = com.etsy.android.localization.addresses.e.c.label_department
            return r4
        L_0x014a:
            int r4 = com.etsy.android.localization.addresses.e.c.label_do_si
            return r4
        L_0x014d:
            int r4 = com.etsy.android.localization.addresses.e.c.label_county
            return r4
        L_0x0150:
            int r4 = com.etsy.android.localization.addresses.e.c.label_area
            return r4
        L_0x0153:
            int r4 = com.etsy.android.localization.addresses.e.c.label_post_town
            return r4
        L_0x0156:
            int r4 = com.etsy.android.localization.addresses.e.c.label_district
            return r4
        L_0x0159:
            int r4 = com.etsy.android.localization.addresses.e.c.label_zip
            return r4
        L_0x015c:
            int r4 = com.etsy.android.localization.addresses.e.c.label_state
            return r4
        L_0x015f:
            int r4 = com.etsy.android.localization.addresses.e.c.label_city
            return r4
        L_0x0162:
            int r4 = com.etsy.android.localization.addresses.e.c.label_zip
            return r4
        L_0x0165:
            int r4 = com.etsy.android.localization.addresses.e.c.label_second_line
            return r4
        L_0x0168:
            int r4 = com.etsy.android.localization.addresses.e.c.label_first_line
            return r4
        L_0x016b:
            int r4 = com.etsy.android.localization.addresses.e.c.label_name
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.localization.addresses.AddressInputForm.getResourceReference(java.lang.String):int");
    }
}
