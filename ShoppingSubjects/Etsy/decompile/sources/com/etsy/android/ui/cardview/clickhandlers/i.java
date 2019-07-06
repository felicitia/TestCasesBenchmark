package com.etsy.android.ui.cardview.clickhandlers;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class i implements OnClickListener {
    static final OnClickListener a = new i();

    private i() {
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        SavedCartClickHandler.a(dialogInterface, i);
    }
}
