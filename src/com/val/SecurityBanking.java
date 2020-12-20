package com.val;

public class SecurityBanking extends Security {

    // inherit the class Security and checks credentials for employee login
    public boolean login(String pinInput) {

        if (pinInput.equals("A1234")) {
            return true;
        }

        return false;
    }
}
