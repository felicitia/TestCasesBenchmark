package com.crashlytics.android.core;

public class CrashlyticsMissingDependencyException extends RuntimeException {
    CrashlyticsMissingDependencyException(String str) {
        super(buildExceptionMessage(str));
    }

    private static String buildExceptionMessage(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(str);
        sb.append("\n");
        return sb.toString();
    }
}
