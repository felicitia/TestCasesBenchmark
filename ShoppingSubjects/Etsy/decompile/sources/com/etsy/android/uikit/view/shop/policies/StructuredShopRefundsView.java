package com.etsy.android.uikit.view.shop.policies;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.m;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.models.apiv3.NonRefundableItem;
import com.etsy.android.lib.models.apiv3.StructuredShopRefunds;
import com.etsy.android.uikit.view.shop.policies.StructuredShopPoliciesView.a;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.List;

public class StructuredShopRefundsView extends StructuredShopPoliciesView {
    private View mSpacer;
    private TextView mTxtCancelWithin;
    private TextView mTxtContactWithin;
    private TextView mTxtNonreturnableInfo;
    private TextView mTxtNonreturnableItems;
    private TextView mTxtNotAcceptedInfo;
    private TextView mTxtQuestionsInfo;
    private TextView mTxtReturnConditionsInfo;
    private TextView mTxtReturnWithin;
    private TextView mTxtSubheadAcceptedSummary;
    private TextView mTxtSubheadNonreturnable;
    private TextView mTxtSubheadNotAcceptedSummary;
    private TextView mTxtSubheadQuestions;
    private TextView mTxtSubheadReturnConditions;
    private TextView mUnstructuredText;
    private TextView mUnstructuredTextHeader;

    public StructuredShopRefundsView(Context context) {
        super(context);
    }

