package com.devpilot.example;

import java.util.Optional;

/**
 * Clean, modular utility service for mathematical calculations.
 */
public class CleanMathService {

    /**
     * Computes the arithmetic mean of an array of numbers.
     */
    public double calculateAverage(double[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0.0;
        }

        double sum = 0.0;
        for (double num : numbers) {
            sum += num;
        }
        return sum / numbers.length;
    }

    /**
     * Performs division with explicit zero-denominator protection.
     */
    public Optional<Double> safeDivide(double numerator, double denominator) {
        if (denominator == 0.0) {
            return Optional.empty();
        }
        return Optional.of(numerator / denominator);
    }

    /**
     * Clamps a value between a lower and upper bound.
     */
    public double clamp(double value, double min, double max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }
}
