package kaylin.utils;

import kaylin.dto.IDuplicate;

import java.util.Scanner;

public class InputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static int getInteger(String prompt) {
        int number;

        while (true) {
            try {
                System.out.print(prompt);
                number = Integer.parseInt(scanner.nextLine());
                return number;
            } catch (Exception e) {
                System.out.println("Not an integer!");
            }
        }
    }

    public static double getDouble(String prompt) {
        double number;

        while (true) {
            try {
                System.out.print(prompt);
                number = Double.parseDouble(scanner.nextLine());
                return number;
            } catch (Exception e) {
                System.out.println("Not a double!");
            }
        }
    }


    public static int getIntegerInRange(String prompt, int min, int max) {
        int number;

        while (true) {
            try {
                System.out.print(prompt);
                number = Integer.parseInt(scanner.nextLine());
                if (number >= min && number <= max)
                    return number;
            } catch (Exception e) {
                System.out.println("Not an integer!");
            }
        }
    }

    public static int getIntegerWithMin(int min, String prompt) {
        int number;

        while (true) {
            try {
                System.out.print(prompt);
                number = Integer.parseInt(scanner.nextLine());
                if (number >= min)
                    return number;
            } catch (Exception e) {
                System.out.println("Not an integer!");
            }
        }
    }

    public static double getDoubleWithMin(double min, String prompt) {
        double number;

        while (true) {
            try {
                System.out.print(prompt);
                number = Double.parseDouble(scanner.nextLine());
                if (number > min)
                    return number;
            } catch (Exception e) {
                System.out.println("Not a double!");
            }
        }
    }

    public static String inputNonBlankStr(String prompt) {
        String data;

        do {
            System.out.print(prompt);
            data = scanner.nextLine().trim();
        } while (data.length() == 0);

        return data;
    }

    public static String inputPattern(String prompt, String pattern) {
        String data;

        do {
            System.out.print(prompt);
            data = scanner.nextLine().trim();
        } while (!data.matches(pattern));

        return data;
    }

    public static String noDuplicate(IDuplicate isDuplicate, String prompt, String warning) {
        String res;
        do {
            res = inputNonBlankStr(prompt);
            if (isDuplicate.isDuplicate(res)) {
                System.out.println(warning);
            }
        } while (isDuplicate.isDuplicate(res));
        return res;
    }

    public static String noDuplicateAndPattern(IDuplicate isDuplicate, String prompt, String warning, String pattern) {
        String res;
        do {
            res = inputNonBlankStr(prompt);
            if (isDuplicate.isDuplicate(res)) {
                System.out.println(warning);
            }
            if (!pattern.trim().isEmpty() && !res.toUpperCase().matches(pattern.toUpperCase()))
                System.out.println("Incorrect format.");
        } while (isDuplicate.isDuplicate(res) || !res.toUpperCase().matches(pattern.toUpperCase()));
        return res;
    }

}
