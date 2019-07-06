package com.onfido.android.sdk.capture.ui.options;

import kotlin.jvm.internal.Intrinsics;

public final class MessageScreenOptions extends BaseOptions {
    private String a;
    private String b;
    private String c;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MessageScreenOptions)) {
            return false;
        }
        MessageScreenOptions messageScreenOptions = (MessageScreenOptions) obj;
        return !(Intrinsics.areEqual(this.a, messageScreenOptions.a) ^ true) && !(Intrinsics.areEqual(this.b, messageScreenOptions.b) ^ true) && !(Intrinsics.areEqual(this.c, messageScreenOptions.c) ^ true);
    }

    public final String getMessage() {
        return this.b;
    }

    public final String getTitle() {
        return this.a;
    }

    public int hashCode() {
        return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }
}
