package com.val;

import java.util.Scanner;

public class TestDrive {

    public void run() {

//        testCustomer();
//        testEmployee();
//        testAccount();


        int userChoice;


        do
        {
            userChoice = TestDrive.startMenu();

            switch (userChoice) {

                case AppConstants.EMPLOYEE:
                    String pin = getBankEmployeePin();
                    SecurityBanking sb = new SecurityBanking();
                    boolean isAuthenticated = sb.login(pin);

                    if (isAuthenticated)
                    {
                        int bankEmployeeAction;

                        do
                        {
                            bankEmployeeAction = TestDrive.bankingMenu();

                            switch (bankEmployeeAction)
                            {
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

                                    do
                                    {
                                        transactionChoice = TestDrive.transactionMenu();

                                        switch (transactionChoice)
                                        {
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
                                                System.out.println("The transactionChoice was invalid!");
                                        }

                                    }
                                    while(transactionChoice != 2);

                                    break;

                                case AppConstants.CUSTOMER_LIST:
                                    BankingAction.listCustomersBalance();
                                    //BankingAction.listCustomers();
                                    break;
                                case AppConstants.QUIT:
                                    startMenu();
                                    break;
                                default:
                                    System.out.println("The transactionChoice was invalid!");
                            }

                        }
                        while(bankEmployeeAction != 5);

                    }
                    else
                    {
                        System.out.println("Authentication error");
                        TestDrive.bankingMenu();
                        return;
                    }

                    break;

                case AppConstants.CUSTOMER:

                    // Get the banking Pin from employee
                    //String pin = getBankEmployeePin();

                    SecurityCustomer sc = new SecurityCustomer();
                    String[] credentials = getCustomerCredentials();

                    boolean isCustomerAuthenticated = sc.getCredentials(credentials[0], credentials[1], credentials[2], credentials[3]);

                    if (isCustomerAuthenticated)
                    {
                        Customer c = FileManager.getCustomer(credentials[2]);

                        int customerAction;

                        do
                        {
                            customerAction = TestDrive.customerMenu();

                            switch(customerAction)
                            {
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
                                    System.out.println("The customerAction was invalid!");
                            }
                        }
                        while(customerAction != 5);

                    }
                    else
                    {
                        System.out.println("error....");
                        return;
                    }

                    break;

                case 3:
                    Quit();
                    break;
                case 4:
                    return;
            }

        }
        while(userChoice != 4);

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

        System.out.println("1 - Lodge/Deposit ");
        System.out.println("2 - Withdraw");
        System.out.println("3 - List transactions");
        System.out.println("4 - Quit");

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
        System.out.println("2 - Withdraw Money");

        selection = scanner.nextInt();
        return selection;
    }

    private static void deleteCustomer() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------------\n");
        System.out.println("Type the number account");

        String account = scanner.nextLine();

    }

    private static BankingTransaction createTransactionLodgeForm() {

        System.out.println("Transaction lodge form goes here");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the account:");
        String account = myObj.nextLine();

        System.out.println("Enter the amount:");
        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        Customer c = FileManager.getCustomer(account);

        if (c == null)
        {
            System.out.println("Customer account: "+ account +" doesn't exist");
            return null;
        }

        System.out.println("Transaction lodge - account: "+ account +" amount: "+ amount +" typeAccount: "+ typeAccount +" ");


        BankingTransactionLodge btl = new BankingTransactionLodge(c, amount, typeAccount);

        return btl;

    }

    private static BankingTransaction createTransactionLodgeForm(Customer c) {

        System.out.println("Customer Transaction lodge form goes here");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the amount:");
        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        System.out.println("Transaction lodge - account: "+ c.getAccount() +" amount: "+ amount +" typeAccount: "+ typeAccount +" ");

        BankingTransactionLodge btl = new BankingTransactionLodge(c, amount, typeAccount);

        return btl;

    }

    private static BankingTransaction createTransactionWithdrawForm() {

        System.out.println("Transaction lodge form goes here");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the account:");
        String account = myObj.nextLine();

        System.out.println("Enter the amount:");

        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        System.out.println("Transaction lodge amount: "+ amount +" typeAccount: "+ typeAccount +" ");

        Customer c = FileManager.getCustomer(account);

        if (c == null)
        {
            System.out.println("Customer account: "+ account +" doesn't exist");
            return null;
        }

        BankingTransactionWithdraw btw = new BankingTransactionWithdraw(c, amount, typeAccount);

        return btw;

    }

    private static BankingTransaction createTransactionWithdrawForm(Customer c) {

        System.out.println("customer Transaction lodge form goes here");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the amount:");
        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        System.out.println("Transaction lodge amount: "+ c.getAccount() +" typeAccount: "+ typeAccount +" ");

        BankingTransactionWithdraw btw = new BankingTransactionWithdraw(c, amount, typeAccount);

        return btw;

    }

    private static int createTransactionListForm(Customer c) {

        System.out.println("Customer Transaction list");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        //System.out.println("Transaction list amount: "+ c.getAccount() +" typeAccount: "+ typeAccount +" ");

        return typeAccount;

    }


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

    private static String getBankEmployeePin() {

        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter Pin");

        String pin = myObj.nextLine();

        return pin;
    }

    private static String[] getCustomerCredentials() {

        String v[] = new String[4];
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter first name");

        v[0] = myObj.nextLine(); //first name
        System.out.println("Enter last name");
        v[1] = myObj.nextLine(); //last name
        System.out.println("Enter bank account");
        v[2] = myObj.nextLine(); //account name
        System.out.println("Enter PIN");
        v[3] = myObj.nextLine(); //pin name

        return v;
    }

    private static void Quit()
    {
        System.out.println("Good bye...");
        System.exit(0);
    }

}
