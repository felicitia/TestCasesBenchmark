package com.otaliastudios.cameraview;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class CameraLogger {
    static String lastMessage;
    static String lastTag;
    private static int sLevel;
    private static List<Logger> sLoggers = new ArrayList();
    private String mTag;

    public interface Logger {
        void log(int i, String str, String str2, Throwable th);
    }

    static {
        setLogLevel(3);
        sLoggers.add(new Logger() {
            public void log(int i, String str, String str2, Throwable th) {
                switch (i) {
                    case 0:
                        Log.v(str, str2, th);
                        return;
                    case 1:
                        Log.i(str, str2, th);
                        return;
                    case 2:
                        Log.w(str, str2, th);
                        return;
                    case 3:
                        Log.e(str, str2, th);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    static CameraLogger create(String str) {
        return new CameraLogger(str);
    }

    public static void setLogLevel(int i) {
        sLevel = i;
    }

    private CameraLogger(String str) {
        this.mTag = str;
    }

    private boolean should(int i) {
        return sLevel <= i && sLoggers.size() > 0;
    }

    /* access modifiers changed from: 0000 */
    public void v(Object... objArr) {
        log(0, objArr);
    }

    /* access modifiers changed from: 0000 */
    public void i(Object... objArr) {
        log(1, objArr);
    }

    /* access modifiers changed from: 0000 */
    public void w(Object... objArr) {
        log(2, objArr);
    }

    /* access modifiers changed from: 0000 */
    public void e(Object... objArr) {
        log(3, objArr);
    }

    private void log(int i, Object... objArr) {
        if (should(i)) {
            String str = "";
            Throwable th = null;
            int length = objArr.length;
            for (Object obj : objArr) {
                if (obj instanceof Throwable) {
                    th = (Throwable) obj;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(String.valueOf(obj));
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(sb2);
                sb3.append(" ");
                str = sb3.toString();
            }
            String trim = str.trim();
            for (Logger log : sLoggers) {
                log.log(i, this.mTag, trim, th);
            }
            lastMessage = trim;
            lastTag = this.mTag;
        }
    }
}
