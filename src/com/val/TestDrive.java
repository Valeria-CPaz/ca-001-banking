package com.val;

import java.util.Scanner;

public class TestDrive {

    // method called inside main - my menu
    public void run() {

        int userChoice;

        do {
            userChoice = TestDrive.startMenu();

            switch (userChoice) {

                case AppConstants.EMPLOYEE:
                    String pin = getBankEmployeePin();
                    SecurityBanking sb = new SecurityBanking();
                    boolean isAuthenticated = sb.login(pin);

                    if (isAuthenticated) {
                        int bankEmployeeAction;

                        do {
                            bankEmployeeAction = TestDrive.bankingMenu();

                            switch (bankEmployeeAction) {
                                case AppConstants.CUSTOMER_CREATE:
                                    Customer newCustomer = addCustomerForm();
                                    BankingAction.addCustomer(newCustomer);
                                    TestDrive.bankingMenu();
                                    break;
                                case AppConstants.CUSTOMER_DELETE:
                                    deleteCustomer();
                                    break;
                                case AppConstants.TRANSACTION_CREATE:

                                    int transactionChoice = 0;

                                    do {
                                        transactionChoice = TestDrive.transactionMenu();

                                        switch (transactionChoice) {
                                            case AppConstants.TRANSACTION_LODGE:
                                                BankingTransaction btl = createTransactionLodgeForm();
                                                BankingAction.createTransaction(btl);
                                                TestDrive.bankingMenu();
                                                break;
                                            case AppConstants.TRANSACTION_WITHDRAW:
                                                BankingTransaction btw = createTransactionWithdrawForm();
                                                BankingAction.createTransaction(btw);
                                                TestDrive.bankingMenu();
                                                break;
                                            default:
                                                System.out.println("Transaction choice invalid");
                                        }

                                    } while (transactionChoice != 2);

                                    break;

                                case AppConstants.CUSTOMER_LIST:
                                    BankingAction.listCustomersBalance();
                                    break;
                                case AppConstants.QUIT:
                                    quit();
                                    break;
                                default:
                                    System.out.println("invalid choice");
                            }

                        } while (bankEmployeeAction != 5);

                    } else {
                        System.out.println("Authentication error");
                    }

                    break;

                case AppConstants.CUSTOMER:

                    SecurityCustomer sc = new SecurityCustomer();
                    String[] credentials = getCustomerCredentials();

                    boolean isCustomerAuthenticated = sc.getCredentials(credentials[0], credentials[1], credentials[2], credentials[3]);

                    if (isCustomerAuthenticated) {
                        Customer c = FileManager.getCustomer(credentials[2]);

                        int customerAction;

                        do {
                            customerAction = TestDrive.customerMenu();

                            switch (customerAction) {
                                case AppConstants.TRANSACTION_LODGE:
                                    BankingTransaction btl = createTransactionLodgeForm(c);
                                    BankingAction.createTransaction(btl);
                                    break;
                                case AppConstants.TRANSACTION_WITHDRAW:
                                    BankingTransaction btw = createTransactionWithdrawForm(c);
                                    BankingAction.createTransaction(btw);
                                    break;
                                case AppConstants.TRANSACTION_LIST:
                                    int type = createTransactionListForm(c);
                                    BankingAction.listTransactionHistory(c.getAccount(), type);
                                    break;
                                case AppConstants.QUIT:
                                    startMenu();
                                    break;
                                default:
                                    System.out.println("invalid choice");
                            }
                        } while (customerAction != 4);

                    } else {
                        System.out.println("error....");
                        return;
                    }

                    break;

                case 3:
                    quit();
                    break;
                case 4:
                    return;
            }

        }
        while (userChoice != 5);

    }

    // method with start menu options
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

    // method with the customer menu option
    private static int customerMenu() {

        int selection;
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------\n");

        System.out.println("1 - Lodge/Deposit ");
        System.out.println("2 - Withdraw");
        System.out.println("3 - List transactions");
        System.out.println("4 - Return");

        selection = scanner.nextInt();
        return selection;
    }

