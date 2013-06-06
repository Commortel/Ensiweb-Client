/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensiweb.client.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thibaut
 */
public class CrypUtils {
    /**
    * Returns the SHA-1 encryption of the string parameters
    * @param str - data
    * @return the SHA-1 encryption of the string parameter
    */
    public static String getSHA1(String str)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(getBytes(str), 0, str.length());
            return getString(md.digest());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CrypUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
    * Returns a string for a byte array
    * @param str - data
    * @return a string for a byte array
    */
    public static String getString(byte[] str)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
          sb.append(Integer.toString((str[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
 
    /**
    * Returns a byte array from a string, utf-8 encoding
    * @param str - data
    * @return a byte array
    */
    public static byte[] getBytes(String str)
    {
        try {
            return str.getBytes("utf-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CrypUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
