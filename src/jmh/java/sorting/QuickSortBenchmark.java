package sorting;

import org.openjdk.jmh.annotations.*;
import util.Array;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 */
@BenchmarkMode(Mode.SampleTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(1)
@Warmup(iterations = 1)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
public class QuickSortBenchmark {
    private  Integer[] array;
    private Sort s1, s2, s3;

    @Setup(Level.Invocation)
    public void setupInitialArray(){
//        this.array = Array.getArray(10_000_000);
        this.array = Array.getArray(100_000);
        this.s1 = Quicksort.createNaiveRecursive(Arrays.copyOf(array, array.length));
        this.s2 = Quicksort.createRecursive(Arrays.copyOf(array, array.length));
        this.s3 = Quicksort.createIterative(Arrays.copyOf(array, array.length));
    }
    @Benchmark
    public void naiveQuickSort() {
        s1.sort();
    }

    @Benchmark
    public void randomQuickSort() {
        s2.sort();
    }

    @Benchmark
    public void iterativeQuickSort() {
        s3.sort();
    }

    @Benchmark
    public void arraySort() {
        Collections.sort(Arrays.asList(array));
    }
}
