package okhttp3;

import com.etsy.android.lib.convos.Draft;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import okhttp3.internal.c;

/* compiled from: Headers */
public final class s {
    private final String[] a;

    /* compiled from: Headers */
    public static final class a {
        final List<String> a = new ArrayList(20);

        /* access modifiers changed from: 0000 */
        public a a(String str) {
            int indexOf = str.indexOf(Draft.IMAGE_DELIMITER, 1);
            if (indexOf != -1) {
                return b(str.substring(0, indexOf), str.substring(indexOf + 1));
            }
            if (str.startsWith(Draft.IMAGE_DELIMITER)) {
                return b("", str.substring(1));
            }
            return b("", str);
        }

        public a a(String str, String str2) {
            d(str, str2);
            return b(str, str2);
        }

        /* access modifiers changed from: 0000 */
        public a b(String str, String str2) {
            this.a.add(str);
            this.a.add(str2.trim());
            return this;
        }

        public a b(String str) {
            int i = 0;
            while (i < this.a.size()) {
                if (str.equalsIgnoreCase((String) this.a.get(i))) {
                    this.a.remove(i);
                    this.a.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        public a c(String str, String str2) {
            d(str, str2);
            b(str);
            b(str, str2);
            return this;
        }

        private void d(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            } else if (str.isEmpty()) {
                throw new IllegalArgumentException("name is empty");
            } else {
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    char charAt = str.charAt(i);
                    if (charAt <= ' ' || charAt >= 127) {
                        throw new IllegalArgumentException(c.a("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i), str));
                    }
                }
                if (str2 == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("value for name ");
                    sb.append(str);
                    sb.append(" == null");
                    throw new NullPointerException(sb.toString());
                }
                int length2 = str2.length();
                int i2 = 0;
                while (i2 < length2) {
                    char charAt2 = str2.charAt(i2);
                    if ((charAt2 > 31 || charAt2 == 9) && charAt2 < 127) {
                        i2++;
                    } else {
                        throw new IllegalArgumentException(c.a("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(charAt2), Integer.valueOf(i2), str, str2));
                    }
                }
            }
        }

        public s a() {
            return new s(this);
        }
    }

    s(a aVar) {
        this.a = (String[]) aVar.a.toArray(new String[aVar.a.size()]);
    }

    private s(String[] strArr) {
        this.a = strArr;
    }

    public String a(String str) {
        return a(this.a, str);
    }

    public int a() {
        return this.a.length / 2;
    }

    public String a(int i) {
        return this.a[i * 2];
    }

    public String b(int i) {
        return this.a[(i * 2) + 1];
    }

    public Set<String> b() {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        int a2 = a();
        for (int i = 0; i < a2; i++) {
            treeSet.add(a(i));
        }
        return Collections.unmodifiableSet(treeSet);
    }

    public List<String> b(String str) {
        int a2 = a();
        ArrayList arrayList = null;
        for (int i = 0; i < a2; i++) {
            if (str.equalsIgnoreCase(a(i))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(b(i));
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    public a c() {
        a aVar = new a();
        Collections.addAll(aVar.a, this.a);
        return aVar;
    }

    public boolean equals(Object obj) {
        return (obj instanceof s) && Arrays.equals(((s) obj).a, this.a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int a2 = a();
        for (int i = 0; i < a2; i++) {
            sb.append(a(i));
            sb.append(": ");
            sb.append(b(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    public Map<String, List<String>> d() {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        int a2 = a();
        for (int i = 0; i < a2; i++) {
            String lowerCase = a(i).toLowerCase(Locale.US);
            List list = (List) treeMap.get(lowerCase);
            if (list == null) {
                list = new ArrayList(2);
                treeMap.put(lowerCase, list);
            }
            list.add(b(i));
        }
        return treeMap;
    }

    private static String a(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    public static s a(String... strArr) {
        if (strArr == null) {
            throw new NullPointerException("namesAndValues == null");
        } else if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        } else {
            String[] strArr2 = (String[]) strArr.clone();
            for (int i = 0; i < strArr2.length; i++) {
                if (strArr2[i] == null) {
                    throw new IllegalArgumentException("Headers cannot be null");
                }
                strArr2[i] = strArr2[i].trim();
            }
            int i2 = 0;
            while (i2 < strArr2.length) {
                String str = strArr2[i2];
                String str2 = strArr2[i2 + 1];
                if (str.length() != 0 && str.indexOf(0) == -1 && str2.indexOf(0) == -1) {
                    i2 += 2;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unexpected header: ");
                    sb.append(str);
                    sb.append(": ");
                    sb.append(str2);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            return new s(strArr2);
        }
    }
}
