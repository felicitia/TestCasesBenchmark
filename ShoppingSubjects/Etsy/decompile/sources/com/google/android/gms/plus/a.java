package com.google.android.gms.plus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;

@Deprecated
public final class a {

    @Deprecated
    @VisibleForTesting
    /* renamed from: com.google.android.gms.plus.a$a reason: collision with other inner class name */
    public static class C0142a {
        private final Context a;
        private final Intent b = new Intent().setAction("android.intent.action.SEND");
        private boolean c;
        private ArrayList<Uri> d;

        @Deprecated
        public C0142a(Activity activity) {
            this.a = activity;
            this.b.addFlags(524288);
            if (activity != null && activity.getComponentName() != null) {
                this.c = true;
            }
        }

        @Deprecated
        public Intent a() {
            Intent intent;
            String str;
            boolean z = true;
            boolean z2 = this.d != null && this.d.size() > 1;
            boolean equals = "android.intent.action.SEND_MULTIPLE".equals(this.b.getAction());
            boolean booleanExtra = this.b.getBooleanExtra("com.google.android.apps.plus.GOOGLE_INTERACTIVE_POST", false);
            Preconditions.checkState(!z2 || !booleanExtra, "Call-to-action buttons are only available for URLs.");
            Preconditions.checkState(!booleanExtra || this.b.hasExtra("com.google.android.apps.plus.CONTENT_URL"), "The content URL is required for interactive posts.");
            if (booleanExtra && !this.b.hasExtra("com.google.android.apps.plus.CONTENT_URL") && !this.b.hasExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID")) {
                z = false;
            }
            Preconditions.checkState(z, "Must set content URL or content deep-link ID to use a call-to-action button.");
            if (this.b.hasExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID")) {
                Preconditions.checkState(a.a(this.b.getStringExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID")), "The specified deep-link ID was malformed.");
            }
            if (!z2 && equals) {
                this.b.setAction("android.intent.action.SEND");
                if (this.d == null || this.d.isEmpty()) {
                    this.b.removeExtra("android.intent.extra.STREAM");
                } else {
                    this.b.putExtra("android.intent.extra.STREAM", (Parcelable) this.d.get(0));
                }
                this.d = null;
            }
            if (z2 && !equals) {
                this.b.setAction("android.intent.action.SEND_MULTIPLE");
                if (this.d == null || this.d.isEmpty()) {
                    this.b.removeExtra("android.intent.extra.STREAM");
                } else {
                    this.b.putParcelableArrayListExtra("android.intent.extra.STREAM", this.d);
                }
            }
            if (!"com.google.android.gms.plus.action.SHARE_INTERNAL_GOOGLE".equals(this.b.getAction())) {
                if (!this.b.hasExtra("android.intent.extra.STREAM")) {
                    this.b.setAction("com.google.android.gms.plus.action.SHARE_GOOGLE");
                } else {
                    intent = this.b;
                    str = "com.google.android.apps.plus";
                    intent.setPackage(str);
                    return this.b;
                }
            }
            intent = this.b;
            str = "com.google.android.gms";
            intent.setPackage(str);
            return this.b;
        }

        @Deprecated
        public C0142a a(Uri uri) {
            this.d = null;
            this.b.putExtra("android.intent.extra.STREAM", uri);
            return this;
        }

        @Deprecated
        public C0142a a(CharSequence charSequence) {
            this.b.putExtra("android.intent.extra.TEXT", charSequence);
            return this;
        }

        @Deprecated
        public C0142a a(String str) {
            this.b.setType(str);
            return this;
        }

        @Deprecated
        public C0142a b(Uri uri) {
            String uri2 = uri != null ? uri.toString() : null;
            if (TextUtils.isEmpty(uri2)) {
                this.b.removeExtra("com.google.android.apps.plus.CONTENT_URL");
                return this;
            }
            this.b.putExtra("com.google.android.apps.plus.CONTENT_URL", uri2);
            return this;
        }
    }

    @VisibleForTesting
    protected static boolean a(String str) {
        String str2;
        String str3;
        if (TextUtils.isEmpty(str)) {
            str2 = "GooglePlusPlatform";
            str3 = "The provided deep-link ID is empty.";
        } else if (!str.contains(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
            return true;
        } else {
            str2 = "GooglePlusPlatform";
            str3 = "Spaces are not allowed in deep-link IDs.";
        }
        Log.e(str2, str3);
        return false;
    }
}
