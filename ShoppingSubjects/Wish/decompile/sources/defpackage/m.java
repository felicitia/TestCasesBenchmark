package defpackage;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* renamed from: m reason: default package */
/* compiled from: GA */
public final class m implements Serializable {
    public volatile byte[] sk2;
    public volatile long timeOfStateCreation;
    public volatile byte[] uuid;

    /* renamed from: m$a */
    /* compiled from: GA */
    static final class a implements Serializable {
        private final byte[] shaHash;
        private final byte[] sk2;
        private final long timeOfStateCreation;
        private final byte[] uuid;

        a(m mVar) {
            this.sk2 = mVar.sk2;
            this.uuid = mVar.uuid;
            this.timeOfStateCreation = mVar.timeOfStateCreation;
            int i = 0;
            int length = (this.sk2 != null ? this.sk2.length : 0) + 0;
            if (this.uuid != null) {
                i = this.uuid.length;
            }
            ByteBuffer allocate = ByteBuffer.allocate(length + i + 8);
            if (this.sk2 != null) {
                allocate.put(this.sk2);
            }
            if (this.uuid != null) {
                allocate.put(this.uuid);
            }
            allocate.putLong(this.timeOfStateCreation);
            allocate.flip();
            this.shaHash = al.a(allocate.array(), c.j.getBytes());
        }

        /* access modifiers changed from: protected */
        public final Object readResolve() throws ObjectStreamException {
            if (!al.a(new m(this.sk2, this.uuid, this.timeOfStateCreation))) {
                return null;
            }
            int i = 0;
            int length = (this.sk2 != null ? this.sk2.length : 0) + 0;
            if (this.uuid != null) {
                i = this.uuid.length;
            }
            ByteBuffer allocate = ByteBuffer.allocate(length + i + 8);
            if (this.sk2 != null) {
                allocate.put(this.sk2);
            }
            if (this.uuid != null) {
                allocate.put(this.uuid);
            }
            allocate.putLong(this.timeOfStateCreation);
            allocate.flip();
            byte[] a = al.a(allocate.array(), c.j.getBytes());
            if (a == null || this.shaHash == null || !Arrays.equals(a, this.shaHash)) {
                return null;
            }
            return new m(this.sk2, this.uuid, this.timeOfStateCreation);
        }
    }

    private m() {
    }

    public m(byte[] bArr, byte[] bArr2, long j) {
        this.sk2 = bArr;
        this.uuid = bArr2;
        this.timeOfStateCreation = j;
    }

    public final boolean a() {
        return this.sk2 != null;
    }

    /* access modifiers changed from: protected */
    public final Object writeReplace() throws ObjectStreamException {
        return new a(this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        throw new InvalidObjectException("Invalid access");
    }
}
