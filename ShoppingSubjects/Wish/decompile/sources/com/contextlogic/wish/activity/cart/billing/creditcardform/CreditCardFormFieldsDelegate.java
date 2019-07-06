package com.contextlogic.wish.activity.cart.billing.creditcardform;

import com.contextlogic.wish.util.CreditCardUtil.CardType;

public interface CreditCardFormFieldsDelegate {
    void onCardTypeChanged(CardType cardType);

    void onEntryComplete();
}
