package edu.unl.cc;

import edu.unl.cc.succession.business.*;
import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

import java.util.Scanner;

public class Main {

    private static void printMenu(){
        System.out.println("\n--- CALCULADORA DE SERIES ---");
        System.out.println("1. Serie de numeros pares hasta un limite (S = 2 + 4 + 6 + 8 + ... N): ");
        System.out.println("2. Serie de primos elevados al cubo hasta un limite (S = 1^3 + 3^3 + 5^3 ... + N^3): ");
        System.out.println("3. Serie de primos elevados al cubo hasta N términos (S = 1^3 + 3^3 + 5^3 + 7^3 ...): ");
        System.out.println("4. Serie de primos elevados por pares hasta un limite (S = 1^2 + 3^4 + 5^6 + 7^8 + 11^10 ... + N): ");
        System.out.println("5. Serie de primos elevados a impares hasta n térmimos (S = 1^1 + 3^3 + 5^5 + 7^7 + 11^9 ..): ");
        System.out.println("6. Serie de primos elevados a la raiz de numeros pares hasta un limite (S = 1^(1/2) + 3^(1/4) + 5^(1/6) + ... + N): ");
        System.out.println("7. Serie de primos elevados a la raiz de numeros impares hasta un n términos (S = 1^(1/1) + 3^(1/3) ...): ");
        System.out.println("8. Serie de primos elevados a la raiz cúbica hasta un n términos (S = 1^(1/3) + 3^(1/3) + 5^(1/3) + 7^(1/3) + 11^(1/3) + 13^(1/3) = ");
        System.out.println("9. Serie de primos elevados a la raiz cuadrada hasta un limite (S = 1^(1/2) + 3^(1/2) + ... + N^(1/2)): ");
        System.out.println("10. Serie de primos hasta un limite (S = 1 + 2 + 3 + 5 + 7 + 11 + 13 + .. + N: ");
    }

    private static int readOption(Scanner scanner){
        printMenu();
        System.out.print("Elija la opción de la serie que desea calcular: ");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            int option = readOption(scanner);
            System.out.print("Debe ingresar un limite/N términos: ");
            int limit = scanner.nextInt();

            scanner.nextLine();

            Successionable serie = null;
            switch (option){
                case (1): {
                    serie = new EvenNumberCalculatorUpToLimit(limit);
                    break;
                }
                case (2): {
                    serie = new PrimeNumberWithCubePowCalculatorUpToLimit(limit);
                    break;
                }
                case (3): {
                    serie = new PrimeNumberWithCubePowCalculatorWithTerm(limit);
                    break;
                }
                case (4): {
                    serie = new PrimeNumberWithEvenPowCalculatorUpToLimit(limit);
                    break;
                }
                case (5): {
                    serie = new PrimeNumberWithOddPowCalculatorWithTerm(limit);
                    break;
                }
                case (6): {
                    serie = new PrimeNumberWithEvenRootCalculatorUpToLimit(limit);
                    break;
                }
                case (7): {
                    serie = new PrimeNumberWithPowCalculatorWithTerm(limit);
                    break;
                }
                case (8): {
                    serie = new PrimeCubicRootCalculatorWithTerm(limit);
                    break;
                }
                case (9): {
                    serie = new PrimeNumberSqrtCalculatorUpToLimit(limit);
                    break;
                }
                case (10): {
                    serie = new PrimeNumberCalculatorUpToLimit(limit);
                    break;
                }
                default: {
                    System.out.println("Opción inválida. Por favor, intente de nuevo.\n");
                }
            }

            if (serie != null){
                Number result = serie.calculate();
                String impresion = ((Printable)serie).print();

                if (impresion.endsWith(" + ")) {
                    impresion = impresion.substring(0, impresion.length() - 3);
                } else if (impresion.endsWith(" +")) {
                    impresion = impresion.substring(0, impresion.length() - 2);
                }

                System.out.println("\n" + impresion);
                System.out.println("S = " + result + "\n");
            }

            System.out.print("¿Desea calcular otra serie? (S/N): ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("N")) {
                continueRunning = false; // Rompe el ciclo while
                System.out.println("Finalizando la calculadora. ¡Hasta pronto!");
            }
        }

        scanner.close();
    }
}