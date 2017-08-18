package com.josemleon.demo.controllers.routes;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.josemleon.demo.context.CreateHashContext;
import com.josemleon.demo.models.DigestResponse;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect;

/**
 * Created for K and M Consulting LLC.
 * Created by Jose M Leon 2017
 **/
public class CreateHashRoute implements Route, ExitRoute {

    private Map<String,String> myMap;
    private Gson gson;

    public CreateHashRoute(Map<String, String> myMap) {
        expect(myMap, "myMap").not().toBeNull().check();
        this.myMap = myMap;
        this.gson = new Gson();
    }

    private String execute(Response res, String payload) {
        CreateHashContext context;
        try {
            context = this.gson.fromJson(payload, CreateHashContext.class);
        } catch (JsonSyntaxException e) {
            return this.exit(res, HttpStatus.NOT_FOUND_404, e.getMessage(), e);
        }
        String converted = null;

        try {
            context.toLowercase();
            converted = context.to256();
            this.myMap.put(converted, context.getMessage());
        } catch (Exception e) {
            return this.exit(res, HttpStatus.BAD_REQUEST_400, "unable to convert", null);
        }

        res.status(HttpStatus.CREATED_201);
        return this.gson.toJson(new DigestResponse(converted));
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return execute(response, request.body());
    }
}
