package com.val;

public class BankingTransactionLodge extends BankingTransaction {

    // constructor for lodge transactions
    public BankingTransactionLodge(Customer c, double d, int _typeAccount)
    {
        customer = c;
        amount = d;
        typeAccount = _typeAccount;
        typeTransaction = "Lodge";
    }


}
