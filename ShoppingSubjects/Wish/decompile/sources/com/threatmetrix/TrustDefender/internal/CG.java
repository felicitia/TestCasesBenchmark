package com.threatmetrix.TrustDefender.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class CG {

    /* renamed from: void reason: not valid java name */
    private static final String f67void = TL.m331if(CG.class);

    /* renamed from: break reason: not valid java name */
    final ArrayList<String> f68break = new ArrayList<>();

    /* renamed from: byte reason: not valid java name */
    String f69byte = "";

    /* renamed from: case reason: not valid java name */
    String f70case = "";

    /* renamed from: char reason: not valid java name */
    short[] f71char = null;

    /* renamed from: do reason: not valid java name */
    HashSet<String> f72do = null;

    /* renamed from: else reason: not valid java name */
    public String f73else = "";

    /* renamed from: for reason: not valid java name */
    long f74for = 0;

    /* renamed from: if reason: not valid java name */
    String f75if = "";

    /* renamed from: int reason: not valid java name */
    long f76int = 0;

    /* renamed from: new reason: not valid java name */
    final ArrayList<String> f77new = new ArrayList<>();

    /* renamed from: this reason: not valid java name */
    String f78this = "";

    /* renamed from: try reason: not valid java name */
    int f79try = 0;

    CG() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0035, code lost:
        continue;
     */
    /* renamed from: if reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m29if(org.xmlpull.v1.XmlPullParser r4) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r3 = this;
            int r0 = r4.next()
            java.lang.String r1 = ""
        L_0x0006:
            r2 = 1
            if (r0 == r2) goto L_0x003a
            if (r0 == 0) goto L_0x0035
            switch(r0) {
                case 2: goto L_0x0030;
                case 3: goto L_0x0023;
                case 4: goto L_0x000f;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x0035
        L_0x000f:
            if (r1 == 0) goto L_0x0035
            java.lang.String r0 = "P"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0035
            java.util.ArrayList<java.lang.String> r0 = r3.f77new
            java.lang.String r2 = r4.getText()
            r0.add(r2)
            goto L_0x0035
        L_0x0023:
            java.lang.String r0 = r4.getName()
            java.lang.String r2 = "PS"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0035
            return
        L_0x0030:
            java.lang.String r0 = r4.getName()
            r1 = r0
        L_0x0035:
            int r0 = r4.next()
            goto L_0x0006
        L_0x003a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.CG.m29if(org.xmlpull.v1.XmlPullParser):void");
    }

    /* renamed from: for reason: not valid java name */
    private void m28for(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int next = xmlPullParser.next();
        String str = "";
        while (next != 1) {
            if (next != 0) {
                switch (next) {
                    case 2:
                        str = xmlPullParser.getName();
                        break;
                    case 3:
                        if (!xmlPullParser.getName().equals("EM")) {
                            break;
                        } else {
                            return;
                        }
                    case 4:
                        if (str != null && str.equals("M")) {
                            this.f68break.add(xmlPullParser.getText());
                            break;
                        }
                    default:
                        TL.m338new(f67void, "Found unexpected event type: ".concat(String.valueOf(next)));
                        break;
                }
            }
            next = xmlPullParser.next();
        }
    }

    /* renamed from: int reason: not valid java name */
    private void m30int(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        String str = "";
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                this.f71char = PH.m275do().m283do((String[]) arrayList.toArray(new String[0]));
                return;
            } else if (next != 0) {
                switch (next) {
                    case 2:
                        str = xmlPullParser.getName();
                        break;
                    case 3:
                        if (!xmlPullParser.getName().equals("PX")) {
                            break;
                        } else {
                            return;
                        }
                    case 4:
                        if (str != null && str.equals("P")) {
                            String text = xmlPullParser.getText();
                            if (text.indexOf(46) >= 0) {
                                arrayList.add(text);
                                break;
                            } else {
                                arrayList.add("android.permission.".concat(String.valueOf(text)));
                                break;
                            }
                        }
                    default:
                        TL.m338new(f67void, "Found unexpected event type: ".concat(String.valueOf(next)));
                        break;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003c, code lost:
        continue;
     */
    /* renamed from: do reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m27do(org.xmlpull.v1.XmlPullParser r4) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r3 = this;
            int r0 = r4.next()
            java.lang.String r1 = ""
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            r3.f72do = r2
        L_0x000d:
            r2 = 1
            if (r0 == r2) goto L_0x0041
            if (r0 == 0) goto L_0x003c
            switch(r0) {
                case 2: goto L_0x0037;
                case 3: goto L_0x002a;
                case 4: goto L_0x0016;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x003c
        L_0x0016:
            if (r1 == 0) goto L_0x003c
            java.lang.String r0 = "H"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x003c
            java.util.HashSet<java.lang.String> r0 = r3.f72do
            java.lang.String r2 = r4.getText()
            r0.add(r2)
            goto L_0x003c
        L_0x002a:
            java.lang.String r0 = r4.getName()
            java.lang.String r2 = "MW"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x003c
            return
        L_0x0037:
            java.lang.String r0 = r4.getName()
            r1 = r0
        L_0x003c:
            int r0 = r4.next()
            goto L_0x000d
        L_0x0041:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.CG.m27do(org.xmlpull.v1.XmlPullParser):void");
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final void m31if(InputStream inputStream) throws InterruptedIOException {
        if (inputStream != null) {
            try {
                XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                newPullParser.setInput(new InputStreamReader(inputStream));
                Object obj = null;
                for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                    if (eventType != 0) {
                        switch (eventType) {
                            case 2:
                                if (!newPullParser.getName().equals("PS")) {
                                    if (!newPullParser.getName().equals("PX")) {
                                        if (!newPullParser.getName().equals("MW")) {
                                            if (!newPullParser.getName().equals("EM")) {
                                                obj = newPullParser.getName();
                                                break;
                                            } else {
                                                m28for(newPullParser);
                                                break;
                                            }
                                        } else {
                                            m27do(newPullParser);
                                            break;
                                        }
                                    } else {
                                        m30int(newPullParser);
                                        break;
                                    }
                                } else {
                                    m29if(newPullParser);
                                    break;
                                }
                            case 3:
                                obj = null;
                                break;
                            case 4:
                                if (obj != null) {
                                    if (!obj.equals("w")) {
                                        if (!obj.equals("O")) {
                                            if (!obj.equals("D")) {
                                                if (!obj.equals("cpath")) {
                                                    if (!obj.equals("Q")) {
                                                        if (!obj.equals("N")) {
                                                            if (!obj.equals("S")) {
                                                                if (!obj.equals("SN")) {
                                                                    break;
                                                                } else {
                                                                    this.f69byte = newPullParser.getText();
                                                                    break;
                                                                }
                                                            } else {
                                                                this.f78this = newPullParser.getText();
                                                                break;
                                                            }
                                                        } else {
                                                            this.f73else = newPullParser.getText();
                                                            break;
                                                        }
                                                    } else {
                                                        this.f79try = Integer.valueOf(newPullParser.getText()).intValue();
                                                        break;
                                                    }
                                                } else {
                                                    this.f70case = newPullParser.getText();
                                                    break;
                                                }
                                            } else {
                                                this.f76int = Long.valueOf(newPullParser.getText()).longValue();
                                                break;
                                            }
                                        } else {
                                            this.f74for = Long.valueOf(newPullParser.getText()).longValue();
                                            break;
                                        }
                                    } else {
                                        this.f75if = newPullParser.getText();
                                        break;
                                    }
                                } else {
                                    break;
                                }
                        }
                    }
                }
            } catch (InterruptedIOException unused) {
                throw new InterruptedIOException();
            } catch (IOException unused2) {
            } catch (XmlPullParserException e) {
                TL.m337int(f67void, "XML Parse Error", e);
            }
        }
    }
}
