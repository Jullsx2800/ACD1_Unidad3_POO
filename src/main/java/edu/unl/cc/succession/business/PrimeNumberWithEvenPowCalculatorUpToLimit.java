package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa el cálculo de la Serie de primos elevados por pares hasta un límite N
 * S = 1^2 + 3^4 + 5^6 + 7^8 + 11^10 + 13^12 ... + N
 * @authors Julian Merino, Roy Gordillo, Pilar Naranjo, Axel Jimenez, Elian Jimenez
 */
public class PrimeNumberWithEvenPowCalculatorUpToLimit implements Successionable, Printable {

    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeNumberWithEvenPowCalculatorUpToLimit(Integer limit) {
        this(1, limit);
    }

    public PrimeNumberWithEvenPowCalculatorUpToLimit(Integer start, Integer limit) {
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
        int currentExponent = 2; // El exponente inicia con el primer numero par (2)

        // Itera mientras el número primo base no supere el límite establecido
        while (this.currentTerm <= this.limit) {
            // Construye la cadena de texto con el formato "base^exponente"
            this.printableTerms.append(this.currentTerm).append("^").append(currentExponent).append(" + ");
            // Calcula la potencia de la base actual y la acumula en el resultado
            result = result + Math.pow(this.currentTerm, currentExponent);

            // Incrementa el exponente de 2 en 2 y genera el siguiente número primo
            this.currentTerm = this.nextTerm(this.currentTerm).intValue();
            currentExponent += 2;
        }
        return result;
    }

    @Override
    public Number nextTerm(Number current) {
        current = current.intValue() + 1;
        boolean isPrime = false;
        // Evalua los siguientes numeros enteros hasta encontrar secuencialmente el proximo primo
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
        // Determina si el numero es primo comprobando si tiene divisores exactos menores a el
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