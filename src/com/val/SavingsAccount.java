package com.val;

public class SavingsAccount extends Account {

    private double balance;

    // method that create the saving account
    public SavingsAccount(String account)
    {
        this.account = account;
        FileManager.createCustomerAccount(account, AppConstants.SAVING_ACCOUNT);
        balance = FileManager.total(account, AppConstants.SAVING_ACCOUNT );
    }


    // method to get balance after transaction
    public double getBalance() {
        return balance;
    }

    // method to set the balance
    public boolean setBalance(String type, double balance) {


        double newBalance = this.balance;

        if(type.equals("Lodge")) {
            newBalance += balance;
        } else {
            newBalance -= balance;
        }
        if(newBalance < 0) {
            return false;
        }

        this.balance = newBalance;

        return true;

    }


}
