package com.google.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Strings;

public final class c {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;

    private c(@NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
        Preconditions.checkState(!Strings.isEmptyOrWhitespace(str), "ApplicationId must be set.");
        this.b = str;
        this.a = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
    }

    public static c a(Context context) {
        StringResourceValueReader stringResourceValueReader = new StringResourceValueReader(context);
        String string = stringResourceValueReader.getString("google_app_id");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        c cVar = new c(string, stringResourceValueReader.getString("google_api_key"), stringResourceValueReader.getString("firebase_database_url"), stringResourceValueReader.getString("ga_trackingId"), stringResourceValueReader.getString("gcm_defaultSenderId"), stringResourceValueReader.getString("google_storage_bucket"), stringResourceValueReader.getString(ResponseConstants.PROJECT_ID));
        return cVar;
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.e;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (!Objects.equal(this.b, cVar.b) || !Objects.equal(this.a, cVar.a) || !Objects.equal(this.c, cVar.c) || !Objects.equal(this.d, cVar.d) || !Objects.equal(this.e, cVar.e) || !Objects.equal(this.f, cVar.f) || !Objects.equal(this.g, cVar.g)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.b, this.a, this.c, this.d, this.e, this.f, this.g);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("applicationId", this.b).add("apiKey", this.a).add("databaseUrl", this.c).add("gcmSenderId", this.e).add("storageBucket", this.f).add("projectId", this.g).toString();
    }
}
