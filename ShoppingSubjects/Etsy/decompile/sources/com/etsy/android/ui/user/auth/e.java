package com.etsy.android.ui.user.auth;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final /* synthetic */ class e implements OnItemClickListener {
    private final RegisterFragment a;

    e(RegisterFragment registerFragment) {
        this.a = registerFragment;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.a.lambda$setupEmailAddressField$2$RegisterFragment(adapterView, view, i, j);
    }
}
