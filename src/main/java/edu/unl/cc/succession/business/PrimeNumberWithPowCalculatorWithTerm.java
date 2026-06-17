package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa el cálculo de la Serie números Primos elevado a la potencia inversa del número primo hasta N términos
 * (S = 1^(1/1) + 3^(1/3) + 5^(1/5) + 7^(1/7) + 11^(1/9) + 13^(1/11)):
 * @author wduck (Wilman Chamba Z.)
 */

public class PrimeNumberWithPowCalculatorWithTerm implements Successionable, Printable {

    private Integer nTerm;  //limit
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeNumberWithPowCalculatorWithTerm(Integer limit) {
        this(1, limit);
    }

    public PrimeNumberWithPowCalculatorWithTerm(Integer start, Integer limit) {
        start = validateLimit(start, "Downn limit");
        setLimit(limit);
        this.currentTerm = nextTerm(start-1).intValue();
        printableTerms = new StringBuilder("S = ");
    }


    private Integer validateLimit(Number value, String label) {
        if  (value == null) {
            throw new IllegalArgumentException(label + " cannot be null");
        }
        if  (value instanceof Integer) {
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
        this.nTerm = validateLimit(limit, "nTerm");
    }

    @Override
    public Number calculate() {
        double result = 0;
        int countTerm = 0;
        final int numeratorExponent =  1;
        //int denominatorExponent = currentTerm;
        int denominatorExponent = 1;
        while (countTerm < nTerm) {

            this.printableTerms.append(currentTerm).append("^(")
                    .append(numeratorExponent).append("/").append(denominatorExponent)
                    .append(") +");
            result = result + Math.pow(currentTerm, ((double)numeratorExponent)/denominatorExponent);
            currentTerm = nextTerm(currentTerm).intValue();
            //denominatorExponent =  currentTerm;
            denominatorExponent += 2;
            countTerm++;
        }
        return result;
    }

    /**
     * Representar el término solo de la base de la serir
     * @param current
     * @return
     */
    @Override
    public Number nextTerm(Number current) {
        current = current.intValue() + 1;
        boolean isPrime = false;
        while (!isPrime){
            isPrime = isPrime(current.intValue());
            if (!isPrime){
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


