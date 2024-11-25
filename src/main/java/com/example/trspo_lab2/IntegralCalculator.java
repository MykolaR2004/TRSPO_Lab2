package com.example.trspo_lab2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class IntegralCalculator {

    private final int numThreads;
    private double total;
    private final AtomicInteger finished = new AtomicInteger();

    public IntegralCalculator(int numThreads) {
        this.numThreads = numThreads;
    }

    public double calculate(double a, double b, long n, Function f) {
        double h = (b - a) / n;
        double sum = (f.calculate(a) + f.calculate(b)) / 2;

        long step = n / numThreads;

        CompletableFuture<?>[] futures = new CompletableFuture[numThreads];
        for (int i = 0; i < numThreads; i++) {
            long start = i * step;
            long end;
            if (i == numThreads - 1) end = n;
            else end = start + step;
            futures[i] = CompletableFuture.runAsync(() -> {
                double taskSum = 0.0;
                for (long j = start; j < end; j++) {
                    double xi = a + j * h;
                    taskSum += f.calculate(xi);
                }
                synchronized (this) {
                    total += taskSum;
                    finished.incrementAndGet();
                }
            });
        }

        CompletableFuture.allOf(futures).join();
        return h * (sum + total);
    }

    public synchronized void sendResult(double res) {
        total += res;
        if (finished.incrementAndGet() == numThreads) {
            this.notifyAll();
        }
    }
}

