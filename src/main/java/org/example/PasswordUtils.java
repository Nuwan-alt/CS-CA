package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
    public String createPassword(String password) {
        String md5Hash = null;
        try {
            // Create a MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Convert the password to bytes
            byte[] passwordBytes = password.getBytes();

            // Calculate the MD5 hash
            byte[] hashBytes = md.digest(passwordBytes);

            // Convert the byte array to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte hashByte : hashBytes) {
                sb.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
            }

            md5Hash = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Hash;
    }

    public boolean checkPasswordMD5(String inputPassword, String storedHash) {
        String inputHash = this.createPassword(inputPassword);
        return storedHash.equals(inputHash);
    }
}
