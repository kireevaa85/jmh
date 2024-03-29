package ru.kireev.jmh;

public class MyMapInt {

    private final int size;

    private final String[] entriesKey;
    private final int[] entriesValue;
    private final boolean[] entriesOcupied;

    MyMapInt(int size) {
        this.size = size;
        entriesKey = new String[this.size];
        entriesValue = new int[this.size];
        entriesOcupied = new boolean[this.size];
    }

    void put(String key, int value) {
        int index = getIndex(key);

        this.entriesKey[index] = key;
        this.entriesValue[index] = value;
        this.entriesOcupied[index] = true;
    }

    int get(String key) {
        int indexBase = getBaseIndex(key);

        for (int idx = indexBase; idx < entriesKey.length; idx++) {
            if (key.equals(entriesKey[idx])) {
                return entriesValue[idx];
            }
        }
        throw new RuntimeException("Value not found, key:" + key);
    }

    private int getBaseIndex(String key) {
        return (key.hashCode() & 0x7fffffff) % this.size;
    }

    private int getIndex(String key) {
        int index = getBaseIndex(key);

        while (index < this.size && entriesOcupied[index]) {
            index++;
        }

        if (index == this.size) {
            throw new RuntimeException("Map is full, key:" + key);
        }
        return index;
    }

}
