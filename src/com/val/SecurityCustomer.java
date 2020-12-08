package com.val;

public class SecurityCustomer extends Security {

    public boolean login(String pinNumber) {
        return false;
    }

    public boolean getCredentials(String firstName, String lastName, String accountNumber) {
        return false;
    }
}
