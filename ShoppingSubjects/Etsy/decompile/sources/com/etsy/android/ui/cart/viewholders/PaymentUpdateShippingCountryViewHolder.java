package com.etsy.android.ui.cart.viewholders;

import android.text.InputFilter;
import android.text.InputFilter.AllCaps;
import android.text.InputFilter.LengthFilter;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.etsy.android.R;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.PaymentUpdateShippingCountry;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.lib.util.CountryUtil;
import com.etsy.android.lib.util.s;
import com.etsy.android.ui.cart.a.a;
import com.etsy.android.ui.core.listingpanel.PostalCodeTextWatcher;
import com.etsy.android.ui.core.listingpanel.aa;
import com.etsy.android.ui.core.listingpanel.y;
import com.etsy.android.ui.core.listingpanel.z;
import com.etsy.android.uikit.EtsySpinnerArrayAdapterWithClickListener;
import java.util.ArrayList;

public class PaymentUpdateShippingCountryViewHolder extends BaseCartGroupItemViewHolder {
    private EtsySpinnerArrayAdapterWithClickListener<Country> mAdapter = new EtsySpinnerArrayAdapterWithClickListener<Country>(this.itemView.getContext(), this.mCountries) {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public String getText(Country country) {
            return country.getDisplayCountry();
        }
    };
    /* access modifiers changed from: private */
    public final Button mBtnCalcShipping = ((Button) findViewById(R.id.btn_calc_shipping));
    /* access modifiers changed from: private */
    public a mClickHandler;
    /* access modifiers changed from: private */
    public final Spinner mCountries = ((Spinner) findViewById(R.id.countries));
    private y mPostalCodePerCountrySettings;
    private PostalCodeTextWatcher mPostalCodeTextWatcher;
    private z mPostalCodeValidator;
    /* access modifiers changed from: private */
    public final TextView mShipToLabel = ((TextView) findViewById(R.id.ship_to_label));
    private EditText mTxtPostalCode = ((EditText) findViewById(R.id.txt_postal_code));

    public PaymentUpdateShippingCountryViewHolder(ViewGroup viewGroup, a aVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_update_shipping_country, viewGroup, false));
        this.mClickHandler = aVar;
    }

    private void removeZipTextWatcher() {
        if (this.mTxtPostalCode != null && this.mPostalCodeTextWatcher != null) {
            this.mTxtPostalCode.removeTextChangedListener(this.mPostalCodeTextWatcher);
            this.mPostalCodeTextWatcher = null;
        }
    }

    public void bindCartGroupItem(final CartGroupItem cartGroupItem) {
        this.mBtnCalcShipping.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PaymentUpdateShippingCountryViewHolder.this.mBtnCalcShipping.setVisibility(8);
                PaymentUpdateShippingCountryViewHolder.this.mShipToLabel.setVisibility(0);
                PaymentUpdateShippingCountryViewHolder.this.mCountries.setVisibility(0);
                Country country = (Country) PaymentUpdateShippingCountryViewHolder.this.mCountries.getSelectedItem();
                if (country != null) {
                    PaymentUpdateShippingCountryViewHolder.this.updatePostalCodeTextView(country.getIsoCountryCode());
                }
            }
        });
        PaymentUpdateShippingCountry paymentUpdateShippingCountry = (PaymentUpdateShippingCountry) cartGroupItem.getData();
        this.mTxtPostalCode.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 0) {
                    return false;
                }
                ServerDrivenAction action = cartGroupItem.getAction(ServerDrivenAction.TYPE_UPDATE_SHIPPING_DESTINATION);
                if (!(action == null || PaymentUpdateShippingCountryViewHolder.this.mClickHandler == null)) {
                    action.addParam(ResponseConstants.COUNTRY_ID, String.valueOf(((Country) PaymentUpdateShippingCountryViewHolder.this.mCountries.getSelectedItem()).getCountryId()));
                    action.addParam(ResponseConstants.ZIP, textView.getText().toString());
                    PaymentUpdateShippingCountryViewHolder.this.mClickHandler.c(PaymentUpdateShippingCountryViewHolder.this.getRootView(), action);
                }
                textView.clearFocus();
                s.a((View) textView);
                return true;
            }
        });
        ArrayList arrayList = new ArrayList();
        int a = CountryUtil.a(arrayList, paymentUpdateShippingCountry.getPreferredCountries(), paymentUpdateShippingCountry.getAllCountryIds(), paymentUpdateShippingCountry.getDestinationCountryId());
        final int destinationCountryId = paymentUpdateShippingCountry.getDestinationCountryId();
        Country country = (Country) arrayList.get(a);
        if (country != null) {
            this.mBtnCalcShipping.setVisibility(8);
            this.mShipToLabel.setVisibility(0);
            this.mCountries.setVisibility(0);
            updatePostalCodeTextView(country.getIsoCountryCode());
        }
        this.mTxtPostalCode.setText(paymentUpdateShippingCountry.getPostalCode());
        this.mAdapter.clear();
        this.mAdapter.addAll(arrayList);
        this.mAdapter.setOnItemClickListener(new EtsySpinnerArrayAdapterWithClickListener.a<Country>() {
            public void a(Country country) {
                if (PaymentUpdateShippingCountryViewHolder.this.mClickHandler != null && country != null && country.getCountryId() != -1) {
                    PaymentUpdateShippingCountryViewHolder.this.updatePostalCodeTextView(country.getIsoCountryCode());
                    if (!PaymentUpdateShippingCountryViewHolder.this.isPostalCodeRequired()) {
                        ServerDrivenAction action = cartGroupItem.getAction(ServerDrivenAction.TYPE_UPDATE_SHIPPING_DESTINATION);
                        if (action != null) {
                            action.addParam(ResponseConstants.COUNTRY_ID, String.valueOf(country.getCountryId()));
                            PaymentUpdateShippingCountryViewHolder.this.mClickHandler.c(PaymentUpdateShippingCountryViewHolder.this.getRootView(), action);
                        }
                    }
                }
            }

            public boolean b(Country country) {
                return country.getCountryId() != destinationCountryId;
            }
        });
        this.mCountries.setSelection(a);
    }

    /* access modifiers changed from: private */
    public boolean isPostalCodeRequired() {
        return (this.mPostalCodeValidator == null || this.mPostalCodePerCountrySettings == null) ? false : true;
    }

    /* access modifiers changed from: private */
    public void updatePostalCodeTextView(String str) {
        aa aaVar = new aa();
        this.mPostalCodeValidator = aaVar.a(str);
        this.mPostalCodePerCountrySettings = aaVar.b(str);
        if (this.mPostalCodePerCountrySettings != null) {
            this.mTxtPostalCode.setInputType(this.mPostalCodePerCountrySettings.b());
            this.mTxtPostalCode.setHint(this.mPostalCodePerCountrySettings.a());
        }
        int i = 0;
        if (this.mPostalCodeValidator != null) {
            this.mTxtPostalCode.setFilters(new InputFilter[]{new LengthFilter(this.mPostalCodeValidator.a()), new AllCaps()});
        }
        EditText editText = this.mTxtPostalCode;
        if (!isPostalCodeRequired()) {
            i = 8;
        }
        editText.setVisibility(i);
        removeZipTextWatcher();
        this.mPostalCodeTextWatcher = new PostalCodeTextWatcher(this.mTxtPostalCode);
        this.mPostalCodeTextWatcher.setMPostalCodeFormatter(aaVar.c(str));
        this.mTxtPostalCode.addTextChangedListener(this.mPostalCodeTextWatcher);
    }
}
