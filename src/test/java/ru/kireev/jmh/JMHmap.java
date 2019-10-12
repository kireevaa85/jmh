package ru.kireev.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@State(value = Scope.Thread)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JMHmap {

    private int mapSize = 200_000;
    private String keyStr = "k";

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(JMHmap.class.getSimpleName()).forks(1).build();
        new Runner(opt).run();
    }

    private MyMapInt myMap;
    private Map<String, Integer> hashMap;

    @Setup
    public void setup() throws Exception {
        myMap = new MyMapInt(mapSize * 2);
        hashMap = new HashMap<>(mapSize);
    }

    @Benchmark
    public long myMapTest() throws Exception {
        for (int idx = 0; idx < mapSize; idx++) {
            myMap.put(keyStr + idx, idx);
        }

        int summ = 0;
        for (int idx = 0; idx < mapSize; idx++) {
            summ += myMap.get(keyStr + idx);
        }
        return summ;
    }

    @Benchmark
    public long HashMapTest() {
        for (int idx = 0; idx < mapSize; idx++) {
            hashMap.put(keyStr + idx, idx);
        }

        int summ = 0;
        for (int idx = 0; idx < mapSize; idx++) {
            summ += hashMap.get(keyStr + idx);
        }
        return summ;
    }

}
