package com.bumptech.glide.load.b;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.load.a.c;

/* compiled from: ResourceLoader */
public class n<T> implements l<Integer, T> {
    private final l<Uri, T> a;
    private final Resources b;

    public n(Context context, l<Uri, T> lVar) {
        this(context.getResources(), lVar);
    }

    public n(Resources resources, l<Uri, T> lVar) {
        this.b = resources;
        this.a = lVar;
    }

    public c<T> a(Integer num, int i, int i2) {
        Object obj;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("android.resource://");
            sb.append(this.b.getResourcePackageName(num.intValue()));
            sb.append('/');
            sb.append(this.b.getResourceTypeName(num.intValue()));
            sb.append('/');
            sb.append(this.b.getResourceEntryName(num.intValue()));
            obj = Uri.parse(sb.toString());
        } catch (NotFoundException e) {
            if (Log.isLoggable("ResourceLoader", 5)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Received invalid resource id: ");
                sb2.append(num);
                Log.w("ResourceLoader", sb2.toString(), e);
            }
            obj = null;
        }
        if (obj != null) {
            return this.a.a(obj, i, i2);
        }
        return null;
    }
}
