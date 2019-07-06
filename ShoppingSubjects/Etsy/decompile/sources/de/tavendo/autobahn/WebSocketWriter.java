package de.tavendo.autobahn;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import de.tavendo.autobahn.g.C0178g;
import de.tavendo.autobahn.g.a;
import de.tavendo.autobahn.g.b;
import de.tavendo.autobahn.g.c;
import de.tavendo.autobahn.g.e;
import de.tavendo.autobahn.g.h;
import de.tavendo.autobahn.g.j;
import de.tavendo.autobahn.g.k;
import de.tavendo.autobahn.g.m;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Random;

public class WebSocketWriter extends Handler {
    private static final boolean DEBUG = true;
    private static final String TAG = "de.tavendo.autobahn.WebSocketWriter";
    private final b mBuffer;
    private final Looper mLooper;
    private final Handler mMaster;
    private final h mOptions;
    private final Random mRng = new Random();
    private final SocketChannel mSocket;

    public WebSocketWriter(Looper looper, Handler handler, SocketChannel socketChannel, h hVar) {
        super(looper);
        this.mLooper = looper;
        this.mMaster = handler;
        this.mSocket = socketChannel;
        this.mOptions = hVar;
        this.mBuffer = new b(hVar.b() + 14, 262144);
        Log.d(TAG, "created");
    }

    public void forward(Object obj) {
        Message obtainMessage = obtainMessage();
        obtainMessage.obj = obj;
        sendMessage(obtainMessage);
    }

    private void notify(Object obj) {
        Message obtainMessage = this.mMaster.obtainMessage();
        obtainMessage.obj = obj;
        this.mMaster.sendMessage(obtainMessage);
    }

    private String newHandshakeKey() {
        byte[] bArr = new byte[16];
        this.mRng.nextBytes(bArr);
        return Base64.encodeToString(bArr, 2);
    }

    private byte[] newFrameMask() {
        byte[] bArr = new byte[4];
        this.mRng.nextBytes(bArr);
        return bArr;
    }

    private void sendClientHandshake(b bVar) throws IOException {
        String str;
        if (bVar.c != null) {
            StringBuilder sb = new StringBuilder(String.valueOf(bVar.b));
            sb.append("?");
            sb.append(bVar.c);
            str = sb.toString();
        } else {
            str = bVar.b;
        }
        b bVar2 = this.mBuffer;
        StringBuilder sb2 = new StringBuilder("GET ");
        sb2.append(str);
        sb2.append(" HTTP/1.1");
        bVar2.a(sb2.toString());
        this.mBuffer.e();
        b bVar3 = this.mBuffer;
        StringBuilder sb3 = new StringBuilder("Host: ");
        sb3.append(bVar.a);
        bVar3.a(sb3.toString());
        this.mBuffer.e();
        this.mBuffer.a("Upgrade: WebSocket");
        this.mBuffer.e();
        this.mBuffer.a("Connection: Upgrade");
        this.mBuffer.e();
        b bVar4 = this.mBuffer;
        StringBuilder sb4 = new StringBuilder("Sec-WebSocket-Key: ");
        sb4.append(newHandshakeKey());
        bVar4.a(sb4.toString());
        this.mBuffer.e();
        if (bVar.d != null && !bVar.d.equals("")) {
            b bVar5 = this.mBuffer;
            StringBuilder sb5 = new StringBuilder("Origin: ");
            sb5.append(bVar.d);
            bVar5.a(sb5.toString());
            this.mBuffer.e();
        }
        if (bVar.e != null && bVar.e.length > 0) {
            this.mBuffer.a("Sec-WebSocket-Protocol: ");
            for (String a : bVar.e) {
                this.mBuffer.a(a);
                this.mBuffer.a(", ");
            }
            this.mBuffer.e();
        }
        this.mBuffer.a("Sec-WebSocket-Version: 13");
        this.mBuffer.e();
        this.mBuffer.e();
    }

    private void sendClose(c cVar) throws IOException, WebSocketException {
        byte[] bArr;
        if (cVar.a > 0) {
            byte[] bArr2 = null;
            if (cVar.b == null || cVar.b.equals("")) {
                bArr = new byte[2];
            } else {
                byte[] bytes = cVar.b.getBytes("UTF-8");
                bArr = new byte[(2 + bytes.length)];
                for (int i = 0; i < bytes.length; i++) {
                    bArr[i + 2] = bytes[i];
                }
            }
            if (bArr == null || bArr.length <= 125) {
                bArr[0] = (byte) ((cVar.a >> 8) & 255);
                bArr[1] = (byte) (cVar.a & 255);
                sendFrame(8, true, bArr);
                return;
            }
            throw new WebSocketException("close payload exceeds 125 octets");
        }
        sendFrame(8, true, null);
    }

