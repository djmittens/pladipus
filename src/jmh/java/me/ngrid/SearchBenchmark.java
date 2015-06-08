package me.ngrid;

import me.ngrid.searching.BinarySearch;
import me.ngrid.searching.Searchable;
import me.ngrid.searching.SequentialSearch;
import me.ngrid.util.Array;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(1)
@Warmup(iterations = 1)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
public class SearchBenchmark {
    private static final Random r = new Random();

    private Searchable<Integer> sortedSequential;
    private Searchable<Integer> randomSequential;
    private Searchable<Integer> sortedBinary;
    private Integer item;
    private Integer[] sorted;

    @Setup(Level.Invocation)
    public void initializeSorts() {
        Integer[] random = Array.getIntegerArray(10_000);
        sorted = Arrays.copyOf(random, 10_000);
        Arrays.sort(sorted);
        item = random[r.nextInt(10_000)];

        sortedSequential = SequentialSearch.getInstance(sorted);
        randomSequential = SequentialSearch.getInstance(random);
        sortedBinary = BinarySearch.getInstance(sorted);
    }

    @Benchmark
    public void sequentialSearchSorted() {
        sortedSequential.find(item);
    }

    @Benchmark
    public void sequentialSearchRandom() {
        randomSequential.find(item);
    }

    @Benchmark
    public void binarySearchSorted() {
        sortedBinary.find(item);
    }

    @Benchmark
    public void nativeArrayBinarySearch() {
        Arrays.binarySearch(sorted, item);
    }
}
