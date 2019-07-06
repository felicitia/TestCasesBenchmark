package com.google.firebase.appindexing.builders;

import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.Action.Builder;

public final class Actions {
    public static Action newView(String str, String str2) {
        return new Builder("ViewAction").setObject(str, str2).build();
    }
}
