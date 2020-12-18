package com.val;

public class BankingTransactionLodge extends BankingTransaction
{
    public BankingTransactionLodge(Customer c, double d, int _typeAccount)
    {
        customer = c;
        amount = d;
        typeAccount = _typeAccount;
        typeTransaction = "L";
    }


}
