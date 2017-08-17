package com.josemleon.demo.controllers;

import com.josemleon.demo.controllers.routes.CreateHashRoute;
import com.josemleon.demo.controllers.routes.FetchHashRoute;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created for K and M Consulting LLC.
 * Created by Jose M Leon 2017
 **/
public class MessagesController implements Controller {
    private CreateHashRoute createHashRoute;
    private FetchHashRoute fetchHashRoute;

    public MessagesController(CreateHashRoute createHashRoute, FetchHashRoute fetchHashRoute) {
        this.createHashRoute = createHashRoute;
        this.fetchHashRoute = fetchHashRoute;
    }

    @Override
    public void expose() {
        post("/messages", this.createHashRoute);
        get("/messages/:hash", this.fetchHashRoute);
    }
}
