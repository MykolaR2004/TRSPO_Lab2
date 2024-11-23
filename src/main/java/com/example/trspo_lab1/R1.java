package com.example.trspo_lab1;

public class R1 implements Runnable {
    private final double a;
    private final double h;
    private final double start;
    private final double end;
    private final Function f;
    private final IntegralCalculator integralCalculator;

    public R1(IntegralCalculator integralCalculator, double a, double h, double start, double end, Function f) {
        this.integralCalculator = integralCalculator;
        this.a = a;
        this.h = h;
        this.start = start;
        this.end = end;
        this.f = f;
    }

    @Override
    public void run() {
        var taskSum = 0.0;
        for (double i = start; i < end; i++) {
            double xi = a+i*h;
            taskSum += f.calculate(xi);
        }
        integralCalculator.sendResult(taskSum);
    }
}
