package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import java.io.PrintStream;
import java.util.regex.Pattern;

public class VersionUtil {
    public static final String PACKAGE_VERSION_CLASS_NAME = "PackageVersion";
    @Deprecated
    public static final String VERSION_FILE = "VERSION.txt";
    private static final Pattern VERSION_SEPARATOR = Pattern.compile("[-_./;:]");
    private final Version _version;

    protected VersionUtil() {
        Version version;
        try {
            version = versionFor(getClass());
        } catch (Exception unused) {
            PrintStream printStream = System.err;
            StringBuilder sb = new StringBuilder();
            sb.append("ERROR: Failed to load Version information for bundle (via ");
            sb.append(getClass().getName());
            sb.append(").");
            printStream.println(sb.toString());
            version = null;
        }
        if (version == null) {
            version = Version.unknownVersion();
        }
        this._version = version;
    }

    public Version version() {
        return this._version;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:19|20|21|22|23) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:7|8|9|10|11|12|13|14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002c, code lost:
        throw new java.lang.RuntimeException(r2);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0022 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0031 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0034 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.fasterxml.jackson.core.Version versionFor(java.lang.Class<?> r2) {
        /*
            com.fasterxml.jackson.core.Version r0 = packageVersionFor(r2)
            if (r0 == 0) goto L_0x0007
            return r0
        L_0x0007:
            java.lang.String r0 = "VERSION.txt"
            java.io.InputStream r2 = r2.getResourceAsStream(r0)
            if (r2 != 0) goto L_0x0014
            com.fasterxml.jackson.core.Version r2 = com.fasterxml.jackson.core.Version.unknownVersion()
            return r2
        L_0x0014:
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ UnsupportedEncodingException -> 0x0034 }
            java.lang.String r1 = "UTF-8"
            r0.<init>(r2, r1)     // Catch:{ UnsupportedEncodingException -> 0x0034 }
            com.fasterxml.jackson.core.Version r1 = doReadVersion(r0)     // Catch:{ all -> 0x002d }
            r0.close()     // Catch:{ IOException -> 0x0022 }
        L_0x0022:
            r2.close()     // Catch:{ IOException -> 0x0026 }
            return r1
        L_0x0026:
            r2 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r2)
            throw r0
        L_0x002d:
            r1 = move-exception
            r0.close()     // Catch:{ IOException -> 0x0031 }
        L_0x0031:
            throw r1     // Catch:{ UnsupportedEncodingException -> 0x0034 }
        L_0x0032:
            r0 = move-exception
            goto L_0x0043
        L_0x0034:
            com.fasterxml.jackson.core.Version r0 = com.fasterxml.jackson.core.Version.unknownVersion()     // Catch:{ all -> 0x0032 }
            r2.close()     // Catch:{ IOException -> 0x003c }
            return r0
        L_0x003c:
            r2 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r2)
            throw r0
        L_0x0043:
            r2.close()     // Catch:{ IOException -> 0x0047 }
            throw r0
        L_0x0047:
            r2 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.util.VersionUtil.versionFor(java.lang.Class):com.fasterxml.jackson.core.Version");
    }

    public static Version packageVersionFor(Class<?> cls) {
        try {
            StringBuilder sb = new StringBuilder(cls.getPackage().getName());
            sb.append(".");
            sb.append(PACKAGE_VERSION_CLASS_NAME);
            Class cls2 = Class.forName(sb.toString(), true, cls.getClassLoader());
            if (cls2 == null) {
                return null;
            }
            try {
                Object newInstance = cls2.newInstance();
                if (newInstance instanceof Versioned) {
                    return ((Versioned) newInstance).version();
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Bad version class ");
                sb2.append(cls2.getName());
                sb2.append(": does not implement ");
                sb2.append(Versioned.class.getName());
                throw new IllegalArgumentException(sb2.toString());
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Failed to instantiate ");
                sb3.append(cls2.getName());
                sb3.append(" to find version information, problem: ");
                sb3.append(e2.getMessage());
                throw new IllegalArgumentException(sb3.toString(), e2);
            }
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0019 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x001d A[ExcHandler: all (r4v2 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.fasterxml.jackson.core.Version doReadVersion(java.io.Reader r4) {
        /*
            java.io.BufferedReader r0 = new java.io.BufferedReader
            r0.<init>(r4)
            r4 = 0
            java.lang.String r1 = r0.readLine()     // Catch:{ IOException -> 0x0022, all -> 0x001d }
            if (r1 == 0) goto L_0x0018
            java.lang.String r2 = r0.readLine()     // Catch:{ IOException -> 0x0018, all -> 0x001d }
            if (r2 == 0) goto L_0x0019
            java.lang.String r3 = r0.readLine()     // Catch:{ IOException -> 0x0019, all -> 0x001d }
            r4 = r3
            goto L_0x0019
        L_0x0018:
            r2 = r4
        L_0x0019:
            r0.close()     // Catch:{ IOException -> 0x0025 }
            goto L_0x0025
        L_0x001d:
            r4 = move-exception
            r0.close()     // Catch:{ IOException -> 0x0021 }
        L_0x0021:
            throw r4
        L_0x0022:
            r1 = r4
            r2 = r1
            goto L_0x0019
        L_0x0025:
            if (r2 == 0) goto L_0x002b
            java.lang.String r2 = r2.trim()
        L_0x002b:
            if (r4 == 0) goto L_0x0031
            java.lang.String r4 = r4.trim()
        L_0x0031:
            com.fasterxml.jackson.core.Version r4 = parseVersion(r1, r2, r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.util.VersionUtil.doReadVersion(java.io.Reader):com.fasterxml.jackson.core.Version");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0053 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.fasterxml.jackson.core.Version mavenVersionFor(java.lang.ClassLoader r3, java.lang.String r4, java.lang.String r5) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "META-INF/maven/"
            r0.append(r1)
            java.lang.String r1 = "\\."
            java.lang.String r2 = "/"
            java.lang.String r4 = r4.replaceAll(r1, r2)
            r0.append(r4)
            java.lang.String r4 = "/"
            r0.append(r4)
            r0.append(r5)
            java.lang.String r4 = "/pom.properties"
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            java.io.InputStream r3 = r3.getResourceAsStream(r4)
            if (r3 == 0) goto L_0x0056
            java.util.Properties r4 = new java.util.Properties     // Catch:{ IOException -> 0x0053, all -> 0x004e }
            r4.<init>()     // Catch:{ IOException -> 0x0053, all -> 0x004e }
            r4.load(r3)     // Catch:{ IOException -> 0x0053, all -> 0x004e }
            java.lang.String r5 = "version"
            java.lang.String r5 = r4.getProperty(r5)     // Catch:{ IOException -> 0x0053, all -> 0x004e }
            java.lang.String r0 = "artifactId"
            java.lang.String r0 = r4.getProperty(r0)     // Catch:{ IOException -> 0x0053, all -> 0x004e }
            java.lang.String r1 = "groupId"
            java.lang.String r4 = r4.getProperty(r1)     // Catch:{ IOException -> 0x0053, all -> 0x004e }
            com.fasterxml.jackson.core.Version r4 = parseVersion(r5, r4, r0)     // Catch:{ IOException -> 0x0053, all -> 0x004e }
            r3.close()     // Catch:{ IOException -> 0x004d }
        L_0x004d:
            return r4
        L_0x004e:
            r4 = move-exception
            r3.close()     // Catch:{ IOException -> 0x0052 }
        L_0x0052:
            throw r4
        L_0x0053:
            r3.close()     // Catch:{ IOException -> 0x0056 }
        L_0x0056:
            com.fasterxml.jackson.core.Version r3 = com.fasterxml.jackson.core.Version.unknownVersion()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.util.VersionUtil.mavenVersionFor(java.lang.ClassLoader, java.lang.String, java.lang.String):com.fasterxml.jackson.core.Version");
    }

    @Deprecated
    public static Version parseVersion(String str) {
        return parseVersion(str, null, null);
    }

    public static Version parseVersion(String str, String str2, String str3) {
        String str4 = null;
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return null;
        }
        String[] split = VERSION_SEPARATOR.split(trim);
        int i = 0;
        int parseVersionPart = parseVersionPart(split[0]);
        int parseVersionPart2 = split.length > 1 ? parseVersionPart(split[1]) : 0;
        if (split.length > 2) {
            i = parseVersionPart(split[2]);
        }
        int i2 = i;
        if (split.length > 3) {
            str4 = split[3];
        }
        Version version = new Version(parseVersionPart, parseVersionPart2, i2, str4, str2, str3);
        return version;
    }

    protected static int parseVersionPart(String str) {
        String str2 = str.toString();
        int length = str2.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str2.charAt(i2);
            if (charAt > '9' || charAt < '0') {
                break;
            }
            i = (i * 10) + (charAt - '0');
        }
        return i;
    }

    public static final void throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }
}
