package com.val;

public class Main {

    public static void main(String[] args) {
	// write your code here

        private static void demoThree() {

            String firstName = "Valeria";
            String lastName = "paz";
            int iFirst = 0, iLast = 0;

            char[] letterArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                    'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

            System.out.println();
            for (int i = 0; i < letterArray.length; i++) {
                if (Character.toLowerCase(firstName.charAt(0)) == letterArray[i]) {
                    iFirst = i + 1;
                }
                if (Character.toLowerCase(lastName.charAt(0)) == letterArray[i]) {
                    iLast = i + 1;
                }

            }

            System.out.printf("account number is: %c%c - %s - %02d - %02d", Character.toLowerCase(firstName.charAt(0)), Character.toLowerCase(lastName.charAt(0)),
                    (firstName.length() + lastName.length()),iFirst, iLast);

        }
    }
}
