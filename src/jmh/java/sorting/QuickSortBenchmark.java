package sorting;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.ThreadParams;
import org.openjdk.jmh.results.BenchmarkTaskResult;
import org.openjdk.jmh.runner.InfraControl;
import util.Array;

import java.util.concurrent.TimeUnit;

/**
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 3)
//@State(Scope.Thread)
public class QuickSortBenchmark {


////    @Setup(Level.Invocation)
//    public void setup(){
////        this.array = Array.getArray(1_000_000);
//    }
    @Benchmark
    public void benchmarkNaiveQuickSort() {
        new Quicksort.NaivePivot<>(Array.getArray(1_000_000)).sort();
    }
}
