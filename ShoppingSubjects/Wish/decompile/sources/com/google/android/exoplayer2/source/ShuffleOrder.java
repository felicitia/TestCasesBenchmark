package com.google.android.exoplayer2.source;

public interface ShuffleOrder {

    public static final class UnshuffledShuffleOrder implements ShuffleOrder {
        private final int length;

        public int getPreviousIndex(int i) {
            int i2 = i - 1;
            if (i2 >= 0) {
                return i2;
            }
            return -1;
        }

        public UnshuffledShuffleOrder(int i) {
            this.length = i;
        }

        public int getLength() {
            return this.length;
        }

        public int getNextIndex(int i) {
            int i2 = i + 1;
            if (i2 < this.length) {
                return i2;
            }
            return -1;
        }

        public int getLastIndex() {
            if (this.length > 0) {
                return this.length - 1;
            }
            return -1;
        }

        public int getFirstIndex() {
            return this.length > 0 ? 0 : -1;
        }
    }

    int getFirstIndex();

    int getLastIndex();

    int getLength();

    int getNextIndex(int i);

    int getPreviousIndex(int i);
}
