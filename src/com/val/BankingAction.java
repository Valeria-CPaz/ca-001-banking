package com.val;

import java.util.Formatter;
import java.util.Scanner;

public class BankingAction {

    // method that adds new customer
    public static void addCustomer(Customer customer) {

        System.out.println("Creating " + customer.firstName + " " + customer.lastName );
    }

    public static void deleteCustomer() {

    }

    // method called to create each transaction
    public static void createTransaction(BankingTransaction transaction) {

        System.out.println("Creating Transaction...");

        if (transaction.customer != null)
        {
            System.out.println(" value: "+ transaction.amount + " account: " + transaction.customer.getAccount() +
                    " type: " + transaction.typeTransaction);

            FileManager.AddTransactionToFile(transaction);
        }

    }

    // method called to list the customer in the txt file
    public static void listCustomers()
    {
        FileManager.ListCustomers();
    }

    // method called to list the customer balance
    public static void listCustomersBalance()
    {
        FileManager.ListCustomersBalance();
    }

    // method called to list the transaction history
    public static void listTransactionHistory(String account, int type)
    {
        FileManager.listTransactions(account, type);
    }
}