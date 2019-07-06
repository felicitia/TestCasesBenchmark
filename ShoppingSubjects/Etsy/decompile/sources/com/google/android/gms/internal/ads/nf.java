package com.google.android.gms.internal.ads;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@bu
public final class nf extends mz {
    private static final Set<String> b = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat c = new DecimalFormat("#,###");
    private File d;
    private boolean e;

    public nf(mo moVar) {
        super(moVar);
        File cacheDir = this.a.getCacheDir();
        if (cacheDir == null) {
            gv.e("Context.getCacheDir() returned null");
            return;
        }
        this.d = new File(cacheDir, "admobVideoStreams");
        if (!this.d.isDirectory() && !this.d.mkdirs()) {
            String str = "Could not create preload cache directory at ";
            String valueOf = String.valueOf(this.d.getAbsolutePath());
            gv.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.d = null;
        } else if (!this.d.setReadable(true, false) || !this.d.setExecutable(true, false)) {
            String str2 = "Could not set cache file permissions at ";
            String valueOf2 = String.valueOf(this.d.getAbsolutePath());
            gv.e(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
            this.d = null;
        }
    }

    private final File a(File file) {
        return new File(this.d, String.valueOf(file.getName()).concat(".done"));
    }

    public final void a() {
        this.e = true;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:223|224|225|(4:226|227|228|229)) */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01ed, code lost:
        r3 = new java.lang.String(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01f3, code lost:
        com.google.android.gms.internal.ads.gv.b(r3);
        r5.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01f9, code lost:
        r3 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01fe, code lost:
        if ((r5 instanceof java.net.HttpURLConnection) == false) goto L_0x025e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:?, code lost:
        r1 = r5.getResponseCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0209, code lost:
        if (r1 < 400) goto L_0x025e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x020b, code lost:
        r2 = "badUrl";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x020d, code lost:
        r3 = "HTTP request failed. Code: ";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:?, code lost:
        r4 = java.lang.String.valueOf(java.lang.Integer.toString(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x021b, code lost:
        if (r4.length() == 0) goto L_0x0222;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x021d, code lost:
        r3 = r3.concat(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0227, code lost:
        r3 = new java.lang.String(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:?, code lost:
        r6 = new java.lang.StringBuilder(32 + java.lang.String.valueOf(r34).length());
        r6.append("HTTP status code ");
        r6.append(r1);
        r6.append(" at ");
        r6.append(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0251, code lost:
        throw new java.io.IOException(r6.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0252, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0253, code lost:
        r1 = r0;
        r4 = r3;
        r3 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0257, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0258, code lost:
        r1 = r0;
        r3 = r2;
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x025b, code lost:
        r2 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:?, code lost:
        r7 = r5.getContentLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0262, code lost:
        if (r7 >= 0) goto L_0x028d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0264, code lost:
        r1 = "Stream cache aborted, missing content-length header at ";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:?, code lost:
        r2 = java.lang.String.valueOf(r34);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x026e, code lost:
        if (r2.length() == 0) goto L_0x0275;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0270, code lost:
        r1 = r1.concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x0275, code lost:
        r1 = new java.lang.String(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x027b, code lost:
        com.google.android.gms.internal.ads.gv.e(r1);
        a(r9, r12.getAbsolutePath(), "contentLengthMissing", null);
        b.remove(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x028c, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:?, code lost:
        r1 = c.format((long) r7);
        r3 = ((java.lang.Integer) com.google.android.gms.internal.ads.ajh.f().a(com.google.android.gms.internal.ads.akl.o)).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x02a4, code lost:
        if (r7 <= r3) goto L_0x02fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:?, code lost:
        r3 = new java.lang.StringBuilder((33 + java.lang.String.valueOf(r1).length()) + java.lang.String.valueOf(r34).length());
        r3.append("Content length ");
        r3.append(r1);
        r3.append(" exceeds limit at ");
        r3.append(r9);
        com.google.android.gms.internal.ads.gv.e(r3.toString());
        r2 = "File too big for full file cache. Size: ";
        r1 = java.lang.String.valueOf(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x02e0, code lost:
        if (r1.length() == 0) goto L_0x02e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x02e2, code lost:
        r1 = r2.concat(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x02e7, code lost:
        r1 = new java.lang.String(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x02ec, code lost:
        a(r9, r12.getAbsolutePath(), "sizeExceeded", r1);
        b.remove(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x02fa, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:?, code lost:
        r2 = new java.lang.StringBuilder((20 + java.lang.String.valueOf(r1).length()) + java.lang.String.valueOf(r34).length());
        r2.append("Caching ");
        r2.append(r1);
        r2.append(" bytes from ");
        r2.append(r9);
        com.google.android.gms.internal.ads.gv.b(r2.toString());
        r5 = java.nio.channels.Channels.newChannel(r5.getInputStream());
        r4 = new java.io.FileOutputStream(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:?, code lost:
        r2 = r4.getChannel();
        r1 = java.nio.ByteBuffer.allocate(1048576);
        r10 = com.google.android.gms.ads.internal.ao.l();
        r17 = r10.currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0354, code lost:
        r20 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:?, code lost:
        r11 = new com.google.android.gms.internal.ads.je(((java.lang.Long) com.google.android.gms.internal.ads.ajh.f().a(com.google.android.gms.internal.ads.akl.r)).longValue());
        r14 = ((java.lang.Long) com.google.android.gms.internal.ads.ajh.f().a(com.google.android.gms.internal.ads.akl.q)).longValue();
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0370, code lost:
        r21 = r5.read(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0374, code lost:
        if (r21 < 0) goto L_0x0480;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0376, code lost:
        r6 = r6 + r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0378, code lost:
        if (r6 <= r3) goto L_0x03be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x037a, code lost:
        r1 = "sizeExceeded";
        r2 = "File too big for full file cache. Size: ";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:?, code lost:
        r3 = java.lang.String.valueOf(java.lang.Integer.toString(r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x038a, code lost:
        if (r3.length() == 0) goto L_0x0392;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x038c, code lost:
        r10 = r2.concat(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x0397, code lost:
        r10 = new java.lang.String(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x039f, code lost:
        throw new java.io.IOException("stream cache file size limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x03a0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x03a1, code lost:
        r3 = r1;
        r2 = r20;
        r1 = r0;
        r32 = r10;
        r10 = r4;
        r4 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x03ac, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x03ad, code lost:
        r3 = r1;
        r10 = r4;
        r2 = r20;
        r4 = null;
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x03b8, code lost:
        r3 = r16;
        r2 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:?, code lost:
        r1.flip();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x03c5, code lost:
        if (r2.write(r1) > 0) goto L_0x0476;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x03c7, code lost:
        r1.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x03d6, code lost:
        if ((r10.currentTimeMillis() - r17) <= (1000 * r14)) goto L_0x0407;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x03d8, code lost:
        r1 = "downloadTimeout";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:?, code lost:
        r2 = java.lang.Long.toString(r14);
        r5 = new java.lang.StringBuilder(29 + java.lang.String.valueOf(r2).length());
        r5.append("Timeout exceeded. Limit: ");
        r5.append(r2);
        r5.append(" sec");
        r10 = r5.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0406, code lost:
        throw new java.io.IOException("stream cache time limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x0407, code lost:
        r26 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x040b, code lost:
        if (r8.e == false) goto L_0x0417;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x040d, code lost:
        r1 = "externalAbort";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x0416, code lost:
        throw new java.io.IOException("abort requested");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x041b, code lost:
        if (r11.a() == false) goto L_0x044d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x041d, code lost:
        r27 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x0427, code lost:
        r28 = r11;
        r23 = r26;
        r11 = com.google.android.gms.internal.ads.jp.a;
        r1 = r1;
        r24 = r2;
        r25 = r3;
        r29 = r14;
        r14 = r4;
        r19 = r6;
        r31 = r5;
        r21 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:?, code lost:
        r1 = new com.google.android.gms.internal.ads.na(r8, r9, r12.getAbsolutePath(), r6, r7, false);
        r11.post(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x044d, code lost:
        r24 = r2;
        r25 = r3;
        r31 = r5;
        r19 = r6;
        r21 = r7;
        r27 = r10;
        r28 = r11;
        r29 = r14;
        r23 = r26;
        r14 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x0461, code lost:
        r4 = r14;
        r6 = r19;
        r7 = r21;
        r1 = r23;
        r2 = r24;
        r3 = r25;
        r10 = r27;
        r11 = r28;
        r14 = r29;
        r5 = r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x0476, code lost:
        r29 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x047a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x047b, code lost:
        r14 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x047c, code lost:
        r1 = r0;
        r10 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x0480, code lost:
        r14 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:?, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0489, code lost:
        if (com.google.android.gms.internal.ads.gv.a(3) == false) goto L_0x04c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:?, code lost:
        r1 = c.format((long) r6);
        r3 = new java.lang.StringBuilder((22 + java.lang.String.valueOf(r1).length()) + java.lang.String.valueOf(r34).length());
        r3.append("Preloaded ");
        r3.append(r1);
        r3.append(" bytes from ");
        r3.append(r9);
        com.google.android.gms.internal.ads.gv.b(r3.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x04c3, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:?, code lost:
        r12.setReadable(true, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x04ce, code lost:
        if (r13.isFile() == false) goto L_0x04d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:?, code lost:
        r13.setLastModified(java.lang.System.currentTimeMillis());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:?, code lost:
        r13.createNewFile();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:?, code lost:
        a(r9, r12.getAbsolutePath(), r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x04e4, code lost:
        r2 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:?, code lost:
        b.remove(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x04ea, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x04eb, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x04ed, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x04ef, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x04f0, code lost:
        r14 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x04f1, code lost:
        r2 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x04f4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x04f5, code lost:
        r14 = r4;
        r2 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x04f7, code lost:
        r1 = r0;
        r10 = r14;
        r3 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x04fb, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x04fd, code lost:
        r2 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x0505, code lost:
        throw new java.io.IOException("Too many redirects (20)");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x0506, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x0508, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x0509, code lost:
        r2 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x050a, code lost:
        r1 = r0;
        r3 = r16;
        r4 = null;
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x0511, code lost:
        if ((r1 instanceof java.lang.RuntimeException) != false) goto L_0x0513;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x0513, code lost:
        com.google.android.gms.ads.internal.ao.i().a(r1, "VideoStreamFullFileCache.preload");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x0521, code lost:
        if (r8.e == false) goto L_0x0548;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x0523, code lost:
        r5 = new java.lang.StringBuilder(26 + java.lang.String.valueOf(r34).length());
        r5.append("Preload aborted for URL \"");
        r5.append(r9);
        r5.append("\"");
        com.google.android.gms.internal.ads.gv.d(r5.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x0548, code lost:
        r6 = new java.lang.StringBuilder(25 + java.lang.String.valueOf(r34).length());
        r6.append("Preload failed for URL \"");
        r6.append(r9);
        r6.append("\"");
        com.google.android.gms.internal.ads.gv.c(r6.toString(), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x0578, code lost:
        r1 = "Could not delete partial cache file at ";
        r5 = java.lang.String.valueOf(r12.getAbsolutePath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x0586, code lost:
        if (r5.length() == 0) goto L_0x058d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x0588, code lost:
        r1 = r1.concat(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x058d, code lost:
        r1 = new java.lang.String(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x0593, code lost:
        com.google.android.gms.internal.ads.gv.e(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x0596, code lost:
        a(r9, r12.getAbsolutePath(), r3, r4);
        b.remove(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x05a3, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0134, code lost:
        r16 = "error";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        com.google.android.gms.ads.internal.ao.q();
        r1 = ((java.lang.Integer) com.google.android.gms.internal.ads.ajh.f().a(com.google.android.gms.internal.ads.akl.s)).intValue();
        r3 = new java.net.URL(r9);
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0150, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0153, code lost:
        if (r2 > 20) goto L_0x04fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0155, code lost:
        r5 = r3.openConnection();
        r5.setConnectTimeout(r1);
        r5.setReadTimeout(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0161, code lost:
        if ((r5 instanceof java.net.HttpURLConnection) != false) goto L_0x0173;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x016a, code lost:
        throw new java.io.IOException("Invalid protocol.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x016b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x016c, code lost:
        r1 = r0;
        r4 = null;
        r2 = r15;
        r3 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        r5 = (java.net.HttpURLConnection) r5;
        r6 = new com.google.android.gms.internal.ads.jt();
        r6.a(r5, (byte[]) null);
        r5.setInstanceFollowRedirects(false);
        r7 = r5.getResponseCode();
        r6.a(r5, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x018a, code lost:
        if ((r7 / 100) != 3) goto L_0x01fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        r4 = r5.getHeaderField(com.etsy.android.lib.models.ResponseConstants.Includes.LOCATION);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0192, code lost:
        if (r4 != null) goto L_0x019c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x019b, code lost:
        throw new java.io.IOException("Missing Location header in redirect");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x019c, code lost:
        r6 = new java.net.URL(r3, r4);
        r3 = r6.getProtocol();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01a5, code lost:
        if (r3 != null) goto L_0x01af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01ae, code lost:
        throw new java.io.IOException("Protocol is null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01b5, code lost:
        if (r3.equals("http") != false) goto L_0x01dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01bd, code lost:
        if (r3.equals("https") != false) goto L_0x01dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01bf, code lost:
        r2 = "Unsupported scheme: ";
        r3 = java.lang.String.valueOf(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01cb, code lost:
        if (r3.length() == 0) goto L_0x01d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01cd, code lost:
        r2 = r2.concat(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01d2, code lost:
        r2 = new java.lang.String(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01db, code lost:
        throw new java.io.IOException(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01dc, code lost:
        r3 = "Redirecting to ";
        r4 = java.lang.String.valueOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01e6, code lost:
        if (r4.length() == 0) goto L_0x01ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01e8, code lost:
        r3 = r3.concat(r4);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:223:0x04db */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x0513  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x0523  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0548  */
    /* JADX WARNING: Removed duplicated region for block: B:263:0x0588  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x058d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(java.lang.String r34) {
        /*
            r33 = this;
            r8 = r33
            r9 = r34
            java.io.File r1 = r8.d
            r10 = 0
            r11 = 0
            if (r1 != 0) goto L_0x0010
            java.lang.String r1 = "noCacheDir"
        L_0x000c:
            r8.a(r9, r10, r1, r10)
            return r11
        L_0x0010:
            java.io.File r1 = r8.d
            if (r1 != 0) goto L_0x0016
            r4 = r11
            goto L_0x0034
        L_0x0016:
            java.io.File r1 = r8.d
            java.io.File[] r1 = r1.listFiles()
            int r2 = r1.length
            r3 = r11
            r4 = r3
        L_0x001f:
            if (r3 >= r2) goto L_0x0034
            r5 = r1[r3]
            java.lang.String r5 = r5.getName()
            java.lang.String r6 = ".done"
            boolean r5 = r5.endsWith(r6)
            if (r5 != 0) goto L_0x0031
            int r4 = r4 + 1
        L_0x0031:
            int r3 = r3 + 1
            goto L_0x001f
        L_0x0034:
            com.google.android.gms.internal.ads.akb<java.lang.Integer> r1 = com.google.android.gms.internal.ads.akl.n
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()
            java.lang.Object r1 = r2.a(r1)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r4 <= r1) goto L_0x0098
            java.io.File r1 = r8.d
            if (r1 != 0) goto L_0x004c
        L_0x004a:
            r1 = r11
            goto L_0x008d
        L_0x004c:
            r1 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.io.File r3 = r8.d
            java.io.File[] r3 = r3.listFiles()
            int r4 = r3.length
            r5 = r1
            r2 = r10
            r1 = r11
        L_0x005b:
            if (r1 >= r4) goto L_0x0078
            r7 = r3[r1]
            java.lang.String r12 = r7.getName()
            java.lang.String r13 = ".done"
            boolean r12 = r12.endsWith(r13)
            if (r12 != 0) goto L_0x0075
            long r12 = r7.lastModified()
            int r14 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r14 >= 0) goto L_0x0075
            r2 = r7
            r5 = r12
        L_0x0075:
            int r1 = r1 + 1
            goto L_0x005b
        L_0x0078:
            if (r2 == 0) goto L_0x004a
            boolean r1 = r2.delete()
            java.io.File r2 = r8.a(r2)
            boolean r3 = r2.isFile()
            if (r3 == 0) goto L_0x008d
            boolean r2 = r2.delete()
            r1 = r1 & r2
        L_0x008d:
            if (r1 != 0) goto L_0x0010
            java.lang.String r1 = "Unable to expire stream cache"
            com.google.android.gms.internal.ads.gv.e(r1)
            java.lang.String r1 = "expireFailed"
            goto L_0x000c
        L_0x0098:
            com.google.android.gms.internal.ads.ajh.a()
            java.lang.String r1 = com.google.android.gms.internal.ads.jp.a(r34)
            java.io.File r12 = new java.io.File
            java.io.File r2 = r8.d
            r12.<init>(r2, r1)
            java.io.File r13 = r8.a(r12)
            boolean r1 = r12.isFile()
            r14 = 1
            if (r1 == 0) goto L_0x00de
            boolean r1 = r13.isFile()
            if (r1 == 0) goto L_0x00de
            long r1 = r12.length()
            int r1 = (int) r1
            java.lang.String r2 = "Stream cache hit at "
            java.lang.String r3 = java.lang.String.valueOf(r34)
            int r4 = r3.length()
            if (r4 == 0) goto L_0x00cd
            java.lang.String r2 = r2.concat(r3)
            goto L_0x00d3
        L_0x00cd:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r2)
            r2 = r3
        L_0x00d3:
            com.google.android.gms.internal.ads.gv.b(r2)
            java.lang.String r2 = r12.getAbsolutePath()
            r8.a(r9, r2, r1)
            return r14
        L_0x00de:
            java.io.File r1 = r8.d
            java.lang.String r1 = r1.getAbsolutePath()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = java.lang.String.valueOf(r34)
            int r3 = r2.length()
            if (r3 == 0) goto L_0x00f8
            java.lang.String r1 = r1.concat(r2)
            r15 = r1
            goto L_0x00fe
        L_0x00f8:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            r15 = r2
        L_0x00fe:
            java.util.Set<java.lang.String> r1 = b
            monitor-enter(r1)
            java.util.Set<java.lang.String> r2 = b     // Catch:{ all -> 0x05a4 }
            boolean r2 = r2.contains(r15)     // Catch:{ all -> 0x05a4 }
            if (r2 == 0) goto L_0x012e
            java.lang.String r2 = "Stream cache already in progress at "
            java.lang.String r3 = java.lang.String.valueOf(r34)     // Catch:{ all -> 0x05a4 }
            int r4 = r3.length()     // Catch:{ all -> 0x05a4 }
            if (r4 == 0) goto L_0x011a
            java.lang.String r2 = r2.concat(r3)     // Catch:{ all -> 0x05a4 }
            goto L_0x0120
        L_0x011a:
            java.lang.String r3 = new java.lang.String     // Catch:{ all -> 0x05a4 }
            r3.<init>(r2)     // Catch:{ all -> 0x05a4 }
            r2 = r3
        L_0x0120:
            com.google.android.gms.internal.ads.gv.e(r2)     // Catch:{ all -> 0x05a4 }
            java.lang.String r2 = r12.getAbsolutePath()     // Catch:{ all -> 0x05a4 }
            java.lang.String r3 = "inProgress"
            r8.a(r9, r2, r3, r10)     // Catch:{ all -> 0x05a4 }
            monitor-exit(r1)     // Catch:{ all -> 0x05a4 }
            return r11
        L_0x012e:
            java.util.Set<java.lang.String> r2 = b     // Catch:{ all -> 0x05a4 }
            r2.add(r15)     // Catch:{ all -> 0x05a4 }
            monitor-exit(r1)     // Catch:{ all -> 0x05a4 }
            java.lang.String r16 = "error"
            com.google.android.gms.ads.internal.ao.q()     // Catch:{ IOException | RuntimeException -> 0x0508 }
            com.google.android.gms.internal.ads.akb<java.lang.Integer> r1 = com.google.android.gms.internal.ads.akl.s     // Catch:{ IOException | RuntimeException -> 0x0508 }
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ IOException | RuntimeException -> 0x0508 }
            java.lang.Object r1 = r2.a(r1)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ IOException | RuntimeException -> 0x0508 }
            int r1 = r1.intValue()     // Catch:{ IOException | RuntimeException -> 0x0508 }
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException | RuntimeException -> 0x0508 }
            r2.<init>(r9)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            r3 = r2
            r2 = r11
        L_0x0150:
            int r2 = r2 + r14
            r4 = 20
            if (r2 > r4) goto L_0x04fd
            java.net.URLConnection r5 = r3.openConnection()     // Catch:{ IOException | RuntimeException -> 0x0508 }
            r5.setConnectTimeout(r1)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            r5.setReadTimeout(r1)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            boolean r6 = r5 instanceof java.net.HttpURLConnection     // Catch:{ IOException | RuntimeException -> 0x0508 }
            if (r6 != 0) goto L_0x0173
            java.io.IOException r1 = new java.io.IOException     // Catch:{ IOException | RuntimeException -> 0x016b }
            java.lang.String r2 = "Invalid protocol."
            r1.<init>(r2)     // Catch:{ IOException | RuntimeException -> 0x016b }
            throw r1     // Catch:{ IOException | RuntimeException -> 0x016b }
        L_0x016b:
            r0 = move-exception
            r1 = r0
            r4 = r10
            r2 = r15
            r3 = r16
            goto L_0x050f
        L_0x0173:
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ IOException | RuntimeException -> 0x0508 }
            com.google.android.gms.internal.ads.jt r6 = new com.google.android.gms.internal.ads.jt     // Catch:{ IOException | RuntimeException -> 0x0508 }
            r6.<init>()     // Catch:{ IOException | RuntimeException -> 0x0508 }
            r6.a(r5, r10)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            r5.setInstanceFollowRedirects(r11)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            int r7 = r5.getResponseCode()     // Catch:{ IOException | RuntimeException -> 0x0508 }
            r6.a(r5, r7)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            int r7 = r7 / 100
            r6 = 3
            if (r7 != r6) goto L_0x01fc
            java.lang.String r4 = "Location"
            java.lang.String r4 = r5.getHeaderField(r4)     // Catch:{ IOException | RuntimeException -> 0x016b }
            if (r4 != 0) goto L_0x019c
            java.io.IOException r1 = new java.io.IOException     // Catch:{ IOException | RuntimeException -> 0x016b }
            java.lang.String r2 = "Missing Location header in redirect"
            r1.<init>(r2)     // Catch:{ IOException | RuntimeException -> 0x016b }
            throw r1     // Catch:{ IOException | RuntimeException -> 0x016b }
        L_0x019c:
            java.net.URL r6 = new java.net.URL     // Catch:{ IOException | RuntimeException -> 0x016b }
            r6.<init>(r3, r4)     // Catch:{ IOException | RuntimeException -> 0x016b }
            java.lang.String r3 = r6.getProtocol()     // Catch:{ IOException | RuntimeException -> 0x016b }
            if (r3 != 0) goto L_0x01af
            java.io.IOException r1 = new java.io.IOException     // Catch:{ IOException | RuntimeException -> 0x016b }
            java.lang.String r2 = "Protocol is null"
            r1.<init>(r2)     // Catch:{ IOException | RuntimeException -> 0x016b }
            throw r1     // Catch:{ IOException | RuntimeException -> 0x016b }
        L_0x01af:
            java.lang.String r7 = "http"
            boolean r7 = r3.equals(r7)     // Catch:{ IOException | RuntimeException -> 0x016b }
            if (r7 != 0) goto L_0x01dc
            java.lang.String r7 = "https"
            boolean r7 = r3.equals(r7)     // Catch:{ IOException | RuntimeException -> 0x016b }
            if (r7 != 0) goto L_0x01dc
            java.io.IOException r1 = new java.io.IOException     // Catch:{ IOException | RuntimeException -> 0x016b }
            java.lang.String r2 = "Unsupported scheme: "
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ IOException | RuntimeException -> 0x016b }
            int r4 = r3.length()     // Catch:{ IOException | RuntimeException -> 0x016b }
            if (r4 == 0) goto L_0x01d2
            java.lang.String r2 = r2.concat(r3)     // Catch:{ IOException | RuntimeException -> 0x016b }
            goto L_0x01d8
        L_0x01d2:
            java.lang.String r3 = new java.lang.String     // Catch:{ IOException | RuntimeException -> 0x016b }
            r3.<init>(r2)     // Catch:{ IOException | RuntimeException -> 0x016b }
            r2 = r3
        L_0x01d8:
            r1.<init>(r2)     // Catch:{ IOException | RuntimeException -> 0x016b }
            throw r1     // Catch:{ IOException | RuntimeException -> 0x016b }
        L_0x01dc:
            java.lang.String r3 = "Redirecting to "
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ IOException | RuntimeException -> 0x016b }
            int r7 = r4.length()     // Catch:{ IOException | RuntimeException -> 0x016b }
            if (r7 == 0) goto L_0x01ed
            java.lang.String r3 = r3.concat(r4)     // Catch:{ IOException | RuntimeException -> 0x016b }
            goto L_0x01f3
        L_0x01ed:
            java.lang.String r4 = new java.lang.String     // Catch:{ IOException | RuntimeException -> 0x016b }
            r4.<init>(r3)     // Catch:{ IOException | RuntimeException -> 0x016b }
            r3 = r4
        L_0x01f3:
            com.google.android.gms.internal.ads.gv.b(r3)     // Catch:{ IOException | RuntimeException -> 0x016b }
            r5.disconnect()     // Catch:{ IOException | RuntimeException -> 0x016b }
            r3 = r6
            goto L_0x0150
        L_0x01fc:
            boolean r1 = r5 instanceof java.net.HttpURLConnection     // Catch:{ IOException | RuntimeException -> 0x0508 }
            if (r1 == 0) goto L_0x025e
            r1 = r5
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ IOException | RuntimeException -> 0x016b }
            int r1 = r1.getResponseCode()     // Catch:{ IOException | RuntimeException -> 0x016b }
            r2 = 400(0x190, float:5.6E-43)
            if (r1 < r2) goto L_0x025e
            java.lang.String r2 = "badUrl"
            java.lang.String r3 = "HTTP request failed. Code: "
            java.lang.String r4 = java.lang.Integer.toString(r1)     // Catch:{ IOException | RuntimeException -> 0x0257 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ IOException | RuntimeException -> 0x0257 }
            int r5 = r4.length()     // Catch:{ IOException | RuntimeException -> 0x0257 }
            if (r5 == 0) goto L_0x0222
            java.lang.String r3 = r3.concat(r4)     // Catch:{ IOException | RuntimeException -> 0x0257 }
            goto L_0x0228
        L_0x0222:
            java.lang.String r4 = new java.lang.String     // Catch:{ IOException | RuntimeException -> 0x0257 }
            r4.<init>(r3)     // Catch:{ IOException | RuntimeException -> 0x0257 }
            r3 = r4
        L_0x0228:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ IOException | RuntimeException -> 0x0252 }
            r5 = 32
            java.lang.String r6 = java.lang.String.valueOf(r34)     // Catch:{ IOException | RuntimeException -> 0x0252 }
            int r6 = r6.length()     // Catch:{ IOException | RuntimeException -> 0x0252 }
            int r5 = r5 + r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException | RuntimeException -> 0x0252 }
            r6.<init>(r5)     // Catch:{ IOException | RuntimeException -> 0x0252 }
            java.lang.String r5 = "HTTP status code "
            r6.append(r5)     // Catch:{ IOException | RuntimeException -> 0x0252 }
            r6.append(r1)     // Catch:{ IOException | RuntimeException -> 0x0252 }
            java.lang.String r1 = " at "
            r6.append(r1)     // Catch:{ IOException | RuntimeException -> 0x0252 }
            r6.append(r9)     // Catch:{ IOException | RuntimeException -> 0x0252 }
            java.lang.String r1 = r6.toString()     // Catch:{ IOException | RuntimeException -> 0x0252 }
            r4.<init>(r1)     // Catch:{ IOException | RuntimeException -> 0x0252 }
            throw r4     // Catch:{ IOException | RuntimeException -> 0x0252 }
        L_0x0252:
            r0 = move-exception
            r1 = r0
            r4 = r3
            r3 = r2
            goto L_0x025b
        L_0x0257:
            r0 = move-exception
            r1 = r0
            r3 = r2
            r4 = r10
        L_0x025b:
            r2 = r15
            goto L_0x050f
        L_0x025e:
            int r7 = r5.getContentLength()     // Catch:{ IOException | RuntimeException -> 0x0508 }
            if (r7 >= 0) goto L_0x028d
            java.lang.String r1 = "Stream cache aborted, missing content-length header at "
            java.lang.String r2 = java.lang.String.valueOf(r34)     // Catch:{ IOException | RuntimeException -> 0x016b }
            int r3 = r2.length()     // Catch:{ IOException | RuntimeException -> 0x016b }
            if (r3 == 0) goto L_0x0275
            java.lang.String r1 = r1.concat(r2)     // Catch:{ IOException | RuntimeException -> 0x016b }
            goto L_0x027b
        L_0x0275:
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException | RuntimeException -> 0x016b }
            r2.<init>(r1)     // Catch:{ IOException | RuntimeException -> 0x016b }
            r1 = r2
        L_0x027b:
            com.google.android.gms.internal.ads.gv.e(r1)     // Catch:{ IOException | RuntimeException -> 0x016b }
            java.lang.String r1 = r12.getAbsolutePath()     // Catch:{ IOException | RuntimeException -> 0x016b }
            java.lang.String r2 = "contentLengthMissing"
            r8.a(r9, r1, r2, r10)     // Catch:{ IOException | RuntimeException -> 0x016b }
            java.util.Set<java.lang.String> r1 = b     // Catch:{ IOException | RuntimeException -> 0x016b }
            r1.remove(r15)     // Catch:{ IOException | RuntimeException -> 0x016b }
            return r11
        L_0x028d:
            java.text.DecimalFormat r1 = c     // Catch:{ IOException | RuntimeException -> 0x0508 }
            long r2 = (long) r7     // Catch:{ IOException | RuntimeException -> 0x0508 }
            java.lang.String r1 = r1.format(r2)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            com.google.android.gms.internal.ads.akb<java.lang.Integer> r2 = com.google.android.gms.internal.ads.akl.o     // Catch:{ IOException | RuntimeException -> 0x0508 }
            com.google.android.gms.internal.ads.akj r3 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ IOException | RuntimeException -> 0x0508 }
            java.lang.Object r2 = r3.a(r2)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ IOException | RuntimeException -> 0x0508 }
            int r3 = r2.intValue()     // Catch:{ IOException | RuntimeException -> 0x0508 }
            if (r7 <= r3) goto L_0x02fb
            r2 = 33
            java.lang.String r3 = java.lang.String.valueOf(r1)     // Catch:{ IOException | RuntimeException -> 0x016b }
            int r3 = r3.length()     // Catch:{ IOException | RuntimeException -> 0x016b }
            int r2 = r2 + r3
            java.lang.String r3 = java.lang.String.valueOf(r34)     // Catch:{ IOException | RuntimeException -> 0x016b }
            int r3 = r3.length()     // Catch:{ IOException | RuntimeException -> 0x016b }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException | RuntimeException -> 0x016b }
            r3.<init>(r2)     // Catch:{ IOException | RuntimeException -> 0x016b }
            java.lang.String r2 = "Content length "
            r3.append(r2)     // Catch:{ IOException | RuntimeException -> 0x016b }
            r3.append(r1)     // Catch:{ IOException | RuntimeException -> 0x016b }
            java.lang.String r2 = " exceeds limit at "
            r3.append(r2)     // Catch:{ IOException | RuntimeException -> 0x016b }
            r3.append(r9)     // Catch:{ IOException | RuntimeException -> 0x016b }
            java.lang.String r2 = r3.toString()     // Catch:{ IOException | RuntimeException -> 0x016b }
            com.google.android.gms.internal.ads.gv.e(r2)     // Catch:{ IOException | RuntimeException -> 0x016b }
            java.lang.String r2 = "File too big for full file cache. Size: "
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ IOException | RuntimeException -> 0x016b }
            int r3 = r1.length()     // Catch:{ IOException | RuntimeException -> 0x016b }
            if (r3 == 0) goto L_0x02e7
            java.lang.String r1 = r2.concat(r1)     // Catch:{ IOException | RuntimeException -> 0x016b }
            goto L_0x02ec
        L_0x02e7:
            java.lang.String r1 = new java.lang.String     // Catch:{ IOException | RuntimeException -> 0x016b }
            r1.<init>(r2)     // Catch:{ IOException | RuntimeException -> 0x016b }
        L_0x02ec:
            java.lang.String r2 = r12.getAbsolutePath()     // Catch:{ IOException | RuntimeException -> 0x016b }
            java.lang.String r3 = "sizeExceeded"
            r8.a(r9, r2, r3, r1)     // Catch:{ IOException | RuntimeException -> 0x016b }
            java.util.Set<java.lang.String> r1 = b     // Catch:{ IOException | RuntimeException -> 0x016b }
            r1.remove(r15)     // Catch:{ IOException | RuntimeException -> 0x016b }
            return r11
        L_0x02fb:
            java.lang.String r2 = java.lang.String.valueOf(r1)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            int r2 = r2.length()     // Catch:{ IOException | RuntimeException -> 0x0508 }
            int r4 = r4 + r2
            java.lang.String r2 = java.lang.String.valueOf(r34)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            int r2 = r2.length()     // Catch:{ IOException | RuntimeException -> 0x0508 }
            int r4 = r4 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException | RuntimeException -> 0x0508 }
            r2.<init>(r4)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            java.lang.String r4 = "Caching "
            r2.append(r4)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            r2.append(r1)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            java.lang.String r1 = " bytes from "
            r2.append(r1)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            r2.append(r9)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            java.lang.String r1 = r2.toString()     // Catch:{ IOException | RuntimeException -> 0x0508 }
            com.google.android.gms.internal.ads.gv.b(r1)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            java.io.InputStream r1 = r5.getInputStream()     // Catch:{ IOException | RuntimeException -> 0x0508 }
            java.nio.channels.ReadableByteChannel r5 = java.nio.channels.Channels.newChannel(r1)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException | RuntimeException -> 0x0508 }
            r4.<init>(r12)     // Catch:{ IOException | RuntimeException -> 0x0508 }
            java.nio.channels.FileChannel r2 = r4.getChannel()     // Catch:{ IOException | RuntimeException -> 0x04f4 }
            r1 = 1048576(0x100000, float:1.469368E-39)
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r1)     // Catch:{ IOException | RuntimeException -> 0x04f4 }
            com.google.android.gms.common.util.Clock r10 = com.google.android.gms.ads.internal.ao.l()     // Catch:{ IOException | RuntimeException -> 0x04f4 }
            long r17 = r10.currentTimeMillis()     // Catch:{ IOException | RuntimeException -> 0x04f4 }
            com.google.android.gms.internal.ads.akb<java.lang.Long> r6 = com.google.android.gms.internal.ads.akl.r     // Catch:{ IOException | RuntimeException -> 0x04f4 }
            com.google.android.gms.internal.ads.akj r11 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ IOException | RuntimeException -> 0x04f4 }
            java.lang.Object r6 = r11.a(r6)     // Catch:{ IOException | RuntimeException -> 0x04f4 }
            java.lang.Long r6 = (java.lang.Long) r6     // Catch:{ IOException | RuntimeException -> 0x04f4 }
            r20 = r15
            long r14 = r6.longValue()     // Catch:{ IOException | RuntimeException -> 0x04ef }
            com.google.android.gms.internal.ads.je r11 = new com.google.android.gms.internal.ads.je     // Catch:{ IOException | RuntimeException -> 0x04ef }
            r11.<init>(r14)     // Catch:{ IOException | RuntimeException -> 0x04ef }
            com.google.android.gms.internal.ads.akb<java.lang.Long> r6 = com.google.android.gms.internal.ads.akl.q     // Catch:{ IOException | RuntimeException -> 0x04ef }
            com.google.android.gms.internal.ads.akj r14 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ IOException | RuntimeException -> 0x04ef }
            java.lang.Object r6 = r14.a(r6)     // Catch:{ IOException | RuntimeException -> 0x04ef }
            java.lang.Long r6 = (java.lang.Long) r6     // Catch:{ IOException | RuntimeException -> 0x04ef }
            long r14 = r6.longValue()     // Catch:{ IOException | RuntimeException -> 0x04ef }
            r6 = 0
        L_0x0370:
            int r21 = r5.read(r1)     // Catch:{ IOException | RuntimeException -> 0x04ef }
            if (r21 < 0) goto L_0x0480
            int r6 = r6 + r21
            if (r6 <= r3) goto L_0x03be
            java.lang.String r1 = "sizeExceeded"
            java.lang.String r2 = "File too big for full file cache. Size: "
            java.lang.String r3 = java.lang.Integer.toString(r6)     // Catch:{ IOException | RuntimeException -> 0x03ac }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ IOException | RuntimeException -> 0x03ac }
            int r5 = r3.length()     // Catch:{ IOException | RuntimeException -> 0x03ac }
            if (r5 == 0) goto L_0x0392
            java.lang.String r2 = r2.concat(r3)     // Catch:{ IOException | RuntimeException -> 0x03ac }
            r10 = r2
            goto L_0x0398
        L_0x0392:
            java.lang.String r3 = new java.lang.String     // Catch:{ IOException | RuntimeException -> 0x03ac }
            r3.<init>(r2)     // Catch:{ IOException | RuntimeException -> 0x03ac }
            r10 = r3
        L_0x0398:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException | RuntimeException -> 0x03a0 }
            java.lang.String r3 = "stream cache file size limit exceeded"
            r2.<init>(r3)     // Catch:{ IOException | RuntimeException -> 0x03a0 }
            throw r2     // Catch:{ IOException | RuntimeException -> 0x03a0 }
        L_0x03a0:
            r0 = move-exception
            r3 = r1
            r2 = r20
            r1 = r0
            r32 = r10
            r10 = r4
            r4 = r32
            goto L_0x050f
        L_0x03ac:
            r0 = move-exception
            r3 = r1
            r10 = r4
            r2 = r20
            r4 = 0
            r1 = r0
            goto L_0x050f
        L_0x03b5:
            r0 = move-exception
            r1 = r0
            r10 = r4
        L_0x03b8:
            r3 = r16
            r2 = r20
            goto L_0x04fb
        L_0x03be:
            r1.flip()     // Catch:{ IOException | RuntimeException -> 0x047a }
        L_0x03c1:
            int r21 = r2.write(r1)     // Catch:{ IOException | RuntimeException -> 0x047a }
            if (r21 > 0) goto L_0x0476
            r1.clear()     // Catch:{ IOException | RuntimeException -> 0x047a }
            long r21 = r10.currentTimeMillis()     // Catch:{ IOException | RuntimeException -> 0x047a }
            long r23 = r21 - r17
            r21 = 1000(0x3e8, double:4.94E-321)
            long r21 = r21 * r14
            int r25 = (r23 > r21 ? 1 : (r23 == r21 ? 0 : -1))
            if (r25 <= 0) goto L_0x0407
            java.lang.String r1 = "downloadTimeout"
            java.lang.String r2 = java.lang.Long.toString(r14)     // Catch:{ IOException | RuntimeException -> 0x03ac }
            r3 = 29
            java.lang.String r5 = java.lang.String.valueOf(r2)     // Catch:{ IOException | RuntimeException -> 0x03ac }
            int r5 = r5.length()     // Catch:{ IOException | RuntimeException -> 0x03ac }
            int r3 = r3 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException | RuntimeException -> 0x03ac }
            r5.<init>(r3)     // Catch:{ IOException | RuntimeException -> 0x03ac }
            java.lang.String r3 = "Timeout exceeded. Limit: "
            r5.append(r3)     // Catch:{ IOException | RuntimeException -> 0x03ac }
            r5.append(r2)     // Catch:{ IOException | RuntimeException -> 0x03ac }
            java.lang.String r2 = " sec"
            r5.append(r2)     // Catch:{ IOException | RuntimeException -> 0x03ac }
            java.lang.String r10 = r5.toString()     // Catch:{ IOException | RuntimeException -> 0x03ac }
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException | RuntimeException -> 0x03a0 }
            java.lang.String r3 = "stream cache time limit exceeded"
            r2.<init>(r3)     // Catch:{ IOException | RuntimeException -> 0x03a0 }
            throw r2     // Catch:{ IOException | RuntimeException -> 0x03a0 }
        L_0x0407:
            r26 = r1
            boolean r1 = r8.e     // Catch:{ IOException | RuntimeException -> 0x047a }
            if (r1 == 0) goto L_0x0417
            java.lang.String r1 = "externalAbort"
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException | RuntimeException -> 0x03ac }
            java.lang.String r3 = "abort requested"
            r2.<init>(r3)     // Catch:{ IOException | RuntimeException -> 0x03ac }
            throw r2     // Catch:{ IOException | RuntimeException -> 0x03ac }
        L_0x0417:
            boolean r1 = r11.a()     // Catch:{ IOException | RuntimeException -> 0x047a }
            if (r1 == 0) goto L_0x044d
            java.lang.String r21 = r12.getAbsolutePath()     // Catch:{ IOException | RuntimeException -> 0x047a }
            android.os.Handler r1 = com.google.android.gms.internal.ads.jp.a     // Catch:{ IOException | RuntimeException -> 0x047a }
            r27 = r10
            com.google.android.gms.internal.ads.na r10 = new com.google.android.gms.internal.ads.na     // Catch:{ IOException | RuntimeException -> 0x047a }
            r22 = 0
            r28 = r11
            r23 = r26
            r11 = r1
            r1 = r10
            r24 = r2
            r2 = r8
            r25 = r3
            r3 = r9
            r29 = r14
            r14 = r4
            r4 = r21
            r15 = r5
            r5 = r6
            r19 = r6
            r31 = r15
            r15 = 3
            r6 = r7
            r21 = r7
            r7 = r22
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            r11.post(r10)     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            goto L_0x0461
        L_0x044d:
            r24 = r2
            r25 = r3
            r31 = r5
            r19 = r6
            r21 = r7
            r27 = r10
            r28 = r11
            r29 = r14
            r23 = r26
            r15 = 3
            r14 = r4
        L_0x0461:
            r4 = r14
            r6 = r19
            r7 = r21
            r1 = r23
            r2 = r24
            r3 = r25
            r10 = r27
            r11 = r28
            r14 = r29
            r5 = r31
            goto L_0x0370
        L_0x0476:
            r29 = r14
            goto L_0x03c1
        L_0x047a:
            r0 = move-exception
            r14 = r4
        L_0x047c:
            r1 = r0
            r10 = r14
            goto L_0x03b8
        L_0x0480:
            r14 = r4
            r15 = 3
            r14.close()     // Catch:{ IOException | RuntimeException -> 0x04ed }
            boolean r1 = com.google.android.gms.internal.ads.gv.a(r15)     // Catch:{ IOException | RuntimeException -> 0x04ed }
            if (r1 == 0) goto L_0x04c5
            java.text.DecimalFormat r1 = c     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            long r2 = (long) r6     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            java.lang.String r1 = r1.format(r2)     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            r2 = 22
            java.lang.String r3 = java.lang.String.valueOf(r1)     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            int r3 = r3.length()     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            int r2 = r2 + r3
            java.lang.String r3 = java.lang.String.valueOf(r34)     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            int r3 = r3.length()     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            r3.<init>(r2)     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            java.lang.String r2 = "Preloaded "
            r3.append(r2)     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            r3.append(r1)     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            java.lang.String r1 = " bytes from "
            r3.append(r1)     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            r3.append(r9)     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            java.lang.String r1 = r3.toString()     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            com.google.android.gms.internal.ads.gv.b(r1)     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            goto L_0x04c5
        L_0x04c3:
            r0 = move-exception
            goto L_0x047c
        L_0x04c5:
            r1 = 0
            r2 = 1
            r12.setReadable(r2, r1)     // Catch:{ IOException | RuntimeException -> 0x04ed }
            boolean r1 = r13.isFile()     // Catch:{ IOException | RuntimeException -> 0x04ed }
            if (r1 == 0) goto L_0x04d8
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            r13.setLastModified(r1)     // Catch:{ IOException | RuntimeException -> 0x04c3 }
            goto L_0x04db
        L_0x04d8:
            r13.createNewFile()     // Catch:{ IOException -> 0x04db }
        L_0x04db:
            java.lang.String r1 = r12.getAbsolutePath()     // Catch:{ IOException | RuntimeException -> 0x04ed }
            r8.a(r9, r1, r6)     // Catch:{ IOException | RuntimeException -> 0x04ed }
            java.util.Set<java.lang.String> r1 = b     // Catch:{ IOException | RuntimeException -> 0x04ed }
            r2 = r20
            r1.remove(r2)     // Catch:{ IOException | RuntimeException -> 0x04eb }
            r1 = 1
            return r1
        L_0x04eb:
            r0 = move-exception
            goto L_0x04f7
        L_0x04ed:
            r0 = move-exception
            goto L_0x04f1
        L_0x04ef:
            r0 = move-exception
            r14 = r4
        L_0x04f1:
            r2 = r20
            goto L_0x04f7
        L_0x04f4:
            r0 = move-exception
            r14 = r4
            r2 = r15
        L_0x04f7:
            r1 = r0
            r10 = r14
            r3 = r16
        L_0x04fb:
            r4 = 0
            goto L_0x050f
        L_0x04fd:
            r2 = r15
            java.io.IOException r1 = new java.io.IOException     // Catch:{ IOException | RuntimeException -> 0x0506 }
            java.lang.String r3 = "Too many redirects (20)"
            r1.<init>(r3)     // Catch:{ IOException | RuntimeException -> 0x0506 }
            throw r1     // Catch:{ IOException | RuntimeException -> 0x0506 }
        L_0x0506:
            r0 = move-exception
            goto L_0x050a
        L_0x0508:
            r0 = move-exception
            r2 = r15
        L_0x050a:
            r1 = r0
            r3 = r16
            r4 = 0
            r10 = 0
        L_0x050f:
            boolean r5 = r1 instanceof java.lang.RuntimeException
            if (r5 == 0) goto L_0x051c
            com.google.android.gms.internal.ads.gf r5 = com.google.android.gms.ads.internal.ao.i()
            java.lang.String r6 = "VideoStreamFullFileCache.preload"
            r5.a(r1, r6)
        L_0x051c:
            r10.close()     // Catch:{ IOException | NullPointerException -> 0x051f }
        L_0x051f:
            boolean r5 = r8.e
            if (r5 == 0) goto L_0x0548
            r1 = 26
            java.lang.String r5 = java.lang.String.valueOf(r34)
            int r5 = r5.length()
            int r1 = r1 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r1)
            java.lang.String r1 = "Preload aborted for URL \""
            r5.append(r1)
            r5.append(r9)
            java.lang.String r1 = "\""
            r5.append(r1)
            java.lang.String r1 = r5.toString()
            com.google.android.gms.internal.ads.gv.d(r1)
            goto L_0x056c
        L_0x0548:
            r5 = 25
            java.lang.String r6 = java.lang.String.valueOf(r34)
            int r6 = r6.length()
            int r5 = r5 + r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            java.lang.String r5 = "Preload failed for URL \""
            r6.append(r5)
            r6.append(r9)
            java.lang.String r5 = "\""
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            com.google.android.gms.internal.ads.gv.c(r5, r1)
        L_0x056c:
            boolean r1 = r12.exists()
            if (r1 == 0) goto L_0x0596
            boolean r1 = r12.delete()
            if (r1 != 0) goto L_0x0596
            java.lang.String r1 = "Could not delete partial cache file at "
            java.lang.String r5 = r12.getAbsolutePath()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r6 = r5.length()
            if (r6 == 0) goto L_0x058d
            java.lang.String r1 = r1.concat(r5)
            goto L_0x0593
        L_0x058d:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r1)
            r1 = r5
        L_0x0593:
            com.google.android.gms.internal.ads.gv.e(r1)
        L_0x0596:
            java.lang.String r1 = r12.getAbsolutePath()
            r8.a(r9, r1, r3, r4)
            java.util.Set<java.lang.String> r1 = b
            r1.remove(r2)
            r1 = 0
            return r1
        L_0x05a4:
            r0 = move-exception
            r2 = r0
            monitor-exit(r1)     // Catch:{ all -> 0x05a4 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.nf.a(java.lang.String):boolean");
    }
}
