package com.icon;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;

/**
 * Created with IntelliJ IDEA.
 * User: kamran
 * Date: 10/9/15
 * Time: 12:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class TOTPGenerator {


    public static void main(String[] args) throws Exception {

        String user = "Kamran";
        String host = "iCon";



        byte[] sharedSecret = "ABCDEFGHIJ".getBytes();

        String encodedSecret = new String(new Base32().encode(sharedSecret));
        System.out.println(encodedSecret);

       /* String format = "https://www.google.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s";
        System.out.println(String.format(format, user, host, encodedSecret));
*/
        long time = System.currentTimeMillis() / 1000 / 30;

        long code = generateCode(sharedSecret, time);
    }

    public static boolean validateCode (long userCode, byte[] sharedSecret, long time) throws Exception{
        return userCode == generateCode(sharedSecret, time);
    }

    private static long generateCode(byte[] sharedSecret, long time) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(sharedSecret, "HmacSHA1");
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.putLong(time);

        byte[] timeBytes = byteBuffer.array();

        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(timeBytes);
        int offset = hash[19] & 0xf;
        long truncatedHash = hash[offset] & 0x7f;
        for (int i=1; i <4; i++)
        {
            truncatedHash <<=8;
            truncatedHash |= hash[offset + i] & 0xff;
        }

        return truncatedHash %=  1000000;
    }


}
