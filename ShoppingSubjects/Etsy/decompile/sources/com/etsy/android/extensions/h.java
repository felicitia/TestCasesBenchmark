package com.etsy.android.extensions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;
import kotlin.text.m;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: Tag.kt */
public final class h {
    private static final Pattern a = Pattern.compile("(\\$\\d+)+$");

    public static final String a() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace.length <= 2) {
            throw new IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?");
        }
        StackTraceElement stackTraceElement = stackTrace[2];
        p.a((Object) stackTraceElement, "stackTrace[CALL_STACK_INDEX]");
        return a(stackTraceElement);
    }

    private static final String a(StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        Matcher matcher = a.matcher(className);
        if (matcher.find()) {
            className = matcher.replaceAll("");
        }
        p.a((Object) className, "tag");
        int a2 = m.a((CharSequence) className, (char) ClassUtils.PACKAGE_SEPARATOR_CHAR, 0, false, 6, (Object) null) + 1;
        if (className == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String substring = className.substring(a2);
        p.a((Object) substring, "(this as java.lang.String).substring(startIndex)");
        if (substring.length() <= 100) {
            return substring;
        }
        if (substring == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String substring2 = substring.substring(0, 100);
        p.a((Object) substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring2;
    }
}
