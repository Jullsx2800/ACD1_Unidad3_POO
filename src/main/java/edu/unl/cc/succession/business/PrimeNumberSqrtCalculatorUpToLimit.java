package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa el cálculo de la Serie de primos elevados a la raíz cuadrada hasta un límite N
 * S = 1^(1/2) + 3^(1/2) + 5^(1/2) + 7^(1/2) + 11^(1/2) + 13^(1/2) + .. + N^(1/2)
 * @authors Julian Merino, Roy Gordillo, Pilar Naranjo, Axel Jimenez, Elian Jimenez
 */
public class PrimeNumberSqrtCalculatorUpToLimit implements Successionable, Printable {

    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeNumberSqrtCalculatorUpToLimit(Integer limit) {
        setLimit(limit);
        this.currentTerm = 1;
        this.printableTerms = new StringBuilder("S = ");
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
        double totalSum = 0.0;
        while (currentTerm <= limit) {
            this.printableTerms.append(currentTerm).append("^(1/2) + ");

            // Calculamos la raíz cuadrada (elevado a la 1/2)
            totalSum += Math.sqrt(currentTerm);

            // Obtenemos el siguiente término de la serie
            currentTerm = this.nextTerm(currentTerm).intValue();
        }
        return totalSum;
    }

    @Override
    public Number nextTerm(Number current) {
        int next = current.intValue();
        if (next == 1) {
            return 3;
        }

        next += 2;
        while (!isPrime(next)) {
            next += 2;
        }
        return next;
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
        String result = this.printableTerms.toString();
        if (result.endsWith(" + ")) {
            result = result.substring(0, result.length() - 3);
        }
        return result;
    }
}