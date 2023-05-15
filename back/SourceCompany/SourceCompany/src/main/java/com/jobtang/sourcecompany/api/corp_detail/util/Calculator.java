package com.jobtang.sourcecompany.api.corp_detail.util;

import org.springframework.stereotype.Component;

@Component
public class Calculator {
    // a/b
    public Double myDivision(Number a, Number b) {
        if (a == null || b == null) {return null;}
        Double x = Double.valueOf(a.intValue());
        Double y = Double.valueOf(b.intValue());
        return x / y;
    }

    // a/b * 100
    public Double myRatio(Number a, Number b) {
        if (a == null || b == null) {return null;}
        Double x = Double.valueOf(a.longValue());
        Double y = Double.valueOf(b.longValue());
        return x / y * 100;
    }

    // a + b
    public Double myPlus(Number a, Number b) {
        if (a == null || b == null) {return null;}
        Double x = Double.valueOf(a.longValue());
        Double y = Double.valueOf(b.longValue());
        return x + y;
    }

    // a - b
    public Long mySubtraction(Long a, Long b) {;
        if (a == null || b == null) {return null;}
        return a - b;
    }

    // a/(b+c)
    public Double myRatioWithSum(Number a, Number b, Number c) {
        if (a == null || b == null || c == null) {return null;}
        Double x = Double.valueOf(a.longValue());
        Double y = Double.valueOf(b.longValue());
        Double z = Double.valueOf(c.longValue());
        return x / (y + z) * 100;
    }

    // (a-b)/c
    public Double myRatioWithSubtraction(Number a, Number b, Number c) {
        if (a == null || b == null || c == null) {return null;}
        Double x = Double.valueOf(a.longValue());
        Double y = Double.valueOf(b.longValue());
        Double z = Double.valueOf(c.longValue());
        return (x - y) / z * 100;
    }

    // a * b
    public Double myMultiply(Number a, Number b) {
        if (a == null || b == null) {return null;}
        Double x = Double.valueOf(a.longValue());
        Double y = Double.valueOf(b.longValue());
        return x * y;
    }

    // a*(b+c)
    public Double myMultiplyWithSum(Number a, Number b, Number c) {
        if (a == null || b == null || c == null) {return null;}
        Double x = Double.valueOf(a.longValue());
        Double y = Double.valueOf(b.longValue());
        Double z = Double.valueOf(c.longValue());
        return x * (y + z);

    }
    // (a-b)/c * d3
    public Double myCalculate(Number a, Number b, Number c, Number d) {
        if (a == null || b == null || c == null || d == null) { return null;}
        Double x = Double.valueOf(a.longValue());
        Double y = Double.valueOf(b.longValue());
        Double z = Double.valueOf(c.longValue());
        Double w = Double.valueOf(d.longValue());
        return (x - y) / z * w;
    }
}
