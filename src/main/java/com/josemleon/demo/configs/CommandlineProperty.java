package com.josemleon.demo.configs;

/**
 * Created for K and M Consulting LLC.
 * Created by Jose M Leon 2017
 **/
public class CommandlineProperty implements Property {

    private String property;
    private String value;
    private boolean exists;

    public CommandlineProperty(String property, String value, boolean exists) {
        this.property = property;
        this.value = value;
        this.exists = exists;
    }

    @Override
    public boolean exists() {
        return this.exists;
    }

    @Override
    public String value() {
        return this.value;
    }
}
