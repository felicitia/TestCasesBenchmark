package com.contextlogic.wish.activity.cart.shipping;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.util.AddressUtil;
import com.contextlogic.wish.util.AddressUtil.Country;
import com.contextlogic.wish.util.KeyboardUtil;
import com.contextlogic.wish.util.ViewUtil;
import java.util.ArrayList;

public class ShippingAddressFormView extends LinearLayout {
    private EditText mAptSuiteText;
    private EditText mCityText;
    /* access modifiers changed from: private */
    public ArrayList<Country> mCountries;
    private Spinner mCountrySpinner;
    private String mCurrentCountryCode;
    private ArrayList<String> mCurrentStates;
    private EntryCompletedCallback mEntryCompletedCallback;
    protected EditText mFirstNameText;
    protected EditText mLastNameText;
    protected EditText mNameText;
    private EditText mPhoneText;
    private TextView mStateProvinceLabel;
    private EditText mStateProvinceText;
    private Spinner mStateSpinner;
    private EditText mStreetAddressText;
    private EditText mZipPostalText;

    public interface EntryCompletedCallback {
        void onEntryCompletion();
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.shipping_address_form;
    }

    public ShippingAddressFormView(Context context) {
        super(context);
        init();
    }

    public ShippingAddressFormView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ShippingAddressFormView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void populateCountry(String str) {
        if (str != null) {
            int i = 0;
            while (true) {
                if (i >= this.mCountries.size()) {
                    i = -1;
                    break;
                } else if (((Country) this.mCountries.get(i)).getCode().equals(str)) {
                    break;
                } else {
                    i++;
                }
            }
            if (i != -1) {
                this.mCountrySpinner.setSelection(i);
                handleCountrySelected(i);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        setOrientation(1);
        this.mCountries = AddressUtil.getSortedCountries();
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(getLayoutId(), this);
        this.mNameText = (EditText) inflate.findViewById(R.id.shipping_address_form_full_name_text);
        this.mFirstNameText = (EditText) inflate.findViewById(R.id.shipping_address_form_first_name_text);
        this.mLastNameText = (EditText) inflate.findViewById(R.id.shipping_address_form_last_name_text);
        this.mStreetAddressText = (EditText) inflate.findViewById(R.id.shipping_address_form_street_address_text);
        this.mAptSuiteText = (EditText) inflate.findViewById(R.id.shipping_address_form_apt_suite_text);
        this.mCityText = (EditText) inflate.findViewById(R.id.shipping_address_form_city_text);
        this.mStateProvinceText = (EditText) inflate.findViewById(R.id.shipping_address_form_state_text);
        this.mStateProvinceLabel = (TextView) inflate.findViewById(R.id.shipping_address_form_state_label);
        this.mZipPostalText = (EditText) inflate.findViewById(R.id.shipping_address_form_zip_postal_text);
        this.mCountrySpinner = (Spinner) inflate.findViewById(R.id.shipping_address_form_country_spinner);
        this.mStateSpinner = (Spinner) inflate.findViewById(R.id.shipping_address_form_state_spinner);
        this.mPhoneText = (EditText) inflate.findViewById(R.id.shipping_address_form_phone_text);
        this.mPhoneText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                KeyboardUtil.hideKeyboard((View) ShippingAddressFormView.this);
                ShippingAddressFormView.this.handleDone();
                return true;
            }
        });
        this.mFirstNameText.setVisibility(0);
        this.mLastNameText.setVisibility(0);
        this.mNameText.setVisibility(8);
        this.mCountrySpinner.setAdapter(new ArrayAdapter<Country>(getContext(), R.layout.spinner_item, this.mCountries) {

            /* renamed from: com.contextlogic.wish.activity.cart.shipping.ShippingAddressFormView$2$ViewHolder */
            class ViewHolder {
                TextView textView;

                ViewHolder() {
                }
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2;
                ViewHolder viewHolder;
                Country country = (Country) ShippingAddressFormView.this.mCountries.get(i);
                if (view == null) {
                    viewHolder = new ViewHolder();
                    view2 = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, viewGroup, false);
                    viewHolder.textView = (TextView) view2.findViewById(16908308);
                    view2.setTag(viewHolder);
                } else {
                    view2 = view;
                    viewHolder = (ViewHolder) view.getTag();
                }
                viewHolder.textView.setText(country.getName());
                return view2;
            }

            public View getDropDownView(int i, View view, ViewGroup viewGroup) {
                View view2;
                ViewHolder viewHolder;
                Country country = (Country) ShippingAddressFormView.this.mCountries.get(i);
                if (view == null) {
                    viewHolder = new ViewHolder();
                    view2 = LayoutInflater.from(getContext()).inflate(17367049, viewGroup, false);
                    viewHolder.textView = (TextView) view2.findViewById(16908308);
                    view2.setTag(viewHolder);
                } else {
                    view2 = view;
                    viewHolder = (ViewHolder) view.getTag();
                }
                viewHolder.textView.setText(country.getName());
                return view2;
            }
        });
        selectUserCountry();
        this.mCountrySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ShippingAddressFormView.this.handleCountrySelected(i);
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:9:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void selectUserCountry() {
        /*
            r4 = this;
            com.contextlogic.wish.api.datacenter.ProfileDataCenter r0 = com.contextlogic.wish.api.datacenter.ProfileDataCenter.getInstance()
            java.lang.String r0 = r0.getCountryCode()
            r1 = 0
            if (r0 == 0) goto L_0x0032
            java.lang.String r2 = r0.toUpperCase()
            java.lang.String r2 = com.contextlogic.wish.util.AddressUtil.getCountryName(r2)
            if (r2 == 0) goto L_0x0032
            com.contextlogic.wish.util.AddressUtil$Country r3 = new com.contextlogic.wish.util.AddressUtil$Country
            java.lang.String r0 = r0.toUpperCase()
            r3.<init>(r2, r0)
            java.util.ArrayList<com.contextlogic.wish.util.AddressUtil$Country> r0 = r4.mCountries
            int r0 = r0.indexOf(r3)
            int r0 = java.lang.Math.max(r1, r0)
            android.widget.Spinner r2 = r4.mCountrySpinner
            r2.setSelection(r0)
            r4.handleCountrySelected(r0)
            r0 = 1
            goto L_0x0033
        L_0x0032:
            r0 = 0
        L_0x0033:
            if (r0 != 0) goto L_0x003d
            android.widget.Spinner r0 = r4.mCountrySpinner
            r0.setSelection(r1)
            r4.handleCountrySelected(r1)
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.cart.shipping.ShippingAddressFormView.selectUserCountry():void");
    }

    public void clearAddress() {
        this.mNameText.setText("");
        this.mFirstNameText.setText("");
        this.mLastNameText.setText("");
        this.mStreetAddressText.setText("");
        this.mAptSuiteText.setText("");
        this.mCityText.setText("");
        selectUserCountry();
        this.mStateProvinceText.setText("");
        this.mStateSpinner.setSelection(0);
        this.mZipPostalText.setText("");
        this.mPhoneText.setText("");
    }

    public void prefillNameFromProfile() {
        this.mNameText.setText(ProfileDataCenter.getInstance().getName());
        this.mFirstNameText.setText(ProfileDataCenter.getInstance().getFirstName());
        this.mLastNameText.setText(ProfileDataCenter.getInstance().getLastName());
    }

    public void prefillAddress(WishShippingInfo wishShippingInfo) {
        if (wishShippingInfo.getName() != null) {
            this.mNameText.setText(wishShippingInfo.getName());
            String trim = wishShippingInfo.getName().trim();
            String str = "";
            int lastIndexOf = trim.lastIndexOf(" ");
            if (lastIndexOf != -1) {
                String substring = trim.substring(0, lastIndexOf);
                str = trim.substring(lastIndexOf + 1);
                trim = substring;
            }
            this.mFirstNameText.setText(trim);
            this.mLastNameText.setText(str);
        }
        if (wishShippingInfo.getStreetAddressLineOne() != null) {
            this.mStreetAddressText.setText(wishShippingInfo.getStreetAddressLineOne());
        }
        if (wishShippingInfo.getStreetAddressLineTwo() != null) {
            this.mAptSuiteText.setText(wishShippingInfo.getStreetAddressLineTwo());
        } else {
            this.mAptSuiteText.setText("");
        }
        if (wishShippingInfo.getCity() != null) {
            this.mCityText.setText(wishShippingInfo.getCity());
        }
        String countryCode = wishShippingInfo.getCountryCode();
        if (countryCode != null) {
            String countryName = AddressUtil.getCountryName(countryCode.toUpperCase());
            if (countryName != null) {
                int max = Math.max(0, this.mCountries.indexOf(new Country(countryName, countryCode.toUpperCase())));
                this.mCountrySpinner.setSelection(max);
                handleCountrySelected(max);
            }
        }
        if (wishShippingInfo.getState() != null) {
            this.mStateProvinceText.setText(wishShippingInfo.getState());
            if (this.mStateSpinner.getVisibility() == 0 && this.mCurrentStates != null) {
                this.mStateSpinner.setSelection(Math.max(0, this.mCurrentStates.indexOf(wishShippingInfo.getState())));
            }
        }
        if (wishShippingInfo.getZipCode() != null) {
            this.mZipPostalText.setText(wishShippingInfo.getZipCode());
        }
        if (wishShippingInfo.getPhoneNumber() != null) {
            this.mPhoneText.setText(wishShippingInfo.getPhoneNumber());
        }
    }

    /* access modifiers changed from: private */
    public void handleCountrySelected(int i) {
        String code = ((Country) this.mCountries.get(i)).getCode();
        if (this.mCurrentCountryCode == null || !code.equals(this.mCurrentCountryCode)) {
            this.mCurrentCountryCode = code;
            if (AddressUtil.getStateMapping() == null || AddressUtil.getStates(code) == null) {
                if (this.mStateProvinceLabel != null) {
                    this.mStateProvinceLabel.setText(R.string.state);
                }
                this.mStateProvinceText.setVisibility(0);
                this.mStateSpinner.setVisibility(8);
                this.mStateProvinceText.setText("");
                ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item, new ArrayList());
                arrayAdapter.setDropDownViewResource(17367049);
                this.mStateSpinner.setAdapter(arrayAdapter);
            } else {
                if (this.mStateProvinceLabel != null) {
                    this.mStateProvinceLabel.setText(R.string.state_asterisk);
                }
                this.mStateProvinceText.setVisibility(8);
                this.mStateSpinner.setVisibility(0);
                this.mStateProvinceText.setText("");
                this.mCurrentStates = new ArrayList<>(AddressUtil.getStates(code));
                String subdivisionNameForCountry = AddressUtil.getSubdivisionNameForCountry(code);
                if (subdivisionNameForCountry == null) {
                    subdivisionNameForCountry = getContext().getString(R.string.state);
                }
                ArrayList<String> arrayList = this.mCurrentStates;
                StringBuilder sb = new StringBuilder();
                sb.append(subdivisionNameForCountry);
                sb.append("*");
                arrayList.add(0, sb.toString());
                ArrayAdapter arrayAdapter2 = new ArrayAdapter(getContext(), R.layout.spinner_item, this.mCurrentStates);
                arrayAdapter2.setDropDownViewResource(17367049);
                this.mStateSpinner.setAdapter(arrayAdapter2);
            }
        }
    }

    public ArrayList<String> getMissingFields() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (areNameFieldsVisible()) {
            if (nameFieldsAreSplit()) {
                if (ViewUtil.extractEditTextValue(this.mFirstNameText) == null) {
                    arrayList.add("first_name");
                }
                if (ViewUtil.extractEditTextValue(this.mLastNameText) == null) {
                    arrayList.add("last_name");
                }
            } else if (ViewUtil.extractEditTextValue(this.mNameText) == null) {
                arrayList.add("name");
            }
        }
        if (ViewUtil.extractEditTextValue(this.mStreetAddressText) == null) {
            arrayList.add("address_line_1");
        }
        if (ViewUtil.extractEditTextValue(this.mCityText) == null) {
            arrayList.add("city");
        }
        if (ViewUtil.extractEditTextValue(this.mZipPostalText) == null) {
            arrayList.add("zip");
        }
        if (ViewUtil.extractEditTextValue(this.mPhoneText) == null) {
            arrayList.add("phone_number");
        }
        if (this.mStateSpinner.getVisibility() == 0 && this.mStateSpinner.getSelectedItemPosition() == 0) {
            arrayList.add("state");
        }
        return arrayList;
    }

    public WishShippingInfo getEnteredShippingAddress() {
        WishShippingInfo wishShippingInfo = new WishShippingInfo();
        if (nameFieldsAreSplit()) {
            if (!(ViewUtil.extractEditTextValue(this.mFirstNameText) == null || ViewUtil.extractEditTextValue(this.mLastNameText) == null)) {
                wishShippingInfo.setName(ViewUtil.extractEditTextValue(this.mFirstNameText), ViewUtil.extractEditTextValue(this.mLastNameText));
            }
        } else if (ViewUtil.extractEditTextValue(this.mNameText) != null) {
            wishShippingInfo.setName(ViewUtil.extractEditTextValue(this.mNameText));
        }
        if (ViewUtil.extractEditTextValue(this.mStreetAddressText) != null) {
            wishShippingInfo.setStreetAddressLineOne(ViewUtil.extractEditTextValue(this.mStreetAddressText));
        }
        if (ViewUtil.extractEditTextValue(this.mAptSuiteText) != null) {
            wishShippingInfo.setStreetAddressLineTwo(ViewUtil.extractEditTextValue(this.mAptSuiteText));
        }
        if (ViewUtil.extractEditTextValue(this.mCityText) != null) {
            wishShippingInfo.setCity(ViewUtil.extractEditTextValue(this.mCityText));
        }
        if (ViewUtil.extractEditTextValue(this.mZipPostalText) != null) {
            wishShippingInfo.setZipCode(ViewUtil.extractEditTextValue(this.mZipPostalText));
        }
        if (ViewUtil.extractEditTextValue(this.mPhoneText) != null) {
            wishShippingInfo.setPhoneNumber(ViewUtil.extractEditTextValue(this.mPhoneText));
        }
        if (this.mStateSpinner.getVisibility() == 0) {
            if (this.mStateSpinner.getSelectedItemPosition() != 0) {
                wishShippingInfo.setState((String) this.mCurrentStates.get(Math.max(0, this.mStateSpinner.getSelectedItemPosition())));
            }
        } else if (ViewUtil.extractEditTextValue(this.mStateProvinceText) != null) {
            wishShippingInfo.setState(ViewUtil.extractEditTextValue(this.mStateProvinceText));
        }
        wishShippingInfo.setCountryCode(((Country) this.mCountries.get(Math.max(0, this.mCountrySpinner.getSelectedItemPosition()))).getCode());
        return wishShippingInfo;
    }

    /* access modifiers changed from: private */
    public void handleDone() {
        if (this.mEntryCompletedCallback != null) {
            this.mEntryCompletedCallback.onEntryCompletion();
        }
    }

    public void setEntryCompletedCallback(EntryCompletedCallback entryCompletedCallback) {
        this.mEntryCompletedCallback = entryCompletedCallback;
    }

    public void requestKeyboardFocus() {
        if (nameFieldsAreSplit()) {
            KeyboardUtil.requestKeyboardFocus(this.mFirstNameText);
        } else {
            KeyboardUtil.requestKeyboardFocus(this.mNameText);
        }
    }

    public void setupSingleNameField() {
        this.mFirstNameText.setVisibility(8);
        this.mLastNameText.setVisibility(8);
        this.mNameText.setVisibility(0);
    }

    private boolean nameFieldsAreSplit() {
        return this.mFirstNameText.getVisibility() == 0 && this.mLastNameText.getVisibility() == 0 && this.mNameText.getVisibility() == 8;
    }

    public void hideNameFields() {
        this.mFirstNameText.setVisibility(8);
        this.mLastNameText.setVisibility(8);
        this.mNameText.setVisibility(8);
    }

    public boolean areNameFieldsVisible() {
        return this.mFirstNameText.getVisibility() == 0 || this.mLastNameText.getVisibility() == 0 || this.mNameText.getVisibility() == 0;
    }

    public boolean isFormEmpty() {
        return TextUtils.isEmpty(this.mNameText.getText()) && TextUtils.isEmpty(this.mFirstNameText.getText()) && TextUtils.isEmpty(this.mLastNameText.getText()) && TextUtils.isEmpty(this.mStreetAddressText.getText()) && TextUtils.isEmpty(this.mAptSuiteText.getText()) && TextUtils.isEmpty(this.mCityText.getText()) && TextUtils.isEmpty(this.mStateProvinceText.getText()) && TextUtils.isEmpty(this.mZipPostalText.getText()) && TextUtils.isEmpty(this.mPhoneText.getText());
    }
}
