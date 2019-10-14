package ru.eltex;

import java.math.*;

public class Plus {
    private BigDecimal value;

    void setValue(BigDecimal value) {
        this.value = value;
    }

    BigDecimal getValue() {
        return value;
    }

    Plus(String input) throws Exception {
        this.value = new BigDecimal(input);
    }

    BigDecimal makeOperation (BigDecimal value, String operator) throws ArithmeticException {
        switch (operator) {
            case "+":
                return this.getValue().add(value);
            case "-":
                return  this.getValue().subtract(value);
            case "/":
                if (this.getValue().compareTo(new BigDecimal(0)) == 0) {
                    System.out.println("Ошибка деления на 0");
                    throw new ArithmeticException("Ошибка деления на 0");
                }
                return this.getValue().divide(value);
            case "*":
                return  this.getValue().multiply(value);

            default:
                return null;
        }
    }

    BigDecimal makeOperation (int value, String operator) {
        switch (operator) {
            case "^":
                return this.getValue().pow(value);
            default:
                return null;
        }
    }
}
