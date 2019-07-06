package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.o;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.p;

/* compiled from: Indent.kt */
class n {
    public static /* bridge */ /* synthetic */ String a(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "|";
        }
        return m.a(str, str2);
    }

    public static final String a(String str, String str2) {
        p.b(str, "$receiver");
        p.b(str2, "marginPrefix");
        return m.a(str, "", str2);
    }

    public static final String a(String str, String str2, String str3) {
        int i;
        String str4 = str;
        String str5 = str3;
        p.b(str4, "$receiver");
        p.b(str2, "newIndent");
        p.b(str5, "marginPrefix");
        if (!(!m.a(str5))) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.".toString());
        }
        List g = m.g(str4);
        int length = str.length() + (str2.length() * g.size());
        b c = c(str2);
        int a = o.a(g);
        Iterable<String> iterable = g;
        Collection arrayList = new ArrayList();
        int i2 = 0;
        for (String str6 : iterable) {
            int i3 = i2 + 1;
            String str7 = null;
            if ((i2 == 0 || i2 == a) && m.a(str6)) {
                str6 = null;
            } else {
                CharSequence charSequence = str6;
                int length2 = charSequence.length();
                int i4 = 0;
                while (true) {
                    if (i4 >= length2) {
                        i = -1;
                        break;
                    } else if (!a.a(charSequence.charAt(i4))) {
                        i = i4;
                        break;
                    } else {
                        i4++;
                    }
                }
                if (i != -1) {
                    int i5 = i;
                    if (m.a(str6, str5, i, false, 4, (Object) null)) {
                        int length3 = i5 + str3.length();
                        if (str6 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        str7 = str6.substring(length3);
                        p.a((Object) str7, "(this as java.lang.String).substring(startIndex)");
                    }
                }
                if (str7 != null) {
                    String str8 = (String) c.invoke(str7);
                    if (str8 != null) {
                        str6 = str8;
                    }
                }
            }
            if (str6 != null) {
                arrayList.add(str6);
            }
            i2 = i3;
        }
        String sb = ((StringBuilder) o.a((List) arrayList, new StringBuilder(length), "\n", null, null, 0, null, null, 124, null)).toString();
        p.a((Object) sb, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return sb;
    }

    public static final String a(String str) {
        p.b(str, "$receiver");
        return m.b(str, "");
    }

    public static final String b(String str, String str2) {
        String str3 = str;
        p.b(str3, "$receiver");
        p.b(str2, "newIndent");
        List g = m.g(str3);
        Iterable<String> iterable = g;
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            if (!m.a((String) next)) {
                arrayList.add(next);
            }
        }
        Iterable<String> iterable2 = (List) arrayList;
        Collection arrayList2 = new ArrayList(o.a(iterable2, 10));
        for (String b : iterable2) {
            arrayList2.add(Integer.valueOf(b(b)));
        }
        Integer num = (Integer) o.h((Iterable<? extends T>) (List) arrayList2);
        int i = 0;
        int intValue = num != null ? num.intValue() : 0;
        int length = str.length() + (str2.length() * g.size());
        b c = c(str2);
        int a = o.a(g);
        Collection arrayList3 = new ArrayList();
        for (String str4 : iterable) {
            int i2 = i + 1;
            if ((i == 0 || i == a) && m.a(str4)) {
                str4 = null;
            } else {
                String a2 = m.a(str4, intValue);
                if (a2 != null) {
                    String str5 = (String) c.invoke(a2);
                    if (str5 != null) {
                        str4 = str5;
                    }
                }
            }
            if (str4 != null) {
                arrayList3.add(str4);
            }
            i = i2;
        }
        String sb = ((StringBuilder) o.a((List) arrayList3, new StringBuilder(length), "\n", null, null, 0, null, null, 124, null)).toString();
        p.a((Object) sb, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return sb;
    }

    private static final int b(String str) {
        CharSequence charSequence = str;
        int length = charSequence.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            } else if (!a.a(charSequence.charAt(i))) {
                break;
            } else {
                i++;
            }
        }
        return i == -1 ? str.length() : i;
    }

    private static final b<String, String> c(String str) {
        if (str.length() == 0) {
            return StringsKt__IndentKt$getIndentFunction$1.INSTANCE;
        }
        return new StringsKt__IndentKt$getIndentFunction$2<>(str);
    }
}