    private void sendPing(C0178g gVar) throws IOException, WebSocketException {
        if (gVar.a == null || gVar.a.length <= 125) {
            sendFrame(9, true, gVar.a);
            return;
        }
        throw new WebSocketException("ping payload exceeds 125 octets");
    }

    private void sendPong(h hVar) throws IOException, WebSocketException {
        if (hVar.a == null || hVar.a.length <= 125) {
            sendFrame(10, true, hVar.a);
            return;
        }
        throw new WebSocketException("pong payload exceeds 125 octets");
    }

    private void sendBinaryMessage(a aVar) throws IOException, WebSocketException {
        if (aVar.a.length > this.mOptions.c()) {
            throw new WebSocketException("message payload exceeds payload limit");
        }
        sendFrame(2, true, aVar.a);
    }

    private void sendTextMessage(m mVar) throws IOException, WebSocketException {
        byte[] bytes = mVar.a.getBytes("UTF-8");
        if (bytes.length > this.mOptions.c()) {
            throw new WebSocketException("message payload exceeds payload limit");
        }
        sendFrame(1, true, bytes);
    }

    private void sendRawTextMessage(k kVar) throws IOException, WebSocketException {
        if (kVar.a.length > this.mOptions.c()) {
            throw new WebSocketException("message payload exceeds payload limit");
        }
        sendFrame(1, true, kVar.a);
    }

