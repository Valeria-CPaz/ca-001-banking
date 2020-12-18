package com.val;

public class CurrentAccount extends Account {

    private double balance;

    public CurrentAccount(String account)
    {
        this.account = account;
        balance = FileManager.total(account, AppConstants.CURRENT_ACCOUNT );
    }

    public double getBalance() {
        return balance;
    }

    public boolean setBalance(String type, double balance) {


        double newBalance = this.balance;

        if(type.equals("L"))
            newBalance += balance;
        else
            newBalance -= balance;

        if(newBalance < 0)
            return false;


        this.balance = newBalance;

        return true;

    }




}
