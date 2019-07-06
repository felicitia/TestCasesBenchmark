package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TypeParser implements Serializable {
    private static final long serialVersionUID = 1;
    protected final TypeFactory _factory;

    static final class a extends StringTokenizer {
        protected final String a;
        protected int b;
        protected String c;

        public a(String str) {
            super(str, "<,>", true);
            this.a = str;
        }

        public boolean hasMoreTokens() {
            return this.c != null || super.hasMoreTokens();
        }

        public String nextToken() {
            String str;
            if (this.c != null) {
                str = this.c;
                this.c = null;
            } else {
                str = super.nextToken();
            }
            this.b += str.length();
            return str;
        }

        public void a(String str) {
            this.c = str;
            this.b -= str.length();
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.a.substring(this.b);
        }
    }

    public TypeParser(TypeFactory typeFactory) {
        this._factory = typeFactory;
    }

    public JavaType parse(String str) throws IllegalArgumentException {
        a aVar = new a(str.trim());
        JavaType parseType = parseType(aVar);
        if (!aVar.hasMoreTokens()) {
            return parseType;
        }
        throw _problem(aVar, "Unexpected tokens after complete type");
    }

    /* access modifiers changed from: protected */
    public JavaType parseType(a aVar) throws IllegalArgumentException {
        if (!aVar.hasMoreTokens()) {
            throw _problem(aVar, "Unexpected end-of-string");
        }
        Class findClass = findClass(aVar.nextToken(), aVar);
        if (aVar.hasMoreTokens()) {
            String nextToken = aVar.nextToken();
            if ("<".equals(nextToken)) {
                return this._factory._fromParameterizedClass(findClass, parseTypes(aVar));
            }
            aVar.a(nextToken);
        }
        return this._factory._fromClass(findClass, null);
    }

    /* access modifiers changed from: protected */
    public List<JavaType> parseTypes(a aVar) throws IllegalArgumentException {
        ArrayList arrayList = new ArrayList();
        while (aVar.hasMoreTokens()) {
            arrayList.add(parseType(aVar));
            if (!aVar.hasMoreTokens()) {
                break;
            }
            String nextToken = aVar.nextToken();
            if (">".equals(nextToken)) {
                return arrayList;
            }
            if (!",".equals(nextToken)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected token '");
                sb.append(nextToken);
                sb.append("', expected ',' or '>')");
                throw _problem(aVar, sb.toString());
            }
        }
        throw _problem(aVar, "Unexpected end-of-string");
    }

    /* access modifiers changed from: protected */
    public Class<?> findClass(String str, a aVar) {
        try {
            return ClassUtil.findClass(str);
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Can not locate class '");
            sb.append(str);
            sb.append("', problem: ");
            sb.append(e.getMessage());
            throw _problem(aVar, sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public IllegalArgumentException _problem(a aVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("Failed to parse type '");
        sb.append(aVar.a());
        sb.append("' (remaining: '");
        sb.append(aVar.b());
        sb.append("'): ");
        sb.append(str);
        return new IllegalArgumentException(sb.toString());
    }
}
