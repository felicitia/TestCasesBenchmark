package org.apache.commons.lang3.time;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

abstract class FormatCache<F extends Format> {
    static final int NONE = -1;
    private final ConcurrentMap<MultipartKey, String> cDateTimeInstanceCache = new ConcurrentHashMap(7);
    private final ConcurrentMap<MultipartKey, F> cInstanceCache = new ConcurrentHashMap(7);

    private static class MultipartKey {
        private int hashCode;
        private final Object[] keys;

        public MultipartKey(Object... objArr) {
            this.keys = objArr;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MultipartKey)) {
                return false;
            }
            return Arrays.equals(this.keys, ((MultipartKey) obj).keys);
        }

        public int hashCode() {
            Object[] objArr;
            if (this.hashCode == 0) {
                int i = 0;
                for (Object obj : this.keys) {
                    if (obj != null) {
                        i = (i * 7) + obj.hashCode();
                    }
                }
                this.hashCode = i;
            }
            return this.hashCode;
        }
    }

    /* access modifiers changed from: protected */
    public abstract F createInstance(String str, TimeZone timeZone, Locale locale);

    FormatCache() {
    }

    public F getInstance() {
        return getDateTimeInstance(Integer.valueOf(3), Integer.valueOf(3), TimeZone.getDefault(), Locale.getDefault());
    }

    public F getInstance(String str, TimeZone timeZone, Locale locale) {
        if (str == null) {
            throw new NullPointerException("pattern must not be null");
        }
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        MultipartKey multipartKey = new MultipartKey(str, timeZone, locale);
        F f = (Format) this.cInstanceCache.get(multipartKey);
        if (f != null) {
            return f;
        }
        F createInstance = createInstance(str, timeZone, locale);
        Format format = (Format) this.cInstanceCache.putIfAbsent(multipartKey, createInstance);
        return format != null ? format : createInstance;
    }

    public F getDateTimeInstance(Integer num, Integer num2, TimeZone timeZone, Locale locale) {
        DateFormat dateFormat;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        MultipartKey multipartKey = new MultipartKey(num, num2, locale);
        String str = (String) this.cDateTimeInstanceCache.get(multipartKey);
        if (str == null) {
            if (num == null) {
                try {
                    dateFormat = DateFormat.getTimeInstance(num2.intValue(), locale);
                } catch (ClassCastException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("No date time pattern for locale: ");
                    sb.append(locale);
                    throw new IllegalArgumentException(sb.toString());
                }
            } else if (num2 == null) {
                dateFormat = DateFormat.getDateInstance(num.intValue(), locale);
            } else {
                dateFormat = DateFormat.getDateTimeInstance(num.intValue(), num2.intValue(), locale);
            }
            String pattern = ((SimpleDateFormat) dateFormat).toPattern();
            String str2 = (String) this.cDateTimeInstanceCache.putIfAbsent(multipartKey, pattern);
            str = str2 != null ? str2 : pattern;
        }
        return getInstance(str, timeZone, locale);
    }
}
