package okhttp3.internal.b;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Protocol;

/* compiled from: StatusLine */
public final class k {
    public final Protocol a;
    public final int b;
    public final String c;

    public k(Protocol protocol, int i, String str) {
        this.a = protocol;
        this.b = i;
        this.c = str;
    }

    public static k a(String str) throws IOException {
        Protocol protocol;
        int i = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() < 9 || str.charAt(8) != ' ') {
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected status line: ");
                sb.append(str);
                throw new ProtocolException(sb.toString());
            }
            int charAt = str.charAt(7) - '0';
            if (charAt == 0) {
                protocol = Protocol.HTTP_1_0;
            } else if (charAt == 1) {
                protocol = Protocol.HTTP_1_1;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Unexpected status line: ");
                sb2.append(str);
                throw new ProtocolException(sb2.toString());
            }
        } else if (str.startsWith("ICY ")) {
            protocol = Protocol.HTTP_1_0;
            i = 4;
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Unexpected status line: ");
            sb3.append(str);
            throw new ProtocolException(sb3.toString());
        }
        int i2 = i + 3;
        if (str.length() < i2) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Unexpected status line: ");
            sb4.append(str);
            throw new ProtocolException(sb4.toString());
        }
        try {
            int parseInt = Integer.parseInt(str.substring(i, i2));
            String str2 = "";
            if (str.length() > i2) {
                if (str.charAt(i2) != ' ') {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("Unexpected status line: ");
                    sb5.append(str);
                    throw new ProtocolException(sb5.toString());
                }
                str2 = str.substring(i + 4);
            }
            return new k(protocol, parseInt, str2);
        } catch (NumberFormatException unused) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append("Unexpected status line: ");
            sb6.append(str);
            throw new ProtocolException(sb6.toString());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
        sb.append(' ');
        sb.append(this.b);
        if (this.c != null) {
            sb.append(' ');
            sb.append(this.c);
        }
        return sb.toString();
    }
}
