package me.ngrid;

import me.ngrid.misc.Palindrome;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(1)
@Warmup(iterations = 1)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
public class PalindromeBenchmark {
    private String str;

    @Setup(Level.Invocation)
    public void setupPalindromes() {
        str = Palindrome.getPalindromic(10_000);
    }

    @Benchmark
    public void arrayBasedPalindromic() {
        Palindrome.isPalindromicArray(str);
    }

    @Benchmark
    public void hashBasePalindromic() {
        Palindrome.isPalindromicHash(str);
    }
}
