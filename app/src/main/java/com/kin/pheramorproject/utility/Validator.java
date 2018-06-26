package com.kin.pheramorproject.utility;

import android.util.Log;
import java.util.Date;

public class Validator {

    private static int maxHeightFeet = 8;
    private static int maxHeightInches = 11;

    public static boolean validateEmailAddress (String email) {
        return email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    public static boolean validatePassword (String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
    }

    public static boolean arePasswordsMatching (String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    // Two or more letters
    public static boolean validateName(String name) {
        return name.matches("[a-zA-Z][a-zA-Z]+");
    }

    public static boolean validateHeight (int feet, int inches) {
        if (feet > maxHeightFeet) {
            return false;
        }
        if (inches > maxHeightInches) {
            return false;
        }
        return true;
    }

    public static boolean validateZipCode (String zipCode) {
        if (zipCode.length() != 5) {
            return false;
        }
        return true;
    }

    /**
     * It's buggy. I tried different ways of validate but none seem to work.
     *
     */
    public static boolean validateDateOfBirth (int year, int month, int day) {
//        Calendar userAge = new GregorianCalendar(year, month, day);
//        Calendar minAdultAge = new GregorianCalendar();
//        minAdultAge.add(Calendar.YEAR, -18);
//        if (minAdultAge.before(userAge)) {
//            return false;
//        }
//        return true;
        Date date = new Date();
        int currentYear = date.getYear();
        int currentMonth = date.getMonth();
        int currentDay = date.getDay();
        int eighteenYearsAgo = currentYear - 18;
        year = year - 1900;
        month = month - 1;
        if (eighteenYearsAgo > year) {
            return true;
        }
        else if (eighteenYearsAgo < year) {
            return false;
        }
        else {
            if (currentMonth > month) {
                return true;
            }
            else if (currentMonth < month) {
                return false;
            }
            else {
                if (currentDay >= day) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
    }
}
