package com.val;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

// my file with each account added is being created in this class
public class FileManager {
    private static Formatter output;
    private static Scanner input;

    // creates my customer listing file
    private static String CustomerFile = "customerlisting.txt";


    // add new customers to my file
    public static void AddCustomerToFile(Customer customer) {
        OpenFileToWrite(CustomerFile);
        output.format("%s %s %s %s %s \n", customer.firstName, customer.lastName, customer.email, customer.account, customer.pin);
        CloseFile();
    }

    // method to remove customer from file
    public static boolean RemoveCustomerFromFile(Customer c) {
        OpenFileToRead();

        if (c.ca.getBalance() != 0 || c.sa.getBalance() != 0) {
            System.err.println("The balance should have 0 value ");
            return false;
        }

        try {
            while (input.hasNext()) {
                String fn, ln, em, ac, pi;
                fn = input.next(); // first name
                ln = input.next(); // last name
                em = input.next(); // email
                ac = input.next(); // account
                pi = input.next(); // pin


                System.out.printf("%s %s %s %s %n", fn, ln, em, ac);

                if (ac.equals(c.getAccount())) {
                    input.remove();
                }

            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");
            return false;
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");
            return false;

        }

        CloseFile();

        return true;
    }

    // write each transaction into file
    public static void AddTransactionToFile(BankingTransaction ts) {

        if (ts.typeAccount == AppConstants.CURRENT_ACCOUNT) {
            OpenFileToWrite(ts.customer.getAccount() + "-current.txt");

            if (!ts.customer.getCa().setBalance(ts.typeTransaction, ts.amount)) {
                System.out.println(" Current balance cannot be < 0");
                CloseFile();
                return;
            }

            output.format("%s %s %f %f \n", date(), ts.typeTransaction, ts.amount, ts.customer.getCa().getBalance());

        } else {
            OpenFileToWrite(ts.customer.getAccount() + "-savings.txt");

            if (!ts.customer.getSa().setBalance(ts.typeTransaction, ts.amount)) {
                System.out.println(" Savings balance cannot be < 0");
                CloseFile();
                return;
            }

            output.format("%s %s %f %f \n", date(), ts.typeTransaction, ts.amount, ts.customer.getSa().getBalance());

        }

        CloseFile();

    }

    public static void createCustomerAccount(String customerAccountName, int _type) {
        String filename;

        if (_type == AppConstants.SAVING_ACCOUNT) {
            filename = customerAccountName + "-savings.txt";
        } else {
            filename = customerAccountName + "-current.txt";
        }
        OpenFileToWrite(filename);
        CloseFile();
    }

    // method to call my customer list
    public static void ListCustomers() {

        OpenFileToRead();

        try {
            while (input.hasNext()) {
                String fn, ln, em, ac, pi;
                fn = input.next(); // first name
                ln = input.next(); // last name
                em = input.next(); // email
                ac = input.next(); // account
                pi = input.next(); // pin

                System.out.printf("%s %s %s %s \n", fn, ln, em, ac);
            }

        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");
            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");
            System.exit(1);

        }

        CloseFile();

    }

    // method to list customer balance
    public static void ListCustomersBalance() {

        OpenFileToRead();

        try {
            while (input.hasNext()) {
                String fn, ln, em, ac, pi;
                fn = input.next(); // first name
                ln = input.next(); // last name
                em = input.next(); // email
                ac = input.next(); // account
                pi = input.next(); // pin

                double ca = total(ac, AppConstants.CURRENT_ACCOUNT);
                double sa = total(ac, AppConstants.SAVING_ACCOUNT);

                System.out.printf("%s %s %s %s current: %.2f savings: %.2f \n", fn, ln, em, ac, ca, sa);
            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");
            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");
            System.exit(1);

        }

        CloseFile();

    }

    // method that checks if customer exists
    public static boolean hasCustomer(String fname, String lname, String account, String pin) {
        OpenFileToRead();

        try {
            while (input.hasNext()) {

                String fn, ln, em, ac, pi;
                fn = input.next(); // first name
                ln = input.next(); // last name
                em = input.next(); // email
                ac = input.next(); // account
                pi = input.next(); // pin

                if (fname.equals(fn) && lname.equals(ln) && account.equals(ac) && pin.equals(pi)) {
                    return true;
                }

            }

        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");

            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");

            System.exit(1);

        }

        CloseFile();
        return false;
    }

    //method called to get customer. I used method overload here
    public static Customer getCustomer(String fname, String lname, String account, String pin) {
        OpenFileToRead();

        try {
            while (input.hasNext()) {

                String fn, ln, em, ac, pi;
                fn = input.next(); // first name
                ln = input.next(); // last name
                em = input.next(); // email
                ac = input.next(); // account
                pi = input.next(); // pin

                if (fname.equals(fn) && lname.equals(ln) && account.equals(ac) && pin.equals(pi)) {
                    return new Customer(fn, ln, em);
                }

            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");

            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");

            System.exit(1);

        }

        CloseFile();
        return null;
    }

    //method called to get customer. I used method overload here
    public static Customer getCustomer(String account) {
        OpenFileToRead();

        try {
            while (input.hasNext()) {

                String fn, ln, em, ac, pin;
                fn = input.next(); // first name
                ln = input.next(); // last name
                em = input.next(); // email
                ac = input.next(); // account
                pin = input.next(); // pin

                if (account.equals(ac)) {
                    return new Customer(fn, ln, em);
                } else {
                    return null;
                }

            }

        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");

            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");

            System.exit(1);

        }

        CloseFile();
        return null;
    }

    // method to get total balance
    public static double total(String account, int _type) {

        String filename;
        double total = 0;

        if (_type == AppConstants.SAVING_ACCOUNT) {
            filename = account + "-savings.txt";
        } else {
            filename = account + "-current.txt";
        }
        if (hasFile(filename)) {
            OpenFileToRead(filename);
        } else {
            return total;
        }

        try {
            while (input.hasNext()) {

                String date, transaction;
                double amount, balance;
                date = input.next();
                transaction = input.next();
                amount = input.nextDouble();
                balance = input.nextDouble();

                total += amount;

            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");

            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");

            System.exit(1);

        }

        CloseFile();
        return total;
    }

    //method to list my transactions
    public static void listTransactions(String account, int _type) {

        String filename;

        if (_type == AppConstants.SAVING_ACCOUNT) {
            filename = account + "-savings.txt";
        } else {
            filename = account + "-current.txt";
        }
        if (hasFile(filename)) {
            OpenFileToRead(filename);
        }

        try {
            while (input.hasNext()) {

                String date, transaction;
                double amount, balance;
                date = input.next();
                transaction = input.next();
                amount = input.nextDouble();
                balance = input.nextDouble();

                System.out.printf("%s | %s | amount: %.2f | balance: %.2f \n", date, (transaction.equals("Lodge")) ? "Lodge" : "Withdraw", amount, balance);

            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");

            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");

            System.exit(1);

        }

        CloseFile();


    }


    //method to open file to write new data
    private static void OpenFileToWrite(String fileToOpen) {

        try {
            FileWriter f = new FileWriter(fileToOpen, true);
            output = new Formatter(f);
        } catch (SecurityException securityException) {
            System.out.println("Write permission denied");
            System.exit(1);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Error opening file, Terminating");
            System.exit(1);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    // method to check if there's a file
    private static boolean hasFile(String name) {
        try {
            input = new Scanner(Paths.get(name));
            return true;
        } catch (IOException ioException) {
            System.err.println("Error opening " + name + ".");
            return false;
        }
    }

    // method to close file
    private static void CloseFile() {
        if (output != null) {
            output.close();
        }
    }

    // method to open file to read
    private static void OpenFileToRead() {

        try {
            input = new Scanner(Paths.get(CustomerFile));
        } catch (IOException ioException) {
            System.err.println("Error opening file. Terminating");
            System.exit(1);
        }
    }

    // method to open file to read - method overload
    private static void OpenFileToRead(String file) {

        try {
            input = new Scanner(Paths.get(file));
        } catch (IOException ioException) {
            System.err.println("Error opening " + file + ". Terminating");
            System.exit(1);
        }
    }

    // method to input date into file
    public static String date() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

}