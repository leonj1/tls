package com.josemleon.demo.context;

import com.josemleon.demo.models.Sha256;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created for K and M Consulting LLC.
 * Created by Jose M Leon 2017
 **/
public class CreateHashContext implements Sha256 {
    private String message;

    public CreateHashContext(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String to256() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return bytesToHex(
                MessageDigest
                        .getInstance("SHA-256")
                        .digest(
                                this.message.getBytes("UTF-8")
                        )
        );
    }

    @Override
    public String from256() {
        StringBuilder sb = new StringBuilder();
        for (byte byt : this.message.getBytes((StandardCharsets.UTF_8))) {
            sb.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    // privates

    private String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
}
