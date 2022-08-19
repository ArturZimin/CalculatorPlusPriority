package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Reader reader = new Reader();
        Util util = new Util();

        StringBuilder sb = new StringBuilder();
        sb.append("******** Application Calculator ******** \n" +
                "1. Enter expression to calculate, example: " +
                "1 + 1 or 1.36 - 1.01 using '+ - * /'\n" +
                "2. Enter 0 - exit");
        System.out.println(sb);


        String expression = "";
        while (true) {
            try {
                expression = reader.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (expression.equals("0")) {
                System.out.println("Good buy!");
                break;
            }
            System.out.println("result: "+util.runCalculate(expression));


        }
    }
}
