package com.val;

public class SecurityCustomer extends Security {

    // inherit the class Security and checks credentials for customer login
    public boolean login(String pinNumber) {
        return false;
    }

    // check the credentials
    public boolean getCredentials(String firstName, String lastName, String accountNumber, String pin)
    {
        return FileManager.hasCustomer(firstName, lastName, accountNumber, pin);
    }


}
