package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa la serie de numeros pares hasta un limite
 * S = 2 + 4 + 6 + 8 + ... N =
 * @author Julian Merino, Roy Gordillo, Pilar Naranjo, Axel Jimenez, Elian Jimenez
 */
public class EvenNumberCalculatorUpToLimit implements Successionable, Printable {

    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;


    public EvenNumberCalculatorUpToLimit(Integer limit) {
        this(0, limit);
    }

    public EvenNumberCalculatorUpToLimit(Integer start, Integer limit) {
        start = validateLimit(start, "Downn limit");
        setLimit(limit);
        this.currentTerm = start % 2 != 0 ? start + 1 : start;
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
    public Number calculate() {
        long result = 0;
        this.currentTerm = this.nextTerm(this.currentTerm).intValue();
        while (this.currentTerm <= this.limit) {
            this.printableTerms.append(this.currentTerm).append("  + ");
            result = result + this.currentTerm;
            this.currentTerm = this.nextTerm(this.currentTerm).intValue();
        }
        return result;
    }

    @Override
    public Number nextTerm(Number current) {
        return current.intValue() + 2;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }
}
