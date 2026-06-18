package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa el cálculo de la Serie de primos elevados a la raiz de numeros pares hasta un limite
 * S = 1^(1/2) + 3^(1/4) + 5^(1/6) + 7^(1/8) + 11^(1/10) + 13^(1/12) ... + N
 */
public class PrimeNumberWithEvenRootCalculatorUpToLimit implements Successionable, Printable {

    private Integer limit;
    private Integer currentTerm;
    private final StringBuilder printableTerms;

    public PrimeNumberWithEvenRootCalculatorUpToLimit(Integer limit) {
        this(1, limit);
    }

    public PrimeNumberWithEvenRootCalculatorUpToLimit(Integer start, Integer limit) {
        start = validateLimit(start, "Down limit");
        setLimit(limit);
        this.currentTerm = nextTerm(start - 1).intValue();
        printableTerms = new StringBuilder("S = ");
    }

    private Integer validateLimit(Number value, String label) {
        if (value == null) {
            throw new IllegalArgumentException(label + " cannot be null");
        }
        if (value instanceof Integer) {
            if (value.intValue() < 0) {
                throw new IllegalArgumentException(label + " cannot be negative");
            }
            return value.intValue();
        } else {
            throw new IllegalArgumentException(label + " must be an integer");
        }
    }

    @Override
    public void setLimit(Number limit) {
        this.limit = validateLimit(limit, "Upper limit");
    }

    @Override
    public Number calculate() {
        double result = 0;
        final int numeratorExponent = 1;
        int denominatorExponent = 2; // Las raíces son pares, por lo que el denominador inicia en 2

        // La serie es "hasta un límite", por lo que evaluamos currentTerm <= limit
        while (currentTerm <= limit) {
            this.printableTerms.append(currentTerm).append("^(")
                    .append(numeratorExponent).append("/").append(denominatorExponent)
                    .append(") + ");

            // Se calcula el término actual y se suma al resultado
            result = result + Math.pow(currentTerm, ((double) numeratorExponent) / denominatorExponent);

            // Se avanza al siguiente primo y se incrementa el denominador en 2 (para mantenerlo par)
            currentTerm = nextTerm(currentTerm).intValue();
            denominatorExponent += 2;
        }
        return result;
    }

    @Override
    public Number nextTerm(Number current) {
        current = current.intValue() + 1;
        boolean isPrime = false;
        while (!isPrime) {
            isPrime = isPrime(current.intValue());
            if (!isPrime) {
                current = current.intValue() + 1;
            }
        }
        return current;
    }

    private boolean isPrime(Integer number) {
        if (number < 1) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String print() {
        return this.printableTerms.toString();
    }
}