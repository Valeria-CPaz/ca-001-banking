package com.val;

public class SecurityBanking extends Security {

    public boolean login(String pinInput) {

        if (pinInput.equals("A1234")) {
            return true;
        }

        return false;
    }
}
