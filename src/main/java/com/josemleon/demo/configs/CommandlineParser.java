package com.josemleon.demo.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created for K and M Consulting LLC.
 * Created by Jose M Leon 2017
 **/
public class CommandlineParser implements Parser {
    private static final Logger log = LoggerFactory.getLogger(CommandlineParser.class);

    private String args[];
    private Map<String, String> map;
    private boolean parsed;
    private Stack<String> stack;

    public CommandlineParser(String[] args) {
        this.args = args;
        this.map = new ConcurrentHashMap<>();
        this.stack = new Stack<String>();
    }

    @Override
    public Property property(String property) {
        if (!this.parsed) {
            parse();
        }

        return new CommandlineProperty(
                property,
                this.map.get(property),
                this.map.containsKey(property)
        );
    }

    private void parse() {
        if (this.parsed || this.args == null) {
            return;
        }

        boolean matched = false;
        Pattern propertiesWithEqualsPattern = Pattern.compile("-{1,2}(.*?)=(.*)");
        Pattern propertiesWithoutEqualsPattern = Pattern.compile("-{1,2}(.*?)");
        Matcher propertiesWithEqualsMatcher;
        Matcher propertiesWithoutEqualsMatcher;
        for(String a : args) {
            matched = false;
            propertiesWithEqualsMatcher = propertiesWithEqualsPattern.matcher(a);
            while (propertiesWithEqualsMatcher.find()) {
                matched = true;
                this.map.put(
                        propertiesWithEqualsMatcher.group(1),
                        propertiesWithEqualsMatcher.group(2)
                );
            }

            if (matched) {
                continue;
            }

            propertiesWithoutEqualsMatcher = propertiesWithoutEqualsPattern.matcher(a);
            while (propertiesWithoutEqualsMatcher.find()) {
                this.stack.push(propertiesWithEqualsMatcher.group(1));
            }

            // This far, could be a value to a property, only if stack is not empty
            // If stack is empty, then its application argument, that's not a property
            if (!this.stack.empty()) {
                this.map.put(
                        this.stack.pop(),
                        a
                );
            } else {
                log.debug(String.format("Found argument %s not associated with property.", a));
            }
        }

        if (!this.stack.empty()) {
            log.debug("There are command line properties without values defined.");
            while(!this.stack.empty()) {
                this.map.put(this.stack.pop(), null);
            }
        }

        this.parsed = true;
    }
}
