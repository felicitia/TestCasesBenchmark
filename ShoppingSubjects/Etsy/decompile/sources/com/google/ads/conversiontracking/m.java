package com.google.ads.conversiontracking;

import android.content.Intent;

public class m extends Exception {
    private final Intent a;

    public m(String str, Intent intent) {
        super(str);
        this.a = intent;
    }
}
