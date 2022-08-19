package com.company;
import java.util.*;
public class Util {

    public double plus(double n1, double n2) {
        return n1 + n2;
    }

    public double minus(double n1, double n2) {
        return n1 - n2;
    }

    public double multiply(double n1, double n2) {
        return n1 * n2;
    }

    public double divide(double n1, double n2) {
        double result = 0;
        if (n2 != 0) {
            result = n1 / n2;
        } else {
            System.out.println("Division into zero ('0')  is not allowed!");
        }
        return result;
    }


    public int getPriority(char symbol) {
        switch (symbol) {
            case '*':
            case '/':
                return 4;
            case '+':
            case '-':
                return 3;
            case '(':
                return 2;
            case ')':
                return 1;
            default:
                return 0;
        }
    }


    public boolean validationEnteredExpression(String exp) {
        char[] expression=exp.toCharArray();
        boolean valid = false;
        for (int i = 0; i < expression.length; i++) {

            if (expression[i] == '('  && Character.isDigit(exp.charAt(i-1))) {

                    System.out.println("Entered  expression incorrect!");
                    throw new NumberFormatException("The expression is not correct '" + expression[i-1] + " "+expression[i]+"'");
            }
            if (expression[i] >= 58 && expression[i] <= 128 || expression[i] >= 33 && expression[i] <= 39 || expression[i] == 44) {
                throw new NumberFormatException("The expression is not correct '" + expression[i] + "'");
            } else {
                valid = true;
            }
        }
        return valid;
    }




    public String parseExpression(String expression) {
        String str = "";
        Stack<Character> stack = new Stack<>();//создали ссылку на коллекцию длф хранения
        for (int i = 0; i < expression.length(); i++) {

            if (validationEnteredExpression(expression)) {//проверяем введенное выражение
            }

            switch (getPriority(expression.charAt(i))) {
                case 0://если цифра
                    str += expression.charAt(i); //добавляем чар в строку
                    break;
                case 2://если ( ложим в стек
                    stack.push(expression.charAt(i));
                    break;
                case 1://если ) то делаем
                        str += ' ';//в строку добавляем пробел
                        while (getPriority(stack.peek()) != 2) {// если не ')' то делаем  (peek возвращает верхний элемент не удаляя)
                            str += stack.pop();//ложим в строку число из стека и удаляем его
                        }
                        stack.pop();//удаляем '('
                    break;
                default:
                    if (getPriority(expression.charAt(i)) > 2) {//если приорити больше 2 (т.е./*-+)
                        str += ' ';
                        while (!stack.empty()) {//если не пустой
                            if (getPriority(stack.peek()) >= getPriority(expression.charAt(i))) {//если  верхний элемент больше приорити чем текущий или равно
                                str += stack.pop();//кладем в переменную верхний элемент удаляя из стека
                            } else {
                                break;
                            }
                        }
                        stack.push(expression.charAt(i));//кладем в стек элемент (т.е./*-+)
                    }
                    break;
            }
        }
        while (!stack.empty()) {//пока стек не пустой
            str += stack.pop();//добавляем в строку при этом удаляя из стека
        }
        return str;
    }



    public double calculate(String convExpr) {

        String symbol = "";
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < convExpr.length(); i++) {
            if (convExpr.charAt(i) == ' ') continue;//если пробел то пропускаем
            if (getPriority(convExpr.charAt(i)) == 0) { //если цифра то запускаем цикл
                while (getPriority(convExpr.charAt(i)) == 0 && convExpr.charAt(i) != ' ') {//пока цифра и пробел
                    symbol += convExpr.charAt(i++);//добавляем в строку следующий чар элемент
                    if (i == convExpr.length()) break;//если и достигает длинны массива прерываем цикл
                }
                stack.push(Double.parseDouble(symbol));//добавляем в коллекцию стек наше число
                symbol = "";//очищаем
            }
            if (getPriority(convExpr.charAt(i)) > 2) {//если приоритет больше 2 (т.е. -+*/)
                double a = stack.pop();//удаляет и возвращает верхний элемент( цифра последняя)
                double b = stack.pop();//удаляет и возвращает следующую цифру
                switch (convExpr.charAt(i)) {//далее считаем
                    case '+':
                        stack.push(plus(b, a));
                        break;
                    case '-':
                        stack.push(minus(b, a));
                        break;
                    case '*':
                        stack.push(multiply(b, a));
                        break;
                    case '/':
                        stack.push(divide(b, a));
                        break;
                }

            }
        }
        return stack.pop();//возвращаем результат действия
    }

    public double runCalculate(String expression) {
        return calculate(parseExpression(expression));//запускаем калькулятор передавая в параметр измененную строку
    }


}
