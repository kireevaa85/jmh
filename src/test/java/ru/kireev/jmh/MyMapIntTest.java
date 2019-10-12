package ru.kireev.jmh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyMapIntTest {

    @Test
    public void putAndGet() {
        MyMapInt map = new MyMapInt(1);
        int value = 88;
        String key = "key";

        map.put(key, value);
        assertEquals(value, map.get(key), "value");
    }

    @Test
    public void putAndGetSequence() {
        int size = 10;
        String keyStr = "k";
        MyMapInt map = new MyMapInt(size);

        for (int idx = 0; idx < size; idx++) {
            map.put(keyStr + idx, idx);
        }

        for (int idx = 0; idx < size; idx++) {
            assertEquals(idx, map.get(keyStr + idx), "value idx:" + idx);
        }
    }

}
