package com.study;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Choose option:");
            System.out.println("1 Start");
            System.out.println("2 Info");
            System.out.println("3 Exit");
            if (!scanner.hasNextInt()) {
                System.out.println("Please write correct number(1 - 3). Your input: " + scanner.next());
            }
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("Write your expression");
                String s = scanner.nextLine();
                String old = "";
                while (!(s = scanner.nextLine()).equals("EXIT")) {
                    if (!s.equals("")) {
                        s = old + s;
                        s = StringParser.parseConst(s);
                        s = StringParser.parseFactorial(s);
                        s = StringParser.parseArg(s);
                        old = Opn.createOpn(s).toString();
                        System.out.print(old);
                    }
                }
            } else if (choice == 2) {
                System.out.println("Trigonometry function(parameters must be radians value): ");
                System.out.println("sin(p) - return sine value");
                System.out.println("cos(p) - return cosine value");
                System.out.println("tan(p) - return tangent value");
                System.out.println("cot(p) - return cotangent value");
                System.out.println("asin(p),acos(p),atan(p),acot(p) - return arc sine,cosine,tangent,cotangent value in radians");
                System.out.println("PI - mathematical constant 3.14158...");
                System.out.println("n! - return factorial of a number(10!, 5!, etc)");
                System.out.println("pow(a,b) - returns the value of the first argument raised to the power of the second argument");
                System.out.println("sqrt(a) - returns the square root of a value");
                System.out.println("E - mathematical constant 2.71828...");
                System.out.println("ln(a) - return natural logarithm (base e)");
                System.out.println("log(a,b) - returns logarithm value of first argument with base of the second argument");
                System.out.println("Write 'EXIT' if you want to finish");
            } else if (choice == 3) {
                System.out.println("Program was successfully closed");
                break;

            }

        }
    }
}
