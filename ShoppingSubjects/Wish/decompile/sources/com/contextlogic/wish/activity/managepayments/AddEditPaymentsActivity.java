package com.contextlogic.wish.activity.managepayments;

import android.content.Context;
import android.content.Intent;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.api.model.WishCreditCardInfo;
import com.contextlogic.wish.util.IntentUtil;

public class AddEditPaymentsActivity extends DrawerActivity {
    public static Intent create(Context context, PaymentProcessor paymentProcessor) {
        Intent intent = new Intent(context, AddEditPaymentsActivity.class);
        IntentUtil.putLargeParcelableExtra(intent, "payment_type", paymentProcessor);
        return intent;
    }

    public static Intent create(Context context, WishCreditCardInfo wishCreditCardInfo) {
        Intent intent = new Intent(context, AddEditPaymentsActivity.class);
        IntentUtil.putLargeParcelableExtra(intent, "credit_card_info", wishCreditCardInfo);
        return intent;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new AddEditPaymentsServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new AddEditPaymentsFragment();
    }

    public WishCreditCardInfo getCreditCardInfo() {
        return (WishCreditCardInfo) IntentUtil.getLargeParcelableExtra(getIntent(), "credit_card_info", WishCreditCardInfo.class);
    }

    public PaymentProcessor getAddNewPaymentType() {
        return (PaymentProcessor) IntentUtil.getLargeParcelableExtra(getIntent(), "payment_type", PaymentProcessor.class);
    }

    public boolean isEditMode() {
        return getCreditCardInfo() != null;
    }
}
