package edu.railsync;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/** Randomized and parallel-style demonstrations for continuous railway event data. */
public final class RandomizedParallelAlgorithms {
    private RandomizedParallelAlgorithms() {}

    public static void randomizedQuickSort(int[] a) { quickSort(a, 0, a.length - 1); }
    private static void quickSort(int[] a, int low, int high) {
        if (low >= high) return;
        int pivotIndex = ThreadLocalRandom.current().nextInt(low, high + 1);
        swap(a, pivotIndex, high);
        int p = partition(a, low, high);
        quickSort(a, low, p - 1); quickSort(a, p + 1, high);
    }
    private static int partition(int[] a, int low, int high) {
        int pivot = a[high], i = low;
        for (int j = low; j < high; j++) if (a[j] <= pivot) swap(a, i++, j);
        swap(a, i, high); return i;
    }
    private static void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }

    public static <T> List<T> reservoirSample(Iterable<T> stream, int k, Random random) {
        if (k < 0) throw new IllegalArgumentException("Sample size cannot be negative.");
        List<T> sample = new ArrayList<>(k); int count = 0;
        for (T item : stream) {
            count++;
            if (sample.size() < k) sample.add(item);
            else {
                int j = random.nextInt(count);
                if (j < k) sample.set(j, item);
            }
        }
        return sample;
    }

    /** Miller-Rabin primality check suitable for positive long values in this demo. */
    public static boolean isProbablyPrime(long n) {
        if (n < 2) return false;
        for (int p : new int[]{2,3,5,7,11,13,17,19,23,29,31,37}) {
            if (n % p == 0) return n == p;
        }
        long d = n - 1; int s = 0;
        while ((d & 1) == 0) { d >>= 1; s++; }
        long[] bases = {2, 3, 5, 7, 11};
        for (long a : bases) {
            if (a >= n) continue;
            long x = modPow(a, d, n);
            if (x == 1 || x == n - 1) continue;
            boolean witness = true;
            for (int r = 1; r < s; r++) {
                x = mulMod(x, x, n);
                if (x == n - 1) { witness = false; break; }
            }
            if (witness) return false;
        }
        return true;
    }
    private static long modPow(long a, long e, long mod) {
        long result = 1;
        while (e > 0) { if ((e & 1) != 0) result = mulMod(result, a, mod); a = mulMod(a, a, mod); e >>= 1; }
        return result;
    }
    private static long mulMod(long a, long b, long mod) {
        // BigInteger keeps multiplication correct for long values without overflow.
        return java.math.BigInteger.valueOf(a).multiply(java.math.BigInteger.valueOf(b))
            .mod(java.math.BigInteger.valueOf(mod)).longValue();
    }

    public static int[] inclusivePrefixScan(int[] input) {
        int[] result = new int[input.length]; int sum = 0;
        for (int i = 0; i < input.length; i++) result[i] = (sum += input[i]);
        return result;
    }
    public static long parallelReduceSum(int[] input) {
        return Arrays.stream(input).parallel().asLongStream().sum();
    }

    public static void demo() {
        int[] delays = {12, 4, 19, 7, 3, 15, 8};
        randomizedQuickSort(delays);
        System.out.println("Sorted service-delay values: " + Arrays.toString(delays));
        List<String> events = List.of("Train arrived", "Platform changed", "Track check",
            "Train delayed", "Passenger announcement", "Maintenance complete");
        System.out.println("Reservoir sample (3 events): " + reservoirSample(events, 3, new Random(42)));
        System.out.println("Miller-Rabin: 104729 probably prime? " + isProbablyPrime(104729));
        int[] counts = {3, 4, 2, 6, 1};
        System.out.println("Prefix scan of event counts: " + Arrays.toString(inclusivePrefixScan(counts)));
        System.out.println("Parallel reduce — total event count: " + parallelReduceSum(counts));
    }
}
