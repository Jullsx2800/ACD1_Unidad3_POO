package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa la serie de numeros pares hasta un limite
 * S = 2 + 4 + 6 + 8 + ... N =
 * @author wduck
 */
public class EvenNumberCalculatorUpToLimit implements Successionable, Printable {

    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;


    public EvenNumberCalculatorUpToLimit(Integer limit) {
        setLimit(limit);
        currentTerm = 0;
        printableTerms = new StringBuilder("S = ");
    }

    @Override
    public void setLimit(Number limit) {
        if  (limit == null) {
            throw new IllegalArgumentException("limit cannot be null");
        }
        if  (limit instanceof Integer) {
            if (limit.intValue() < 0) {
                throw new IllegalArgumentException("limit cannot be negative");
            }
            this.limit = limit.intValue();
        } else {
            throw new IllegalArgumentException("limit must be an integer");
        }
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
