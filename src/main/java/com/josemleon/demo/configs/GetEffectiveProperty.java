package com.josemleon.demo.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created for K and M Consulting LLC.
 * Created by Jose M Leon 2017
 **/
public class GetEffectiveProperty implements AppProperty {
    private static final Logger log = LoggerFactory.getLogger(GetEffectiveProperty.class);

    private AppProperty appProperty;
    private Parser parser;

    public GetEffectiveProperty(AppProperty appProperty, Parser parser) {
        this.appProperty = appProperty;
        this.parser = parser;
    }

    /**
     * Allow command line properties to override those in application.properties
     * @param property
     * @return
     */
    @Override
    public String value(String property) {
        Property p = this.parser.property(property);
        if (p.exists()) {
            log.debug(String.format("GetEffectiveProperty: Returning cmdline value %s for property %s", p.value(), property));
            return p.value();
        }
        log.debug(String.format("GetEffectiveProperty: Returning default value %s for property %s", this.appProperty.value(property), property));
        return this.appProperty.value(property);
    }
}
