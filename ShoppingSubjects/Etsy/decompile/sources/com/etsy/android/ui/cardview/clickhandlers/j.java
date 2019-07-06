package com.etsy.android.ui.cardview.clickhandlers;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.etsy.android.lib.models.apiv3.cart.SavedCart;

final /* synthetic */ class j implements OnClickListener {
    private final SavedCartClickHandler a;
    private final SavedCart b;
    private final int c;

    j(SavedCartClickHandler savedCartClickHandler, SavedCart savedCart, int i) {
        this.a = savedCartClickHandler;
        this.b = savedCart;
        this.c = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.a(this.b, this.c, dialogInterface, i);
    }
}
