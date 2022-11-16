package com.student.ust.util;

import com.student.ust.exception.InvalidEmailException;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Ust util.
 */
public class UstUtil {

    /**
     * Valid email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean validEmail(String email){
        String Regex="^([A-Za-z0-9+_.-]+)@([a-z]+)\\.([a-z]+)$";
        Pattern pattern = Pattern.compile(Regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valid password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean validPassword(String password) {
        String passRegex =  "^(?=(?:.*\\d){3,})(?=\\S+$)(?=.*[@#$%^&+=])" +
                "(?=(?:.*[A-Za-z]){3,})(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(passRegex);
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Hash password string.
     *
     * @param password the password
     * @return the string
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException{

        String hexPassword = toHexString(getSHA(password));
        return hexPassword;

    }

    /**
     * Get sha byte [ ].
     *
     * @param input the input
     * @return the byte [ ]
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * To hex string string.
     *
     * @param hash the hash
     * @return the string
     */
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);
        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));
        // Pad with leading zeros
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}
