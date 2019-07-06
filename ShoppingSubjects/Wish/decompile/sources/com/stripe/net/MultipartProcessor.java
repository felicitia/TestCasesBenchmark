package com.stripe.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.Random;

public class MultipartProcessor {
    private final String boundary;
    private String charset;
    private HttpURLConnection conn;
    private OutputStream outputStream;
    private PrintWriter writer;

    public static String getBoundary() {
        return String.valueOf(Long.valueOf(Math.abs(new Random().nextLong())));
    }

    public MultipartProcessor(HttpURLConnection httpURLConnection, String str, String str2) throws IOException {
        this.boundary = str;
        this.charset = str2;
        this.conn = httpURLConnection;
        this.outputStream = httpURLConnection.getOutputStream();
        this.writer = new PrintWriter(new OutputStreamWriter(this.outputStream, str2), true);
    }

    public void addFormField(String str, String str2) {
        PrintWriter printWriter = this.writer;
        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(this.boundary);
        printWriter.append(sb.toString()).append("\r\n");
        PrintWriter printWriter2 = this.writer;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Content-Disposition: form-data; name=\"");
        sb2.append(str);
        sb2.append("\"");
        printWriter2.append(sb2.toString()).append("\r\n");
        this.writer.append("\r\n");
        this.writer.append(str2).append("\r\n");
        this.writer.flush();
    }

    /* JADX INFO: finally extract failed */
    public void addFileField(String str, File file) throws IOException {
        String name = file.getName();
        PrintWriter printWriter = this.writer;
        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(this.boundary);
        printWriter.append(sb.toString()).append("\r\n");
        PrintWriter printWriter2 = this.writer;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Content-Disposition: form-data; name=\"");
        sb2.append(str);
        sb2.append("\"; filename=\"");
        sb2.append(name);
        sb2.append("\"");
        printWriter2.append(sb2.toString()).append("\r\n");
        String guessContentTypeFromName = URLConnection.guessContentTypeFromName(name);
        PrintWriter printWriter3 = this.writer;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Content-Type: ");
        sb3.append(guessContentTypeFromName);
        printWriter3.append(sb3.toString()).append("\r\n");
        this.writer.append("Content-Transfer-Encoding: binary").append("\r\n");
        this.writer.append("\r\n");
        this.writer.flush();
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    this.outputStream.write(bArr, 0, read);
                } else {
                    this.outputStream.flush();
                    fileInputStream.close();
                    this.writer.append("\r\n");
                    this.writer.flush();
                    return;
                }
            }
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    public void finish() throws IOException {
        PrintWriter printWriter = this.writer;
        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(this.boundary);
        sb.append("--");
        printWriter.append(sb.toString()).append("\r\n");
        this.writer.flush();
        this.writer.close();
        this.outputStream.flush();
        this.outputStream.close();
    }
}
