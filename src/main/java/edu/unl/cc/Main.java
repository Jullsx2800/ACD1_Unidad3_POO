package edu.unl.cc;

import edu.unl.cc.succession.business.EvenNumberCalculatorUpToLimit;
import edu.unl.cc.succession.business.PrimeNumberCalculatorUpToLimit;
import edu.unl.cc.succession.business.PrimeNumberWithPowCalculatorWithTerm;
import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static void printMenu(){
        System.out.println("CALCULADORA DE SERIES");
        System.out.println("1. Serie de numeros pares hasta un limite (S = 2 + 4 + 6 + 8 + ... N): ");
        System.out.println("2. Serie de primos elevados al cubo  hasta un limite (S = 1^3 + 3^3 + 5^3 + 7^3 + 11^3 + 13^3 ... + N^3): ");
        System.out.println("7. Serie de primos elevados a la raiz de numeros impares hasta un n términos (S = 1^(1/1) + 3^(1/3) + 5^(1/5) + 7^(1/7) + 11^(1/9) + 13^(1/11)): ");
        System.out.println("10. Serie de primos hasta un limite (S = 1 + 2 + 3 + 5 + 7 + 11 + 13 + .. + N: ");
    }

    private static int readOption(Scanner scanner){
        int option;
        printMenu();
        System.out.print("Elija la opción de la serie que desea calcular: ");
        option = scanner.nextInt();
        return option;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
            case (7): {
                serie = new PrimeNumberWithPowCalculatorWithTerm(limit);
                break;
            }
            case (10): {
                serie = new PrimeNumberCalculatorUpToLimit(limit);
                break;
            }
            default: {
                   System.out.println("Opción inválida");
            }

        }
        if (serie != null){
            Number result = serie.calculate();
            System.out.println(((Printable)serie).print());
            System.out.println("S = " + result + "\n");
        }
    }
}