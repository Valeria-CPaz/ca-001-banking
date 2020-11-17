package com.val;

public class Customer_ValeriaPaz_21214 {

    private String firstName;
    private String lastName;
    private String email;
    private String account;
    private String pin;

    public Customer_ValeriaPaz_21214(String _firstName, String _lastName, String _email) {
        firstName = _firstName;
        lastName = _lastName;
        email = _email;
        account = pinGenerator(false);
        pin = pinGenerator(true);
    }

    public void show() {
        System.out.println("name: " + firstName + " lastname: " + lastName + " email: " + email + " account: " + account + " pin: " + pin);

    }

    private String pinGenerator(boolean _pin) {

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

        if (!_pin) {
            return String.format("%c%c-%s-%02d-%02d", Character.toLowerCase(firstName.charAt(0)), Character.toLowerCase(lastName.charAt(0)),
                    (firstName.length() + lastName.length()), iFirst, iLast);
        } else {
            return String.format("%02d%02d", iFirst, iLast);
        }

    }
}

