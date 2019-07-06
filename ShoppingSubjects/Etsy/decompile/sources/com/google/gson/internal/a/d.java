package com.google.gson.internal.a;

import com.google.gson.a.a;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.e;
import com.google.gson.internal.b;
import com.google.gson.q;
import com.google.gson.r;

/* compiled from: JsonAdapterAnnotationTypeAdapterFactory */
public final class d implements r {
    private final b a;

    public d(b bVar) {
        this.a = bVar;
    }

    public <T> q<T> a(e eVar, a<T> aVar) {
        JsonAdapter jsonAdapter = (JsonAdapter) aVar.a().getAnnotation(JsonAdapter.class);
        if (jsonAdapter == null) {
            return null;
        }
        return a(this.a, eVar, aVar, jsonAdapter);
    }

    /* JADX WARNING: type inference failed for: r9v13, types: [com.google.gson.q] */
    /* JADX WARNING: type inference failed for: r9v14, types: [com.google.gson.q] */
    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.gson.q<?> a(com.google.gson.internal.b r9, com.google.gson.e r10, com.google.gson.a.a<?> r11, com.google.gson.annotations.JsonAdapter r12) {
        /*
            r8 = this;
            java.lang.Class r0 = r12.a()
            com.google.gson.a.a r0 = com.google.gson.a.a.b(r0)
            com.google.gson.internal.e r9 = r9.a(r0)
            java.lang.Object r9 = r9.a()
            boolean r0 = r9 instanceof com.google.gson.q
            if (r0 == 0) goto L_0x0017
            com.google.gson.q r9 = (com.google.gson.q) r9
            goto L_0x0075
        L_0x0017:
            boolean r0 = r9 instanceof com.google.gson.r
            if (r0 == 0) goto L_0x0022
            com.google.gson.r r9 = (com.google.gson.r) r9
            com.google.gson.q r9 = r9.a(r10, r11)
            goto L_0x0075
        L_0x0022:
            boolean r0 = r9 instanceof com.google.gson.p
            if (r0 != 0) goto L_0x005b
            boolean r1 = r9 instanceof com.google.gson.j
            if (r1 == 0) goto L_0x002b
            goto L_0x005b
        L_0x002b:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "Invalid attempt to bind an instance of "
            r12.append(r0)
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getName()
            r12.append(r9)
            java.lang.String r9 = " as a @JsonAdapter for "
            r12.append(r9)
            java.lang.String r9 = r11.toString()
            r12.append(r9)
            java.lang.String r9 = ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer."
            r12.append(r9)
            java.lang.String r9 = r12.toString()
            r10.<init>(r9)
            throw r10
        L_0x005b:
            r1 = 0
            if (r0 == 0) goto L_0x0063
            r0 = r9
            com.google.gson.p r0 = (com.google.gson.p) r0
            r3 = r0
            goto L_0x0064
        L_0x0063:
            r3 = r1
        L_0x0064:
            boolean r0 = r9 instanceof com.google.gson.j
            if (r0 == 0) goto L_0x006b
            r1 = r9
            com.google.gson.j r1 = (com.google.gson.j) r1
        L_0x006b:
            r4 = r1
            com.google.gson.internal.a.l r9 = new com.google.gson.internal.a.l
            r7 = 0
            r2 = r9
            r5 = r10
            r6 = r11
            r2.<init>(r3, r4, r5, r6, r7)
        L_0x0075:
            if (r9 == 0) goto L_0x0081
            boolean r10 = r12.b()
            if (r10 == 0) goto L_0x0081
            com.google.gson.q r9 = r9.a()
        L_0x0081:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.a.d.a(com.google.gson.internal.b, com.google.gson.e, com.google.gson.a.a, com.google.gson.annotations.JsonAdapter):com.google.gson.q");
    }
}
