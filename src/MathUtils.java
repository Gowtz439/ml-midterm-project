package com.example.utils;

import java.util.List;

public class MathUtils {

    /**
     * Calculates the arithmetic mean of a list of numbers with defensive checks.
     *
     * @param values List of double values.
     * @return Calculated mean.
     * @throws IllegalArgumentException if the list is null or empty.
     */
    public static double calculateMean(List<Double> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Input list cannot be null or empty.");
        }

        double sum = 0.0;
        int validCount = 0;

        for (Double value : values) {
            if (value != null && !Double.isNaN(value) && !Double.isInfinite(value)) {
                sum += value;
                validCount++;
            }
        }

        if (validCount == 0) {
            return 0.0;
        }

        return sum / validCount;
    }

    /**
     * Checks if a string contains valid integer digits.
     */
    public static boolean isInteger(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }

        try {
            Integer.parseInt(str.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
