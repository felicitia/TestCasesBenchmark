package com.etsy.android.ui.convos;

import com.etsy.android.ui.convos.i.a;
import io.reactivex.functions.Consumer;

final /* synthetic */ class d implements Consumer {
    private final ConvoComposeFragment a;

    d(ConvoComposeFragment convoComposeFragment) {
        this.a = convoComposeFragment;
    }

    public void accept(Object obj) {
        this.a.lambda$fetchRecipient$0$ConvoComposeFragment((a) obj);
    }
}
