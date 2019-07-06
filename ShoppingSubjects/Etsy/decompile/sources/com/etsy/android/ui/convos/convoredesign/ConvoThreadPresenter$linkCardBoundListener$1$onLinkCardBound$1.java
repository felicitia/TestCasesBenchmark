package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.ListingCard;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.ui.convos.convoredesign.af.f;
import java.util.List;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: ConvoThreadPresenter.kt */
final class ConvoThreadPresenter$linkCardBoundListener$1$onLinkCardBound$1 extends Lambda implements b<List<? extends ListingCard>, h> {
    final /* synthetic */ int $adapterPosition;
    final /* synthetic */ ak $linkCard;
    final /* synthetic */ f this$0;

    ConvoThreadPresenter$linkCardBoundListener$1$onLinkCardBound$1(f fVar, ak akVar, int i) {
        this.this$0 = fVar;
        this.$linkCard = akVar;
        this.$adapterPosition = i;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List) obj);
        return h.a;
    }

    public final void invoke(List<ListingCard> list) {
        p.a((Object) list, "listingCards");
        if (!list.isEmpty()) {
            ListingCard listingCard = (ListingCard) list.get(0);
            this.$linkCard.a(listingCard.getTitle());
            ak akVar = this.$linkCard;
            String a = CurrencyUtil.a(listingCard.getPrice(), listingCard.getCurrencyCode());
            p.a((Object) a, "CurrencyUtil.formatPrice…listingCard.currencyCode)");
            akVar.b(a);
            this.$linkCard.c(listingCard.getImageUrl170());
            this.this$0.a.m.notifyItemChanged(this.$adapterPosition);
        }
    }
}
