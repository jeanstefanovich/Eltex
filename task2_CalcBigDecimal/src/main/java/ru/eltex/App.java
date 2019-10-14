package ru.eltex;

import java.io.*;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

/* //old version
        System.out.println("Введите число: ");
        String input = bufferedReader.readLine();

        Integer X = Integer.parseInt(input);

        System.out.println("Введите операцию(+, -, *, /: ");
        String operator = bufferedReader.readLine();

        System.out.println("Введите число: ");
        input = bufferedReader.readLine();

        Integer Y = Integer.parseInt(input);
        System.out.println("Результат: ");
        switch (operator) {
            case "+":
                System.out.println(X + Y);
                break;
            case "-":
                System.out.println(X - Y);
                break;
            case "/":
                if (Y == 0) {
                    System.out.println("Ошибка деления на 0");
                    break;
                }
                System.out.println(new Double(X) / Y);
                break;
            case "*":
                System.out.println(X * Y);
                break;
        }

         */

        System.out.println("Введите число: ");
        String input = bufferedReader.readLine();
        try {
            Plus a = new Plus(input);
            System.out.println("Введите операцию(+, -, *, /, ^: ");
            String operator = bufferedReader.readLine();

            System.out.println("Введите второе число: ");
            input = bufferedReader.readLine();

            if (operator.equals("^")) {
                Integer Y = Integer.parseInt(input);
                System.out.println(a.makeOperation(Y, operator));
            }
            else {
                BigDecimal b = new BigDecimal(input);
                System.out.println(a.makeOperation(b, operator));
            }

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