    public StructuredShopRefundsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StructuredShopRefundsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public StructuredShopRefundsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, LinearLayout linearLayout) {
        inflate(context, k.view_structured_shop_refunds, linearLayout);
        this.mTxtSubheadAcceptedSummary = (TextView) findViewById(i.txt_subhead_accepted_summary);
        this.mTxtContactWithin = (TextView) findViewById(i.txt_contact_within);
        this.mTxtReturnWithin = (TextView) findViewById(i.txt_return_within);
        this.mTxtCancelWithin = (TextView) findViewById(i.txt_cancel_within);
        this.mSpacer = findViewById(i.spacer);
        this.mTxtSubheadNotAcceptedSummary = (TextView) findViewById(i.txt_subhead_not_accepted_summary);
        this.mTxtNotAcceptedInfo = (TextView) findViewById(i.txt_not_accepted_info);
        this.mTxtSubheadNonreturnable = (TextView) findViewById(i.txt_subhead_nonreturnable);
        this.mTxtNonreturnableInfo = (TextView) findViewById(i.txt_nonreturnable_info);
        this.mTxtNonreturnableItems = (TextView) findViewById(i.txt_nonreturnable_items);
        this.mTxtSubheadReturnConditions = (TextView) findViewById(i.txt_subhead_return_conditions);
        this.mTxtReturnConditionsInfo = (TextView) findViewById(i.txt_return_conditions_info);
        this.mTxtSubheadQuestions = (TextView) findViewById(i.txt_subhead_questions);
        this.mTxtQuestionsInfo = (TextView) findViewById(i.txt_questions_info);
        this.mUnstructuredText = (TextView) findViewById(i.textview_unstructured_refunds_message);
        this.mUnstructuredTextHeader = (TextView) findViewById(i.textview_unstructured_refunds_header);
    }

    public void setStructuredShopRefunds(@NonNull StructuredShopRefunds structuredShopRefunds, @Nullable a aVar) {
        Resources resources = getContext().getResources();
        new SpannableStringBuilder();
        boolean z = structuredShopRefunds.acceptsReturns() || structuredShopRefunds.acceptsExchanges() || structuredShopRefunds.acceptsCancellations();
        boolean z2 = structuredShopRefunds.acceptsReturns() || structuredShopRefunds.acceptsExchanges();
        boolean z3 = structuredShopRefunds.acceptsReturns() && structuredShopRefunds.acceptsExchanges() && structuredShopRefunds.acceptsCancellations();
        int i = 8;
        this.mTxtSubheadAcceptedSummary.setVisibility(8);
        this.mTxtContactWithin.setVisibility(8);
        this.mTxtReturnWithin.setVisibility(8);
        this.mTxtCancelWithin.setVisibility(8);
        this.mTxtSubheadNotAcceptedSummary.setVisibility(8);
        this.mTxtNotAcceptedInfo.setVisibility(8);
        this.mTxtSubheadNonreturnable.setVisibility(8);
        this.mTxtNonreturnableInfo.setVisibility(8);
        this.mTxtNonreturnableItems.setVisibility(8);
        this.mTxtSubheadReturnConditions.setVisibility(8);
        this.mTxtReturnConditionsInfo.setVisibility(8);
        this.mTxtSubheadQuestions.setVisibility(8);
        this.mTxtQuestionsInfo.setVisibility(8);
        if (z) {
            this.mTxtSubheadAcceptedSummary.setVisibility(0);
            this.mTxtSubheadAcceptedSummary.setText(structuredShopRefunds.getAcceptedSummaryString());
        }
        if (z2) {
            this.mTxtContactWithin.setVisibility(0);
            this.mTxtReturnWithin.setVisibility(0);
            int contactSellerWithinDays = structuredShopRefunds.contactSellerWithinDays();
            String quantityString = resources.getQuantityString(m.structured_refunds_days, contactSellerWithinDays, new Object[]{Integer.valueOf(contactSellerWithinDays)});
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(resources.getString(o.structured_refunds_contact_within));
            spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(quantityString).setSpan(new ForegroundColorSpan(resources.getColor(e.dark_grey)), spannableStringBuilder.toString().indexOf(quantityString), spannableStringBuilder.length(), 33);
            this.mTxtContactWithin.setText(spannableStringBuilder);
            int returnItemsWithinDays = structuredShopRefunds.returnItemsWithinDays();
            String quantityString2 = resources.getQuantityString(m.structured_refunds_days, returnItemsWithinDays, new Object[]{Integer.valueOf(returnItemsWithinDays)});
            SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(resources.getString(o.structured_refunds_return_within));
            spannableStringBuilder2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(quantityString2).setSpan(new ForegroundColorSpan(resources.getColor(e.dark_grey)), spannableStringBuilder2.toString().indexOf(quantityString2), spannableStringBuilder2.length(), 33);
            this.mTxtReturnWithin.setText(spannableStringBuilder2);
            List<NonRefundableItem> nonRefundableItems = structuredShopRefunds.getNonRefundableItems();
            SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder();
            int i2 = 0;
            for (NonRefundableItem nonRefundableItem : nonRefundableItems) {
                if (nonRefundableItem.isNonRefundable()) {
                    i2++;
                    if (spannableStringBuilder3.length() != 0) {
                        spannableStringBuilder3.append("\n");
                    }
                    spannableStringBuilder3.append(Html.fromHtml("&#8226; ")).append(nonRefundableItem.getName());
                }
            }
            if (i2 > 0) {
                this.mTxtSubheadNonreturnable.setVisibility(0);
                this.mTxtNonreturnableInfo.setVisibility(0);
                this.mTxtNonreturnableItems.setVisibility(0);
                this.mTxtNonreturnableItems.setText(spannableStringBuilder3);
            }
        }
        if (structuredShopRefunds.acceptsCancellations()) {
            String str = null;
            String cancellationType = structuredShopRefunds.getCancellationType();
            char c = 65535;
            int hashCode = cancellationType.hashCode();
            if (hashCode != -1907964558) {
                if (hashCode == 99469071 && cancellationType.equals(StructuredShopRefunds.TYPE_CANCEL_WITHIN_HOURS)) {
                    c = 0;
                }
            } else if (cancellationType.equals(StructuredShopRefunds.TYPE_CANCEL_BEFORE_SHIPPED)) {
                c = 1;
            }
            switch (c) {
                case 0:
                    int cancelWithinHours = structuredShopRefunds.cancelWithinHours();
                    if (cancelWithinHours <= 24) {
                        str = resources.getQuantityString(m.structured_refunds_hours_of_purchase, cancelWithinHours, new Object[]{Integer.valueOf(cancelWithinHours)});
                        break;
                    } else {
                        int i3 = cancelWithinHours / 24;
                        str = resources.getQuantityString(m.structured_refunds_days_of_purchase, i3, new Object[]{Integer.valueOf(i3)});
                        break;
                    }
                case 1:
                    str = resources.getString(o.structured_refunds_before_shipped);
                    break;
            }
            if (str != null) {
                this.mTxtCancelWithin.setVisibility(0);
                SpannableStringBuilder spannableStringBuilder4 = new SpannableStringBuilder(resources.getString(o.structured_refunds_accept_cancellations_within));
                spannableStringBuilder4.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(str).setSpan(new ForegroundColorSpan(resources.getColor(e.dark_grey)), spannableStringBuilder4.toString().indexOf(str), spannableStringBuilder4.length(), 33);
                this.mTxtCancelWithin.setText(spannableStringBuilder4);
            }
        }
        View view = this.mSpacer;
        if (z && !z3) {
            i = 0;
        }
        view.setVisibility(i);
        if (!z3) {
            this.mTxtSubheadNotAcceptedSummary.setVisibility(0);
            this.mTxtNotAcceptedInfo.setVisibility(0);
            this.mTxtSubheadNotAcceptedSummary.setText(structuredShopRefunds.getNotAcceptedSummaryString());
            this.mTxtNotAcceptedInfo.setText(Html.fromHtml(resources.getString(o.structured_refunds_cancellation_message)));
            linkifyContactShopUrlSpan(this.mTxtNotAcceptedInfo, aVar);
        }
        if (z2) {
            this.mTxtSubheadReturnConditions.setVisibility(0);
            this.mTxtReturnConditionsInfo.setVisibility(0);
        }
        if (z3) {
            this.mTxtSubheadQuestions.setVisibility(0);
            this.mTxtQuestionsInfo.setVisibility(0);
            this.mTxtQuestionsInfo.setText(Html.fromHtml(resources.getString(o.structured_refunds_questions_message)));
            linkifyContactShopUrlSpan(this.mTxtQuestionsInfo, aVar);
        }
        setContentCollapsible(z2);
    }

    public void hideConditions() {
        this.mTxtSubheadReturnConditions.setVisibility(8);
        this.mTxtReturnConditionsInfo.setVisibility(8);
    }

    public void setUnstructuredReturnText(String str) {
        this.mUnstructuredText.setText(str);
        this.mUnstructuredText.setVisibility(0);
        this.mUnstructuredTextHeader.setVisibility(0);
    }
}
