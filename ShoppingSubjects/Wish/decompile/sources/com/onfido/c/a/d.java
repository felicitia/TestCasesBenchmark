package com.onfido.c.a;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class d {
    static final d a = new a().a(true).b(false).a();
    private final boolean b;
    private final boolean c;

    /* renamed from: com.onfido.c.a.d$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[JsonToken.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|(3:11|12|14)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                android.util.JsonToken[] r0 = android.util.JsonToken.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.util.JsonToken r1 = android.util.JsonToken.BEGIN_OBJECT     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001f }
                android.util.JsonToken r1 = android.util.JsonToken.BEGIN_ARRAY     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x002a }
                android.util.JsonToken r1 = android.util.JsonToken.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.util.JsonToken r1 = android.util.JsonToken.NULL     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0040 }
                android.util.JsonToken r1 = android.util.JsonToken.NUMBER     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x004b }
                android.util.JsonToken r1 = android.util.JsonToken.STRING     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onfido.c.a.d.AnonymousClass1.<clinit>():void");
        }
    }

    public static class a {
        private boolean a;
        private boolean b;

        public a a(boolean z) {
            this.a = z;
            return this;
        }

        public d a() {
            return new d(this.a, this.b);
        }

        public a b(boolean z) {
            this.b = z;
            return this;
        }
    }

    d(boolean z, boolean z2) {
        this.b = z;
        this.c = z2;
    }

    private static Map<String, Object> a(JsonReader jsonReader) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            linkedHashMap.put(jsonReader.nextName(), c(jsonReader));
        }
        jsonReader.endObject();
        return linkedHashMap;
    }

    private static void a(Object obj, JsonWriter jsonWriter) {
        jsonWriter.beginArray();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            b(Array.get(obj, i), jsonWriter);
        }
        jsonWriter.endArray();
    }

    private static void a(List<?> list, JsonWriter jsonWriter) {
        jsonWriter.beginArray();
        for (Object b2 : list) {
            b(b2, jsonWriter);
        }
        jsonWriter.endArray();
    }

    private static void a(Map<?, ?> map, JsonWriter jsonWriter) {
        jsonWriter.beginObject();
        for (Entry entry : map.entrySet()) {
            jsonWriter.name(String.valueOf(entry.getKey()));
            b(entry.getValue(), jsonWriter);
        }
        jsonWriter.endObject();
    }

    private static List<Object> b(JsonReader jsonReader) {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(c(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    private static void b(Object obj, JsonWriter jsonWriter) {
        if (obj == null) {
            jsonWriter.nullValue();
        } else if (obj instanceof Number) {
            jsonWriter.value((Number) obj);
        } else if (obj instanceof Boolean) {
            jsonWriter.value(((Boolean) obj).booleanValue());
        } else if (obj instanceof List) {
            a((List) obj, jsonWriter);
        } else if (obj instanceof Map) {
            a((Map) obj, jsonWriter);
        } else if (obj.getClass().isArray()) {
            a(obj, jsonWriter);
        } else {
            jsonWriter.value(String.valueOf(obj));
        }
    }

    private static Object c(JsonReader jsonReader) {
        JsonToken peek = jsonReader.peek();
        switch (AnonymousClass1.a[peek.ordinal()]) {
            case 1:
                return a(jsonReader);
            case 2:
                return b(jsonReader);
            case 3:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case 4:
                jsonReader.nextNull();
                return null;
            case 5:
                return Double.valueOf(jsonReader.nextDouble());
            case 6:
                return jsonReader.nextString();
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid token ");
                sb.append(peek);
                throw new IllegalStateException(sb.toString());
        }
    }

    public String a(Map<?, ?> map) {
        StringWriter stringWriter = new StringWriter();
        try {
            a(map, (Writer) stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public Map<String, Object> a(Reader reader) {
        if (reader == null) {
            throw new IllegalArgumentException("reader == null");
        }
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.setLenient(this.b);
        try {
            return a(jsonReader);
        } finally {
            reader.close();
        }
    }

    public Map<String, Object> a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("json == null");
        } else if (str.length() != 0) {
            return a((Reader) new StringReader(str));
        } else {
            throw new IllegalArgumentException("json empty");
        }
    }

    public void a(Map<?, ?> map, Writer writer) {
        if (map == null) {
            throw new IllegalArgumentException("map == null");
        } else if (writer == null) {
            throw new IllegalArgumentException("writer == null");
        } else {
            JsonWriter jsonWriter = new JsonWriter(writer);
            jsonWriter.setLenient(this.b);
            if (this.c) {
                jsonWriter.setIndent("  ");
            }
            try {
                a(map, jsonWriter);
            } finally {
                jsonWriter.close();
            }
        }
    }
}
