package com.etsy.android.uikit.view.shop.policies;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.apiv3.StructuredShopShipping;
import com.etsy.android.lib.models.apiv3.StructuredShopShippingEstimate;
import com.etsy.android.lib.util.CountryUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StructuredShopShippingView extends StructuredShopPoliciesView {
    private Country mCountryFilter;
    private StructuredShopShipping mShippingPolicy;
    private boolean mShowProcessingTimeSection;
    private View mSpacer1;
    private View mSpacer2;
    private TextView mTxtCustomsFees;
    private TextView mTxtProcessingTime;
    private TextView mTxtShippingDisclaimer;
    private TextView mTxtShippingTime;
    private TextView mTxtSubheadCustomsFees;
    private TextView mTxtSubheadProcessingTime;
    private TextView mTxtSubheadShippingTime;

    public StructuredShopShippingView(Context context) {
        super(context);
    }

    public StructuredShopShippingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StructuredShopShippingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public StructuredShopShippingView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, LinearLayout linearLayout) {
        inflate(context, k.view_structured_shop_shipping, linearLayout);
        this.mTxtSubheadProcessingTime = (TextView) findViewById(i.txt_subhead_processing_time);
        this.mTxtProcessingTime = (TextView) findViewById(i.txt_processing_time);
        this.mSpacer1 = findViewById(i.spacer_1);
        this.mTxtSubheadShippingTime = (TextView) findViewById(i.txt_subhead_shipping_time);
        this.mTxtShippingTime = (TextView) findViewById(i.txt_shipping_time);
        this.mTxtShippingDisclaimer = (TextView) findViewById(i.txt_shipping_disclaimer);
        this.mSpacer2 = findViewById(i.spacer_2);
        this.mTxtSubheadCustomsFees = (TextView) findViewById(i.txt_subhead_customs_fees);
        this.mTxtCustomsFees = (TextView) findViewById(i.txt_customs_fees);
    }

    public void setStructuredShopShipping(@NonNull StructuredShopShipping structuredShopShipping, boolean z) {
        this.mShippingPolicy = structuredShopShipping;
        this.mShowProcessingTimeSection = z;
        setupView(this.mShippingPolicy, this.mShowProcessingTimeSection);
    }

    public void setupView(@NonNull StructuredShopShipping structuredShopShipping, boolean z) {
        Resources resources = getContext().getResources();
        int i = 8;
        boolean z2 = false;
        if (z) {
            this.mTxtSubheadProcessingTime.setVisibility(0);
            this.mTxtProcessingTime.setVisibility(0);
            this.mTxtProcessingTime.setText(structuredShopShipping.getProcessingTimeText());
        } else {
            this.mTxtSubheadProcessingTime.setVisibility(8);
            this.mTxtProcessingTime.setVisibility(8);
        }
        List<StructuredShopShippingEstimate> estimates = structuredShopShipping.getEstimates();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        List arrayList = new ArrayList();
        if (this.mCountryFilter != null) {
            String a = CountryUtil.a(this.mCountryFilter.getCountryId());
            Iterator it = estimates.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                StructuredShopShippingEstimate structuredShopShippingEstimate = (StructuredShopShippingEstimate) it.next();
                if (structuredShopShippingEstimate.getCountryId() == null || structuredShopShippingEstimate.getCountryId().intValue() != this.mCountryFilter.getCountryId()) {
                    if (a != null && a.equals(structuredShopShippingEstimate.getRegionCode())) {
                        if (structuredShopShippingEstimate.isSet()) {
                            arrayList.add(structuredShopShippingEstimate);
                        }
                    }
                } else if (structuredShopShippingEstimate.isSet()) {
                    arrayList.add(structuredShopShippingEstimate);
                }
            }
        }
        if (!arrayList.isEmpty()) {
            estimates = arrayList;
        }
        int i2 = 0;
        for (StructuredShopShippingEstimate structuredShopShippingEstimate2 : estimates) {
            if (structuredShopShippingEstimate2.isSet()) {
                i2++;
                String string = getResources().getString(StructuredShopShippingEstimate.UNIT_WEEKS.equals(structuredShopShippingEstimate2.getUnit()) ? o.structured_shipping_time_range_weeks : o.structured_shipping_time_range_business_days, new Object[]{Integer.valueOf(structuredShopShippingEstimate2.getMin()), Integer.valueOf(structuredShopShippingEstimate2.getMax())});
                SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder();
                if (spannableStringBuilder.length() != 0) {
                    spannableStringBuilder2.append("\n");
                }
                spannableStringBuilder2.append(structuredShopShippingEstimate2.getDisplayName()).append(": ").append(string).setSpan(new ForegroundColorSpan(resources.getColor(e.dark_grey)), spannableStringBuilder2.toString().indexOf(string), spannableStringBuilder2.length(), 33);
                spannableStringBuilder.append(spannableStringBuilder2);
            }
        }
        boolean z3 = i2 > 0;
        if (z3) {
            this.mTxtSubheadShippingTime.setVisibility(0);
            this.mTxtShippingTime.setVisibility(0);
            this.mTxtShippingTime.setText(spannableStringBuilder);
            this.mTxtShippingDisclaimer.setVisibility(0);
        } else {
            this.mTxtSubheadShippingTime.setVisibility(8);
            this.mTxtShippingTime.setVisibility(8);
            this.mTxtShippingDisclaimer.setVisibility(8);
        }
        boolean shipsInternational = structuredShopShipping.shipsInternational();
        if (!shipsInternational) {
            this.mTxtSubheadCustomsFees.setVisibility(8);
            this.mTxtCustomsFees.setVisibility(8);
        } else {
            this.mTxtSubheadCustomsFees.setVisibility(0);
            this.mTxtCustomsFees.setVisibility(0);
        }
        this.mSpacer1.setVisibility((!z || (!z3 && !shipsInternational)) ? 8 : 0);
        View view = this.mSpacer2;
        if (z3 && shipsInternational) {
            i = 0;
        }
        view.setVisibility(i);
        if (z3 || shipsInternational) {
            z2 = true;
        }
        setContentCollapsible(z2);
    }

    public void filterEstimates(@Nullable Country country) {
        this.mCountryFilter = country;
        if (this.mShippingPolicy != null) {
            setupView(this.mShippingPolicy, this.mShowProcessingTimeSection);
        }
    }
}
