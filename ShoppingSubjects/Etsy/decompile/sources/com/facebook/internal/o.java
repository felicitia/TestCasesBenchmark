package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.f;
import java.util.Locale;

/* compiled from: ImageRequest */
public class o {
    private Context a;
    private Uri b;
    private b c;
    private boolean d;
    private Object e;

    /* compiled from: ImageRequest */
    public static class a {
        /* access modifiers changed from: private */
        public Context a;
        /* access modifiers changed from: private */
        public Uri b;
        /* access modifiers changed from: private */
        public b c;
        /* access modifiers changed from: private */
        public boolean d;
        /* access modifiers changed from: private */
        public Object e;

        public a(Context context, Uri uri) {
            aa.a((Object) uri, "imageUri");
            this.a = context;
            this.b = uri;
        }

        public a a(b bVar) {
            this.c = bVar;
            return this;
        }

        public a a(Object obj) {
            this.e = obj;
            return this;
        }

        public a a(boolean z) {
            this.d = z;
            return this;
        }

        public o a() {
            return new o(this);
        }
    }

    /* compiled from: ImageRequest */
    public interface b {
        void a(p pVar);
    }

    public static Uri a(String str, int i, int i2) {
        aa.a(str, "userId");
        int max = Math.max(i, 0);
        int max2 = Math.max(i2, 0);
        if (max == 0 && max2 == 0) {
            throw new IllegalArgumentException("Either width or height must be greater than 0");
        }
        Builder path = Uri.parse(x.b()).buildUpon().path(String.format(Locale.US, "%s/%s/picture", new Object[]{f.g(), str}));
        if (max2 != 0) {
            path.appendQueryParameter(ResponseConstants.HEIGHT, String.valueOf(max2));
        }
        if (max != 0) {
            path.appendQueryParameter(ResponseConstants.WIDTH, String.valueOf(max));
        }
        path.appendQueryParameter("migration_overrides", "{october_2012:true}");
        return path.build();
    }

    private o(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e == null ? new Object() : aVar.e;
    }

    public Context a() {
        return this.a;
    }

    public Uri b() {
        return this.b;
    }

    public b c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public Object e() {
        return this.e;
    }
}
