package com.etsy.android.ui.convos.convolistredesign;

import com.etsy.android.lib.models.datatypes.EtsyId;
import kotlin.h;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.Lambda;

/* compiled from: ConvosListFragment.kt */
final class ConvosListFragment$onCreateView$1 extends Lambda implements m<Boolean, EtsyId, h> {
    final /* synthetic */ ConvosListFragment this$0;

    ConvosListFragment$onCreateView$1(ConvosListFragment convosListFragment) {
        this.this$0 = convosListFragment;
        super(2);
    }

    public /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Boolean) obj).booleanValue(), (EtsyId) obj2);
        return h.a;
    }

    public final void invoke(boolean z, EtsyId etsyId) {
        this.this$0.getPresenter().a(etsyId);
    }
}
