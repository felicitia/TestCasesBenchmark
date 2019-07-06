package com.google.android.gms.a;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;

@Deprecated
@VisibleForTesting
public final class b {
    private final Uri a;

    private b(Uri uri) {
        this.a = uri;
    }

    @VisibleForTesting
    public static b a(Uri uri) {
        b bVar = new b(uri);
        if (!"android-app".equals(bVar.a.getScheme())) {
            throw new IllegalArgumentException("android-app scheme is required.");
        } else if (TextUtils.isEmpty(bVar.b())) {
            throw new IllegalArgumentException("Package name is empty.");
        } else {
            if (bVar.a.equals(a(bVar.b(), bVar.c()).a())) {
                return bVar;
            }
            throw new IllegalArgumentException("URI is not canonical.");
        }
    }

    @VisibleForTesting
    public static b a(String str, Uri uri) {
        Builder authority = new Builder().scheme("android-app").authority(str);
        if (uri != null) {
            authority.appendPath(uri.getScheme());
            if (uri.getAuthority() != null) {
                authority.appendPath(uri.getAuthority());
            }
            for (String appendPath : uri.getPathSegments()) {
                authority.appendPath(appendPath);
            }
            authority.encodedQuery(uri.getEncodedQuery()).encodedFragment(uri.getEncodedFragment());
        }
        return new b(authority.build());
    }

    @VisibleForTesting
    public final Uri a() {
        return this.a;
    }

    @VisibleForTesting
    public final String b() {
        return this.a.getAuthority();
    }

    @VisibleForTesting
    public final Uri c() {
        List pathSegments = this.a.getPathSegments();
        if (pathSegments.size() <= 0) {
            return null;
        }
        String str = (String) pathSegments.get(0);
        Builder builder = new Builder();
        builder.scheme(str);
        if (pathSegments.size() > 1) {
            builder.authority((String) pathSegments.get(1));
            for (int i = 2; i < pathSegments.size(); i++) {
                builder.appendPath((String) pathSegments.get(i));
            }
        }
        builder.encodedQuery(this.a.getEncodedQuery());
        builder.encodedFragment(this.a.getEncodedFragment());
        return builder.build();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof b) {
            return this.a.equals(((b) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.a);
    }

    public final String toString() {
        return this.a.toString();
    }
}
