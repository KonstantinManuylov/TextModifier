package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        // String phrase1 = "генрих1  играет+2   л-июбит0школу";
        // String phrase2 = "Я ю-лбю-л джаву   всем сердцем+";
        String input = getUserInput();
        String result = textModifier(input);
        System.out.println(result);

    }

    /**
     * @return метод для получения строки от пользователя
     */
    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите предложение: ");
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }

    /**
     * @param input строка от пользователя
     * @return возврат строки без повторных пробелов
     */
    public static String replaceMultipleSpaces(String input) {
        StringBuilder result = new StringBuilder();
        boolean inSpaceSequence = false;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (currentChar == ' ') {
                if (!inSpaceSequence) {
                    result.append(' ');
                    inSpaceSequence = true;
                }
            } else {
                result.append(currentChar);
                inSpaceSequence = false;
            }
        }

        return result.toString();
    }

    /**
     * @param input строка от пользователя
     * @return строка, + заменен на !
     */
    public static String replacePlusToExclamation(String input) {
        return input.replace("+", "!");
    }


    /**
     * @param input строка от пользователя
     * @return строку без числовых значений
     */
    public static String removeNumbers(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * @param input строка от пользователя
     * @return строку без знака - и измененным порядком символов слева и справа от него
     */
    public static String swapAroundMinus(String input) {
        StringBuilder result = new StringBuilder(input);
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '-') {
                if (i > 0 && i < result.length() - 1) {
                    char leftChar = result.charAt(i - 1);
                    char rightChar = result.charAt(i + 1);
                    result.setCharAt(i - 1, rightChar);
                    result.setCharAt(i + 1, leftChar);
                }
                result.deleteCharAt(i);
                i--;
            }
        }
        return result.toString();
    }
    /**
     * @param input строка от пользователя
     * @return сумму числовых значений
     */
    public static int findSumOfNumbers(String input) {
        int result = 0;
        for (char c : input.toCharArray()) {
            if (Character.isDigit((c))) {
                result += Character.getNumericValue(c);
            }
        }
        return result;
    }

    /**
     * @param method метод ввода данных пользователем
     * @return полностью модифицированную строку
     */
    public static String textModifier(String method) {
        // Убираем повторные пробелы
        String inputWithoutMultipleSpaces = replaceMultipleSpaces(method);

        // Меняем местами символы возле знака минус, удаляем знак минус
        String inputWithoutMinusSign = swapAroundMinus(inputWithoutMultipleSpaces);

        // Меняем знак плюс на восклицательный
        String inputWithReplacedPlus = replacePlusToExclamation(inputWithoutMinusSign);

        // Удаляем числовые значения и возвращаем необходимый вариант
        if (findSumOfNumbers(inputWithReplacedPlus) == 0) {
            return removeNumbers(inputWithReplacedPlus);
        } else {
            return removeNumbers(inputWithReplacedPlus) + "\n" + "Сумма чисел в тексте = " + findSumOfNumbers(inputWithReplacedPlus);
        }
    }
}