    /* access modifiers changed from: protected */
    public void sendFrame(int i, boolean z, byte[] bArr) throws IOException {
        if (bArr != null) {
            sendFrame(i, z, bArr, 0, bArr.length);
            return;
        }
        sendFrame(i, z, null, 0, 0);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendFrame(int r20, boolean r21, byte[] r22, int r23, int r24) throws java.io.IOException {
        /*
            r19 = this;
            r0 = r19
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = -128(0xffffffffffffff80, float:NaN)
            r5 = 0
            if (r21 == 0) goto L_0x0012
            byte r6 = (byte) r4
            r7 = r6
            r6 = r20
            goto L_0x0015
        L_0x0012:
            r6 = r20
            r7 = r5
        L_0x0015:
            byte r6 = (byte) r6
            r6 = r6 | r7
            byte r6 = (byte) r6
            de.tavendo.autobahn.b r7 = r0.mBuffer
            r7.write(r6)
            de.tavendo.autobahn.h r6 = r0.mOptions
            boolean r6 = r6.h()
            if (r6 == 0) goto L_0x0026
            goto L_0x0027
        L_0x0026:
            r4 = r5
        L_0x0027:
            long r6 = (long) r3
            r8 = 125(0x7d, double:6.2E-322)
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            r9 = 2
            r11 = 1
            if (r10 > 0) goto L_0x003d
            int r10 = (int) r6
            byte r10 = (byte) r10
            r4 = r4 | r10
            byte r4 = (byte) r4
            de.tavendo.autobahn.b r10 = r0.mBuffer
            r10.write(r4)
        L_0x0039:
            r17 = r6
            goto L_0x00ca
        L_0x003d:
            r12 = 65535(0xffff, double:3.23786E-319)
            int r10 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            r12 = 8
            r13 = 255(0xff, double:1.26E-321)
            if (r10 > 0) goto L_0x0066
            r4 = r4 | 126(0x7e, float:1.77E-43)
            byte r4 = (byte) r4
            de.tavendo.autobahn.b r10 = r0.mBuffer
            r10.write(r4)
            de.tavendo.autobahn.b r4 = r0.mBuffer
            byte[] r10 = new byte[r9]
            long r15 = r6 >> r12
            long r8 = r15 & r13
            int r8 = (int) r8
            byte r8 = (byte) r8
            r10[r5] = r8
            long r8 = r6 & r13
            int r8 = (int) r8
            byte r8 = (byte) r8
            r10[r11] = r8
            r4.write(r10)
            goto L_0x0039
        L_0x0066:
            r4 = r4 | 127(0x7f, float:1.78E-43)
            byte r4 = (byte) r4
            de.tavendo.autobahn.b r8 = r0.mBuffer
            r8.write(r4)
            de.tavendo.autobahn.b r4 = r0.mBuffer
            byte[] r8 = new byte[r12]
            r9 = 56
            long r9 = r6 >> r9
            long r11 = r9 & r13
            int r9 = (int) r11
            byte r9 = (byte) r9
            r8[r5] = r9
            r9 = 48
            long r9 = r6 >> r9
            long r11 = r9 & r13
            int r9 = (int) r11
            byte r9 = (byte) r9
            r10 = 1
            r8[r10] = r9
            r9 = 40
            long r9 = r6 >> r9
            long r11 = r9 & r13
            int r9 = (int) r11
            byte r9 = (byte) r9
            r10 = 2
            r8[r10] = r9
            r9 = 32
            long r9 = r6 >> r9
            long r11 = r9 & r13
            int r9 = (int) r11
            byte r9 = (byte) r9
            r10 = 3
            r8[r10] = r9
            r9 = 24
            long r9 = r6 >> r9
            long r11 = r9 & r13
            int r9 = (int) r11
            byte r9 = (byte) r9
            r10 = 4
            r8[r10] = r9
            r9 = 5
            r10 = 16
            long r10 = r6 >> r10
            r17 = r6
            long r5 = r10 & r13
            int r5 = (int) r5
            byte r5 = (byte) r5
            r8[r9] = r5
            r5 = 6
            r6 = 8
            long r6 = r17 >> r6
            long r9 = r6 & r13
            int r6 = (int) r9
            byte r6 = (byte) r6
            r8[r5] = r6
            r5 = 7
            long r6 = r17 & r13
            int r6 = (int) r6
            byte r6 = (byte) r6
            r8[r5] = r6
            r4.write(r8)
        L_0x00ca:
            r4 = 0
            byte[] r4 = (byte[]) r4
            de.tavendo.autobahn.h r5 = r0.mOptions
            boolean r5 = r5.h()
            if (r5 == 0) goto L_0x00fa
            byte[] r4 = r19.newFrameMask()
            de.tavendo.autobahn.b r5 = r0.mBuffer
            r6 = 0
            byte r7 = r4[r6]
            r5.write(r7)
            de.tavendo.autobahn.b r5 = r0.mBuffer
            r7 = 1
            byte r7 = r4[r7]
            r5.write(r7)
            de.tavendo.autobahn.b r5 = r0.mBuffer
            r7 = 2
            byte r7 = r4[r7]
            r5.write(r7)
            de.tavendo.autobahn.b r5 = r0.mBuffer
            r7 = 3
            byte r7 = r4[r7]
            r5.write(r7)
            goto L_0x00fb
        L_0x00fa:
            r6 = 0
        L_0x00fb:
            r7 = 0
            int r5 = (r17 > r7 ? 1 : (r17 == r7 ? 0 : -1))
            if (r5 <= 0) goto L_0x0123
            de.tavendo.autobahn.h r5 = r0.mOptions
            boolean r5 = r5.h()
            if (r5 == 0) goto L_0x011e
        L_0x0109:
            long r7 = (long) r6
            int r5 = (r7 > r17 ? 1 : (r7 == r17 ? 0 : -1))
            if (r5 < 0) goto L_0x010f
            goto L_0x011e
        L_0x010f:
            int r5 = r6 + r2
            byte r7 = r1[r5]
            int r8 = r6 % 4
            byte r8 = r4[r8]
            r7 = r7 ^ r8
            byte r7 = (byte) r7
            r1[r5] = r7
            int r6 = r6 + 1
            goto L_0x0109
        L_0x011e:
            de.tavendo.autobahn.b r4 = r0.mBuffer
            r4.write(r1, r2, r3)
        L_0x0123:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: de.tavendo.autobahn.WebSocketWriter.sendFrame(int, boolean, byte[], int, int):void");
    }

    public void handleMessage(Message message) {
        try {
            this.mBuffer.c();
            processMessage(message.obj);
            this.mBuffer.b();
            while (this.mBuffer.d() > 0) {
                this.mSocket.write(this.mBuffer.a());
            }
        } catch (Exception e) {
            com.google.a.a.a.a.a.a.a(e);
            notify(new e(e));
        }
    }

    /* access modifiers changed from: protected */
    public void processMessage(Object obj) throws IOException, WebSocketException {
        if (obj instanceof m) {
            sendTextMessage((m) obj);
        } else if (obj instanceof k) {
            sendRawTextMessage((k) obj);
        } else if (obj instanceof a) {
            sendBinaryMessage((a) obj);
        } else if (obj instanceof C0178g) {
            sendPing((C0178g) obj);
        } else if (obj instanceof h) {
            sendPong((h) obj);
        } else if (obj instanceof c) {
            sendClose((c) obj);
        } else if (obj instanceof b) {
            sendClientHandshake((b) obj);
        } else if (obj instanceof j) {
            this.mLooper.quit();
            Log.d(TAG, "ended");
        } else {
            processAppMessage(obj);
        }
    }

    /* access modifiers changed from: protected */
    public void processAppMessage(Object obj) throws WebSocketException, IOException {
        throw new WebSocketException("unknown message received by WebSocketWriter");
    }
}
