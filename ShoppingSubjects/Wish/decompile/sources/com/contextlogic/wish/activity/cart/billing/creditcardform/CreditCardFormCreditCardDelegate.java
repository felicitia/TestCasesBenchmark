package com.contextlogic.wish.activity.cart.billing.creditcardform;

import com.contextlogic.wish.util.CreditCardUtil.CardType;

public interface CreditCardFormCreditCardDelegate {
    void onCardTypeChange(CardType cardType);

    void onCreditCardNumberValid();
}
