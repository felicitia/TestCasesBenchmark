package com.stripe.exception;

public abstract class StripeException extends Exception {
    private String requestId;
    private Integer statusCode;

    public StripeException(String str, String str2, Integer num) {
        super(str, null);
        this.requestId = str2;
        this.statusCode = num;
    }

    public StripeException(String str, String str2, Integer num, Throwable th) {
        super(str, th);
        this.statusCode = num;
        this.requestId = str2;
    }

    public String toString() {
        String str = "";
        if (this.requestId != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("; request-id: ");
            sb.append(this.requestId);
            str = sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(super.toString());
        sb2.append(str);
        return sb2.toString();
    }
}
