package com.bumptech.glide.load.b.a;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.b.b;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.b.m;
import java.io.File;

/* compiled from: FileDescriptorFileLoader */
public class a extends b<ParcelFileDescriptor> implements b<File> {

    /* renamed from: com.bumptech.glide.load.b.a.a$a reason: collision with other inner class name */
    /* compiled from: FileDescriptorFileLoader */
    public static class C0009a implements m<File, ParcelFileDescriptor> {
        public void a() {
        }

        public l<File, ParcelFileDescriptor> a(Context context, c cVar) {
            return new a(cVar.a(Uri.class, ParcelFileDescriptor.class));
        }
    }

    public a(l<Uri, ParcelFileDescriptor> lVar) {
        super(lVar);
    }
}
