package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {

    public String read() throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       String expression = bufferedReader.readLine();
       return expression;
    }
}
