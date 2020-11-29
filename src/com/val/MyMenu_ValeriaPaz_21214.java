package com.val;

import java.util.Scanner;

public class MyMenu_ValeriaPaz_21214 {

    Scanner scanner = new Scanner(System.in);

    private static String myMenu = "Hello. Welcome to CA1 - Banking Application. Please choose an option:\n" +
            "1 - I am a customer \n" +
            "2 - I am an employee \n" +
            "3 - Finish application";

    int input = scanner.nextInt();

    public void logIn() {

        do{
            System.out.println(myMenu);

            switch (input) {
                case 1:
                    System.out.println("Please, enter your first name: ");
                    System.out.println("Please, enter your last name: ");
                    System.out.println("Please, enter your account number:");
                    System.out.println("Please, enter your pin:");

                case 2:
                    System.out.println("Please, enter your employee pin: ");
            }

        } while (input != 3);

    }
}
