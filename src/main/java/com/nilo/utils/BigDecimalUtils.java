package com.nilo.utils;

import java.math.BigDecimal;

/**
 * @author: Stony  Date: 2016/4/5 Time: 13:28
 */
public abstract class BigDecimalUtils {

    private static final int DEFAULT_DIV_SCALE = 10;

    /**
     * #####################   divide                   #############################################
     **/
    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        if (b.floatValue() == 0F) {
            return BigDecimal.ZERO;
        }
        return a.divide(b, DEFAULT_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal divide(BigDecimal a, Integer b) {
        return divide(a, new BigDecimal(String.valueOf(b)));
    }

    public static BigDecimal divide(Integer a, Integer b) {
        return divide(String.valueOf(a), String.valueOf(b));
    }

    public static BigDecimal divide(double a, double b) {
        return divide(String.valueOf(a), String.valueOf(b));
    }

    public static BigDecimal divide(String a, String b) {
        return divide(new BigDecimal(a), new BigDecimal(b));
    }

    /**
     * #####################   multiply                   #############################################
     **/
    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    public static BigDecimal multiply(String a, String b) {
        return multiply(new BigDecimal(a), new BigDecimal(b));
    }

    public static BigDecimal multiply(double a, String b) {
        return multiply(String.valueOf(a), b);
    }
    public static BigDecimal multiply(double a, double b) {
        return multiply(a, String.valueOf(b));
    }
    public static BigDecimal multiply(float a, float b) {
        return multiply(String.valueOf(a), String.valueOf(b));
    }

    /**
     * ####################      subtract               ##############################################
     **/
    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    public static BigDecimal subtract(String a, String b) {
        return subtract(new BigDecimal(a), new BigDecimal(b));
    }
    public static BigDecimal subtract(String a, BigDecimal b) {
        return subtract(new BigDecimal(a), b);
    }

    public static BigDecimal subtract(double a, double b) {
        return subtract(String.valueOf(a), String.valueOf(b));
    }
    private static BigDecimal subtract(BigDecimal a, String b){
        return subtract(a, new BigDecimal(b));
    }

    public static BigDecimal subtract(BigDecimal a, double b) {
        return subtract(a, String.valueOf(b));
    }
    public static BigDecimal subtract(BigDecimal a, int b) {
        return subtract(a, String.valueOf(b));
    }
    public static BigDecimal subtract(long a, BigDecimal b) {
        return subtract(String.valueOf(a),b);
    }
    public static BigDecimal subtract(double a, BigDecimal b) {
        return subtract(String.valueOf(a),b);
    }


    /**
     * ####################      add               ##############################################
     **/
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    public static BigDecimal add(String a, String b) {
        return add(new BigDecimal(a), new BigDecimal(b));
    }

    public static BigDecimal add(double x, double y) {
        BigDecimal xd = new BigDecimal(String.valueOf(x));
        BigDecimal yd = new BigDecimal(String.valueOf(y));
        return add(xd, yd);
    }

    public static BigDecimal add(BigDecimal xd, double y) {
        BigDecimal yd = new BigDecimal(Double.toString(y));
        return add(xd, yd);
    }

    public static BigDecimal add(double x, BigDecimal yd) {
        BigDecimal xd = new BigDecimal(Double.toString(x));
        return add(xd, yd);
    }

    /**
     * @param args
     * @return
     */
    public static BigDecimal add(double... args) {
        if (args.length == 1) {
            return new BigDecimal(Double.toString(args[0]));
        } else if (args.length >= 2) {
            BigDecimal xd = new BigDecimal(Double.toString(args[0]));
            BigDecimal yd = new BigDecimal(Double.toString(args[1]));
            BigDecimal result = add(xd, yd);
            for (int i = 2; i < args.length; i++) {
                result = result.add(new BigDecimal(Double.toString(args[i])));
            }
            return result;
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal add(String... args) {
        if (args.length == 1) {
            return new BigDecimal((args[0]));
        } else if (args.length >= 2) {
            BigDecimal xd = new BigDecimal((args[0]));
            BigDecimal yd = new BigDecimal((args[1]));
            BigDecimal result = add(xd, yd);
            for (int i = 2; i < args.length; i++) {
                result = result.add(new BigDecimal((args[i])));
            }
            return result;
        }
        return BigDecimal.ZERO;
    }



}
