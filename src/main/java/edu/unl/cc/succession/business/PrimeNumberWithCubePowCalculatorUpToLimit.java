package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa el cálculo de la Serie de primos elevados al cubo hasta un límite N
 * S = 1^3 + 3^3 + 5^3 + 7^3 + 11^3 + 13^3 ... + N^3
 * @author Axel Jiménez, Roy Gordillo, Pilar Naranjo, Julian Merino, Elian Jiménez
 */
public class PrimeNumberWithCubePowCalculatorUpToLimit implements Successionable, Printable {

    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeNumberWithCubePowCalculatorUpToLimit(Integer limit) {
        this(1, limit);
    }

    public PrimeNumberWithCubePowCalculatorUpToLimit(Integer start, Integer limit) {
        start = validateLimit(start, "Down limit");
        setLimit(limit);
        // Inicializa la base buscando el primer primo desde el punto de partida
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

        // Itera mientras el numero primo base no supere el limite de valor establecido
        while (this.currentTerm <= this.limit) {
            // Construye la cadena de texto visualizando la base elevada al cubo
            this.printableTerms.append(this.currentTerm).append("^3 + ");
            // Calcula el cubo de la base actual y lo acumula en el resultado total
            result = result + Math.pow(this.currentTerm, 3);

            // Avanza al siguiente número primo para la siguiente iteración
            this.currentTerm = this.nextTerm(this.currentTerm).intValue();
        }
        return result;
    }

    @Override
    public Number nextTerm(Number current) {
        current = current.intValue() + 1;
        boolean isPrime = false;
        // Evalua de forma secuencial hasta encontrar el proximo numero primo
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
        // Comprueba si el número es divisible por algún otro que no sea 1 o si mismo
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
