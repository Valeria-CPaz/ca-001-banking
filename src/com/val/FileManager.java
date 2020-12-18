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

public class FileManager {
    private static Formatter output;
    private static Scanner input;

    private static String CustomerFile = "customerlisting.txt";


    public static void AddCustomerToFile(Customer customer) {
        OpenFileToWrite(CustomerFile);
        output.format("%s %s %s %s %s %n",customer.firstName, customer.lastName, customer.email, customer.account, customer.pin);
        CloseFile();
    }


    public static boolean RemoveCustomerFromFile(Customer c)
    {
        OpenFileToRead();

        if(c.ca.getBalance() != 0 || c.sa.getBalance() != 0)
        {
            System.err.println("The balance should have 0 value ");
            return false;
        }

        try {
            while (input.hasNext())
            {
                String fn, ln, em, ac, pi;
                fn = input.next();
                ln = input.next();
                em = input.next();
                ac = input.next();
                pi = input.next();


                System.out.printf("%s %s %s %s %n", fn, ln, em, ac);

                if(ac.equals(c.getAccount()))
                {
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

    public static void AddTransactionToFile(BankingTransaction ts) {

        if(ts.typeAccount == AppConstants.CURRENT_ACCOUNT)
        {
            OpenFileToWrite(ts.customer.getAccount() + "-current.txt");
            //ts.customer.getCa().setBalance(ts.typeTransaction, ts.amount);

            if(!ts.customer.getCa().setBalance(ts.typeTransaction, ts.amount))
            {
                System.out.println(" The current balance must have plus then 0.  ");
                CloseFile();
                return;
            }

            output.format("%s %s %f %f %n", date(), ts.typeTransaction, ts.amount, ts.customer.getCa().getBalance());

        }
        else
        {
            OpenFileToWrite(ts.customer.getAccount() + "-savings.txt");
            //ts.customer.getSa().setBalance(ts.typeTransaction, ts.amount);

            if(!ts.customer.getSa().setBalance(ts.typeTransaction, ts.amount))
            {
                System.out.println(" The savings balance must have plus then 0.  ");
                CloseFile();
                return;
            }

            output.format("%s %s %f %f %n", date(), ts.typeTransaction, ts.amount, ts.customer.getSa().getBalance());

        }

        CloseFile();

    }

    public static void CreateCustomerAccount(String customerAccountName) {
        OpenFileToWrite(customerAccountName);

        output.format("testing customer account %n");
        CloseFile();
    }

    public static void ListCustomers() {

        OpenFileToRead();

        try {
            while (input.hasNext())
            {
                String fn, ln, em, ac, pi;
                fn = input.next();
                ln = input.next();
                em = input.next();
                ac = input.next();
                pi = input.next();


                System.out.printf("%s %s %s %s %n", fn, ln, em, ac);
                //System.out.printf("%s %s %s %s %s %n", input.next(), input.next(), input.next(), input.nextInt(), input.nextInt());
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

    public static void ListCustomersBalance() {

        OpenFileToRead();

        try {
            while (input.hasNext())
            {
                String fn, ln, em, ac, pi;
                fn = input.next();
                ln = input.next();
                em = input.next();
                ac = input.next();
                pi = input.next();

                double ca = total(ac, AppConstants.CURRENT_ACCOUNT);
                double sa = total(ac, AppConstants.SAVING_ACCOUNT);

                System.out.printf("%s %s %s %s current: %f savings: %f %n", fn, ln, em, ac, ca, sa);
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

    public static boolean hasCustomer(String fname, String lname, String account, String pin)
    {
        OpenFileToRead();

        try {
            while (input.hasNext())
            {
                //System.out.printf("%s %s %s %d %d%n",input.next(),input.next(),input.nextInt(),input.nextInt());
                String fn, ln, em, ac, pi;
                fn = input.next();
                ln = input.next();
                em = input.next();
                ac = input.next();
                pi = input.next();
                //System.out.println("'" + fn + "' '" +  ln + "' '" + ac + "' '" +  pi + "' ");
                if(
                        fname.equals(fn) &&
                                lname.equals(ln) &&
                                account.equals(ac) &&
                                pin.equals(pi)
                )
                {

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

    public static Customer getCustomer(String fname, String lname, String account, String pin)
    {
        OpenFileToRead();

        try {
            while (input.hasNext())
            {
                //System.out.printf("%s %s %s %d %d%n",input.next(),input.next(),input.nextInt(),input.nextInt());
                String fn, ln, em, ac, pi;
                fn = input.next();
                ln = input.next();
                em = input.next();
                ac = input.next();
                pi = input.next();

                if(
                        fname.equals(fn) &&
                                lname.equals(ln) &&
                                account.equals(ac) &&
                                pin.equals(pi)
                )
                {

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

    public static Customer getCustomer(String account)
    {
        OpenFileToRead();

        try {
            while (input.hasNext())
            {
                //System.out.printf("%s %s %s %d %d%n",input.next(),input.next(),input.nextInt(),input.nextInt());
                String fn, ln, em, ac;
                fn = input.next();
                ln = input.next();
                em = input.next();
                ac = input.next();

                if( account.equals(ac))
                {
                    return new Customer(fn, ln, em);
                }
                else
                {
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

    public static double total(String account, int _type)
    {

        String filename;
        double total = 0;

        if(_type == AppConstants.SAVING_ACCOUNT)
            filename = account + "-savings.txt";
        else
            filename = account + "-current.txt";

        if(hasFile(filename))
            OpenFileToRead(filename);
        else
            return total;


        try {
            while (input.hasNext())
            {
                //output.format("%s %s %f %f %n", date(), ts.typeTransaction, ts.amount, ts.customer.getCa().getBalance());

                String date, transaction;
                double amount, balance;
                date = input.next();
                transaction = input.next();
                amount = input.nextDouble();
                balance = input.nextDouble();

                total += amount;

                //System.out.printf("total: %s %s %f %f %n", date ,  transaction, amount, balance);

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

    public static void listTransactions(String account, int _type)
    {

        String filename;

        if(_type == AppConstants.SAVING_ACCOUNT)
            filename = account + "-savings.txt";
        else
            filename = account + "-current.txt";

        if(hasFile(filename))
            OpenFileToRead(filename);


        try {
            while (input.hasNext())
            {
                //output.format("%s %s %f %f %n", date(), ts.typeTransaction, ts.amount, ts.customer.getCa().getBalance());

                String date, transaction;
                double amount, balance;
                date = input.next();
                transaction = input.next();
                amount = input.nextDouble();
                balance = input.nextDouble();

                System.out.printf("%s %s %f %f %n", date , (transaction.equals("L"))?"Lodge":"Withdraw", amount, balance);

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

    private static boolean hasFile(String name)
    {
        try {
            input = new Scanner(Paths.get(name));
            return true;
        } catch (IOException ioException) {
            System.err.println("Error opening "+ name +".");
            return false;
        }
    }


    private static void CloseFile() {

        if (output != null) {
            output.close();
        }


    }

    private static void OpenFileToRead() {

        try {
            input = new Scanner(Paths.get(CustomerFile));
        } catch (IOException ioException) {
            System.err.println("Error opening file. Terminating");
            System.exit(1);
        }

    }

    private static void OpenFileToRead(String file) {

        try {
            input = new Scanner(Paths.get(file));
        } catch (IOException ioException) {
            System.err.println("Error opening "+ file +". Terminating");
            System.exit(1);
        }

    }


    public static String date()
    {
        //SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy-MM-dd 'at' HH:mm:ss z");
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        //System.out.println(formatter.format(date));
        return formatter.format(date);
    }


}


