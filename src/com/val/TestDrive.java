package com.val;

import java.util.Scanner;

public class TestDrive {

    public void run() {

//        testCustomer();
//        testEmployee();
//        testAccount();

        int userChoice = TestDrive.startMenu();

        switch (userChoice) {

            case AppConstants.EMPLOYEE:
                String pin = getBankEmployeePin();
                SecurityBanking sb = new SecurityBanking();
                boolean isAuthenticated = sb.login(pin);

                if (isAuthenticated) {

                    int bankEmployeeAction = TestDrive.bankingMenu();

                    switch (bankEmployeeAction) {
                        case AppConstants.CUSTOMER_CREATE:
                            Customer newCustomer = addCustomerForm();
                            BankingAction.addCustomer(newCustomer);
                            TestDrive.bankingMenu();
                            break;
                        case AppConstants.CUSTOMER_DELETE:
                            break;
                        case AppConstants.TRANSACTION_CREATE:

                            int transactionChoice = TestDrive.transactionMenu();

                            switch (transactionChoice) {
                                case 1:
                                    BankingTransaction btl = createTransactionLodgeForm();
                                    BankingAction.createTransaction(btl);
                                    break;
                                case 2:
                                    BankingTransaction btw = createTransactionWithdrawForm();
                                    BankingAction.createTransaction(btw);
                                    break;
                            }

                            break;
                        case AppConstants.QUIT:

                    }

                } else {
                    System.out.println("Authentication error");
                    TestDrive.bankingMenu();
                    return;
                }

                break;
            case 2:

                // Get the banking Pin from employee
                //String pin = getBankEmployeePin();
                //SecurityBanking sb = new SecurityBanking();
                /// boolean isAuthenticated = sb.login(pin);

                // add logic here if isAuthenticated

                // if (isAuthenticated) {

                // int customerAction = MenuBuilder.customerMneu();

                // userChoice = MenuBuilder.customerMenu();

                // } else {
                // System.out.println("error....");
                //  return;
                //  }


                break;
            case 3:
                System.out.println("Option 3s");
                break;
            case 4:
                return;
        }

    }


    private static int startMenu() {

        int selection;
        Scanner scanner = new Scanner(System.in);
        /*************************************************/

        System.out.println("Choose from these choices:");

        System.out.println("-------------------------------\n");
        System.out.println("1 - Bank Employee Login");
        System.out.println("2 - Customer Login");
        System.out.println("3 - Quit");

        selection = scanner.nextInt();
        return selection;

    }

    private static int customerMenu() {

        int selection;
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------\n");
        System.out.println("1 - Login");
        System.out.println("2 - customerMenuOption 2");
        System.out.println("3 - customerMenuOption 3");
        System.out.println("4 - customerMenuQuit");

        selection = scanner.nextInt();
        return selection;
    }

    private static Customer addCustomerForm() {

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter First Name");
        String firstName = myObj.nextLine();
        System.out.println("First Name is " + firstName);

        System.out.println("Enter Last Name");
        String lastName = myObj.nextLine();
        System.out.println("Last Name is: " + lastName);

        System.out.println("Enter e-mail");
        String email = myObj.nextLine();
        System.out.println("E-mail is: " + email);

        Customer customer = new Customer(firstName, lastName, email);
        FileManager.AddCustomerToFile(customer);

        return customer;

    }

    private static int transactionMenu() {

        int selection;
        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------------\n");
        System.out.println("1 - Lodge Money");
        System.out.println("1 - Withdraw Money");

        selection = scanner.nextInt();
        return selection;
    }

    private static void deleteCustomer() {

    }

    private static BankingTransaction createTransactionLodgeForm() {

        System.out.println("Transaction lodge form goes here");
        BankingTransactionLodge btl = new BankingTransactionLodge();
        return btl;

    }

    private static BankingTransaction createTransactionWithdrawForm() {

        System.out.println("Transaction lodge form goes here");
        BankingTransactionWithdraw btw = new BankingTransactionWithdraw();
        return btw;

    }

    private static int bankingMenu() {

        int selection;
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------------\n");
        System.out.println("1 - Add Customer");
        System.out.println("2 - Delete Customer");
        System.out.println("3 - Create Transaction");
        System.out.println("4 - Quit");

        selection = scanner.nextInt();
        return selection;

    }

    private static String getBankEmployeePin() {

        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter Pin");

        String pin = myObj.nextLine();

        return pin;
    }

}
