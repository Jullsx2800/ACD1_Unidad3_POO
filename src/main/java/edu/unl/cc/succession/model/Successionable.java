package edu.unl.cc.succession.model;

/**
 * @author wduck (Wilman Chamba Z.)
 */
public interface Successionable {

    /**
     * Setter the limit to succession
     * @param limit
     */
    void setLimit(Number limit);

    /**
     * Calculate the result. The main method in the succession
     * @return Number of the result calculate
     */
    Number calculate();

    /**
     * Generate the next term in the succession from current term.
     * @param current
     * @return Number the next term
     */
    Number nextTerm(Number current);

}
