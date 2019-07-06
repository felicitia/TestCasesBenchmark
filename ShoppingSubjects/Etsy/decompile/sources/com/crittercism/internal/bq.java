package com.crittercism.internal;

public class bq extends Throwable {
    private static final long serialVersionUID = -1947260712494608235L;
    public String a = null;

    public bq(String str, String str2, String str3) {
        String[] strArr;
        super(str2);
        if (str == null) {
            str = "";
        }
        if (str.length() > 0) {
            this.a = str;
        } else {
            this.a = "JavaScript Exception";
        }
        if (str3 == null) {
            strArr = new String[0];
        } else {
            strArr = str3.split("\\r?\\n");
        }
        setStackTrace(a(strArr));
    }

    private static StackTraceElement[] a(String[] strArr) {
        StackTraceElement[] stackTraceElementArr;
        boolean z = true;
        if (strArr.length < 2 || strArr[0] == null || strArr[1] == null || !strArr[0].equals(strArr[1])) {
            stackTraceElementArr = null;
            z = false;
        } else {
            stackTraceElementArr = new StackTraceElement[(strArr.length - 1)];
        }
        if (!z) {
            stackTraceElementArr = new StackTraceElement[strArr.length];
        }
        for (int i = 0; i < strArr.length; i++) {
            if (i != 0 || !z) {
                stackTraceElementArr[z ? i - 1 : i] = new StackTraceElement("", strArr[i], "", -1);
            }
        }
        return stackTraceElementArr;
    }

    public String toString() {
        String localizedMessage = getLocalizedMessage();
        String str = this.a;
        if (localizedMessage == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(": ");
        sb.append(localizedMessage);
        return sb.toString();
    }
}
