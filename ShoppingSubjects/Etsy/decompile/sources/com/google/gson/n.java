package com.google.gson;

import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.a;
import java.math.BigInteger;

/* compiled from: JsonPrimitive */
public final class n extends k {
    private static final Class<?>[] a = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object b;

    public n(Boolean bool) {
        a((Object) bool);
    }

    public n(Number number) {
        a((Object) number);
    }

    public n(String str) {
        a((Object) str);
    }

    /* access modifiers changed from: 0000 */
    public void a(Object obj) {
        if (obj instanceof Character) {
            this.b = String.valueOf(((Character) obj).charValue());
            return;
        }
        a.a((obj instanceof Number) || b(obj));
        this.b = obj;
    }

    public boolean o() {
        return this.b instanceof Boolean;
    }

    /* access modifiers changed from: 0000 */
    public Boolean n() {
        return (Boolean) this.b;
    }

    public boolean f() {
        if (o()) {
            return n().booleanValue();
        }
        return Boolean.parseBoolean(b());
    }

    public boolean p() {
        return this.b instanceof Number;
    }

    public Number a() {
        return this.b instanceof String ? new LazilyParsedNumber((String) this.b) : (Number) this.b;
    }

    public boolean q() {
        return this.b instanceof String;
    }

    public String b() {
        if (p()) {
            return a().toString();
        }
        if (o()) {
            return n().toString();
        }
        return (String) this.b;
    }

    public double c() {
        return p() ? a().doubleValue() : Double.parseDouble(b());
    }

    public long d() {
        return p() ? a().longValue() : Long.parseLong(b());
    }

    public int e() {
        return p() ? a().intValue() : Integer.parseInt(b());
    }

    private static boolean b(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class cls = obj.getClass();
        for (Class<?> isAssignableFrom : a) {
            if (isAssignableFrom.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.b == null) {
            return 31;
        }
        if (a(this)) {
            long longValue = a().longValue();
            return (int) (longValue ^ (longValue >>> 32));
        } else if (!(this.b instanceof Number)) {
            return this.b.hashCode();
        } else {
            long doubleToLongBits = Double.doubleToLongBits(a().doubleValue());
            return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        n nVar = (n) obj;
        if (this.b == null) {
            if (nVar.b != null) {
                z = false;
            }
            return z;
        } else if (a(this) && a(nVar)) {
            if (a().longValue() != nVar.a().longValue()) {
                z = false;
            }
            return z;
        } else if (!(this.b instanceof Number) || !(nVar.b instanceof Number)) {
            return this.b.equals(nVar.b);
        } else {
            double doubleValue = a().doubleValue();
            double doubleValue2 = nVar.a().doubleValue();
            if (doubleValue != doubleValue2 && (!Double.isNaN(doubleValue) || !Double.isNaN(doubleValue2))) {
                z = false;
            }
            return z;
        }
    }

    private static boolean a(n nVar) {
        boolean z = false;
        if (!(nVar.b instanceof Number)) {
            return false;
        }
        Number number = (Number) nVar.b;
        if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
            z = true;
        }
        return z;
    }
}
