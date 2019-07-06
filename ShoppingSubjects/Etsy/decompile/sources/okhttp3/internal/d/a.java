package okhttp3.internal.d;

import java.io.File;
import java.io.IOException;

/* compiled from: FileSystem */
public interface a {
    public static final a a = new a() {
        public void a(File file) throws IOException {
            if (!file.delete() && file.exists()) {
                StringBuilder sb = new StringBuilder();
                sb.append("failed to delete ");
                sb.append(file);
                throw new IOException(sb.toString());
            }
        }

        public boolean b(File file) {
            return file.exists();
        }

        public long c(File file) {
            return file.length();
        }

        public void a(File file, File file2) throws IOException {
            a(file2);
            if (!file.renameTo(file2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("failed to rename ");
                sb.append(file);
                sb.append(" to ");
                sb.append(file2);
                throw new IOException(sb.toString());
            }
        }
    };

    void a(File file) throws IOException;

    void a(File file, File file2) throws IOException;

    boolean b(File file);

    long c(File file);
}
