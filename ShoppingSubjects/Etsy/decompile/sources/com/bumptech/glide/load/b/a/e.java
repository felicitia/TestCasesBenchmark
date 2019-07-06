package com.bumptech.glide.load.b.a;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.d;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.q;

/* compiled from: FileDescriptorUriLoader */
public class e extends q<ParcelFileDescriptor> implements b<Uri> {

    /* compiled from: FileDescriptorUriLoader */
    public static class a implements m<Uri, ParcelFileDescriptor> {
        public void a() {
        }

        public l<Uri, ParcelFileDescriptor> a(Context context, c cVar) {
            return new e(context, cVar.a(d.class, ParcelFileDescriptor.class));
        }
    }

    public e(Context context, l<d, ParcelFileDescriptor> lVar) {
        super(context, lVar);
    }

    /* access modifiers changed from: protected */
    public com.bumptech.glide.load.a.c<ParcelFileDescriptor> a(Context context, Uri uri) {
        return new com.bumptech.glide.load.a.e(context, uri);
    }

    /* access modifiers changed from: protected */
    public com.bumptech.glide.load.a.c<ParcelFileDescriptor> a(Context context, String str) {
        return new com.bumptech.glide.load.a.d(context.getApplicationContext().getAssets(), str);
    }
}
