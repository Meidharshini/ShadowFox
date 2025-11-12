import java.util.InputMismatchException;
import java.util.Scanner;

public class CalculatorApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Enhanced Calculator ===");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Square Root");
            System.out.println("6. Power");
            System.out.println("7. Temperature Conversion (C ↔ F)");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = getIntInput(sc); 

            switch(choice) {
                case 1: System.out.println("Result: " + addition(sc)); break;
                case 2: System.out.println("Result: " + subtraction(sc)); break;
                case 3: System.out.println("Result: " + multiplication(sc)); break;
                case 4: System.out.println("Result: " + division(sc)); break;
                case 5: System.out.println("Result: " + squareRoot(sc)); break;
                case 6: System.out.println("Result: " + power(sc)); break;
                case 7: temperatureConversion(sc); break;
                case 8: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice! Please select 1-8.");
            }

        } while(choice != 8);

        sc.close();
    }

    
    public static int getIntInput(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Please enter a number: ");
                sc.next(); 
            }
        }
    }

   
    public static double getDoubleInput(Scanner sc) {
        while (true) {
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Please enter a valid number: ");
                sc.next();
            }
        }
    }

 
    public static double addition(Scanner sc) {
        System.out.print("Enter first number: ");
        double a = getDoubleInput(sc);
        System.out.print("Enter second number: ");
        double b = getDoubleInput(sc);
        return a + b;
    }

    public static double subtraction(Scanner sc) {
        System.out.print("Enter first number: ");
        double a = getDoubleInput(sc);
        System.out.print("Enter second number: ");
        double b = getDoubleInput(sc);
        return a - b;
    }

    public static double multiplication(Scanner sc) {
        System.out.print("Enter first number: ");
        double a = getDoubleInput(sc);
        System.out.print("Enter second number: ");
        double b = getDoubleInput(sc);
        return a * b;
    }

    public static double division(Scanner sc) {
        System.out.print("Enter numerator: ");
        double a = getDoubleInput(sc);
        double b;
        while (true) {
            System.out.print("Enter denominator: ");
            b = getDoubleInput(sc);
            if(b == 0) {
                System.out.println("Error: Cannot divide by zero!");
            } else break;
        }
        return a / b;
    }

    public static double squareRoot(Scanner sc) {
        double a;
        while (true) {
            System.out.print("Enter number: ");
            a = getDoubleInput(sc);
            if(a < 0) System.out.println("Error: Cannot take square root of negative number!");
            else break;
        }
        return Math.sqrt(a);
    }

    public static double power(Scanner sc) {
        System.out.print("Enter base: ");
        double base = getDoubleInput(sc);
        System.out.print("Enter exponent: ");
        double exp = getDoubleInput(sc);
        return Math.pow(base, exp);
    }

    public static void temperatureConversion(Scanner sc) {
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        System.out.print("Enter choice: ");
        int option = getIntInput(sc);
        System.out.print("Enter temperature: ");
        double temp = getDoubleInput(sc);

        if(option == 1) System.out.println("Result: " + (temp * 9/5 + 32) + " °F");
        else if(option == 2) System.out.println("Result: " + ((temp - 32) * 5/9) + " °C");
        else System.out.println("Invalid choice!");
    }
}
