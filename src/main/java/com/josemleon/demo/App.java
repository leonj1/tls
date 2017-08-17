package com.josemleon.demo;

import com.josemleon.demo.configs.AppProperties;
import com.josemleon.demo.configs.CommandlineParser;
import com.josemleon.demo.configs.GetEffectiveProperty;
import com.josemleon.demo.configs.GetProperty;
import com.josemleon.demo.configs.Parser;
import com.josemleon.demo.controllers.Controller;
import com.josemleon.demo.controllers.MessagesController;
import com.josemleon.demo.controllers.RestEndpoints;
import com.josemleon.demo.controllers.routes.CreateHashRoute;
import com.josemleon.demo.controllers.routes.FetchHashRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Sample Restful TLS endpoints
 * by Jose M Leon 2017
 *
 */
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);
    private static final String APPLICATION_PROPERTIES = "application.properties";

    public static void main( String[] args ) {
        log.info("Some succeed because they are destined to. Most succeed because they are determined to. -- Unknown");

        // Get properties from either cmdline or application.properties file
        AppProperties appProperties = null;
        Parser cmdlineParser = new CommandlineParser(args);
        try {
            appProperties = new AppProperties(
                    new GetEffectiveProperty(
                            new GetProperty(
                                    APPLICATION_PROPERTIES,
                                    cmdlineParser
                            ),
                            cmdlineParser
                    )
            );
        } catch (Exception e) {
            log.error(String.format("Really bad problem trying to find resource %s", APPLICATION_PROPERTIES));
            System.exit(1);
        }

        // Our Web Server
        Spark.port(appProperties.getHttpServerPort());
        if (appProperties.enableSsl()) {
            Spark.secure(
                    appProperties.getKeyStoreFilePath(),
                    appProperties.getKeyStorePassword(),
                    null,
                    null
            );
        }

        Map<String,String> myMap = new ConcurrentHashMap<>();

        // Our Endpoints
        RestEndpoints restEndpoints = new RestEndpoints(
                new Controller[]{
                        new MessagesController(
                                new CreateHashRoute(myMap),
                                new FetchHashRoute(myMap)
                        ),
                }
        );
        restEndpoints.start();

    }
}
