package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa el cálculo de la Serie números Primos hasta Limite N
 * S = 1 + 2 + 3 + 5 + 7 + 11 + ... + N
 * @author Julian Merino, Roy Gordillo, Pilar Naranjo, Axel Jimenez, Elian Jimenez
 */

public class PrimeNumberCalculatorUpToLimit implements Successionable, Printable {

    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;


    public PrimeNumberCalculatorUpToLimit(Integer limit) {
        this(1, limit);
    }

    public PrimeNumberCalculatorUpToLimit(Integer start, Integer limit) {
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
        this.limit = validateLimit(limit, "Upper limit");
    }


    @Override
    public String print() {
        return this.printableTerms.toString();
    }

    @Override
    public Number calculate() {
        long result = 0;
        while (currentTerm <= limit) {
            this.printableTerms.append(String.valueOf(currentTerm)).append(" + ");
            result = result + currentTerm;//result += currentTerm;
            currentTerm = this.nextTerm(currentTerm).intValue();
        }
        return result;
    }

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
}
















