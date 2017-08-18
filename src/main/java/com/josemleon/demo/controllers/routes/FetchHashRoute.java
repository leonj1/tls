package com.josemleon.demo.controllers.routes;

import com.google.gson.Gson;
import com.josemleon.demo.models.ErrorMessage;
import com.josemleon.demo.models.MessageResponse;
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
public class FetchHashRoute implements Route, ExitRoute {

    private Map<String,String> myMap;
    private Gson gson;

    public FetchHashRoute(Map<String, String> myMap) {
        expect(myMap, "myMap").not().toBeNull().check();
        this.myMap = myMap;
        this.gson = new Gson();
    }

    private String execute(Response res, String hash) {

        String result;
        result = this.myMap.get(hash.toLowerCase());
        if (result == null) {
            res.status(HttpStatus.NOT_FOUND_404);
            return this.gson.toJson(new ErrorMessage("Message Not Found"));
        }

        res.status(HttpStatus.OK_200);
        return this.gson.toJson(new MessageResponse(result));
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String hash = request.params(":hash");
        if (hash == null || "".equals(hash)) {
            return this.exit(response, HttpStatus.BAD_REQUEST_400, "invalid", null);
        }
        return execute(response, hash);
    }
}
