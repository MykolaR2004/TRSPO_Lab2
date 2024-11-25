package com.example.trspo_lab2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntegralCalculatorTest {

    @Test
    void calculateTest1() {
        Function function = new Function();
        IntegralCalculator integralCalculator = new IntegralCalculator(1);

        double res = integralCalculator.calculate(1,4,3,function);
        Assertions.assertEquals(6.138196,res,1e-6);
    }

    @Test
    void functionTest1(){
        Function function = new Function();
        double res = function.calculate(1);
        Assertions.assertEquals(1.414213,res,1e-6);
    }

    @Test
    void functionTest2(){
        Function function = new Function();
        double res = function.calculate(4);
        Assertions.assertEquals(1.767766,res,1e-6);
    }
}
