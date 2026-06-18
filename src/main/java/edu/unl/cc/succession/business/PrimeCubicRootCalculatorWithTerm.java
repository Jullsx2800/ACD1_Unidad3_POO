package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Serie de primos elevados a la raíz cúbica hasta N términos.
 * S = 1^(1/3) + 3^(1/3) + 5^(1/3) + 7^(1/3) + 11^(1/3) + 13^(1/3) + ...
 * @author Axel Jiménez, Roy Gordillo, Pilar Naranjo, Julian Merino, Elian Jiménez
 */
public class PrimeCubicRootCalculatorWithTerm implements Successionable, Printable {

    private Integer limitTerms;
    private Integer currentTerm;  
    private StringBuilder printableTerms;

    public PrimeCubicRootCalculatorWithTerm(Integer limitTerms) {
        this(1, limitTerms);
    }

    public PrimeCubicRootCalculatorWithTerm(Integer start, Integer limitTerms) {
        start = validateLimit(start, "Down limit");
        setLimit(limitTerms);
        // start-1 porque nextTerm siempre suma 1 antes de buscar el primo
        this.currentTerm = nextTerm(start - 1).intValue();
        this.printableTerms = new StringBuilder("S = ");
    }

    // valida que el valor no sea nulo, negativo o de tipo incorrecto
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
        this.limitTerms = validateLimit(limit, "N terms limit");
    }

    @Override
    public Number calculate() {
        double result = 0;
        int termCount = 0;

        // el exponente es siempre 1/3, por eso ambos son final
        final int numeratorExponent = 1;
        final int denominatorExponent = 3;

        while (termCount < limitTerms) {
            this.printableTerms.append(currentTerm)
                    .append("^(")
                    .append(numeratorExponent)
                    .append("/")
                    .append(denominatorExponent)
                    .append(") + ");

            result = result + Math.pow(currentTerm, ((double) numeratorExponent) / denominatorExponent);

            currentTerm = nextTerm(currentTerm).intValue();
            termCount++;
        }
        return result;
    }

    // busca el siguiente primo a partir de current+1
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

    // el 1 se acepta para respetar el primer término de la serie (1^(1/3))
    // el 2 se excluye explícitamente para que la secuencia sea 1, 3, 5, 7, 11...
    private boolean isPrime(Integer number) {
        if (number < 1 || number == 2) {
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