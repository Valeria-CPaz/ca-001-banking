package com.val;

public class BankingTransactionWithdraw extends BankingTransaction {

    // constructor for withdraw transactions
    public BankingTransactionWithdraw(Customer c, double d, int _typeAccount)
    {
        customer = c;
        amount = d;
        typeAccount = _typeAccount;
        typeTransaction = "Withdraw";

    }



}
