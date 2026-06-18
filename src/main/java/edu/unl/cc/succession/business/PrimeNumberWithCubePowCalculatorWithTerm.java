package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa el cálculo de la Serie de primos elevados al cubo hasta N términos
 * S = 1^3 + 3^3 + 5^3 + 7^3 + 11^3 + 13^3 ...
 * @author Axel Jiménez, Roy Gordillo, Pilar Naranjo, Julian Merino, Elian Jiménez
 */
public class PrimeNumberWithCubePowCalculatorWithTerm implements Successionable, Printable {

    private Integer nTerm;  // Controla el limite por cantidad de términos ejecutados
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeNumberWithCubePowCalculatorWithTerm(Integer limit) {
        this(1, limit);
    }

    public PrimeNumberWithCubePowCalculatorWithTerm(Integer start, Integer limit) {
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
        this.nTerm = validateLimit(limit, "nTerm");
    }

    @Override
    public Number calculate() {
        double result = 0;
        int countTerm = 0; // Contador para rastrear cuantos terminos se han sumado

        // Detiene el ciclo estrictamente al alcanzar la cantidad de términos (nTerm) solicitada
        while (countTerm < nTerm) {
            // Construye la cadena de texto visualizando la base elevada al cubo
            this.printableTerms.append(currentTerm).append("^3 + ");
            // Calcula el cubo de la base actual y lo acumula en el resultado total
            result = result + Math.pow(currentTerm, 3);

            // Avanza al siguiente número primo
            currentTerm = nextTerm(currentTerm).intValue();
            // Registra que un termino completo ha sido procesado
            countTerm++;
        }
        return result;
    }

    @Override
    public Number nextTerm(Number current) {
        current = current.intValue() + 1;
        boolean isPrime = false;
        // Evalua de forma secuencial hasta encontrar el próximo número primo
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
        // Comprueba si el número es divisible por algun otro que no sea 1 o si mismo
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