    // method that adds the customer form
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

    // method with the transaction menu options
    private static int transactionMenu() {

        int selection;
        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------------\n");
        System.out.println("1 - Lodge Money");
        System.out.println("2 - Withdraw Money");

        selection = scanner.nextInt();
        return selection;
    }

    // method to delete customers
    private static void deleteCustomer() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------------\n");
        System.out.println("Type account number");

        String account = scanner.nextLine();

    }

    // method called to create lodge transactions
    private static BankingTransaction createTransactionLodgeForm() {

        System.out.println("customer Transaction: lodge");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter account:");
        String account = myObj.nextLine();

        System.out.println("Enter amount:");
        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        Customer c = FileManager.getCustomer(account);

        if (c == null) {
            System.out.println("Customer account: " + account + " doesn't exist");
            return null;
        }

        System.out.println("Transaction lodge - account: " + account + " amount: " + amount + " typeAccount: " + typeAccount);


        BankingTransactionLodge btl = new BankingTransactionLodge(c, amount, typeAccount);

        return btl;

    }

    // method called to create lodge transactions - Overload
    private static BankingTransaction createTransactionLodgeForm(Customer c) {

        System.out.println("customer Transaction: lodge");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter amount:");
        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        System.out.println("Transaction lodge - account: " + c.getAccount() + " amount: " + amount + " typeAccount: " + typeAccount);

        BankingTransactionLodge btl = new BankingTransactionLodge(c, amount, typeAccount);

        return btl;

    }

    // method called to create withdraw transactions
    private static BankingTransaction createTransactionWithdrawForm() {

        System.out.println("customer Transaction: withdraw");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter account:");
        String account = myObj.nextLine();

        System.out.println("Enter amount:");

        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        System.out.println("Transaction withdraw amount: " + amount + " typeAccount: " + typeAccount);

        Customer c = FileManager.getCustomer(account);

        if (c == null) {
            System.out.println("Customer account: " + account + " doesn't exist");
            return null;
        }

        BankingTransactionWithdraw btw = new BankingTransactionWithdraw(c, amount, typeAccount);

        return btw;

    }

    // method called to create withdraw transactions - Overload
    private static BankingTransaction createTransactionWithdrawForm(Customer c) {

        System.out.println("customer Transaction: withdraw");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter amount:");
        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        System.out.println("Transaction amount: " + c.getAccount() + " typeAccount: " + typeAccount);

        BankingTransactionWithdraw btw = new BankingTransactionWithdraw(c, amount, typeAccount);

        return btw;

    }

    // method called to create transaction history
    private static int createTransactionListForm(Customer c) {

        System.out.println("Customer Transaction list");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        return typeAccount;

    }

    // method with employee menu options
    private static int bankingMenu() {

        int selection;
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------------\n");
        System.out.println("1 - Add Customer");
        System.out.println("2 - Delete Customer");
        System.out.println("3 - Create Transaction");
        System.out.println("4 - List customers");
        System.out.println("5 - Quit");

        selection = scanner.nextInt();
        return selection;

    }

    // method called to check employee pin input
    private static String getBankEmployeePin() {

        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter Pin");

        String pin = myObj.nextLine();

        return pin;
    }

    // method that saves the input information inside an array to check customer credentials
    private static String[] getCustomerCredentials() {

        String variables[] = new String[4];
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter first name");

        variables[0] = myObj.nextLine(); //first name
        System.out.println("Enter last name");
        variables[1] = myObj.nextLine(); //last name
        System.out.println("Enter bank account");
        variables[2] = myObj.nextLine(); //account name
        System.out.println("Enter PIN");
        variables[3] = myObj.nextLine(); //pin name

        return variables;
    }

    // method called to quit programme
    private static void quit() {
        System.out.println("exiting programme");
        System.exit(0);
    }

}