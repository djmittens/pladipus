package sorting;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.ThreadParams;
import org.openjdk.jmh.results.BenchmarkTaskResult;
import org.openjdk.jmh.runner.InfraControl;
import util.Array;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(1)
@Warmup(iterations = 1)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
public class QuickSortBenchmark {
    private  Integer[] array;

    @Setup(Level.Invocation)
    public void setup(){
        this.array = Array.getArray(10_000_000);
    }
    @Benchmark
    public void benchmarkNaiveQuickSort() {
        new Quicksort.NaivePivot<>(array).sort();
    }

    @Benchmark
    public void benchmarkRandomQuickSort() {
        new Quicksort.RandomPivot<>(array).sort();
    }

    @Benchmark
    public void benchmarkIterativeQuickSort() {
        new Quicksort.RandomIterative<>(array).sort();
    }

    @Benchmark
    public void benchmarkNativeSort() {
        Collections.sort(Arrays.asList(array));
    }
}
