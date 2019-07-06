package com.google.android.gms.internal.icing;

final class zzet {
    static String zzc(zzbi zzbi) {
        String str;
        zzeu zzeu = new zzeu(zzbi);
        StringBuilder sb = new StringBuilder(zzeu.size());
        for (int i = 0; i < zzeu.size(); i++) {
            int zzi = zzeu.zzi(i);
            if (zzi == 34) {
                str = "\\\"";
            } else if (zzi == 39) {
                str = "\\'";
            } else if (zzi != 92) {
                switch (zzi) {
                    case 7:
                        str = "\\a";
                        break;
                    case 8:
                        str = "\\b";
                        break;
                    case 9:
                        str = "\\t";
                        break;
                    case 10:
                        str = "\\n";
                        break;
                    case 11:
                        str = "\\v";
                        break;
                    case 12:
                        str = "\\f";
                        break;
                    case 13:
                        str = "\\r";
                        break;
                    default:
                        if (zzi < 32 || zzi > 126) {
                            sb.append('\\');
                            sb.append((char) (((zzi >>> 6) & 3) + 48));
                            sb.append((char) (((zzi >>> 3) & 7) + 48));
                            zzi = (zzi & 7) + 48;
                        }
                        sb.append((char) zzi);
                        continue;
                }
            } else {
                str = "\\\\";
            }
            sb.append(str);
        }
        return sb.toString();
    }
}
