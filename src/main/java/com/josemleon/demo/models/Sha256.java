package com.josemleon.demo.models;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created for K and M Consulting LLC.
 * Created by Jose M Leon 2017
 **/
public interface Sha256 {
    String to256() throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
