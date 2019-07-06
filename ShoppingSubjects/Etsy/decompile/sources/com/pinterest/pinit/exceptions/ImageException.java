package com.pinterest.pinit.exceptions;

public class ImageException extends RuntimeException {
    public static final String MESSAGE = "imageUrl and/or imageUri cannot be null! Did you call setImageUrl(String) or setImageUri(Uri)?";

    public ImageException() {
        super(MESSAGE);
    }
}
