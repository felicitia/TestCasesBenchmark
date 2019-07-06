package kotlin.text;

import com.etsy.android.lib.models.ResponseConstants;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.b.c;
import kotlin.b.d;
import kotlin.collections.f;
import kotlin.collections.n;
import kotlin.collections.o;
import kotlin.jvm.internal.p;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: Strings.kt */
class v extends u {

    /* compiled from: Strings.kt */
    public static final class a extends n {
        final /* synthetic */ CharSequence a;
        private int b;

        a(CharSequence charSequence) {
            this.a = charSequence;
        }

        public char b() {
            CharSequence charSequence = this.a;
            int i = this.b;
            this.b = i + 1;
            return charSequence.charAt(i);
        }

        public boolean hasNext() {
            return this.b < this.a.length();
        }
    }

    public static final n c(CharSequence charSequence) {
        p.b(charSequence, "$receiver");
        return new a(charSequence);
    }

    public static final c d(CharSequence charSequence) {
        p.b(charSequence, "$receiver");
        return new c(0, charSequence.length() - 1);
    }

    public static final int e(CharSequence charSequence) {
        p.b(charSequence, "$receiver");
        return charSequence.length() - 1;
    }

    public static final String a(CharSequence charSequence, c cVar) {
        p.b(charSequence, "$receiver");
        p.b(cVar, "range");
        return charSequence.subSequence(cVar.f().intValue(), cVar.g().intValue() + 1).toString();
    }

