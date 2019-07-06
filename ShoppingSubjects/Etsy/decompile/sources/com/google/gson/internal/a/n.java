package com.google.gson.internal.a;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.e;
import com.google.gson.h;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.k;
import com.google.gson.l;
import com.google.gson.m;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.b;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* compiled from: TypeAdapters */
public final class n {
    public static final q<String> A = new q<String>() {
        /* renamed from: a */
        public String b(com.google.gson.stream.a aVar) throws IOException {
            JsonToken f = aVar.f();
            if (f == JsonToken.NULL) {
                aVar.j();
                return null;
            } else if (f == JsonToken.BOOLEAN) {
                return Boolean.toString(aVar.i());
            } else {
                return aVar.h();
            }
        }

        public void a(b bVar, String str) throws IOException {
            bVar.b(str);
        }
    };
    public static final q<BigDecimal> B = new q<BigDecimal>() {
        /* renamed from: a */
        public BigDecimal b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            try {
                return new BigDecimal(aVar.h());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException((Throwable) e);
            }
        }

        public void a(b bVar, BigDecimal bigDecimal) throws IOException {
            bVar.a((Number) bigDecimal);
        }
    };
    public static final q<BigInteger> C = new q<BigInteger>() {
        /* renamed from: a */
        public BigInteger b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            try {
                return new BigInteger(aVar.h());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException((Throwable) e);
            }
        }

        public void a(b bVar, BigInteger bigInteger) throws IOException {
            bVar.a((Number) bigInteger);
        }
    };
    public static final r D = a(String.class, A);
    public static final q<StringBuilder> E = new q<StringBuilder>() {
        /* renamed from: a */
        public StringBuilder b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return new StringBuilder(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, StringBuilder sb) throws IOException {
            bVar.b(sb == null ? null : sb.toString());
        }
    };
    public static final r F = a(StringBuilder.class, E);
    public static final q<StringBuffer> G = new q<StringBuffer>() {
        /* renamed from: a */
        public StringBuffer b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return new StringBuffer(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, StringBuffer stringBuffer) throws IOException {
            bVar.b(stringBuffer == null ? null : stringBuffer.toString());
        }
    };
    public static final r H = a(StringBuffer.class, G);
    public static final q<URL> I = new q<URL>() {
        /* renamed from: a */
        public URL b(com.google.gson.stream.a aVar) throws IOException {
            URL url = null;
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            String h = aVar.h();
            if (!"null".equals(h)) {
                url = new URL(h);
            }
            return url;
        }

        public void a(b bVar, URL url) throws IOException {
            bVar.b(url == null ? null : url.toExternalForm());
        }
    };
    public static final r J = a(URL.class, I);
    public static final q<URI> K = new q<URI>() {
        /* renamed from: a */
        public URI b(com.google.gson.stream.a aVar) throws IOException {
            URI uri = null;
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            try {
                String h = aVar.h();
                if (!"null".equals(h)) {
                    uri = new URI(h);
                }
                return uri;
            } catch (URISyntaxException e) {
                throw new JsonIOException((Throwable) e);
            }
        }

        public void a(b bVar, URI uri) throws IOException {
            bVar.b(uri == null ? null : uri.toASCIIString());
        }
    };
    public static final r L = a(URI.class, K);
    public static final q<InetAddress> M = new q<InetAddress>() {
        /* renamed from: a */
        public InetAddress b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return InetAddress.getByName(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, InetAddress inetAddress) throws IOException {
            bVar.b(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    };
    public static final r N = b(InetAddress.class, M);
    public static final q<UUID> O = new q<UUID>() {
        /* renamed from: a */
        public UUID b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return UUID.fromString(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, UUID uuid) throws IOException {
            bVar.b(uuid == null ? null : uuid.toString());
        }
    };
    public static final r P = a(UUID.class, O);
    public static final q<Currency> Q = new q<Currency>() {
        /* renamed from: a */
        public Currency b(com.google.gson.stream.a aVar) throws IOException {
            return Currency.getInstance(aVar.h());
        }

        public void a(b bVar, Currency currency) throws IOException {
            bVar.b(currency.getCurrencyCode());
        }
    }.a();
    public static final r R = a(Currency.class, Q);
    public static final r S = new r() {
        public <T> q<T> a(e eVar, com.google.gson.a.a<T> aVar) {
            if (aVar.a() != Timestamp.class) {
                return null;
            }
            final q a = eVar.a(Date.class);
            return new q<Timestamp>() {
                /* renamed from: a */
                public Timestamp b(com.google.gson.stream.a aVar) throws IOException {
                    Date date = (Date) a.b(aVar);
                    if (date != null) {
                        return new Timestamp(date.getTime());
                    }
                    return null;
                }

                public void a(b bVar, Timestamp timestamp) throws IOException {
                    a.a(bVar, timestamp);
                }
            };
        }
    };
    public static final q<Calendar> T = new q<Calendar>() {
        /* renamed from: a */
        public Calendar b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            aVar.c();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (aVar.f() != JsonToken.END_OBJECT) {
                String g = aVar.g();
                int m = aVar.m();
                if ("year".equals(g)) {
                    i = m;
                } else if ("month".equals(g)) {
                    i2 = m;
                } else if ("dayOfMonth".equals(g)) {
                    i3 = m;
                } else if ("hourOfDay".equals(g)) {
                    i4 = m;
                } else if ("minute".equals(g)) {
                    i5 = m;
                } else if ("second".equals(g)) {
                    i6 = m;
                }
            }
            aVar.d();
            GregorianCalendar gregorianCalendar = new GregorianCalendar(i, i2, i3, i4, i5, i6);
            return gregorianCalendar;
        }

        public void a(b bVar, Calendar calendar) throws IOException {
            if (calendar == null) {
                bVar.f();
                return;
            }
            bVar.d();
            bVar.a("year");
            bVar.a((long) calendar.get(1));
            bVar.a("month");
            bVar.a((long) calendar.get(2));
            bVar.a("dayOfMonth");
            bVar.a((long) calendar.get(5));
            bVar.a("hourOfDay");
            bVar.a((long) calendar.get(11));
            bVar.a("minute");
            bVar.a((long) calendar.get(12));
            bVar.a("second");
            bVar.a((long) calendar.get(13));
            bVar.e();
        }
    };
    public static final r U = b(Calendar.class, GregorianCalendar.class, T);
    public static final q<Locale> V = new q<Locale>() {
        /* renamed from: a */
        public Locale b(com.google.gson.stream.a aVar) throws IOException {
            String str = null;
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(aVar.h(), "_");
            String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            if (stringTokenizer.hasMoreElements()) {
                str = stringTokenizer.nextToken();
            }
            if (nextToken2 == null && str == null) {
                return new Locale(nextToken);
            }
            if (str == null) {
                return new Locale(nextToken, nextToken2);
            }
            return new Locale(nextToken, nextToken2, str);
        }

        public void a(b bVar, Locale locale) throws IOException {
            bVar.b(locale == null ? null : locale.toString());
        }
    };
    public static final r W = a(Locale.class, V);
    public static final q<k> X = new q<k>() {
        /* renamed from: a */
        public k b(com.google.gson.stream.a aVar) throws IOException {
            switch (AnonymousClass30.a[aVar.f().ordinal()]) {
                case 1:
                    return new com.google.gson.n((Number) new LazilyParsedNumber(aVar.h()));
                case 2:
                    return new com.google.gson.n(Boolean.valueOf(aVar.i()));
                case 3:
                    return new com.google.gson.n(aVar.h());
                case 4:
                    aVar.j();
                    return l.a;
                case 5:
                    h hVar = new h();
                    aVar.a();
                    while (aVar.e()) {
                        hVar.a(b(aVar));
                    }
                    aVar.b();
                    return hVar;
                case 6:
                    m mVar = new m();
                    aVar.c();
                    while (aVar.e()) {
                        mVar.a(aVar.g(), b(aVar));
                    }
                    aVar.d();
                    return mVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public void a(b bVar, k kVar) throws IOException {
            if (kVar == null || kVar.j()) {
                bVar.f();
            } else if (kVar.i()) {
                com.google.gson.n m = kVar.m();
                if (m.p()) {
                    bVar.a(m.a());
                } else if (m.o()) {
                    bVar.a(m.f());
                } else {
                    bVar.b(m.b());
                }
            } else if (kVar.g()) {
                bVar.b();
                Iterator it = kVar.l().iterator();
                while (it.hasNext()) {
                    a(bVar, (k) it.next());
                }
                bVar.c();
            } else if (kVar.h()) {
                bVar.d();
                for (Entry entry : kVar.k().o()) {
                    bVar.a((String) entry.getKey());
                    a(bVar, (k) entry.getValue());
                }
                bVar.e();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Couldn't write ");
                sb.append(kVar.getClass());
                throw new IllegalArgumentException(sb.toString());
            }
        }
    };
    public static final r Y = b(k.class, X);
    public static final r Z = new r() {
        public <T> q<T> a(e eVar, com.google.gson.a.a<T> aVar) {
            Class<Enum> a = aVar.a();
            if (!Enum.class.isAssignableFrom(a) || a == Enum.class) {
                return null;
            }
            if (!a.isEnum()) {
                a = a.getSuperclass();
            }
            return new a(a);
        }
    };
    public static final q<Class> a = new q<Class>() {
        public void a(b bVar, Class cls) throws IOException {
            StringBuilder sb = new StringBuilder();
            sb.append("Attempted to serialize java.lang.Class: ");
            sb.append(cls.getName());
            sb.append(". Forgot to register a type adapter?");
            throw new UnsupportedOperationException(sb.toString());
        }

        /* renamed from: a */
        public Class b(com.google.gson.stream.a aVar) throws IOException {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    }.a();
    public static final r b = a(Class.class, a);
    public static final q<BitSet> c = new q<BitSet>() {
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x003d, code lost:
            if (java.lang.Integer.parseInt(r1) != 0) goto L_0x0064;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0040, code lost:
            r5 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0062, code lost:
            if (r7.m() != 0) goto L_0x0064;
         */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.BitSet b(com.google.gson.stream.a r7) throws java.io.IOException {
            /*
                r6 = this;
                java.util.BitSet r0 = new java.util.BitSet
                r0.<init>()
                r7.a()
                com.google.gson.stream.JsonToken r1 = r7.f()
                r2 = 0
                r3 = r2
            L_0x000e:
                com.google.gson.stream.JsonToken r4 = com.google.gson.stream.JsonToken.END_ARRAY
                if (r1 == r4) goto L_0x0070
                int[] r4 = com.google.gson.internal.a.n.AnonymousClass30.a
                int r5 = r1.ordinal()
                r4 = r4[r5]
                r5 = 1
                switch(r4) {
                    case 1: goto L_0x005e;
                    case 2: goto L_0x0059;
                    case 3: goto L_0x0035;
                    default: goto L_0x001e;
                }
            L_0x001e:
                com.google.gson.JsonSyntaxException r7 = new com.google.gson.JsonSyntaxException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "Invalid bitset value type: "
                r0.append(r2)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r7.<init>(r0)
                throw r7
            L_0x0035:
                java.lang.String r1 = r7.h()
                int r4 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x0042 }
                if (r4 == 0) goto L_0x0040
                goto L_0x0064
            L_0x0040:
                r5 = r2
                goto L_0x0064
            L_0x0042:
                com.google.gson.JsonSyntaxException r7 = new com.google.gson.JsonSyntaxException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "Error: Expecting: bitset number value (1, 0), Found: "
                r0.append(r2)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r7.<init>(r0)
                throw r7
            L_0x0059:
                boolean r5 = r7.i()
                goto L_0x0064
            L_0x005e:
                int r1 = r7.m()
                if (r1 == 0) goto L_0x0040
            L_0x0064:
                if (r5 == 0) goto L_0x0069
                r0.set(r3)
            L_0x0069:
                int r3 = r3 + 1
                com.google.gson.stream.JsonToken r1 = r7.f()
                goto L_0x000e
            L_0x0070:
                r7.b()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.a.n.AnonymousClass12.b(com.google.gson.stream.a):java.util.BitSet");
        }

        public void a(b bVar, BitSet bitSet) throws IOException {
            bVar.b();
            int length = bitSet.length();
            for (int i = 0; i < length; i++) {
                bVar.a(bitSet.get(i) ? 1 : 0);
            }
            bVar.c();
        }
    }.a();
    public static final r d = a(BitSet.class, c);
    public static final q<Boolean> e = new q<Boolean>() {
        /* renamed from: a */
        public Boolean b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            } else if (aVar.f() == JsonToken.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(aVar.h()));
            } else {
                return Boolean.valueOf(aVar.i());
            }
        }

        public void a(b bVar, Boolean bool) throws IOException {
            bVar.a(bool);
        }
    };
    public static final q<Boolean> f = new q<Boolean>() {
        /* renamed from: a */
        public Boolean b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return Boolean.valueOf(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, Boolean bool) throws IOException {
            bVar.b(bool == null ? "null" : bool.toString());
        }
    };
    public static final r g = a(Boolean.TYPE, Boolean.class, e);
    public static final q<Number> h = new q<Number>() {
        /* renamed from: a */
        public Number b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Byte.valueOf((byte) aVar.m());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException((Throwable) e);
            }
        }

        public void a(b bVar, Number number) throws IOException {
            bVar.a(number);
        }
    };
    public static final r i = a(Byte.TYPE, Byte.class, h);
    public static final q<Number> j = new q<Number>() {
        /* renamed from: a */
        public Number b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Short.valueOf((short) aVar.m());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException((Throwable) e);
            }
        }

        public void a(b bVar, Number number) throws IOException {
            bVar.a(number);
        }
    };
    public static final r k = a(Short.TYPE, Short.class, j);
    public static final q<Number> l = new q<Number>() {
        /* renamed from: a */
        public Number b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Integer.valueOf(aVar.m());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException((Throwable) e);
            }
        }

        public void a(b bVar, Number number) throws IOException {
            bVar.a(number);
        }
    };
    public static final r m = a(Integer.TYPE, Integer.class, l);
    public static final q<AtomicInteger> n = new q<AtomicInteger>() {
        /* renamed from: a */
        public AtomicInteger b(com.google.gson.stream.a aVar) throws IOException {
            try {
                return new AtomicInteger(aVar.m());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException((Throwable) e);
            }
        }

        public void a(b bVar, AtomicInteger atomicInteger) throws IOException {
            bVar.a((long) atomicInteger.get());
        }
    }.a();
    public static final r o = a(AtomicInteger.class, n);
    public static final q<AtomicBoolean> p = new q<AtomicBoolean>() {
        /* renamed from: a */
        public AtomicBoolean b(com.google.gson.stream.a aVar) throws IOException {
            return new AtomicBoolean(aVar.i());
        }

        public void a(b bVar, AtomicBoolean atomicBoolean) throws IOException {
            bVar.a(atomicBoolean.get());
        }
    }.a();
    public static final r q = a(AtomicBoolean.class, p);
    public static final q<AtomicIntegerArray> r = new q<AtomicIntegerArray>() {
        /* renamed from: a */
        public AtomicIntegerArray b(com.google.gson.stream.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.a();
            while (aVar.e()) {
                try {
                    arrayList.add(Integer.valueOf(aVar.m()));
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException((Throwable) e);
                }
            }
            aVar.b();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        public void a(b bVar, AtomicIntegerArray atomicIntegerArray) throws IOException {
            bVar.b();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                bVar.a((long) atomicIntegerArray.get(i));
            }
            bVar.c();
        }
    }.a();
    public static final r s = a(AtomicIntegerArray.class, r);
    public static final q<Number> t = new q<Number>() {
        /* renamed from: a */
        public Number b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Long.valueOf(aVar.l());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException((Throwable) e);
            }
        }

        public void a(b bVar, Number number) throws IOException {
            bVar.a(number);
        }
    };
    public static final q<Number> u = new q<Number>() {
        /* renamed from: a */
        public Number b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return Float.valueOf((float) aVar.k());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, Number number) throws IOException {
            bVar.a(number);
        }
    };
    public static final q<Number> v = new q<Number>() {
        /* renamed from: a */
        public Number b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return Double.valueOf(aVar.k());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, Number number) throws IOException {
            bVar.a(number);
        }
    };
    public static final q<Number> w = new q<Number>() {
        /* renamed from: a */
        public Number b(com.google.gson.stream.a aVar) throws IOException {
            JsonToken f = aVar.f();
            int i = AnonymousClass30.a[f.ordinal()];
            if (i != 1) {
                switch (i) {
                    case 3:
                        break;
                    case 4:
                        aVar.j();
                        return null;
                    default:
                        StringBuilder sb = new StringBuilder();
                        sb.append("Expecting number, got: ");
                        sb.append(f);
                        throw new JsonSyntaxException(sb.toString());
                }
            }
            return new LazilyParsedNumber(aVar.h());
        }

        public void a(b bVar, Number number) throws IOException {
            bVar.a(number);
        }
    };
    public static final r x = a(Number.class, w);
    public static final q<Character> y = new q<Character>() {
        /* renamed from: a */
        public Character b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            String h = aVar.h();
            if (h.length() == 1) {
                return Character.valueOf(h.charAt(0));
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Expecting character, got: ");
            sb.append(h);
            throw new JsonSyntaxException(sb.toString());
        }

        public void a(b bVar, Character ch) throws IOException {
            bVar.b(ch == null ? null : String.valueOf(ch));
        }
    };
    public static final r z = a(Character.TYPE, Character.class, y);

    /* renamed from: com.google.gson.internal.a.n$30 reason: invalid class name */
    /* compiled from: TypeAdapters */
    static /* synthetic */ class AnonymousClass30 {
        static final /* synthetic */ int[] a = new int[JsonToken.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.google.gson.stream.JsonToken[] r0 = com.google.gson.stream.JsonToken.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.NUMBER     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.STRING     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.NULL     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.BEGIN_ARRAY     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.BEGIN_OBJECT     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.END_DOCUMENT     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.NAME     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x006e }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.END_OBJECT     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x007a }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.END_ARRAY     // Catch:{ NoSuchFieldError -> 0x007a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007a }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007a }
            L_0x007a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.a.n.AnonymousClass30.<clinit>():void");
        }
    }

    /* compiled from: TypeAdapters */
    private static final class a<T extends Enum<T>> extends q<T> {
        private final Map<String, T> a = new HashMap();
        private final Map<T, String> b = new HashMap();

        public a(Class<T> cls) {
            Enum[] enumArr;
            try {
                for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                    String name = enumR.name();
                    com.google.gson.annotations.b bVar = (com.google.gson.annotations.b) cls.getField(name).getAnnotation(com.google.gson.annotations.b.class);
                    if (bVar != null) {
                        name = bVar.a();
                        for (String put : bVar.b()) {
                            this.a.put(put, enumR);
                        }
                    }
                    this.a.put(name, enumR);
                    this.b.put(enumR, name);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        /* renamed from: a */
        public T b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return (Enum) this.a.get(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, T t) throws IOException {
            bVar.b(t == null ? null : (String) this.b.get(t));
        }
    }

    public static <TT> r a(final com.google.gson.a.a<TT> aVar, final q<TT> qVar) {
        return new r() {
            public <T> q<T> a(e eVar, com.google.gson.a.a<T> aVar) {
                if (aVar.equals(aVar)) {
                    return qVar;
                }
                return null;
            }
        };
    }

    public static <TT> r a(final Class<TT> cls, final q<TT> qVar) {
        return new r() {
            public <T> q<T> a(e eVar, com.google.gson.a.a<T> aVar) {
                if (aVar.a() == cls) {
                    return qVar;
                }
                return null;
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("Factory[type=");
                sb.append(cls.getName());
                sb.append(",adapter=");
                sb.append(qVar);
                sb.append("]");
                return sb.toString();
            }
        };
    }

    public static <TT> r a(final Class<TT> cls, final Class<TT> cls2, final q<? super TT> qVar) {
        return new r() {
            public <T> q<T> a(e eVar, com.google.gson.a.a<T> aVar) {
                Class a2 = aVar.a();
                if (a2 == cls || a2 == cls2) {
                    return qVar;
                }
                return null;
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("Factory[type=");
                sb.append(cls2.getName());
                sb.append("+");
                sb.append(cls.getName());
                sb.append(",adapter=");
                sb.append(qVar);
                sb.append("]");
                return sb.toString();
            }
        };
    }

    public static <TT> r b(final Class<TT> cls, final Class<? extends TT> cls2, final q<? super TT> qVar) {
        return new r() {
            public <T> q<T> a(e eVar, com.google.gson.a.a<T> aVar) {
                Class a2 = aVar.a();
                if (a2 == cls || a2 == cls2) {
                    return qVar;
                }
                return null;
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("Factory[type=");
                sb.append(cls.getName());
                sb.append("+");
                sb.append(cls2.getName());
                sb.append(",adapter=");
                sb.append(qVar);
                sb.append("]");
                return sb.toString();
            }
        };
    }

    public static <T1> r b(final Class<T1> cls, final q<T1> qVar) {
        return new r() {
            public <T2> q<T2> a(e eVar, com.google.gson.a.a<T2> aVar) {
                final Class a2 = aVar.a();
                if (!cls.isAssignableFrom(a2)) {
                    return null;
                }
                return new q<T1>() {
                    public void a(b bVar, T1 t1) throws IOException {
                        qVar.a(bVar, t1);
                    }

                    public T1 b(com.google.gson.stream.a aVar) throws IOException {
                        T1 b2 = qVar.b(aVar);
                        if (b2 == null || a2.isInstance(b2)) {
                            return b2;
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append("Expected a ");
                        sb.append(a2.getName());
                        sb.append(" but was ");
                        sb.append(b2.getClass().getName());
                        throw new JsonSyntaxException(sb.toString());
                    }
                };
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("Factory[typeHierarchy=");
                sb.append(cls.getName());
                sb.append(",adapter=");
                sb.append(qVar);
                sb.append("]");
                return sb.toString();
            }
        };
    }
}
