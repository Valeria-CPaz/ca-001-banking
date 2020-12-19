package com.val;

public class Customer {

    // variables to defined customer
    String firstName, lastName, email, account, pin;

    CurrentAccount	ca;
    SavingsAccount	sa;

    // constructor of customer class
    public Customer(String _firstName, String _lastName, String _email) {
        firstName = _firstName;
        lastName = _lastName;
        email = _email;
        account = pinGenerator(false);
        pin = pinGenerator(true);

        ca = new CurrentAccount(account);
        sa = new SavingsAccount(account);
    }

    // method that creates the account and pin based on the first and last name input
    private String pinGenerator(boolean _pin) {

        int iFirst = 1, iLast = 1;

        char[] letterArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        System.out.println();
        for (int i = 0; i < letterArray.length; i++) {
            if (Character.toLowerCase(firstName.charAt(0)) == letterArray[i]) {
                iFirst += i;
            }
            if (Character.toLowerCase(lastName.charAt(0)) == letterArray[i]) {
                iLast += i;
            }

        }

        if (!_pin) {
            return String.format("%c%c-%s-%02d-%02d", Character.toLowerCase(firstName.charAt(0)), Character.toLowerCase(lastName.charAt(0)),
                    (firstName.length() + lastName.length()), iFirst, iLast);
        } else {
            return String.format("%02d%02d", iFirst, iLast);
        }

    }

    // get account
    public String getAccount() {
        return account;
    }

    // set account
    public void setAccount(String account) {
        this.account = account;
    }

    // get current account
    public CurrentAccount getCa() {
        return ca;
    }

    //set current account
    public void setCa(CurrentAccount ca) {
        this.ca = ca;
    }

    //get savings account
    public SavingsAccount getSa() {
        return sa;
    }

    // set savings account
    public void setSa(SavingsAccount sa) {
        this.sa = sa;
    }

}