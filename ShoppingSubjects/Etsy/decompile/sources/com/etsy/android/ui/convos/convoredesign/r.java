package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.ui.convos.convoredesign.q.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.TypeCastException;
import kotlin.collections.o;
import kotlin.jvm.internal.p;
import kotlin.text.j;
import kotlin.text.m;

/* compiled from: ConvoMessageParserResult.kt */
public final class r {
    public static final List<q> a(String str) {
        boolean z;
        p.b(str, "message");
        List b = b(str);
        if (b.isEmpty()) {
            return o.a();
        }
        Iterable<String> iterable = b;
        Collection arrayList = new ArrayList();
        for (String c : iterable) {
            a c2 = c(c);
            if (c2 != null) {
                arrayList.add(c2);
            }
        }
        List<q> list = (List) arrayList;
        Iterable iterable2 = list;
        Collection arrayList2 = new ArrayList();
        Iterator it = iterable2.iterator();
        String str2 = str;
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            a aVar = (a) it.next();
            str2 = m.a(str2, aVar.a(), "", false, 4, (Object) null);
            a aVar2 = null;
            if (m.a(str, aVar.a(), false, 2, null) || m.b(str, aVar.a(), false, 2, null)) {
                aVar2 = aVar;
            }
            if (aVar2 != null) {
                arrayList2.add(aVar2);
            }
        }
        List<q> list2 = (List) arrayList2;
        if (str2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        if (m.b(str2).toString().length() == 0) {
            z = true;
        }
        if (z) {
            if (list.isEmpty()) {
                list = o.a();
            }
            return list;
        }
        if (list2.isEmpty()) {
            list2 = o.a();
        }
        return list2;
    }

    private static final List<String> b(String str) {
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile("\\b(?:https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])", 2).matcher(str);
        while (matcher.find()) {
            int start = matcher.start(0);
            int end = matcher.end(0);
            if (str == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String substring = str.substring(start, end);
            p.a((Object) substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            arrayList.add(substring);
        }
        return arrayList;
    }

    private static final a c(String str) {
        com.etsy.android.lib.parsing.a aVar = new com.etsy.android.lib.parsing.a("\\/listing\\/([^\\/A-Za-z]*)");
        if (!aVar.a(str)) {
            return null;
        }
        j b = aVar.b(str);
        List c = b != null ? b.c() : null;
        if (c != null && c.size() > 1) {
            if (((CharSequence) c.get(1)).length() > 0) {
                return new a(str, (String) c.get(1));
            }
        }
        return null;
    }
}
