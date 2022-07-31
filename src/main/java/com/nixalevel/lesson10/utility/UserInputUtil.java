package com.nixalevel.lesson10.utility;

import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.Vehicle;
import com.nixalevel.lesson10.service.VehicleService;

import java.util.List;
import java.util.Scanner;

public class UserInputUtil {
    private static final Scanner SCANNER = new Scanner(System.in);

    private UserInputUtil() {
    }

    public static int getUserInput(String line, List<String> names) {
        int userInput;
        do {
            System.out.println(line);
            for (int i = 0; i < names.size(); i++) {
                System.out.printf("%d) %s%n", i, names.get(i));
            }
            userInput = SCANNER.nextInt();
        } while (userInput < 0 || userInput >= names.size());
        return userInput;
    }

    public static String getUserInputChangeString(String line) {
        System.out.println(line);
        String userInput;
        userInput = SCANNER.next();
        return userInput;
    }

    public static int getUserInputChangeInt(String line) {
        System.out.println(line);
        int userInput;
        userInput = SCANNER.nextInt();
        return userInput;
    }
}
