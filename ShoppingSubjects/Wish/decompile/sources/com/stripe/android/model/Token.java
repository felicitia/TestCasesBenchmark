package com.stripe.android.model;

import com.stripe.model.StripeObject;
import java.util.Date;

public class Token extends StripeObject {
    private final Card card;
    private final Date created;
    private final String id;
    private final boolean livemode;
    private final boolean used;

    public Token(String str, boolean z, Date date, Boolean bool, Card card2) {
        this.id = str;
        this.livemode = z;
        this.card = card2;
        this.created = date;
        this.used = bool.booleanValue();
    }

    public String getId() {
        return this.id;
    }
}
