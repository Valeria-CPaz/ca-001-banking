package com.val;

import java.util.Formatter;
import java.util.Scanner;

public class BankingAction {


    private static Formatter output;
    private static Scanner input;

    private static String CustomerFileSavings = "customerlisting.txt";

    public static void addCustomer(Customer customer) {

        System.out.println("Creating " + customer.firstName + " " + customer.lastName );

        // you can add in all the other parameters for a customer

        //then you write the customer details out to the file
    }

    public static void deleteCustomer() {

    }

    public static void createTransaction(BankingTransaction transaction) {

        System.out.println("Creating Transaction....");

        if (transaction.customer != null)
        {
            System.out.println(" value: "+ transaction.amount +" account: "+ transaction.customer.getAccount() +" type: "+ transaction.typeTransaction+" ");

            FileManager.AddTransactionToFile(transaction);
        }

    }

    public static void listCustomers()
    {
        FileManager.ListCustomers();
    }

    public static void listCustomersBalance()
    {
        FileManager.ListCustomersBalance();
    }

    public static void listTransactionHistory(String account, int type)
    {
        FileManager.listTransactions(account, type);
    }

    public static void lodge() {

    }

    public static void withdraw() {

    }
}
