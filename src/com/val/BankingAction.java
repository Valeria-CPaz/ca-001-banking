package com.val;

public class BankingAction {

    public static void addCustomer(Customer customer) {

        System.out.println("Creating " + customer.firstName);
        System.out.println("Creating " + customer.lastName);
        // you can add in all the other parameters for a customer

        //then you write the customer details out to the file
    }

    public static void deleteCustomer() {

    }

    public static void createTransaction(BankingTransaction transaction) {

        System.out.println("Creating Transaction....");
    }

    public static void listCustomers() {

    }

    public static void listTransactionHistory() {

    }

    public static void lodge() {

    }

    public static void withdraw() {

    }
}
