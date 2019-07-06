package com.etsy.android.extensions;

import com.etsy.android.lib.logger.l;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import kotlin.jvm.internal.p;

/* compiled from: ParamExtensions.kt */
public final class f {
    public static final String a(String str, l lVar) {
        p.b(str, "$receiver");
        try {
            String decode = URLDecoder.decode(str, "UTF-8");
            p.a((Object) decode, "URLDecoder.decode(this, \"UTF-8\")");
            return decode;
        } catch (UnsupportedEncodingException unused) {
            if (lVar == null) {
                return str;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Couldn't decode query parameter: ");
            sb.append(str);
            lVar.d(sb.toString());
            return str;
        }
    }
}
