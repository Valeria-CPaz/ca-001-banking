package com.val;

public class TestDrive_ValeriaPaz_21214 {

    public void run() {

        testCustomer();
        testEmployee();
        testAccount();
        testMyMenu();

    }

    private void testMyMenu() {
        MyMenu_ValeriaPaz_21214 menu = new MyMenu_ValeriaPaz_21214();
        menu.logIn();
    }

    private void testAccount() {

    }

    private void testEmployee() {

    }

    private void testCustomer() {

        Customer_ValeriaPaz_21214 c1 = new Customer_ValeriaPaz_21214("John", "Smith", "val.paz");
        c1.show();

    }
}
