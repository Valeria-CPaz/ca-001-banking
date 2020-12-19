package com.val;

public class CurrentAccount extends Account {

    private double balance;

    // constructor of the current account class
    public CurrentAccount(String account) {
        this.account = account;
        FileManager.createCustomerAccount(account, AppConstants.CURRENT_ACCOUNT);
        balance = FileManager.total(account, AppConstants.CURRENT_ACCOUNT);
    }

    // method to get balance
    public double getBalance() {
        return balance;
    }

    // method to set balance
    public boolean setBalance(String type, double balance) {

        double newBalance = this.balance;

        if (type.equals("Lodge")) {
            newBalance += balance;
        } else {
            newBalance -= balance;
        }

        if (newBalance < 0) {
            return false;
        }
        this.balance = newBalance;

        return true;

    }
}
