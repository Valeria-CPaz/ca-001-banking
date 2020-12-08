package com.val;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

    public class FileManager {
        private static Formatter output;
        private static Scanner input;

        private static String CustomerFile = "customerlisting.txt";


        public static void AddCustomerToFile(Customer customer) {
            OpenFileToWrite(CustomerFile);
            output.format("%s %s %s %s %n",customer.firstName, customer.lastName, customer.email, customer.account);
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
                while (input.hasNext()) {
                    System.out.printf("%s %s %s %d %d%n",input.next(),input.next(),input.nextInt(),input.nextInt());
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


    }


