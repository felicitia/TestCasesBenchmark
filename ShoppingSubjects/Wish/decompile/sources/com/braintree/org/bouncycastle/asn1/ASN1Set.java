package com.braintree.org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public abstract class ASN1Set extends ASN1Object {
    protected Vector set = new Vector();

    /* access modifiers changed from: 0000 */
    public abstract void encode(DEROutputStream dEROutputStream) throws IOException;

    public Enumeration getObjects() {
        return this.set.elements();
    }

    public int size() {
        return this.set.size();
    }

    public int hashCode() {
        Enumeration objects = getObjects();
        int size = size();
        while (objects.hasMoreElements()) {
            size = (size * 17) ^ getNext(objects).hashCode();
        }
        return size;
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(DERObject dERObject) {
        if (!(dERObject instanceof ASN1Set)) {
            return false;
        }
        ASN1Set aSN1Set = (ASN1Set) dERObject;
        if (size() != aSN1Set.size()) {
            return false;
        }
        Enumeration objects = getObjects();
        Enumeration objects2 = aSN1Set.getObjects();
        while (objects.hasMoreElements()) {
            DEREncodable next = getNext(objects);
            DEREncodable next2 = getNext(objects2);
            DERObject dERObject2 = next.getDERObject();
            DERObject dERObject3 = next2.getDERObject();
            if (dERObject2 != dERObject3 && !dERObject2.equals(dERObject3)) {
                return false;
            }
        }
        return true;
    }

    private DEREncodable getNext(Enumeration enumeration) {
        DEREncodable dEREncodable = (DEREncodable) enumeration.nextElement();
        return dEREncodable == null ? DERNull.INSTANCE : dEREncodable;
    }

    private boolean lessThanOrEqual(byte[] bArr, byte[] bArr2) {
        int min = Math.min(bArr.length, bArr2.length);
        boolean z = false;
        for (int i = 0; i != min; i++) {
            if (bArr[i] != bArr2[i]) {
                if ((bArr[i] & 255) < (bArr2[i] & 255)) {
                    z = true;
                }
                return z;
            }
        }
        if (min == bArr.length) {
            z = true;
        }
        return z;
    }

    private byte[] getEncoded(DEREncodable dEREncodable) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ASN1OutputStream(byteArrayOutputStream).writeObject(dEREncodable);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
    }

    /* access modifiers changed from: protected */
    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:29)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal$$Lambda$34/1534130292.accept(Unknown Source)
        	at java.util.ArrayList.forEach(ArrayList.java:1249)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
        	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
        */
    public void sort() {
        /*
            r9 = this;
            java.util.Vector r0 = r9.set
            int r0 = r0.size()
            r1 = 1
            if (r0 <= r1) goto L_0x0058
            java.util.Vector r0 = r9.set
            int r0 = r0.size()
            int r0 = r0 - r1
            r2 = r0
            r0 = 1
        L_0x0012:
            if (r0 == 0) goto L_0x0058
            java.util.Vector r0 = r9.set
            r3 = 0
            java.lang.Object r0 = r0.elementAt(r3)
            com.braintree.org.bouncycastle.asn1.DEREncodable r0 = (com.braintree.org.bouncycastle.asn1.DEREncodable) r0
            byte[] r0 = r9.getEncoded(r0)
            r4 = 0
            r5 = 0
        L_0x0023:
            if (r3 == r2) goto L_0x0055
            java.util.Vector r6 = r9.set
            int r7 = r3 + 1
            java.lang.Object r6 = r6.elementAt(r7)
            com.braintree.org.bouncycastle.asn1.DEREncodable r6 = (com.braintree.org.bouncycastle.asn1.DEREncodable) r6
            byte[] r6 = r9.getEncoded(r6)
            boolean r8 = r9.lessThanOrEqual(r0, r6)
            if (r8 == 0) goto L_0x003b
            r0 = r6
            goto L_0x0053
        L_0x003b:
            java.util.Vector r4 = r9.set
            java.lang.Object r4 = r4.elementAt(r3)
            java.util.Vector r5 = r9.set
            java.util.Vector r6 = r9.set
            java.lang.Object r6 = r6.elementAt(r7)
            r5.setElementAt(r6, r3)
            java.util.Vector r5 = r9.set
            r5.setElementAt(r4, r7)
            r4 = r3
            r5 = 1
        L_0x0053:
            r3 = r7
            goto L_0x0023
        L_0x0055:
            r2 = r4
            r0 = r5
            goto L_0x0012
        L_0x0058:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.braintree.org.bouncycastle.asn1.ASN1Set.sort():void");
    }

    /* access modifiers changed from: protected */
    public void addObject(DEREncodable dEREncodable) {
        this.set.addElement(dEREncodable);
    }

    public String toString() {
        return this.set.toString();
    }
}
