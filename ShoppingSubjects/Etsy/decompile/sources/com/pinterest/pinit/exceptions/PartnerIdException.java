package com.pinterest.pinit.exceptions;

public class PartnerIdException extends RuntimeException {
    public static final String MESSAGE = "partnerId cannot be null! Did you call setPartnerId?";

    public PartnerIdException() {
        super(MESSAGE);
    }
}
