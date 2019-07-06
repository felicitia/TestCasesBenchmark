package com.klarna.checkout.internal.c;

import java.io.IOException;

public final class c {
    public static String a(String str) {
        String sb;
        if (str == null) {
            return null;
        }
        try {
            d dVar = new d(str.length() * 2);
            if (str != null) {
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    char charAt = str.charAt(i);
                    if (charAt > 4095) {
                        StringBuilder sb2 = new StringBuilder("\\u");
                        sb2.append(Integer.toHexString(charAt).toUpperCase());
                        sb = sb2.toString();
                    } else if (charAt > 255) {
                        StringBuilder sb3 = new StringBuilder("\\u0");
                        sb3.append(Integer.toHexString(charAt).toUpperCase());
                        sb = sb3.toString();
                    } else if (charAt > 127) {
                        StringBuilder sb4 = new StringBuilder("\\u00");
                        sb4.append(Integer.toHexString(charAt).toUpperCase());
                        sb = sb4.toString();
                    } else {
                        if (charAt < ' ') {
                            switch (charAt) {
                                case 8:
                                    dVar.write(92);
                                    dVar.write(98);
                                    continue;
                                case 9:
                                    dVar.write(92);
                                    dVar.write(116);
                                    continue;
                                case 10:
                                    dVar.write(92);
                                    dVar.write(110);
                                    continue;
                                case 12:
                                    dVar.write(92);
                                    dVar.write(102);
                                    continue;
                                case 13:
                                    dVar.write(92);
                                    dVar.write(114);
                                    continue;
                                default:
                                    if (charAt <= 15) {
                                        StringBuilder sb5 = new StringBuilder("\\u000");
                                        sb5.append(Integer.toHexString(charAt).toUpperCase());
                                        sb = sb5.toString();
                                        break;
                                    } else {
                                        StringBuilder sb6 = new StringBuilder("\\u00");
                                        sb6.append(Integer.toHexString(charAt).toUpperCase());
                                        sb = sb6.toString();
                                        break;
                                    }
                            }
                        } else {
                            int i2 = 34;
                            if (charAt != '\"') {
                                i2 = 39;
                                if (charAt == '\'') {
                                    dVar.write(92);
                                } else if (charAt != '\\') {
                                    dVar.write(charAt);
                                } else {
                                    dVar.write(92);
                                    dVar.write(92);
                                }
                            } else {
                                dVar.write(92);
                            }
                            dVar.write(i2);
                        }
                    }
                    dVar.write(sb);
                }
            }
            return dVar.a();
        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }

    public static String b(String str) {
        StringBuffer stringBuffer;
        if (str == null) {
            return null;
        }
        try {
            d dVar = new d(str.length());
            if (str != null) {
                int length = str.length();
                stringBuffer = new StringBuffer(4);
                boolean z = false;
                boolean z2 = false;
                for (int i = 0; i < length; i++) {
                    char charAt = str.charAt(i);
                    if (z2) {
                        stringBuffer.append(charAt);
                        if (stringBuffer.length() == 4) {
                            dVar.write((char) Integer.parseInt(stringBuffer.toString(), 16));
                            stringBuffer.setLength(0);
                            z = false;
                            z2 = false;
                        }
                    } else if (z) {
                        int i2 = 34;
                        if (charAt != '\"') {
                            i2 = 39;
                            if (charAt != '\'') {
                                if (charAt == '\\') {
                                    dVar.write(92);
                                } else if (charAt == 'b') {
                                    dVar.write(8);
                                } else if (charAt == 'f') {
                                    dVar.write(12);
                                } else if (charAt == 'n') {
                                    dVar.write(10);
                                } else if (charAt != 'r') {
                                    switch (charAt) {
                                        case 't':
                                            dVar.write(9);
                                            break;
                                        case 'u':
                                            z = false;
                                            z2 = true;
                                            continue;
                                        default:
                                            dVar.write(charAt);
                                            break;
                                    }
                                } else {
                                    dVar.write(13);
                                }
                                z = false;
                            }
                        }
                        dVar.write(i2);
                        z = false;
                    } else if (charAt == '\\') {
                        z = true;
                    } else {
                        dVar.write(charAt);
                    }
                }
                if (z) {
                    dVar.write(92);
                }
            }
            return dVar.a();
        } catch (NumberFormatException e) {
            StringBuilder sb = new StringBuilder("Unable to parse unicode value: ");
            sb.append(stringBuffer);
            throw new Exception(sb.toString(), e);
        } catch (IOException e2) {
            e2.getMessage();
            return null;
        }
    }
}