    public static final boolean a(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3, boolean z) {
        p.b(charSequence, "$receiver");
        p.b(charSequence2, ResponseConstants.OTHER);
        if (i2 < 0 || i < 0 || i > charSequence.length() - i3 || i2 > charSequence2.length() - i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!a.a(charSequence.charAt(i + i4), charSequence2.charAt(i2 + i4), z)) {
                return false;
            }
        }
        return true;
    }

    public static final int a(CharSequence charSequence, char[] cArr, int i, boolean z) {
        boolean z2;
        p.b(charSequence, "$receiver");
        p.b(cArr, "chars");
        if (z || cArr.length != 1 || !(charSequence instanceof String)) {
            int c = d.c(i, 0);
            int e = m.e(charSequence);
            if (c <= e) {
                while (true) {
                    char charAt = charSequence.charAt(c);
                    int length = cArr.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            z2 = false;
                            break;
                        } else if (a.a(cArr[i2], charAt, z)) {
                            z2 = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z2) {
                        if (c == e) {
                            break;
                        }
                        c++;
                    } else {
                        return c;
                    }
                }
            }
            return -1;
        }
        return ((String) charSequence).indexOf(f.a(cArr), i);
    }

    public static final int b(CharSequence charSequence, char[] cArr, int i, boolean z) {
        p.b(charSequence, "$receiver");
        p.b(cArr, "chars");
        if (z || cArr.length != 1 || !(charSequence instanceof String)) {
            for (int d = d.d(i, m.e(charSequence)); d >= 0; d--) {
                char charAt = charSequence.charAt(d);
                boolean z2 = false;
                int length = cArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (a.a(cArr[i2], charAt, z)) {
                        z2 = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z2) {
                    return d;
                }
            }
            return -1;
        }
        return ((String) charSequence).lastIndexOf(f.a(cArr), i);
    }

    static /* bridge */ /* synthetic */ int a(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            z2 = false;
        }
        return a(charSequence, charSequence2, i, i2, z, z2);
    }

    private static final int a(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2) {
        kotlin.b.a aVar;
        if (!z2) {
            aVar = new c(d.c(i, 0), d.d(i2, charSequence.length()));
        } else {
            aVar = d.a(d.d(i, m.e(charSequence)), d.c(i2, 0));
        }
        if (!(charSequence instanceof String) || !(charSequence2 instanceof String)) {
            int a2 = aVar.a();
            int b = aVar.b();
            int c = aVar.c();
            if (c <= 0 ? a2 >= b : a2 <= b) {
                while (true) {
                    if (!m.a(charSequence2, 0, charSequence, a2, charSequence2.length(), z)) {
                        if (a2 == b) {
                            break;
                        }
                        a2 += c;
                    } else {
                        return a2;
                    }
                }
            }
        } else {
            int a3 = aVar.a();
            int b2 = aVar.b();
            int c2 = aVar.c();
            if (c2 <= 0 ? a3 >= b2 : a3 <= b2) {
                while (true) {
                    if (!m.a((String) charSequence2, 0, (String) charSequence, a3, charSequence2.length(), z)) {
                        if (a3 == b2) {
                            break;
                        }
                        a3 += c2;
                    } else {
                        return a3;
                    }
                }
            }
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static final Pair<Integer, String> b(CharSequence charSequence, Collection<String> collection, int i, boolean z, boolean z2) {
        Object obj;
        Object obj2;
        Pair<Integer, String> pair = null;
        if (z || collection.size() != 1) {
            kotlin.b.a cVar = !z2 ? new c(d.c(i, 0), charSequence.length()) : d.a(d.d(i, m.e(charSequence)), 0);
            if (charSequence instanceof String) {
                int a2 = cVar.a();
                int b = cVar.b();
                int c = cVar.c();
                if (c <= 0 ? a2 >= b : a2 <= b) {
                    while (true) {
                        Iterator it = collection.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                obj2 = null;
                                break;
                            }
                            obj2 = it.next();
                            String str = (String) obj2;
                            if (m.a(str, 0, (String) charSequence, a2, str.length(), z)) {
                                break;
                            }
                        }
                        String str2 = (String) obj2;
                        if (str2 == null) {
                            if (a2 == b) {
                                break;
                            }
                            a2 += c;
                        } else {
                            return kotlin.f.a(Integer.valueOf(a2), str2);
                        }
                    }
                }
            } else {
                int a3 = cVar.a();
                int b2 = cVar.b();
                int c2 = cVar.c();
                if (c2 <= 0 ? a3 >= b2 : a3 <= b2) {
                    while (true) {
                        Iterator it2 = collection.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                obj = null;
                                break;
                            }
                            obj = it2.next();
                            String str3 = (String) obj;
                            if (m.a((CharSequence) str3, 0, charSequence, a3, str3.length(), z)) {
                                break;
                            }
                        }
                        String str4 = (String) obj;
                        if (str4 == null) {
                            if (a3 == b2) {
                                break;
                            }
                            a3 += c2;
                        } else {
                            return kotlin.f.a(Integer.valueOf(a3), str4);
                        }
                    }
                }
            }
            return null;
        }
        String str5 = (String) o.c(collection);
        int a4 = !z2 ? m.a(charSequence, str5, i, false, 4, (Object) null) : m.b(charSequence, str5, i, false, 4, null);
        if (a4 >= 0) {
            pair = kotlin.f.a(Integer.valueOf(a4), str5);
        }
        return pair;
    }

    public static /* bridge */ /* synthetic */ int a(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return m.a(charSequence, str, i, z);
    }

    public static final int a(CharSequence charSequence, String str, int i, boolean z) {
        p.b(charSequence, "$receiver");
        p.b(str, "string");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(str, i);
        }
        return a(charSequence, str, i, charSequence.length(), z, false, 16, null);
    }

    public static /* synthetic */ int a(CharSequence charSequence, char c, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = m.e(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return m.a(charSequence, c, i, z);
    }

    public static final int a(CharSequence charSequence, char c, int i, boolean z) {
        p.b(charSequence, "$receiver");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(c, i);
        }
        return m.b(charSequence, new char[]{c}, i, z);
    }

    public static /* synthetic */ int b(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = m.e(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return m.b(charSequence, str, i, z);
    }

    public static final int b(CharSequence charSequence, String str, int i, boolean z) {
        p.b(charSequence, "$receiver");
        p.b(str, "string");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(str, i);
        }
        return a(charSequence, (CharSequence) str, i, 0, z, true);
    }

    public static /* bridge */ /* synthetic */ boolean a(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return m.a(charSequence, charSequence2, z);
    }

    public static final boolean a(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        p.b(charSequence, "$receiver");
        p.b(charSequence2, ResponseConstants.OTHER);
        if (charSequence2 instanceof String) {
            if (m.a(charSequence, (String) charSequence2, 0, z, 2, (Object) null) < 0) {
                return false;
            }
        } else {
            if (a(charSequence, charSequence2, 0, charSequence.length(), z, false, 16, null) < 0) {
                return false;
            }
        }
        return true;
    }

    static /* bridge */ /* synthetic */ kotlin.sequences.c a(CharSequence charSequence, String[] strArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return a(charSequence, strArr, i, z, i2);
    }

    private static final kotlin.sequences.c<c> a(CharSequence charSequence, String[] strArr, int i, boolean z, int i2) {
        if (i2 >= 0) {
            return new e<>(charSequence, i, i2, new StringsKt__StringsKt$rangesDelimitedBy$4(f.a(strArr), z));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Limit must be non-negative, but was ");
        sb.append(i2);
        sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
        throw new IllegalArgumentException(sb.toString().toString());
    }

    public static /* bridge */ /* synthetic */ kotlin.sequences.c a(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return m.a(charSequence, strArr, z, i);
    }

    public static final kotlin.sequences.c<String> a(CharSequence charSequence, String[] strArr, boolean z, int i) {
        p.b(charSequence, "$receiver");
        p.b(strArr, "delimiters");
        return kotlin.sequences.d.c(a(charSequence, strArr, 0, z, i, 2, null), new StringsKt__StringsKt$splitToSequence$1(charSequence));
    }

    public static final kotlin.sequences.c<String> f(CharSequence charSequence) {
        p.b(charSequence, "$receiver");
        return m.a(charSequence, new String[]{"\r\n", "\n", "\r"}, false, 0, 6, (Object) null);
    }

    public static final List<String> g(CharSequence charSequence) {
        p.b(charSequence, "$receiver");
        return kotlin.sequences.d.b(m.f(charSequence));
    }

    public static final CharSequence b(CharSequence charSequence) {
        p.b(charSequence, "$receiver");
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean a2 = a.a(charSequence.charAt(!z ? i : length));
            if (!z) {
                if (!a2) {
                    z = true;
                } else {
                    i++;
                }
            } else if (!a2) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }
}
