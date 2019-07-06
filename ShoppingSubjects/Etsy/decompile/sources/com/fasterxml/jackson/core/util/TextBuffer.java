package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.BufferRecycler.CharBufferType;
import java.math.BigDecimal;
import java.util.ArrayList;

public final class TextBuffer {
    static final int MAX_SEGMENT_LEN = 262144;
    static final int MIN_SEGMENT_LEN = 1000;
    static final char[] NO_CHARS = new char[0];
    private final BufferRecycler _allocator;
    private char[] _currentSegment;
    private int _currentSize;
    private boolean _hasSegments = false;
    private char[] _inputBuffer;
    private int _inputLen;
    private int _inputStart;
    private char[] _resultArray;
    private String _resultString;
    private int _segmentSize;
    private ArrayList<char[]> _segments;

    public TextBuffer(BufferRecycler bufferRecycler) {
        this._allocator = bufferRecycler;
    }

    public void releaseBuffers() {
        if (this._allocator == null) {
            resetWithEmpty();
        } else if (this._currentSegment != null) {
            resetWithEmpty();
            char[] cArr = this._currentSegment;
            this._currentSegment = null;
            this._allocator.releaseCharBuffer(CharBufferType.TEXT_BUFFER, cArr);
        }
    }

    public void resetWithEmpty() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
    }

    public void resetWithShared(char[] cArr, int i, int i2) {
        this._resultString = null;
        this._resultArray = null;
        this._inputBuffer = cArr;
        this._inputStart = i;
        this._inputLen = i2;
        if (this._hasSegments) {
            clearSegments();
        }
    }

    public void resetWithCopy(char[] cArr, int i, int i2) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        } else if (this._currentSegment == null) {
            this._currentSegment = findBuffer(i2);
        }
        this._segmentSize = 0;
        this._currentSize = 0;
        append(cArr, i, i2);
    }

    public void resetWithString(String str) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = str;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        this._currentSize = 0;
    }

    private char[] findBuffer(int i) {
        if (this._allocator != null) {
            return this._allocator.allocCharBuffer(CharBufferType.TEXT_BUFFER, i);
        }
        return new char[Math.max(i, 1000)];
    }

    private void clearSegments() {
        this._hasSegments = false;
        this._segments.clear();
        this._segmentSize = 0;
        this._currentSize = 0;
    }

    public int size() {
        if (this._inputStart >= 0) {
            return this._inputLen;
        }
        if (this._resultArray != null) {
            return this._resultArray.length;
        }
        if (this._resultString != null) {
            return this._resultString.length();
        }
        return this._segmentSize + this._currentSize;
    }

    public int getTextOffset() {
        if (this._inputStart >= 0) {
            return this._inputStart;
        }
        return 0;
    }

    public boolean hasTextAsCharacters() {
        if (this._inputStart >= 0 || this._resultArray != null || this._resultString == null) {
            return true;
        }
        return false;
    }

    public char[] getTextBuffer() {
        if (this._inputStart >= 0) {
            return this._inputBuffer;
        }
        if (this._resultArray != null) {
            return this._resultArray;
        }
        if (this._resultString != null) {
            char[] charArray = this._resultString.toCharArray();
            this._resultArray = charArray;
            return charArray;
        } else if (!this._hasSegments) {
            return this._currentSegment;
        } else {
            return contentsAsArray();
        }
    }

    public String contentsAsString() {
        String str;
        if (this._resultString == null) {
            if (this._resultArray != null) {
                this._resultString = new String(this._resultArray);
            } else if (this._inputStart < 0) {
                int i = this._segmentSize;
                int i2 = this._currentSize;
                if (i == 0) {
                    if (i2 == 0) {
                        str = "";
                    } else {
                        str = new String(this._currentSegment, 0, i2);
                    }
                    this._resultString = str;
                } else {
                    StringBuilder sb = new StringBuilder(i + i2);
                    if (this._segments != null) {
                        int size = this._segments.size();
                        for (int i3 = 0; i3 < size; i3++) {
                            char[] cArr = (char[]) this._segments.get(i3);
                            sb.append(cArr, 0, cArr.length);
                        }
                    }
                    sb.append(this._currentSegment, 0, this._currentSize);
                    this._resultString = sb.toString();
                }
            } else if (this._inputLen < 1) {
                String str2 = "";
                this._resultString = str2;
                return str2;
            } else {
                this._resultString = new String(this._inputBuffer, this._inputStart, this._inputLen);
            }
        }
        return this._resultString;
    }

    public char[] contentsAsArray() {
        char[] cArr = this._resultArray;
        if (cArr != null) {
            return cArr;
        }
        char[] buildResultArray = buildResultArray();
        this._resultArray = buildResultArray;
        return buildResultArray;
    }

    public BigDecimal contentsAsDecimal() throws NumberFormatException {
        if (this._resultArray != null) {
            return new BigDecimal(this._resultArray);
        }
        if (this._inputStart >= 0) {
            return new BigDecimal(this._inputBuffer, this._inputStart, this._inputLen);
        }
        if (this._segmentSize == 0) {
            return new BigDecimal(this._currentSegment, 0, this._currentSize);
        }
        return new BigDecimal(contentsAsArray());
    }

    public double contentsAsDouble() throws NumberFormatException {
        return NumberInput.parseDouble(contentsAsString());
    }

    public void ensureNotShared() {
        if (this._inputStart >= 0) {
            unshare(16);
        }
    }

    public void append(char c) {
        if (this._inputStart >= 0) {
            unshare(16);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr = this._currentSegment;
        if (this._currentSize >= cArr.length) {
            expand(1);
            cArr = this._currentSegment;
        }
        int i = this._currentSize;
        this._currentSize = i + 1;
        cArr[i] = c;
    }

    public void append(char[] cArr, int i, int i2) {
        if (this._inputStart >= 0) {
            unshare(i2);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr2 = this._currentSegment;
        int length = cArr2.length - this._currentSize;
        if (length >= i2) {
            System.arraycopy(cArr, i, cArr2, this._currentSize, i2);
            this._currentSize += i2;
            return;
        }
        if (length > 0) {
            System.arraycopy(cArr, i, cArr2, this._currentSize, length);
            i += length;
            i2 -= length;
        }
        do {
            expand(i2);
            int min = Math.min(this._currentSegment.length, i2);
            System.arraycopy(cArr, i, this._currentSegment, 0, min);
            this._currentSize += min;
            i += min;
            i2 -= min;
        } while (i2 > 0);
    }

    public void append(String str, int i, int i2) {
        if (this._inputStart >= 0) {
            unshare(i2);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr = this._currentSegment;
        int length = cArr.length - this._currentSize;
        if (length >= i2) {
            str.getChars(i, i + i2, cArr, this._currentSize);
            this._currentSize += i2;
            return;
        }
        if (length > 0) {
            int i3 = i + length;
            str.getChars(i, i3, cArr, this._currentSize);
            i2 -= length;
            i = i3;
        }
        while (true) {
            expand(i2);
            int min = Math.min(this._currentSegment.length, i2);
            int i4 = i + min;
            str.getChars(i, i4, this._currentSegment, 0);
            this._currentSize += min;
            i2 -= min;
            if (i2 > 0) {
                i = i4;
            } else {
                return;
            }
        }
    }

    public char[] getCurrentSegment() {
        if (this._inputStart >= 0) {
            unshare(1);
        } else {
            char[] cArr = this._currentSegment;
            if (cArr == null) {
                this._currentSegment = findBuffer(0);
            } else if (this._currentSize >= cArr.length) {
                expand(1);
            }
        }
        return this._currentSegment;
    }

    public char[] emptyAndGetCurrentSegment() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        char[] cArr = this._currentSegment;
        if (cArr != null) {
            return cArr;
        }
        char[] findBuffer = findBuffer(0);
        this._currentSegment = findBuffer;
        return findBuffer;
    }

    public int getCurrentSegmentSize() {
        return this._currentSize;
    }

    public void setCurrentLength(int i) {
        this._currentSize = i;
    }

    public char[] finishCurrentSegment() {
        if (this._segments == null) {
            this._segments = new ArrayList<>();
        }
        this._hasSegments = true;
        this._segments.add(this._currentSegment);
        int length = this._currentSegment.length;
        this._segmentSize += length;
        char[] _charArray = _charArray(Math.min(length + (length >> 1), 262144));
        this._currentSize = 0;
        this._currentSegment = _charArray;
        return _charArray;
    }

    public char[] expandCurrentSegment() {
        int i;
        char[] cArr = this._currentSegment;
        int length = cArr.length;
        if (length == 262144) {
            i = 262145;
        } else {
            i = Math.min(262144, (length >> 1) + length);
        }
        this._currentSegment = _charArray(i);
        System.arraycopy(cArr, 0, this._currentSegment, 0, length);
        return this._currentSegment;
    }

    public String toString() {
        return contentsAsString();
    }

    private void unshare(int i) {
        int i2 = this._inputLen;
        this._inputLen = 0;
        char[] cArr = this._inputBuffer;
        this._inputBuffer = null;
        int i3 = this._inputStart;
        this._inputStart = -1;
        int i4 = i + i2;
        if (this._currentSegment == null || i4 > this._currentSegment.length) {
            this._currentSegment = findBuffer(i4);
        }
        if (i2 > 0) {
            System.arraycopy(cArr, i3, this._currentSegment, 0, i2);
        }
        this._segmentSize = 0;
        this._currentSize = i2;
    }

    private void expand(int i) {
        if (this._segments == null) {
            this._segments = new ArrayList<>();
        }
        char[] cArr = this._currentSegment;
        this._hasSegments = true;
        this._segments.add(cArr);
        this._segmentSize += cArr.length;
        int length = cArr.length;
        int i2 = length >> 1;
        if (i2 >= i) {
            i = i2;
        }
        char[] _charArray = _charArray(Math.min(262144, length + i));
        this._currentSize = 0;
        this._currentSegment = _charArray;
    }

    private char[] buildResultArray() {
        char[] cArr;
        int i;
        if (this._resultString != null) {
            return this._resultString.toCharArray();
        }
        if (this._inputStart < 0) {
            int size = size();
            if (size < 1) {
                return NO_CHARS;
            }
            cArr = _charArray(size);
            if (this._segments != null) {
                int size2 = this._segments.size();
                i = 0;
                for (int i2 = 0; i2 < size2; i2++) {
                    char[] cArr2 = (char[]) this._segments.get(i2);
                    int length = cArr2.length;
                    System.arraycopy(cArr2, 0, cArr, i, length);
                    i += length;
                }
            } else {
                i = 0;
            }
            System.arraycopy(this._currentSegment, 0, cArr, i, this._currentSize);
        } else if (this._inputLen < 1) {
            return NO_CHARS;
        } else {
            cArr = _charArray(this._inputLen);
            System.arraycopy(this._inputBuffer, this._inputStart, cArr, 0, this._inputLen);
        }
        return cArr;
    }

    private char[] _charArray(int i) {
        return new char[i];
    }
}
