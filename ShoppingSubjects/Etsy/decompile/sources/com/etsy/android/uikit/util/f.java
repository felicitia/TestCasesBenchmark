package com.etsy.android.uikit.util;

import java.util.ArrayList;
import java.util.List;
import kotlin.sequences.d;
import kotlin.text.Regex;

/* compiled from: HashtagHelper.kt */
public final class f {
    public final List<String> a(String str) {
        if (str == null) {
            str = "";
        }
        CharSequence charSequence = str;
        if (charSequence.length() == 0) {
            return new ArrayList<>();
        }
        return d.b(d.c(Regex.findAll$default(new Regex("\\B(#[a-zA-Z]+\\b)"), charSequence, 0, 2, null), HashtagHelper$getHashTagsFromString$1.INSTANCE));
    }
}
