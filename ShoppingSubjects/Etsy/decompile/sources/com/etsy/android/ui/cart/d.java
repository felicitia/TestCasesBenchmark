package com.etsy.android.ui.cart;

import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.vespa.PositionList;
import com.etsy.android.vespa.f;

/* compiled from: ICartGroupActionDelegate */
public interface d extends f {
    void proceedToCheckout(String str, String str2);

    void showVariationSelectDialog(PositionList positionList, ServerDrivenAction serverDrivenAction);
}
