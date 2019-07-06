package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.convo.context.CustomOrderContext.Action;

public final /* synthetic */ class ag {
    public static final /* synthetic */ int[] a = new int[Action.values().length];
    public static final /* synthetic */ int[] b = new int[Action.values().length];

    static {
        a[Action.VIEW_CART.ordinal()] = 1;
        a[Action.MANAGE.ordinal()] = 2;
        a[Action.ADD_TO_CART.ordinal()] = 3;
        a[Action.OPEN_ORDER.ordinal()] = 4;
        a[Action.UNKNOWN.ordinal()] = 5;
        a[Action.START.ordinal()] = 6;
        b[Action.ADD_TO_CART.ordinal()] = 1;
        b[Action.VIEW_CART.ordinal()] = 2;
        b[Action.OPEN_ORDER.ordinal()] = 3;
        b[Action.UNKNOWN.ordinal()] = 4;
        b[Action.START.ordinal()] = 5;
        b[Action.MANAGE.ordinal()] = 6;
    }
}
