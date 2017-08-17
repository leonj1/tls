package com.josemleon.demo.configs;

/**
 * Created for K and M Consulting LLC.
 * Created by Jose M Leon 2016
 **/
public class AppProperties {

    private AppProperty getProperty;

    public AppProperties(AppProperty getProperty) {
        this.getProperty = getProperty;
    }

    public int getHttpServerPort() {
        return Integer.parseInt(this.getProperty.value("http.server.port"));
    }

    public String getKeyStoreFilePath() {
        return this.getProperty.value("keystore.file.path");
    }

    public String getKeyStorePassword() {
        return this.getProperty.value("keystore.password");
    }

    public String getTruststoreFilePath() {
        return this.getProperty.value("truststore.file.path");
    }

    public String getTruststorePassword() {
        return this.getProperty.value("truststore.password");
    }

    public boolean enableSsl() {
        return Boolean.parseBoolean(this.getProperty.value("enable.ssl"));
    }
}
