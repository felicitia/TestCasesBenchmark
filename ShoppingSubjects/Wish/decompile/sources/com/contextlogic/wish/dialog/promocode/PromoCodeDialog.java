package com.contextlogic.wish.dialog.promocode;

public interface PromoCodeDialog {
    void handleApplyPromoCodeFailure(String str);

    void handleApplyPromoCodeSuccess(String str);

    boolean isCancelable();
}
