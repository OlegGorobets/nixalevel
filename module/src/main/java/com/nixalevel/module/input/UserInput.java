package com.nixalevel.module.input;

import java.util.Scanner;

public class UserInput {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int getUserInput() {
        int userInput;
        String line = "Enter a total amount limit:";
        do {
            System.out.println(line);
            userInput = SCANNER.nextInt();
        } while (userInput <= 0);
        return userInput;
    }
}
